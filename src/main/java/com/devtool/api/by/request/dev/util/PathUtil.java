package com.devtool.api.by.request.dev.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathUtil {

	private static final String PATH_SUFFIX = "/src/main/proto/";
	private static final String FILE_NAME = "proto.zip";

	public static Path getPath(File projectDir) {
		return Paths.get(projectDir.getAbsolutePath() + PATH_SUFFIX);
	}

	public static Path getZipPath(File projectDir) {
		return Paths.get(projectDir.getAbsolutePath() + PATH_SUFFIX + FILE_NAME);
	}

	private PathUtil() {
		throw new UnsupportedOperationException();
	}
}
