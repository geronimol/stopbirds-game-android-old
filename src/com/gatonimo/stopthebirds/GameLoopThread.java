package com.gatonimo.stopthebirds;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.MenuItem;

@SuppressLint("WrongCall")
public class GameLoopThread extends Thread {
	static final long FPS = 60;
	private GameView view;
	private boolean running = false;
	private boolean paused = false;

	public GameLoopThread(GameView view) {
		this.view = view;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		while (running) {
			while (!paused) {
				Canvas c = null;
				startTime = System.currentTimeMillis();
				try {
					c = view.getHolder().lockCanvas();
					synchronized (view.getHolder()) {
						view.onDraw(c);
					}
				} finally {
					if (c != null) {
						view.getHolder().unlockCanvasAndPost(c);
					}
				}
				sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
				try {
					if (sleepTime > 0)
						sleep(sleepTime);
					else
						sleep(1);
				} catch (Exception e) {
				}
			}
		}
	}

	public void pausar() {
		if (paused)
			paused = false;
		else
			paused = true;

	}

	public boolean isPaused() {
		return paused;
	}
}