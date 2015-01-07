package com.utn.vo;

import java.io.Serializable;

public class MateriaVO implements Serializable
{
	private static final long serialVersionUID = -7309002479707784401L;
	
	private Integer id;
	private String nombre;
	private String estado;
	private boolean habilitada;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isHabilitada() {
		return habilitada;
	}
	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	@Override
	public String toString() {
		return (id!=null)?id + "." + nombre:nombre;
	}
	
	
}
