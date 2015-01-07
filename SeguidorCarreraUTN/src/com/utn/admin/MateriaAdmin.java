package com.utn.admin;

import java.util.ArrayList;
import java.util.List;

import com.utn.utils.Constantes;
import com.utn.vo.MateriaVO;

public class MateriaAdmin 
{
	public static final String ESTADO_SIN_CURSAR = "SIN CURSAR";
	@SuppressWarnings("serial")
	public static final List<Integer> materiasHabilitadas = new ArrayList<Integer>(){{
		add(1);
		add(2);
		add(3);}};
	
	public static MateriaVO crearMateria(String materiaAux)
	{
		if(materiaAux.contains("AÑO"))
		{
			MateriaVO m = new MateriaVO();
			m.setId(null);
			m.setNombre(materiaAux);
			m.setEstado(null);
			m.setHabilitada(true);
			return m;
		}
		String[] arrayMateria = materiaAux.split("-");
		
		MateriaVO m = new MateriaVO();
		m.setId(Integer.valueOf(arrayMateria[0]));
		m.setNombre(arrayMateria[1]);
		m.setEstado(ESTADO_SIN_CURSAR);
		if(Constantes.materiasHabilitadas.contains(m.getId()))
			m.setHabilitada(true);
		else
			m.setHabilitada(false);
		
		m.setHabilitaParaCursar(getLista(arrayMateria[2]));
		
		return m;
	}

	private static List<Integer> getLista(String string) 
	{
		String[] arrayMaterias = string.split(",");
		List<Integer> lista = new ArrayList<Integer>();
		
		for(int x=0; x<arrayMaterias.length; x++)
		{
			Integer i = Integer.valueOf(arrayMaterias[x]);
			lista.add(i);
		}
		return lista;
	}
}
