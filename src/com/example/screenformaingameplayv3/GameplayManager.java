package com.example.screenformaingameplayv3;

import android.util.Log;
import android.widget.RelativeLayout;


public class GameplayManager {
	ThreadManager tm;
	RelativeLayout gl;
	private static int currentVisibility;
	
	public GameplayManager(ThreadManager[] threadManagerArr, Player p1, Player p2, RelativeLayout gl) {
		tm = threadManagerArr[0].getInstance();
		this.gl = gl;
		p1.setThreadManager(threadManagerArr[0].getInstance());
		p2.setThreadManager(threadManagerArr[0].getInstance());
		
		threadManagerArr[0].executePlayerThreadPools(p1, p2);
		
		for (int i=0; i<3; i++) {


			
			while((p1.getLife() > 0)	&&	(p2.getLife() > 0)) {
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			currentVisibility = RelativeLayout.INVISIBLE;
			tm.handleGameLayoutUpdate(GameConstants.GAME_LAYOUT_UPDATE, this);
			
			p1.resetPlayer();
			p2.resetPlayer();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			currentVisibility = RelativeLayout.VISIBLE;
			tm.handleGameLayoutUpdate(GameConstants.GAME_LAYOUT_UPDATE, this);
			//gl.setBackgroundColor(Color.)
			
			
			
		}
	}
	
	
	
	public int getCurrentVisibility() {
		return currentVisibility;
	}
	
	public RelativeLayout getGameLayout() {
		return gl;
	}


}
