package com.example.Cache;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

public class URLInfo {

	private String url;
	private Long timeStamp;
	private Long responeTime;
	private RequestMethod httpRequestMethod;
	private HttpStatus httpStatus;

	public URLInfo(String url, Long timeStamp, Long responeTime,
			RequestMethod httpRequestMethod, HttpStatus httpStatus) {
		this.url = url;
		this.timeStamp = timeStamp;
		this.responeTime = responeTime;
		this.httpRequestMethod = httpRequestMethod;
		this.httpStatus = httpStatus;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String URL) {
		this.url = URL;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getResponeTime() {
		return responeTime;
	}

	public void setResponeTime(Long responeTime) {
		this.responeTime = responeTime;
	}

	public RequestMethod getHttpRequestMethod() {
		return httpRequestMethod;
	}

	public void setHttpRequestMethod(RequestMethod httpRequestMethod) {
		this.httpRequestMethod = httpRequestMethod;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
