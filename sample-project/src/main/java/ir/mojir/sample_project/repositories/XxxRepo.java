package ir.mojir.sample_project.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import ir.mojir.sample_project.entities.Xxx;

public interface XxxRepo extends CrudRepository<Xxx, Long>,
	JpaSpecificationExecutor<Xxx>{

}
