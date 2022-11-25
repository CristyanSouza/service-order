package br.com.so.ServiceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.so.ServiceOrder.model.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long>{
	
}
