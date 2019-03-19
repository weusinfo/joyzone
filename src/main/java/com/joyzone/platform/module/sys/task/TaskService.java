package com.joyzone.platform.module.sys.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void execute() {

		logger.info("This job has begun...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("Error while executing sample job", e);
		} finally {
			logger.info("Sample job has finished...");
		}
	}

}
