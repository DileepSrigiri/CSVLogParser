package com.example.Cache;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMethod;

public class URLStat implements Comparable<URLStat> {

	private String url;
	private RequestMethod httpRequestMethod;
	private Long minResponseTime;
	private Long maxResponseTime;
	private Double averageResponseTime;

	private Integer noOfHits;

	public URLStat(String url, Long minResponseTime, RequestMethod httpRequestMethod, Long maxResponseTime,
			Double averageResponseTime,
			Integer noOfHits) {
		this.url = url;
		this.minResponseTime = minResponseTime;
		this.maxResponseTime = maxResponseTime;
		this.averageResponseTime = averageResponseTime;
		this.noOfHits = noOfHits;
		this.httpRequestMethod = httpRequestMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getMinResponseTime() {
		return minResponseTime;
	}

	public void setMinResponseTime(Long minResponseTime) {
		this.minResponseTime = minResponseTime;
	}

	public Long getMaxResponseTime() {
		return maxResponseTime;
	}

	public void setMaxResponseTime(Long maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}

	public Double getAverageResponseTime() {
		return averageResponseTime;
	}

	public void setAverageResponseTime(Double averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	public Integer getNoOfHits() {
		return noOfHits;
	}

	public void setNoOfHits(Integer noOfHits) {
		this.noOfHits = noOfHits;
	}

	public RequestMethod getHttpRequestMethod() {
		return httpRequestMethod;
	}

	public void setHttpRequestMethod(RequestMethod httpRequestMethod) {
		this.httpRequestMethod = httpRequestMethod;
	}

	@Override
	public int compareTo(URLStat o) {
		return this.noOfHits.compareTo(o.noOfHits);
	}
}
