package com.harias.libreria.controllers;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.harias.libreria.entities.Cliente;

import com.harias.libreria.daos.ClienteDao;


@RequestMapping("cliente") 
public class ClienteController{


	@Autowired
	private ClienteService clienteService;

	@PostMapping("/save")
	private Cliente save(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
	}

	@PutMapping("/update/{id}")
	private Cliente update(@RequestBody Cliente cliente, @PathVariable("id") String cedula){
		return clienteService.update(cliente, cedula);
	}

	@DeleteMapping("/delete/{id}")
	private void delete(@PathVariable("id") String cedula){
		clienteService.deleteById(cedula);
	}

	@GetMapping("/")
	public List<Cliente> findAll(){
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public Cliente findById(@PathVariable("id")){
		return clienteService.findById(cedula);
	}

}
