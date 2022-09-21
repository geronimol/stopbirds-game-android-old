package com.gatonimo.stopthebirds;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Boton {
	private GameView gameView;
	private Bitmap bmp;
	private float x;
	private float y;
	private float width;
	private float height;
	private List<Canion> caniones;
	private int direccion;
	
	public Boton(GameView gameView, Bitmap bmp, float posX, List<Canion> caniones,int direccion, int i) {
		this.gameView=gameView;
		this.bmp=bmp;
		width=bmp.getWidth();
		height=bmp.getHeight();
		x=posX+(width*i);
		y=(gameView.getHeight()/2)+height;
		this.caniones=caniones;
		this.direccion=direccion;
	}
	
	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
		
	}

	private void update() {
		
	}


	public void onTouch(float x2, float y2) {
		if(isCollition(x2,y2)){
			moverCanion();
		}
	}
	
	private void moverCanion() {
		for(Canion canion: caniones)
			canion.mover(direccion);
		
	}

	private boolean isCollition(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}
}
