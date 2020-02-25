package com.harias.app.logica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PalabraPlural {

	private Logger logger = LoggerFactory.getLogger(PalabraPlural.class);
	
	public static final String CARACTER_S = "s";
	
	public PalabraPlural() {
	}

	public String convertirAPluaral(String palabra) {

		logger.info("convertir a plural");
		
		return palabra + CARACTER_S;

	}
	
}
