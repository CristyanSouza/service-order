package br.com.so.ServiceOrder.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "client")
public class Client extends Person{
	
	@OneToMany(mappedBy = "client")
	private List<ServiceOrder> listSO = new ArrayList<>();
	
	public Client() {
		super();
	}

	public Client(String name, String cpf, String phoneNumber) {
		super(name, cpf, phoneNumber);
	}

	public List<ServiceOrder> getListSO() {
		return listSO;
	}
	
	
	
}
