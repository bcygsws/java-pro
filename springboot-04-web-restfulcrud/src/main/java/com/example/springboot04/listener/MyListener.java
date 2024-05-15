package com.example.springboot04.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @className: MyListener
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/15 21:56
 * @Version: 1.0
 */
public class MyListener implements ServletContextListener {
	// 测试：
	// 项目重启，控制台打印 contextInitialized web启动了
	// 使用退出或者停止（shift+F5）,控制台打印 contextDestroyed web项目销毁了
	// 说明：我们自定义的Listener确实生效了


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized web启动了");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed web项目销毁了");

	}
}
