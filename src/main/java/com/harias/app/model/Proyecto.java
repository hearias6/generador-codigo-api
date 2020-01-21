package com.harias.app.model;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {

	private long id;
	private String descripcion;
	private List<Tabla> tablas = new ArrayList<Tabla>();
	private List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
	
	public Proyecto(long id, String descripcion, List<Tabla> tablas, List<Tecnologia> tecnologias) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tablas = tablas;
		this.tecnologias = tecnologias;
	}

	public Proyecto() {
		super();
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

	public List<Tabla> getTablas() {
		return tablas;
	}

	public void setTablas(List<Tabla> tablas) {
		this.tablas = tablas;
	}

	public List<Tecnologia> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<Tecnologia> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
	
	
	
}
