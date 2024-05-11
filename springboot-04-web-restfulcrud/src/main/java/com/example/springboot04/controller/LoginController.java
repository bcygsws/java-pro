package com.example.springboot04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @className: LoginController
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/11 4:46
 * @Version: 1.0
 */
@Controller
public class LoginController {
	/*
	 *  home()方法处理登录成功或失败后，页面的转向
	 * 有三个参数;
	 * username 请求参数
	 * password 请求参数
	 * map 存放，请求失败时的message
	 * session参数，类型HttpSession，将渲染在登录成功的页面上
	 *
	 * session有两个作用：
	 * 1.在loginHandlerInterceptor拦截器类中，用于判别是否登录成功（只有登录成功的分支中，才传入了session）
	 * 2.携带的信息，可以在thymeleaf模板中渲染，${session.loginUser},以指明当前登录账号的信心
	 *
	 *
	 *
	 **/
	// 处理登录后，要跳转的页面
	// @RequestMapping(value = "/user/login", method = RequestMethod.GET)
	// RequestMapping这个注解写起来长，为了简化直接用@PostMapping这个注解（其他三个注解@GetMapping、@DeleteMapping、@PutMapping都是存在的）
	@PostMapping(value = "/user/login")
	public String home(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map, HttpSession session) {
		// 登录密码先做一个简单处理，规则为：用户名不限，密码必须为123456
		if (!StringUtils.isEmpty(username) && password.equals("123456")) {// 符合自定义的登录规则
			session.setAttribute("loginUser", username);
			// 为了防止表单重复提交，最好的方法是，使用重定向（登录成功，重定向；好处：地址栏也变成了/main.html）；先重定向到main.html--->映射到dashbord.html
			return "redirect:/main.html";
		} else {// 登录失败了，还应该重写回到登录界面，以再次输入正确的用户名和密码
			map.put("msg", "用户名或密码错误，请重新登录！");
			return "login";
		}
	}
}
