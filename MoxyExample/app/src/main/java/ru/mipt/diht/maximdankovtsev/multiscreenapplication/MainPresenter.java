package ru.mipt.diht.maximdankovtsev.multiscreenapplication;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import android.app.Activity;
import android.content.Intent;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity.getname.GetNameActivity;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainActivityView> {

    private Person person;

    public MainPresenter() {
        person = new Person();
        // Стандартное приветствие без имени
        getViewState().showHelloMessage(null);
    }

    public void onPersonChanged(final Person person) {
        this.person = person;
        getViewState().showHelloMessage(this.person);
    }

}
