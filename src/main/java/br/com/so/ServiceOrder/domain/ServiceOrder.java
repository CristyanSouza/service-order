package br.com.so.ServiceOrder.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.so.ServiceOrder.domain.enums.Priority;
import br.com.so.ServiceOrder.domain.enums.Status;

public class ServiceOrder {

	private Long id;
	private LocalDateTime openingDate;
	private LocalDateTime closingDate;
	private Integer priority;
	private String comments;
	private Integer status;
	private Technician technician;
	private Client client;

	public ServiceOrder() {
		this.openingDate = LocalDateTime.now();
		this.setStatus(Status.OPENED);
		this.setPriority(Priority.LOW);
	}

	public ServiceOrder(Long id, Priority priority, String comments, Status status, Technician technician,
			Client client) {
		this.id = id;
		this.openingDate = LocalDateTime.now();
		this.priority = (priority == null) ? 0 : priority.getCod();
		this.comments = comments;
		this.status = (status == null) ? 0 : status.getCod();
		this.technician = technician;
		this.client = client;
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

	public void setPriority(Priority priority) {
		this.priority = priority.getCod();
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

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, closingDate, comments, id, openingDate, priority, status, technician);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		return Objects.equals(client, other.client) && Objects.equals(closingDate, other.closingDate)
				&& Objects.equals(comments, other.comments) && Objects.equals(id, other.id)
				&& Objects.equals(openingDate, other.openingDate) && Objects.equals(priority, other.priority)
				&& Objects.equals(status, other.status) && Objects.equals(technician, other.technician);
	}

}
