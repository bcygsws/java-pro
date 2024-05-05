package com.example.springbootdemo3;

import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemo3ApplicationTests {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	void contextLoads() {
		// 日志的级别
		// 级别由高到低分别是：trace<debug<info<warn<error
		// 可以调整日志的输出级别：只会输出当前级别和当前更高的级别
		// spring boot默认设置的是日志级别是info,也可以在配置文件中自定义设置日志的级别
		// 自定义日志级别，也在配置文件中配置，例如：将当前项目的日志级别调整为trace
		// logging.level.com.example.springbootdemo3=trace
		logger.trace("这是trace日志……");
		logger.debug("这是debug日志……");
		logger.info("这是info日志……");
		logger.warn("这是warn日志……");
		logger.error("这是error日志……");

	}

}
