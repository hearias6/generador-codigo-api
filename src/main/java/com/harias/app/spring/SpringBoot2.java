package com.harias.app.spring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.logica.ConvertidorCodigoFuente;
import com.harias.app.model.ArchivoFuente;
import com.harias.app.model.Tabla;

public class SpringBoot2 {
	
	private Logger logger = LoggerFactory.getLogger(SpringBoot2.class);
	
	private Entidad entidad = new Entidad();
	private Dao dao = new Dao();
	
	private ConvertidorCodigoFuente convertidorCodigoFuente = new ConvertidorCodigoFuente();
	
	private List<Tabla> tablas = new ArrayList<Tabla>();
	
	
	public SpringBoot2() {
		super();
		
	}
	
	public void setTablas(List<Tabla> tablas) {
		this.tablas = tablas;
	}
	
	public void generarSpringBoot2() {
		this.tablas.forEach(tabla->{
			this.generarEntidad(tabla);
			this.generarDao(tabla);
		});
	}
	
	private void generarEntidad(Tabla tabla) {
		
		logger.info("Generar Entidad");
		
		entidad.setTabla(tabla);
		entidad.generarEntidad();
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(entidad.getEntidad());
		convertidorCodigoFuente.convertirACodigoFuente();
	}
	
	private void generarDao(Tabla tabla) {
		
		logger.info("Generar Dao");
		
		dao.setTabla(tabla);
		dao.generarDao();		
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setSubfijo("_DAO");
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(dao.getDao());
		convertidorCodigoFuente.convertirACodigoFuente();
	}
	
	public ArrayList<ArchivoFuente> getArchivosFuentes(){
		return convertidorCodigoFuente.getArchivosFuentes();
	}
	
}
