package br.com.so.ServiceOrder.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "technician")
public class Technician extends Person {
	
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
