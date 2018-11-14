package com.lingjie.quicksearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class QuickIndex extends View {
	private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	private Paint paint;
	private int width;
	private float cellHeight;
	public onTouchLetterListener letterListener;
	public QuickIndex(Context context) {
		super(context);
		init();
	}

	public QuickIndex(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public QuickIndex(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = getMeasuredWidth();
		cellHeight = getMeasuredHeight() / indexArr.length;
	}

	private void init() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);// 抗锯齿
		paint.setColor(Color.GRAY);
		paint.setTextSize(20);
		// paint.setTextAlign(Align.CENTER);//设置文本的起点是文字边框底边的中心

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < indexArr.length; i++) {
			float x = width / 2;
			int textHeight = getTextHeight(indexArr[i]);
			float y = cellHeight / 2 + textHeight / 2 + i * (float) cellHeight;
			paint.setColor(lastIndex==i?Color.RED:Color.GRAY);
			canvas.drawText(indexArr[i], x, y, paint);
		}

	}

	private int getTextHeight(String text) {
		// 获取文本高度
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
	private int lastIndex = -1;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			float y = event.getY();
			int index = (int) (y/cellHeight);
			if (lastIndex != index) {
				//Log.e("tag", indexArr[index]);
				if (letterListener != null) {
					letterListener.onTouchListener(indexArr[index]);
				}
			}
			lastIndex = index;
			break;
		case MotionEvent.ACTION_UP:
			lastIndex = -1;
			break;
		default:
			break;
		}
		invalidate();
		return super.onTouchEvent(event);
	}
	
	public void setOnTouchLetterListener(onTouchLetterListener listener){
		this.letterListener = listener;
	}
	
	public interface onTouchLetterListener{
		void onTouchListener(String Letter);
	}
}
