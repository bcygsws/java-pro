package com.example.springboot04.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: LoginHandlerInterceptor
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/11 6:35
 * @Version: 1.0
 */
// 登录检查，没有登录的用户
public class LoginHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object user = request.getSession().getAttribute("loginUser");
		if (user == null) {
			// 没有登录成功，返回到登录页面，使用请求转发器getRequestDispatcher
			request.setAttribute("msg", "没有权限，请先登录！");
			request.getRequestDispatcher("/login.html").forward(request, response);
			return false;

		} else {// 登录成功,放行请求
			return true;

		}
	}
}
