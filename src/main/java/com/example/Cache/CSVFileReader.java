package com.example.Cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

public class CSVFileReader extends AbstrtactFileReader<URLInfo> {

	private static final String COMMA_DELIMITER = ",";

	CSVFileReader(String fileLocation) {
		super(fileLocation);
	}

	@Override
	public void constructData(List<List<String>> data) {
		List<URLInfo> urlInfos = new ArrayList<>();

		for (int i = 1; i < data.size(); i++) {
			Long timestamp = Long.parseLong(data.get(i).get(0));
			String url = URLUtil.maskURL(data.get(i).get(1));
			RequestMethod method = RequestMethod.valueOf(data.get(i).get(2));
			Long responseTime = Long.parseLong(data.get(i).get(3));
			HttpStatus status = HttpStatus.valueOf(Integer.parseInt(data.get(i).get(4)));
			URLInfo urlInfo = new URLInfo(url, timestamp, responseTime, method, status);
			urlInfos.add(urlInfo);
		}

		setConstructedData(urlInfos);
	}

	@Override
	public List<List<String>> readData() {
		List<List<String>> records = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(getFileLocation()));) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return records;
	}

	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return values;
	}
}
