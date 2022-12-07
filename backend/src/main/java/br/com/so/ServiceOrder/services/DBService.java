package br.com.so.ServiceOrder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.so.ServiceOrder.domain.enums.Priority;
import br.com.so.ServiceOrder.domain.enums.Status;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.model.Technician;
import br.com.so.ServiceOrder.repository.ClientRepository;
import br.com.so.ServiceOrder.repository.ServiceOrderRepository;
import br.com.so.ServiceOrder.repository.TechnicianRepository;

@Service
public class DBService {

	@Autowired
	private ServiceOrderRepository serviceOrderRepository ;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private TechnicianRepository technicianRepository;
	
	public void instanceDb() {
		Technician tech = new Technician("Cristyan de Souza", "128.031.239-40", "(48) 9 9830-7254");
		Technician tech2 = new Technician("Tania Regina", "026.721.799-48", "(48) 9 9830-7254");

		Client client= new Client("Tainna Oliveira", "138.693.180-23", "(48) 9 9830-7254");
		ServiceOrder so = new ServiceOrder(Priority.LOW, "Teste criação h2", Status.IN_PROGRESS, tech, client);		
		
		tech.getListSO().add(so);
		client.getListSO().add(so);
		
		technicianRepository.save(tech);
		technicianRepository.save(tech2);
		clientRepository.save(client);
		serviceOrderRepository.save(so);
	}
}
