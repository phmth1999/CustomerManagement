package com.phmth.service;

import com.phmth.dao.AccountDao;
import com.phmth.model.AccountModel;

public class AccountService {
	AccountDao userDao = new AccountDao();
	
	public boolean checkUserNameExist(String userName) throws Exception{
		boolean valid = false;
		try {
			if(userDao.selCheckUserNameExist(userName)) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	public AccountModel login(String userName, String password) throws Exception{
		AccountModel accountModel = null;
		try {
			if(checkUserNameExist(userName)) {
				if(userDao.selCheckLogin(userName, password) != null) {
					accountModel = userDao.selCheckLogin(userName, password);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountModel;
	}
}
