package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot04WebJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04WebJspApplication.class, args);
	}

}
/*
 *
 * 十、使用外部servlet容器的步骤
 * 1.创建一个war项目
 * 包括在【项目结构】添加类路径下webapp，和webapp/WEB-INF的web.xml文件
 * 部署，配置本地tomcat
 *
 * 2.将嵌入式的tomcat指定为provided
 * 表示已经另外 提供 外部环境，打包时，不必带上tomcat了
 * <dependency>
 *       <groupId>org.springframework.boot</groupId>
 *       <artifactId>spring-boot-starter-tomcat</artifactId>
 *       <scope>provided</scope>// 表示目标环境，已经提供了，打包时，就不必带上tomcat
 * </dependency>
 *
 * 注：在properties配置文件中，需要配置如下：（以方便controller层中拼串成视图名称，将视图名称+结果交给model and view,然后前端控制器dispatcher servlet
 * 将其交给视图解析器viewResolver解析）
 * spring.mvc.view.prefix=/WEB-INF/
 * spring.mvc.view.suffix=.jsp
 *
 * 3.必须编写一个ServletInitializer类，没有这个类项目无法启动
 * 这个IDEA 在建立war包的项目时，自动生成了；不需要我们额外编写
 * 伪代码：
 *
 * public class ServletInitializer extends SpringBootServletInitializer {
 *    @Override
 *    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
 *         return application.sources(Springboot04WebJspApplication.class);
 *    }
 *  }
 *
 * 注；继承SpringBootServletInitializer的类里，重写了configure方法，会自动忽略内置的tomcat
 *
 * 4.启动服务器就可以使用了
 *
 * bug: 使用外部的tomcat时，一定要注意版本兼容问题
 * 当前使用spring boot 2.6.13 +tomcat 9.0.89,之前配置的tomcat10会出现controller层不生效的情况：
 * 在controller/文件里，已经确实处理了/abc请求，hello.jsp跳转success.jsp时，始终报404错误
 * 参考文档：
 * https://blog.csdn.net/qq_47901630/article/details/128301561?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-128301561-blog-77425043.235^v43^control&spm=1001.2101.3001.4242.2&utm_relevant_index=4
 *
 * bug解决：卸载tomcat10，安装tomcat9后，问题解决；/abc请求可以正常访问了
 *
 *
 *
 *
 *
 * */