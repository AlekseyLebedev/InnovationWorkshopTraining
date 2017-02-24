package com.example.ibirby.fragmenttask;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "myLogs";
    private static final String TEXT_FRAGMENT = "TEXT_EDIT";
    private static final String SPIN_FRAGMENT = "SPINNER";

    private RelativeLayout mRelativeLayout;
    private FragmentManager mFragmentManager;
    private TextEditFragment mTextFragment;
    private SpinnerFragment mSpinFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.mainRoot);
        mRelativeLayout.setBackgroundColor(Color.BLACK);
        Log.d(LOG_TAG, "Создал layout, id = " + mRelativeLayout.getId());

        mFragmentManager = getSupportFragmentManager();
        Log.d(LOG_TAG, "Получили FragmentManager");

        mSpinFragment = (SpinnerFragment) mFragmentManager
                .findFragmentByTag(SPIN_FRAGMENT);
        if (mSpinFragment == null) {
            mSpinFragment = new SpinnerFragment();
            Log.d(LOG_TAG, "Создал новый SpinFragment");
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(mRelativeLayout.getId(), mSpinFragment, SPIN_FRAGMENT);
            transaction.commit();
            Log.d(LOG_TAG, "Закоммитил SpinFragment");
        } else {
            Log.d(LOG_TAG, "Достал сохраненный SpinFragment");
        }

        mTextFragment = (TextEditFragment) mFragmentManager
                .findFragmentByTag(TEXT_FRAGMENT);
        if (mTextFragment == null) {
            mTextFragment = new TextEditFragment();
            Log.d(LOG_TAG, "Создал новый TextFragment");
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(mRelativeLayout.getId(), mTextFragment, TEXT_FRAGMENT);
            transaction.commit();
            Log.d(LOG_TAG, "Закоммитил TextFragment");
        } else {
            Log.d(LOG_TAG, "Достал сохраненный TextFragment");
        }
    }

    public static class TextEditFragment extends Fragment {
        public TextEditFragment() {}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(LOG_TAG, "Создали TextFragmentView");
            View view = inflater.inflate(R.layout.fragment_layout, container, false);
            LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.frRoot);
            view.setBackgroundColor(Color.RED);

            EditText mEditText = new EditText(getActivity());
            mEditText.setId(R.id.my_text);
            mLinearLayout.addView(mEditText);

            Button mButton = new Button(getActivity());
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG, "Переходим в Spin");
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    SpinnerFragment spinFragment = (SpinnerFragment) manager
                            .findFragmentByTag(SPIN_FRAGMENT);
                    TextEditFragment textFragment = (TextEditFragment) manager
                            .findFragmentByTag(TEXT_FRAGMENT);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(textFragment.getId(), spinFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });
            mLinearLayout.addView(mButton);

            setRetainInstance(true);
            return view;
        }
    }

    public static class SpinnerFragment extends Fragment {

        public SpinnerFragment() {}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(LOG_TAG, "Создали SpinFragmentView");
            View view = inflater.inflate(R.layout.fragment_layout, container, false);
            LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.frRoot);
            view.setBackgroundColor(Color.YELLOW);

            Spinner mSpinner = new Spinner(getActivity());
            mSpinner.setId(R.id.my_spin);
            ArrayList<String> spinnerArray = new ArrayList<String>();
            spinnerArray.add("one");
            spinnerArray.add("two");
            spinnerArray.add("three");
            spinnerArray.add("four");
            spinnerArray.add("five");
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item, spinnerArray);
            mSpinner.setAdapter(spinnerArrayAdapter);

            mLinearLayout.addView(mSpinner);

            Button mButton = new Button(getActivity());
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG, "Переходим в Text");
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    SpinnerFragment spinFragment = (SpinnerFragment) manager
                            .findFragmentByTag(SPIN_FRAGMENT);
                    TextEditFragment textFragment = (TextEditFragment) manager
                            .findFragmentByTag(TEXT_FRAGMENT);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(spinFragment.getId(), textFragment);
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
