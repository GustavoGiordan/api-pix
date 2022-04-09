package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//Cliente findByidCliente(long id);
	
	Cliente findByAgenciaAndConta(Long agencia, Long conta);

	List<Cliente> findByNomeCliente(String nome);
}
