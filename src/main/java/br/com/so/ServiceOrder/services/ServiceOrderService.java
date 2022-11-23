package br.com.so.ServiceOrder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.domain.ServiceOrder;
import br.com.so.ServiceOrder.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {

	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	public List<ServiceOrder> findAll() {
		List<ServiceOrder> list = serviceOrderRepository.findAll();

			
		return list;
	}

}
