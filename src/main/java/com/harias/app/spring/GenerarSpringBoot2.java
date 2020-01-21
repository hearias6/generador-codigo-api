package com.harias.app.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.archivo.ArchivoImpl;
import com.harias.app.generador.GeneradorImplementor;
import com.harias.app.model.ArchivoFuente;
import com.harias.app.model.Proyecto;

public class GenerarSpringBoot2 implements GeneradorImplementor{

	private SpringBoot2 springBoot2 =new SpringBoot2();
	
	private ArchivoImpl archivoImpl = new ArchivoImpl();
	private String RUTA = "generador/backend/spring/";
	private Logger logger = LoggerFactory.getLogger(GenerarSpringBoot2.class);
	
	@Override
	public void generarCodigo(Proyecto proyecto) {
		
		springBoot2.setTablas(proyecto.getTablas());
		springBoot2.generarSpringBoot2();
		springBoot2.getArchivosFuentes().forEach(archivo->{
			
			logger.info("Archivo Fuente " + archivo.getNombreArchivo());
			logger.info("codigo fuente " + archivo.getCodigoFuente());
			
			try {
				archivoImpl.crearArchivo(RUTA + archivo.getNombreArchivo(), archivo.getCodigoFuente());
			} catch (IOException e) {
				logger.error("Error en generar Archivo.." + e.getMessage());
				e.printStackTrace();
			}
		});
			
		
	}

}
