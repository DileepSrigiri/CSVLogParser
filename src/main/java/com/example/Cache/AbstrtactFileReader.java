package com.example.Cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public abstract class AbstrtactFileReader<T> implements FileReader {

	private final String fileLocation;

	private List<T> constructedData = new ArrayList<>();

	AbstrtactFileReader(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public abstract void constructData(List<List<String>> data);

	public List<T> getConstructedData() {
		if (!CollectionUtils.isEmpty(constructedData)) {
			return constructedData;
		}
		constructData(readData());
		return constructedData;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setConstructedData(List<T> constructedData) {
		this.constructedData = constructedData;
	}

}
