package ru.mipt.diht.maximdankovtsev.multiscreenapplication.splashscreen;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.content.Intent;
import android.os.Bundle;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.MainActivity;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.R;

public class SplashScreenActivity extends MvpActivity implements SplashScreenView {

    @InjectPresenter
    SplashScreenPresenter mSplashScreenPresenter;

    @Override
    public void substituteWithAnotherActivity(final Class anotherActivityClass) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }
}
