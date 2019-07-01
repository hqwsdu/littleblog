package com.example.zt.feeling.Model;

public class Blog {
	public int blog_id;
	public String blog_date;
	public String blog_content;
	public String blogstate;
	public String blogtype;
	public int bloguser_id;
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public String getBlog_date() {
		return blog_date;
	}
	public void setBlog_date(String blog_date) {
		this.blog_date = blog_date;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}
	public String getBlogstate() {
		return blogstate;
	}
	public void setBlogstate(String blogstate) {
		this.blogstate = blogstate;
	}
	public String getBlogtype() {
		return blogtype;
	}
	public void setBlogtype(String blogtype) {
		this.blogtype = blogtype;
	}
	public int getBloguser_id() {
		return bloguser_id;
	}
	public void setBloguser_id(int bloguser_id) {
		this.bloguser_id = bloguser_id;
	}
	public Blog() {
		super();
	}
	

}
