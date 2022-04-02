package com.example.asm2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class FlyingPlaneView extends View {

    private Bitmap plane, bg;

    public FlyingPlaneView(Context context) {
        super(context);
        plane = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
        bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingame_bg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bg,0,0,null);
        canvas.drawBitmap(plane,0,0,null);

    }
}
