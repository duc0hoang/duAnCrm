package com.myclass.service;

import com.myclass.entity.Job;

public interface IJobService {

	Object getAllJob();

	Job getJobById(int id);

	void removeJobById(int id);

	void addNewJob(Job job);

	void updateJobById(Job job, int id);

}
