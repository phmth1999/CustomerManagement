package com.phmth.service;

import java.util.ArrayList;
import java.util.List;

import com.phmth.dao.CustomerDao;
import com.phmth.dto.FormSearch;
import com.phmth.model.CustomerModel;

public class CustomerService {
	CustomerDao customerDao = new CustomerDao();
	
	public List<CustomerModel> getCustomer(FormSearch formSearch, int page, int limit) throws Exception {
		List<CustomerModel> listCustomer = new ArrayList<>();
		try {
			listCustomer = customerDao.selCustomer(formSearch, page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCustomer;
	}
	public int countCustomer(FormSearch formSearch) throws Exception{
		int count = 0;
		try {
			count = customerDao.selCountCustomer(formSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public CustomerModel getCustomerById(int id) throws Exception{
		CustomerModel customerModel = new CustomerModel();
		try {
			customerModel = customerDao.selCustomerById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerModel;
	}
	public int addCustomer(CustomerModel customerModel) {
		int newCustomerID = 0;
		try {
			newCustomerID = customerDao.insertCustomer(customerModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCustomerID;
	}
	public void editCustomer(CustomerModel customerModel) {
		try {
			customerDao.updateCustomer(customerModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteCustomer(String customerId) {
		try {
			customerDao.deleteCustomer(customerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
