package com.example.springboot04.servlet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: MyServlet2
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/16 5:02
 * @Version: 1.0
 */
/*
*
* 测试
* 第四种方式没有mapping处理具体的请求
* 1.写两个组件MyServlet2 MyServlet3后，将各自组件id加入路径，就可以访问到页面，并正常输出内容：servlet注册方式四：abc
* http://localhost:8083/crud/?name=abc       404
* http://localhost:8083/crud/myServlet2?name=abc    正常打印结果
* http://localhost:8083/crud/myServlet3?name=abc    正常打印结果
*
* 2.单个组件，加/bean的id/访问404时；两个组件了，分别都能正常访问，并正常显示内容；
* 将MyServlet3上的@Component注解注释掉，并且在MyServlet2类上，加上Order(-1000)注解，提高myServlet2组件的优先级
* 此时访问：
* http://localhost:8083/crud/?name=abc
* http://localhost:8083/crud/myServlet2/?name=abc
* 都可以正常访问，并显示内容：servlet注册方式四：abc
*
* */
@Order(-1000)
@Component
public class MyServlet2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取查询参数name,String类型（/my_servlet2?name=值）
		String name = req.getParameter("name");
		// resp.setCharacterEncoding("UTF-8");// 可以省略，tomcat默认就是以utf-8编码的方式对相应数据进行编码
		// 为浏览器接收数据解析时的编码格式为UTF-8，以防止：显示页面上的中文乱码
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write("servlet注册方式四，利用@Order注解提高优先级：" + name);
		writer.flush();
		writer.close();

	}
}
