package br.com.so.ServiceOrder.services;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.so.ServiceOrder.dtos.ServiceOrderDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.model.Technician;
import br.com.so.ServiceOrder.repository.ServiceOrderRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceOrderServiceTest {

	@Mock
	private ServiceOrderRepository serviceOrderRepository;
	@Mock
	private TechnicianService techService;
	@Mock
	private ClientService clientService;

	@InjectMocks
	private ServiceOrderService service;
	
	//Models
	private ServiceOrder serviceOrder;
	private ServiceOrderDTO soDTO;
	private Technician technician = new Technician();
	private Client client = new Client();

	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		client.setId(1l);
		technician.setId(2l);
		serviceOrder = new ServiceOrder();
		serviceOrder.setId(3l);
		serviceOrder.setComments("test");
		serviceOrder.setTechnician(technician);
		serviceOrder.setClient(client);
		soDTO = new ServiceOrderDTO(serviceOrder);
	}
	
	@Test
	void ShouldReturnAListOfServiceOrders(){
		Mockito.when(serviceOrderRepository.findAll()).thenReturn(List.of(serviceOrder));
		
		List<ServiceOrder> serviceOrders = service.findAll();
		List<ServiceOrder> serviceOrdersToCompare = new ArrayList<ServiceOrder>();
		
		serviceOrdersToCompare.add(serviceOrder);
		
		
		Assertions.assertNotNull(serviceOrders);
		Assertions.assertEquals(serviceOrdersToCompare, serviceOrders);
		Assertions.assertEquals(1, serviceOrders.size());
		Assertions.assertEquals(ServiceOrder.class, serviceOrders.get(0).getClass());
	}
	
	@Test
	void ShouldReturnTheObjectWhenTheServiceOrderWithIdExistsInDataBase() {
		Mockito.when(serviceOrderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(serviceOrder));
		
		ServiceOrder so = service.findById(Mockito.anyLong());
		
		Assertions.assertNotNull(so);
		Assertions.assertEquals(ServiceOrder.class, so.getClass());
		
	}
	
	@Test
	void ShouldThrowExceptionIfTheObjectWithIdDoNotExistsInDataBase() {
		Mockito.when(serviceOrderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ObjectNotFoundException ex = Assertions.assertThrows(ObjectNotFoundException.class, () -> service.findById(Mockito.anyLong()));
		Assertions.assertEquals(ex.getMessage(), "Ordem de serviço não encontrada");
	}
	
	
	
	@Test
	void ShouldCreateAServiceOrderAndReturnAServiceOrderDTO() {
		Mockito.when(serviceOrderRepository.save(Mockito.any(ServiceOrder.class))).thenReturn(serviceOrder);
		Mockito.when(techService.findById(Mockito.anyLong())).thenReturn(technician);
		Mockito.when(clientService.findById(Mockito.anyLong())).thenReturn(client);



		
		ServiceOrderDTO soDtoReturn = service.create(soDTO);
		
		Assertions.assertNotNull(soDtoReturn);
		Assertions.assertEquals(ServiceOrderDTO.class, soDtoReturn.getClass());
		Assertions.assertEquals(soDTO.getClient(), soDtoReturn.getClient());
		Assertions.assertEquals(soDTO.getTechnician(), soDtoReturn.getTechnician());
		Assertions.assertEquals(soDTO.getComments(), soDtoReturn.getComments());
		Assertions.assertEquals(soDTO.getStatus(), soDtoReturn.getStatus());
		Assertions.assertEquals(soDTO.getPriority(), soDtoReturn.getPriority());
		verify(serviceOrderRepository, Mockito.times(1)).save(Mockito.any(ServiceOrder.class));
	}
	
	@Test
	void ShouldUpdateTheServiceOrderOnDataBase() {
		Mockito.when(techService.findById(Mockito.anyLong())).thenReturn(technician);
		Mockito.when(clientService.findById(Mockito.anyLong())).thenReturn(client);
		service.update(soDTO);
		verify(serviceOrderRepository, Mockito.times(1)).save(Mockito.any(ServiceOrder.class));
	}

}
