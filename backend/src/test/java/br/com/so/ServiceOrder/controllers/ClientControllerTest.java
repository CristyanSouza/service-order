package br.com.so.ServiceOrder.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.services.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService clienteService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	

	@Test
	void ShouldCreateClient() throws Exception {
		Client client = new Client("Teste criação", "128.031.239-40", "(48) 9 98307254");
		String clientJson = mapper.writeValueAsString(client);
		
		
		mockMvc.perform(
				post("/client}")
				.contentType(MediaType.APPLICATION_JSON)
				.content(clientJson)
				)
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
		
	
	}

}
