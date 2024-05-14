package com.example.springboot04.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @className: MyErrorAttributes
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/15 3:36
 * @Version: 1.0
 */
// 给容器中加入我们自定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		Map<String, Object> map = super.getErrorAttributes(webRequest, options);
		// 添加一些自己的字段，比如说company，那么每一次错误请求，都返回增加了一个componeny字段
		map.put("company", "Microsoft");
		// webRequest参数对象中，getAttribute方法，方便我们取出我们自定义的异常处理类MyExceptionHandler中的ext里保存的map
		// webRequest.getAttribute("ext", 0)，返回的只是一个Object,强制类型转换
		Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
		// 把取出的ext存入当前map后，一并返回
		map.put("ext", ext);
		// map中除了自定义的company外，还增加了ext字段，ext字段的结构取决于我们在MyExceptionHandler中的自定义
		return map;

	}
}
