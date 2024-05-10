package com.example.springboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class Springboot04WebRestfulcrudApplication {

	public static void main(String[] args) {
		// SpringApplication.run(Springboot04WebRestfulcrudApplication.class, args);
		// 1.返回ioc容器
		ConfigurableApplicationContext ioc = SpringApplication.run(Springboot04WebRestfulcrudApplication.class, args);
		// 2.获取ioc容器中组件的名称，并打印出来
		String[] comNames = ioc.getBeanNamesForType(MyViewResolver.class);
		for (String name : comNames) {
			System.out.println(name);// myViewResolver
		}
		// 3.判断id为myViewResolver的组件是否在ioc容器中
		boolean bool = ioc.containsBean("myViewResolver");
		System.out.println(bool);// true
		// 4.直接从容器中获取MyViewResolver对象
		MyViewResolver myViewResolverObj = ioc.getBean(MyViewResolver.class);
		System.out.println(myViewResolverObj);

	}


	@Bean
	public ViewResolver myViewResolver() {
		return new MyViewResolver();
	}

	// 静态内部类
	private static class MyViewResolver implements ViewResolver {

		@Override
		public View resolveViewName(String viewName, Locale locale) throws Exception {
			return null;
		}
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
 * 六、Spring MVC自动配置原理
 * 搜索类 SpringMvcAutoConfiguration
 * 一句话：spring boot默认配置好了Spring MVC
 * 以下是spring boot对spring mvc的默认处理：
 * 6.1. inclusion of ContentNegotiationViewResolver and BeanNameViewResolver
 * 自动配置了ViewResolver(视图解析器：根据方法的返回值得到视图对象View,视图对象决定如何渲染？是转发、还是重定向等等)
 * ContentNegotiationViewResolver：作用是组合视图的解析器
 * 自己如何定制呢？我们向容器中添加一个视图解析器，自动的将其组合起来
 * 6.1.1 在当前主配置类中书写以下代码：
 * @Bean
 * public ViewResolver myViewResolver(){
 *   return new MyViewResolver();
 * }
 * // 静态内部类
 * private static class MyViewResolver implements ViewResolver{
 *
 * @Override
 * public View resolveViewName(String viewName, Locale locale) throws Exception {
 *    return null;
 * }
 *
 * 6.1.2 shift点击两下，搜索DispatcherServlet类
 * 在该类的doDispatch()方法上，打上断点，F5调试运行
 * 6.1.3 发送一个请求，例如：localhost:8080/success
 * 在调试器中，依次展开
 * this--->viewResolvers(size=6)--->索引为0，也即第一个元素就是ContentNegotiationViewResolvers,
 * 继续展开ContentNegotiationViewResolvers，就看到了自定义的MyViewResolver
 *
 * SpringBoot04WebRestfulcrudApplication$MyViewResolver@6958
 *
 * 6.2 支持服务器端静态资源访问
 * support for static resources,including support for webjars(see below)
 * static index.html 静态首页访问
 * custom favicon support (see below) 网页图标的支持
 * 6.3 自动注册了转换器- Converter,GenericConverter,Formatter beans
 * Converter(转换器) 例如：public String hello(){}，类型转换使用Converter
 * Formatter(格式化器) 2017-12-17 --->Date类型的数据
 *
 * 6.4 支持HttpMessageConverters (see below)
 * HttpMessageConverters是spring mvc用来转换http请求和响应的,比如user---要一json的形式写出去
 * HttpMessageConverters是从容器中确定的，获取所有的HttpMessageConverters
 *
 * 6.5 自动注册消息解析器-MessageCodesResolver
 * 用来定义错误代码生成规则的
 *
 * 6.6 自动使用一个ConfigurableWebBindingInitializer bean(see below)
 * 6.6.1 初始化 数据绑定器 WebDateBinder
 * 6.6.2 请求数据===JavaBean
 * C:\Users\Administrator\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\2.6.13\spring-boot-autoconfigure-2.6.13.jar!\org\springframework\boot\autoconfigure\web
 *
 * 七、扩展spring MVC
 * springmvc.xml
 *    <mvc:view-controller path="/hello" view-name="success"></mvc:view-controller>
 *    <mvc:interceptors>
 *        <mvc:interceptor>
 *           <mvc:mapping path="/hello"></mvc:mapping>
 *           <bean></bean>
 *        </mvc:interceptor>
 *    </mvc:interceptors>
 *
 * 为实现上面的功能，在spring boot使用配置类实现
 * 定义一个配置类（@Configuration）,它必须是WebMvcConfigurerAdapter类型（高版本后，由抽象类变成了接口WebMvcConfigurer），而且
 * 不能添加@EnableWebMvc注解
 *
 * 好处：
 * 既保持了spring boot的自动配置，也能使用用户扩展的配置
 *
 * 原理是什么？
 * 1.关注WebMvcAutoConfiguration类上有一个注解
 * 伪代码
 * @Import({EnableWebMvcConfiguration.class})
 * @EnableConfigurationProperties({WebMvcProperties.class, WebProperties.class})
 * @Order(0)
 *
 * 2.导入了一个类EnableWebMvcConfiguration
 * 3.进入该类（仍然定义在WebMvcAutoConfiguration类中），423行@Import({EnableWebMvcConfiguration.class})
 * 4.EnableWebMvcConfiguration，继承自DelegatingWebMvcConfiguration，我们发现这个DelegatingWebMvcConfiguration类中，
 * 声明了WebMvcConfigurerAdapter（高版本后，由抽象类变成了接口WebMvcConfigurer）中所有的功能方法，比如：addViewController(添加视图映射方法)等等
 * 5.List<WebMvcConfigurer> 表示容器中所有的WebMvcConfigurer都会被放到一个List,一起起作用。用户自己配置类也会被调用
 * 效果：spring mvc的自动配置和自己的配置都会起作用
 *
 *
 * 参考文档：https://juejin.cn/s/%40autowired(required%20%3D%20false)%E4%BB%80%E4%B9%88%E6%84%8F%E6%80%9D
 * 伪代码：
 * @Autowired(
 *        required = false
 *  )
 * public void setConfigurers(List<WebMvcConfigurer> configurers) {
 *     if (!CollectionUtils.isEmpty(configurers)) {
 *        this.configurers.addWebMvcConfigurers(configurers);
 *       }
 *  }
 *
 * @Autowired(required = false)表示:其下方的字段是可选的，如果找不到匹配的bean（用户自定义的bean），也不会报错
 *
 *
 * 6.全面接管spring mvc（添加@EnableWebMvc注解）
 * spring boot对spring mvc的自动配置全部失效了，所有的东西都需要我们自己配置
 * 如果我们在MyMvcConfig类中添加@EnalbeWebMvc注解，其结果之一就是那些静态资源的访问全部失效
 * localhost:8080/css/based.css，就会成为404页面了
 *
 * 分析：为什么添加注解@EnableWebMvc,spring boot对spring mvc自动配置就失效了呢？（a、b、c、d）
 * a.查找EnableWebMvc，伪代码：
 * @Import({DelegatingWebMvcConfiguration.class})
 * public @interface EnableWebMvc {
 * }
 *
 * b.导入了DelegatingWebMvcConfiguration，分析这个类；
 * 伪代码
 * public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {}
 *
 * c.再回来看WebMvcAutoConfiguration类的注解
 * @Configuration(
 * proxyBeanMethods = false
 * )
 * @ConditionalOnWebApplication(
 *  type = Type.SERVLET
 * )
 * @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
 * @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})  // ConditionalOnMissingBean含义是：容器中没有这个bean时，自动配置类才生效
 * @AutoConfigureOrder(-2147483638)
 * @AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
 * public class WebMvcAutoConfiguration {
 *
 * d.没有WebMvcConfigurationSupport自动配置才生效，@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
 * 然而，添加@EnableWebMvc注解后--->自动导入DelegatingWebMvcConfiguration--->DelegatingWebMvcConfiguration这个类继
 * 承自WebMvcConfigurationSupport--->也就是容器中有WebMvcConfigurationSupport这个类---> @ConditionalOnMissingBean拿到了相反的条件
 * --->spring boot对spring mvc的自动配置功能就失效了
 *
 *
 *
 *
 *
 * 附：如何修改spring boot默认配置？
 * 模式一：
 * spring boot在自动配置很多组件时，先看用户有没有自己配置的(例如使用@Bean或者@Component添加了组件)
 * 如果有，一般就优先使用用户配置的；如果没有，才使用自动配置的；有些组件有多个ViewResolver可以将用户配置的和spring boot默认的组合起来
 *
 * 模式二：
 * 在spring boot中有非常多的xxxConfigurer,帮助我们进行扩展配置
 *
 * 项目开发开始
 * 一、导入dao和entities文件夹，以及静态资源
 * 包含asserts的整个文件夹放入默认static目录下，spring boot能够映射到这些静态资源
 * 至于html页面，将其放在/templates文件夹下，我们想使用thymeleaf模板引擎语法
 *
 * 在application.properties文件中配置
 * server.servlet.context-path=/crud
 * 项目访问时，都需要在@RequstMapping中的原有路径前叠加 /crud了；但注意：其他静态资源的路径不需要更改，spring boot配置生效后，会主动叠加上/crud
 * context-path配置设置前，访问登录页
 * localhost:8080/
 * localhost:8080/login.html
 *
 * 配置后
 * localhost:8080/crud
 * localhost:8080/crud/login.html
 *
 * 可以使用webjars导入bootstrap的公共样式
 * th:href="@{/webjars/}"
 *
 * 二、登录窗口的国际化问题
 * spring mvc中国际化的步骤，spring boot中简化，只需要编写国际化配置文件即可
 * 2.1 编写国际化配置文件
 * 2.2 使用ResourceBundleMessageSource管理国际化资源
 * 2.3 在页面使用fmt:message取出国际化内容
 *
 * 步骤
 * 1.在resources下新建文件夹i18n
 * 依次创建配置文件
 * login.properties  不指定语言，默认的
 * login_en_US.properties 英文的
 * login_zh_CN.properties 中文的
 *
 * 2.检查MessageSourceAutoConfiguration自动配置类
 * 里面确实使用了ResourceBundleMessageSource类管理国际化资源，spring boot已经帮我们处理好了
 *  ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 *
 * @Bean
 * @ConfigurationProperties(
 *     prefix = "spring.messages"
 *  )
 * 里面有个注解@ConfigurationProperties
 * 配置文件的前缀名为messages
 * 也就只要配置文件写成messages.properties,那么不需要任何额外配置，就可以使用国家化功能了
 * 但是，我们将国际化配置文件，统一放在i18n文件夹下了，需要在主配置文件中配置以下代码：
 * spring.messages.basename=i18n.login
 * i18n是类路径起的文件夹名，login是basename
 *
 * @Beanpublic MessageSource messageSource(MessageSourceProperties properties) {
 *
 * ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 * if (StringUtils.hasText(properties.getBasename())) {
 *         messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
 *       }
 *    if (properties.getEncoding() != null) {
 *
 *           messageSource.setDefaultEncoding(properties.getEncoding().name());
 *      }
 *
 *      messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
 *       Duration cacheDuration = properties.getCacheDuration();
 *       if (cacheDuration != null) {
 *           messageSource.setCacheMillis(cacheDuration.toMillis());
 *       }
 *
 *      messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
 *      messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
 *      return messageSource;
 *  }
 *
 * 3.去页面获取国家化值
 * 参考file:///E:/java-pro/%E6%96%87%E6%A1%A3/usingthymeleaf.pdf
 * 4.1小节的内容
 * #{}获取国际化值的
 *
 *
 *
 *
 */