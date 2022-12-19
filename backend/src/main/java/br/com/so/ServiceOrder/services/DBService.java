package br.com.so.ServiceOrder.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		List<Technician> technicians = new ArrayList<>();
		List<Client> clients = new ArrayList<>();
		List<ServiceOrder> serviceOrders = new ArrayList<>();


		technicians.add(new Technician("Iago Takeda", "888.362.060-77", "(63) 3813-1339"));
		technicians.add(new Technician("Eric Leite", "435.404.270-31", "(63) 3001-6866"));
		technicians.add(new Technician("Márcia Flores", "294.512.800-49", "(54) 2878-3075"));
		technicians.add(new Technician("Daniela Martins", "430.794.860-21", "(51) 3762-2444"));
		technicians.add(new Technician("Reinaldo Garcia", "618.402.860-08", "(83) 2817-6340"));
		technicians.add(new Technician("Vinícius Lobo", "726.132.050-16", "(19) 2995-1885"));
		
		technicians.forEach(tech -> {
			technicianRepository.save(tech);
		});
		
		clients.add(new Client("Sérgio Moura", "167.261.660-36", "(83) 2211-0649"));
		clients.add(new Client("Vanda Leite", "167.261.660-36", "(68) 2948-5012"));
		clients.add(new Client("Cícero Coelho", "952.938.200-60", "(79) 3387-3544"));
		clients.add(new Client("Aline Cerqueira", "482.945.030-45", "(28) 2319-9885"));
		clients.add(new Client("Breno Ribeiro", "047.179.910-60", "(81) 3882-8367"));
		clients.add(new Client("Gilson Maia", "379.181.980-18", "(85) 2375-5472"));
		clients.add(new Client("Kelly Amaral", "076.166.160-38", "(82) 2705-4877"));
		clients.add(new Client("Júnior Vasconcelos", "598.122.100-32", "(68) 2057-1507"));
		clients.add(new Client("Gabriela Arruda", "622.888.810-20", "(79) 2552-3897"));
		clients.add(new Client("Isaías Corrêa", "652.477.540-69", "(95) 3215-4554"));
		clients.add(new Client("Cássio Vaz", "528.865.050-05", "(49) 2875-5349"));
		clients.add(new Client("Miguel Monteiro", "851.169.630-04", "(65) 2246-3536"));
		clients.add(new Client("Lúcio Assis", "715.569.690-07", "(95) 2667-7854"));
		clients.add(new Client("Túlio Esteves", "642.306.400-80", "(82) 2268-3528"));
		clients.add(new Client("Ágata Quintana", "950.540.470-00", "(67) 3153-8374"));
		clients.add(new Client("Paula Sanches", "798.588.920-55", "(65) 3241-3925"));
		clients.add(new Client("Jorge Prado", "480.978.320-03", "(67) 2033-6151"));
		
		clients.forEach(client -> {
			clientRepository.save(client);
		});
		
		serviceOrders.add(new ServiceOrder(Priority.HIGH, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(0), clients.get(1)));
		serviceOrders.add(new ServiceOrder(Priority.MEDIUM, "Ordem de serviço", Status.OPENED, technicians.get(1), clients.get(2)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.CLOSED, technicians.get(2), clients.get(3)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.OPENED, technicians.get(2), clients.get(4)));
		serviceOrders.add(new ServiceOrder(Priority.HIGH, "Ordem de serviço", Status.OPENED, technicians.get(1), clients.get(5)));
		serviceOrders.add(new ServiceOrder(Priority.MEDIUM, "Ordem de serviço", Status.CLOSED, technicians.get(3), clients.get(6)));
		serviceOrders.add(new ServiceOrder(Priority.MEDIUM, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(4), clients.get(7)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(5), clients.get(8)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.OPENED, technicians.get(2), clients.get(9)));
		serviceOrders.add(new ServiceOrder(Priority.HIGH, "Ordem de serviço", Status.OPENED, technicians.get(0), clients.get(10)));
		serviceOrders.add(new ServiceOrder(Priority.HIGH, "Ordem de serviço", Status.OPENED, technicians.get(2), clients.get(11)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.CLOSED, technicians.get(1), clients.get(12)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(4), clients.get(13)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(2), clients.get(14)));
		serviceOrders.add(new ServiceOrder(Priority.MEDIUM, "Ordem de serviço", Status.OPENED, technicians.get(5), clients.get(15)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.OPENED, technicians.get(4), clients.get(16)));
		serviceOrders.add(new ServiceOrder(Priority.MEDIUM, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(2), clients.get(1)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(1), clients.get(2)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.CLOSED, technicians.get(2), clients.get(3)));
		serviceOrders.add(new ServiceOrder(Priority.HIGH, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(3), clients.get(4)));
		serviceOrders.add(new ServiceOrder(Priority.LOW, "Ordem de serviço", Status.IN_PROGRESS, technicians.get(1), clients.get(5)));

		
		serviceOrders.forEach(so -> {
			so.setOpeningDate(LocalDateTime.of(2001, 03, 28, 10, 10));

		});
		
		
		technicians.forEach(tech -> {
			serviceOrders.forEach(so -> {
				if (so.getTechnician().getId().equals(tech.getId())) {
					tech.getListSO().add(so);

				}
			});
		});
		
		
		clients.forEach(client -> {
			serviceOrders.forEach(so -> {
				if (so.getClient().getId().equals(client.getId())) {
					client.getListSO().add(so);

				}
			});
		});
		
		
		technicians.forEach(tech -> {
			technicianRepository.save(tech);
		});
		
		clients.forEach(client -> {
			clientRepository.save(client);
		});
		
		serviceOrders.forEach(so -> {
			serviceOrderRepository.save(so);
		});
		
	}
}
