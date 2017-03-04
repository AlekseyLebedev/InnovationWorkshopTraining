package com.example.ibirby.moxysample.presentation.view.blank;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SplashView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showNextActivity(Class activity);
}
