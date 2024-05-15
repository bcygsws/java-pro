package com.example.springboot04.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: MyServlet
 * @description: servlet注册方式二（常用方式，ServletRegistrationBean方式）：自定义的servlet组件：MyServlet,在MyServletConfig类完成注册
 * @author: Bao Chengyi
 * @date: 2024/5/15 19:27
 * @Version: 1.0
 */
public class MyServlet extends HttpServlet {

	@Override
	// 处理get请求
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("show myServlet!");
	}
}
