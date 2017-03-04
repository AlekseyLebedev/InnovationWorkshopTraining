package com.example.ibirby.moxysample.presentation.presenter.blank;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ibirby.moxysample.presentation.view.blank.NameView;

@InjectViewState
public class NamePresenter extends MvpPresenter<NameView> {

    public void updateName(String firstName, String lastName) {

    }
}
