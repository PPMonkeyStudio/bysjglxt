package com.bysjglxt.service;

public interface LoginOrWriteOffService {

	/**
	 * int --> -1账号不存在 -2密码错误 1教师登录成功 2学生登录成功
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int login(String username, String password);

	/**
	 * 
	 * 根据role的不同返回不同的DTO(Student Or Teacher)
	 * 
	 * @param role
	 * @return
	 */
	public Object loginInformation(int role, String username);

}
