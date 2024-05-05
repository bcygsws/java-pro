package com.example.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemoApplication {
	// run方法，指示spring boot应用启动起来、

	/**
	 * @run方法 SpringApplication.run()
	 * 两个参数：
	 * 1.传入当前应用的类名
	 * 2.传入main方法中的args参数数组
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
