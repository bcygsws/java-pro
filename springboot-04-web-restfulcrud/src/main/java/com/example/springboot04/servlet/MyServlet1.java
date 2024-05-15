package com.example.springboot04.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @className: MyServlet1
 * @description: servlet注册方式三（不常用，ServletContextInitializer方式完成注册）：自定义的servlet组件：MyServlet1,使用
 * config/MyServletContextConfig类完成注册
 * @author: Bao Chengyi
 * @date: 2024/5/15 23:13
 * @Version: 1.0
 */
public class MyServlet1 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		PrintWriter writer = resp.getWriter();
		writer.write("welcome ServletContextInitializer" + name);
		System.out.println("使用ServletContext方式注册的servlet组件生效了");
		writer.flush();
		writer.close();
	}
}
