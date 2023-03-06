package com.devtool.api.by.request.dev.model;

import java.time.Instant;

public class ApiRequestProperty {

	private final String name;

	private final Instant actual;

	private final Boolean updateNeeded;

	ApiRequestProperty(String name, Instant actual, Boolean updateNeeded) {
		this.name = name;
		this.actual = actual;
		this.updateNeeded = updateNeeded;
	}

	public static ApiRequestPropertiesBuilder builder() {
		return new ApiRequestPropertiesBuilder();
	}

	public String getName() {
		return this.name;
	}

	public Instant getActual() {
		return this.actual;
	}

	public Boolean getUpdateNeeded() {
		return this.updateNeeded;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof ApiRequestProperty)) return false;
		final ApiRequestProperty other = (ApiRequestProperty) o;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
		final Object this$actual = this.getActual();
		final Object other$actual = other.getActual();
		if (this$actual == null ? other$actual != null : !this$actual.equals(other$actual)) return false;
		final Object this$updateNeeded = this.getUpdateNeeded();
		final Object other$updateNeeded = other.getUpdateNeeded();
		if (this$updateNeeded == null ? other$updateNeeded != null : !this$updateNeeded.equals(other$updateNeeded))
			return false;
		return true;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		final Object $actual = this.getActual();
		result = result * PRIME + ($actual == null ? 43 : $actual.hashCode());
		final Object $updateNeeded = this.getUpdateNeeded();
		result = result * PRIME + ($updateNeeded == null ? 43 : $updateNeeded.hashCode());
		return result;
	}

	public String toString() {
		return "ApiRequestProperties(name=" + this.getName() + ", actual=" + this.getActual() + ", updateNeeded=" + this.getUpdateNeeded() + ")";
	}

	public static class ApiRequestPropertiesBuilder {
		private String name;
		private Instant actual;
		private Boolean updateNeeded;

		ApiRequestPropertiesBuilder() {
		}

		public ApiRequestPropertiesBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ApiRequestPropertiesBuilder actual(Instant actual) {
			this.actual = actual;
			return this;
		}

		public ApiRequestPropertiesBuilder updateNeeded(Boolean updateNeeded) {
			this.updateNeeded = updateNeeded;
			return this;
		}

		public ApiRequestProperty build() {
			return new ApiRequestProperty(name, actual, updateNeeded);
		}

		public String toString() {
			return "ApiRequestProperties.ApiRequestPropertiesBuilder(name=" + this.name + ", actual=" + this.actual + ", updateNeeded=" + this.updateNeeded + ")";
		}
	}
}
