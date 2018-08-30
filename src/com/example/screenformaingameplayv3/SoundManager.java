package com.example.screenformaingameplayv3;

import com.example.prototype1.*;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	Context context;
	SoundPool soundPool;
	int soundRyuHadouken;
	int soundKenShoryuken;
	
	public SoundManager(Context context) {
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		soundRyuHadouken = soundPool.load(context, R.raw.ryu_hadouken_sound_effect, 1);
		soundKenShoryuken = soundPool.load(context, R.raw.ken_shoryuken_sound_effect, 1);
	}
	
	public void sound(int action) {
		switch (action) {
		case GameConstants.GO_RIGHT:
			soundPool.play(soundKenShoryuken, 1.0f, 1.0f, 0, 0, 1.0f);
			break;
		case GameConstants.PUNCH:
			soundPool.play(soundRyuHadouken, 1.0f, 1.0f, 0, 0, 1.0f);
			break;
		default: // just walking
			break;
		}
	}
}
