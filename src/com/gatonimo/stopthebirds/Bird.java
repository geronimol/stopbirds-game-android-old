package com.gatonimo.stopthebirds;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bird {
	private GameView gameView;
	private Bitmap bmp;
	private float x;
	private float y;
	private float ancho;
	private boolean eliminar=false;
	private float width;
	private float height;
	private float velocidad;

	public Bird(GameView gameView, Bitmap bmp) {
		this.gameView=gameView;
		this.bmp=bmp;
		ancho=gameView.getWidth();
		velocidad=ancho/100;
		width=bmp.getWidth();
		height=bmp.getHeight();
		x=0-bmp.getWidth();
		y=10;
	}
	

	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
		
	}

	private void update() {
		if(x<ancho)
			x+=velocidad;
		else
			eliminar=true;
		
	}


	public boolean choco() {
		return eliminar;
	}


	public boolean ColisionBala(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}


	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}
