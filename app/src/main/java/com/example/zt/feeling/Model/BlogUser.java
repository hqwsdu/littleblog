package com.example.zt.feeling.Model;

public class BlogUser {
	private int bloguser_id;
	private String bloguser_nickname;
	private String bloguser_phone;
	private String bloguser_password;
	private String bloguser_date;
	private String bloguser_address;
	private String bloguser_state;
	private String bloguser_data;
	public int getBloguser_id() {
		return bloguser_id;
	}
	public void setBloguser_id(int bloguser_id) {
		this.bloguser_id = bloguser_id;
	}
	public String getBloguser_nickname() {
		return bloguser_nickname;
	}
	public void setBloguser_nickname(String bloguser_nickname) {
		this.bloguser_nickname = bloguser_nickname;
	}
	public String getBloguser_phone() {
		return bloguser_phone;
	}
	public void setBloguser_phone(String bloguser_phone) {
		this.bloguser_phone = bloguser_phone;
	}
	public String getBloguser_password() {
		return bloguser_password;
	}
	public void setBloguser_password(String bloguser_password) {
		this.bloguser_password = bloguser_password;
	}
	public String getBloguser_date() {
		return bloguser_date;
	}
	public void setBloguser_date(String bloguser_date) {
		this.bloguser_date = bloguser_date;
	}
	public String getBloguser_address() {
		return bloguser_address;
	}
	public String getBloguser_state() {
		return bloguser_state;
	}
	public void setBloguser_state(String bloguser_state) {
		this.bloguser_state = bloguser_state;
	}
	public void setBloguser_address(String bloguser_address) {
		this.bloguser_address = bloguser_address;
	}
	public String getBloguser_data() {
		return bloguser_data;
	}
	public void setBloguser_data(String bloguser_data) {
		this.bloguser_data = bloguser_data;
	}
	public BlogUser(int bloguser_id, String bloguser_nickname, String bloguser_phone, String bloguser_password,
			String bloguser_date, String bloguser_address, String bloguser_state, String bloguser_data) {
		super();
		this.bloguser_id = bloguser_id;
		this.bloguser_nickname = bloguser_nickname;
		this.bloguser_phone = bloguser_phone;
		this.bloguser_password = bloguser_password;
		this.bloguser_date = bloguser_date;
		this.bloguser_address = bloguser_address;
		this.bloguser_state = bloguser_state;
		this.bloguser_data = bloguser_data;
	}
	public BlogUser() {
		super();
	}
	@Override
	public String toString() {
		return "BlogUser [bloguser_id=" + bloguser_id + ", bloguser_nickname=" + bloguser_nickname + ", bloguser_phone="
				+ bloguser_phone + ", bloguser_password=" + bloguser_password + ", bloguser_date=" + bloguser_date
				+ ", bloguser_address=" + bloguser_address + ", bloguser_state=" + bloguser_state + ", bloguser_data="
				+ bloguser_data + "]";
	}

	

}
