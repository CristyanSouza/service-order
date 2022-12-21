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

import br.com.so.ServiceOrder.dtos.TechnicianDTO;
import br.com.so.ServiceOrder.model.Technician;
import br.com.so.ServiceOrder.services.TechnicianService;

@SpringBootTest
@AutoConfigureMockMvc
class TechnicianControllerTest {

	@InjectMocks
	private TechnicianController controller;

	@Mock
	private TechnicianService technicianService;

	// Models
	private Technician technician;
	private TechnicianDTO technicianDTO;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		technician = new Technician("Cristyan de Souza", "374.499.550-08", "48 9 98307254");
		technician.setId(1l);
		technicianDTO = new TechnicianDTO(technician);
	}

	@Test
	void ShouldReturnATechnicianById() {
		Mockito.when(technicianService.findById(Mockito.anyLong())).thenReturn(technician);

		ResponseEntity<TechnicianDTO> response = controller.findById(30l);
		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(TechnicianDTO.class, response.getBody().getClass());

		Assertions.assertEquals(response.getBody().getName(), "Cristyan de Souza");
		Assertions.assertEquals(response.getBody().getCpf(), "374.499.550-08");
		Assertions.assertEquals(response.getBody().getPhoneNumber(), "48 9 98307254");

	}

	@Test
	void ShouldReturnAListWithAllTechnicians() {
		Mockito.when(technicianService.findAll()).thenReturn(List.of(technician, technician));

		ResponseEntity<List<TechnicianDTO>> response = controller.findAll();

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(TechnicianDTO.class, response.getBody().get(0).getClass());
		Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(2, response.getBody().size());
		Assertions.assertEquals(response.getBody().get(0).getName(), "Cristyan de Souza");
		Assertions.assertEquals(response.getBody().get(0).getCpf(), "374.499.550-08");
		Assertions.assertEquals(response.getBody().get(0).getPhoneNumber(), "48 9 98307254");

	}

	@Test
	void ShouldReturnStatusCreatedWithLocationOnHeader() {
		Mockito.when(technicianService.create(Mockito.any(TechnicianDTO.class))).thenReturn(technician);

		ResponseEntity<TechnicianDTO> response = controller.create(technicianDTO);

		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getHeaders().get("Location"));
	}

	@Test
	void ShouldUpdateTheTechnicianAndReturnOkWithLocationOnHeader() {
		Mockito.doNothing().when(technicianService).update(eq(Mockito.anyLong()), technicianDTO);

		ResponseEntity<TechnicianDTO> response = controller.update(1l, technicianDTO);

		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getHeaders().get("Location"));
		Mockito.verify(technicianService, Mockito.times(1)).update(Mockito.anyLong(), Mockito.any(TechnicianDTO.class));
	}

	@Test
	void ShouldDeleteTheTechnicianAndReturnOkWithMessageOnBody() {
		Mockito.doNothing().when(technicianService).delete(Mockito.anyLong());

		ResponseEntity<String> response = controller.delete(1l);

		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("Tecnico excluido com sucesso", response.getBody());

		Mockito.verify(technicianService, Mockito.times(1)).delete(Mockito.anyLong());
	}

}
