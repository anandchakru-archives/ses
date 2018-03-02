package com.rathna.controller;

import java.util.LinkedHashMap;
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
import com.rathna.model.Cypher;
import com.rathna.model.Request;
import com.rathna.model.Response;
import com.rathna.service.Encryptr;

@Controller
public class Main {
	@Autowired
	private Encryptr encryptor;

	@RequestMapping("/")
	public @ResponseBody Map<String, String> mindeix() {
		Map<String, String> hashMap = new LinkedHashMap<>();
		hashMap.put("readme", "https://github.com/anandchakru/ses");
		hashMap.put("Encryption using HTTP.GET", "/e/payload/password");
		hashMap.put("Encryption example HTTP.GET", "http://still-hamlet-17048.herokuapp.com/e/FooBar/BJsP3xAX9Qq6G5D6");
		hashMap.put("Encryption using HTTP.POST",
				"curl -d '{\"payload\":\"FooBar\", \"pwd\":\"BJsP3xAX9Qq6G5D6\"}' -H \"Content-Type: application/json\" -X POST http://still-hamlet-17048.herokuapp.com/el");
		hashMap.put("Decryption using HTTP.GET",
				"http://still-hamlet-17048.herokuapp.com/d/cipherd_payload/iv/password");
		hashMap.put("Decryption example HTTP.GET",
				"/d/eb0fa49228d2d4c1b5f59a263b561c1e/73a3aca1cebe98b2f7ba81a713b56681/BJsP3xAX9Qq6G5D6");
		hashMap.put("Decryption using HTTP.POST",
				"curl -d '{\"payload\":\"eb0fa49228d2d4c1b5f59a263b561c1e\", \"iv\":\"73a3aca1cebe98b2f7ba81a713b56681\", \"pwd\":\"BJsP3xAX9Qq6G5D6\"}' -H \"Content-Type: application/json\" -X POST http://still-hamlet-17048.herokuapp.com/dl");
		return hashMap;
	}
	@RequestMapping(value = { "/el" }, method = RequestMethod.POST)
	public HttpEntity<Response> encryptLarge(@RequestBody Request request) throws Exception {
		return this.encrypt(request.getPayload(), request.getPwd());
	}
	@RequestMapping(value = { "/e/{value}/{pwd}" }, method = RequestMethod.GET)
	public HttpEntity<Response> encrypt(@PathVariable String value, @PathVariable String pwd) throws Exception {
		Cypher c = encryptor.encrypt(pwd, pwd.toUpperCase(), value);
		Response rsp = new Response(new String(c.getCiphertext()));
		rsp.setIv(new String(c.getIv()));
		rsp.setPwd(pwd);
		return new ResponseEntity<Response>(rsp, HttpStatus.OK);
	}
	@RequestMapping(value = { "/dl" }, method = RequestMethod.POST)
	public HttpEntity<Response> decryptLarge(@RequestBody Request request) throws Exception {
		return this.decrypt(request.getPayload(), request.getIv(), request.getPwd());
	}
	@RequestMapping("/d/{value}/{iv}/{pwd}")
	public HttpEntity<Response> decrypt(@PathVariable String value, @PathVariable String iv, @PathVariable String pwd)
			throws Exception {
		Response rsp = new Response(encryptor.decrypt(pwd, pwd.toUpperCase(), value.toCharArray(), iv.toCharArray()));
		return new ResponseEntity<Response>(rsp, HttpStatus.OK);
	}
}
