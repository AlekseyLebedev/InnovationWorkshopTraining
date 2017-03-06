package com.rtrsdk.arsentii.mvptask.presentation.presenter;


import com.rtrsdk.arsentii.mvptask.Person;
import com.rtrsdk.arsentii.mvptask.presentation.view.Activity3View;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class Activity3Presenter extends MvpPresenter<Activity3View> {


    public Activity3Presenter() {}

    public void onReceiveNameAndButtonClick(Person who) {
        getViewState().startActivity2(who);
    }
}
