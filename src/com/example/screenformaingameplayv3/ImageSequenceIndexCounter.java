package com.example.screenformaingameplayv3;

import android.util.Log;
import android.widget.TextView;

public class ImageSequenceIndexCounter implements Runnable {
	
	private Player p;

	// constructor
	public ImageSequenceIndexCounter(Player p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		// Moves the current Thread into the background
		// Is this necessary? Try running this app without this line.
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        // here is the animation thread
        while (true) {
        	p.update(this);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
        }
     
        
	}

}
