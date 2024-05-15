package com.example.springboot04.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @className: MyFilter
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/15 22:14
 * @Version: 1.0
 */
public class MyFilter implements Filter {
	// 过滤器的初始化
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	// 过滤器的处理
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// 	打印一个内容
		System.out.println("MyFilter process……");
		// 然后放行请求就可以了
		filterChain.doFilter(servletRequest, servletResponse);
	}

	// 过滤器的销毁
	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
