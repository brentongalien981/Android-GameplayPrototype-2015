package com.example.screenformaingameplayv3;

import android.graphics.Bitmap;

public class Player {
	private ThreadManager threadManager;
	private PlayerView pView;
	private PlayerImageManager pImageManager;
	private PlayerHealthView pHealthView;
	private SoundManager pSoundManager;
	private int facingDirection;
	private int action;
	private int index;
	private Bitmap image;
	private int life;
	private static boolean threadTerminated;
	
	public Player(PlayerView pView, PlayerImageManager pImageManager, PlayerHealthView pHealthView, SoundManager pSoundManager, int facingDirection) {
		this.pView = pView;
		this.pImageManager = pImageManager;
		this.pHealthView = pHealthView;
		this.pSoundManager = pSoundManager;
		this.facingDirection = facingDirection;
		this.action = GameConstants.STAND_STILL;
		this.index = 0;
		this.image = null;
		life = 384; // TODO: Change this to 100, and do the ration 384 px to 100percent.
		threadTerminated = false;
	}
	
	public void endThread() {
		threadTerminated = true;
	}
	
	public void setThreadManager(ThreadManager threadManager) {
		this.threadManager = threadManager;
	}
	
	public PlayerView getView() {
		return pView;
	}
	
	private int getCurrentIndex() {
		// reset to stance position
		if (index == 6) { 
			index = 0; 
			if (action != GameConstants.STAND_STILL) {
				action = GameConstants.STAND_STILL;
			}
		} 
		
		int currentIndex = index;
		++index;
		return currentIndex;
	}
	
	public int getBackFootX() {
		if (facingDirection == GameConstants.FACING_RIGHT) {
			return (int) (pView.getX() + 100);
		} else { // facing left
			return (int) (pView.getX() + 170);
		}
	}
	
	public int getHittableX() {
		if (facingDirection == GameConstants.FACING_RIGHT) {
			return (int) (pView.getX() + 170);
		} else { // facing left
			return (int) (pView.getX() + 100);
		}
	}
	
	public void setAction(int action) {
		this.action = action;
		index = 0;
	}
	
	public int getAction() {
		return action;
	}
	
	public void setFacingDirection(int facingDirection) {
		this.facingDirection = facingDirection;
	}
	
	public int getFacingDirection() {
		return facingDirection;
	}
	
	public Bitmap getImage() {
		return image;
	}

	public void update(ImageSequenceIndexCounter pThread) {
		int tempCurrentIndex = getCurrentIndex();
		image = pImageManager.getImage(action, facingDirection, tempCurrentIndex);
		
		// update the sound
		if (tempCurrentIndex == 0) {
			pSoundManager.sound(action);
		}
		
		threadManager.handleUpdate(GameConstants.PLAYER_VIEW_UPDATE, this);
		
	}
	
	public void update(GameObserver oThread, int damage) {
		damage *= 5; // FOR DEBUG
		updateLife(damage);
		threadManager.handleUpdate(GameConstants.GAME_STATUS_UPDATE, this);
	}
	
	public PlayerHealthView getHealthView() {
		return pHealthView;
	}
	
	public void updateLife(int damage) {
		life -= damage;
	}
	
	public int getLife() {
		return life;
	}
	
	public boolean hasThreadTerminated() {
		return threadTerminated;
	}
	
	public void setX(int x) {
		pView.setX((float) x);
	}
	
	public void resetPlayer() {
		index = 0;
		action = GameConstants.STAND_STILL;
		life = 384;
		image = null;
		threadTerminated = false;
		threadManager.handleUpdate(GameConstants.END_OF_ROUND, this);
	}	
}
