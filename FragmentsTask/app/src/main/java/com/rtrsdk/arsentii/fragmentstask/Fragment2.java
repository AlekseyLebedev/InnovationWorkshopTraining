package com.rtrsdk.arsentii.fragmentstask;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class Fragment2 extends Fragment {

    private static final String[] SPINNER_DATA = {"one", "two", "three", "four", "five"};
    private static final String SELECTION = "selection";

    private int spinnerSelection = 1;


    public Fragment2() {
        // Required empty public constructor
    }


    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            spinnerSelection = savedInstanceState.getInt(SELECTION);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, SPINNER_DATA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setSelection(spinnerSelection);

        Button button = (Button) view.findViewById(R.id.button_fragment2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = Fragment1.newInstance();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.activity_main, fragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Spinner editText = (Spinner) getActivity().findViewById(R.id.spinner);
        spinnerSelection = editText.getSelectedItemPosition();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTION, spinnerSelection);
        super.onSaveInstanceState(outState);
    }

}
