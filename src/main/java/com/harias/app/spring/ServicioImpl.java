package com.harias.app.spring;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.harias.app.api.directorio.DirectorioCodigo;
import com.harias.app.api.directorio.DirectorioLogica;
import com.harias.app.logica.GenerarLoggerSpring;
import com.harias.app.logica.PrimerLetraMayuscula;
import com.harias.app.logica.PrimerLetraMinuscula;
import com.harias.app.logica.GenerarNuevoNombre;
import com.harias.app.logica.Indexacion;
import com.harias.app.logica.InyectarDependencia;
import com.harias.app.logica.PrimaryKey;
import com.harias.app.model.Columna;
import com.harias.app.model.Directorio;
import com.harias.app.model.Tabla;

public class ServicioImpl {

	private Tabla tabla;
	private StringBuilder servicio;
	private Indexacion indexacion = new Indexacion();

	private String nombreClase;
	private String nombreObjeto;
	
	private String nombreEntidad;
	
	private String SERVICE_IMPL = "ServiceImpl";
	private String SERVICE = "Service";
	
	private String DAO = "Dao";
	
//	private String nombrePrimaryKey = "";
	
	private DirectorioLogica directorioLogica = new DirectorioLogica();
	
	private GenerarLoggerSpring logs = new GenerarLoggerSpring();
	private InyectarDependencia dependencia = new InyectarDependencia();
	
	ServicioImpl() {
		servicio = new StringBuilder();
	}
	
	void generarServicio() {
		
		try {
			generarNombreEntidad();
			importarLibrerias();
			crearServicio();
			abrirCorchetes();
			declararVariables();
			crear();
			editar();
			eliminar();
			consultarTodos();
			consultarPorId();
			servicio.append(indexacion.agregarSaltoLinea(2));
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
//		getNombrePrimaryKey(tabla.getColumnas());
	}
	
	private void importarLibrerias() {
		
		Directorio directorio = directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_SERVICE);
		
		if(directorio != null) {
			servicio.append("package ");
			servicio.append(directorio.getUbicacion());
			servicio.append(";");
			servicio.append(indexacion.agregarSaltoLinea(2));
		}
		
		logs.setNombreClase(nombreClase);
		servicio.append(logs.importarLogger());	
		servicio.append("import java.util.List; ");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("import org.springframework.beans.factory.annotation.Autowired; ");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("import org.springframework.stereotype.Service;		  ");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("import ");
		servicio.append(directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_ENTITY).getUbicacion());
		servicio.append(".");
		servicio.append(nombreEntidad);
		servicio.append(";");
		servicio.append(indexacion.agregarSaltoLinea(1));
		
		servicio.append("import ");
		servicio.append(directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_DAO).getUbicacion());
		servicio.append(".");
		servicio.append(nombreEntidad);
		servicio.append(DAO);
		servicio.append(";");
		servicio.append(indexacion.agregarSaltoLinea(1));
		
	}
	
	
	
	private void generarNombreEntidad() {
		this.nombreEntidad = PrimerLetraMayuscula.transformar(tabla.getDescripcion());
	}
	
	private void declararVariables() {
		servicio.append(logs.getLogger());
		servicio.append(dependencia.inyectarConAutowired(tabla.getDescripcion() + "_"+DAO));
	}
	
	private void crearServicio() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("@Service");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append("public class ");
		servicio.append(nombreClase);
		servicio.append(SERVICE_IMPL);
		servicio.append(" implements ");
		servicio.append(nombreClase);
		servicio.append(SERVICE);
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
		servicio.append(")");
		abrirCorchetes();
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append(logs.logInformativo("save " + nombreObjeto));
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append("return ");
		servicio.append(dependencia.useDependency(tabla.getDescripcion()+ "_" + DAO));
		servicio.append(".save(" + nombreObjeto + ");");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
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
		servicio.append(")");
		abrirCorchetes();
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append(logs.logInformativo("update " + nombreObjeto));
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append("return ");
		servicio.append(dependencia.useDependency(tabla.getDescripcion()+ "_" + DAO));
		servicio.append(".save(" + nombreObjeto + ");");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}
	
	private void eliminar() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public ");
		servicio.append(" deleteById(");
		servicio.append(" ");
		servicio.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		servicio.append(")");
		abrirCorchetes();
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append(logs.logInformativo("delete " + nombreObjeto));
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append("return ");
		servicio.append(dependencia.useDependency(tabla.getDescripcion()+ "_" + DAO));
		servicio.append(".deleteById(" + PrimaryKey.nombre(tabla.getColumnas()) + ");");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}
	
	private void consultarTodos() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public List<");
		servicio.append(nombreClase);
		servicio.append("> findAll()");
		abrirCorchetes();
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append(logs.logInformativo("findAll " + nombreObjeto));
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append("return ");
		servicio.append(dependencia.useDependency(tabla.getDescripcion()+ "_" + DAO));
		servicio.append(".findAll();");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}
	
	private void consultarPorId() {
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		servicio.append("public ");
		servicio.append(nombreClase);
		servicio.append(" findById(");
		servicio.append(PrimaryKey.nombreTipoDato(tabla.getColumnas()));
		servicio.append(")");
		abrirCorchetes();
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append(logs.logInformativo("find by id " + nombreObjeto));
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(2));
		servicio.append("return ");
		servicio.append(dependencia.useDependency(tabla.getDescripcion()));
		servicio.append(".findById(");
		servicio.append(PrimaryKey.nombre(tabla.getColumnas()));
		servicio.append(");");
		servicio.append(indexacion.agregarSaltoLinea(1));
		servicio.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}

	public String getServicio() {
		return servicio.toString();
	}
	
	private void generarNombreClase() {
		nombreClase = PrimerLetraMayuscula.transformar(tabla.getDescripcion());
	}
	
	private void generarNombreObjeto() {
		nombreObjeto = PrimerLetraMinuscula.transformar(tabla.getDescripcion());
	}
	
	public void setDirectorio(List<Directorio> directorios) {
		directorioLogica.setDirectorios(directorios);
	}
	
	
}
