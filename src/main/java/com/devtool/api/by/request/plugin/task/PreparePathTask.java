package com.devtool.api.by.request.plugin.task;

import com.devtool.api.by.request.dev.executor.impl.PreparePathActionExecutor;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public class PreparePathTask extends AbstractRequestApiTask {

	@Inject
	public PreparePathTask(final File projectDir) {
		this.actionExecutor = new PreparePathActionExecutor(projectDir);
	}

	@TaskAction
	public void doWork() {
		actionExecutor.execute();
	}
}
