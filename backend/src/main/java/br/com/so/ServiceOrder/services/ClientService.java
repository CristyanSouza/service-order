package br.com.so.ServiceOrder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.dtos.ClientDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.model.Person;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.repository.ClientRepository;
import br.com.so.ServiceOrder.repository.PersonRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PersonRepository personRepository;


	public Client findById(Long id) {
		Optional<Client> obj = clientRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada"));
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Client create(ClientDTO dto) {
		Optional<Person> validation = personRepository.findByCpf(dto.getCpf());

		if (validation.isPresent()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}

			Client client = new Client(dto.getName(), dto.getCpf(), dto.getPhoneNumber());
			return clientRepository.save(client);

	}
	
	
	public void update(Long id, ClientDTO dto) {
		Client client = clientRepository.findById(id).get();
		client.setCpf(dto.getCpf());
		client.setName(dto.getName());
		client.setPhoneNumber(dto.getPhoneNumber());
		clientRepository.save(client);
	
	}
	
	public void delete(Long id) {
		Client client = clientRepository.findById(id).get();
		
		List<ServiceOrder> soList = client.getListSO();
		
		if(soList.size() > 0) {
			throw new DataIntegrityViolationException("O cliente possui ordens de serviços vinculadas");
		}
		clientRepository.deleteById(id);
	
	}
}
