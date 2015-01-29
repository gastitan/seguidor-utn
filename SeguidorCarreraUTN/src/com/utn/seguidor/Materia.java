package com.utn.seguidor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Materia extends ActionBarActivity {

	ListView listView ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);
        
        TextView nombreMateria = (TextView) findViewById(R.id.nombre_materia_desc);
        TextView listaMateriasPC = (TextView) findViewById(R.id.lista_materias_pc);
        TextView listaMateriasPA = (TextView) findViewById(R.id.lista_materias_pa);
        Button btnClose = (Button) findViewById(R.id.buttonClose);
        
        Intent i = getIntent();

        String matParam = i.getStringExtra("nombreMateria");
        
        String materiasPorCursar = i.getStringExtra("materiasPorCursar");
        String materiasPorAprobar = i.getStringExtra("materiasPorAprobar");

        nombreMateria.setText(matParam);
        listaMateriasPC.setText(materiasPorCursar);
        listaMateriasPA.setText(materiasPorAprobar);
        
        btnClose.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
    }
    
}
