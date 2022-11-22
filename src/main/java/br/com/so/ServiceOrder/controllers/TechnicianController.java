package br.com.so.ServiceOrder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.so.ServiceOrder.domain.Technician;
import br.com.so.ServiceOrder.dtos.TechnicianDTO;
import br.com.so.ServiceOrder.services.TechnicianService;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianController {

	@Autowired
	private TechnicianService techService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id) {
		TechnicianDTO objDTO = new TechnicianDTO(techService.findById(id));
		
		return ResponseEntity.ok().body(objDTO);
	}
	
	
}
