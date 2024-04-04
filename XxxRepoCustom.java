package org.cbi.hit.test;

import java.util.ArrayList;
import java.util.List;

import org.cbi.hit.permission_manager.helpers.RepositoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import ir.mojir.spring_boot_commons.dtos.SearchDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;

@Component
public class XxxRepoCustom {

	@Autowired
	private XxxRepo xxxRepo;
	
	@SuppressWarnings("serial")
	public Page<Xxx> search(@Valid SearchDto<XxxSearchFilter> req) {
		Pageable pageable = RepositoryHelper.generatePageRequestWithSort(req.getPaging(), req.getSorting());
		return xxxRepo.findAll(new Specification<Xxx>() {

			@Override
			public Predicate toPredicate(Root<Xxx> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = makePredicates(req, criteriaBuilder, root);
				return criteriaBuilder.and(
						predicates.toArray(new Predicate[predicates.size()])
				);
			}

			
			
		}, pageable);
	}
	
	private List<Predicate> makePredicates(@Valid SearchDto<XxxSearchFilter> req,
			CriteriaBuilder cb, Root<Xxx> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		XxxSearchFilter filters = req.getFilters();
		
		if(filters != null) {
//			if(!Validations.isBlank(filters.getName()))
//				predicates.add(cb.like(root.get("name"), "%" + filters.getName().trim() + "%"));
		}
		
		return predicates;
	}

}
