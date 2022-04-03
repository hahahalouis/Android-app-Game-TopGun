package com.example.asm2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FlyingPlaneView extends View {

    private Bitmap plane, bg,resize_bg,resize_plane;
    private int planeX = 10, planeY,planeSpeed;
    private int canvasWidth, canvasHeight;
    private boolean touchStatus = false;


    public FlyingPlaneView(Context context) {
        super(context);
        plane = BitmapFactory.decodeResource(getResources(), R.drawable.plane);
        resize_plane = Bitmap.createScaledBitmap(plane, 200, 200 ,false);
        planeY = 550;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        bg = BitmapFactory.decodeResource(getResources(),R.drawable.ingame_bg);
        resize_bg = Bitmap.createScaledBitmap(bg, canvas.getWidth(),canvas.getHeight(),false);
        canvas.drawBitmap(resize_bg,0,0,null);

        int minPlaneY = resize_plane.getHeight();
        int maxPlaneY = canvasHeight - resize_plane.getHeight() * 3;
        planeY = planeY + planeSpeed;

        if(planeY < minPlaneY)
        {
            planeY = minPlaneY;
        }
        if(planeY > maxPlaneY)
        {
            planeY = maxPlaneY;
        }
        planeSpeed = planeSpeed + 2;
        if(touchStatus)
        {
            canvas.drawBitmap(resize_plane,planeX,planeY,null);
            touchStatus = false;
        }else
        {
            canvas.drawBitmap(resize_plane,planeX,planeY,null);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchStatus = true;

            planeSpeed = -22;
        }

        return super.onTouchEvent(event);
    }
}
