package com.example.springboot04.controller;

import com.example.springboot04.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: MyExceptionHandler
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/14 20:58
 * @Version: 1.0
 */
@ControllerAdvice
public class MyExceptionHandler {

	// 情形一：浏览器和客户端中，都返回的是json数据

	// 响应到页面或客户端中,添加@ResponseBody注解
	// @ResponseBody
	// @ExceptionHandler(UserNotExistException.class)
	// public Map<String, Object> handleException(Exception e) {
	// 	Map<String, Object> map = new HashMap<>();
	// 	map.put("code", "user.notexist");
	// 	map.put("message", e.getMessage());// 异常信息，从参数e中拿到，e.getMessage()方法
	// 	return map;
	// 	// 	有@ResponseBody注解，返回map;直接在页面上响应一个json对象了，再也不是错误页面了
	// }

	// 情形二、让异常处理器，不返回map；而是返回【转发请求】，可以实现异常出现时，浏览器端返回页面，客户端返回json
	/*
	 *
	 * 让它转发请求/error，实现异常出现时，浏览器返回错误页面，客户端返回json数据，重启项目测试
	 * 测试结果：但是，浏览器返回的页面不再是，自己配置的4xx.html或者5xx.html,而是默认页面,status 是200(而自己设置的错误页面都是4xx,5xx)
	 * 分析：
	 * 1.实际上是有BasicErrorController控制器处理/error,里面有两个方法，errorHtml()处理浏览器、 error()处理客户端
	 * 伪代码a：
	 * public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
	 * HttpStatus status = this.getStatus(request);
	 * a.观察其中的getStatus()方法
	 *
	 * 伪代码b:
	 * protected HttpStatus getStatus(HttpServletRequest request) {
	 * Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
	 * b.是从request请求中获取statusCode的
	 *
	 * 2.BasicErrorController类的作用，就是用来处理浏览器和客户端/error请求的
	 * 根据1，配置这个属性javax.servlet.error.status_code,就是指定自定义 状态码
	 * request.setAttribute("javax.servlet.error.status_code", 500);
	 *
	 * 再次重启项目，测试发现：
	 * 访问：http://localhost:8080/crud/hel?user=aaa，用到是自定义的错误页面5xx.html，不再是默认页面了
	 * 访问，客户端postman或者ApiFox，发送请求，返回的是json数据
	 *
	 * {
	 *   "timestamp": "2024-05-14T15:22:57.071+00:00",
	 *   "status": 500,
	 *   "error": "Internal Server Error",
	 *   "exception": "com.example.springboot04.exception.UserNotExistException",
	 *   "message": "用户不存在",
	 *   "path": "/crud/hel"
	 *  }
	 *
	 * 至此，当异常出现时，访问浏览器返回自定义的错误页面；访问客户端返回自己定制的json数据，功能实现
	 *
	 * */
	// @ExceptionHandler(UserNotExistException.class)
	// // public String handleException(Exception e) {
	// public String handleException(Exception e, HttpServletRequest request) {
	// 	request.setAttribute("javax.servlet.error.status_code", 500);
	// 	Map<String, Object> map = new HashMap<>();
	// 	map.put("code", "user.notexist");
	// 	map.put("message", "用户出错了");// 异常信息，从参数e中拿到，e.getMessage()方法
	// 	return "forward:/error";
	// }


	/*
	 *
	 *  情形三：情形二中基本实现了功能，也能获取到自定义的status_code 500;但是异常中的数据没有获取到当前类handleException中定义的
	 *  code和message信息;比如：是UserNotExistException中传入的"用户不存在"，而不是自己定义的；继续改进？
	 *
	 * 出现错误以后，ErrorPageCustomizer会组织成/error请求，交给BasicErrorController处理，响应获得的数据是由getErrorAttributes()
	 * 得到的
	 * 分析1.getErrorAttributes()是AbstractErrorController类中的方法---而这个类实现了ErrorController接口---ErrorController出现在
	 * ErrorMvcAutoConfiguration自动配置类的注解中,来控制BasicDController是否生效
	 *
	 *  @ConditionalOnMissingBean(
	 *     value = {ErrorController.class},
	 *    search = SearchStrategy.CURRENT
	 * )
	 * public BasicErrorController basicErrorController(ErrorAttributes errorAttributes, ObjectProvider<ErrorViewResolver> errorViewResolvers) {
	 * 1.总结成方式一:重新编写一个ErrorController类，或者重写AbstractErrorController的几个方法，然后把它们放到容器中（这种方式繁琐）
	 *
	 * 分析2；DefaultErrorAttributes---实现了ErrorAttributes接口---getErrorAttributes是接口ErrorAttributes里定义的方法
	 *
	 * 2.总结成方式二：ErrorAttributes接口中定义了方法getErrorAttributes,
	 * DefaultErrorAttributes实现了该接口；若重写该类(DefaultErrorAttributes)中getErrorAttributes的也能达到效果
	 *
	 * 实现：
	 * 1.在component/路径下，新建MyErrorAttributes类，并让它继承DefaultErrorAttributes,以方便重写父类中的getErrorAttributes方法
	 * 2.alt+insert,插入getErrorAttributes()方法后，发现该方法的返回值是Map类型
	 * 3.使用map.put("自己添加字段"，val)方法，可以给错误请求，添加新的字段（原有的status、timestamp、exception等字段）
	 * 例如：map.put("company","Microsoft");
	 * 重启项目，每一次异常请求，都返回带有该company字段
	 *
	 * 于是：
	 * 通过自定义异常处理类（MyExceptionHandler），定制了ext字段
	 * 而通过重写继承自DefaultErrorAttributes的getErrorAttributes()方法，定制了company字段
	 *
	 *
	 * */
	@ExceptionHandler(UserNotExistException.class)
	public String handleException(Exception e, HttpServletRequest request) {
		request.setAttribute("javax.servlet.error.status_code", 500);
		Map<String, Object> map = new HashMap<>();
		map.put("code", "user.notexist");
		map.put("message", "用户出错了");// 异常信息，从参数e中拿到，e.getMessage()方法
		// 把异常处理的map，放到请求参数中；在MyErrorAttributes中取出，重新整合那里返回的map
		request.setAttribute("ext", map);
		return "forward:/error";
	}


}
