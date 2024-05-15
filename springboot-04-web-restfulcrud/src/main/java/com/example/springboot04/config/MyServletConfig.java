package com.example.springboot04.config;

import com.example.springboot04.filter.MyFilter;
import com.example.springboot04.listener.MyListener;
import com.example.springboot04.servlet.MyServlet;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

/**
 * @className: MyServletConfig
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/15 19:33
 * @Version: 1.0
 */
// 使用servlet提供的ServletRegistrationBean、FilterRegistrationBean和ServletListenerRegistrationBean注册servlet
@Configuration
public class MyServletConfig {
	// 功能：注册Servlet三大组件
	// 参考：SpringBoot系列教程web篇Servlet 注册的四种姿势，https://www.cnblogs.com/yihuihui/p/11925749.html

	// 1.注册servlet
	// 访问：http://localhost:8083/crud/my_servlet，就可以在页面中显示内容：show myServlet!
	@Bean
	public ServletRegistrationBean<Servlet> myServlet() {
		return new ServletRegistrationBean<Servlet>(new MyServlet(), "/my_servlet");
	}

	// 2.注册Filter
	// 测试：
	// 项目重启，访问/my_servlet 或者/hello ,控制台打印 MyFilter process……
	// 说明：我们自定义的Filter确实生效了
	@Bean
	public FilterRegistrationBean<Filter> myFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/my_servlet"));
		return filterRegistrationBean;

	}

	// 3.注册Listener

	// 测试：
	// 项目重启，控制台打印 contextInitialized web启动了
	// 使用退出或者停止（shift+F5）,控制台打印 contextDestroyed web项目销毁了
	// 说明：我们自定义的Listener确实生效了
	@Bean
	public ServletListenerRegistrationBean<EventListener> myListener() {
		return new ServletListenerRegistrationBean<EventListener>(new MyListener());
	}

	// 把定制servlet容器，用到的WebServerFactoryCustomizer<ConfigurableWebServerFactory>类代码，汇总到这里
	// 功能：添加一个EmbeddedServletContainerCustomizer组件，修改和定制Servlet容器（等价于主配置文件中的server.xxx的一系列设置）
	// @Bean
	// public EmbeddedServletContainerCustomizer myEmbeddedServletContainerCustomizer() {
	// 	return new EmbeddedServletContainerCustomizer();
	// }

	// 注：新版本中 嵌入式容器定制类EmbeddedServletContainerCustomizer已经被弃用，使用了新的接口WebServerFactoryCustomizer,
	// 泛型设置为ConfigurableServletWebServerFactory,以使用一系列的设置方法setXXX()
	// 参考文档：https://blog.csdn.net/qq_43843951/article/details/108049897
	// 重启项目后，控制台提示：“Tomcat started on port(s): 8083 (http) with context path '/crud'”，已经在8083端口启动了服务器
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> myWebServerFactoryCustomizer() {
		// 接口需要实现后，才能实例化
		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
			@Override
			public void customize(ConfigurableServletWebServerFactory factory) {
				factory.setPort(8083);
			}
		};
	}
}