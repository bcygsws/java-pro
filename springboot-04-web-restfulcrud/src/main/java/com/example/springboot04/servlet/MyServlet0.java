package com.example.springboot04.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: MyServlet0
 * @description: 自定义servlet组件：MyServlet0，将使用@WebServlet注解注册该servlet
 * 参考文档：https://www.cnblogs.com/yihuihui/p/11925749.html
 * @author: Bao Chengyi
 * @date: 2024/5/15 23:44
 * @Version: 1.0
 */
@WebServlet(urlPatterns = "/my_servlet0")
public class MyServlet0 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// /my_servlet0?name=abc
		// 获取查询参数name的值
		String name = req.getParameter("name");
		PrintWriter writer = resp.getWriter();
		writer.write("welcome @WebServlet注解注册servlet组件" + name);
		writer.flush();
		writer.close();
	}
}

/*
 *
 *  为了使得使用@WebServlet注解 注册servlet生效，当前自动启动类添加@ServletComponentScan注解
 *  @ServletComponentScan
 *  0.
 *
 * */