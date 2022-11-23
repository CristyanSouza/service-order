package br.com.so.ServiceOrder.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
		
}
