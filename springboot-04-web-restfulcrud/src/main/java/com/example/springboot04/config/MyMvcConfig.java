package com.example.springboot04.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @className: MyMvcConfig
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/9 3:56
 * @Version: 1.0
 */
// 步骤1：是一个配置类
// 步骤2：是WebMvcConfigurerAdapter,里面有很多代表各种功能的空方法，用它来扩展spring MVC就可以了

// @EnableWebMvc
// 	添加该注解，意味着spring boot对spring mvc所有自动配置全部失效，交给spring mvc全面接管，所有东西需要用户自己配置，最直接的后果是
// 静态资源访问不到了，比如：localhost:8080/css/based.css，会成为404页面
@Configuration
// public class MyMvcConfig extends WebMvcConfigurerAdapter {
// 	// shift两次点击，查看WebMvcConfigurerAdapter，发现它是一个抽象类，让MyMvcConfig继承它就可以了
// 	// 选中类名，然后右键 生成…… 或者快捷键alter+insert，为它重写方法或者添加构造方法/setter依赖等等
// 	// 为它添加一个视图映射
// 	@Override
// 	public void addViewControllers(ViewControllerRegistry registry) {
// 		registry.addViewController("/atguigu").setViewName("success");
// 	}
//
// }

// 当然在在2.x版本，WebMvcConfigurerAdapter已经被弃用，
public class MyMvcConfig implements WebMvcConfigurer {
	// shift两次点击，查看WebMvcConfigurerAdapter，发现它是一个抽象类，让MyMvcConfig继承它就可以了
	// 选中类名，然后右键 生成…… 或者快捷键alter+insert，为它重写方法或者添加构造方法/setter依赖等等
	// 为它添加一个视图映射
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// /atguigu路径中，没有添加数据，所有跳转到了success.html页面，但是没有填充数据
		registry.addViewController("/atguigu").setViewName("success");
	}

}