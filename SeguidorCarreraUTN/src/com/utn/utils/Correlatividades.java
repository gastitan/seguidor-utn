package com.utn.utils;

import android.util.SparseArray;

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

	public void habilitarMaterias() {
		// TODO Auto-generated method stub
		
	}

}
