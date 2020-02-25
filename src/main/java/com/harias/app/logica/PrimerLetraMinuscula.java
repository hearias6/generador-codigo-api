package com.harias.app.logica;

public class PrimerLetraMinuscula {

	public static String transformar(String palabra) {
		GenerarNuevoNombre generarNuevoNombre = new GenerarNuevoNombre();
		generarNuevoNombre.setPalabra(palabra);
		generarNuevoNombre.setNamelCase(true);
		generarNuevoNombre.convertir();
		return generarNuevoNombre.getNuevaPalabra();
	}
	
}
