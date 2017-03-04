package ru.mipt.diht.maximdankovtsev.multiscreenapplication.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.Person;

public interface MainActivityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showHelloMessage(final Person person);
}
