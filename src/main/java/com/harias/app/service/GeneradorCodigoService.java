package com.harias.app.service;

import org.springframework.http.ResponseEntity;

import com.harias.app.model.Proyecto;

public interface GeneradorCodigoService {


	ResponseEntity<?> generarCodigo(Proyecto proyecto);
	
}
