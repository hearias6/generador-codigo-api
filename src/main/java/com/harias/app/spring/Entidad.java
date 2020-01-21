package com.harias.app.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.model.Tabla;

public class Entidad {
	
	private Logger logger = LoggerFactory.getLogger(Entidad.class);

	private Tabla tabla;
	private StringBuilder entidad;
	
	Entidad(){		
		this.entidad = new StringBuilder();
	}
	
	void generarEntidad(){
		
		logger.info("Generar Entidad..");
		
		this.importarLibrerias();
		this.mapearEntidad();
		this.abrirCorchetes();
		this.crearLosSetter();
		this.crearLosGetter();
		this.cerrarCorchetes();
	}
	
	
	void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	String getEntidad() {
		logger.info("getEntidad ");
		logger.info(entidad.toString());
		return this.entidad.toString();
	}
	
	private void importarLibrerias() {
		entidad.append("import java.com.entidad; ");
	}
	
	private void mapearEntidad() {
		logger.info("mapear entidad ..");
		this.entidad.append("@entity");
		this.entidad.append("@table(value='"+ tabla.getDescripcion() +"')");
		this.entidad.append("class " + tabla.getDescripcion() + "()");
	}
	
	private void abrirCorchetes() {
		this.entidad.append("{");
	};
	
	private void cerrarCorchetes() {
		this.entidad.append("}");
	}
	
	private void crearLosSetter() {
		this.tabla.getColumnas().forEach(columna->{
			this.entidad.append("@Column");
			this.entidad.append("private " + columna.getDescripcion() + ";");
		});
	}
	
	private void crearLosGetter() {
		this.tabla.getColumnas().forEach(columna->{
			this.entidad.append("@Column");
			this.entidad.append("private " + columna.getDescripcion() + ";");
		});
	}
	
}
