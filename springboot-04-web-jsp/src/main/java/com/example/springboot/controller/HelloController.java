package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @className: HelloController
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/17 2:54
 * @Version: 1.0
 */
@Controller
public class HelloController {
	//访问：http://localhost:8080/abc
	@GetMapping("/abc")
	public String doAbc(Model model) {
		model.addAttribute("msg", "你好啊~");
		// 在jsp页面中，${msg}取值
		// 在主配置文件使用spring.mvc.view.prefix和spring.mvc.view.suffix配置拼串的前缀和后缀
		return "success";
	}
}
