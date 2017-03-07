package ru.mipt.diht.maximdankovtsev.cameraexample;

import android.Manifest;
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

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String TAG = "CameraActivity";

    // Код для создания запроса на получение доступа к камере
    static final int REQUEST_CAMERA_PERMISSION = 0;

    // Размеры изображения указанные в задании
    private static final int PREVIEW_WIDTH = 1280;

    private static final int PREVIEW_HEIGHT = 720;


    Camera mCamera;

    // Системный id камеры, используется для получения CameraInfo для определения rotation
    int mCameraId;

    private SurfaceView mSurfaceView;

    //todo: похоже этот флаг можно заменить проверкой == null у Surface
    private boolean mIsSurfaceCreated;

    // Сама Surface доступна только между событиями surfaceCreated и surfaceDestroyed.
    // Поэтому необходимо начинать camera.cameraPreview в момент после создания surface.
    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
        mIsSurfaceCreated = true;
        tryStartCameraPreview();
    }

    // При повороте поверхности, необходимо сменить ориентацию изображения с камеры
    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width,
            final int height) {
        Log.d(TAG, "surfaceChanged");
        if (mCamera != null) {
            mCamera.stopPreview();
            updateCameraRotation(mCamera);
            tryStartCameraPreview();
        }
    }

    private boolean tryStartCameraPreview() {
        Log.d(TAG, "tryStartCameraPreview");
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

    private void updateCameraRotation(Camera camera) {
        // Получение ориентации устройства
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
        // Получение абсолютного поворота камеры относительно натурального положения экрана
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId, cameraInfo);
        int initialCameraRotation = cameraInfo.orientation;
        camera.setDisplayOrientation((initialCameraRotation - degrees + 360) % 360);
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
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
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        // Получаем доступ к камере, после чего будет инициализирована камера.
        ensureCameraIsPermitted();
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

    // Получение и настройка CameraWithId
    CameraWithId initializeCamera() {
        CameraWithId cameraWithId = getCameraInstance();
        Camera newCamera = cameraWithId.camera();

        // Настройка режима фокусировки и размера изображения
        assert newCamera != null;
        Camera.Parameters parameters = newCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setPreviewSize(PREVIEW_WIDTH, PREVIEW_HEIGHT);
        newCamera.setParameters(parameters);
        updateCameraRotation(newCamera);
        Log.d(TAG, "initializeCamera: parameters are set");
        return cameraWithId;
    }

    // Пытается найти и открыть первую доступную камеру.
    // Возвращает камеру и ее id.
    private CameraWithId getCameraInstance() {
        // Поиск нужен чтобы получить id камеры, иначе невозможно никак
        // Camera Id нужен для коректной обработки поворотов
        Camera newCamera = null;
        int newCameraId = -1;
        for (int i = 0; i < Camera.getNumberOfCameras(); ++i) {
            try {
                newCamera = Camera.open();
                newCameraId = i;
                Log.d(TAG, "getCameraInstance: Camera is found and opened, id:" + newCameraId);
                break;
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        }
        return new CameraWithId(newCamera, newCameraId);
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

    // Когда гарантированно получены права доступа к камере, можно открыть и настроить ее,
    // и, если целевая поверхность была создана раньше камеры, то присоеденить новую камеру к ней.
    private void onCameraAccessGranted() {
        CameraWithId cameraWithId = initializeCamera();
        mCamera = cameraWithId.camera();
        mCameraId = cameraWithId.id();
        if (mIsSurfaceCreated) {
            tryStartCameraPreview();
        }
    }

    // Класс объединяет в себе Camera и ее системный Id.
    // Это требуется, потому что не существует способа по уже созданному объекту Camera
    // получить ее Id(который в свою очередь требуется для обработки повоторов камеры).
    private static class CameraWithId {

        private Camera mCamera;

        private int mId;

        Camera camera() {
            return mCamera;
        }

        int id() {
            return mId;
        }

        CameraWithId(final Camera camera, final int id) {
            mCamera = camera;
            mId = id;
        }
    }
}
