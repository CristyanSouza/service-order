package br.com.so.ServiceOrder.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.so.ServiceOrder.domain.enums.Priority;
import br.com.so.ServiceOrder.domain.enums.Status;
import br.com.so.ServiceOrder.dtos.ServiceOrderDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.model.Technician;
import br.com.so.ServiceOrder.services.ServiceOrderService;

@SpringBootTest
@AutoConfigureMockMvc
class ServiceOrderControllerTest {
	
	@InjectMocks
	private ServiceOrderController controller;
	
	@Mock
	private ServiceOrderService soService;
	
	private ServiceOrder so;
	private ServiceOrderDTO dto;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		so = new ServiceOrder(Priority.HIGH, "Teste comentário", Status.IN_PROGRESS, new Technician(), new Client());
		dto = new ServiceOrderDTO(so);
	}
	
	
	@Test
	void ShouldReturnAListWithAllServiceOrders() {
		Mockito.when(soService.findAll()).thenReturn(List.of(so, so));

		ResponseEntity<List<ServiceOrderDTO>> response = controller.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ServiceOrderDTO.class, response.getBody().get(0).getClass());
		Assertions.assertEquals(ArrayList.class ,response.getBody().getClass());
		Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
		Assertions.assertEquals(2,response.getBody().size());
		Assertions.assertEquals(response.getBody().get(0).getPriority(), Priority.HIGH);
		Assertions.assertEquals(response.getBody().get(0).getStatus(), Status.IN_PROGRESS);
		Assertions.assertEquals(response.getBody().get(0).getComments(), "Teste comentário");

	}
	
	
	@Test
	void ShouldReturnAServiceOrderById() {
		Mockito.when(soService.findById(Mockito.anyLong())).thenReturn(so);

		ResponseEntity<ServiceOrderDTO> response = controller.findById(30l);
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ServiceOrderDTO.class, response.getBody().getClass());

		Assertions.assertEquals(response.getBody().getPriority(), Priority.HIGH);
		Assertions.assertEquals(response.getBody().getStatus(), Status.IN_PROGRESS);
		Assertions.assertEquals(response.getBody().getComments(), "Teste comentário");

	}
	
	@Test
	void ShouldReturnStatusCreatedWithLocationOnHeader() {
		Mockito.when(soService.create(Mockito.any(ServiceOrderDTO.class))).thenReturn(dto);
		
		ResponseEntity<ServiceOrderDTO> response = controller.create(dto);
		
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull( response.getHeaders().get("Location"));
	}
	
	@Test
	void ShouldUpdateTheServiceOrderAndReturnOkWithLocationOnHeader() {
		Mockito.when(soService.update(Mockito.any())).thenReturn(dto);
		
		ResponseEntity<ServiceOrderDTO> response = controller.update(dto);
		
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(soService, Mockito.times(1)).update(Mockito.any(ServiceOrderDTO.class));
	}
}
