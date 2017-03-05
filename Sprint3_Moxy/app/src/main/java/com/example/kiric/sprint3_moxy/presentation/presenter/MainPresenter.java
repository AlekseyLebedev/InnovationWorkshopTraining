package com.example.kiric.sprint3_moxy.presentation.presenter;


import com.example.kiric.sprint3_moxy.R;
import com.example.kiric.sprint3_moxy.presentation.view.MainView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.kiric.sprint3_moxy.ui.activity.MainActivity;
import com.example.kiric.sprint3_moxy.ui.activity.RequestActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    String name;
    public MainPresenter (){

    }
    public void saveResukt (String result){
        name = result;
    }
    public void showMessege (TextView mTextView){
        mTextView.setText("Hello " + name + "!");
    }
}
