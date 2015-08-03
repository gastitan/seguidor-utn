package com.utn.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.utn.vo.MateriaXmlVO;

public class ParserUtils 
{
	public static ArrayList<MateriaXmlVO> parseXMLElectivas(XmlPullParser parser) throws XmlPullParserException,IOException
	{
		ArrayList<MateriaXmlVO> electivas = null;
        int eventType = parser.getEventType();
        MateriaXmlVO currentElectiva = null;

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	electivas = new ArrayList<MateriaXmlVO>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("electiva")){
                        currentElectiva = new MateriaXmlVO();
                    } else if (currentElectiva != null){
                        if (name.equals("id")){
                            currentElectiva.setId(parser.nextText());
                        } else if (name.equals("nombre")){
                        	currentElectiva.setNombre(parser.nextText());
                        }  
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("electiva") && currentElectiva != null){
                    	electivas.add(currentElectiva);
                    } 
            }
            eventType = parser.next();
        }

        return electivas;
	}
	
	public static ArrayList<MateriaXmlVO> parseXMLPrincipales(XmlPullParser parser) throws XmlPullParserException,IOException
	{
		ArrayList<MateriaXmlVO> electivas = null;
        int eventType = parser.getEventType();
        MateriaXmlVO currentElectiva = null;

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	electivas = new ArrayList<MateriaXmlVO>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("materia")){
                        currentElectiva = new MateriaXmlVO();
                    } else if (currentElectiva != null){
                        if (name.equals("id")){
                            currentElectiva.setId(parser.nextText());
                        } else if (name.equals("nombre")){
                        	currentElectiva.setNombre(parser.nextText());
                        } else if (name.equals("habilita_cursar")){
                        	currentElectiva.setHabilitaCursar(parser.nextText());
                        } else if (name.equals("habilita_aprobar")){
                        	currentElectiva.setHabilitaAprobar(parser.nextText());
                        } else if (name.equals("por_cursar")){
                        	currentElectiva.setPorCursar(parser.nextText());
                        } else if (name.equals("por_aprobar")){
                        	currentElectiva.setPorAprobar(parser.nextText());
                        }     
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("materia") && currentElectiva != null){
                    	electivas.add(currentElectiva);
                    } 
            }
            eventType = parser.next();
        }

        return electivas;
	}
}
