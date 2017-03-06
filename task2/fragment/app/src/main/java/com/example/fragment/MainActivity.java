package com.example.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends FragmentActivity {
    private static final String FIRST_FRAGMENT_TAG = "first_fragment";
    private static final String SECOND_FRAGMENT_TAG = "second_fragment";
    private android.app.Fragment mFirstFragment;
    private android.app.Fragment mSecondFragment;

    public android.app.Fragment getFirstFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment result = fragmentManager.findFragmentByTag(FIRST_FRAGMENT_TAG);
        if (result == null) {
            result = new FirstFragmentActivity();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(result, FIRST_FRAGMENT_TAG).commit();
        }
        return result;
    }

    public android.app.Fragment getSecondFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment result = fragmentManager.findFragmentByTag(SECOND_FRAGMENT_TAG);
        if (result == null) {
            result = new SecondFragmentActivity();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(result, SECOND_FRAGMENT_TAG).commit();
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main, getFirstFragment());
        transaction.addToBackStack(null);
        transaction.commit();

    }





}
