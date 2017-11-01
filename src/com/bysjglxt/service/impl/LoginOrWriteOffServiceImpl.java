package com.bysjglxt.service.impl;

import com.bysjglxt.dao.LoginOrWriteOffDao;
import com.bysjglxt.service.LoginOrWriteOffService;

public class LoginOrWriteOffServiceImpl implements LoginOrWriteOffService {

	private LoginOrWriteOffDao loginOrWriteOffDao;

	public void setLoginOrWriteOffDao(LoginOrWriteOffDao loginOrWriteOffDao) {
		this.loginOrWriteOffDao = loginOrWriteOffDao;
	}

}
