package com.example.springboot04.servlet;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: MyServlet3
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/16 5:02
 * @Version: 1.0
 */

// @Component
public class MyServlet3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取查询参数name,String类型（/my_servlet2?name=值）
		String name = req.getParameter("name");
		// resp.setCharacterEncoding("UTF-8");// 可以省略，tomcat默认就是以utf-8编码的方式对相应数据进行编码
		// 为浏览器接收数据解析时的编码格式为UTF-8，以防止：显示页面上的中文乱码
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write("servlet注册方式四：" + name);
		writer.flush();
		writer.close();

	}
}
