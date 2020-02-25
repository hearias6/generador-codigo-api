package com.harias.app.logica;

public class PrimerLetraMayuscula {

	public static String transformar(String palabra) {
		GenerarNuevoNombre generarNuevoNombre = new GenerarNuevoNombre();
		generarNuevoNombre.setPalabra(palabra);
		generarNuevoNombre.convertir();
		return generarNuevoNombre.getNuevaPalabra();
	}
	
}
