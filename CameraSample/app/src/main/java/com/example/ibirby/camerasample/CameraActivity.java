package com.example.ibirby.camerasample;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.Manifest.permission.CAMERA;

public class CameraActivity extends AppCompatActivity {

    private static final int REQUEST = 112;
    private static final String TAG = "CameraActivity";
    private SurfaceView mSurfaceView;
    private Camera mCamera;
    Camera.CameraInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Log.d(TAG, "onCreate: start");
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated: on");
            }

            @Override
            public void surfaceChanged(final SurfaceHolder holder, final int format,
                    final int width, final int height) {
                Log.d(TAG, "surfaceChanged: on");
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {
                    mCamera.stopPreview();
                    try {
                        setCameraOrientation();
                        mCamera.setPreviewDisplay(holder);
                        mCamera.startPreview();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void surfaceDestroyed(final SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed: on");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: start");
        if (mCamera == null) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) !=
                    PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onResume: start getting Permission");
                ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST);
                Log.d(TAG, "onResume: get Permission");
            } else {
                Log.d(TAG, "onResume: permission already granted");
                initializeCamera(0);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mCamera != null) {
            Log.d(TAG, "onPause: release Camera");
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void initializeCamera(Integer i) {
        Log.d(TAG, "initializeCamera: start with " + Camera.getNumberOfCameras());
        mCamera = Camera.open(i);
        Log.d(TAG, "initializeCamera: open Camera " + i);
        Camera.Parameters params = mCamera.getParameters();
        Log.d(TAG, "initializeCamera: get Params");
        params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(params);
        Log.d(TAG, "initializeCamera: set Params");
        mInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, mInfo);
        mCamera.setDisplayOrientation(mInfo.orientation);
        Log.d(TAG, "initializeCamera: set Orientation");
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode,
            @NonNull final String[] permissions,
            @NonNull final int[] grantResults) {
        switch (requestCode) {
            case REQUEST:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initializeCamera(0);
                    try {
                        mCamera.setPreviewDisplay(mSurfaceView.getHolder());
                        mCamera.startPreview();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
                break;
            }
        }
    }

    private void setCameraOrientation(){
        int rotation = getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (mInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (mInfo.orientation + degrees) % 360;
            result = (360 - result) % 360;
        } else {
            result = (mInfo.orientation - degrees + 360) % 360;
        }
        mCamera.setDisplayOrientation(result);
    }
}
