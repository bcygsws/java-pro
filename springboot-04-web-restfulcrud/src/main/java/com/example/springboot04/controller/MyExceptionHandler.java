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
	// 	@ResponseBody
	@ExceptionHandler(UserNotExistException.class)
	// public String handleException(Exception e) {
	public String handleException(Exception e, HttpServletRequest request) {
		request.setAttribute("javax.servlet.error.status_code", 500);
		Map<String, Object> map = new HashMap<>();
		map.put("code", "user.notexist");
		map.put("message", e.getMessage());// 异常信息，从参数e中拿到，e.getMessage()方法
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
		return "forward:/error";
	}

}
