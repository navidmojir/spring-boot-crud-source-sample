package org.cbi.hit.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Xxx {

	@Id
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
