package com.example.Cache;

import java.util.List;

public interface URLStatsAggregatorService {

	public List<URLStat> getTopNFrequentedURLS(int n);

	public List<URLStat> getStats();

}
