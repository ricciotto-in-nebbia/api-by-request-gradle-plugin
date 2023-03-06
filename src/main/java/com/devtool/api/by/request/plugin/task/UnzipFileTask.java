package com.devtool.api.by.request.plugin.task;

import com.devtool.api.by.request.dev.executor.impl.UnzipFileActionExecutor;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public class UnzipFileTask extends AbstractRequestApiTask {

	@Inject
	public UnzipFileTask(final File projectDir) {
		this.actionExecutor = new UnzipFileActionExecutor(projectDir);
	}

	@TaskAction
	public void unzip() {
		actionExecutor.execute();
	}
}
