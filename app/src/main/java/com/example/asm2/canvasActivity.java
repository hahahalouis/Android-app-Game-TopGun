package com.example.asm2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kyanogen.signatureview.SignatureView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import yuku.ambilwarna.AmbilWarnaDialog;

public class canvasActivity extends AppCompatActivity{

    SignatureView signatureView;
    ImageView imgEraser , imgColor, imgSave;
    SeekBar seekBar;
    TextView txtPenSize;
    int defaultColor;

    ImageView canvas_iv;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint, cpaint, clearPaint;
    Button canvas_btn;
    //    OutputStream outputStream;
    float downx = 0, downy = 0, upx = 0, upy = 0;
    float lastx, lasty;
    Boolean imgSaved=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        Display currentDisplay = getWindowManager().getDefaultDisplay();
        float dw = currentDisplay.getWidth();
        float dh = currentDisplay.getHeight();

//        bitmap = Bitmap.createBitmap((int)dw,(int)dh, Bitmap.Config.ARGB_8888);
//        canvas = new Canvas(bitmap);
//        paint = new Paint();
//        cpaint = new Paint();
//        paint.setStrokeWidth(20);
//        paint.setColor(Color.BLACK);
//        cpaint.setStrokeWidth(10);
//        cpaint.setColor(Color.RED);
//        canvas_iv.setImageBitmap(bitmap);
//        canvas_iv.setOnTouchListener(this);

        clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        signatureView = findViewById(R.id.signature_view);
        seekBar = findViewById(R.id.penSize);
        txtPenSize = findViewById(R.id.txtPenSize);
        imgColor = findViewById(R.id.btnColor);
        imgEraser = findViewById(R.id.btnEraser);
        imgSave = findViewById(R.id.btnSave);

        defaultColor = ContextCompat.getColor(canvasActivity.this,R.color.black);

        imgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPenSize.setText(i+ "dp");
                signatureView.setPenSize(i);
                seekBar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        imgEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView.clearCanvas();
            }
        });

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!signatureView.isBitmapEmpty()){
                    saveimg();
                }
            }
        });
    }

    private void saveimg() {
        bitmap = signatureView.getSignatureBitmap();
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                signatureView.setDrawingCacheEnabled(true);
                setImg();
                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG);
                    savedToast.show();
                }
                else {
                    Toast unSaved = Toast.makeText(getApplicationContext(), "Error, the imgae not saved",Toast.LENGTH_LONG);
                    unSaved.show();
                }
                signatureView.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        saveDialog.show();
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

    private void openColorPicker(){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor= color;
                signatureView.setPenColor(color);
            }
        });
        ambilWarnaDialog.show();
    }


//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                downx = event.getX();
//                downy = event.getY();
//                lastx = downx;
//                lasty = downy;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                upx = event.getX();
//                upy = event.getY();
//                canvas.drawLine(lastx, lasty,upx,upy, cpaint);
//                canvas_iv.invalidate();
//                lastx = upx;
//                lasty = upy;
//                break;
//        }
//
//        return true;
//    }




}
