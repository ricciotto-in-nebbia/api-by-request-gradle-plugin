package com.devtool.api.by.request.dev.util;

import com.devtool.api.by.request.dev.model.ApiRequest;
import com.devtool.api.by.request.dev.model.ApiRequestProperty;

import java.util.stream.Collectors;

public class ApiRequestJsonUtil {

	public static String getJsonString(ApiRequest request) {
		return request.getProperties().stream()
		              .map(ApiRequestJsonUtil::propertyToString)
		              .collect(Collectors.joining(
			              ",",
			              "{\"projectName\":\"" + request.getProjectName() + "\",\"properties\":[",
			              "]}"));
	}

	private static String propertyToString(ApiRequestProperty property) {
		return "{" +
			"\"name\":" + "\"" + property.getName() + "\"" + "," +
			"\"actual\":" + "\"" + property.getActual() + "\"" + "," +
			"\"updateNeeded\":" + property.getUpdateNeeded() +
			"}";
	}
}