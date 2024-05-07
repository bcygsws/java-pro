package com.example.springboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot04WebRestfulcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot04WebRestfulcrudApplication.class, args);
	}

}
/*
 *
 * @spring boot对静态资源映射规则
 * 快捷键：
 * 双击shift，搜索类或者接口
 *
 * 代码片段来自WebMvcAutoConfiguration.class(双击shift键搜索类或者接口)
 *
 * public void addResourceHandlers(ResourceHandlerRegistry registry) {
 *           if (!this.resourceProperties.isAddMappings()) {
 *              logger.debug("Default resource handling disabled");
 *          } else {
 *              this.addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
 *              this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
 *                  registration.addResourceLocations(this.resourceProperties.getStaticLocations());
 *                  if (this.servletContext != null) {
 *                      ServletContextResource resource = new ServletContextResource(this.servletContext, "/");
 *                      registration.addResourceLocations(new Resource[]{resource});
 *                  }
 *
 *                });
 *            }
 *       }
 * 一、所有【/webjars/xx,xx代表星星】请求，都是这里（"classpath:/META-INF/resources/webjars/"）找资源
 * 什么是webjars？
 * webjars：是以jar包的方式引入静态资源
 * 网址：www.webjars.org
 * 1.1 例如：以jar包的方式在pom文件中引入jquery
 * <dependency>
 *  <groupId>org.webjars.npm</groupId>
 *   <artifactId>jquery</artifactId>
 *     <version>3.3.1</version>
 * </dependency>
 *
 * 1.2 刷新一下maven，下载jquery的依赖
 * 1.3 将项目运行起来
 * 1.4 复制jquery.js所在的路径:webjars/jquery/3.3.1/dist/jquery.js
 * 使用url地址：http://localhost:8080/webjars/jquery/3.3.1/dist/jquery.js 就可以访问到jquery.js静态资源
 *
 * 二、【/xx】访问当前项目的资源（静态资源的文件夹，也就是把静态资源放在以下这些目录中）
 *  类路径classpath是指main下java或者resources所在路径，都是类路径根路径
 *  "classpath:/META-INF/resources/"       在项目原有的resources下创建META-INF文件夹
 *  "classpath:/resources/"                在项目原有的resources下创建resources文件夹
 *  "classpath:/static/"                   在项目原有的resources下创建static文件夹
 *  "classpath:/public/"                   在项目原有的resources下创建public文件夹
 *
 * 例如：项目创建时自带的static/index.html
 * 1.项目启动起来
 * 2.然后localhost:8080/index.html 就可以访问主页(自己书写的js html css文件，放在static下也能一样访问到)
 * 注：所有的静态资源访问地址，都不包括resources static 和public这些文件夹，在WebProperties.class源码中就是在这些文件夹之下寻找静态资源
 *
 * 三、欢迎页,静态资源文件下的index.html，也是放在static文件夹，被类路径/xx映射
 * private Resource getWelcomePage() {
 *       String[] var1 = this.resourceProperties.getStaticLocations();
 *           int var2 = var1.length;
 *
 *        for(int var3 = 0; var3 < var2; ++var3) {
 *             String location = var1[var3];
 *             Resource indexHtml = this.getIndexHtml(location);
 *             if (indexHtml != null) {
 *                 return indexHtml;
 *             }
 *         }
 *  例如：在public/文件夹下，书写一个主页面文件index1.html
 *  1.项目运行起来
 *  2.网址 localhost:8080/index1.html 就可以访问index1.html页面
 *
 * 四、为页面选项卡设置一个喜欢的图标，favicon.ico
 * 4.1 在E:/java-pro/文档路径下，找到图标favicon.ico,这次放到类路径下的resources文件夹里
 * 4.2 重启项目
 * 4.3 ctrl+F5刷新浏览器缓存（必须刷新浏览器缓存，否则看不到效果）
 * 发现favicon.ico图标已经应用到了static/index.html和public/index1.html这两个页面上了
 *
 * 注：也可以在配置文件application.properties中使用语句，自定义静态资源的路径：
 * spring.web.resources.static-locations=classpath:/hello/, classpath:/antiguigu/
 *
 * 指定自己的静态资源路径后，再使用上面的路径，访问静态资源，就报错了
 * There was an unexpected error (type=Not Found, status=404).
 *
 * 五、模板引擎
 * 通常前端拿过来的html文件，之前的方式，先转成jsp文件
 * 但是，spring boot使用的是嵌入式的Tomcat，不支持jsp了，我们使用模板引擎的方式来解决这个问题
 * 模板引擎常用的有JSP、Velocity、Freemarker、Thymeleaf【音，taimlif】等等
 * 5.1 spring boot推荐thymeleaf
 * spring boot推荐我们使用Thymeleaf这种模板引擎
 * 原因：thymeleaf语法更简单，功能更强大
 * thymeleaf[音，泰姆立夫] 是一个用于web和独立环境的现代服务器端java模板引擎
 *
 * 使用thymeleaf的步骤：
 * 1.在pom文件中，导入依赖，spring-boot-starter-thymeleaf
 * 2.
 *
 *
 *
 *
 *
 *
 *
 */