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
* 表示已经另外 提供 外部环境，大宝时，不必带上tomcat了
* <dependency>
*       <groupId>org.springframework.boot</groupId>
*       <artifactId>spring-boot-starter-tomcat</artifactId>
*       <scope>provided</scope>// 表示目标环境，已经提供了，打包时，就不必带上tomcat
* </dependency>
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
* 4.启动服务器就可以使用了
*
*
*
*
* */