package com.oracle.shoesMaket.model.bean;

public class User {
	
	private int userid;
	private String firstname;
	private String lastname;
	private String sex;
	private String email;
	private String password;

	private int age;
	private int phone;
	private String image;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public User(int userid, String firstname, String lastname, String sex, String email, String password,
			int age, int phone, String image) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.email = email;
		this.password = password;
		
		this.age = age;
		this.phone = phone;
		this.image = image;
	}
	
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", sex=" + sex
				+ ", email=" + email + ", password=" + password +  ", age=" + age + ", phone="
				+ phone + ", image=" + image + "]";
	}
	
	
}
