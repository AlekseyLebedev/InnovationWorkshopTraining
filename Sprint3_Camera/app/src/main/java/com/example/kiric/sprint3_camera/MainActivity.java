package com.example.kiric.sprint3_camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;

    private SurfaceHolder mHolder;

    private Camera mCamera;

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
        //mCamera = getCameraInstance();
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    protected void onResume() {
        super.onResume();
        mCamera = getCameraInstance();
        Log.d("Log", "установил фокус");
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            Log.d("Log", "surfaceCreated");
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
        }

        Camera.Parameters params = mCamera.getParameters();
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        params.setPreviewSize(720, 480);

        ViewGroup.LayoutParams mlayoutParams = mSurfaceView.getLayoutParams();
        if (this.getResources().getConfiguration().orientation
                != Configuration.ORIENTATION_LANDSCAPE) {
            // портретный вид
            mCamera.setDisplayOrientation(90);
            mlayoutParams.width = mSurfaceView.getWidth();
            mlayoutParams.height = mlayoutParams.width*720/480;
            Log.d("Log", mlayoutParams.width + " " + mlayoutParams.height)
            ;
        } else {
            // ландшафтный
            mlayoutParams.height = mSurfaceView.getHeight();
            mlayoutParams.width = mlayoutParams.height*720/480;
            Log.d("Log", mlayoutParams.width + " " + mlayoutParams.height);
        }
        mCamera.setParameters(params);
        mSurfaceView.setLayoutParams(mlayoutParams);
        mCamera.startPreview();
    }


    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

    }
}


