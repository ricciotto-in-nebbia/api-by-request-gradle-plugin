package com.devtool.api.by.request.dev.executor.impl;

import com.devtool.api.by.request.dev.exception.RequestApiGradlePluginException;
import com.devtool.api.by.request.dev.executor.ActionExecutor;
import com.devtool.api.by.request.dev.util.PathUtil;
import com.devtool.api.by.request.plugin.task.UnzipFileTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFileActionExecutor implements ActionExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnzipFileTask.class);

	private final File projectDir;

	public UnzipFileActionExecutor(final File projectDir) {
		this.projectDir = projectDir;
	}

	@Override
	public void execute() {

		final Path path = PathUtil.getPath(projectDir);
		final Path zipPath = PathUtil.getZipPath(projectDir);
		LOGGER.info(" - info: path is " + zipPath);

		try (FileInputStream fileInputStream = new FileInputStream(zipPath.toFile());
		     BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		     ZipInputStream zin = new ZipInputStream(bufferedInputStream)) {

			ZipEntry entry;
			String name;
			long size;

			while ((entry = zin.getNextEntry()) != null) {
				name = entry.getName();
				size = entry.getSize();
				System.out.printf("File name: %s \t File size: %d \n", name, size);

				FileOutputStream fileOutputStream = new FileOutputStream(path.toFile().getAbsolutePath() + "/" + name);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
				for (int i = zin.read(); i != -1; i = zin.read()) {
					bufferedOutputStream.write(i);
				}

				bufferedOutputStream.flush();
			}
		} catch (Exception e) {
			LOGGER.error(" - error: ", e);
			throw new RequestApiGradlePluginException(e);
		}
	}
}
