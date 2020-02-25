package com.harias.app.logica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerarLoggerSpring {
	
	Logger logger = LoggerFactory.getLogger(GenerarLoggerSpring.class);
	Indexacion indexacion = new Indexacion();
	
	private String nombreClase;

	public GenerarLoggerSpring() {
		super();
	}

	public GenerarLoggerSpring(String nombreClase) {
		super();
		this.nombreClase = nombreClase;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	
	public String getLogger() {
		String logger = indexacion.agregarSaltoLinea(1);
		logger += indexacion.agregarTabulacion(1);
		logger += "private Logger logger = LoggerFactory.getLogger("+ nombreClase +".class);";
		logger += indexacion.agregarSaltoLinea(1);
		return logger; 
	}
	
	public String importarLogger() {
		StringBuilder resultado = new StringBuilder();
		resultado.append("import org.slf4j.Logger; ");
		resultado.append(indexacion.agregarSaltoLinea(1));
		resultado.append("import org.slf4j.LoggerFactory;");
		resultado.append(indexacion.agregarSaltoLinea(2));
		return resultado.toString();
	}
	
	public String logCrearExitosamente(String nombre) {
		return "logger.info(\"se creado con éxito la "+ nombre + "\"); ";
	}
	
	public String logEliminarExitosamente(String nombre) {
		return "logger.info(\"se eliminado con éxito la "+ nombre + "\"); ";
	}
	
	public String logErrorSistema(String detalleError) {
		return "logger.info(\"Error en el sistema, detalle del error : "+ detalleError + "\"); ";
	}
	
	public String logInformativo(String info) {
		return "logger.info(\""+ info + "\"); ";
	}

	
	
}
