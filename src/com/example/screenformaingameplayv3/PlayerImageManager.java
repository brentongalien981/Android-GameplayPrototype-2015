package com.example.screenformaingameplayv3;

import com.example.prototype1.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

public class PlayerImageManager {		
	private Bitmap[] currentImageSequence;
	private Bitmap[] stanceImageSequence, stanceImageSequenceFacingLeft;
	private Bitmap[] walkLeftImageSequence, walkLeftImageSequenceFacingLeft;
	private Bitmap[] walkRightImageSequence, walkRightImageSequenceFacingLeft;
	private Bitmap[] punchImageSequence, punchImageSequenceFacingLeft;
	private Bitmap[] gotHeadPunchedImageSequence, gotHeadPunchedImageSequenceFacingLeft;
	private Bitmap image;
	private Context context;

	public PlayerImageManager(Context context) {
		this.context = context;
		setAllImageSequences();
	}
	
	private void setAllImageSequences() {
		currentImageSequence = new Bitmap[6];
		stanceImageSequence = new Bitmap[6];		
		walkRightImageSequence = new Bitmap[6];
		walkLeftImageSequence = new Bitmap[6];
		punchImageSequence = new Bitmap[6];		
		gotHeadPunchedImageSequence = new Bitmap[6];
		stanceImageSequenceFacingLeft = new Bitmap[6];
		walkRightImageSequenceFacingLeft = new Bitmap[6];
		walkLeftImageSequenceFacingLeft = new Bitmap[6];
		punchImageSequenceFacingLeft = new Bitmap[6];
		gotHeadPunchedImageSequenceFacingLeft = new Bitmap[6];
		
		stanceImageSequence[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle1);
		stanceImageSequence[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle2);
		stanceImageSequence[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle3);
		stanceImageSequence[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle4);
		stanceImageSequence[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle5);
		stanceImageSequence[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle6);

		walkRightImageSequence[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right1);
		walkRightImageSequence[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right2);
		walkRightImageSequence[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right3);
		walkRightImageSequence[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right4);
		walkRightImageSequence[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right5);
		walkRightImageSequence[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_right6);
		
		walkLeftImageSequence[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left1);
		walkLeftImageSequence[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left2);
		walkLeftImageSequence[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left3);
		walkLeftImageSequence[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left4);
		walkLeftImageSequence[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left5);
		walkLeftImageSequence[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.walk_left6);
		
		punchImageSequence[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch1);
		punchImageSequence[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch2);
		punchImageSequence[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch3);
		punchImageSequence[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch4);
		punchImageSequence[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch5);
		punchImageSequence[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.punch6);
		
		gotHeadPunchedImageSequence[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.got_head_punched1);
		gotHeadPunchedImageSequence[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.got_head_punched2);
		gotHeadPunchedImageSequence[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.got_head_punched3);
		gotHeadPunchedImageSequence[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.got_head_punched4);
		gotHeadPunchedImageSequence[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.got_head_punched5);
		gotHeadPunchedImageSequence[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.got_head_punched6);
		
		
		// reflect the image in the vertical axis
		Matrix matrix = new Matrix(); 
		matrix.preScale(-1.0f, 1.0f); 
		
		for (int i = 0; i < 6; i++) {
			stanceImageSequenceFacingLeft[i] = Bitmap.createBitmap(stanceImageSequence[i], 0, 0, stanceImageSequence[i].getWidth(), stanceImageSequence[i].getHeight(), matrix, false);
		}
		for (int i = 0; i < 6; i++) {
			walkRightImageSequenceFacingLeft[i] = Bitmap.createBitmap(walkRightImageSequence[i], 0, 0, walkRightImageSequence[i].getWidth(), walkRightImageSequence[i].getHeight(), matrix, false);
		}
		for (int i = 0; i < 6; i++) {
			walkLeftImageSequenceFacingLeft[i] = Bitmap.createBitmap(walkLeftImageSequence[i], 0, 0, walkLeftImageSequence[i].getWidth(), walkLeftImageSequence[i].getHeight(), matrix, false);
		}
		for (int i = 0; i < 6; i++) {
			punchImageSequenceFacingLeft[i] = Bitmap.createBitmap(punchImageSequence[i], 0, 0, punchImageSequence[i].getWidth(), punchImageSequence[i].getHeight(), matrix, false);
		}
		for (int i = 0; i < 6; i++) {
			gotHeadPunchedImageSequenceFacingLeft[i] = Bitmap.createBitmap(gotHeadPunchedImageSequence[i], 0, 0, gotHeadPunchedImageSequence[i].getWidth(), gotHeadPunchedImageSequence[i].getHeight(), matrix, false);
		}
		
		// initialize the current image sequence
		currentImageSequence = stanceImageSequence;
	}
	
	public Bitmap getImage(int action, int facingDirection, int index) {
		switch (action) {
		case GameConstants.GO_LEFT:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = walkRightImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = walkLeftImageSequence;
			}
			break;
		case GameConstants.GO_RIGHT:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = walkLeftImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = walkRightImageSequence;
			}
			break;
		case GameConstants.JUMP:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = stanceImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = stanceImageSequence;
			}
			break;
		case GameConstants.DUCK:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = stanceImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = stanceImageSequence;
			}
			break;
		case GameConstants.PUNCH:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = punchImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = punchImageSequence;
			}
			break;
		case GameConstants.STAND_STILL:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = stanceImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = stanceImageSequence;
			}
			break;
		case GameConstants.GOT_BUMPED:
			if (facingDirection == GameConstants.FACING_LEFT) {
				currentImageSequence = gotHeadPunchedImageSequenceFacingLeft;
			}
			else {
				currentImageSequence = gotHeadPunchedImageSequence;
			}
			break;
		}
		return image = currentImageSequence[index];
	}
}
