package com.rathna.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cypher implements Serializable {
	private char[] ciphertext;
	private char[] iv;

	public Cypher(char[] ciphertext, char[] iv) {
		super();
		this.ciphertext = ciphertext;
		this.iv = iv;
	}
	public char[] getCiphertext() {
		return ciphertext;
	}
	public void setCiphertext(char[] ciphertext) {
		this.ciphertext = ciphertext;
	}
	public char[] getIv() {
		return iv;
	}
	public void setIv(char[] iv) {
		this.iv = iv;
	}
}
