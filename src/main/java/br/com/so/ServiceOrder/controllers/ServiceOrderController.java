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

import br.com.so.ServiceOrder.dtos.ServiceOrderDTO;
import br.com.so.ServiceOrder.services.ServiceOrderService;

@RestController
@RequestMapping(value = "/serviceorder")
public class ServiceOrderController {

	@Autowired
	private ServiceOrderService soService;
	
	@GetMapping
	public ResponseEntity<List<ServiceOrderDTO>> findAll(){
		List<ServiceOrderDTO> listDTO = soService.findAll().stream().map(so -> new ServiceOrderDTO(so)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ServiceOrderDTO> findById(@PathVariable Long id){
		ServiceOrderDTO serviceDTO = new ServiceOrderDTO(soService.findById(id));
				
		return ResponseEntity.ok().body(serviceDTO);
	}
	
	
	@PostMapping()
	public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO so){
		
		ServiceOrderDTO soDto = soService.create(so);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(soDto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
		
}
