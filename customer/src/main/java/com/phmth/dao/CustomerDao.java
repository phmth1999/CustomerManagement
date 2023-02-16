package com.phmth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.phmth.common.DBConnection;
import com.phmth.common.StatusCustomer;
import com.phmth.dto.FormSearch;
import com.phmth.model.CustomerModel;

public class CustomerDao {
	public List<CustomerModel> selCustomer(FormSearch formSearch, int page, int limit) throws Exception {
		Connection conn = null;
		List<CustomerModel> listCustomer = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from customers where status=BINARY '"+StatusCustomer.Activated.toString()+"'";
			if(formSearch.getFullname() != null && !formSearch.getFullname().isEmpty()) {
				sql += " and fullname like '"+formSearch.getFullname()+"%'";
			}
			if(formSearch.getSex() != null && !formSearch.getSex().isEmpty()) {
				sql += " and sex=BINARY '"+formSearch.getSex()+"'";
			}
			if(formSearch.getBirthdayFirst() != null && !formSearch.getBirthdayFirst().isEmpty()) {
				sql += " and birthday>='"+formSearch.getBirthdayFirst()+"'";
			}
			if(formSearch.getBirthdayLast() != null && !formSearch.getBirthdayLast().isEmpty()) {
				sql += " and birthday<='"+formSearch.getBirthdayLast()+"'";
			}
			sql += " order by id desc limit ?, ? ";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, page);
			preparedStatement.setInt(2, limit);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CustomerModel customerModel = new CustomerModel();
				customerModel.setId(resultSet.getInt("id"));
				customerModel.setFullname(resultSet.getString("fullname"));
				customerModel.setSex(resultSet.getString("sex"));
				customerModel.setBirthday(resultSet.getString("birthday"));
				customerModel.setEmail(resultSet.getString("email"));
				customerModel.setPhone(resultSet.getString("phone"));
				customerModel.setAddress(resultSet.getString("address"));
				listCustomer.add(customerModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listCustomer;
	}
	public int selCountCustomer(FormSearch formSearch) throws Exception {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select count(id) as count from customers where status=BINARY ?";
			if(formSearch.getFullname() != null && !formSearch.getFullname().isEmpty()) {
				sql += " and fullname like '"+formSearch.getFullname()+"%'";
			}
			if(formSearch.getSex() != null && !formSearch.getSex().isEmpty()) {
				sql += " and sex=BINARY '"+formSearch.getSex()+"'";
			}
			if(formSearch.getBirthdayFirst() != null && !formSearch.getBirthdayFirst().isEmpty()) {
				sql += " and birthday>='"+formSearch.getBirthdayFirst()+"'";
			}
			if(formSearch.getBirthdayLast() != null && !formSearch.getBirthdayLast().isEmpty()) {
				sql += " and birthday<='"+formSearch.getBirthdayLast()+"'";
			}
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, StatusCustomer.Activated.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return count;
	}
	public CustomerModel selCustomerById(int id) throws Exception {
		Connection conn = null;
		CustomerModel customerModel = new CustomerModel();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from customers where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customerModel.setId(resultSet.getInt("id"));
				customerModel.setFullname(resultSet.getString("fullname"));
				customerModel.setSex(resultSet.getString("sex"));
				customerModel.setBirthday(resultSet.getString("birthday"));
				customerModel.setEmail(resultSet.getString("email"));
				customerModel.setPhone(resultSet.getString("phone"));
				customerModel.setAddress(resultSet.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);
		}
		return customerModel;
	}
	public int insertCustomer(CustomerModel customerModel) throws Exception {
		Connection conn = null;
		int newCustomerID = 0;
		try {
			conn = DBConnection.connectionDB();
			conn.setAutoCommit(false);
			String sql = "insert into customers(fullname, sex, birthday, email, phone, address, status) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, customerModel.getFullname());
			preparedStatement.setString(2, customerModel.getSex());
			preparedStatement.setString(3, customerModel.getBirthday());
			preparedStatement.setString(4, customerModel.getEmail());
			preparedStatement.setString(5, customerModel.getPhone());
			preparedStatement.setString(6, customerModel.getAddress());
			preparedStatement.setString(7, StatusCustomer.Activated.toString());
			newCustomerID = preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			DBConnection.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return newCustomerID;
	}
	public void updateCustomer(CustomerModel customerModel) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			conn.setAutoCommit(false);
			String sql = "update customers set fullname=?, sex=?, birthday=?, email=?, phone=?, address=? where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, customerModel.getFullname());
			preparedStatement.setString(2, customerModel.getSex());
			preparedStatement.setString(3, customerModel.getBirthday());
			preparedStatement.setString(4, customerModel.getEmail());
			preparedStatement.setString(5, customerModel.getPhone());
			preparedStatement.setString(6, customerModel.getAddress());
			preparedStatement.setInt(7, customerModel.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			DBConnection.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
	public void deleteCustomer(String customerId) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			conn.setAutoCommit(false);
			String sql = "update customers "
					   + "set status=? "
					   + "where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, StatusCustomer.InActivated.toString());
			preparedStatement.setString(2, customerId);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			DBConnection.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
}
