package com.example.springbootdemo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemo3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemo3Application.class, args);
	}

}
/*
 * @ 测试springboot日志统一管理
 * 一、步骤：
 * 1.在pom.xml文件中，右键点击-选择图表-图表
 * idea工具可视化的显示，当前项目中各jar包的依赖关系
 *
 * 2.找到spring-boot-starter-logging,看当前项目日志管理的日志门面+日志实现
 *
 * 3.可以看到，spring boot的日志管理，
 * 默认也采用的slf4j + logback
 *
 * 日志门面：slf4j(jul-to-slf4j.jar和log4j-to-slf4j.jar)
 * 日志实现：logback方式(logback-classic.jar和logback-core.jar)
 *
 * 4.如果引入了其他框架，那么其他框架的管理日志的默认jar包必须要移除掉
 * 否则，会出现包名冲突
 * 例如：spring框架默认使用的是commons-logging,那么spring boot是否排除了commons-logging呢？
 *
 * 二、再测试代码，就可以使用日志管理的方法
 * 日志的级别
 * 由低到高 trace<debug<info<warn<error
 *
 * logger.trace("这是trace日志……") 跟踪
 * logger.debug("这是debug日志……")
 * logger.info("这是info日志……") 打印信息
 * logger.warn("这是warn日志……")
 * logger.error("这是error日志……")
 *
 * 2.1 【输出原则】可以调整日志的输出级别：只会输出当前级别和当前更高的级别
 * 2.2 【默认级别】spring boot默认设置的是日志级别是info,也可以在配置文件中自定义设置日志的级别
 * 2.3 【命令自定义级别】自定义日志级别，也在配置文件中配置，例如：将当前项目的日志级别调整为trace
 * logging.level.com.example.springbootdemo3=trace
 *
 * 当前项目中生成springboot.log日志
 * logging.file.name=springboot.log
 * logging.file.name=D:/springboot.log
 *
 * logging.file.path=E:/springboot.log
 * 2.4 logging.file.name和logging.file.path是两个冲突设置，指定一个即可
 * 其中logging.file.name值也可以是路径
 * 2.5 logging.file.name和logging.file.path都指定的话，还是logging.file.name优先级更高，它会生效；
 * #5.与上面不同的是，指定了D盘，就是在D盘依次创建文件夹spring和log,默认日志文件名是spring.log;而不再是当前项目所在盘的根路径
 * logging.file.path=D:/spring/log结论：会在当前项目所在盘，是E盘，会在E盘创建文件夹spring,以及子文件夹，日志文件名称默认是spring.log
 * 2.6.在控制台输出日志的格式
 * #logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{50} -%msg%n
 * logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{50} -%msg%n
 * 2.7.输出日志文件的的格式
 * #logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{50} -%msg%n
 * logging.pattern.file=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} -%msg%n
 *
 * %d{日期格式} 表示日期
 * %thread 表示线程名
 * %-5level 表示级别，从左（-短横线的含义）显示5个字符宽度
 * %logger{50} 表示logger最长的名字是50个字符，否则按照句点分割
 * %msg 日志消息
 * %n 是换行符
 *
 *
 *
 *
 *
 *
 */