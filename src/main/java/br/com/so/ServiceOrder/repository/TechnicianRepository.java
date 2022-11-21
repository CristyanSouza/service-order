package br.com.so.ServiceOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.so.ServiceOrder.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long>{

}
