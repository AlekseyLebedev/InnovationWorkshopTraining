package ru.mipt.diht.maximdankovtsev.cameraexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    // Код для создания запроса на получение доступа к камере
    static final int REQUEST_CAMERA_PERMISSION = 0;

    // Размеры изображения указанные в задании
    private static final int PREVIEW_WIDTH = 1280;

    private static final int PREVIEW_HEIGHT = 720;

    Camera mCamera;

    private SurfaceView mSurfaceView;

    private boolean mIsSurfaceCreated;

    // Сама Surface доступна только между событиями surfaceCreated и surfaceDestroyed.
    // Поэтому необходимо начинать camera.cameraPreview в момент после создания surface.
    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        mIsSurfaceCreated = true;
        tryStartCameraPreview();
    }


    private boolean tryStartCameraPreview() {
        if (mCamera != null && mIsSurfaceCreated) {
            try {
                mCamera.setPreviewDisplay(mSurfaceView.getHolder());
                mCamera.startPreview();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // При повороте поверхности, необходимо сменить ориентацию изображения с камеры
    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width,
            final int height) {
        mCamera.stopPreview();
        updateCameraRotation();
        tryStartCameraPreview();

    }

    private void updateCameraRotation() {
        // Получение ориентации устройства
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 90;
                break;
            case Surface.ROTATION_90:
                degrees = 0;
                break;
            case Surface.ROTATION_180:
                degrees = 0;
                break;
            case Surface.ROTATION_270:
                degrees = 180;
                break;
        }

        mCamera.setDisplayOrientation(degrees);
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {
        mIsSurfaceCreated = false;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mSurfaceView.getHolder().addCallback(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        // Получаем доступ к камере, после чего будет инициализирована камера.
        ensureCameraIsPermitted();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseCamera();
    }

    // Проверяет разрешен ли доступ к камере, или запрашивает доступ в противном случае
    private void ensureCameraIsPermitted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            onCameraAccessGranted();
        } else {
            requestCameraPermission();
        }
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA_PERMISSION);
    }

    // Когда гарантированно получены права доступа к камере, можно открыть и настроить ее,
    // и, если целевая поверхность была создана раньше камеры, то присоеденить новую камеру к ней.
    private void onCameraAccessGranted() {
        mCamera = initializeCamera();
        if (mIsSurfaceCreated) {
            tryStartCameraPreview();
        }
    }

    Camera initializeCamera() {
        // Открыть камеру
        Camera newCamera = null;
        try {
            newCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
        assert newCamera != null;
        // Настройка режима фокусировки и размера изображения
        Camera.Parameters parameters = newCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setPreviewSize(PREVIEW_WIDTH, PREVIEW_HEIGHT);
        newCamera.setParameters(parameters);
        return newCamera;
    }

    void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }
}
