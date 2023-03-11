package com.devtool.api.by.request.plugin;

import com.devtool.api.by.request.plugin.task.PreparePathTask;
import com.devtool.api.by.request.plugin.task.RemoveZipTask;
import com.devtool.api.by.request.plugin.task.RequestApiTask;
import com.devtool.api.by.request.plugin.task.UnzipFileTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskContainer;

import java.io.File;

public class ApiOnDemandCradlePlugin implements Plugin<Project> {

	private static final String COLON = ":";
	private static final String TASK_0 = "preparePath";
	private static final String TASK_1 = "requestApi";
	private static final String TASK_2 = "unzipFile";
	private static final String TASK_3 = "removeZip";
	private static final String TASK_FINAL_COMPILE_JAVA = "compileJava";

	@Override
	public void apply(Project project) {
		registerTask(project);
		setTaskOrder(project);
	}

	private void registerTask(Project project) {
		File projectDir = project.getProjectDir();
		String name = project.getName();

		project.getTasks().register(TASK_0, PreparePathTask.class, projectDir);
		project.getTasks().register(TASK_1, RequestApiTask.class, projectDir, name);
		project.getTasks().register(TASK_2, UnzipFileTask.class, projectDir);
		project.getTasks().register(TASK_3, RemoveZipTask.class, projectDir);
	}

	private void setTaskOrder(Project project) {
		TaskContainer tasks = project.getTasks();

		Task prepareDirectory = tasks.getByPath(COLON + TASK_0);
		prepareDirectory.setGroup("api by request");

		Task requestApi = tasks.getByPath(COLON + TASK_1);
		requestApi.dependsOn(COLON + TASK_0);
		requestApi.setGroup("api by request");

		Task unzipTask = tasks.getByPath(COLON + TASK_2);
		unzipTask.dependsOn(COLON + TASK_1);
		unzipTask.setGroup("api by request");

		Task removeZipTask = tasks.getByPath(COLON + TASK_3);
		removeZipTask.dependsOn(COLON + TASK_2);
		removeZipTask.setGroup("api by request");

		Task compileJava = tasks.getByPath(COLON + TASK_FINAL_COMPILE_JAVA);
		compileJava.dependsOn(COLON + TASK_3);
	}
}
