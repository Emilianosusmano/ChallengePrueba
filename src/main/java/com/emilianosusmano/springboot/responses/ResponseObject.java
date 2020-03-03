package com.emilianosusmano.springboot.responses;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.emilianosusmano.springboot.responses.utils.HTTPResponseCodesEnum;

public class ResponseObject<T> {
	private static Logger logger = Logger.getLogger(ResponseObject.class);

	private String statusCode;
	private String statusText;
	private String particularCode;
	private T result;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getParticularCode() {
		return particularCode;
	}

	public void setParticularCode(String particularCode) {
		this.particularCode = particularCode;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public ResponseObject<T> toStatus200OK() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_200.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_200.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	public ResponseObject<T> toStatus400BadRequest() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_400.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_400.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	public ResponseObject<T> toStatus401Unauthorized() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_401.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_401.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	public ResponseObject<T> toStatus403Forbidden() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_403.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_403.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	public ResponseObject<T> toStatus500InternalServerError() {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_500.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_500.getStatusText());
		this.loggingTheResponse();
		return this;
	}

	public ResponseObject<T> toStatus402PaymentRequired(String code) {
		this.setStatusCode(HTTPResponseCodesEnum.STATUS_402.getStatusCode());
		this.setStatusText(HTTPResponseCodesEnum.STATUS_402.getStatusText());
		this.particularCode = code;
		return this;
	}

	@Override
	public String toString() {
		// Separo el resultado, porque si es null, el json que genera la respuesta no es
		// valido.
		String resultString = (this.result != null) ? ",\"result\":{\"" + this.result + "\"}" : "";
		return "{\"statusCode\":\"" + this.statusCode + "\",\"statusText\":\"" + this.statusText + "\"" + resultString
				+ "}";
	}

	public void loggingTheResponse() {
		logger.info(MessageFormat.format("REST-RESPONSE: status:{0}", this.getStatusCode()));
	}

}
