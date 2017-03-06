package com.example.evgeniatveritinova.livetemplates.presentation.view.blank;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showHelloMessage(String firstName, String secondName);
}
