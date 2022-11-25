package br.com.so.ServiceOrder.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.domain.Client;
import br.com.so.ServiceOrder.domain.ServiceOrder;
import br.com.so.ServiceOrder.domain.Technician;
import br.com.so.ServiceOrder.domain.enums.Priority;
import br.com.so.ServiceOrder.domain.enums.Status;
import br.com.so.ServiceOrder.dtos.ServiceOrderDTO;
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
		ServiceOrder soToSave = toSo(so);
		serviceOrderRepository.save(soToSave);
		
		return new ServiceOrderDTO(soToSave);
	}
	
	
		
	private ServiceOrder toSo(ServiceOrderDTO newObj){
		Technician technician = techService.findById(newObj.getTechnician());
		Client client = clientService.findById(newObj.getClient());
		
	
		return new ServiceOrder(Priority.toEnum(newObj.getPriority()), newObj.getComments(), Status.toEnum(newObj.getPriority()),technician,client);
	}


}
