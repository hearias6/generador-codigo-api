package com.harias.app.logica;

import java.util.List;

import com.harias.app.model.Columna;

public class PrimaryKey {
	
	private static Columna getPrimaryKey(List<Columna> columnas) {
		return columnas.stream().filter(columna->columna.isPrimaryKey()).findFirst().get();
	};

	public static String nombreTipoDato(List<Columna> columnas) {
		Columna columna = getPrimaryKey(columnas);
		String resultado = columna.getTipoDato().getDescripcion() + " "+PrimerLetraMinuscula.transformar(columna.getNombre());
		return resultado;
	}
	
	public static String nombre(List<Columna> columnas) {
		Columna columna = getPrimaryKey(columnas);
		String resultado = PrimerLetraMinuscula.transformar(columna.getNombre());
		return resultado;
	}
	
}
