package com.example.Cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

public class InMemoryURLStatsAggregatorServiceImpl implements URLStatsAggregatorService {

	private URLStatsCalculatorService urlStatsCalculatorService;

	private URLStatService urlStatService;

	private Map<Integer, List<URLStat>> urlFreqMap = new HashMap<>();

	InMemoryURLStatsAggregatorServiceImpl(URLStatsCalculatorService urlStatsCalculatorService,
			URLStatService urlStatService) {
		this.urlStatsCalculatorService = urlStatsCalculatorService;
		this.urlStatService = urlStatService;
	}

	@Override
	public List<URLStat> getTopNFrequentedURLS(int n) {
		if (urlFreqMap.get(n) != null) {
			return urlFreqMap.get(n);
		}
		List<URLStat> topFrequentedURLS = calculateTopNFrequentedURLS(n);
		Collections.sort(topFrequentedURLS, Collections.reverseOrder());
		urlFreqMap.put(n, topFrequentedURLS);
		return topFrequentedURLS;
	}

	private List<URLStat> calculateTopNFrequentedURLS(int n) {
		urlStatsCalculatorService.calculateStats();
		List<URLStat> urlStats = urlStatService.readAll();
		PriorityQueue<URLStat> pq = new PriorityQueue<>(n);
		for (int i = 0; i < urlStats.size(); i++) {
			if (pq.size() < n) {
				pq.add(urlStats.get(i));
			} else {
				if (urlStats.get(i).getNoOfHits() > pq.peek().getNoOfHits()) {
					pq.poll();
					pq.add(urlStats.get(i));
				}
			}
		}

		return pq.stream().collect(Collectors.toList());
	}

	@Override
	public List<URLStat> getStats() {
		return urlStatService.readAll();
	}

}
