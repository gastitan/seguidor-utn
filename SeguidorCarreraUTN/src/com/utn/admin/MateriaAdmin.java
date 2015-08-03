package com.utn.admin;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.utn.utils.Constantes;
import com.utn.utils.ParserUtils;
import com.utn.vo.MateriaVO;
import com.utn.vo.MateriaXmlVO;

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
		if(materiaAux.contains(Constantes.ANIO))
		{
			MateriaVO m = new MateriaVO();
			m.setId(null);
			m.setNombre(materiaAux);
			m.setEstado(null);
			return m;
		}
		String[] arrayMateria = materiaAux.split("-");
		
		MateriaVO m = new MateriaVO();
		m.setId(Integer.valueOf(arrayMateria[0]));
		m.setNombre(arrayMateria[1]);
		m.setEstado(ESTADO_SIN_CURSAR);
		
		List<Integer> materiasPorCursar = getLista(arrayMateria[4]);
		List<Integer> materiasPorAprobar = getLista(arrayMateria[5]);

		m.setMateriasPorCursar(materiasPorCursar);
		m.setMateriasPorAprobar(materiasPorAprobar);
		
		m.setHabilitaParaCursar(getLista(arrayMateria[2]));
		m.setHabilitaParaAprobar(getLista(arrayMateria[3]));
		
		return m;
	}

	private static List<Integer> getLista(String string) 
	{
		String[] arrayMaterias = string.split(",");
		List<Integer> lista = new ArrayList<Integer>();
		
		if(arrayMaterias.length==1 && arrayMaterias[0].equals("0"))
			return lista;
		
		for(int x=0; x<arrayMaterias.length; x++)
		{
			Integer i = Integer.valueOf(arrayMaterias[x]);
			lista.add(i);
		}
		return lista;
	}
	
	public static List<MateriaVO> getMateriasPrincipales(InputStream inputStream)
	{
        try 
        {
        	XmlPullParser parser = Xml.newPullParser();
        	parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            ArrayList<MateriaXmlVO> electivas = ParserUtils.parseXMLPrincipales(parser);

            return getMateriasVOFromPrincipales(electivas);

        }catch(Exception e)
        {
        	return null;
        }
	}
	
	public static List<MateriaVO> getMateriasElectivas(InputStream inputStream)
	{
        try 
        {
        	XmlPullParser parser = Xml.newPullParser();
        	parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            ArrayList<MateriaXmlVO> electivas = ParserUtils.parseXMLElectivas(parser);

            return getMateriasVOfromElectivas(electivas);

        }catch(Exception e)
        {
        	return null;
        }
	}
	
	public static List<MateriaVO> getMateriasVOFromPrincipales(ArrayList<MateriaXmlVO> principales)
	{
		List<MateriaVO> materias = new ArrayList<MateriaVO>();
		for (MateriaXmlVO materiaXml : principales) 
		{
			MateriaVO m = new MateriaVO();
			m.setId(Integer.valueOf(materiaXml.getId()));
			m.setNombre(materiaXml.getNombre());
			m.setEstado(ESTADO_SIN_CURSAR);
			
			List<Integer> materiasPorCursar = getLista(materiaXml.getPorCursar());
			List<Integer> materiasPorAprobar = getLista(materiaXml.getPorAprobar());
	
			m.setMateriasPorCursar(materiasPorCursar);
			m.setMateriasPorAprobar(materiasPorAprobar);
			
			m.setHabilitaParaCursar(getLista(materiaXml.getHabilitaCursar()));
			m.setHabilitaParaAprobar(getLista(materiaXml.getHabilitaAprobar()));
		
			materias.add(m);
		}
		return materias;
	}
	
	public static List<MateriaVO> getMateriasVOfromElectivas(ArrayList<MateriaXmlVO> electivas) 
	{
		List<MateriaVO> materias = new ArrayList<MateriaVO>();
		for (MateriaXmlVO electiva : electivas) 
		{
			MateriaVO vo = new MateriaVO();
			vo.setId(Integer.valueOf(electiva.getId()));
			vo.setNombre(electiva.getNombre());
			vo.setMateriasPorCursar(new ArrayList<Integer>());
			vo.setMateriasPorAprobar(new ArrayList<Integer>());
			vo.setEstado(ESTADO_SIN_CURSAR);
			materias.add(vo);
		}
		return materias;
	}
	
	public static MateriaVO getTitulo(String titulo)
	{
		MateriaVO m = new MateriaVO();
		m.setId(null);
		m.setNombre(titulo);
		m.setEstado(null);
		return m;
	}
}
