package com.harias.app.logica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PalabraSingular {
	
	private Logger logger = LoggerFactory.getLogger(PalabraSingular.class);
	
	public static final String CARACTER_S = "s";
	
	public PalabraSingular() {
		
	}

	public String convertirASingular(String palabra) {

		logger.info("convertir a singular");
		
		if(palabra.substring(palabra.length() - 1).equalsIgnoreCase(CARACTER_S))
			palabra = palabra.substring(0, palabra.length() - 1);
		
		logger.info("palabra singular : " + palabra);
		
		return palabra;

	}

	
}
