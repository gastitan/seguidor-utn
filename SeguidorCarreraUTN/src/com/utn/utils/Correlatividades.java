package com.utn.utils;

import java.util.List;

import android.util.SparseArray;

import com.utn.adapter.MateriaAdapter;
import com.utn.vo.MateriaVO;

public class Correlatividades 
{
	public static Correlatividades INSTANCE;
	
	public static Correlatividades getInstance()
	{
		if(null == INSTANCE)
		{
			INSTANCE = new Correlatividades();
			cargarMapaCursadas();
			cargarMapaAprobadas();
		}
		return INSTANCE;
	}

	public static SparseArray<Integer[]> listaCursadas;
	public static SparseArray<Integer[]> listaAprobadas;
	
	private static void cargarMapaCursadas()
	{
		 listaCursadas = new SparseArray<Integer[]>();
		
		listaCursadas.put(10, new Integer[]{1,2});
		listaCursadas.put(12, new Integer[]{4,5});
		listaCursadas.put(13, new Integer[]{3,5});
		listaCursadas.put(14, new Integer[]{3,5});
		listaCursadas.put(17, new Integer[]{1,2});
	}
	
	private static void cargarMapaAprobadas()
	{
		listaAprobadas = new SparseArray<Integer[]>();
		
		listaAprobadas.put(18, new Integer[]{3,4,5});
		
	}

	public void habilitarMaterias(MateriaAdapter materiaAdapter, MateriaVO materiaCambiada, String nuevoEstado, String viejoEstado) 
	{
		List<Integer> habilitaParaCursar = materiaCambiada.getHabilitaParaCursar();
		List<Integer> habilitaParaAprobar = materiaCambiada.getHabilitaParaAprobar();
		if(nuevoEstado.equals(Constantes.CURSADA) && viejoEstado.equals(Constantes.SIN_CURSAR))
		{
			removerMateriaPorCursar(materiaAdapter, habilitaParaCursar, materiaCambiada.getId());
		}
		else if(nuevoEstado.equals(Constantes.CURSADA) && viejoEstado.equals(Constantes.APROBADA))
		{
			agregarMateriaPorAprobar(materiaAdapter, habilitaParaCursar, materiaCambiada.getId());
		}
		else if(nuevoEstado.equals(Constantes.SIN_CURSAR) && viejoEstado.equals(Constantes.CURSADA))
		{
			agregarMateriaPorCursar(materiaAdapter, habilitaParaCursar, materiaCambiada.getId());
		}
		else if(nuevoEstado.equals(Constantes.SIN_CURSAR) && viejoEstado.equals(Constantes.APROBADA))
		{
			agregarMateriaPorCursar(materiaAdapter, habilitaParaCursar, materiaCambiada.getId());
			agregarMateriaPorAprobar(materiaAdapter, habilitaParaAprobar, materiaCambiada.getId());
		}
		else if(nuevoEstado.equals(Constantes.APROBADA) && viejoEstado.equals(Constantes.CURSADA))
		{
			removerMateriaPorAprobar(materiaAdapter, habilitaParaAprobar, materiaCambiada.getId());
		}
		else if(nuevoEstado.equals(Constantes.APROBADA) && viejoEstado.equals(Constantes.SIN_CURSAR))
		{
			removerMateriaPorCursar(materiaAdapter, habilitaParaCursar, materiaCambiada.getId());
			removerMateriaPorAprobar(materiaAdapter, habilitaParaAprobar, materiaCambiada.getId());
		}
	}
	
	private void removerMateriaPorCursar(MateriaAdapter materiaAdapter, List<Integer> lista, Integer materiaId)
	{
		if(lista.size()==1 && lista.get(0)==0)
			return;
		
		for(Integer i : lista)
		{
			MateriaVO materia = getMateriaById(materiaAdapter, i);
			materia.getMateriasPorCursar().remove(materiaId);
		}
	}
	private void agregarMateriaPorCursar(MateriaAdapter materiaAdapter, List<Integer> lista, Integer materiaId)
	{
		for(Integer i : lista)
		{
			MateriaVO materia = getMateriaById(materiaAdapter, i);
			materia.getMateriasPorCursar().add(materiaId);
		}
	}
	private void removerMateriaPorAprobar(MateriaAdapter materiaAdapter, List<Integer> lista, Integer materiaId)
	{
		if(lista.size()==1 && lista.get(0)==0)
			return;
		
		for(Integer i : lista)
		{
			MateriaVO materia = getMateriaById(materiaAdapter, i);
			materia.getMateriasPorAprobar().remove(materiaId);
		}
	}
	private void agregarMateriaPorAprobar(MateriaAdapter materiaAdapter, List<Integer> lista, Integer materiaId)
	{
		for(Integer i : lista)
		{
			MateriaVO materia = getMateriaById(materiaAdapter, i);
			materia.getMateriasPorAprobar().add(materiaId);
		}
	}
	

	private MateriaVO getMateriaById(MateriaAdapter materiaAdapter, Integer i) 
	{
		for(int x=0; x<materiaAdapter.getCount(); x++)
		{
			MateriaVO mat = materiaAdapter.getItem(x);
			if(mat.getId() == i)
				return mat;
		}
		return null;
		
	}

}
