package com.harias.app.model;

import java.util.List;

public class Tecnologia {

	private long id;
	private String descripcion;
	private List<Directorio> directorios;
	
	public Tecnologia() {
		super();
	}

	public Tecnologia(long id, String descripcion, List<Directorio> directorios) {
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

	public List<Directorio> getDirectorios() {
		return directorios;
	}

	public void setDirectorios(List<Directorio> directorios) {
		this.directorios = directorios;
	}

	@Override
	public String toString() {
		return "Tecnologia [id=" + id + ", descripcion=" + descripcion + ", directorios=" + directorios + "]";
	}
	
	
	
}
