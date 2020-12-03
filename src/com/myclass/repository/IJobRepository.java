package com.myclass.repository;

import java.util.List;

import com.myclass.entity.Job;

public interface IJobRepository {

	List<Job> getAllJob();

	Job getJobById(int id);

	void removeJobById(int id);

	void addNewJob(Job job);

	void updateJobById(Job job, int id);

}
