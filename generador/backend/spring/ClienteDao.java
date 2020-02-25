package com.harias.libreria.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.harias.libreria.entities.Cliente;


@Repository
public interface ClienteDao extends JpaRepository<Cliente, Long>{

}