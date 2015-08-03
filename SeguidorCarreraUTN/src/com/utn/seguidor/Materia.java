package com.utn.seguidor;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
        Button btnNotif = (Button) findViewById(R.id.buttonNotif);
        
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
        
        btnNotif.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
//            	Intent intent = new Intent(listView.getContext(), NotifyMessage.class);
//            	PendingIntent pIntent = PendingIntent.getActivity(listView.getContext(), 0, intent, 0);

            	// build notification
            	// the addAction re-use the same intent to keep the example short
            	NotificationCompat.Builder mBuilder =
            		    new NotificationCompat.Builder(getApplicationContext())
            		    .setSmallIcon(R.drawable.ic_launcher)
            		    .setContentTitle("My notification")
            		    .setContentText("Hello World!");
            	    
            	// Sets an ID for the notification
            	int mNotificationId = 001;
            	// Gets an instance of the NotificationManager service
            	NotificationManager mNotifyMgr = 
            	        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            	// Builds the notification and issues it.
            	mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        });
    }
    
}
