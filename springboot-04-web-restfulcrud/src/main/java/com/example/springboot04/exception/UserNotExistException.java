package com.example.springboot04.exception;

/**
 * @className: UserNotExistException
 * @description:TODO
 * @author: Bao Chengyi
 * @date: 2024/5/14 19:49
 * @Version: 1.0
 */
public class UserNotExistException extends RuntimeException {

	public UserNotExistException(String message) {
		// 访问：访问：localhost:8080/crud/hel?user=aaa，抛出这个自定义异常
		super(message);
	}
}
