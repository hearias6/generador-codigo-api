package com.harias.app.spring;

import com.harias.app.model.Tabla;

public class Dao {
	
	private Tabla tabla;
	private StringBuilder dao;

	Dao() {
		dao = new StringBuilder();
	}
	
	void generarDao() {
		
		try {
			this.importarLibrerias();
			this.mapearDao();
			this.abrirCorchetes();
			this.cerrarCorchetes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	void setTabla(Tabla tabla) {
		this.tabla = tabla;
	}

	String getDao() {
		return this.dao.toString();
	}
	
	private void importarLibrerias() {
		dao.append("import org.springframework.data.jpa.repository.JpaRepository;");
		dao.append("import org.springframework.stereotype.Repository;");
		dao.append("import com.harias.app.entities.HaContrato;");
	}
	
	private void mapearDao() {
		dao.append("@Repository");
		dao.append("public interface HaContratoDao extends JpaRepository<HaContrato, Long>");
	}
	
	private void abrirCorchetes() {
		this.dao.append("{");
	};
	
	private void cerrarCorchetes() {
		this.dao.append("}");
	}
	
}
