package com.harias.app.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.logica.ConvertidorCodigoFuente;
import com.harias.app.logica.PalabraSingular;
import com.harias.app.model.ArchivoFuente;
import com.harias.app.model.Directorio;
import com.harias.app.model.Tabla;

public class SpringBoot2 {
	
	private Logger logger = LoggerFactory.getLogger(SpringBoot2.class);
	
	private Entidad entidad = new Entidad();
	private Dao dao = new Dao();
	private Servicio servicio = new Servicio();
	private ServicioImpl servicioImpl = new ServicioImpl();
	private RestController restController = new RestController();
	
	private ConvertidorCodigoFuente convertidorCodigoFuente = new ConvertidorCodigoFuente();
	
	private List<Tabla> tablas = new ArrayList<Tabla>();
	private List<Directorio> directorios = new ArrayList<Directorio>();
	
	private PalabraSingular singular = new PalabraSingular();
	
	public SpringBoot2() {
		super();
		
	}
	
	public void setTablas(List<Tabla> tablas) {
		this.tablas = tablas.stream()
				.map(t->new Tabla(t.getId(), singular.convertirASingular(t.getDescripcion()), t.getColumnas(), t.getNombreApi()))
				.collect(Collectors.toList());
	}
	
	public List<Directorio> getDirectorios() {
		return directorios;
	}

	public void setDirectorios(List<Directorio> directorios) {
		this.directorios = directorios;
	}

	public void generarSpringBoot2() {
		this.tablas.forEach(tabla->{
			this.generarEntidad(tabla);
			this.generarDao(tabla);
			this.generarServicio(tabla);
			this.generarServicioImpl(tabla);
			this.generarRestController(tabla);
		});
	}
	
	private void generarEntidad(Tabla tabla) {
		
		logger.info("Generar Entidad");
		
		entidad.setTabla(tabla);
		entidad.setDirectorio(directorios);
		entidad.generarEntidad();
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(entidad.getEntidad());
		convertidorCodigoFuente.convertirACodigoFuente();
	}
	
	private void generarDao(Tabla tabla) {
		
		logger.info("Generar Dao");
		
		dao.setTabla(tabla);
		dao.setDirectorios(directorios);
		dao.generarDao();		
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setSubfijo("_DAO");
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(dao.getDao());
		convertidorCodigoFuente.convertirACodigoFuente();
	}
	
	private void generarServicio(Tabla tabla) {
		logger.info("Generar Servicio");
		servicio.setTabla(tabla);
		servicio.setDirectorio(directorios);
		servicio.generarServicio();
		
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setSubfijo("_SERVICE");
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(servicio.getServicio());
		convertidorCodigoFuente.convertirACodigoFuente();
		
	}

	private void generarServicioImpl(Tabla tabla) {
		logger.info("Generar Servicio");
		servicioImpl.setTabla(tabla);
		servicioImpl.setDirectorio(directorios);
		servicioImpl.generarServicio();
		
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setSubfijo("_SERVICE_IMPL");
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(servicioImpl.getServicio());
		convertidorCodigoFuente.convertirACodigoFuente();
		
	}
	
	private void generarRestController(Tabla tabla) {
		logger.info("Generar Servicio");
		restController.setTabla(tabla);
		restController.setDirectorio(directorios);
		restController.generarRestController();
		
		convertidorCodigoFuente.setNombreTabla(tabla.getDescripcion());
		convertidorCodigoFuente.setSubfijo("_CONTROLLER");
		convertidorCodigoFuente.setExtension("java");
		convertidorCodigoFuente.setContenido(restController.getRestController());
		convertidorCodigoFuente.convertirACodigoFuente();
		
	}
	
	public ArrayList<ArchivoFuente> getArchivosFuentes(){
		return convertidorCodigoFuente.getArchivosFuentes();
	}
	
}
