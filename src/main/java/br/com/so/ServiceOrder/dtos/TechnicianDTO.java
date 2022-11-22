package br.com.so.ServiceOrder.dtos;

import org.hibernate.validator.constraints.br.CPF;

import br.com.so.ServiceOrder.domain.Technician;

public class TechnicianDTO {
	private Long id;
	private String name;
	
	@CPF
	private String cpf;
	private String phoneNumber;
	
	public TechnicianDTO() {
	}

	public TechnicianDTO(Technician obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.phoneNumber = obj.getPhoneNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
	
}
