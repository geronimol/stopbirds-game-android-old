package com.gatonimo.stopthebirds;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Canion {
	private GameView gameView;
	private Bitmap bmp;
	private float x;
	private float y;
	private float width;
	private float height;

	public Canion(GameView gameView, Bitmap bmp, float posX) {
		this.gameView=gameView;
		this.bmp=bmp;
		width=bmp.getWidth();
		height=bmp.getHeight();
		x=posX-(width/2);
		y=(gameView.getHeight()/2)+height;
	}
	

	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
		
	}

	private void update() {
		
	}


	public void onTouch(float x2, float y2) {
		if(isCollition(x2,y2)){
			gameView.createBalas(x,y);
		}
	}


	public boolean isCollition(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}


	public void mover(int direccion) {
		x+=direccion;
		
	}


	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}


	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
