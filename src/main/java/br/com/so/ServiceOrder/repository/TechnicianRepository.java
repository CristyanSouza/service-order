package br.com.so.ServiceOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.so.ServiceOrder.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long>{
	
	Optional<Technician> findByCpf(String cpf);
}
