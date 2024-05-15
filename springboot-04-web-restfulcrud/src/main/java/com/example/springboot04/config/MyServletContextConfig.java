package com.example.springboot04.config;

import com.example.springboot04.servlet.MyServlet1;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @className: MyServletContextConfig
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/15 23:29
 * @Version: 1.0
 */
/*
*测试
* 访问localhost:8083/crud/my_servlet1?name="abc"
*
*
* */
@Component
public class MyServletContextConfig implements ServletContextInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		ServletRegistration initServlet = servletContext.addServlet("myServlet1", MyServlet1.class);
		initServlet.addMapping("/my_servlet1");
	}
}
