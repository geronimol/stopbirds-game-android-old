package com.gatonimo.stopthebirds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class GameOver extends Activity {
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.gameover);
	        
	        final Button btnJugar = (Button) findViewById(R.id.BtnJuga);
	        
	        btnJugar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Intent intent = new Intent(GameOver.this, MainActivity.class);
					startActivity(intent);
					}
			});
	 	}
	   
	 
	 

}
