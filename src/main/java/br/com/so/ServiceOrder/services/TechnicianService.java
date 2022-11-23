package br.com.so.ServiceOrder.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.domain.Person;
import br.com.so.ServiceOrder.domain.Technician;
import br.com.so.ServiceOrder.dtos.TechnicianDTO;
import br.com.so.ServiceOrder.repository.PersonRepository;
import br.com.so.ServiceOrder.repository.TechnicianRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository techRepository;
	@Autowired
	private PersonRepository personRepository;

	public Technician findById(Long id) {
		Optional<Technician> obj = techRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
	}

	public List<Technician> findAll() {
		return techRepository.findAll();
	}

	public Technician create(TechnicianDTO dto) {
		Optional<Person> validation = personRepository.findByCpf(dto.getCpf());

		if (validation.isPresent()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}

		try {
			Technician technician = new Technician(dto.getName(), dto.getCpf(), dto.getName());
			return techRepository.save(technician);
		} catch (ConstraintViolationException e) {
			throw new DataIntegrityViolationException("CPF inválido");

		}
	}
	
	
	public void update(Long id, TechnicianDTO dto) {
		Technician technician = techRepository.findById(id).get();
		technician.setCpf(dto.getCpf());
		technician.setName(dto.getName());
		technician.setPhoneNumber(dto.getPhoneNumber());
		techRepository.save(technician);
	
	}
	
	public void delete(Long id) {
		Technician technician = techRepository.findById(id).get();
		
		if(technician.getListSO().size() > 0) {
			throw new DataIntegrityViolationException("O funcionário possui ordens de serviços vinculadas");
		}
		techRepository.deleteById(id);
	
	}
}
