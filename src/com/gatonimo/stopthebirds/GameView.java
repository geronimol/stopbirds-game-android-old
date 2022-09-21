package com.gatonimo.stopthebirds;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "WrongCall", "DrawAllocation" })
public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private List<Bird> birds = new ArrayList<Bird>();
	private List<Canion> caniones=new ArrayList<Canion>();
	private List<Bala> balas= new ArrayList<Bala>();
	private List<BloodBird> bloodbirds=new ArrayList<BloodBird>();
	private List<Boton> botones=new ArrayList<Boton>();
	private List<Explosion> explosiones= new ArrayList<Explosion>();
	private List<Pause> pauses= new ArrayList<Pause>();
	private Bitmap bmpBlood;
	private Bitmap bmpBird;
	private Bitmap bmpFondo;
	private Bitmap resized;
	private Bitmap bmpCanion;
	private Bitmap bmpBala;
	private long lastClick;
	private boolean gameOver=false;

	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		getHolder().addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				bmpFondo = Bitmap.createScaledBitmap(bmpFondo, getWidth(),getHeight(),true);
				creaCaniones(getWidth()/2);
				creaBoton(0, (-1)*getWidth()/20, R.drawable.botonizq,0);
				creaBoton(getWidth(), getWidth()/20,R.drawable.botonder,-1);
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		bmpBird = BitmapFactory.decodeResource(getResources(),
				R.drawable.bird);
		bmpFondo = BitmapFactory.decodeResource(getResources(),
				R.drawable.fondo);
		bmpCanion = BitmapFactory.decodeResource(getResources(),
				R.drawable.canion);
		bmpBala = BitmapFactory.decodeResource(getResources(),
				R.drawable.bala);
		bmpBlood = BitmapFactory.decodeResource(getResources(),
				R.drawable.bloodbird);
		
		
		
	}
	


	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawBitmap(bmpFondo,0,0, null);
		
		for (int i = pauses.size() - 1; i >= 0; i--) {
            pauses.get(i).onDraw(canvas);
		}
		
		for (int i = bloodbirds.size() - 1; i >= 0; i--) {
            bloodbirds.get(i).onDraw(canvas);
		}
		for (int i = explosiones.size() - 1; i >= 0; i--) {
            explosiones.get(i).onDraw(canvas);
		}
		
		if(gameOver)
			gameOver();
		
		for (int i = balas.size() - 1; i >= 0; i--) {
			Bala bala = balas.get(i);
			if(bala.choco())
				balas.remove(i);
			else
				bala.onDraw(canvas);
		}
		
		for(Canion canion: caniones)
			canion.onDraw(canvas);
		
		for(Boton boton: botones)
			boton.onDraw(canvas);
		
		if(birds.isEmpty())
			creaBirds();
		else
			for (Bird bird : birds)
				bird.onDraw(canvas);
		for (Bird bird : birds)
			if(bird.choco())
				birds.remove(bird);
		
		
		
			
			
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if (System.currentTimeMillis() - lastClick > 300  && !gameLoopThread.isPaused()) {
			lastClick = System.currentTimeMillis();
			synchronized (getHolder()) {
				float x = event.getX();
				float y = event.getY();
				for(Pause pause: pauses)
					pause.onTouch(x,y);
				for (Canion canion: caniones)
					canion.onTouch(x,y);
				for(Boton boton: botones)
					boton.onTouch(x,y);
				}
		}
			return true;
		
	}
	
	private Bird createBird(int resource) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new Bird(this, bmp);
	}

	private void creaBirds(){
		Bird bird=createBird(R.drawable.bird);
		birds.add(bird);
	}
	private void creaCaniones(float x) {
		Canion canion=createCanion(x);
		caniones.add(canion);
		
	}

	private Canion createCanion(float x) {
		return new Canion(this,bmpCanion,x);
	}
	
	private void creaBoton(float posX, int direccion, int resource, int i){
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		Boton boton=new Boton(this, bmp, posX, caniones, direccion,i);
		botones.add(boton);
	}
	
	protected void creaPause() {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pause);
		Pause pause=new Pause(this,bmp);
		pauses.add(pause);
		
	}
	
	public Bala createBala(int resource,float x, float y){
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new Bala(this, bmp,x,y);
	}

	public void createBalas(float x, float y) {
		balas.add(createBala(R.drawable.bala,x,y));
		
	}
	private BloodBird creaBloodBird(int resource,float x, float y) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
		return new BloodBird(bloodbirds,bmp,x,y);
	}
	private void creaBloodBirds(float x, float y) {
		bloodbirds.add(creaBloodBird(R.drawable.bloodbird,x,y));
		
	}
	
	private void creaExplosion(float x, float y) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.explosion);
		explosiones.add(new Explosion(explosiones,bmp,x,y));
		
	}



	public boolean ColisionBird(float x, float y) {
		float X,Y;
		for (int i = birds.size() - 1; i >= 0; i--) {
			Bird bird = birds.get(i);
			if(bird.ColisionBala(x,y)){
				X=bird.getX();
				Y=bird.getY();
				birds.remove(i);
				creaBloodBirds(X,Y);
				return true;
			}
		}
		return false;
	}
	
	public boolean ColisionCanion(float x, float y) {
		float X,Y;
		for (int i = caniones.size() - 1; i >= 0; i--) {
			Canion canion = caniones.get(i);
			if(canion.isCollition(x,y)){
				X=canion.getX();
				Y=canion.getY();
				caniones.remove(i);
				creaExplosion(X,Y);
				gameOver=true;
				return true;
			}
		}
		return false;
	}

	private void gameOver() {
		Intent gameOver = new Intent(getContext(), GameOver.class);
		getContext().startActivity(gameOver);
		
	}



	public void pausar() {
		gameLoopThread.pausar();
		
	}



	public boolean isPaused() {
		return gameLoopThread.isPaused();
	}

	public void setRunning(boolean run){
		gameLoopThread.setRunning(run);
	}



}
