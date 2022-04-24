package com.example.asm2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.UUID;

public class canvasActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener{

    ImageView canvas_iv;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint, cpaint, clearPaint;
    Button canvas_btn;
    //    OutputStream outputStream;
    float downx = 0, downy = 0, upx = 0, upy = 0;
    float lastx, lasty;
    Boolean imgSaved=null;

    private static int REQUEST_CODE = 100;
    ImageView bird;
    Button SaveImg;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        canvas_iv = findViewById(R.id.canvas_iv);
        canvas_btn = findViewById(R.id.canvas_btn);
        canvas_btn.setOnClickListener(this);

        Display currentDisplay = getWindowManager().getDefaultDisplay();
        float dw = currentDisplay.getWidth();
        float dh = currentDisplay.getHeight();

        bitmap = Bitmap.createBitmap((int)dw,(int)dh, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        cpaint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(Color.BLACK);
        cpaint.setStrokeWidth(10);
        cpaint.setColor(Color.RED);
        canvas_iv.setImageBitmap(bitmap);
        canvas_iv.setOnTouchListener(this);

        clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    private void setImg() {
        File fileSaveImage = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "plane_custom.jpg");
        try (FileOutputStream out = new FileOutputStream(fileSaveImage)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgSaved=true;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                lastx = downx;
                lasty = downy;
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                upy = event.getY();
                canvas.drawLine(lastx, lasty,upx,upy, cpaint);
                canvas_iv.invalidate();
                lastx = upx;
                lasty = upy;
                break;
        }

        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.canvas_btn:
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
                saveDialog.setTitle("Save?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        canvas_iv.setDrawingCacheEnabled(true);
                        setImg();
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG);
                            savedToast.show();
                        }
                        else {
                            Toast unSaved = Toast.makeText(getApplicationContext(), "Error, the imgae not saved",Toast.LENGTH_LONG);
                            unSaved.show();
                        }
                        canvas_iv.destroyDrawingCache();
                    }
                });
                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                saveDialog.show();
                break;

        }

    }


}
