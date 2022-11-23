package br.com.so.ServiceOrder.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import br.com.so.ServiceOrder.domain.Person;

public class ClientDTO {
	
	private Long id;

	@NotEmpty(message = "O campo nome deve estar preenchido")
	private String name;
	@CPF
	@NotEmpty(message = "O campo cpf deve estar preenchido")
	private String cpf;
	@NotEmpty(message = "O campo telefone deve estar preenchido")
	private String phoneNumber;
	
	public ClientDTO() {
	}

	public ClientDTO(Person obj) {
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
