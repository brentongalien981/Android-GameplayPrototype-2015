package com.example.screenformaingameplayv3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;

public class PlayerView extends View {
	Paint p;
	Bitmap image;
	int facingDirection;

	public PlayerView(Context context) {
		super(context);
		p = new Paint();
		image = null;
		facingDirection = -1;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    //MUST CALL THIS
	    setMeasuredDimension(300, 200);
	}	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (image != null) {
			if (facingDirection == GameConstants.FACING_RIGHT) {
				canvas.drawBitmap(image, 100, 0, p);
			}
			else { // facing left
				canvas.drawBitmap(image, -30, 0, p);
			}
		}
	}
	
	public void updateImage(Bitmap image, int facingDirection) {
		this.image = image;
		this.facingDirection = facingDirection;
		invalidate();
	}
}
