package com.devtool.api.by.request.plugin.task;

import com.devtool.api.by.request.dev.executor.impl.RequestApiActionExecutor;
import groovy.lang.Tuple3;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

public class RequestApiTask extends AbstractRequestApiTask {

	private final File projectDir;

	private final String projectName;

	@Input
	private String uri;

	@Input
	private List<Tuple3<String, String, Boolean>> apiData;

	@Input
	private int requestTimeoutInSeconds;

	@Input
	private int connectTimeoutInSeconds;

	@Inject
	public RequestApiTask(final File projectDir, final String projectName) {
		this.projectDir = projectDir;
		this.projectName = projectName;
	}

	@TaskAction
	public void requestApi() {
		this.actionExecutor = new RequestApiActionExecutor(
			projectDir,
			projectName,
			uri,
			apiData,
			requestTimeoutInSeconds,
			connectTimeoutInSeconds
		);

		actionExecutor.execute();
	}

	public void setUri(final String uri) {
		this.uri = uri;
	}

	public void setApiData(List<Tuple3<String, String, Boolean>>  apiData) {
		this.apiData = apiData;
	}

	public void setRequestTimeoutInSeconds(final Integer requestTimeoutInSeconds) {
		this.requestTimeoutInSeconds = requestTimeoutInSeconds;
	}

	public void setConnectTimeoutInSeconds(final Integer connectTimeoutInSeconds) {
		this.connectTimeoutInSeconds = connectTimeoutInSeconds;
	}

	public String getUri() {
		return uri;
	}

	public List<Tuple3<String, String, Boolean>>  getApiData() {
		return apiData;
	}

	public int getRequestTimeoutInSeconds() {
		return requestTimeoutInSeconds;
	}

	public int getConnectTimeoutInSeconds() {
		return connectTimeoutInSeconds;
	}
}
