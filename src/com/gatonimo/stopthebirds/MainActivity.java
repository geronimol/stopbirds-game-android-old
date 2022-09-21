package com.gatonimo.stopthebirds;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private AdView adView;
	private String MY_AD_UNIT_ID = "0";//"ca-app-pub-4389568825977209/3247353537";
	
	private String MY_UNIT_ID = "42DBE7BCAD1E6AC46D3AF5353FA02033";
	//private String MY_UNIT_ID="D05BCB553FB78110FC15611A4D0FF265";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		/*final TextView Cargando = (TextView)findViewById(R.id.Loading);
		// Crear adView.
		adView = new AdView(this);
		adView.setAdUnitId(MY_AD_UNIT_ID);
		adView.setAdSize(AdSize.BANNER);
		// Buscar LinearLayout suponiendo que se le ha asignado
		// el atributo android:id="@+id/mainLayout".
		LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
		// Añadirle adView.
		layout.addView(adView);
		// Iniciar una solicitud genérica.
		AdRequest adRequest = new AdRequest.Builder().addTestDevice(MY_UNIT_ID).build();
		// Cargar adView con la solicitud de anuncio.
		adView.loadAd(adRequest);
		
		comprobarError();*/
		
		// Obtenemos una referencia a los controles de la interfaz
		final Button btnJugar = (Button) findViewById(R.id.BtnJugar);
		// Implementamos el evento “click” del botón
		btnJugar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Creamos el Intent
				Intent intent = new Intent(MainActivity.this, MainJuego.class);
				// Iniciamos la nueva actividad
				startActivity(intent);
				}
		});
		/*btnJugar.setVisibility(View.INVISIBLE);

		adView.setAdListener(new AdListener() {

			@Override
			public void onAdLoaded() {
				
				Cargando.setVisibility(View.INVISIBLE);
				btnJugar.setVisibility(View.VISIBLE);
			}
			
		});*/

	}

	private void comprobarError() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
		if (resultCode == ConnectionResult.SUCCESS) {
			Toast.makeText(this, "Loading!", Toast.LENGTH_SHORT).show();
		} else if (resultCode == ConnectionResult.SERVICE_MISSING ||
		           resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
		           resultCode == ConnectionResult.SERVICE_DISABLED) {
		    Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1);
		    dialog.show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	@Override protected void onStart() {
		   super.onStart();
		   //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
		}
		 
		@Override protected void onResume() {
		   super.onResume();
		   //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
		}
		 
		@Override protected void onPause() {
		  // Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
		   super.onPause();
		}
		 
		@Override protected void onStop() {
		   super.onStop();
		  // Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show(); 
		}
		 
		@Override protected void onRestart() {
		   super.onRestart();
		   //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
		}
		 
		@Override protected void onDestroy() {
		   super.onDestroy();
		   //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();     
		}
}
