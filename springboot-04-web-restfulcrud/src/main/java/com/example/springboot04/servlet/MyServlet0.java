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
 * @description: 【方式一:使用@WebServlet注解的方式，注册servlet组件】，主配置类，需要添加@ServletComponentScan注解接收
 * 自定义servlet组件：MyServlet0，将使用@WebServlet注解注册该servlet
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
		// 解决页面显示内容时中文乱码，分两步（在writer实例化之前【PrintWriter writer语句之前】，增加这两处设置，不然仍无法解决中文乱码）：
		// 参考文档（解决页面中文乱码）：https://blog.csdn.net/weixin_45680962/article/details/106038698
		// 1.将数据以utf-8编码的方式发出去，tomcat默认采用的就是utf-8,所以步骤1的语句可以省略（注释掉后测试，中文正常显示）
		// resp.setCharacterEncoding("UTF-8");
		// 2.告诉浏览器以什么样的编码方式解析服务器返回的数据
		// 在浏览器network中查看，发现浏览器的响应头文件中Content-Type字段的值被修改为"text/html; charset=UTF-8"，而不是默认的text/htm;charset=ISO-889-1
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.write("welcome @WebServlet注解注册servlet组件：" + name);
		writer.flush();
		writer.close();
	}
}

/*
 *
 *  为了使得使用@WebServlet注解 注册servlet生效，当前自动启动类添加@ServletComponentScan注解
 *  @ServletComponentScan
 *  测试
 *  访问：http://localhost:8083/crud/my_servlet0?name=abc
 *  页面显示了内容（但中文乱码）
 *  welcome @WebServlet????servlet??abc
 *  参考文档（解决页面中文乱码）：https://blog.csdn.net/weixin_45680962/article/details/106038698
 *  1.将数据以utf-8编码的方式发出去，tomcat默认采用的就是utf-8,所以步骤1的语句可以省略（注释掉后测试，中文正常显示）
 *  resp.setCharacterEncoding("UTF-8");
 *  2.告诉浏览器以什么样的编码方式解析服务器返回的数据
 *  在浏览器network中查看，发现浏览器的响应头文件中Content-Type字段的值被修改为"text/html; charset=UTF-8"，而不是默认的text/htm;charset=ISO-889-1
 *
 *
 * */