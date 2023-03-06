package com.devtool.api.by.request.dev.executor.impl;

import com.devtool.api.by.request.dev.exception.RequestApiGradlePluginException;
import com.devtool.api.by.request.dev.executor.ActionExecutor;
import com.devtool.api.by.request.dev.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RemoveZipActionExecutor implements ActionExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveZipActionExecutor.class);

	private final File projectDir;

	public RemoveZipActionExecutor(final File projectDir) {
		this.projectDir = projectDir;
	}

	@Override
	public void execute() {
		final Path zipPath = PathUtil.getZipPath(projectDir);

		try {
			Files.delete(zipPath);
			LOGGER.info(" - info: zip file deleted");
		} catch (IOException e) {
			LOGGER.error(" - error: unable to delete zip file", e);
			throw new RequestApiGradlePluginException(e);
		}
	}
}
