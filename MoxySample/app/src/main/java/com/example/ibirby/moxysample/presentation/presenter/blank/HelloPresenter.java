package com.example.ibirby.moxysample.presentation.presenter.blank;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ibirby.moxysample.R;
import com.example.ibirby.moxysample.presentation.view.blank.HelloView;
import com.example.ibirby.moxysample.ui.activity.blank.NameActivity;

@InjectViewState
public class HelloPresenter extends MvpPresenter<HelloView> {

    public HelloPresenter() {
        updateName("", "");
    }

    public void click() {
        getViewState().showNextActivity(NameActivity.class);
    }

    public void updateName(String firstName, String lastName) {
        getViewState().setNameText(R.string.hello, firstName, lastName);
    }
}
