package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import com.arellomobile.mvp.MvpView;

public interface SplashScreenView extends MvpView {

    void substituteWithAnotherActivity(Class anotherActivityClass);
}
