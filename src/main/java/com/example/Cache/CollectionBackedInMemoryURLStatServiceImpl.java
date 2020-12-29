package com.example.Cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class CollectionBackedInMemoryURLStatServiceImpl implements URLStatService {

	Map<URLAndRequestMethodPair, URLStat> urlNameToStatMap = new HashMap<>();

	@Override
	public URLStat findByURLNameAndRequestMethod(String url, RequestMethod method) {
		return urlNameToStatMap.getOrDefault(new URLAndRequestMethodPair(url, method), null);
	}

	@Override
	public List<URLStat> readAll() {
		return urlNameToStatMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
	}

	@Override
	public void putStat(URLStat stat) {
		urlNameToStatMap.put(new URLAndRequestMethodPair(stat.getUrl(), stat.getHttpRequestMethod()), stat);
	}

	@Override
	public void deleteStat(URLStat stat) {
		urlNameToStatMap.remove(new URLAndRequestMethodPair(stat.getUrl(), stat.getHttpRequestMethod()));
	}

	@Override
	public void deleteAll() {
		urlNameToStatMap = new HashMap<>();
	}
}
