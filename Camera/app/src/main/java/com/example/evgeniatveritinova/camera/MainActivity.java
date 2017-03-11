package com.example.evgeniatveritinova.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
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

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private static final String TAG = "CameraActivity";

    static final int REQUEST_CAMERA_PERMISSION = 0;

    Camera mCamera = null;
    int mCameraId = -1;

    private SurfaceView mSurfaceView;

    private boolean mIsSurfaceCreated;

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
        mIsSurfaceCreated = true;
        tryStartCameraPreview();
    }

    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width,
            final int height) {
        Log.d(TAG, "surfaceChanged");
        if (mCamera != null) {
            mCamera.stopPreview();
            updateCameraRotation();
            tryStartCameraPreview();
        }
    }

    private boolean tryStartCameraPreview() {
        if (mCamera != null && mIsSurfaceCreated) {
            try {
                mCamera.setPreviewDisplay(mSurfaceView.getHolder());
                mCamera.startPreview();
                setupPreviewCallback(mCamera);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void updateCameraRotation() {

        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
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

        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId, cameraInfo);
        mCamera.setDisplayOrientation((cameraInfo.orientation - degrees + 360) % 360);
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
        mIsSurfaceCreated = false;
    }

    @Override
    public void onPreviewFrame(final byte[] data, final Camera camera) {
        if (mCamera == null) {
            return;
        }
        Camera.Size camera_size = mCamera.getParameters().getPreviewSize();

        // Y stands for the brightness and U and V are color components
        int ind = 0;
        int maxBrightness = 0;
        int size = camera_size.height * camera_size.width;
        for (int i = 0; i < size; ++i) {
            if (data[i] > maxBrightness) {
                maxBrightness = data[i];
                ind = i;
            }
        }
        Log.d(TAG, "BRIGHTEST PIXEL AT (" + ind % camera_size.width + "," + ind / camera_size.width + ")");

        camera.addCallbackBuffer(data);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSurfaceView = (SurfaceView) findViewById(R.id.camera_view);
        mSurfaceView.getHolder().addCallback(this);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            onCameraAccessGranted();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        releaseCamera();
    }

    void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
            Log.d(TAG, "Camera is released");
        }
    }

    private void setupPreviewCallback(final Camera camera) {
        byte[] frameBuffer = new byte[calculateFrameBufferSize(camera)];
        camera.addCallbackBuffer(frameBuffer);
        camera.setPreviewCallbackWithBuffer(this);
        Log.d(TAG, "setupPreviewCallback: callback buffer is ready");
    }

    private int calculateFrameBufferSize(final Camera camera) {
        Camera.Size size = camera.getParameters().getPreviewSize();
        int bitsPerPixel = ImageFormat.getBitsPerPixel(ImageFormat.NV21);
        return size.height * size.width * bitsPerPixel / 8;
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode,
            @NonNull final String[] permissions,
            @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onCameraAccessGranted();
        } else {
            finish();
        }
    }

    private void onCameraAccessGranted() {
        for (int i = 0; i < Camera.getNumberOfCameras(); ++i) {
            try {
                mCamera = Camera.open();
                mCameraId = i;
                Log.d(TAG, "getCameraInstance: Camera is found and opened, id:" + mCameraId);
                break;
            } catch (Exception ignore) {
            }
        }
        if (mCamera == null) {
            finish();
        } else {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            mCamera.setParameters(parameters);
            updateCameraRotation();

            if (mIsSurfaceCreated) {
                tryStartCameraPreview();
            }
        }
    }
}