package com.gatonimo.stopthebirds;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Pause {
	private GameView gameView;
	private Bitmap bmp;
	private float x;
	private float y;
	private float width;
	private float height;
	
	public Pause(GameView gameView, Bitmap bmp) {
		this.gameView=gameView;
		this.bmp=bmp;
		width=bmp.getWidth();
		height=bmp.getHeight();
		x=(gameView.getWidth()/2)+width;
		y=(gameView.getHeight()/2)+height;
	}
	
	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
		
	}

	private void update() {
		
	}


	public void onTouch(float x2, float y2) {
		
			
		
	}
	private boolean isCollition(float x2, float y2) {
		return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
	}

}
