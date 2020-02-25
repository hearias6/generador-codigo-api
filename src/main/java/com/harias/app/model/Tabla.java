package com.harias.app.model;

import java.util.ArrayList;
import java.util.List;

public class Tabla {

	private long id;
	private String descripcion;
	private List<Columna> columnas = new ArrayList<Columna>();
	private String nombreApi;
	
	public Tabla() {
		super();
	}

	public Tabla(long id, String descripcion, List<Columna> columnas, String nombreApi) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.columnas = columnas;
		this.nombreApi = nombreApi;
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

	public List<Columna> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<Columna> columnas) {
		this.columnas = columnas;
	}

	public String getNombreApi() {
		return nombreApi;
	}

	public void setNombreApi(String nombreApi) {
		this.nombreApi = nombreApi;
	}
	
	
	
}
