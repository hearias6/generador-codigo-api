package com.harias.app.api.directorio;

import java.util.ArrayList;
import java.util.List;

import com.harias.app.model.Directorio;

public class DirectorioLogica {
	
	private List<Directorio> directorios = new ArrayList<Directorio>();

	public DirectorioLogica() {
		super();
	}
	
	public List<Directorio> getDirectorios() {
		return directorios;
	}

	public void setDirectorios(List<Directorio> directorios) {
		this.directorios = directorios;
	}
	
	public Directorio getDirectorioByCodigo(Long id) {
		return directorios.stream().filter(d->d.getId() == id).findFirst().orElse(null);
	}

}
