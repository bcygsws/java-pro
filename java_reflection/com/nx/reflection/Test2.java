package com.nx.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @className: Test2
 * @description: Java反射机制（Java Reflection）：在运行时动态的加载类的完整结构信息和调用类的方法的机制
 * 是Java的一种运行时(动态)访问、检测和修改它本身的能力
 * @author: Bao Chengyi
 * @date: 2024/5/10 18:08
 * @Version: 1.0
 */
// 使用反射机制为SystemService的成员方法login,传入了形参
public class Test2 {
	// throws Exception一定要写，避免代码频繁报异常错误
	public static void main(String[] args) throws Exception {
		// 功能：为SystemService类的login方法注入实参
		Class<?> clazz = Class.forName("com.nx.reflection.SystemService");
		// 1.创建SystemService类实例
		// newInstance()创建类实例对象的方式，已经被弃用
		// Object obj = clazz.newInstance();
		Constructor<?> cons = clazz.getConstructor();
		Object obj = cons.newInstance();

		// 2.获取类的方法名
		Method logMethod = clazz.getMethod("login", String.class, String.class);
		// 3.为SystemService类的login方法传入实参
		Object result = logMethod.invoke(obj, "admin", "abc123456");
		System.out.println(result);
	}
}
