package com.example.Cache;

import java.util.List;

import org.springframework.util.CollectionUtils;

public class URLStatsCalculatorServiceImpl implements URLStatsCalculatorService {

	private URLStatService urlStatService;

	private final List<URLInfo> urlInfos;

	URLStatsCalculatorServiceImpl(List<URLInfo> urlInfos, URLStatService urlStatService) {
		this.urlInfos = urlInfos;
		this.urlStatService = urlStatService;
	}

	private void calculateStatsFromInfo() {

		for (int i = 0; i < urlInfos.size(); i++) {
			URLInfo urlInfo = urlInfos.get(i);

			if (urlStatService.findByURLNameAndRequestMethod(urlInfo.getURL(), urlInfo.getHttpRequestMethod())
					!= null) {
				URLStat urlStat = urlStatService
						.findByURLNameAndRequestMethod(urlInfo.getURL(), urlInfo.getHttpRequestMethod());

				setTimeStatsAndIncreaseHitsCount(urlInfo, urlStat);
			} else {
				URLStat newStat = new URLStat(urlInfo.getURL(), urlInfo.getResponeTime(),
						urlInfo.getHttpRequestMethod(),
						urlInfo.getResponeTime(), urlInfo.getResponeTime().doubleValue(), 1);
				urlStatService.putStat(newStat);
			}
		}
	}

	private void setTimeStatsAndIncreaseHitsCount(URLInfo urlInfo, URLStat urlStat) {

		if (urlStat.getMaxResponseTime() < urlInfo.getResponeTime()) {
			urlStat.setMaxResponseTime(urlInfo.getResponeTime());
		}

		if (urlStat.getMinResponseTime() > urlInfo.getResponeTime()) {
			urlStat.setMinResponseTime(urlInfo.getResponeTime());
		}

		Double currentAverageResponseTime = urlStat.getAverageResponseTime();

		urlStat.setAverageResponseTime(
				(currentAverageResponseTime * urlStat.getNoOfHits() + urlInfo.getResponeTime()) / (urlStat.getNoOfHits()
						+ 1));
		urlStat.setNoOfHits(urlStat.getNoOfHits() + 1);
	}

	@Override
	public void calculateStats() {
		if (CollectionUtils.isEmpty(urlStatService.readAll())) {
			calculateStatsFromInfo();
		}
	}
}
