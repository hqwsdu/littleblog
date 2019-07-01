package com.example.zt.feeling.Model;


import com.example.zt.feeling.tools.URL;

public class ImageFile {
	private int file_id;
	private String file_address;
	private int blog_id;

	@Override
	public String toString() {
		return "ImageFile{" +
				"file_id=" + file_id +
				", file_address='" + file_address + '\'' +
				", blog_id=" + blog_id +
				'}';
	}

	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_address() {
		return file_address;
	}
	public void setFile_address(String file_address) {
		this.file_address = file_address;
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

}
