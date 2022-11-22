package br.com.so.ServiceOrder.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "technician")
public class Technician extends Person {
	
	@JsonIgnore
	@OneToMany(mappedBy = "technician")
	private List<ServiceOrder> listSO = new ArrayList<>();
	
	public Technician() {
	}

	public Technician(String name, String cpf, String phoneNumber) {
		super(name, cpf, phoneNumber);
	}

	public List<ServiceOrder> getListSO() {
		return listSO;
	}

	
	
}
