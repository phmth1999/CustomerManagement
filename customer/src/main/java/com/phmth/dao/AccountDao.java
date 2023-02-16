package com.phmth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.phmth.common.DBConnection;
import com.phmth.common.StatusAccount;
import com.phmth.model.AccountModel;

public class AccountDao {
	public boolean selCheckUserNameExist(String userName) throws Exception {
		Connection conn = null;
		boolean valid = false;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from accounts where username=BINARY ? and status=BINARY ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, StatusAccount.Activated.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return valid;
	}
	public AccountModel selCheckLogin(String userName, String password) throws Exception {
		Connection conn = null;
		AccountModel accountModel = null;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from accounts where username=BINARY ? and password=? and status=BINARY ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, StatusAccount.Activated.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				accountModel = new AccountModel(); 
				accountModel.setId(resultSet.getInt("id"));
				accountModel.setUsername(resultSet.getString("userName"));
				accountModel.setPassword(resultSet.getString("password"));
				accountModel.setFullname(resultSet.getString("fullname"));
				accountModel.setEmail(resultSet.getString("email"));
				accountModel.setPhone(resultSet.getString("phone"));
				accountModel.setAddress(resultSet.getString("address"));
				accountModel.setStatus(resultSet.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return accountModel;
	}
}
