package com.utn.vo;

import java.io.Serializable;
import java.util.List;

public class MateriaVO implements Serializable
{
	private static final long serialVersionUID = -7309002479707784401L;
	
	private Integer id;
	private String nombre;
	private String estado;
//	private boolean habilitada;
	private List<Integer> materiasPorCursar;
	private List<Integer> materiasPorAprobar;
	private List<Integer> habilitaParaCursar;
	private List<Integer> habilitaParaAprobar;

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
		return (materiasPorCursar.size()==0 && materiasPorAprobar.size()==0);
	}
	public List<Integer> getMateriasPorCursar() {
		return materiasPorCursar;
	}
	public void setMateriasPorCursar(List<Integer> necesitaParaCursar) {
		this.materiasPorCursar = necesitaParaCursar;
	}
	public List<Integer> getMateriasPorAprobar() {
		return materiasPorAprobar;
	}
	public void setMateriasPorAprobar(List<Integer> necesitaParaAprobar) {
		this.materiasPorAprobar = necesitaParaAprobar;
	}
	public List<Integer> getHabilitaParaCursar() {
		return habilitaParaCursar;
	}
	public void setHabilitaParaCursar(List<Integer> habilitaParaCursar) {
		this.habilitaParaCursar = habilitaParaCursar;
	}
	public List<Integer> getHabilitaParaAprobar() {
		return habilitaParaAprobar;
	}
	public void setHabilitaParaAprobar(List<Integer> habilitaParaAprobar) {
		this.habilitaParaAprobar = habilitaParaAprobar;
	}
	
	@Override
	public String toString() {
		return (id!=null)?id + "." + nombre:nombre;
	}
	
	public boolean sePuedeCursar()
	{
		return materiasPorCursar.size() == 0;
	}
	
	public boolean sePuedeFinal()
	{
		return materiasPorAprobar.size() == 0;
	}
}
