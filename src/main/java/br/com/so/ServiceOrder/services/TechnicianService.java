package br.com.so.ServiceOrder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.domain.Technician;
import br.com.so.ServiceOrder.repository.TechnicianRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;


@Service
public class TechnicianService {
	
	@Autowired
	private TechnicianRepository techRepository;
	
	public Technician findById(Long id) {
		Optional<Technician> obj = techRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
