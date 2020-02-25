package com.harias.libreria.services;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;		  
import com.harias.libreria.entities.Cliente;
import com.harias.libreria.daos.ClienteDao;

@Service
public class ClienteServiceImpl implements ClienteService{

	private Logger logger = LoggerFactory.getLogger(Cliente.class);

	@Autowired
	private ClienteDao clienteDao;

	public Cliente save(Cliente cliente){
		logger.info("save cliente"); 
		return clienteDao.save(cliente);
	}

	public Cliente update(Cliente cliente, String cedula){
		logger.info("update cliente"); 
		return clienteDao.save(cliente);
	}

	public  deleteById( String cedula){
		logger.info("delete cliente"); 
		return clienteDao.deleteById(cedula);
	}

	public List<Cliente> findAll(){
		logger.info("findAll cliente"); 
		return clienteDao.findAll();
	}

	public Cliente findById(String cedula){
		logger.info("find by id cliente"); 
		return cliente.findById(cedula);
	}


}
