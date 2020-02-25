package com.harias.app.spring;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.api.directorio.DirectorioCodigo;
import com.harias.app.api.directorio.DirectorioLogica;
import com.harias.app.logica.GenerarLoggerSpring;
import com.harias.app.logica.PrimerLetraMayuscula;
import com.harias.app.logica.PrimerLetraMinuscula;
import com.harias.app.logica.Indexacion;
import com.harias.app.logica.InyectarDependencia;
import com.harias.app.logica.PrimaryKey;
import com.harias.app.model.Columna;
import com.harias.app.model.Directorio;
import com.harias.app.model.Tabla;

public class RestController {
	
	private Tabla tabla;
	private StringBuilder restController;
	private Indexacion indexacion = new Indexacion();

	private String nombreClase;
	private String nombreObjeto;
	
	private String SERVICE = "_SERVICE";
	
//	private String nombrePrimaryKey = "";
	
	private DirectorioLogica directorioLogica = new DirectorioLogica();
	
	private Logger logger = LoggerFactory.getLogger(RestController.class);
	
	private GenerarLoggerSpring logs = new GenerarLoggerSpring();
	private InyectarDependencia dependencia = new InyectarDependencia();
	
	private String nombreEntidad;
	
	RestController() {
		restController = new StringBuilder();
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
	
	
	void generarRestController() {
		
		try {
			generarNombreEntidad();
			importarLibrerias();
			crearrestController();
			abrirCorchetes();
			restController.append(indexacion.agregarSaltoLinea(1));
			declararVariables();
			crear();
			editar();
			eliminar();
			consultarTodos();
			consultarPorId();
			restController.append(indexacion.agregarSaltoLinea(1));
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
	
	private void importarLibrerias() {
		
		
		Directorio directorio = directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_CONTROLLER);
		
		if(directorio != null) {
			restController.append("package ");
			restController.append(directorio.getUbicacion());
			restController.append(";");
			restController.append(indexacion.agregarSaltoLinea(2));
		}
		
		restController.append(logs.importarLogger());
		restController.append("import java.util.List;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.beans.factory.annotation.Autowired;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.DeleteMapping;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.GetMapping;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.PathVariable;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.PostMapping;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.PutMapping;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.RequestBody;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.RequestMapping;");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("import org.springframework.web.bind.annotation.RestController; ");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(getImportDirectorio(DirectorioCodigo.DIRECTORIO_SPRING_ENTITY, nombreEntidad));
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(getImportDirectorio(DirectorioCodigo.DIRECTORIO_SPRING_DAO, nombreEntidad + "Dao"));
		restController.append(indexacion.agregarSaltoLinea(1));
		
	}
	
	private void declararVariables() {
		logs.setNombreClase(nombreClase);
		logs.getLogger();
		restController.append(dependencia.inyectarConAutowired(tabla.getDescripcion()+SERVICE));
	}
	
	private void crearrestController() {
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("@RequestMapping(\""+ tabla.getNombreApi() +"\") ");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append("public class ");
		restController.append(nombreClase);
		restController.append("Controller");
	}
	
	private void abrirCorchetes() {
		restController.append("{");
		restController.append(indexacion.agregarSaltoLinea(1));
	};

	private void cerrarCorchetes() {
		restController.append("}");
		restController.append(indexacion.agregarSaltoLinea(1));
	}
	
	private void crear() {
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("@PostMapping(\"/save\")");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("private ");
		restController.append(nombreClase);
		restController.append(" save(@RequestBody ");
		restController.append(nombreClase);
		restController.append(" ");
		restController.append(nombreObjeto);
		restController.append(")");
		abrirCorchetes();
		restController.append(indexacion.agregarTabulacion(2));
		restController.append("return ");
		restController.append(dependencia.useDependency(tabla.getDescripcion() + SERVICE));
		restController.append(".save(");
		restController.append(nombreObjeto);
		restController.append(");");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}
	
	private void editar() {

		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("@PutMapping(\"/update/{id}\")");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("private ");
		restController.append(nombreClase);
		restController.append(" update(@RequestBody ");
		restController.append(nombreClase);
		restController.append(" ");
		restController.append(nombreObjeto);
		restController.append(", @PathVariable(\"id\") ");
		//restController.append(getPrimaryKey(tabla.getColumnas()));
		restController.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		restController.append(")");
		abrirCorchetes();
		restController.append(indexacion.agregarTabulacion(2));
		restController.append("return ");
		restController.append(dependencia.useDependency(tabla.getDescripcion() + SERVICE));
		restController.append(".update(");
		restController.append(nombreObjeto);
		restController.append(", " + PrimaryKey.nombre(tabla.getColumnas()));
		restController.append(");");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();		
	}
	
	private void eliminar() {
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("@DeleteMapping(\"/delete/{id}\")");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("private void");
		restController.append(" delete(@PathVariable(\"id\") ");
		restController.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		restController.append(")");
		abrirCorchetes();
		restController.append(indexacion.agregarTabulacion(2));
		restController.append(dependencia.useDependency(tabla.getDescripcion() + SERVICE));
		restController.append(".deleteById(");
		restController.append(PrimaryKey.nombre(tabla.getColumnas()));
		restController.append(");");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();	
		
	}
	
	private void consultarTodos() {
		
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("@GetMapping(\"/\")");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("public List<");
		restController.append(nombreClase);
		restController.append("> findAll()");
		abrirCorchetes();
		restController.append(indexacion.agregarTabulacion(2));
		restController.append("return ");
		restController.append(dependencia.useDependency(tabla.getDescripcion() + SERVICE));
		restController.append(".findAll();");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
		
	}
	
	private void consultarPorId() {
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("@GetMapping(\"/{id}\")");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		restController.append("public ");
		restController.append(nombreClase);
		restController.append(" findById(");
		restController.append("@PathVariable(\"id\"))");
		abrirCorchetes();
		restController.append(indexacion.agregarTabulacion(2));
		restController.append("return ");
		restController.append(dependencia.useDependency(tabla.getDescripcion() + SERVICE));
		restController.append(".findById(");
		restController.append(PrimaryKey.nombre(tabla.getColumnas()));
		restController.append(");");
		restController.append(indexacion.agregarSaltoLinea(1));
		restController.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
		
	}

	public String getRestController() {
		return restController.toString();
	}

	public void setDirectorio(List<Directorio> directorios) {
		directorioLogica.setDirectorios(directorios);
	}
	
	private String getImportDirectorio(Long directorio, String entidad) {
		StringBuilder servicio = new StringBuilder();
		servicio.append("import ");
		servicio.append(directorioLogica.getDirectorioByCodigo(directorio).getUbicacion());
		servicio.append(".");
		servicio.append(entidad);
		servicio.append(";");
		servicio.append(indexacion.agregarSaltoLinea(1));
		return servicio.toString();
	}
	
}
