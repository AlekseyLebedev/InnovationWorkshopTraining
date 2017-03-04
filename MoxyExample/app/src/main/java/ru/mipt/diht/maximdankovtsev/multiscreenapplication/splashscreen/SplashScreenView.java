package ru.mipt.diht.maximdankovtsev.multiscreenapplication.splashscreen;

import com.arellomobile.mvp.MvpView;

public interface SplashScreenView extends MvpView {

    // Запустить другую активность и завершить финишировать
    void substituteWithAnotherActivity(Class anotherActivityClass);
}
