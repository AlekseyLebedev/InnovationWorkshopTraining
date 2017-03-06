package com.example.evgeniatveritinova.livetemplates.presentation.view.blank;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SplashScreenView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void changeActivity(Class anotherActivityClass);
}
