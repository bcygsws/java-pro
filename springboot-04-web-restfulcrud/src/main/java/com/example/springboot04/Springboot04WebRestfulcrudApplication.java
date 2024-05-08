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
 * 2.把.html文件路径，放在类路径下的templates文件夹下，就可以使用thymeleaf模板引擎来解析了
 *
 * 原理：ThymeleafProperties.class中代码片段
 *
 * public class ThymeleafProperties {
 *  private static final Charset DEFAULT_ENCODING;
 *  public static final String DEFAULT_PREFIX = "classpath:/templates/";
 *  public static final String DEFAULT_SUFFIX = ".html";
 *  private boolean checkTemplate = true;
 *  private boolean checkTemplateLocation = true;
 *  private String prefix = "classpath:/templates/";
 *  private String suffix = ".html";
 *
 * 3.在BasicController类中，写一个hello请求，返回的是经过thymeleaf引擎渲染的success.html页面
 * 3.1 为了获得thymeleaf的语法提示功能
 * 在html标签中添加xmlns(xm文件的命名空间xmlns)命名空间以后，就可以使用thymeleaf的提示功能了，不导入这个属性，项目正常运行，但是没有thymeleaf的语法提示
 * 3.2 前、后端开发人员分工更明确
 * 直接在本地打开success.html显示前端书写的原始页面，运行后端项目后，hello值就覆盖掉了【这是要显示的值：】，如此，前后端的分工就更加明确了
 * 前端给出的页面，本地打开 <h2 th:text="${hello}">这是要显示的值：</h2>
 * 运行spring boot项目后，经过模板引擎thymeleaf渲染后，hello值就覆盖掉【这是要显示的值：】 <h2 th:text="${hello}">这是要显示的值：</h2>
 * 4.thymeleaf语法规则
 * 4.1 th:text="${}" ,改变当前元素里的文本内容的
 * 4.2 可以使用html任意属性的值，来替换原生属性的值
 * 例如：如果前端给的页面还有id或者class属性，都可以更改
 * <h2  id="myId" class="myClass" th:text="${hello}">这是要显示的值：</h2>
 * 使用类似的th:id或者th:class进行更改
 * <h2  id="myId" class="myClass" th:id="" th:class="" th:text="${hello}">这是要显示的值：</h2>
 * 问题：th: 属性有多少以及 th: 标签的优先级（attribute precedence）？
 *
 * 表格，th:属性分成九个级别
 *
 * Order                Feature                                  Attributes
 * 1                    fragment inclusion(片段包含操作)          th:insert
 *                                                               th:replace
 * 2                    fragment iteration (遍历)                    th:each
 * 3                    conditional evaluation (条件判断)           th:if
 *                                                                  th:unless
 *                                                                  th:switch
 *                                                                  th:case
 * 4                    local variable definition(本地变量声明)      th:object
 *                                                                   th:with
 * 5                     general attribute modification(一般属性修改)         th:attr
 *                                                                           th:attrprepend
 *                                                                           th:attrappend
 * 6                   specific attribute modification(专用属性修改)          th:value
 *                                                                            th:href
 *                                                                            th:src
 * 7                   Text(tag body modification,标签包裹的文本内容修改)       th:text(转义，会将html代码片段以纯文本的形式显示，不会解析假期中的html或者script里的语句)
 *                                                                             th:utext(不转义，按照本来的内容输出，会将解析其中的html片段或者执行script标签里面的语句)
 * 8                   fragment  specification(声明片段)                           th:fragment
 * 9                   fragment removal（移除片段）                                 th:remove
 *
 * 具体参考截图：java-pro/attribute-precedence_thymeleaf.jpg
 *
 * 4.3 表达式
 * thymeleaf可以写哪些表达式？
 * 表达式名字	  语法	        用途
 * 变量取值	    ${...} 	 获取请求域、session域、对象等值
 * 选择变量	    *{...}	 获取上下文对象值
 * 消息	        #{...}	 获取国际化等值
 * 链接	        @{...}	 生成链接
 * 片段表达式	~{...}	 jsp:include 作用，引入公共页面片段
 *
 * a. 表达式 ：${变量}  用于获取变量值，
 * 高级功能：
 * 1.还可以调用属性和方法
 * 2.使用内置对象  例如：获取当前国家信息<div th:text="${#locale.country}"></div>
 *
 * b. 表达式 ：*{变量}  用于获取上下文对象值,通常用于简化上下文对象中拿到变量的值（其功能类似于this获取上下文对象，然后使用 ...toRefs()的功能）
 * ${session.user}拿到对象以后，下面直接使用*{firstName}（代替书写更加繁琐的${session.user.firstName}）
 * 举例：
 * Object user={firstName:"",lastName:"",nationality:""}
 * user是具有上面三个键的对象
 * <div th:object="${session.user}">
 *  <p>Name:<span th:text="*{firstName}"></span></p>
 *  <p>Surname:<span th:text="*{lastName}"></span></p>
 *  <p>country:<span th:text="*{nationality}"></span></p>
 * </div>
 *
 *
 *
 *
 * 链接：https://www.bilibili.com/video/BV1Et411Y7tQ/?p=30&spm_id_from=pageDriver&vd_source=2806005ba784a40cae4906d632a64bd6
 *
 *
 *
 *
 *
 *
 *
 */