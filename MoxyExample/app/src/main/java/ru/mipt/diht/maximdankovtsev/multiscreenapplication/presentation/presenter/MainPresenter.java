package ru.mipt.diht.maximdankovtsev.multiscreenapplication.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.Person;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.presentation.view.MainActivityView;

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
