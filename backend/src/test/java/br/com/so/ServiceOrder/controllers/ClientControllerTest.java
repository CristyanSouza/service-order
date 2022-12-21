package br.com.so.ServiceOrder.controllers;

import static org.mockito.ArgumentMatchers.eq;

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

import br.com.so.ServiceOrder.dtos.ClientDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.services.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

	@InjectMocks
	private ClientController controller;

	@Mock
	private ClientService clientService;

	// Models
	private Client client;
	private ClientDTO clientDTO;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		client = new Client("Cristyan de Souza", "374.499.550-08", "48 9 98307254");
		client.setId(1l);
		clientDTO = new ClientDTO(client);
	}

	@Test
	void ShouldReturnAClientById() {
		Mockito.when(clientService.findById(Mockito.anyLong())).thenReturn(client);

		ResponseEntity<ClientDTO> response = controller.findById(30l);
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ClientDTO.class, response.getBody().getClass());

		Assertions.assertEquals(response.getBody().getName(), "Cristyan de Souza");
		Assertions.assertEquals(response.getBody().getCpf(), "374.499.550-08");
		Assertions.assertEquals(response.getBody().getPhoneNumber(), "48 9 98307254");

	}
	
	@Test
	void ShouldReturnAListWithAllClients() {
		Mockito.when(clientService.findAll()).thenReturn(List.of(client, client));

		ResponseEntity<List<ClientDTO>> response = controller.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(ClientDTO.class, response.getBody().get(0).getClass());
		Assertions.assertEquals(ArrayList.class ,response.getBody().getClass());
		Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
		Assertions.assertEquals(2,response.getBody().size());
		Assertions.assertEquals(response.getBody().get(0).getName(), "Cristyan de Souza");
		Assertions.assertEquals(response.getBody().get(0).getCpf(), "374.499.550-08");
		Assertions.assertEquals(response.getBody().get(0).getPhoneNumber(), "48 9 98307254");

	}
	
	@Test
	void ShouldReturnStatusCreatedWithLocationOnHeader() {
		Mockito.when(clientService.create(Mockito.any(ClientDTO.class))).thenReturn(client);

		ResponseEntity<ClientDTO> response = controller.create(clientDTO);
		
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull( response.getHeaders().get("Location"));
	}
	
	@Test
	void ShouldUpdateTheClientAndReturnOkWithLocationOnHeader() {
		Mockito.doNothing().when(clientService).update(eq(Mockito.anyLong()), clientDTO);
		
		ResponseEntity<ClientDTO> response = controller.update(1l, clientDTO);
		
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getHeaders().get("Location"));
		Mockito.verify(clientService, Mockito.times(1)).update(Mockito.anyLong(), Mockito.any(ClientDTO.class));
	}

	
	@Test
	void ShouldDeleteTheClientAndReturnOkWithMessageOnBody() {
		Mockito.doNothing().when(clientService).delete(Mockito.anyLong());
		
		ResponseEntity<String> response = controller.delete(1l);
		
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("Cliente excluido com sucesso", response.getBody());
		
		Mockito.verify(clientService, Mockito.times(1)).delete(Mockito.anyLong());	
}
	

}
