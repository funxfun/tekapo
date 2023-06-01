package jp.funx.tekapo;

import android.R.color;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class PINActivity extends Activity {
    final String TAG = "PINActivity";

    public TextView textViewBitte;
    public TextView textViewStatus;

    public ImageView imageViewPin1;
    public ImageView imageViewPin2;
    public ImageView imageViewPin3;
    public ImageView imageViewPin4;

    public int counter;
    public boolean isDigit;

    @Override
    public void onStart(){
        Log.d(TAG,"onStart()");
        super.onStart();
//        MainActivity.active = true; // TODO: not sure if should comment out
        textViewStatus.setText("");
        imageViewPin1.setImageDrawable(getDrawable(R.drawable.pin_empty));
        imageViewPin2.setImageDrawable(getDrawable(R.drawable.pin_empty));
        imageViewPin3.setImageDrawable(getDrawable(R.drawable.pin_empty));
        imageViewPin4.setImageDrawable(getDrawable(R.drawable.pin_empty));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");

        textViewBitte = findViewById(R.id.textViewBitte);
        textViewStatus = findViewById(R.id.textViewStatus);

        imageViewPin1 = findViewById(R.id.imageViewPin1);
        imageViewPin2 = findViewById(R.id.imageViewPin2);
        imageViewPin3 = findViewById(R.id.imageViewPin3);
        imageViewPin4 = findViewById(R.id.imageViewPin4);

        counter = 1;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        Log.d(TAG, "onKeyUp() = " + keyCode);
//        if ( keyCode == KeyEvent.KEYCODE_S || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY ){
//            if ( !isServiceRunning(Service.class) ) {
//                Intent intent = new Intent(this, Service.class);
//                startService(intent);
//                Toast.makeText(this, "startService(intent)", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(this, "service already started", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
//        if ( keyCode == KeyEvent.KEYCODE_B || keyCode == KeyEvent.KEYCODE_MEDIA_NEXT ){
//            if (!mBound) {
//                // Bind to Service
//                Intent intent = new Intent(this, Service.class);
//                bindService(intent, connection, Context.BIND_AUTO_CREATE);
//                Toast.makeText(this, "bindService()", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(this, "already bound", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
        if ( keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_R ){
            return true;
        }

        if ( counter == 1 )
        {
            imageViewPin1.setImageDrawable(getDrawable(R.drawable.pin_filled));
            imageViewPin2.setImageDrawable(getDrawable(R.drawable.pin_empty));
            imageViewPin3.setImageDrawable(getDrawable(R.drawable.pin_empty));
            imageViewPin4.setImageDrawable(getDrawable(R.drawable.pin_empty));
            textViewStatus.setText("");
            if ( keyCode == KeyEvent.KEYCODE_6 )
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is correct");
                isDigit = true;
            }
            else
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is wrong");
                isDigit = false;
            }
        }
        if ( counter == 2 )
        {
            imageViewPin2.setImageDrawable(getDrawable(R.drawable.pin_filled));
            if ( keyCode == KeyEvent.KEYCODE_6 )
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is correct");
            }
            else
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is wrong");
                isDigit = false;
            }
        }
        if ( counter == 3 )
        {
            imageViewPin3.setImageDrawable(getDrawable(R.drawable.pin_filled));
            if ( keyCode == KeyEvent.KEYCODE_8 )
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is correct");
            }
            else
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is wrong");
                isDigit = false;
            }
        }
        if ( counter == 4 )
        {
            imageViewPin4.setImageDrawable(getDrawable(R.drawable.pin_filled));
            if ( keyCode == KeyEvent.KEYCODE_3 )
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is correct");
                if (isDigit) {
                    Log.d(TAG, "onKeyUp() - PIN IS CORRECT!!!");
//                    if (mBound) {
//                        Log.d(TAG, "setIsPIN(true)");
//                        mService.setIsPIN(true);
//                    }
//                    else
//                    {
//                        Log.d(TAG, "cannot setIsPIN(true) - service not bound");
//                        Toast.makeText(this, "cannot setIsPIN(true) - service not bound", Toast.LENGTH_SHORT).show();
//                    }
                    textViewStatus.setText(getString(R.string.TextOkPIN));
                    textViewStatus.setTextColor(getResources().getColor(color.white));
                    textViewBitte.setTextColor(getResources().getColor(color.white));

                    //PINActivity.super.moveTaskToBack(true);
                    Intent data = new Intent();
                    data.putExtra("isPIN", true);
                    setResult(RESULT_OK, data);
                    finish();
                }
                else
                {
                    Log.d(TAG, "onKeyUp() - PIN IS WRONG!!!");
                    textViewStatus.setText(getString(R.string.TextWrongPIN));
                    textViewStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    textViewBitte.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    imageViewPin1.setImageDrawable(getDrawable(R.drawable.pin_empty));
                    imageViewPin2.setImageDrawable(getDrawable(R.drawable.pin_empty));
                    imageViewPin3.setImageDrawable(getDrawable(R.drawable.pin_empty));
                    imageViewPin4.setImageDrawable(getDrawable(R.drawable.pin_empty));
                }
            }
            else
            {
                Log.d(TAG, "onKeyUp() - " + counter + ". digit is wrong");
                isDigit = false;
                Log.d(TAG, "onKeyUp() - PIN IS WRONG!!!");
                textViewStatus.setText(getString(R.string.TextWrongPIN));
                textViewStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                textViewBitte.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                imageViewPin1.setImageDrawable(getDrawable(R.drawable.pin_empty));
                imageViewPin2.setImageDrawable(getDrawable(R.drawable.pin_empty));
                imageViewPin3.setImageDrawable(getDrawable(R.drawable.pin_empty));
                imageViewPin4.setImageDrawable(getDrawable(R.drawable.pin_empty));
            }
            counter = 0;
        }
        counter++;
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        Log.d(TAG, "onKeyDown() = " + keyCode);
        return true;
    }
}
