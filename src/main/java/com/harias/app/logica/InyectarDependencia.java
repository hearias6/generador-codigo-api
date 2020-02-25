package com.harias.app.logica;

public class InyectarDependencia {
	
	private Indexacion indexacion = new Indexacion();

	public String importarLibreriaAutowired() {
		String resultado = "import org.springframework.beans.factory.annotation.Autowired;";
		resultado += indexacion.agregarSaltoLinea(2);
		return resultado;
	}
	
	public String inyectarConAutowired(String nombreTabla) {
		StringBuilder resultado = new StringBuilder();
		resultado.append(indexacion.agregarSaltoLinea(1));
		resultado.append(indexacion.agregarTabulacion(1));
		resultado.append("@Autowired");
		resultado.append(indexacion.agregarSaltoLinea(1));
		resultado.append(indexacion.agregarTabulacion(1));
		resultado.append("private ");
		resultado.append(PrimerLetraMayuscula.transformar(nombreTabla));
		resultado.append(" ");
		resultado.append(PrimerLetraMinuscula.transformar(nombreTabla));
		resultado.append(";");
		resultado.append(indexacion.agregarSaltoLinea(1));
		return resultado.toString();
	}
	
	public String useDependency(String nombreClase) {
		return PrimerLetraMinuscula.transformar(nombreClase);
	};

	
}
