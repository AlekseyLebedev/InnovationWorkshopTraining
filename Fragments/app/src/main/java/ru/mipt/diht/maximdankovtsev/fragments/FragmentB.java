package ru.mipt.diht.maximdankovtsev.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        fillSpinner(view);

        Button button = (Button) view.findViewById(R.id.button_b);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                Fragment fragmentA = ((MainActivity) getActivity()).getFragmentA();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragmentA);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    private void fillSpinner(View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        List<String> spinnerContent = new ArrayList<String>();
        for (int i = 0; i < 5; ++i) {
            spinnerContent.add("Spinner " + String.valueOf(i));
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, spinnerContent);
        spinner.setAdapter(spinnerArrayAdapter);
    }

}
