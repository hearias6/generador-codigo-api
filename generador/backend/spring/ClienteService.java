package com.harias.libreria.services;

import java.util.List;
import com.harias.libreria.entities.Cliente;
public interface Cliente{


	public Cliente save(Cliente cliente);

	public Cliente update(Cliente cliente, String cedula);

	public  deleteById( String cedula);

	public List<Cliente> findAll()

	public Cliente findById(String cedula);

}
