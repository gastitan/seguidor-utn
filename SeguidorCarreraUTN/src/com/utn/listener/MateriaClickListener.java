package com.utn.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.utn.seguidor.Materia;
import com.utn.vo.MateriaVO;

public class MateriaClickListener implements OnItemClickListener {

	Activity activity;
	ListView listView;
	
	public MateriaClickListener(Activity activity, ListView listView) {
		this.listView = listView;
		this.activity = activity;
	}
	
	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	{
		MateriaVO  itemSelected    = (MateriaVO) listView.getItemAtPosition(position);
		if(itemSelected.getNombre().contains("AÑO"))
			return;
		if(!itemSelected.isHabilitada())
			return;
        
		Intent vistaMateria = new Intent(activity.getApplicationContext(), Materia.class);
		vistaMateria.putExtra("nombreMateria", itemSelected.getNombre());
		vistaMateria.putExtra("posicion", itemSelected.getId().toString());
		activity.startActivity(vistaMateria);
   
	}
}
