package com.devtool.api.by.request.plugin.task;

import com.devtool.api.by.request.dev.executor.ActionExecutor;
import org.gradle.api.DefaultTask;

public abstract class AbstractRequestApiTask extends DefaultTask {

	protected ActionExecutor actionExecutor;
}
