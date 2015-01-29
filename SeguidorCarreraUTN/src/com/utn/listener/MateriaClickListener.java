package com.utn.listener;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.utn.adapter.MateriaAdapter;
import com.utn.seguidor.Materia;
import com.utn.utils.Constantes;
import com.utn.vo.MateriaVO;

public class MateriaClickListener implements OnItemClickListener {

	Activity activity;
	ListView listView;
	MateriaAdapter materiaAdapter;
	
	public MateriaClickListener(Activity activity, ListView listView, MateriaAdapter materiaAdapter) {
		this.listView = listView;
		this.activity = activity;
		this.materiaAdapter = materiaAdapter;
	}
	
	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	{
		MateriaVO  itemSelected    = (MateriaVO) listView.getItemAtPosition(position);
		if(itemSelected.getNombre().contains(Constantes.ANIO))
			return;
//		if(!itemSelected.isHabilitada())
//			return;

		Intent vistaMateria = new Intent(activity.getApplicationContext(), Materia.class);
		vistaMateria.putExtra("nombreMateria", itemSelected.getNombre());
		vistaMateria.putExtra("posicion", itemSelected.getId().toString());
		
		List<Integer> materiasPorCursar = itemSelected.getMateriasPorCursar();
		String listaString1 = armarListaString(materiasPorCursar);
		List<Integer> materiasPorAprobar = itemSelected.getMateriasPorAprobar();
		String listaString2 = armarListaString(materiasPorAprobar);
		
		vistaMateria.putExtra("materiasPorCursar", listaString1);
		vistaMateria.putExtra("materiasPorAprobar", listaString2);
		
		activity.startActivity(vistaMateria);
   
	}
	
	private String armarListaString(List<Integer> lista)
	{
		String aux = "";
	    for (Integer integ : lista) 
	    {
	    	MateriaVO mat = getMateriaById(integ);
	    	aux += "- " + mat.getNombre() + "\n";
		}
	    return aux;
	}
	
	private MateriaVO getMateriaById(Integer i) 
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
