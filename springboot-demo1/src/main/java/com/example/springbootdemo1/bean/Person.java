package com.example.springbootdemo1.bean;

import com.example.springbootdemo1.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 *
 * @那么如何将resources下的yaml配置文件中定义的变量person映射到当前文件中？
 * 解决：
 * 使用一个注解，@ConfigurationProperties
 * @ConfigurationProperties 会告诉Spring Boot将本类中的属性和配置文件中的相关进行绑定
 * 其中第一个参数prefix表示，只获取yaml配置文件中定义的person对象的属性进行一一映射
 * prefix="person"
 *
 * 注意：读取yaml文件时，@ConfigurationProperties注解的类中必须有getter/setter方法，否则在test中测试
 * 也是拿不到数据的，所有变量拿到的值都是null
 * 问题解决，参考文档：https://blog.csdn.net/eagle_321/article/details/119039386
 *
 * @Component注解： 将Person类加入到容器中
 * 关于@Component注解的作用
 * 参考：https://blog.csdn.net/wenhuakulv2008/article/details/132577138
 *
 *
 */

/*
 * @一、读取配置文件的变量两种方法
 * 方式一：@ConfigurationProperties注解
 *
 * 方式二：@Value注解
 * <bean class="Person">
 *    <property name="lastName" value="值可以是 字面量/${key}从环境变量、或者配置文件中获取值的/#{SpEL}，这是spring的表达式语言"></property>
 * </bean>
 *
 * @Value注解中也就支持这几种格式的值
 * @Value(${person})
 *
 *                                                 @ConfigurationProperties                                                     @Value
 * 功能                                                 批量注入数据属性                                                 只能一个一个的指定
 * 松散语法绑定（relaxed Binding）                 【支持】(配置文件中的last-name会解析成驼峰命名的方式，lastName)          【不支持】（必须和配置文件中的名称相同，否则代码报错）
 * SpEL,spring表达式语言方面                              【不支持】                                                           【支持】(例如：application.properties文件中age值，修改为#{11*2)
 * JSR303数据校验                                         【支持】                                                             【不支持】
 * 复杂类型封装（比如对象、Map、List等，复杂类型对应基本类型） 【支持】                                                             【不支持】
 *
 * @二、ConfigurationProperties注解和@Value注解的使用场景
 *
 * 1.配置文件是properties还是yml文件，都能获取到值
 * 2.如果我们只是要获取配置文件中的某项值(值要求时是简单类型，复杂类型@Value注解也不能获取)，我们就使用@Value
 * 3.如果我们编写了一个javabean来和配置文件中的值进行映射，就直接使用@ConfigurationProperties就好了
 * 4.@ConfigurationProperties默认是 从【全局配置文件】中获取值
 *
 *
 * 三、@SourceProperty注解，用于从指定路径的文件中，获取值
 * 1.注意：单独使用@PropertySource()注解，拿到的person.properties中的person所有对象值为null
 * 此时，仍然需要@ConfigurationProperties这个注解
 * 2.可以理解为，@PropertySource()指定的文件路径，仅仅是为了将@ConfigurationProperties这个注解中的搜索范围 从【默认配置文件】变成了【默认配置文件+指定路径文件】
 *
 * 四、注解@ImportResource
 * 作用：导入Spring的配置文件，让文件里的内容生效
 * 实现功能：给容器里加组件
 *
 * 之前的写法：
 * 4.1.在/resources下创建bean.xml文件
 * 4.2.创建一个类 service/HelloService.java,不加任何的注解
 * 4.3.实例化ioc容器对象ioc，并另外写一个测试方法，testHelloService()
 *  4.3.1 没有添加@ImportResource注解，使得Spring Boot的配置文件beans.xml生效，b的值打印false;表示Spring Boot里还没有这个配置文件
 *  4.3.2 需要将这个注解@ImportResource，加在一个配置类上；我们就将它加在主配置类上（com.example.springbootdemo1.SpringbootDemo1Application）
 *  4.3.3 在主配置类上，添加@ImportResources注解以后，重新运行观察该方法的返回值变成了true
 *
 * spring boot推荐写法：（不写xml文件，使用配置类来实现）
 * 4.1 创建一个配置类 config.MyAppConfig，加上注解@Configuration，指明当前类是一个配置类（替代之前的Spring配置文件的）
 * 4.1.1 配置类=======beans.xml(测试这种方式时，注释掉主配置中的@ImportResources注解，此时beans.xml失效)
 * 4.1.2 定义个方法helloService(),并添加注解@Bean,方法的返回值是HelloService()
 * 伪代码：
 *
 *
 * @Configuration
 * public class MyAppConfig {
 * @Bean
 * public HelloService helloService(){
 *   return new HelloService();
 * }
 *
 * 五、配置文件占位符
 * 5.1 在application.properties和yml文件中都可以使用
 * 5.2 占位符语法
 * 配置文件中的值：
 * a.可以写随即数${random.uuid}  ${random.int}
 * b.使用其他变量的占位符 ${person.name};例如：小狗名称使用了dog${person.name}
 * c.承接b,如果配置文件中，person.lastName被注释掉，但是Person类仍然有lastName，这时候person对象，lastName的值为null;${person.lastName}会作为字符原样输出
 * d.如果占位符中使用，没有在配置文件中和当前Person类中定义的属性，例如：person.hello属性，则代码不会报错，将${person.hello}原样输出；
 * 当然，也可以为不存在的属性，设置一个默认值，指示变量不存在就使用默认值
 * application.properties
 *
 * person.dog.name=${person.hello: hello}dog
 * 分析：hello属性在配置文件和当前类中都没有定义，而且指定了默认值，那就使用默认值
 * 在测试类中方法输出，person.dog.name=hellodog
 *
 *
 * 六、配置Profile多环境支持
 * 参考文档；https://blog.csdn.net/CCChloe/article/details/130701715
 *
 *
 * 多Profile文件
 * 配置文件带上profile标识，就可以自动切换
 * application-{profile}.properties 或者 application-{profile}.yml
 *
 * 6.1 properties类型配置文件
 * 6.1.1 例如：除了application.properties外
 * 分别创建了另外两个在开发和生产环境中，使用的配置文件
 * application-dev.properties 开发环境下的配置文件
 * 在文件中配置内容：（生产环境）
 * server.port=8081
 *
 * application-prod.properties 生产环境下的配置文件
 * 在文件名中配置内容:(生产环境)
 * server.port=8082
 *
 * 6.1.2 仅执行上面的创建和配置，自动切换不同环境下的配置文件，还不会生效
 * 需要在默认配置文件，application.properties中配置以下内容
 * 两个环境下的配置文件命名规范
 * application-{profile}.properties
 * 激活该文件就使用
 * spring.profiles.active={profile}
 *
 * application.profiles.active=dev
 * 或者application.profiles.active=prod
 *
 *
 * 6.2 yml类型配置文件
 * yml有一种文档块语法
 * 测试这种文档块语法时，先注视到properties类型配置文件中的所有内容
 *
 * 6.2.1 三个短横线，分割出一个文档块
 *
 * application.yml
 * server:
 *   port: 8080
 * spring:
 *   profiles:
 *     active: dev
 *
 * ---
 * server:
 *   port: 8081
 * spring:
 *   profiles: dev
 * ---
 * server:
 *   prot: 8082
 * spring:
 *   profiles: prod
 *
 *
 * 6.3 profiles的激活方式（三种方式）
 *
 * 方式一：在主配置文件包中配置激活语句（yml中类似）
 * .properties文件中的 spring.profiles.active=dev
 * 或者yml文件中的yml语法
 *
 * 方式二：命令行的方式
 * --spring.profiles.active=dev
 * a.点击【当前运行类】左侧有一个图标（开口向右的铃铛+开关），点击一下，弹出下拉列表：
 * b.在IDEA中，点击 编辑配置，切换到Spring Boot下主配置类所在的界面
 * c.在有效配置文件右上方的【修改选项】中，在下拉列表中，必须选中【程序实参】、【将带有provided作用于的依赖项添加到类路径】这两项
 * d.在应用程序实参文本框内，输入命令行：
 * --spring.profiles.active=dev  (注意：即使在properties或yml文件中，已经指明了要激活的配置文件，当前命令行也有更高的优先级，即：生效的是命令行的方式)
 *
 * 附：命令行也可以写在打包好的jar包，运行jar包命令时指定
 *
 * 回顾打成jar包的步骤：
 * 1.1.在pom文件中，配置<build></build>标签
 * 1.2.展开idea工具右侧折叠的Maven构建工具，依次选择，生命周期-选中package-右键【运行Maven构建】，就开始打包了
 *
 * 在打好的jar包在target目录下运行时，添加激活配置文件的命令
 * 2.1.在项目的target路径下，在本地路径栏中，输入cmd,即打开cmd黑窗口
 * 2.2.运行打好的jar包命令中，添加上面激活使用哪个配置文件的命令
 * java -jar springboot-demo1-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
 *
 * 方式三：指定虚拟机VM参数;虚拟机参数指定过程类似，方式二a,b,c,d;不同点在于，这次选中的是【添加虚拟机选项】
 * 虚拟机参数的格式：-D打头
 * -Dspring.profiles.active=dev
 *
 * 七、Spring Boot配置文件的优先级问题
 * 7.1 Spring Boot默认会扫描以下路径的application.properties或者application.yml作为配置文件
 * 7.2 配置文件有优先级，高优先级配置文件会覆盖低级别配置文件
 *
 * 优先级从高到低依次是：
 * 项目根路径/config/
 * 项目根路径./
 *
 * 类路径./resource/config/
 * 类路径./resource/
 * 7.3 并不是说低级别的配置文件就不访问了，仍然会访问（仍然有效果）；只是同名的设置，高优先级覆盖低优先级
 * 比如：在优先级最低的类路径下（/resource）为请求增加一个路径
 *
 * ./resource下 application.properties或者yml
 * server.servlet.content-path=/boot02
 *
 * 7.4 指定配置文件的默认路径(spring.config.location,上面用到过spring.profiles.active)
 * 情景：我们在打包后，需要更改配置文件里的配置；不需要修改配置后，重新打包；而只需要几行简单的代码，写成本地的配置文件application.properties或者application.yml
 * 例如：这些配置文件的路径为D:/application。properties
 * 在运行打包文件时，使用命令参数 --spring.config.location
 * java -jar springboot-demo1-0.0.1-SNAPSHOT.jar --spring.config.location=D:/application.properties
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
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
@Component
// @Validated
@ConfigurationProperties(prefix = "person")
// @PropertySource(value = "classpath:person.properties")
// 注解@PropertySource
// 一、获取默认配置文件（默认文件是，名称为application.properties或者application.yml）中的变量值
// 1.1 使用注解@ConfigurationProperties
// 1.2 使用@Value()注解
// 二、获取指定路径的文件中的值,使用注解@PropertySource()

public class Person {
	// ${key}方式,读取配置文件中person.lastName的值
	// @Email
	// @Value("${person.lastName}")

	private String lastName;
	// SpEL，spring表达式方式，直接为person.age赋值
	// @Value("#{11*2}")
	private Integer age;
	// 字面量方式，直接赋值false，直接为person.boss赋值
	// @Value("false")
	private Boolean boss;
	private Date birth;
	// 使用@Value注解，来拿Map对象的值，报错：Could not resolve placeholder 'person.maps' in value "${person.maps}"
	/**
	 * 这涉及到两种方式的使用场景
	 * <p>
	 * 注意：@Value注解的方式，来拿配置文件中的值，是不支持复杂封装的
	 */
	// @Value("${person.maps}")
	private Map<Object, String> maps;
	// 报错：Could not resolve placeholder 'person.list' in value "${person.list}"
	// @Value("${list}")
	private List<Object> list;
	// 报错：Could not resolve placeholder 'person.dog' in value "${person.dog}"
	// @Value("${person.dog}")
	private Dog dog;

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setMaps(Map<Object, String> maps) {
		this.maps = maps;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public Boolean getBoss() {
		return boss;
	}

	public Date getBirth() {
		return birth;
	}

	public Map<Object, String> getMaps() {
		return maps;
	}

	public List<Object> getList() {
		return list;
	}

	public Dog getDog() {
		return dog;
	}

	@Override
	public String toString() {
		return "Person{" +
				"lastName='" + lastName + '\'' +
				", age=" + age +
				", boss=" + boss +
				", birth=" + birth +
				", maps=" + maps +
				", list=" + list +
				", dog=" + dog +
				'}';
	}
}
