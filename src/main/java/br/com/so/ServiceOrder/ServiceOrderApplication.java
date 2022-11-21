package br.com.so.ServiceOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.so.ServiceOrder.domain.Client;
import br.com.so.ServiceOrder.domain.ServiceOrder;
import br.com.so.ServiceOrder.domain.Technician;
import br.com.so.ServiceOrder.domain.enums.Priority;
import br.com.so.ServiceOrder.domain.enums.Status;
import br.com.so.ServiceOrder.repository.ClientRepository;
import br.com.so.ServiceOrder.repository.ServiceOrderRepository;
import br.com.so.ServiceOrder.repository.TechnicianRepository;

@SpringBootApplication
public class ServiceOrderApplication implements CommandLineRunner{

	@Autowired
	private ServiceOrderRepository serviceOrderRepository ;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private TechnicianRepository technicianRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Technician tech = new Technician("Cristyan de Souza", "128.031.239-40", "(48) 9 9830-7254");
		Client client= new Client("Tainna Oliveira", "138.693.180-23", "(48) 9 9830-7254");
		ServiceOrder so = new ServiceOrder(Priority.LOW, "Teste criação h2", Status.IN_PROGRESS, tech, client);		
		
		tech.getListSO().add(so);
		client.getListSO().add(so);
		
		technicianRepository.save(tech);
		clientRepository.save(client);
		serviceOrderRepository.save(so);

	}

}
