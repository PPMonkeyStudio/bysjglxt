package com.bysjglxt.service.impl;

import com.bysjglxt.dao.TestDao;
import com.bysjglxt.domain.DO.test;
import com.bysjglxt.service.TestService;

public class TestServiceImpl implements TestService {
	private TestDao testDao;

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public void addTest(test t) {
		testDao.addTest(t);
	}
}
