package br.com.so.ServiceOrder.services;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.dao.DataIntegrityViolationException;

import br.com.so.ServiceOrder.dtos.ClientDTO;
import br.com.so.ServiceOrder.model.Client;
import br.com.so.ServiceOrder.model.Person;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.repository.ClientRepository;
import br.com.so.ServiceOrder.repository.PersonRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;


@SpringBootTest
@AutoConfigureMockMvc
class ClientServiceTest {
	

	@Mock
	private ClientRepository clientRepository;
	
	@Mock
	private PersonRepository personRepository;
	

	@Mock
	private Client client;
	private Optional<Client> clientOptional;
	private Optional<Person> personOptional;
	private ClientDTO clientDTO;
	
	
	@InjectMocks
	private ClientService clientService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		client = new Client("Cristyan de Souza", "374.499.550-08", "48 9 98307254");
		clientOptional = Optional.of(new Client("Cristyan de Souza", "374.499.550-08", "48 9 98307254"));
		personOptional = Optional.of(new Client("Cristyan de Souza", "374.499.550-08", "48 9 98307254"));
		clientDTO = new ClientDTO(client);

	}


	@Test
	void ReturnExceptionWhenClientIdNotFoundInDataBase() {
		Mockito.when(clientRepository.findById(eq(1l))).thenReturn(Optional.empty());
		
		ObjectNotFoundException exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> clientService.findById(1l));
		Assertions.assertEquals("Pessoa não encontrada", exception.getMessage());
					
	}
	
	
	@Test
	void ReturnTheObjectWhenTheClientWithIdExistsInDataBase() {
		Mockito.when(clientRepository.findById(eq(2l))).thenReturn(clientOptional);
				
		Assertions.assertEquals(Client.class, clientService.findById(2l).getClass());
	}
	
	@Test
	void ShouldReturnAListOfClients(){
		Mockito.when(clientRepository.findAll()).thenReturn(List.of(client));
		
		List<Client> clients = clientService.findAll();
		List<Client> clientsToCompare = new ArrayList<Client>();
		clientsToCompare.add(client);
		
		
		Assertions.assertNotNull(clients);
		Assertions.assertEquals(clientsToCompare, clients);
		Assertions.assertEquals(1, clients.size());
		Assertions.assertEquals(Client.class, clients.get(0).getClass());
	}

	
	@Test
	void ShouldCreateANewClientOnDataBase() {
		Mockito.when(personRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(clientRepository.save(Mockito.any())).thenReturn(client);
		
		Client client = clientService.create(clientDTO);
		Assertions.assertNotNull(client);
		Assertions.assertEquals(clientDTO.getId(), client.getId());
		Assertions.assertEquals(clientDTO.getName(), client.getName());
		Assertions.assertEquals(clientDTO.getPhoneNumber(), client.getPhoneNumber());
	}
	
	@Test
	void ShouldThrowExcpetionWhenCpfAlreadyExistsOnDataBase() {
		Mockito.when(personRepository.findByCpf(Mockito.anyString())).thenReturn(personOptional);		
		
		DataIntegrityViolationException ex = Assertions.assertThrows(DataIntegrityViolationException.class, 
				() -> clientService.create(clientDTO));
		Assertions.assertEquals(ex.getMessage(), "CPF já cadastrado no sistema");
	}
	
	
	@Test
	void ShouldUpdateTheClientOnDataBase() {
		Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(clientOptional);
		clientService.update(Mockito.anyLong(), clientDTO);
		verify(clientRepository, Mockito.times(1)).save(client);
	}
	
	
	@Test
	void ShouldDeleteTheClientOfDataBase() {
		Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(clientOptional);
		doNothing().when(clientRepository).deleteById(Mockito.anyLong());
		clientService.delete(Mockito.anyLong());
		verify(clientRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
	
	
	
	@Test
	void ShouldThrowExceptionBecauseTheClientHasServiceOrder() {
		Client clientTestListSoGreaterThanOne = new Client("Test Service Order", "318.758.610-41", "48 9 98307254");
		clientTestListSoGreaterThanOne.addSo(new ServiceOrder(null, null, null, null, clientTestListSoGreaterThanOne));
		
		Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(clientTestListSoGreaterThanOne));
	
		doNothing().when(clientRepository).deleteById(Mockito.anyLong());
		
		
		DataIntegrityViolationException ex = Assertions.assertThrows(DataIntegrityViolationException.class, 
				() -> clientService.delete(Mockito.anyLong()));
		
		Assertions.assertEquals(ex.getMessage(), "O cliente possui ordens de serviços vinculadas");
		verify(clientRepository, Mockito.times(0)).deleteById(Mockito.anyLong());
	}




}
