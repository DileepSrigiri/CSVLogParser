package com.example.Cache;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheApplication {

	public static void main(String[] args) {
		String fileName = "Sampleinput.csv";
		CSVFileReader csvFileReader = new CSVFileReader(fileName);
		URLStatService urlStatService = new CollectionBackedInMemoryURLStatServiceImpl();
		URLStatsCalculatorService urlStatsCalculatorService = new URLStatsCalculatorServiceImpl(
				csvFileReader.getConstructedData(), urlStatService);
		URLStatsAggregatorService urlStatsAggregatorService = new InMemoryURLStatsAggregatorServiceImpl(
				urlStatsCalculatorService, urlStatService);

		List<URLStat> topFiveFrequentedURLs = urlStatsAggregatorService.getTopNFrequentedURLS(5);

		printTopFiveFrequentedURLs(topFiveFrequentedURLs);

		printallStats(urlStatService.readAll());
	}

	private static void printTopFiveFrequentedURLs(List<URLStat> stats) {
		for (int i = 0; i < stats.size(); i++) {
			System.out.print("method :" + stats.get(i).getHttpRequestMethod().toString() + " ");
			System.out.print("URL :" + stats.get(i).getUrl() + " ");
			System.out.print("Frequency :" + stats.get(i).getNoOfHits());
			System.out.println();
		}
		System.out.println("--------------------------------------------");
	}

	private static void printallStats(List<URLStat> stats) {
		for (int i = 0; i < stats.size(); i++) {
			System.out.print("method :" + stats.get(i).getHttpRequestMethod().toString() + " ");
			System.out.print("URL :" + stats.get(i).getUrl() + " ");
			System.out.print("Min Time :" + stats.get(i).getMinResponseTime());
			System.out.print("Max Time :" + stats.get(i).getMaxResponseTime());
			System.out.print("Average Time :" + Double
					.parseDouble(String.format("%.2f", stats.get(i).getAverageResponseTime())));
			System.out.println();
		}
	}
}