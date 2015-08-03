package com.utn.seguidor;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.utn.adapter.MateriaAdapter;
import com.utn.admin.MateriaAdmin;
import com.utn.listener.MateriaClickListener;
import com.utn.listener.MateriaLongClickListener;
import com.utn.utils.FileUtils;
import com.utn.vo.MateriaVO;

public class MenuPrincipal extends Activity {

	private static final String NOMBRE_ARCHIVO = "su_materias";
	ListView listView;
	MateriaAdapter materiaAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        eliminarArchivo();
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        
        listView = (ListView) findViewById(R.id.listaPrincipal);
        materiaAdapter = new MateriaAdapter(this, R.layout.vista_materia);
        listView.setAdapter(materiaAdapter);
        
        List<MateriaVO> materias = new ArrayList<MateriaVO>();
        if(esPrimeraVez())
        {
        	materias = getMateriasBase();
        	FileUtils.guardarArchivo(this, materias);
        }else{
        	materias = FileUtils.leerDeArchivo(this);
        }

        for (MateriaVO materiaVO : materias) 
        {
        	materiaAdapter.add(materiaVO);
		}
        
        listView.setOnItemClickListener(new MateriaClickListener(this, listView, materiaAdapter));
        listView.setOnItemLongClickListener(new MateriaLongClickListener(this, listView, materiaAdapter));
    }
    
    //TODO Eliminar metodo
    private void eliminarArchivo() {
    	File f = new File(getFilesDir()+File.separator+NOMBRE_ARCHIVO);
		f.delete();
	}



	private boolean esPrimeraVez()
	{
		File f = new File(getFilesDir()+File.separator+NOMBRE_ARCHIVO);
		return !f.exists();
	}
	
	private List<MateriaVO> getMateriasBase()
	{
		List<MateriaVO> materias = new ArrayList<MateriaVO>();
		
		//Obtengo las materias 
		materias.addAll(getMateriasPrincipales());
		
		//Obtengo las electivas del XML
		//materias.addAll(getMateriasElectivas());
		
	    return materias;
        
	}
/*
	private List<MateriaVO> getMateriasPrincipalesOld()
	{
		List<MateriaVO> materias = new ArrayList<MateriaVO>();
		InputStream inputStream = getResources().openRawResource(R.raw.materias_base);
		InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        List<String> list = new ArrayList<String>(); 
            
        try 
        {
        	//Obtengo las materias del txt
            while (( line = bufferedreader.readLine()) != null) 
            {
                list.add(line);                
            }
	        String[] values = list.toArray(new String[list.size()]);
	        for(int x=0; x<values.length; x++)
	    	{
	    		MateriaVO m = MateriaAdmin.crearMateria(values[x]);
	    		materias.add(m);
	    	}
        }catch(Exception e)
        {
        	return null;
        }
        return materias;
	}
*/	
	private List<MateriaVO> getMateriasPrincipales()
	{
		List<MateriaVO> materias = new ArrayList<MateriaVO>();
		
		//Materias principales de primer año
		InputStream xmlPrin1 = getResources().openRawResource(R.raw.materias1);
        List<MateriaVO> materias1 = MateriaAdmin.getMateriasPrincipales(xmlPrin1);
        
        //Materias principales de segundo año
        InputStream xmlPrin2 = getResources().openRawResource(R.raw.materias2);
        List<MateriaVO> materias2 = MateriaAdmin.getMateriasPrincipales(xmlPrin2);
        
        //Materias principales de tercer año
        InputStream xmlPrin3 = getResources().openRawResource(R.raw.materias3);
        List<MateriaVO> materias3 = MateriaAdmin.getMateriasPrincipales(xmlPrin3);
        
        //Materias electivas de tercer año
        InputStream xmlElec3 = getResources().openRawResource(R.raw.electivas3);
        List<MateriaVO> electivas3 = MateriaAdmin.getMateriasElectivas(xmlElec3);
        
        //Materias principales de cuarto año
        InputStream xmlPrin4 = getResources().openRawResource(R.raw.materias4);
        List<MateriaVO> materias4 = MateriaAdmin.getMateriasPrincipales(xmlPrin4);
        
        //Materias electivas de cuarto año
        InputStream xmlElec4 = getResources().openRawResource(R.raw.electivas4);
        List<MateriaVO> electivas4 = MateriaAdmin.getMateriasElectivas(xmlElec4);
        
        //Materias principales de quinto año
        InputStream xmlPrin5 = getResources().openRawResource(R.raw.materias5);
        List<MateriaVO> materias5 = MateriaAdmin.getMateriasPrincipales(xmlPrin5);
        
        //Materias electivas de quinto año
        InputStream xmlElec5 = getResources().openRawResource(R.raw.electivas5);
        List<MateriaVO> electivas5 = MateriaAdmin.getMateriasElectivas(xmlElec5);
        
        materias.add(MateriaAdmin.getTitulo("PRIMER AÑO"));
        materias.addAll(materias1);
        materias.add(MateriaAdmin.getTitulo("SEGUNDO AÑO"));
        materias.addAll(materias2);
        materias.add(MateriaAdmin.getTitulo("TERCER AÑO"));
        materias.addAll(materias3);
        materias.add(MateriaAdmin.getTitulo("ELECTIVAS TERCER AÑO"));
        materias.addAll(electivas3);
        materias.add(MateriaAdmin.getTitulo("CUARTO AÑO"));
        materias.addAll(materias4);
        materias.add(MateriaAdmin.getTitulo("ELECTIVAS CUARTO AÑO"));
        materias.addAll(electivas4);
        materias.add(MateriaAdmin.getTitulo("QUINTO AÑO"));
        materias.addAll(materias5);
        materias.add(MateriaAdmin.getTitulo("ELECTIVAS QUINTO AÑO"));
        materias.addAll(electivas5);
        
        return materias;
	}
	
//	private List<MateriaVO> getMateriasElectivas()
//	{
//		List<MateriaVO> materias = new ArrayList<MateriaVO>();
//		InputStream principales1 = getResources().openRawResource(R.raw.electivas3);
//        
//        List<MateriaVO> materias1 = MateriaAdmin.getMateriasElectivas(principales1);
//        
//        materias.addAll(materias1);
//        
//        return materias;
//	}
	
	
}
