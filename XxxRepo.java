package org.cbi.hit.test;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface XxxRepo extends CrudRepository<Xxx, Long>,
	JpaSpecificationExecutor<Xxx>{

}
