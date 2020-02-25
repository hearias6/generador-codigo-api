package com.harias.app.logica;

public class Indexacion {
	
	private String SALTO_LINEA = "\n";
	private String TABULACION = "\t";
	
	public String agregarSaltoLinea(int cantidad) {
		return this.repetirXCantidadVeces(cantidad, SALTO_LINEA);
	}
	
	public String agregarTabulacion(int cantidad) {
		return this.repetirXCantidadVeces(cantidad, TABULACION);
	};
	
	
	private String repetirXCantidadVeces(int cantidad, String TEXTO) {
		String resultado = "";
		
		for(int i = 0; i < cantidad; i++) {
			resultado += TEXTO;
		}
		return resultado;
	};

}
