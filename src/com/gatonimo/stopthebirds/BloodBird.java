package com.gatonimo.stopthebirds;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BloodBird {
	private GameView gameView;
	private Bitmap bmp;
	private int life=300;
	private float x;
	private float y;
	private float width;
	private float height;
	private List<BloodBird> bloodbirds;
	
	public BloodBird(List<BloodBird> bloodbirds, Bitmap bmp, float posX,float posY) {
		this.bloodbirds=bloodbirds;
		this.bmp=bmp;
		width=bmp.getWidth();
		height=bmp.getHeight();
		x=posX;
		y=posY;
	}
	
	public void onDraw(Canvas canvas) {
		update();
		canvas.drawBitmap(bmp, x, y, null);
	}

	private void update() {
		if (--life < 1) {
			bloodbirds.remove(this);
		}
		
	}
}
