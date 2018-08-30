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

public class ViewOfGameStatus extends View {

	public ViewOfGameStatus(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint p = new Paint();
		p.setTextSize(40);
		canvas.drawText("VS", (192/2)-10, 40, p);
		
		p.setTextSize(25);
		canvas.drawText("Round 1", (192/2)-25, 70, p);
	}

}

