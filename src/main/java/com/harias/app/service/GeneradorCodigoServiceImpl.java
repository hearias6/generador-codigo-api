package com.harias.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.harias.app.generador.Generador;
import com.harias.app.generador.GeneradorImpl;
import com.harias.app.model.Proyecto;
import com.harias.app.spring.GenerarSpringBoot2;

@Service
public class GeneradorCodigoServiceImpl implements GeneradorCodigoService{

	private Logger logger = LoggerFactory.getLogger(GeneradorCodigoServiceImpl.class);
	
	private String mensaje = "";
	
	private GeneradorImpl generador = new GeneradorImpl();
	
	@Override
	public ResponseEntity<?> generarCodigo(Proyecto proyecto) {

		logger.info("Service Generador Codigo");
		
		try {

			generador.setImplementador(new GenerarSpringBoot2());
			generador.generarCodigo(proyecto);
			
			this.mensaje = "se ha generado exitosamente";
			logger.info(this.mensaje);
			return new ResponseEntity<String>(this.mensaje, HttpStatus.OK);
		} catch (Exception e) {
			this.mensaje = "Hay el siguiente error en generar Codigo(), " + e.getMessage();
			logger.error(this.mensaje);
			e.printStackTrace();
			return new ResponseEntity<String>(this.mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
