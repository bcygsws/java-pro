package com.example.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// 可以在pom文件中，设置spring-boot-starter-tomcat scope为provided;实际上，继承自SpringBootServletInitializer类的configure方法重写后，
// 自动忽略内置的tomcat
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// application.sources(传入主配置类)
		return application.sources(Springboot04WebJspApplication.class);
	}

}
