package com.example.demo.dto;

public class SimpleReponseDTO {
	private String message;
	private String status;
	
	public SimpleReponseDTO(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
