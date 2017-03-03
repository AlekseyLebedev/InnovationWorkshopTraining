package com.rtrsdk.arsentii.fragmentstask;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    public static enum FragmentType {
        Fragment1,
        Fragment2;

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

        Fragment fragment = null;
        switch (fragmentToStartFirst) {
            case Fragment1:
                fragment = fragmentManager.findFragmentByTag(FragmentType.Fragment1.toString());
                if (fragment == null) {
                    fragment = Fragment1.newInstance();
                }
                break;
            case Fragment2:
                fragment = fragmentManager.findFragmentByTag(FragmentType.Fragment2.toString());
                if (fragment == null) {
                    fragment = Fragment2.newInstance();
                }break;
        }

        Fragment currentFragment = fragmentManager.findFragmentById(R.id.activity_main);
        if (fragment == currentFragment) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (currentFragment == null) {
            fragmentTransaction.add(R.id.activity_main, fragment, fragmentToStartFirst.toString());
        } else {
            fragmentTransaction.replace(R.id.activity_main, fragment, fragmentToStartFirst.toString());
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.activity_main);
        if (currentFragment instanceof  Fragment1) {
            fragmentToStartFirst = FragmentType.Fragment1;
        } else if (currentFragment instanceof  Fragment2) {
            fragmentToStartFirst = FragmentType.Fragment2;
        } else {
            throw new RuntimeException("Wrong fragment type found!");
        }
        // fragmentToStartFirst = FragmentType.Fragment1;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(FRAGMENT_PARAM, fragmentToStartFirst.toString());
        super.onSaveInstanceState(outState);
    }
}
