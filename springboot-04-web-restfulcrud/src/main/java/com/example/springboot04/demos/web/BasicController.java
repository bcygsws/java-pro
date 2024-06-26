/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springboot04.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {
	// http://127.0.0.1:8080/
	// 导入dao和entities文件夹后，默认要访问到templates文件夹下的login.html
	// @RequestMapping({"/", "/login.html"})
	// public String login() {
	// 	return "login";
	// }

	// http://127.0.0.1:8080/hello?name=lisi
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
		return "Hello " + name;
	}

	// http://127.0.0.1:8080/user
	@RequestMapping("/user")
	@ResponseBody
	public User user() {
		User user = new User();
		user.setName("theonefx");
		user.setAge(666);
		return user;
	}

	// http://127.0.0.1:8080/save_user?name=newName&age=11
	@RequestMapping("/save_user")
	@ResponseBody
	public String saveUser(User u) {
		return "user will save: name=" + u.getName() + ", age=" + u.getAge();
	}

	// http://127.0.0.1:8080/html
	@RequestMapping("/html")
	public String html() {
		return "index.html";
	}

	@ModelAttribute
	public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
			, @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
		user.setName("zhangsan");
		user.setAge(18);
	}

	// thymeleaf模板引擎，可以解析类路径下的templates文件下的.html页面，在/templates文件夹下写一个success.html页面
	// 发送http://localhost:8080/success请求，看是否返回thymeleaf模板引擎解析的success.html页面？
	// 为了掩饰，thymeleaf语法，在success()方法中，放入一个map键值对
	@RequestMapping("/success")
	public String success(Map<String, Object> map) {
		// th:text="${hello}"可以在success.html页面中取值了
		// map.put("hello", "你好啊~");
		map.put("hello", "<h1>你好</h1>");
		map.put("users", Arrays.asList("屈原", "陶渊明", "杜甫", "苏轼"));
		// classpath: "/templates/success.html"
		// 返回的是类路径下templates文件夹下，一个.html文件的名称
		return "success";
	}
}
