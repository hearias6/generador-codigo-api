package com.harias.app.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.harias.app.model.Proyecto;
import com.harias.app.service.GeneradorCodigoService;

@Component
public class FacadeImpl implements Facade{

	@Autowired
	private GeneradorCodigoService generadorCodigoService;
	
	@Override
	public ResponseEntity<?> generarCodigo(Proyecto proyecto) {
		return generadorCodigoService.generarCodigo(proyecto);
	}
	
	


}
