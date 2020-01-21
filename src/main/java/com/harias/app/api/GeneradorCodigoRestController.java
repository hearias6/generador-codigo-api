package com.harias.app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harias.app.facade.Facade;
import com.harias.app.model.Proyecto;

@RestController
@RequestMapping("api/generador-codigo")
@CrossOrigin("*")
public class GeneradorCodigoRestController {

	@Autowired 
	private Facade logicaNegocio;
	
	@PostMapping(value="/")
	public ResponseEntity<?> crearCodigoFuente(@RequestBody Proyecto proyecto){
		return logicaNegocio.generarCodigo(proyecto);
	} 
	
}
