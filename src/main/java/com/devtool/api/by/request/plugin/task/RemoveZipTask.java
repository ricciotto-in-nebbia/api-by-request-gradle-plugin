package com.devtool.api.by.request.plugin.task;

import com.devtool.api.by.request.dev.executor.impl.RemoveZipActionExecutor;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public class RemoveZipTask extends AbstractRequestApiTask {

	@Inject
	public RemoveZipTask(final File projectDir) {
		this.actionExecutor = new RemoveZipActionExecutor(projectDir);
	}

	@TaskAction
	public void cleaning() {
		actionExecutor.execute();
	}
}
