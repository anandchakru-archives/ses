package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.Encryptr;
import com.example.model.Cypher;
import com.example.model.Request;
import com.example.model.Response;

@Controller
public class Main {
	@Autowired
	private Encryptr encryptor;

	@RequestMapping("/")
	public @ResponseBody Map<String, String> mindeix() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("eg1", "/e/k/v");
		hashMap.put("eg2", "/d/k/v/i");
		return hashMap;
	}
	@RequestMapping(value = { "/el" }, method = RequestMethod.POST)
	public HttpEntity<Response> encryptLarge(@RequestBody Request request) throws Exception {
		return this.encrypt(request.getValue(), request.getPwd());
	}
	@RequestMapping(value = { "/e/{value}/{pwd}" }, method = RequestMethod.GET)
	public HttpEntity<Response> encrypt(@PathVariable String value, @PathVariable String pwd) throws Exception {
		Cypher c = encryptor.encrypt(pwd, pwd.toUpperCase(), value);
		return new ResponseEntity<Response>(
				new Response("/d/" + new String(c.getCiphertext()) + "/" + new String(c.getIv()) + "/" + pwd),
				HttpStatus.OK);
	}
	@RequestMapping(value = { "/dl" }, method = RequestMethod.POST)
	public HttpEntity<Response> decryptLarge(@RequestBody Request request) throws Exception {
		return this.decrypt(request.getValue(), request.getIv(), request.getPwd());
	}
	@RequestMapping("/d/{value}/{iv}/{pwd}")
	public HttpEntity<Response> decrypt(@PathVariable String value, @PathVariable String iv, @PathVariable String pwd)
			throws Exception {
		return new ResponseEntity<Response>(
				new Response(encryptor.decrypt(pwd, pwd.toUpperCase(), value.toCharArray(), iv.toCharArray())),
				HttpStatus.OK);
	}
}
