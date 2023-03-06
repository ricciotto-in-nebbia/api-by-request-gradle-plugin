package com.devtool.api.by.request.dev.model;

import java.util.List;

public final class ApiRequest {

	private final String projectName;

	private final List<ApiRequestProperty> properties;

	ApiRequest(String projectName, List<ApiRequestProperty> properties) {
		this.projectName = projectName;
		this.properties = properties;
	}

	public static ApiRequestBuilder builder() {
		return new ApiRequestBuilder();
	}

	public String getProjectName() {
		return this.projectName;
	}

	public List<ApiRequestProperty> getProperties() {
		return this.properties;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof ApiRequest)) return false;
		final ApiRequest other = (ApiRequest) o;
		final Object this$projectName = this.getProjectName();
		final Object other$projectName = other.getProjectName();
		if (this$projectName == null ? other$projectName != null : !this$projectName.equals(other$projectName))
			return false;
		final Object this$properties = this.getProperties();
		final Object other$properties = other.getProperties();
		if (this$properties == null ? other$properties != null : !this$properties.equals(other$properties)) return false;
		return true;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $projectName = this.getProjectName();
		result = result * PRIME + ($projectName == null ? 43 : $projectName.hashCode());
		final Object $properties = this.getProperties();
		result = result * PRIME + ($properties == null ? 43 : $properties.hashCode());
		return result;
	}

	public String toString() {
		return "ApiRequest(projectName=" + this.getProjectName() + ", properties=" + this.getProperties() + ")";
	}

	public static class ApiRequestBuilder {
		private String projectName;
		private List<ApiRequestProperty> properties;

		ApiRequestBuilder() {
		}

		public ApiRequestBuilder projectName(String projectName) {
			this.projectName = projectName;
			return this;
		}

		public ApiRequestBuilder properties(List<ApiRequestProperty> properties) {
			this.properties = properties;
			return this;
		}

		public ApiRequest build() {
			return new ApiRequest(projectName, properties);
		}

		public String toString() {
			return "ApiRequest.ApiRequestBuilder(projectName=" + this.projectName + ", properties=" + this.properties + ")";
		}
	}
}
