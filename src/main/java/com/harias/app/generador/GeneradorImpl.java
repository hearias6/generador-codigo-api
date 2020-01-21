package com.harias.app.generador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.model.Proyecto;

public class GeneradorImpl implements Generador{

	public GeneradorImplementor implementador;
	private Logger logger = LoggerFactory.getLogger(GeneradorImpl.class);
	
	public void setImplementador(GeneradorImplementor implementador) {
		
		logger.info("Implementador");
		this.implementador = implementador;
	}
	
	@Override
	public void generarCodigo(Proyecto proyecto) {
		implementador.generarCodigo(proyecto);
	}

}
