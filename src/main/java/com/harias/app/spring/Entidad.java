package com.harias.app.spring;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.harias.app.api.directorio.DirectorioCodigo;
import com.harias.app.api.directorio.DirectorioLogica;
import com.harias.app.logica.Indexacion;
import com.harias.app.logica.PalabraPlural;
import com.harias.app.logica.PrimerLetraMayuscula;
import com.harias.app.logica.PrimerLetraMinuscula;
import com.harias.app.model.Columna;
import com.harias.app.model.Directorio;
import com.harias.app.model.Tabla;

public class Entidad {

	private Logger logger = LoggerFactory.getLogger(Entidad.class);

	private Tabla tabla;
	private StringBuilder entidad;
	
	private Indexacion indexacion = new Indexacion();
	
	private DirectorioLogica directorioLogica = new DirectorioLogica();
	
	private PalabraPlural palabraPlural = new PalabraPlural();
	
	Entidad() {
		this.entidad = new StringBuilder();
	}

	void generarEntidad() {

		this.importarLibrerias();
		this.mapearEntidad();
		this.abrirCorchetes();
		entidad.append(indexacion.agregarSaltoLinea(1));
		this.crearLosAtributos();
		crearContructorVacio();
		crearConstructorConCampos(tabla.getColumnas());
		this.metodosAccesoInformacion();
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("}");
	}

	void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}
	
	void setDirectorio(List<Directorio> directorios) {
		directorioLogica.setDirectorios(directorios);
	}

	String getEntidad() {
		logger.info("getEntidad ");
		logger.info(entidad.toString());
		return this.entidad.toString();
	}

	private void importarLibrerias() {
		
		Directorio directorio = directorioLogica.getDirectorioByCodigo(DirectorioCodigo.DIRECTORIO_SPRING_ENTITY);
		
		if(directorio != null) {
			entidad.append("package ");
			entidad.append(directorio.getUbicacion());
			entidad.append(";");
			entidad.append(indexacion.agregarSaltoLinea(2));
		}

		entidad.append("import javax.persistence.Column;");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("import javax.persistence.Entity;");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("import javax.persistence.GeneratedValue;");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("import javax.persistence.GenerationType;");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("import javax.persistence.Id;");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("import javax.persistence.Table;");
		entidad.append(indexacion.agregarSaltoLinea(1));
	}

	private void mapearEntidad() {

		String nombreTabla = palabraPlural.convertirAPluaral(tabla.getDescripcion()).toUpperCase();

		entidad.append(indexacion.agregarSaltoLinea(2));
		entidad.append("@Entity");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("@Table(name=\"" + nombreTabla + "\")");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append("class " + PrimerLetraMayuscula.transformar(tabla.getDescripcion()) + "");

	}

	private void abrirCorchetes() {
		this.entidad.append("{");
		entidad.append(indexacion.agregarSaltoLinea(1));
	};

	private void cerrarCorchetes() {
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		this.entidad.append("}");
		entidad.append(indexacion.agregarSaltoLinea(1));
	}

	private void mapearAtributoPrimaryKey(Columna columna) {
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("@Id");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("@GeneratedValue(strategy = GenerationType.IDENTITY)");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("private ");
		entidad.append(columna.getTipoDato().getDescripcion());
		entidad.append(" ");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(";");
		entidad.append(indexacion.agregarSaltoLinea(1));
	}

	private void mapearAtributoForeignKey(Columna columna) {

		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("@ManyToOne");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("@JoinColumn(name=\" " + columna.getReferencia().getNombre() + " \")");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("private ");
		entidad.append(columna.getTipoDato().getDescripcion());
		entidad.append(" ");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(";");
		entidad.append(indexacion.agregarSaltoLinea(1));
	}

	private void mapearAtributoPorTipoDato(Columna columna) {
		
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("@Column(name=\""+ columna.getNombre() +"\")");
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("private ");
		entidad.append(columna.getTipoDato().getDescripcion());
		entidad.append(" ");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(";");
		entidad.append(indexacion.agregarSaltoLinea(1));
	}

	private void crearLosAtributos() {

		this.tabla.getColumnas().forEach(columna -> {
			if (columna.isPrimaryKey()) {
				mapearAtributoPrimaryKey(columna);
			} else if (columna.isForeignKey()) {
				mapearAtributoForeignKey(columna);
			} else {
				mapearAtributoPorTipoDato(columna);
			}
		});
	}

	private void metodosAccesoInformacion() {
		this.tabla.getColumnas().forEach(columna->{
			crearMetodoGetter(columna);
			crearMetodoSetter(columna);
		});
	}
	
	private void crearMetodoSetter(Columna columna) {

		entidad.append(indexacion.agregarSaltoLinea(2));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("public ");
		entidad.append(" void ");
		entidad.append(PrimerLetraMinuscula.transformar("set_" + columna.getNombre()));
		entidad.append("( ");
		entidad.append(columna.getTipoDato().getDescripcion());
		entidad.append(" ");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(")");
		abrirCorchetes();
		entidad.append(indexacion.agregarTabulacion(2));
		entidad.append("this.");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(" = ");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(";");
		entidad.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}

	private void crearMetodoGetter(Columna columna) {
	
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("public ");
		entidad.append(columna.getTipoDato().getDescripcion());
		entidad.append(" ");
		entidad.append(PrimerLetraMinuscula.transformar("get_"+columna.getNombre()));
		entidad.append("()");
		entidad.append(indexacion.agregarTabulacion(1));
		abrirCorchetes();
		entidad.append(indexacion.agregarTabulacion(2));
		entidad.append("return ");
		entidad.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		entidad.append(";");
		entidad.append(indexacion.agregarTabulacion(1));
		cerrarCorchetes();
	}
	
	private void crearContructorVacio() {
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("public ");
		entidad.append(PrimerLetraMayuscula.transformar(tabla.getDescripcion()));
		entidad.append("()");
		abrirCorchetes();
		entidad.append(indexacion.agregarTabulacion(2));
		entidad.append("super(); ");
		cerrarCorchetes();
		
	}
	
	private void crearConstructorConCampos(List<Columna> columnas) {
		
		StringBuilder encabezado = new StringBuilder();
		StringBuilder detalle = new StringBuilder();
		
		columnas.forEach(columna->{
			encabezado.append(getEncabezado(columna));
			detalle.append(getDetalle(columna));
		});
		
		entidad.append(indexacion.agregarSaltoLinea(1));
		entidad.append(indexacion.agregarTabulacion(1));
		entidad.append("public ");
		entidad.append(PrimerLetraMayuscula.transformar(tabla.getDescripcion()));
		entidad.append("(");
		entidad.append(encabezado.toString().substring(0, encabezado.toString().length() - 2));
		entidad.append(")");
		abrirCorchetes();
		entidad.append(detalle.toString());
		cerrarCorchetes();
	}
	
	private String getEncabezado(Columna columna) {
		StringBuilder encabezado = new StringBuilder();
		encabezado.append(columna.getTipoDato().getDescripcion());
		encabezado.append(" ");
		encabezado.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		encabezado.append(", ");
		return encabezado.toString();
	};
	

	private String getDetalle(Columna columna) {
		StringBuilder detalle = new  StringBuilder();
		detalle.append(indexacion.agregarTabulacion(2));
		detalle.append("this.");
		detalle.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		detalle.append("=");
		detalle.append(PrimerLetraMinuscula.transformar(columna.getNombre()));
		detalle.append(";");
		detalle.append(indexacion.agregarSaltoLinea(1));
		return detalle.toString();
	};
	
}
