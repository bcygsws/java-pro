package com.example.springboot04.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @className: MyLocaleResolver
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/10 23:36
 * @Version: 1.0
 */
public class MyLocaleResolver implements LocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// langStr为en_US或者en_CN
		String langStr = request.getParameter("lang");
		Locale locale;
		locale = Locale.getDefault();
		if (!StringUtils.isEmpty(langStr)) {// 如果langStr不为空，表示th:href中携带了参数，拼接locale
			String[] l = langStr.split("_");
			locale = new Locale(l[0], l[1]);
		}
		// 上面if成立，表示携带了请求参数；返回这个locale
		// 上面if不成立，表示没有携带请求参数，则返回22行默认的这个locale
		// 最后MyLocaleResolver需要添加到容器中，在config/MyMvcConfig配置类中，将这个组件添加到容器中
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

	}
}
