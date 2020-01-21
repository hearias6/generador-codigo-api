package com.harias.app.model;

public class ArchivoFuente {

	private Long id;
	private String nombreArchivo;
	private String codigoFuente;
	
	public ArchivoFuente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArchivoFuente(Long id, String nombreArchivo, String codigoFuente) {
		super();
		this.id = id;
		this.nombreArchivo = nombreArchivo;
		this.codigoFuente = codigoFuente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getCodigoFuente() {
		return codigoFuente;
	}

	public void setCodigoFuente(String codigoFuente) {
		this.codigoFuente = codigoFuente;
	}
	
	
}
