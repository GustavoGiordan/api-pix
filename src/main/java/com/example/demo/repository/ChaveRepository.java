package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Chave;
import com.example.demo.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaveRepository extends JpaRepository<Chave, String>{
    
    List<Chave> findByIdCliente(Cliente Cliente);

    List<Chave> findByTipoChave(String chave);

    Chave findByValorChave(String valorChave);


}
