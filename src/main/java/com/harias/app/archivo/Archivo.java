package com.harias.app.archivo;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Archivo {
	
	String leerArchivo(String ruta)  throws FileNotFoundException, IOException;
	void crearArchivo(String ruta, String contenido) throws IOException;

}
