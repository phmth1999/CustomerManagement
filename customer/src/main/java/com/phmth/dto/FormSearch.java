package com.phmth.dto;

public class FormSearch {
	private int page;
	private String fullname;
	private String sex;
	private String birthdayFirst;
	private String birthdayLast;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public String getBirthdayFirst() {
		return birthdayFirst;
	}

	public void setBirthdayFirst(String birthdayFirst) {
		this.birthdayFirst = birthdayFirst;
	}

	public String getBirthdayLast() {
		return birthdayLast;
	}

	public void setBirthdayLast(String birthdayLast) {
		this.birthdayLast = birthdayLast;
	}

}
