package com.example.Cache;

import org.springframework.web.bind.annotation.RequestMethod;

public class URLAndRequestMethodPair {
	private String url;
	private RequestMethod method;

	URLAndRequestMethodPair(String url, RequestMethod method) {
		this.url = url;
		this.method = method;
	}

	@Override
	public int hashCode() {
		return url.hashCode() + method.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		URLAndRequestMethodPair other = (URLAndRequestMethodPair) (o);

		return this.url.equals(other.url) && this.method.equals(other.method);
	}
}
