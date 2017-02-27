package com.example.kiric.sprint2_task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FIRST_FRAGMENT = "FIRST";
    private static final String SECOND_FRAGMENT = "SECOND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        RelativeLayout mRelativeLayout = (RelativeLayout) findViewById(R.id.main_layout);

        FragmentManager mFragmentManager = getSupportFragmentManager();

        SecondFragment mSecondFragment = (SecondFragment) mFragmentManager.findFragmentByTag(SECOND_FRAGMENT);
        if (mSecondFragment == null) {
            mSecondFragment = new SecondFragment();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(mRelativeLayout.getId(), mSecondFragment, SECOND_FRAGMENT);
            transaction.commit();
        }

        FirstFragment mFirstFragment = (FirstFragment) mFragmentManager.findFragmentByTag(FIRST_FRAGMENT);
        if (mFirstFragment == null) {
            mFirstFragment = new FirstFragment();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(mRelativeLayout.getId(), mFirstFragment, FIRST_FRAGMENT);
            transaction.replace(mSecondFragment.getId(), mFirstFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public static class FirstFragment extends Fragment {
        public FirstFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_layout, container, false);
            LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_layout);

            EditText mEditText = new EditText(getActivity());
            mEditText.setId(R.id.text);
            mLinearLayout.addView(mEditText);

            Button mButton = new Button(getActivity());
            mButton.setText("Switch to the second fragment");
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    SecondFragment mSecondFragment = (SecondFragment) mFragmentManager.findFragmentByTag(SECOND_FRAGMENT);
                    FirstFragment mFirstFragment = (FirstFragment) mFragmentManager.findFragmentByTag(FIRST_FRAGMENT);
                    FragmentTransaction transaction = mFragmentManager.beginTransaction();
                    transaction.replace(mFirstFragment.getId(), mSecondFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });
            mLinearLayout.addView(mButton);

            setRetainInstance(true);
            return view;
        }
    }

    public static class SecondFragment extends Fragment {

        public SecondFragment() {}


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_layout, container, false);
            LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_layout);

            Spinner mSpinner = new Spinner(getActivity());
            mSpinner.setId(R.id.spin);
            ArrayList<String> spinnerArray = new ArrayList<String>();
            spinnerArray.add("-");
            spinnerArray.add("qwerty");
            spinnerArray.add("йцукен");
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item, spinnerArray);
            mSpinner.setAdapter(spinnerArrayAdapter);
            mLinearLayout.addView(mSpinner);

            Button mButton = new Button(getActivity());
            mButton.setText("Switch to the first fragment");
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    SecondFragment mSecondFragment = (SecondFragment) mFragmentManager.findFragmentByTag(SECOND_FRAGMENT);
                    FirstFragment mFirstFragment = (FirstFragment) mFragmentManager.findFragmentByTag(FIRST_FRAGMENT);
                    FragmentTransaction transaction = mFragmentManager.beginTransaction();
                    transaction.replace(mSecondFragment.getId(), mFirstFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
            mLinearLayout.addView(mButton);

            setRetainInstance(true);
            return view;
        }
    }
}