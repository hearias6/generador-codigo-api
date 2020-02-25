package com.harias.app.logica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerarNuevoNombre {
	
	private String GUION_BAJO = "_";
	private String palabra;
	private String nuevaPalabra;
	private  Logger logger = LoggerFactory.getLogger(GenerarNuevoNombre.class);
	
	private boolean namelCase = false;

	private DescomponerPalabras descomponerPalabras = new DescomponerPalabras();
	
	public GenerarNuevoNombre() {
		
	}
	
	private String quitarUnDeterminadoCaracter(String caracter, String palabra) {
		String resultado = palabra.replace(caracter, "");
		return resultado;
	}
	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getNuevaPalabra() {
		return nuevaPalabra;
	}

	public void setNuevaPalabra(String nuevaPalabra) {
		this.nuevaPalabra = nuevaPalabra;
	}

	private String[] dividirPalabra(String caracterParaDividir) {
		return this.palabra.split(caracterParaDividir);
	}
	
	private String primerLetraMayuscula(String palabra) {
		descomponerPalabras.setPalabra(palabra);
		descomponerPalabras.descomponerEnLetras();
		String resultado = descomponerPalabras.getPrimerLetra().toUpperCase() + descomponerPalabras.getRestoLetras().toLowerCase();
	    return resultado.trim();  
	}
	
	public String primerLetraMinuscula(String palabra) {
		descomponerPalabras.setPalabra(palabra);
		descomponerPalabras.descomponerEnLetras();
		String resultado = descomponerPalabras.getPrimerLetra().toLowerCase() + descomponerPalabras.getRestoLetras().toLowerCase();
	    return resultado.trim();  
	}

	public void convertir() {
		this.transformarPalabra(GUION_BAJO);
	}
	
	public void transformarPalabra(String caracter) {

		logger.info("init transformar Palabra");
		
		String[] arrayPalabras = this.dividirPalabra(caracter);
		String nueva = "";
		
		if(arrayPalabras.length > 0) {
			for(int index = 0; index < arrayPalabras.length; index++) {
				String palabra = arrayPalabras[index].toString(); 
				palabra = this.quitarUnDeterminadoCaracter(caracter, palabra);
				
				if (index == 0 && isNamelCase() == true) {
					palabra = this.primerLetraMinuscula(palabra);
				}else {
					palabra = this.primerLetraMayuscula(palabra);
				}
				
				
				nueva += palabra;
			}
			this.nuevaPalabra = nueva;
		}else {
			this.nuevaPalabra = this.primerLetraMayuscula(this.palabra);
			
		}
		
	}

	public boolean isNamelCase() {
		return namelCase;
	}

	public void setNamelCase(boolean namelCase) {
		this.namelCase = namelCase;
	}
	
	private void limpiar() {
		
	}
	
	
	
}
