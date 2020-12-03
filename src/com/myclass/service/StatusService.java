package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.entity.Status;
import com.myclass.repository.IStatusRepository;

@Component
public class StatusService implements IStatusService{
	IStatusRepository statusRepository;
	
	public StatusService(IStatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}

	@Override
	public List<Status> getAllStatus() {
		return statusRepository.getAllStatus();
	}

}
