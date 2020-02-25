package com.harias.app.logica;

public class DescomponerPalabras {

	private String primerLetra;
	private String restoLetras;
	private String palabra;
	
	public DescomponerPalabras() {
		super();
	}
	
	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getPrimerLetra() {
		return primerLetra;
	}

	public String getRestoLetras() {
		return restoLetras;
	}

	public void descomponerEnLetras() {
		String palabras[]=palabra.split("\\s");  
	    for(String p : palabras){  
	        this.primerLetra =p.substring(0,1);
	        this.restoLetras =p.substring(1);  
	    }  
	}

}
