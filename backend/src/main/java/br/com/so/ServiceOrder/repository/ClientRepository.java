package br.com.so.ServiceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.so.ServiceOrder.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
