package com.twitterapi.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweet")
public class Tweet {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String usuario;
	
	@Column(columnDefinition = "varchar(max)")
	private String texto;
	private String localizacion;
	
	private boolean validacion = false;

	
	public Tweet() {		
	}

	public Tweet(String usuario, String texto, String localizacion) {
		this.usuario = usuario;
		this.texto = texto;
		this.localizacion = localizacion;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public boolean isValidacion() {
		return validacion;
	}

	public void setValidacion(boolean validacion) {
		this.validacion = validacion;
	}
}
