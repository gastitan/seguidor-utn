package com.utn.utils;

import java.util.ArrayList;
import java.util.List;

public class Constantes 
{
	public static final Integer BLOQUEADA = 0;
	public static final String SIN_CURSAR = "SIN CURSAR";
	public static final String CURSADA = "CURSADA";
	public static final String APROBADA = "APROBADA";
	
	public static final String ANIO = "AÑO";
	
	@SuppressWarnings("serial")
	public static final List<Integer> materiasHabilitadas = new ArrayList<Integer>(){{
		add(1); add(2); add(3); add(4); add(5); add(6); add(9); add(22); add(7); add(16); add(8);}};
}
