package com.harias.app.model;

public class Columna {

	private long id;
	private String nombre;
	private TipoDato tipoDato;
	private boolean primaryKey;
	private boolean foreignKey;
	private Columna referencia;
	
	public Columna() {
		super();
	}

	public Columna(long id, String nombre, TipoDato tipoDato) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoDato = tipoDato;
	}
	
	public Columna(long id, String nombre, TipoDato tipoDato, boolean primaryKey, boolean foreignKey,
			Columna referencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipoDato = tipoDato;
		this.primaryKey = primaryKey;
		this.foreignKey = foreignKey;
		this.referencia = referencia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDato getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(TipoDato tipoDato) {
		this.tipoDato = tipoDato;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	public Columna getReferencia() {
		return referencia;
	}

	public void setReferencia(Columna referencia) {
		this.referencia = referencia;
	}

	
	
	
}
