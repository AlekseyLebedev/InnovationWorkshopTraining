package com.example.ibirby.moxysample.presentation.view.blank;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface HelloView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setNameText(Integer s, String firstName, String lastName);

    @StateStrategyType(SkipStrategy.class)
    void showNextActivity(Class activity);
}
