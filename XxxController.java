package org.cbi.hit.test;

import java.util.List;
import java.util.stream.Collectors;

import org.cbi.hit.permission_manager.helpers.PersianCharNormalizer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mojir.spring_boot_commons.dtos.SearchDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/xxxs")
public class XxxController {
	
	@Autowired
	private XxxService xxxService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping
	public XxxCreateResp create(@Valid @RequestBody XxxCreateReq req) {
		new PersianCharNormalizer().normalize(req);
		Xxx entity = mapper.map(req, Xxx.class);
		return mapper.map(xxxService.create(entity), XxxCreateResp.class);
	}
	
	@GetMapping("/{id}")
	public XxxGetResp get(@PathVariable long id) {
		return mapper.map(xxxService.get(id), XxxGetResp.class);
	}
	
	@PutMapping("/{id}")
	public XxxUpdateResp update(@PathVariable long id, @Valid @RequestBody XxxUpdateReq req) {
		new PersianCharNormalizer().normalize(req);
		return mapper.map(xxxService.update(id, mapper.map(req, Xxx.class)), XxxUpdateResp.class);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable long id) {
		xxxService.remove(id);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<XxxSearchResultRow>> search(@Valid @RequestBody SearchDto<XxxSearchFilter> req) {
		new PersianCharNormalizer().normalize(req);
		Page<Xxx> result = xxxService.search(req);
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(result.getTotalElements()))
				.body(result.getContent().stream().map((e)->mapper.map(e, XxxSearchResultRow.class))
						.collect(Collectors.toList()));
	}

}
