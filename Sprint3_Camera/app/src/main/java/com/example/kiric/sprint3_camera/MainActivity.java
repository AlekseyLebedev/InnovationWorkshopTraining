package com.example.kiric.sprint3_camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;

    private SurfaceHolder mHolder;

    private Camera mCamera;

    private boolean isListenerExist = false;

    public static Camera getCameraInstance() {
        Camera mCamera = null;
        try {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
        } catch (Exception e) {
            Log.d("Log", e.toString());
        }
        return mCamera;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
            Log.d("Log", "запросил разрешение на камеру");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        if (!isListenerExist) {
            DisplayManager displayManager = (DisplayManager) getSystemService(DISPLAY_SERVICE);
            Log.d("Log", "-----------" + displayManager);
            if (displayManager != null) {
                isListenerExist = true;
                displayManager.registerDisplayListener(new DisplayManager.DisplayListener() {

                    @Override
                    public void onDisplayAdded(final int displayId) {
                        Log.d("Log", "onDisplayAdded");
                    }

                    @Override
                    public void onDisplayRemoved(final int displayId) {
                        Log.d("Log", "onDisplayRemoved");
                    }

                    @Override
                    public void onDisplayChanged(final int displayId) {
                        if (mCamera != null) {
                            mCamera.setDisplayOrientation(getRotation());
                        }
                        Log.d("Log", "onDisplayChanged-------------");
                    }
                }, null);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("ListenerExist", isListenerExist);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isListenerExist = savedInstanceState.getBoolean("ListenerExist");
    }

    protected void onResume() {
        super.onResume();
        Log.d("Log", "om Resume");
        mCamera = getCameraInstance();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Log", "on Pause");
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    int getRotation() {
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        Log.d("Log", "Get Rotation");
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        return ((90 - degrees + 360) % 360);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            Log.d("Log", "surfaceCreated");
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
        }

        Camera.Parameters params = mCamera.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        Log.d("Log", "установил фокус");
        params.setPreviewSize(720, 480);

        ViewGroup.LayoutParams mlayoutParams = mSurfaceView.getLayoutParams();
        if (this.getResources().getConfiguration().orientation
                != Configuration.ORIENTATION_LANDSCAPE) {
            // портретный вид
            mlayoutParams.width = mSurfaceView.getWidth();
            mlayoutParams.height = mlayoutParams.width * 720 / 480;
            Log.d("Log", mlayoutParams.width + " " + mlayoutParams.height)
            ;
        } else {
            // ландшафтный
            mlayoutParams.height = mSurfaceView.getHeight();
            mlayoutParams.width = mlayoutParams.height * 720 / 480;
            Log.d("Log", mlayoutParams.width + " " + mlayoutParams.height);
        }
        mCamera.setDisplayOrientation(getRotation());
        mCamera.setParameters(params);
        mSurfaceView.setLayoutParams(mlayoutParams);
        mCamera.startPreview();
    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("Log", "Surface destroyed");
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.d("Log", "Surface changed");
    }
}


