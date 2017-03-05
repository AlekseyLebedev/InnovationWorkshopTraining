package com.example.evgeniatveritinova.task3;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    private Fragment firstFragment;
    private Fragment secondFragment;

    public Fragment getFirstFragment() {
        return firstFragment;
    }

    public Fragment getSecondFragment() {
        return secondFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();

        firstFragment = fragmentManager.findFragmentById(R.id.first_fragment);
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }

        secondFragment = fragmentManager.findFragmentById(R.id.second_fragment);
        if (secondFragment == null) {
            secondFragment = new SecondFragment();
        }

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout, firstFragment);
            fragmentTransaction.commit();
        }
    }
}
