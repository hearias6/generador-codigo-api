package com.harias.app.archivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ArchivoImpl implements Archivo{

	private Logger logger = LoggerFactory.getLogger(ArchivoImpl.class);
	
	@Override
	public String leerArchivo(String ruta) throws FileNotFoundException, IOException{
		
		String resultado = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			resultado = br.readLine();
		}
		
		return resultado;
	}

	
	@Override
	public void crearArchivo(String ruta, String contenido) throws IOException {

		logger.info("Crear Archivo ");
		logger.info("Ruta " + ruta);
		logger.info("Contenido " + contenido);
		
		try {
			
			File archivo = new File(ruta);
	        BufferedWriter bw;
	        
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(contenido);
	        } else {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(contenido);
	        }
	        bw.close();
	        
	        logger.info("Exito en crearse..");
	        
		} catch (Exception e) {
			logger.error("Error en el methodo crearArchivo " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
