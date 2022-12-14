package br.com.so.ServiceOrder.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.so.ServiceOrder.domain.enums.Priority;
import br.com.so.ServiceOrder.domain.enums.Status;
import br.com.so.ServiceOrder.model.ServiceOrder;


public class ServiceOrderDTO {

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime openingDate;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime closingDate;
	private Integer priority;
	
	@NotEmpty(message = "O campo comentário é obrigatório")
	private String comments;
	private Integer status;
	private Long technician;
	private Long client;
	
	public ServiceOrderDTO(ServiceOrder serviceOrder) {
		this.id = serviceOrder.getId();
		this.openingDate = serviceOrder.getOpeningDate();
		this.closingDate = serviceOrder.getClosingDate();
		this.priority = serviceOrder.getPriority().getCod();
		this.comments = serviceOrder.getComments();
		this.status = serviceOrder.getStatus().getCod();
		this.technician = serviceOrder.getTechnician().getId();
		this.client = serviceOrder.getClient().getId();
	}
	
	public ServiceOrderDTO() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDateTime getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDateTime closingDate) {
		this.closingDate = closingDate;
	}

	public Priority getPriority() {
		return Priority.toEnum(priority);
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTechnician() {
		return technician;
	}

	public void setTechnician(Long technician) {
		this.technician = technician;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	
	

	
	
	
	
}
