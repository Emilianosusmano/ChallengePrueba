package com.emilianosusmano.springboot.responses;

import org.springframework.http.HttpStatus;

public class ResponseMutante {
	private HttpStatus status;
	private String esMutante;

	public String getEsMutante() {
		return esMutante;
	}

	public void setEsMutante(String esMutante) {
		this.esMutante = esMutante;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
