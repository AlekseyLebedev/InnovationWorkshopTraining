package com.example.evgeniatveritinova.livetemplates.ui.activity.blank;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.evgeniatveritinova.livetemplates.R;
import com.example.evgeniatveritinova.livetemplates.presentation.presenter.blank.SplashScreenPresenter;
import com.example.evgeniatveritinova.livetemplates.presentation.view.blank.SplashScreenView;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends MvpAppCompatActivity implements SplashScreenView {

    @InjectPresenter
    SplashScreenPresenter mSplashScreenPresenter;

    @Override
    public void changeActivity(final Class anotherActivityClass) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
}
