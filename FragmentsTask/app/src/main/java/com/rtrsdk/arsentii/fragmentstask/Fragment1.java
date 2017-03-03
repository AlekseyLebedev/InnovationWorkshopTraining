package com.rtrsdk.arsentii.fragmentstask;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Fragment1 extends Fragment {
    private static final String TEXT_PARAM = "text_param";

    private String text = "Hi!";


    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            text = savedInstanceState.getString(TEXT_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        EditText editText = (EditText) view.findViewById(R.id.editText_fragment1);
        editText.setText(text);

        Button button = (Button) view.findViewById(R.id.button_fragment1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();

                Fragment fragment = fragmentManager.findFragmentByTag(MainActivity.FragmentType.Fragment2.toString());
                if (fragment == null) {
                    fragment = Fragment2.newInstance();
                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.activity_main, fragment, MainActivity.FragmentType.Fragment2.toString());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        EditText editText = (EditText) getActivity().findViewById(R.id.editText_fragment1);
        text = editText.getText().toString();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_PARAM, text);
        super.onSaveInstanceState(outState);
    }
}
