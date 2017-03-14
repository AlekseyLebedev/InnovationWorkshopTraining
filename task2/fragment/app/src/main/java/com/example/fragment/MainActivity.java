package com.example.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {
    private static final String FIRST_FRAGMENT_TAG = "first_fragment";
    private static final String SECOND_FRAGMENT_TAG = "second_fragment";
    private static final String CURRENT_FRAGMENT_TAG = "current_fragment";
    private static final String DEPTH_TAG = "depth";

    private static final String TEXT_TAG = "fragment1_text";
    private static final String POSITION_TAG = "fragment2_position";

    private int mDepth;
    private String mCurrentFragment;

    private String mText;
    private int mPosition;

    private android.app.Fragment mFirstFragment;
    private android.app.Fragment mSecondFragment;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public android.app.Fragment getFragment(String tag) {
        FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment result = fragmentManager.findFragmentByTag(tag);
        if (result == null) {
            if (tag.equals(FIRST_FRAGMENT_TAG)) {
                result = new FirstFragment();
            } else {
                result = new SecondFragment();
            }

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(result, tag).commit();
        }
        return result;
    }

    public void replaceFragment() {
        if (mCurrentFragment.equals(FIRST_FRAGMENT_TAG)) {
            mCurrentFragment = SECOND_FRAGMENT_TAG;
        } else {
            mCurrentFragment = FIRST_FRAGMENT_TAG;
        }
        mDepth++;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main, getFragment(mCurrentFragment));
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CURRENT_FRAGMENT_TAG, mCurrentFragment);
        outState.putInt(DEPTH_TAG, mDepth);

        outState.putString(TEXT_TAG, mText);
        outState.putInt(POSITION_TAG, mPosition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentFragment = savedInstanceState.getString(CURRENT_FRAGMENT_TAG);
            mDepth = savedInstanceState.getInt(DEPTH_TAG);
            mText = savedInstanceState.getString(TEXT_TAG);
            mPosition = savedInstanceState.getInt(POSITION_TAG);
        } else {
            mCurrentFragment = FIRST_FRAGMENT_TAG;
            mDepth = 0;
            mText = "";
            mPosition = 2;
        }

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main, getFragment(mCurrentFragment));
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mDepth == 0) {
            super.onBackPressed();
            return;
        }
        mDepth -= 2;
        replaceFragment();
    }
}
