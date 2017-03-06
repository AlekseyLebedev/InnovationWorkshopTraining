package com.example.evgeniatveritinova.livetemplates.presentation.presenter.blank;


import com.example.evgeniatveritinova.livetemplates.presentation.view.blank.MainView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private String firstName;
    private String secondName;

    public MainPresenter() {
        firstName = "";
        secondName = "";
        // Стандартное приветствие без имени
        getViewState().showHelloMessage(firstName, secondName);
    }

    public void onNamesChanged(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        getViewState().showHelloMessage(this.firstName, this.secondName);
    }

}
