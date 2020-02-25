package com.harias.libreria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CLIENTES")
class Cliente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String cedula;

	@Column(name="NOMBRE_COMPLETO")
	private String nombreCompleto;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;

	public Cliente(){
		super(); 
	}

	public Cliente(String cedula, String nombreCompleto, String primerApellido){
		this.cedula=cedula;
		this.nombreCompleto=nombreCompleto;
		this.primerApellido=primerApellido;

	}

	public String getCedula()	{
		return cedula;	
	}


	public  void setCedula( String cedula){
		this.cedula = cedula;	
	}

	public String getNombreCompleto()	{
		return nombreCompleto;	
	}


	public  void setNombreCompleto( String nombreCompleto){
		this.nombreCompleto = nombreCompleto;	
	}

	public String getPrimerApellido()	{
		return primerApellido;	
	}


	public  void setPrimerApellido( String primerApellido){
		this.primerApellido = primerApellido;	
	}

}