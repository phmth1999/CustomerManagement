package com.phmth.model;

public class CustomerModel {
	private int id;
	private String fullname;
	private String sex;
	private String birthday;
	private String email;
	private String phone;
	private String address;
	private String status;

	public CustomerModel() {
	}

	public CustomerModel(int id, String fullname, String sex, String birthday, String email, String phone,
			String address, String status) {
		this.id = id;
		this.fullname = fullname;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
