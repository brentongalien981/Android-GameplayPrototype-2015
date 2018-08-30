package com.example.screenformaingameplayv3;

public class GameObserver implements Runnable {

	private Player p1;
	private Player p2;
	private long sleepTime;
	//private PlayerView p1View;
	//private PlayerView p2View;

	public GameObserver(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		sleepTime = 0;
	}

	@Override
	public void run() {
        while (true) {
        	updateFacingDirections();
			
        	if (hasActionCollision()) {
        		sleepTime = (long) 600;
        	}
        	else {
        		sleepTime = (long) 70;
        	}
        	
			try {
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}

        }

	}
	
	private void updateFacingDirections() {
    	if (p1.getBackFootX() > p2.getBackFootX()) {
    		p1.setFacingDirection(GameConstants.FACING_LEFT);
    		p2.setFacingDirection(GameConstants.FACING_RIGHT);
    	}
    	else {
    		p1.setFacingDirection(GameConstants.FACING_RIGHT);
    		p2.setFacingDirection(GameConstants.FACING_LEFT);
    	}
	}
	
	private boolean hasActionCollision() {		
		// p1 is the one who initiated the action
		switch (p1.getAction()) {
		case GameConstants.PUNCH:
			if (p1.getFacingDirection() == GameConstants.FACING_RIGHT) {
				if (p1.getHittableX() >= p2.getHittableX()) {
					// PlayerStatus offendedPlayer = p2Status;
					// The damage will depend on the action.
					// It's the PlayerStatus object's responsibility to call
					// switch()-case:
					// offendedPlayer.updateLife(action);
					
					p2.setAction(GameConstants.GOT_BUMPED);
					p2.update(this, 20);
					return true;
				}
			}
			else { // facing left
				if (p1.getHittableX() <= p1.getHittableX()) {
					// PlayerStatus offendedPlayer = p2Status;
					// The damage will depend on the action.
					// It's the PlayerStatus object's responsibility to call
					// switch()-case:
					// offendedPlayer.updateLife(action);
					p2.setAction(GameConstants.GOT_BUMPED);
					p2.update(this, 20);
					return true;
				}
			}
		}
		
		// p2 is the one who initiated the action
		switch (p2.getAction()) {
		case GameConstants.PUNCH:
			if (p2.getFacingDirection() == GameConstants.FACING_RIGHT) {
				if (p2.getHittableX() >= p1.getHittableX()) {
					p1.setAction(GameConstants.GOT_BUMPED);
					p1.update(this, 20);
					return true;
				}
			}
			else { // facing left
				if (p2.getHittableX() <= p1.getHittableX()) {
					p1.setAction(GameConstants.GOT_BUMPED);
					p1.update(this, 20);
					return true;
				}
			}
		}
		
		return false;
	}	
}
