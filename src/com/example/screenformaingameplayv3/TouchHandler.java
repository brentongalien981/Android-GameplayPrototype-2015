package com.example.screenformaingameplayv3;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;

public class TouchHandler {
	//Context context;
	int initialX, initialY, lastX, lastY;
	private Player p;
	
	
	// Constructor
	public TouchHandler(Player p) {
		//this.context = context;
		this.p = p;
		initialX = initialY = lastX = lastY = 0;
	}
	
	

	public boolean myOnTouchEvent(MotionEvent event) {
		int action = event.getActionMasked();
		
	    switch(action) {
	        case MotionEvent.ACTION_DOWN:
	            initialX = (int) event.getX();
	            initialY = (int) event.getY();         
	            break;
	        case MotionEvent.ACTION_UP:
	        	lastX = (int) event.getX();
	        	lastY = (int) event.getY();
	        	
	        	if (lastX > initialX) {
	        		movePlayer(true);	        		
	        	} 
	        	else {
	        		movePlayer(false);
	        	}
	    }
    
    return true;
	}
	
	private void movePlayer(boolean isGoingRight) {		
		int a, b;
		double angle;
		int playerAction;
		
		if (isGoingRight) { // positive/going-to-right touch direction
			a = lastX - initialX;
			b = initialY - lastY;
			angle = Math.toDegrees(Math.atan2(b, a));
			
			if ((angle > 45) && (angle <= 90)) { playerAction = GameConstants.JUMP; }
			else if ((angle > -45) && (angle <= 45)) { playerAction = GameConstants.GO_RIGHT; }
			else { playerAction = GameConstants.DUCK; }
		}
		else { // negative/going-to-left touch direction
			a = lastX - initialX;
			b = lastY - initialY;

			a = (a * -1);
			b = (b * -1);
			angle = Math.toDegrees(Math.atan2(b, a));	
			
			if ((angle > 45) && (angle <= 90)) { playerAction = GameConstants.JUMP; }
			else if ((angle > -45) && (angle <= 45)) { playerAction = GameConstants.GO_LEFT; }
			else { playerAction = GameConstants.DUCK; }
		}
		
		p.setAction(playerAction);
	}
}
