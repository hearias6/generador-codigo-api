package com.harias.app.model;

public class Tecnologia {

	private long id;
	private String descripcion;
	
	public Tecnologia() {
		super();
	}

	public Tecnologia(long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
