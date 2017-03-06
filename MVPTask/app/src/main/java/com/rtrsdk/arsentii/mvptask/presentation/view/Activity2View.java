package com.rtrsdk.arsentii.mvptask.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.rtrsdk.arsentii.mvptask.Person;

public interface Activity2View extends MvpView {

    public void printName(final Person who);

    public void startActivity3();

}
