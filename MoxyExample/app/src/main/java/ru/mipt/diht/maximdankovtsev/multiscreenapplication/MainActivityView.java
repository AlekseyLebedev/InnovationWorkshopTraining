package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

//todo : систематизировать классы как положено
public interface MainActivityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showHelloMessage(final Person person);
}
