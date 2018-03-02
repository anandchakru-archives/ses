package com.example.model;

import java.io.Serializable;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Response extends ResourceSupport implements Serializable {
	private String data;

	@JsonCreator
	public Response(@JsonProperty("data") String data) {
		super();
		this.data = data;
	}
	public String getData() {
		return data;
	}
}