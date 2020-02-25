package com.harias.app.logica;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.model.ArchivoFuente;

public class ConvertidorCodigoFuente {
	
	private Logger logger = LoggerFactory.getLogger(ConvertidorCodigoFuente.class);

	private String nombreTabla;
	private String extension;
	private String prefijo;
	private String subfijo;
	private String contenido;
	
	private ArrayList<ArchivoFuente> archivosFuentes;
	
	private GenerarNuevoNombre organizarNombreArchivo = new GenerarNuevoNombre();

	public ConvertidorCodigoFuente() {
		super();
		this.archivosFuentes = new ArrayList<ArchivoFuente>();
		this.nombreTabla = "";
		this.extension = "";
		this.contenido ="";
		this.prefijo = "";
		this.subfijo = "";
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getSubfijo() {
		return subfijo;
	}

	public void setSubfijo(String subfijo) {
		this.subfijo = subfijo;
	}

	public ArrayList<ArchivoFuente> getArchivosFuentes() {
		return archivosFuentes;
	}
	
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setArchivosFuentes(ArrayList<ArchivoFuente> archivosFuentes) {
		this.archivosFuentes = archivosFuentes;
	}
	
	private String crearNombreArchivo(){
		logger.info("crearNombreArchivo() ");
		String nombre = this.prefijo + this.nombreTabla + this.subfijo + "." + this.extension;
		
//		organizarNombreArchivo.setNombreArchivo(nombre);
//		organizarNombreArchivo.generarNombreArchivo();
//		nombre = organizarNombreArchivo.getNombreArchivo();
		
		organizarNombreArchivo.setPalabra(nombre);
		organizarNombreArchivo.convertir();
		nombre = organizarNombreArchivo.getNuevaPalabra();
		
		logger.info("archivo fuente : " + nombre);
		return nombre;
	};
	
	public void convertirACodigoFuente() {
		logger.info("convertirACodigoFuente");
		logger.info("agregar nuevo archivo fuente..");
		this.archivosFuentes.add(new ArchivoFuente(1l, this.crearNombreArchivo(), this.getContenido()));
	}
	
}
