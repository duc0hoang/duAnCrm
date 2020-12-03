package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.entity.Job;
import com.myclass.repository.IJobRepository;

@Component
public class JobService implements IJobService{
	IJobRepository jobRepository;
	
	public JobService(IJobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	@Override
	public List<Job> getAllJob() {
		return jobRepository.getAllJob();
	}

	@Override
	public Job getJobById(int id) {
		return jobRepository.getJobById(id);
	}

	@Override
	public void removeJobById(int id) {
		jobRepository.removeJobById(id);
	}

	@Override
	public void addNewJob(Job job) {
		jobRepository.addNewJob(job);
	}

	@Override
	public void updateJobById(Job job, int id) {
		jobRepository.updateJobById(job,id);
	}
	
}
