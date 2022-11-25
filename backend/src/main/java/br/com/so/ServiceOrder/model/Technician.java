package br.com.so.ServiceOrder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "technician")
public class Technician extends Person {
	
	@JsonIgnore
	@OneToMany(mappedBy = "technician")
	private List<ServiceOrder> listSO = new ArrayList<>();
	
	public Technician() {
		super();
	}

	public Technician(String name, String cpf, String phoneNumber) {
		super(name, cpf, phoneNumber);
	}

	public List<ServiceOrder> getListSO() {
		return listSO;
	}

	
	
}
