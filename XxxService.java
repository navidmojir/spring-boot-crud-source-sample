package org.cbi.hit.test;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import ir.mojir.spring_boot_commons.dtos.SearchDto;
import ir.mojir.spring_boot_commons.exceptions.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class XxxService {
	private static Logger logger = LoggerFactory.getLogger(XxxService.class);
	
	@Autowired
	private XxxRepo xxxRepo;
	
	@Autowired
	private XxxRepoCustom xxxRepoCustom;
	
	public Xxx create(Xxx xxx) {
		Xxx savedEntity = xxxRepo.save(xxx);
		logger.info("A new xxx with id {} was created successfully.", savedEntity.getId());
		return savedEntity;
	}

	public Xxx get(long id) {
		Xxx xxx = findById(id);
		logger.info("Xxx with id {} was retrieved successfully.", xxx.getId());
		return xxx;
	}
	
	private Xxx findById(long id) {
		Optional<Xxx> optXxx = xxxRepo.findById(id);
		if(optXxx.isEmpty())
			throw new EntityNotFoundException(id, null);
		return optXxx.get();
	}

	public Xxx update(long id, Xxx xxx) {
		findById(id); 
		xxx.setId(id);
		Xxx result = xxxRepo.save(xxx);
		logger.info("Xxx with id {} was updated successfully.", xxx.getId());
		return result;
	}

	public void remove(long id) {
		findById(id);
		xxxRepo.deleteById(id);
		logger.info("Xxx with id {} was deleted successfully.", id);
	}

	public Page<Xxx> search(@Valid SearchDto<XxxSearchFilter> req) {
		logger.info("Searching xxx");
		return xxxRepoCustom.search(req);
	}
}
