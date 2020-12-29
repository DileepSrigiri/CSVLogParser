package com.example.Cache;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

public interface URLStatService {

	URLStat findByURLNameAndRequestMethod(String url, RequestMethod method);

	List<URLStat> readAll();

	 void putStat(URLStat stat);

	 void deleteStat(URLStat stat);

	 void deleteAll();

}
