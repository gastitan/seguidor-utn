package com.utn.seguidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        TextView posicionMateria = (TextView) findViewById(R.id.posicionMateria);
        Button btnClose = (Button) findViewById(R.id.buttonClose);
        
        Intent i = getIntent();

        String matParam = i.getStringExtra("nombreMateria");
        String posParam = i.getStringExtra("posicion");
        
        nombreMateria.setText(matParam);
        posicionMateria.setText(posParam);
        
        btnClose.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
    }

	protected void leerCambio() 
	{
		String ret = "";

	    try {
	        InputStream inputStream = openFileInput("brenda");

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	            System.out.println(ret);
	        }
	    }
	    catch (FileNotFoundException e) {
	        System.out.println("File not found: " + e.toString());
	    } catch (IOException e) {
	    	System.out.println("Can not read file: " + e.toString());
	    }
		
	}
}
