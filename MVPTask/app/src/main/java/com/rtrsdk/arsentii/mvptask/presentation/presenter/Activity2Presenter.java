package com.rtrsdk.arsentii.mvptask.presentation.presenter;


import com.rtrsdk.arsentii.mvptask.Person;
import com.rtrsdk.arsentii.mvptask.presentation.view.Activity2View;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import android.os.Parcelable;

@InjectViewState
public class Activity2Presenter extends MvpPresenter<Activity2View> {


    public void onReceiveNameAndPrintIt(Parcelable arg) {
        Person who = null;

        if (arg != null) {
            who = (Person) arg;
        }

        if (who == null) {
            who = new Person("Anonymous", "Anonymous");
        }
        getViewState().printName(who);
    }

    public void onButtonClick() {
        getViewState().startActivity3();
    }

}
