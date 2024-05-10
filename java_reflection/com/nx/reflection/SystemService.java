package com.nx.reflection;

/**
 * @className: SystemService
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/10 18:10
 * @Version: 1.0
 */
public class SystemService {
	public String login(String admin, String password) {
		System.out.println("admin: " + admin);
		System.out.println("password: " + password);
		return "登录成功！";
	}

	public void logout() {
		System.out.println("退出系统");
	}

	public int show(int number) {
		System.out.println("要公布的数字是" + number);
		return number;

	}
}
