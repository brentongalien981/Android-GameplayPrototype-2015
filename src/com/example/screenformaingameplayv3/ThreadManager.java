package com.example.screenformaingameplayv3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ThreadManager {
	Runnable[] runnableArr;

	ThreadPoolExecutor threadPool1, threadPool2, threadPoolOfGameObserver;
	// A queue of Runnables
	private BlockingQueue<Runnable> mDecodeWorkQueue;
	private BlockingQueue<Runnable> mDecodeWorkQueue2;
	private BlockingQueue<Runnable> mDecodeWorkQueue3;
	
	PlayerView player1, player2;

	private static ThreadManager myInstance;
	private Handler mHandler;


	// Sets the amount of time an idle thread waits before terminating
	private static final int KEEP_ALIVE_TIME = 1;
	// Sets the Time Unit to seconds
	private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

	/*
	 * Gets the number of available cores (not always the same as the maximum
	 * number of cores)
	 */
	private static int NUMBER_OF_CORES = Runtime.getRuntime()
			.availableProcessors();

	/**
	 * Constructs the work queues and thread pools used to download and decode
	 * images. Because the constructor is marked private, it's unavailable to
	 * other classes, even in the same package.
	 */
	public ThreadManager() {
		myInstance = this;
		setThreadPools();

		mHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message inputMessage) {
                int updateType = inputMessage.what;
                
                if (updateType == GameConstants.PLAYER_VIEW_UPDATE) {  
                	Player p = (Player) inputMessage.obj;
                	PlayerView pView = p.getView();
                	
	                switch (p.getAction()) {	                   
	                    case GameConstants.GO_RIGHT:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        pView.setX(pView.getX() + 10);
	                        break;
	                    case GameConstants.GO_LEFT:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        pView.setX(pView.getX() - 10);
	                        break;
	                    case GameConstants.JUMP:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        pView.setY(pView.getY() - 10);
	                        break;
	                    case GameConstants.DUCK:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        pView.setY(pView.getY() + 10);
	                        break;
	                    case GameConstants.PUNCH:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        break;                    
	                    case GameConstants.STAND_STILL:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        break;
	                    case GameConstants.GOT_BUMPED:
	                        pView.updateImage(p.getImage(), p.getFacingDirection());
	                        break;
	                }	
                }
                else if (updateType == GameConstants.GAME_STATUS_UPDATE) { 	
                	Player p = (Player) inputMessage.obj;
                	PlayerHealthView pHealthView = p.getHealthView();
                	pHealthView.updateLife(p.getLife());
				}
                else if (updateType == GameConstants.GAME_LAYOUT_UPDATE) {
                	GameplayManager gm = (GameplayManager) inputMessage.obj;
                	gm.getGameLayout().setVisibility(gm.getCurrentVisibility());
                }
                else { // updateType == END_OF_ROUND
                	Player p = (Player) inputMessage.obj;
                	PlayerHealthView pHealthView = p.getHealthView();
                	pHealthView.updateLife(p.getLife());
                	
                	PlayerView pView = p.getView();
                	if (p.getFacingDirection() == GameConstants.FACING_RIGHT) {
                		pView.setX((float) -100);
                	}
                	else {
                		pView.setX((float) 500);
                	}                	
                }
			}
		};
	}
	
	private void setThreadPools() {
		mDecodeWorkQueue = new LinkedBlockingQueue<Runnable>();
		mDecodeWorkQueue2 = new LinkedBlockingQueue<Runnable>();
		mDecodeWorkQueue3 = new LinkedBlockingQueue<Runnable>();

		// Creates a thread pool manager
		threadPool1 = new ThreadPoolExecutor(NUMBER_OF_CORES, // Initial pool size
				NUMBER_OF_CORES, // Max pool size
				KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mDecodeWorkQueue);

		threadPool2 = new ThreadPoolExecutor(NUMBER_OF_CORES, // Initial pool size
				NUMBER_OF_CORES, // Max pool size
				KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mDecodeWorkQueue2);
		
		threadPoolOfGameObserver = new ThreadPoolExecutor(NUMBER_OF_CORES, // Initial pool size
				NUMBER_OF_CORES, // Max pool size
				KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mDecodeWorkQueue3);

	}
	
	
	public ThreadManager getInstance() {
		return myInstance;
	}
	
	public void executePlayerThreadPools(Player p1, Player p2) {
		threadPool1.execute(new ImageSequenceIndexCounter(p1));
		threadPool2.execute(new ImageSequenceIndexCounter(p2));
		threadPoolOfGameObserver.execute(new GameObserver(p1, p2));	
	}



	public void handleGameLayoutUpdate(int updateType, GameplayManager gm) {
		Message completeMessage = mHandler.obtainMessage(updateType, gm);
        completeMessage.sendToTarget();

	}
	
	public void handleUpdate(int updateType, Player p) {
		Message completeMessage = mHandler.obtainMessage(updateType, p);
        completeMessage.sendToTarget();

	}
}
