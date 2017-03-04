package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreenActivity extends MvpActivity implements SplashScreenView {

    @InjectPresenter
    SplashScreenPresenter splashScreenPresenter;

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
