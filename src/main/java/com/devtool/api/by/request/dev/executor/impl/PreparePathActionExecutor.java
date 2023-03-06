package com.devtool.api.by.request.dev.executor.impl;

import com.devtool.api.by.request.dev.exception.RequestApiGradlePluginException;
import com.devtool.api.by.request.dev.executor.ActionExecutor;
import com.devtool.api.by.request.dev.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class PreparePathActionExecutor implements ActionExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreparePathActionExecutor.class);

	private final File projectDir;

	public PreparePathActionExecutor(final File projectDir) {
		this.projectDir = projectDir;
	}

	@Override
	public void execute() {
		final Path path = PathUtil.getPath(projectDir);
		LOGGER.info(" - info: path is " + path);
		deleteFiles(path);
		createPath(path);
	}

	private void deleteFiles(final Path path) {
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {

			try (Stream<Path> walk = Files.walk(path)) {
				walk.sorted(Comparator.reverseOrder())
				    .map(Path::toFile)
				    .peek(fileName -> LOGGER.info(" - info: deleted " + fileName))
				    .forEach(File::delete);
			} catch (IOException e) {
				LOGGER.error(" - error: impossible to delete the previous version of the API", e);
				throw new RequestApiGradlePluginException(e);
			}

		} else {
			LOGGER.warn(" - warn: the previous version of the API was not detected");
		}
	}

	private void createPath(final Path path) {
		try {
			final Path directory = Files.createDirectories(path);
			LOGGER.info(" - info: created directory " + directory);
		} catch (IOException e) {
			LOGGER.error(" - error: unable to create a directory for the API", e);
			throw new RequestApiGradlePluginException(e);
		}
	}
}
