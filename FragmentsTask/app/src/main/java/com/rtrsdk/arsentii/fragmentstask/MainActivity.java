package com.rtrsdk.arsentii.fragmentstask;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    private static enum FragmentType {
        Fragment1, Fragment2;

        public static FragmentType getType(String type) {
            if (Fragment1.toString().equals(type)) {
                return Fragment1;
            }
            if (Fragment2.toString().equals(type)) {
                return Fragment2;
            }
            throw new RuntimeException("Wrong Argument");
        }
    }

    private static final String FRAGMENT_PARAM = "fragment_param";

    private FragmentType fragmentToStartFirst = FragmentType.Fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            fragmentToStartFirst = FragmentType.getType(savedInstanceState.getString(FRAGMENT_PARAM));
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;
        switch (fragmentToStartFirst) {
            case Fragment1: fragment = Fragment1.newInstance(); break;
            case Fragment2: fragment = Fragment2.newInstance(); break;
        }

        fragmentTransaction.add(R.id.activity_main, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_PARAM, fragmentToStartFirst.toString());
        super.onSaveInstanceState(outState);
    }
}
