package com.gatonimo.stopthebirds;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bala {
	private GameView gameView;
	private Bitmap bmp;
	private float x;
	private float y;
	private float alto;
	private float height;
	public float velocidad;
	private boolean eliminar=false;
	private boolean llego=false;

	public Bala(GameView gameView, Bitmap bmp,float x,float y) {
		this.gameView=gameView;
		this.bmp=bmp;
		height=bmp.getHeight();
		this.x=x;
		this.y=y;
		this.alto=gameView.getHeight();
		this.velocidad=alto/50;
		
	}
	

	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
		
	}

	private void update() {
		if(y>0 && !llego)
			y-=velocidad;
		else
			if(gameView.ColisionCanion(x,y))
				eliminar=true;
			else
				if(y<alto - height){
					llego=true;
					y+=velocidad;}
			
		if(ColisionBird())
			eliminar=true;
		
	}


	private boolean ColisionBird() {
		return gameView.ColisionBird(x,y);
	}


	public boolean choco() {
		return eliminar;
	}
}
