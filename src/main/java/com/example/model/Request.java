package com.example.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {
	private String payload;
	private String iv;
	private String pwd;

	public Request(String payload, String pwd) {
		super();
		this.payload = payload;
		this.pwd = pwd;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
