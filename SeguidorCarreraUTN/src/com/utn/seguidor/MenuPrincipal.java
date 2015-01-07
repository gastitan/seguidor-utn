package com.utn.seguidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        
        listView.setOnItemClickListener(new MateriaClickListener(this, listView));
        listView.setOnItemLongClickListener(new MateriaLongClickListener(this, listView, materiaAdapter));
    }
    
    

	private boolean esPrimeraVez()
	{
		File f = new File(getFilesDir()+File.separator+NOMBRE_ARCHIVO);
		return !f.exists();
	}
	
	private List<MateriaVO> getMateriasBase()
	{
		List<MateriaVO> materias = new ArrayList<MateriaVO>();
		InputStream inputStream = getResources().openRawResource(R.raw.materias_base);
		InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedreader = new BufferedReader(inputreader);
        String line;
        List<String> list = new ArrayList<String>(); 
            
        try 
        {
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
	        return materias;
        } 
        catch (Exception e) 
        {
        	return null;
        }
	}
	
}
