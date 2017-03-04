package ru.mipt.diht.maximdankovtsev.multiscreenapplication.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SplashScreenView extends MvpView {

    // Запустить другую активность и завершить финишировать
    @StateStrategyType(SkipStrategy.class)
    void substituteWithAnotherActivity(Class anotherActivityClass);
}
