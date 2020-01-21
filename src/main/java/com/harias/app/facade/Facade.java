package com.harias.app.facade;

import org.springframework.http.ResponseEntity;

import com.harias.app.model.Proyecto;

public interface Facade {

	ResponseEntity<?> generarCodigo(Proyecto proyecto);
	
}
