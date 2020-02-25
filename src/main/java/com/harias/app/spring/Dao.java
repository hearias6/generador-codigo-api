package com.harias.app.spring;

import java.util.List;

import com.harias.app.api.directorio.DirectorioCodigo;
import com.harias.app.api.directorio.DirectorioLogica;
import com.harias.app.logica.PrimerLetraMayuscula;
import com.harias.app.logica.Indexacion;
import com.harias.app.model.Directorio;
import com.harias.app.model.Tabla;

public class Dao {
	
	private Tabla tabla;
	private StringBuilder dao;
	
	private String nombreEntidad;
	private String nombreDao;
	
	private DirectorioLogica directorioLogica = new DirectorioLogica();
	
//	private PrimerLetraMayuscula generarNombreclase = new PrimerLetraMayuscula();
	
	private Indexacion indexacion = new Indexacion();


	Dao() {
		dao = new StringBuilder();
	}
	
	void generarDao() {
		
		try {
			this.generarNombreEntidad();
			this.generarNombreDao();
			this.importarLibrerias();
			this.mapearDao();
			this.abrirCorchetes();
			this.cerrarCorchetes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDirectorios(List<Directorio> directorios) {
		directorioLogica.setDirectorios(directorios);
	}
	
	private void generarNombreEntidad() {
//		this.nombreEntidad = this.generarNombreclase.getNombreClase(this.tabla.getDescripcion());
		this.nombreEntidad = PrimerLetraMayuscula.transformar(tabla.getDescripcion());
	}
	
	private void generarNombreDao() {
//		this.nombreDao = this.generarNombreclase.getNombreClase(this.tabla.getDescripcion() + "_DAO");
		this.nombreDao = PrimerLetraMayuscula.transformar(tabla.getDescripcion() + "_DAO");
	}
	
	void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	String getDao() {
		return this.dao.toString();
	}
	
	
	private void importarLibrerias() {

		Directorio directorio = directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_DAO);
		
		if(directorio != null) {
			dao.append("package ");
			dao.append(directorio.getUbicacion());
		    dao.append(";");
			dao.append(indexacion.agregarSaltoLinea(2));
		}
		
		dao.append("import org.springframework.data.jpa.repository.JpaRepository;");
		dao.append(indexacion.agregarSaltoLinea(1));
		dao.append("import org.springframework.stereotype.Repository;");
		dao.append(indexacion.agregarSaltoLinea(1));
		dao.append("import ");
		dao.append(directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_ENTITY).getUbicacion());
		dao.append(".");
		dao.append(nombreEntidad);
		dao.append(";");
		dao.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void mapearDao() {
		dao.append(indexacion.agregarSaltoLinea(2));
		dao.append("@Repository");
		dao.append(indexacion.agregarSaltoLinea(1));
		dao.append("public interface ");
		dao.append(nombreDao);
		dao.append(" extends JpaRepository<");
		dao.append(nombreEntidad);
		dao.append(", Long>");
	}
	
	private void abrirCorchetes() {		
		dao.append("{");
		dao.append(indexacion.agregarSaltoLinea(1));
	};
	
	private void cerrarCorchetes() {
		dao.append(indexacion.agregarSaltoLinea(1));
		dao.append("}");
	}
	
}
