package com.harias.app.spring;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.archivo.ArchivoImpl;
import com.harias.app.generador.GeneradorImplementor;
import com.harias.app.model.Proyecto;
import com.harias.app.model.Tecnologia;

public class GenerarSpringBoot2 implements GeneradorImplementor{

	private SpringBoot2 springBoot2 =new SpringBoot2();
	
	private ArchivoImpl archivoImpl = new ArchivoImpl();
	private String RUTA = "generador/backend/spring/";
	private Logger logger = LoggerFactory.getLogger(GenerarSpringBoot2.class);
	
	@Override
	public void generarCodigo(Proyecto proyecto) {
		
		List<Tecnologia> tecnologias = proyecto.getTecnologias()
		.stream()
		.filter(t->t.getId() == 1l).collect(Collectors.toList());
		
		springBoot2.setTablas(proyecto.getTablas());
		springBoot2.setDirectorios(tecnologias.get(0).getDirectorios());
		springBoot2.generarSpringBoot2();
		springBoot2.getArchivosFuentes().forEach(archivo->{
			try {
				archivoImpl.crearArchivo(RUTA + archivo.getNombreArchivo(), archivo.getCodigoFuente());
			} catch (IOException e) {
				logger.error("Error en generar Archivo.." + e.getMessage());
				e.printStackTrace();
			}
		});
		
	}

}
