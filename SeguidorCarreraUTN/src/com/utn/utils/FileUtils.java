package com.utn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.utn.adapter.MateriaAdapter;
import com.utn.vo.MateriaVO;

public class FileUtils {
	
	private static final String NOMBRE_ARCHIVO = "su_materias";
	
	public static void actualizarArchivo(Activity activity, MateriaAdapter materiaAdapter)
	{
		try{
			File f = new File(activity.getFilesDir()+File.separator+NOMBRE_ARCHIVO);
 
    		if(f.delete()){
    			System.out.println(f.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    		
    		List<MateriaVO> auxList = new ArrayList<MateriaVO>();
    		for(int pos=0; pos < materiaAdapter.getCount(); pos++)
    			auxList.add(materiaAdapter.getItem(pos));
    		
    		guardarArchivo(activity, auxList);
    			
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
	}
	
	public static void guardarArchivo(Activity activity, List<MateriaVO> materias)
    {
		ObjectOutput out = null;
	    try {
	        out = new ObjectOutputStream(new FileOutputStream(new File(activity.getFilesDir(),"")+File.separator+NOMBRE_ARCHIVO));
	        out.writeObject(materias);
	        out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	
	@SuppressWarnings("unchecked")
    public static List<MateriaVO> leerDeArchivo(Activity activity) 
    {
//    	ObjectInputStream input;
    	List<MateriaVO> lista = null;
	    try {
	    	File file = new File(activity.getFilesDir()+File.separator+NOMBRE_ARCHIVO);
	    	FileInputStream fin = new FileInputStream(file);
	    	ObjectInputStream ois = new ObjectInputStream(fin);
	    	lista = (List<MateriaVO>) ois.readObject();
	    	ois.close();
	    	return lista;
	    }
	    catch (FileNotFoundException e) {
	        System.out.println("File not found: " + e.toString());
	        return null;
	    } catch (IOException e) {
	    	System.out.println("Can not read file: " + e.toString());
	    	return null;
	    } catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
