package com.example.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {
	private String value;
	private String iv;
	private String pwd;

	public Request(String value, String pwd) {
		super();
		this.value = value;
		this.pwd = pwd;
	}
	public String getValue() {
		return value;
	}
	public String getPwd() {
		return pwd;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
