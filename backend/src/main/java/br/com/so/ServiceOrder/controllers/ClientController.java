package br.com.so.ServiceOrder.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.so.ServiceOrder.dtos.ClientDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.services.ClientService;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private ClientService ClientService;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<ClientDTO> listDTO = ClientService.findAll().stream().map(tec -> new ClientDTO(tec)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO objDTO = new ClientDTO(ClientService.findById(id));
		
		return ResponseEntity.ok().body(objDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create( @RequestBody @Valid ClientDTO ClientDTO){
		Client client = ClientService.create(ClientDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).build();	
		}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody @Valid ClientDTO objDTO) {
		ClientService.update(id, objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.ok().location(uri).build();		
		}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		ClientService.delete(id);
		}


	
	
	
}
