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

import br.com.so.ServiceOrder.dtos.TechnicianDTO;
import br.com.so.ServiceOrder.model.Technician;
import br.com.so.ServiceOrder.model.Person;
import br.com.so.ServiceOrder.model.ServiceOrder;
import br.com.so.ServiceOrder.repository.TechnicianRepository;
import br.com.so.ServiceOrder.repository.PersonRepository;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;


@SpringBootTest
@AutoConfigureMockMvc
class TechnicianServiceTest {



	@Mock
	private TechnicianRepository technicianRepository;
	
	@Mock
	private PersonRepository personRepository;
	

	@Mock
	private Technician technician;
	private Optional<Technician> technicianOptional;
	private Optional<Person> personOptional;
	private TechnicianDTO technicianDTO;
	
	
	@InjectMocks
	private TechnicianService technicianService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		technician = new Technician("Cristyan de Souza", "128.031.239-40", "48 9 98307254");
		technicianOptional = Optional.of(new Technician("Cristyan de Souza", "374.499.550-08", "48 9 98307254"));
		personOptional = Optional.of(new Technician("Cristyan de Souza", "374.499.550-08", "48 9 98307254"));
		technicianDTO = new TechnicianDTO(technician);

	}


	@Test
	void ReturnExceptionWhenTechnicianIdNotFoundInDataBase() {
		Mockito.when(technicianRepository.findById(eq(1l))).thenReturn(Optional.empty());
		
		ObjectNotFoundException exception = Assertions.assertThrows(ObjectNotFoundException.class, () -> technicianService.findById(1l));
		Assertions.assertEquals("Técnico não encontrado", exception.getMessage());
					
	}
	
	
	@Test
	void ReturnTheObjectWhenTheTechnicianWithIdExistsInDataBase() {
		Mockito.when(technicianRepository.findById(eq(2l))).thenReturn(technicianOptional);
				
		Assertions.assertEquals(Technician.class, technicianService.findById(2l).getClass());
	}
	
	@Test
	void ShouldReturnAListOfTechnicians(){
		Mockito.when(technicianRepository.findAll()).thenReturn(List.of(technician));
		
		List<Technician> technicians = technicianService.findAll();
		List<Technician> techniciansToCompare = new ArrayList<Technician>();
		techniciansToCompare.add(technician);
		
		
		Assertions.assertNotNull(technicians);
		Assertions.assertEquals(techniciansToCompare, technicians);
		Assertions.assertEquals(1, technicians.size());
		Assertions.assertEquals(Technician.class, technicians.get(0).getClass());
	}

	
	@Test
	void ShouldCreateANewTechnicianOnDataBase() {
		Mockito.when(personRepository.findByCpf(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(technicianRepository.save(Mockito.any())).thenReturn(technician);
		
		Technician technician = technicianService.create(technicianDTO);
		Assertions.assertNotNull(technician);
		Assertions.assertEquals(technicianDTO.getId(), technician.getId());
		Assertions.assertEquals(technicianDTO.getName(), technician.getName());
		Assertions.assertEquals(technicianDTO.getPhoneNumber(), technician.getPhoneNumber());
	}
	
	@Test
	void ShouldThrowExcpetionWhenCpfAlreadyExistsOnDataBase() {
		Mockito.when(personRepository.findByCpf(Mockito.anyString())).thenReturn(personOptional);		
		
		DataIntegrityViolationException ex = Assertions.assertThrows(DataIntegrityViolationException.class, 
				() -> technicianService.create(technicianDTO));
		Assertions.assertEquals(ex.getMessage(), "CPF já cadastrado no sistema");
	}
	
	
	@Test
	void ShouldUpdateTheTechnicianOnDataBase() {
		Mockito.when(technicianRepository.findById(Mockito.anyLong())).thenReturn(technicianOptional);
		technicianService.update(Mockito.anyLong(), technicianDTO);
		verify(technicianRepository, Mockito.times(1)).save(technician);
	}
	
	
	@Test
	void ShouldDeleteTheTechnicianOfDataBase() {
		Mockito.when(technicianRepository.findById(Mockito.anyLong())).thenReturn(technicianOptional);
		doNothing().when(technicianRepository).deleteById(Mockito.anyLong());
		technicianService.delete(Mockito.anyLong());
		verify(technicianRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
	
	
	
	@Test
	void ShouldThrowExceptionBecauseTheTechnicianHasServiceOrder() {
		Technician technicianTestListSoGreaterThanOne = new Technician("Test Service Order", "318.758.610-41", "48 9 98307254");
		technicianTestListSoGreaterThanOne.addSo(new ServiceOrder(null, null, null, technicianTestListSoGreaterThanOne,null));
		
		Mockito.when(technicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(technicianTestListSoGreaterThanOne));
	
		doNothing().when(technicianRepository).deleteById(Mockito.anyLong());
		
		
		DataIntegrityViolationException ex = Assertions.assertThrows(DataIntegrityViolationException.class, 
				() -> technicianService.delete(Mockito.anyLong()));
		
		Assertions.assertEquals(ex.getMessage(), "O funcionário possui ordens de serviços vinculadas");
		verify(technicianRepository, Mockito.times(0)).deleteById(Mockito.anyLong());
	}


}
