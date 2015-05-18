package com.aula.meuprojeto08;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Principal extends Activity implements SensorEventListener {
	SensorManager sm;
	Sensor sensor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Obtendo o SensorManager
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //Obtem o Sensor
        sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //Verificamos se o sensor existe no dispositivo
        if (sensor != null){
        	Toast.makeText(this, "Nome do Sensor:" + sensor.getType(), Toast.LENGTH_LONG).show();
        }
        else{
        	Toast.makeText(this, "Não localizamos este sensor", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	//Por economia de energia vamos registrar o sensor apenas
    	//Quando a Activity estiver disponível para o usuário
    	sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	//Quando a Activity não estiver disponível para o usuário
    	//nos vamos remover o registro do nosso sensor. Assim que ela estiver
    	//disponível novamente o registro será refeito.
    	sm.unregisterListener(this);
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Valor:" + event.values[0], Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
}
