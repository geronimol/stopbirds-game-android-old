package com.gatonimo.stopthebirds;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class MainJuego extends Activity {
	public GameView gv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(gv=new GameView(this));
		
	}
	
	@Override protected void onPause() {
			if(!gv.isPaused())
				gv.pausar();
           gv.setRunning(false);
           gv=null;
		   Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
		   super.onPause();
		}
	
	@Override protected void onStop() {
		   super.onStop();
		   Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show(); 
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
			gv.pausar();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	
}
