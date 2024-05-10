package com.example.springboot04.config;

import com.example.springboot04.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
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

	// 添加一个组件webMvcConfigurer,来控制程序的默认访问到login.html(/、/login.html路径，都访问到templates中的登录页面)
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		WebMvcConfigurer webMvcConfigurer;
		// 由于webMvcConfigurer是一个接口，实例化时需要实现；我们需要添加视图映射，就重写其中的addViewControllers()方法
		webMvcConfigurer = new WebMvcConfigurer() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/login.html").setViewName("login");

			}
		};
		return webMvcConfigurer;
	}

	// 登录切面国际化，切换中、英文，自定义的MyLocaleResolver需要添加到容器中，在config/MyMvcConfig配置类中，将这个组件添加到容器中
	// 重写localeResolver方法；spring boot为我们自动配置的localeResolver(此时，切换浏览器语言，界面中英文切换已经不能用了，
	// 原因是：spring boot为我们自动配置的localResolver已经被自定义的覆盖掉了)
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}

}