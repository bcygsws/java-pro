package com.example.springboot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

// 为了使得使用@WebServlet注解 注册servlet生效，当前自动启动类添加@ServletComponentScan注解
@ServletComponentScan
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
 * @Bean
 * public MessageSource messageSource(MessageSourceProperties properties) {
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
 * LocaleResolver接口是用来做 当前语言-国家的解析器，依然位于WebMvcAutoConfiguration中
 * 伪代码；
 *
 * @Bean
 *    @ConditionalOnMissingBean(
 *        name = {"localeResolver"}
 *    )
 *    public LocaleResolver localeResolver() {
 *        if (this.webProperties.getLocaleResolver() == org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver.FIXED) {
 *            return new FixedLocaleResolver(this.webProperties.getLocale());
 *        } else {
 *            AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
 *           localeResolver.setDefaultLocale(this.webProperties.getLocale());
 *           return localeResolver;
 *       }
 *   }
 *
 * 以上是spring boot为我们自动配置的localeResolver组件
 * 我们自己写一个同名的Bean,覆盖掉它（MyMvcConfig配置类中的方法必须是localeResolver,才能覆盖掉spring boot自动的）
 * 参考文档：
 * https://www.jianshu.com/p/1bfcafb3dd12
 *
 * 4.点击 中文 或者English 切换中文/英文登录页面
 *
 * 三、登录界面和拦截器
 * 3.1 在开发期间先禁止thymeleaf模板引擎的缓存，以使得对前端页面的更改立即生效，更便于调试
 * 3.2  登录失败和成功的处理
 * 3.2.1 登录失败，逻辑上要重新返回到登录页面login,这在LoginController类的登录失败中处理
 * 同时，为了更直观的看到登录失败提示，在LoginController类的home方法，设置了第三个参数map,在thymeleaf模板中
 * 使用${msg}拿到值，显示在p标签上
 * 这个p标签一开始没有，登录失败，才会在dom中添加p标签，使用th:if(优先级3) th:text（优先级7），th:if满足条件了p才显示【登录失败文本】
 *
 * 在thymeleaf文档的 （4.standard Expressions syntax）
 * Expression utility object，其中有#strings工具对象
 *
 * 在thymeleaf文档的（ 20.Appendix C: Markup Selector Syntax）
 * 有#strings的用法
 * <p th:text="${msg}" th:if="not #strings.isEmpty(msg)"></p>
 *
 * 3.2.2 登录成功
 * 为防止表单重复提交，最好使用重定向的方式(使用重定向后，地址栏地址改变了，这是我们期望的，/user/login 变成了 /main.html)
 * return "redirect:/main.html"
 *
 * bug:但是我们在其他浏览器中，粘贴http://localhost:8080/crud/main.html，能看到登录后的页面，这是不符合程序安全原则的
 * 解决：使用拦截器来进行登录检查
 *
 * 在LoginController，登录成功逻辑中，使用session添加一个信息
 *
 * 四、crud员工列表
 * 增 查 该 删 员工信息
 * 4.1 restful风格的crud
 * restful风格：
 * URI /资源名称/资源标识， 再通过请求方式的不同区分对资源的crud的操作
 *
 *              普通crud和restful crud的区别
 * action          普通crud                restful crud
 * 增加       addEmp                   emp---post
 * 查询       getEmp                   emp---get
 * 更改       updateEmp                emp/{id}---put
 * 删除       deleteEmp                emp/{id}---delete
 *
 *
 * 4.2 CRUD的请求架构
 *                                        URI(统一资源识别符)            请求方式method
 * 查询所有员工信息                             emps                           get
 * 查询某个员工（到修改某个员工信心页面）         emp/{id}                      get
 * 来到添加页面                                  emp                           get
 * 添加员工                                      emp                           post
 * 来到修改页面（要修改信息回显）                 emp/{id}                       get
 * 修改某个员工信息                               emp                           put
 * 删除员工                                       emp/{id}                      delete
 *
 * 4.3 员工列表
 * thymeleaf公共代码片段抽取
 * 在dashboard.html页面中抽取
 *
 * 4.3.1 抽取公共代码片段 th:fragment="topbar"  级别8
 * 在其他页面引用，这个代码片段
 * 语法，注意选择器和片段名的用法区别：
 * th:insert="~{模板名:选择器名}"
 * th:insert="~{模板名::片段名}"
 *
 * 4.3.2 引用代码片段
 * th:fragment="topbar"
 * th:insert="~{dashboard :: topbar}" 或者 th:insert="dashboard :: topbar"
 * 或者使用选择器方式(侧边栏使用选择器方式抽取)
 * id="sidebar"
 * th:replace="~{dashboard::#sidebar}"
 *
 * bug:使用th:insert无端为list中公共部分额外引入了一对div标签，有可能对这段公共片段的样式有影响，如何解决？
 * 引入代码片段的三种th:含义
 * th:insert插入，直接将公共片段插入div中了，<div th:insert="~{dashboard :: topbar}"></div>
 * th:replace替换，不会生成额外的div标签 <div th:replace="~{dashboard :: topbar}"></div>
 * th:include 包含，相当于抽取时，把nav这段根标签去掉，把里面的内容包含进来；<div th:insert="~{dashboard :: topbar}"></div>
 * 显然，使用th:replace更符合要求
 *
 * 4.4 链接高亮
 * P38,5:07  https://www.bilibili.com/video/BV1Et411Y7tQ/?p=38&spm_id_from=pageDriver&vd_source=2806005ba784a40cae4906d632a64bd6
 *
 * DAO三层
 * entity/pojo 实体层，POJO(plain ordinary java object简单java对象)，简单的实体类（getter/setter便于读写）
 * utils工具
 * dao层，也叫映射（mapper）层,也叫数据访问层，持久层，是和数据打交道的（DAO,是data access objects）
 * biz/service层，业务逻辑层（biz,美国俚语等于business，业务的意思）
 * MVC三层
 * MVC是一种架构
 * M 模型层，model(java bean)
 * V 视图层，view
 * C 控制器，controller
 *
 * mvc+dao开发，纵向开发
 * 数据库
 * DAO
 * biz
 * controller
 * UI
 * spring要管理控制器、业务逻辑和数据访问，对应三个注解、
 *
 * 控制器，@Controller，web层
 * 业务逻辑biz，@Service，service层，负责整合映射层mapper,供外界调用
 * 管理数据访问， @Repository,DAO层
 * java bean @Component
 * 关于Controller，Service，Dao三层架构的理解以及Mybatis的关系
 * 参考文档：https://blog.csdn.net/weixin_48312926/article/details/125022959
 *
 * 五、错误处理的原理&定制错误页面
 * 5.1 spring boot错误异常
 * 在浏览器中，弹出一个页面，里面包含错误原因和状态码
 * 在客户端，比如postman，会返回一个json对象，展示错误信息
 *
 * 测试：异常处理时，注释掉config/MyMvcConfig类中的addInterceptors()方法中的代码
 * 原因：使用postman 和 ApiFox等第三方客户端测试时，脱离了浏览器，这些异常将会被拦截
 *
 * 5.2 spring boot的错误处理自动配置类,ErrorMvcAutoConfiguration
 *
 * 原理
 * ErrorPageCustomizer：一旦报4xx或者5xx错误，ErrorPageCustomizer就会生效，定制错误的响应规则；（customizer含义：定制应用程序）
 * BasicErrorController：然后/error错误请求，就会交由BasicErrorController控制器处理(包含errorHtml()和error()两个方法)
 * DefaultErrorViewResolver：响应页面，去哪个页面，有DefaultErrorViewResolver得到
 * DefaultErrorAttributes  帮我们共享信息;timestamp时间戳、status状态码、error错误提示、exception异常、message异常消息、errors(JSR303数据校验的错误)
 * 步骤：
 * 一旦系统报4xx,5xx的错误，ErrorPageCustomizer就会生效（定制错误的响应规则）；然后/error错误请求，就会交给控制器
 * BasicErrorController处理;
 * 1）响应页面,去哪个页面，有DefaultErrorViewResolver得到
 * DefaultErrorViewResolver类中resolve方法负责处理（如果模板引擎可用，用模板引擎；如果不可用this.resolveResource(errorViewName, model)处理，
 * 会去static下寻找）
 *
 * a.有模板引擎的情况：
 * DefaultErrorAttributes  帮我们共享信息
 * timestamp时间戳
 * status状态码
 * error错误提示
 * exception异常，  4xx.html或者5xx.html中不显示exception或者message,需要在配置文件中配置server.error.include-exception=true 和 server.error.include-message=always
 * message异常消息，4xx.html或者5xx.html中不显示exception或者message,需要在配置文件中配置server.error.include-exception=true 和 server.error.include-message=always
 * errors(JSR303数据校验的错误)
 *
 * b.没有模板引擎的情况（就是templates/下没有4xx.html页面），例如：将templates/模板引擎文件夹下的/error整个文件夹，移动到static/下，
 * 错误页面4xx.html/404.html也是可以用的，只不过放到静态资源文件夹下，一些动态数据就无法解析了（比如：${timestamp}、#{status}等等）
 *
 * c.以上都没有(templates/和static/下都没有)，就是我们在没有配置错误页面前的 默认空白页面
 *
 * 2）响应客户端请求，定制json数据
 * 视频链接：https://www.bilibili.com/video/BV1Et411Y7tQ?p=44&spm_id_from=pageDriver&vd_source=2806005ba784a40cae4906d632a64bd6
 *
 *
 *
 * 参考伪代码；
 * private ModelAndView resolve(String viewName, Map<String, Object> model) {
 *        String errorViewName = "error/" + viewName;
 *         TemplateAvailabilityProvider provider = this.templateAvailabilityProviders.getProvider(errorViewName, this.applicationContext);
 *          return provider != null ? new ModelAndView(errorViewName, model) : this.resolveResource(errorViewName, model);
 *   }
 *
 * 2）响应客户端，返回包含错误信息的json数据
 *
 *
 *
 * 5.2.1 一旦系统报4xx(客户端错误),5xx的错误（服务端错误），ErrorPageCustomizer就会生效（定制错误的响应规则）；
 * 伪代码
 * @Value("${error.path:/error}")
 * private String path = "/error";
 *
 * 5.2.2 BasicErrorController
 * BasicErrorController是一个controller,用于处理/error请求的;
 * 有两种方式：
 * 一种响应页面，text/html
 * 一种是客户端响应json数据
 *
 *  @Bean
 *  @ConditionalOnMissingBean(
 *      value = {ErrorController.class},
 *      search = SearchStrategy.CURRENT
 *    )
 *  public BasicErrorController basicErrorController(ErrorAttributes errorAttributes, ObjectProvider<ErrorViewResolver> errorViewResolvers) {
 *           return new BasicErrorController(errorAttributes, this.serverProperties.getError(), (List)errorViewResolvers.orderedStream().collect(Collectors.toList()));
 *    }
 *
 *
 *  @RequestMapping(
 *       produces = {"text/html"}    // 处理浏览器text/html，浏览器请求头中Request Headers，Accept 中text/html
 * )
 * public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
 *     HttpStatus status = this.getStatus(request);
 *     Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
 *     response.setStatus(status.value());
 *     ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
 *     return modelAndView != null ? modelAndView : new ModelAndView("error", model);
 * }
 *
 *  @RequestMapping                      // 处理客户端，显示json数据;客户端的请求头中Accept中，是(星斜杠星)
 *  public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
 *    HttpStatus status = this.getStatus(request);
 *       if (status == HttpStatus.NO_CONTENT) {
 *       return new ResponseEntity(status);
 *   } else {
 *           Map<String, Object> body = this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.ALL));  这里的ALL有x/x
 *       return new ResponseEntity(body, status);
 *   }
 * }
 *
 *
 * 5.2.3 DefaultErrorViewResolver负责处理用户要得到的错误页面
 * 1）响应页面,去哪个页面，有DefaultErrorViewResolver得到
 * DefaultErrorViewResolver类中resolve方法负责处理（如果模板引擎可用，用模板引擎；如果不可用this.resolveResource(errorViewName, model)处理，
 * 会去static下寻找）
 *
 * 5.2.4 DefaultErrorAttributes:帮我们共享信息
 * DefaultErrorAttributes类中的，有多个重载的getErrorAttributes方法
 *  timestamp时间戳
 *  status状态码
 *  error错误提示
 *  exception异常
 *  message异常消息
 *  errors(JSR303数据校验的错误)
 *
 *
 * 伪代码：
 * private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
 *         Map<String, Object> errorAttributes = new LinkedHashMap();
 *         errorAttributes.put("timestamp", new Date());
 *         this.addStatus(errorAttributes, webRequest);
 *          this.addErrorDetails(errorAttributes, webRequest, includeStackTrace);
 *          this.addPath(errorAttributes, webRequest);
 *           return errorAttributes;
 *   }
 *
 *
 *  如何定制错误响应？
 * a.如何定制错误页面
 * 有模板引擎的情况下，error/404.html  error下的状态码（实验：在templates下新建error文件夹，把404.html页面放进去，发送错误请求，
 * 看返回是不是这404.html?）
 * 答：项目重启后，发送错误请求时，可以使用这个404.html；
 * 总结错误页面的规则：以错误状态码为名字,.html为后缀的文件，放在templates/ 下error文件夹下
 *
 * 错误状态码4开头的很多，每一个都按照上面规则配置一个错误页面，不现实；我们可以为其配置一个4xx.html（或者5xx.html）
 * 如果发生404错误，error/下有404.html页面（原则：精确优先）；如果没有这个页面，就统一使用4xx.html(比如：添加员工信息，
 * 生日日期不按照配置文件中的格式2012-12-12，而输入字符串"afgqaghb"，就会报400错误，检查error/没有400.html页面，就统一使用4xx.html页面)
 *
 *
 *
 *
 * b.如何定制错误的json数据
 * 1.在controller/文件夹下，新建MyExceptionHandler类
 * 类注解：@ControllerAdvice
 * 方法注解：@ExceptionHandler
 *
 * 伪代码：
 * @ControllerAdvice
 * public class MyExceptionHandler {
 *	// 响应到页面或客户端中,添加@ResponseBody注解
 *	@ResponseBody
 *	@ExceptionHandler(UserNotExistException.class)
 *	public Map<String, Object> handleException(Exception e) {
 *		Map<String, Object> map = new HashMap<>();
 *		map.put("code", "user.notexist");
 *		map.put("message", e.getMessage());// 异常信息，从参数e中拿到，e.getMessage()方法
 *		return map;
 *		// 	有@ResponseBody注解，返回map;直接在页面上响应一个json对象了，再也不是错误页面了
 *	}
 * }
 *
 * 但是，上面的方式不是自适应的
 * 要实现：当异常出现时，在浏览器中返回异常页面（在配置好异常页面的前提下），而使用客户端时发送请求时，返回json数据
 * 具体见类MyExceptionHandler注释
 *
 * 六、配置嵌入式Servlet容器
 * 参考文档：https://www.bilibili.com/video/BV1Et411Y7tQ/?p=45&vd_source=2806005ba784a40cae4906d632a64bd6
 * 6.1 war包+外部配置的tomcat容器，可以通过修改tomcat中的server.xml和web.xml来修改配置
 * 6.2 spring boot默认使用tomcat作为嵌入式servlet容器
 *
 *----------------问题1 start----------------
 * 【问题1：那么如何修改和定制servlet容器呢？】
 * 方式1）修改和server（ServerProperties）有关的配置
 * 一般是设置server.xxx.……=
 *
 * a.设置端口号
 * server.port=8080
 * b.设置项目路径，比如本项目设置成了/crud
 * server.servlet.context-path=/crud
 * c.设置tomcat的编码字符集
 * server.servlet.encoding.charset=UTF-8
 *
 * 方式2)添加一个组件EmbeddedServletContainerCustomizer(已经被弃用)，spring boot2.x使用WebServerFactoryCustomizer接口取代它
 * 伪代码：
 * @Bean
 *	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> myWebServerFactoryCustomizer() {
 *		// 接口需要实现后，才能实例化
 *		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
 *			@Override
 *			public void customize(ConfigurableServletWebServerFactory factory) {
 *				factory.setPort(8083);
 *			}
 *		};
 *	}
 * -----------------------
 * 总结spring boot修改默认配置的方式？
 * 1.spring boot在启用自动配置组件时，会先检查用户是否自己配置了（@Bean @Component），如果用户配置了，就优先使用用户自己配置的；如果用户
 * 没有配置，才让自动配置生效；有些组件有多个viewResolver可以将用户和自动配置的连接起来
 * 2.在Spring Boot中有非常多的xxxConfigure帮助我们扩展；例如：当前项目的MyMvcConfig实现了WebMvcConfigurer接口
 * 视图映射addViewControllers
 * 注册拦截器addInterceptors
 * 另外为当前类注解了@Configuration，还可以方便的为spring boot容器添加组件
 * 3.在spring boot中有非常多的xxxCustomizer帮助我们扩展；例如：当前项目中WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>自己定制
 * servlet容器的配置
 * -------------------------
 *
 * 总结二
 * 2.1 spring boot帮我们自动处理spring mvc时，自动注册spring mvc的前端控制器DispatcherServlet
 * 查看DispatchServletAutoConfiguration自动配置类
 * 伪代码：
 *  @Bean(
 *          name = {"dispatcherServletRegistration"}
 *      )
 *      @ConditionalOnBean(
 *          value = {DispatcherServlet.class},
 *          name = {"dispatcherServlet"}
 *      )
 *       public DispatcherServletRegistrationBean dispatcherServletRegistration(DispatcherServlet dispatcherServlet, WebMvcProperties webMvcProperties, ObjectProvider<MultipartConfigElement> multipartConfig) {
 *           DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet, webMvcProperties.getServlet().getPath());
 *           registration.setName("dispatcherServlet");
 *           registration.setLoadOnStartup(webMvcProperties.getServlet().getLoadOnStartup());
 *           multipartConfig.ifAvailable(registration::setMultipartConfig);
 *           return registration;
 *       }
 *   }
 *
 * 伪代码：来自 .getServlet().getPatH()
 * 2.2 默认拦截/的所有请求，包括静态资源，但是不会拦截jsp请求；/星 会拦截jsp
 * 可以通过server.servletPath()来修改spring mvc前端控制器的请求路径
 *
 * public String getServletMapping() {
 *          if (!this.path.equals("") && !this.path.equals("/")) {
 *              return this.path.endsWith("/") ? this.path + "*" : this.path + "/*";
 *          } else {
 *              return "/";
 *          }
 *      }
 *
 *
 *
 * 七、注册Servlet三大组件,注册Servlet Filter和Listener三大组件
 * ServletRegistrationBean
 * FilterRegistrationBean
 * ServletListenerRegistrationBean
 *
 * 7.1 spring boot以jar包的形式，使用嵌入式tomcat
 * 而我们正常的web应用，目录结构会有src/main/会有webapp/WEB-INF/web.xml,我们把三大组件可以注册在web.xml中
 * 在类路径下，新建servlet.MyServlet
 *
 * ServletContextListener,定义Listener,时需要实现的接口
 *
 * servlet注册的四种方式：
 * 储备知识：
 * @ServletComponentScan和@ComponentScan 注解
 * 参考文档：https://blog.csdn.net/weixin_45433031/article/details/133863703
 * 下面我们就言简意赅的介绍一下这两个注解的作用
 * 一、 @ServletComponentScan
 * 在SpringBootApplication上使用@ServletComponentScan注解后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
 * 二、 @ComponentScan
 * Spring是一个依赖注入(dependency injection)框架。所有的内容都是关于bean的定义及其依赖关系。定义Spring Beans的第一步是使用正确的注解-@Component或@Service或@Repository.
 * 但是，Spring不知道你定义了某个bean除非它知道从哪里可以找到这个bean.
 * ComponentScan做的事情就是告诉Spring从哪里找到bean。
 *
 * 包扫描会扫描只要标注了@Controller,@Service,@Repository,@Component这四个注解都会被扫描到容器中。
 * 1、@Controller 控制器（注入服务）
 * 用于标注控制层，相当于struts中的action层
 * 2、@Service 服务（注入dao）
 * 用于标注服务层，主要用来进行业务的逻辑处理
 * 3、@Repository（实现dao访问）
 * 用于标注数据访问层，也可以说用于标注数据访问组件，即DAO组件.
 * 4、@Component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）泛指各种组件，就是说当我们的类不属于各种归类的时候（不属于@Controller、@Service等的时候），我们就可以使用@Component来标注这个类。
 *
 * servlet注册的四种方式，参考文档：https://www.cnblogs.com/yihuihui/p/11925749.html
 * 方式一：常用的注册方式，使用@WebServlet注解直接注册，在主配置类中加@ServletComponentScan;不需要@Component注解
 * @ServletComponentScan注解的作用：spring boot启动时，会扫描并注册所有注解@WebServlet、@WebFilter、@WebListener的类
 *
 * 方式二：常用的注册方式，ServletRegistrationBean的方式，不需要@Component注解
 *
 * 方式三：不常用的注册servlet的方式；ContextServlet extends HttpServlet + ServletContextInitializer，需要@Component注解
 *
 * 方式四：bean的方式，有坑；需要@Component注解
 *----------------问题1 end------------------
 *
 * ---------------问题2 start----------------
 * 【问题2：Spring Boot支持其他Servlet容器吗？】
 * 默认支持以下servlet容器
 *  Tomcat,spring boot默认使用的
 *  Jetty
 *  Undertow(不支持JSP,是一个高性能非阻塞的servlet容器，并发性很好;但如果使用了jsp，就不要使用undertow)
 *
 * 支持其他servlet容器，默认可以切换Jetty和Undertow
 * 切换步骤：
 * 还是在pom文件中操作
 * 1.先使用pom文件，生成可视化图，打开pom文件，右键，图表-可视化图
 * 2.在可视化图中，可以清晰得得出依赖之间的关系；看到spring-boot-starter-tomcat依赖pom中的spring-boot-starter-web
 * 3.需要在spring-boot-starter-web中排除掉spring-boot-starter-web
 * <exclusions>
 *    <exclusion></exclusion>
 * </exclusions>
 *
 * 八、嵌入式Servlet容器自动配置原理
 * 查看EmbeddedWebServerFactoryCustomizerAutoConfiguration类
 * 熟悉：WebServerFactoryCustomizer接口，我们用于配置Servlet容器（例子：参考config/MyServletConfig 类）
 *
 * UndertowWebServerFactoryCustomizerConfiguration
 * JettyWebServerFactoryCustomizerConfiguration
 * NettyWebServerFactoryCustomizerConfiguration
 * TomcatWebServerFactoryCustomizerConfiguration
 *
 *
 * 九、使用外部servlet
 * spring boot默认的嵌入式servlet的优点：
 * 9.1 优点：简单、便携
 * 缺点：默认不支持jsp、优化定制比较复杂（a.使用配置文件，它是基于ServerProperties
 * b.使用WebServerFactoryCustomizer,把这个类型的bean添加到容器中
 * c.更高层次，自己创建Servlet容器的工厂）
 *
 * 9.2 为了支持jsp，我们使用外置的servlet容器（本地安装一个Tomcat）
 * 创建一个springboot-94-web-jsp项目，将spring boot项目稍加改造，添加webapp文件下，和web.xml
 *
 *
 * ---------------问题2 end-------------------
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */