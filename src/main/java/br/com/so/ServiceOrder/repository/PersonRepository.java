package br.com.so.ServiceOrder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.so.ServiceOrder.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	Optional<Person> findByCpf(String cpf);

}
