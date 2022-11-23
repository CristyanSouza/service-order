package br.com.so.ServiceOrder.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.so.ServiceOrder.domain.Technician;
import br.com.so.ServiceOrder.dtos.TechnicianDTO;
import br.com.so.ServiceOrder.services.TechnicianService;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

	@Autowired
	private TechnicianService techService;
	
	@GetMapping
	public ResponseEntity<List<TechnicianDTO>> findAll(){
		List<TechnicianDTO> listDTO = techService.findAll().stream().map(tec -> new TechnicianDTO(tec)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id) {
		TechnicianDTO objDTO = new TechnicianDTO(techService.findById(id));
		
		return ResponseEntity.ok().body(objDTO);
	}
	
	@PostMapping
	public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO techDTO){
		Technician technician = techService.create(techDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(technician.getId()).toUri();
		
		return ResponseEntity.created(uri).build();	
		}


	
	
	
}
