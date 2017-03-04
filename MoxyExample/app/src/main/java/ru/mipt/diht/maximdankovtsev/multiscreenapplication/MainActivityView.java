package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import com.arellomobile.mvp.MvpView;

//todo : систематизировать классы как положено
//todo : add strategy
public interface MainActivityView extends MvpView {

    void showHelloMessage(final Person person);
}
