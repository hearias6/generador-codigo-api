package com.harias.app.archivo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrganizarNombreArchivo {
	
	private String ESPACIOS = " ";
	private String GUION_BAJO = "_";
	private String GUION_INTERMEDIO = "-";
	
	private String nombreArchivo;
	
	private  Logger logger = LoggerFactory.getLogger(OrganizarNombreArchivo.class);
	
	public OrganizarNombreArchivo() {
		
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	private String quitarUnDeterminadoCaracter(String caracter, String palabra) {
		return palabra.replace(caracter, "");
	}
	
	private String[] dividirNombreArchivo(String caracterParaDividir) {
		return this.nombreArchivo.split(caracterParaDividir);
	}
	
	private String primerLetraMayuscula(String palabra) {
		String words[]=palabra.split("\\s");  
	    String capitalizeWord="";  
	    for(String w:words){  
	        String first=w.substring(0,1).toUpperCase();  
	        String afterfirst=w.substring(1).toLowerCase();  
	        capitalizeWord+=first+afterfirst;  
	    }  
	    return capitalizeWord.trim();  
	}

	public void generarNombreArchivo() {
		this.transformarPalabra(GUION_BAJO);
	}
	
	public void transformarPalabra(String caracter) {

		logger.info("init transformar Palabra");
		
		String[] arrayPalabras = this.dividirNombreArchivo(caracter);
		String palabraNueva = "";
		
		if(arrayPalabras.length > 0) {
			
			for(int index = 0; index < arrayPalabras.length; index++) {
				String palabra = arrayPalabras[index].toString(); 
				palabra = this.quitarUnDeterminadoCaracter(caracter, palabra);
				palabra = this.primerLetraMayuscula(palabra);
				palabraNueva += palabra;
			}

			this.nombreArchivo = palabraNueva;
			
		}else {
			
			this.nombreArchivo = this.primerLetraMayuscula(this.nombreArchivo);
			
		}
		
	}
	
}
