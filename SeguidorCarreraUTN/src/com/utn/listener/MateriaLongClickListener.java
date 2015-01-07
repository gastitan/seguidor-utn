package com.utn.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.utn.adapter.MateriaAdapter;
import com.utn.seguidor.R;
import com.utn.utils.Correlatividades;
import com.utn.utils.FileUtils;
import com.utn.vo.MateriaVO;

public class MateriaLongClickListener implements OnItemLongClickListener
{
	ListView listView;
	Activity activity;
	MateriaAdapter materiaAdapter;
	
	public MateriaLongClickListener(Activity activity, ListView listView, MateriaAdapter materiaAdapter)
	{
		this.listView = listView;
		this.activity = activity;
		this.materiaAdapter = materiaAdapter;
	}
	
	@Override
    public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id)
    {
    	MateriaVO  itemSelected    = (MateriaVO) listView.getItemAtPosition(position);
        if(itemSelected.getNombre().contains("AÑO"))
        	return false;
        if(!itemSelected.isHabilitada())
			return false;
        
    	AlertDialog.Builder choiceDialogBuilder = new Builder(activity);
        choiceDialogBuilder.setSingleChoiceItems(R.array.estados_array, 0, new OnClickListener() {
			
        	@Override
        	public void onClick(DialogInterface dialog, int which) 
        	{
        		MateriaVO materiaSelected = materiaAdapter.getItem(position);
        		//int menuItemIndex = item.getItemId();
        		String[] menuItems = activity.getResources().getStringArray(R.array.estados_array);
        	  	String menuItemName = menuItems[which];
//        	  	String listItemName = Countries[info.position];
        	  	//int pos = info.position;
        	  	materiaSelected.setEstado(menuItemName);
        	  	materiaAdapter.notifyDataSetChanged();
        	
        	  	Correlatividades.getInstance().habilitarMaterias();
        	  	
        	  	FileUtils.actualizarArchivo(activity, materiaAdapter);
        	  	dialog.dismiss();
        	}
   
        });
        choiceDialogBuilder.create().show();
        return true;
    }
}
