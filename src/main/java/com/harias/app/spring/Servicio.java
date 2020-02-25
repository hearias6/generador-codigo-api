package com.harias.app.spring;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.api.directorio.DirectorioCodigo;
import com.harias.app.api.directorio.DirectorioLogica;
import com.harias.app.logica.PrimerLetraMayuscula;
import com.harias.app.logica.PrimerLetraMinuscula;
import com.harias.app.logica.Indexacion;
import com.harias.app.logica.PrimaryKey;
import com.harias.app.model.Columna;
import com.harias.app.model.Directorio;
import com.harias.app.model.Tabla;

public class Servicio {
	
	private Tabla tabla;
	private StringBuilder servicio;
	private Indexacion indexacion = new Indexacion();
	
	private String nombreClase;
	private String nombreObjeto;
	
	private DirectorioLogica directorioLogica = new DirectorioLogica();
	
	private String nombreEntidad;
	
	private Logger logger = LoggerFactory.getLogger(Servicio.class);
	
	Servicio() {
		servicio = new StringBuilder();
	}
	
	void generarServicio() {
		
		try {
			generarNombreEntidad();
			importarLibrerias();
			crearServicio();
			abrirCorchetes();
			servicio.append(indexacion.agregarSaltoLinea(1));
			crear();
			editar();
			eliminar();
			consultarTodos();
			consultarPorId();
			servicio.append(indexacion.agregarSaltoLinea(1));
			cerrarCorchetes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Tabla getTabla() {
		return tabla;
	}

	public void setTabla(Tabla tabla) {		
		this.tabla = tabla;
		generarNombreClase();
		generarNombreObjeto();
	}
	
	
	private void generarNombreEntidad() {
		this.nombreEntidad = PrimerLetraMayuscula.transformar(tabla.getDescripcion());
	}
	
	private void generarNombreClase() {
		nombreClase = PrimerLetraMayuscula.transformar(tabla.getDescripcion());
	}
	
	private void generarNombreObjeto() {
		nombreObjeto = PrimerLetraMinuscula.transformar(tabla.getDescripcion());
	}
	
	private void importarLibrerias() {
		
		Directorio directorio = directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_SERVICE);
		
		if(directorio != null) {
			servicio.append("package ");
			servicio.append(directorio.getUbicacion());
			servicio.append(";");
			servicio.append(indexacion.agregarSaltoLinea(2));
		}
		
		servicio.append("import java.util.List;");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("import ");
		servicio.append(directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_ENTITY).getUbicacion());
		servicio.append(".");
		servicio.append(nombreEntidad);
		servicio.append(";");
	}
	
	private void crearServicio() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("public interface ");
		servicio.append(nombreClase);
	}
	
	private void abrirCorchetes() {
		servicio.append("{");
		servicio.append(indexacion.agregarSaltoLinea(1));
	};

	private void cerrarCorchetes() {
		servicio.append("}");
		servicio.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void crear() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public ");
		servicio.append(nombreClase);
		servicio.append(" save(");
		servicio.append(nombreClase);
		servicio.append(" ");
		servicio.append(nombreObjeto);
		servicio.append(");");
		servicio.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void editar() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public ");
		servicio.append(nombreClase);
		servicio.append(" update(");
		servicio.append(nombreClase);
		servicio.append(" ");
		servicio.append(nombreObjeto);
		servicio.append(", ");
		servicio.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		servicio.append(");");
		servicio.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void eliminar() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public ");
		servicio.append(" deleteById(");
		servicio.append(" ");
		servicio.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		servicio.append(");");
		servicio.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void consultarTodos() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public List<");
		servicio.append(nombreClase);
		servicio.append("> findAll()");
		servicio.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void consultarPorId() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public ");
		servicio.append(nombreClase);
		servicio.append(" findById(");
		servicio.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		servicio.append(");");
		servicio.append(indexacion.agregarSaltoLinea(1));
	}

	public String getServicio() {
		return servicio.toString();
	}


	public void setDirectorio(List<Directorio> directorios) {
		directorioLogica.setDirectorios(directorios);
	}
	
}
