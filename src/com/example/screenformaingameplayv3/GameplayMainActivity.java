/*
 * V4: Here, I added the functionality that handles the motion of the player.
 * 
 * V5: Here, I would add the class "FightObserver.class",  which handles the:
 * 			-> facing direction of players
 * 			-> life of players
 * 			-> rhythm of players
 * 
 * V6: Here, I restructured the classes.
 * 
 */

package com.example.screenformaingameplayv3;

import com.example.prototype1.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GameplayMainActivity extends Activity implements Button.OnClickListener {
	// buttons
	private Button bL, bR, bU, bD, bP;
		
	private LinearLayout p1HealthLayout, p2HealthLayout, layoutOfGameStatus;
	private RelativeLayout layoutForCharacters, secondDeepest;
	
	private Player p1, p2;
	private PlayerImageManager p1ImageManager, p2ImageManager;
	private PlayerHealthView p1HealthView, p2HealthView;
	private SoundManager p1SoundManager, p2SoundManager;
	private PlayerView p1View, p2View;
	
	private ThreadManager[] threadManagerArr;
	private TouchHandler touchHandler;
	private MediaPlayer gameMediaPlayer;
	private Context context;
	
	// TEMPORARY
	private ThreadPoolExecutor threadPool;
	private BlockingQueue<Runnable> mDecodeWorkQueue;
	private static final int KEEP_ALIVE_TIME = 1;
	private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
	private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

	
	// FOR DEBUG
	int xPosition;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gameplay);		//root = (LinearLayout) findViewById(R.id.root);
		
		context = this;
		
		setThreadManagers();
		setPlayerImageManagers();
		setPlayerHealthViews();
		setPlayerSoundManagers();
		setPlayerViews();
		setPlayers();	
				
		setLayouts();
		setTouchHandler();		
		setGameMediaPlayer();
		setControlButtons(); // temporary

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new GameplayManager(threadManagerArr, p1, p2, secondDeepest);
				
			}
		}).start();
		

	}
	
		
	private void setLayouts() {
		secondDeepest = (RelativeLayout) findViewById(R.id.secondDeepest);
		layoutForCharacters = (RelativeLayout) findViewById(R.id.layoutForCharacters);
		layoutOfGameStatus = (LinearLayout) findViewById(R.id.layoutOfGameStatus);
		p1HealthLayout = (LinearLayout) findViewById(R.id.layoutOfPlayer1Status);
		p2HealthLayout = (LinearLayout) findViewById(R.id.layoutOfPlayer2Status);
		
		p1HealthLayout.addView(p1HealthView);
		p2HealthLayout.addView(p2HealthView); 		
		
		layoutForCharacters.addView(p1View);
		layoutForCharacters.addView(p2View);	
		
		layoutOfGameStatus.addView(new ViewOfGameStatus(context));
		
	}



	private void setGameMediaPlayer() {
		gameMediaPlayer = MediaPlayer.create(context, R.raw.game_sound_theme);
		gameMediaPlayer.setLooping(true);
		gameMediaPlayer.start();		
	}



	private void setTouchHandler() {
		touchHandler = new TouchHandler(p1);
		
	}



	private void setThreadManagers() {
		threadManagerArr = new ThreadManager[1];
		threadManagerArr[0] = new ThreadManager();
		
	}



	private void setPlayers() {
		p1 = new Player(p1View, p1ImageManager, p1HealthView, p1SoundManager, GameConstants.FACING_RIGHT);
		p2 = new Player(p2View, p2ImageManager, p2HealthView, p2SoundManager, GameConstants.FACING_LEFT);		
	}



	private void setPlayerViews() {
		p1View = new PlayerView(context);
		p2View = new PlayerView(context);
		p1View.setX(-100);
		p2View.setX(360);		
	}



	private void setPlayerSoundManagers() {
		p1SoundManager = new SoundManager(context);
		p2SoundManager = new SoundManager(context);		
	}



	private void setPlayerHealthViews() {
		p1HealthView = new PlayerHealthView(context, "Ryu");
		p2HealthView = new PlayerHealthView(context, "Mattaba");		
	}



	private void setPlayerImageManagers() {
		p1ImageManager = new PlayerImageManager(context);
		p2ImageManager = new PlayerImageManager(context);
		
	}



	private void setControlButtons() {
		bL = (Button) findViewById(R.id.buttonLeft);
		bR = (Button) findViewById(R.id.buttonRight);
		bU = (Button) findViewById(R.id.buttonUp);
		bD = (Button) findViewById(R.id.buttonDown);
		bP = (Button) findViewById(R.id.buttonPunch);
		
		bL.setOnClickListener(this);
		bR.setOnClickListener(this);
		bU.setOnClickListener(this);
		bD.setOnClickListener(this);
		bP.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonLeft:
			p2.setAction(GameConstants.GO_LEFT);
			break;
		case R.id.buttonRight:
			p2.setAction(GameConstants.GO_RIGHT);
			break;
		case R.id.buttonUp:
			p2.setAction(GameConstants.JUMP);
			break;
		case R.id.buttonDown:
			p2.setAction(GameConstants.DUCK);
			break;
		case R.id.buttonPunch:
			p2.setAction(GameConstants.PUNCH);
			break;
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchHandler.myOnTouchEvent(event);
		return super.onTouchEvent(event);
	}

	@Override
	protected void onPause() {
		super.onPause();
		gameMediaPlayer.release();
	}
}
