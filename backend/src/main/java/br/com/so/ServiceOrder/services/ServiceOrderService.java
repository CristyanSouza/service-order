package br.com.so.ServiceOrder.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.domain.enums.Status;
import br.com.so.ServiceOrder.dtos.ServiceOrderDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.model.Technician;
import br.com.so.ServiceOrder.repository.ServiceOrderRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;

@Service
public class ServiceOrderService {

	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	@Autowired
	private TechnicianService techService;
	@Autowired
	private ClientService clientService;
	

	
	
	public List<ServiceOrder> findAll() {
		List<ServiceOrder> list = serviceOrderRepository.findAll();

		return list;
	}

	public ServiceOrder findById(Long id) {
		
		Optional<ServiceOrder> so = serviceOrderRepository.findById(id);
				
		return so.orElseThrow(() -> new ObjectNotFoundException("Ordem de serviço não encontrada"));
	}

	public ServiceOrderDTO create(@Valid ServiceOrderDTO so) {
		ServiceOrder soToSave = this.toSo(so);
		serviceOrderRepository.save(soToSave);
		return new ServiceOrderDTO(soToSave);
	}
	
	
		

	public ServiceOrderDTO update(@Valid ServiceOrderDTO so) {
		ServiceOrder serviceOrder = this.toSo(so);
		
		if(serviceOrder.getStatus().equals(Status.CLOSED)) {
			serviceOrder.setClosingDate(LocalDateTime.now());
		}
		
		serviceOrderRepository.save(serviceOrder);
		
		return new ServiceOrderDTO(serviceOrder);
	}


	
	public ServiceOrder toSo(ServiceOrderDTO osDto){
		ServiceOrder newSo = new ServiceOrder();
		Technician technician = techService.findById(osDto.getTechnician());
		Client client = clientService.findById(osDto.getClient());
		
		newSo.setId(osDto.getId());
		newSo.setPriority(osDto.getPriority());
		newSo.setComments(osDto.getComments());
		newSo.setStatus(osDto.getStatus());
		newSo.setClient(client);
		newSo.setTechnician(technician);
		
		return newSo;
	}
	
}
