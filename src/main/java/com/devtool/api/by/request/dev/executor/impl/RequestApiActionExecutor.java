package com.devtool.api.by.request.dev.executor.impl;

import com.devtool.api.by.request.dev.exception.RequestApiGradlePluginException;
import com.devtool.api.by.request.dev.executor.ActionExecutor;
import com.devtool.api.by.request.dev.model.ApiRequest;
import com.devtool.api.by.request.dev.model.ApiRequestProperty;
import com.devtool.api.by.request.dev.util.ApiRequestJsonUtil;
import com.devtool.api.by.request.dev.util.PathUtil;
import groovy.lang.Tuple3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.gradle.internal.impldep.com.google.common.net.HttpHeaders.CONTENT_TYPE;

public class RequestApiActionExecutor implements ActionExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestApiActionExecutor.class);
	//
	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset utf-8";
	private static final int REQUEST_TIMEOUT = 15;
	private static final int CONNECT_TIMEOUT = 15;
	//
	private final File projectDir;
	private final String projectName;
	private final String uri;
	private final List<Tuple3<String, String, Boolean>> apiData;
	private final int requestTimeout;
	private final int connectTimeout;

	public RequestApiActionExecutor(File projectDir,
	                                final String projectName,
	                                final String uri,
	                                final List<Tuple3<String, String, Boolean>> apiData,
	                                final Integer requestTimeout,
	                                final Integer connectTimeout) {
		this.projectDir = projectDir;
		this.projectName = projectName;
		this.uri = uri;
		this.apiData = apiData;

		if (requestTimeout > 0) {
			this.requestTimeout = requestTimeout;
		} else {
			this.requestTimeout = REQUEST_TIMEOUT;
		}

		if (connectTimeout > 0) {
			this.connectTimeout = connectTimeout;
		} else {
			this.connectTimeout = CONNECT_TIMEOUT;
		}
	}

	@Override
	public void execute() {
		try {
			final Path path = PathUtil.getPath(projectDir);
			final ApiRequest apiRequest = createApiRequest();
			final String jsonString = ApiRequestJsonUtil.getJsonString(apiRequest);

			LOGGER.info(" - info: url is " + uri);
			LOGGER.info(" - info: requestTimeout is " + this.requestTimeout + " seconds");
			LOGGER.info(" - info: connectTimeout is " + this.connectTimeout + " seconds");
			LOGGER.info(" - info: message is '" + projectName + "'");
			LOGGER.info(" - info: request is '" + jsonString + "'");

			final HttpRequest request = HttpRequest.newBuilder()
			                                       .uri(URI.create(uri))
			                                       .header(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8)
			                                       .POST(HttpRequest.BodyPublishers.ofString(jsonString, StandardCharsets.UTF_8))
			                                       .timeout(Duration.of(requestTimeout, ChronoUnit.SECONDS))
			                                       .build();

			final HttpClient client = HttpClient.newBuilder()
			                                    .connectTimeout(Duration.of(connectTimeout, ChronoUnit.SECONDS))
			                                    .build();

			final HttpResponse.BodyHandler<Path> pathBodyHandler = HttpResponse.BodyHandlers.ofFileDownload(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

			final HttpResponse<Path> response = client.send(request, pathBodyHandler);
			LOGGER.info(" - info: API received");
		} catch (RuntimeException | IOException | InterruptedException e) {
			LOGGER.error(" - error: API receiving error ", e);
			throw new RequestApiGradlePluginException(e);
		}
	}

	private ApiRequest createApiRequest() {
		final List<ApiRequestProperty> properties =
			apiData.stream()
			       .map(it -> ApiRequestProperty.builder()
			                                    .name(it.getV1())
			                                    .actual(getDateTime(it.getV2()))
			                                    .updateNeeded(it.getV3())
			                                    .build())
			       .collect(Collectors.toUnmodifiableList());

		return ApiRequest.builder()
		                 .projectName(projectName)
		                 .properties(properties)
		                 .build();
	}

	private static Instant getDateTime(String data) {
		Instant result;

		try {
			if (Objects.equals(data, "latest")) {
				result = Instant.now();
			} else {
				result = Instant.parse(data);
			}
		} catch (RuntimeException e) {
			LOGGER.error(" - error: unable to parse date", e);
			throw new RequestApiGradlePluginException(e);
		}

		return result;
	}
}