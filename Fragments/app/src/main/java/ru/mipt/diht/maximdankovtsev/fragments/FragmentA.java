package ru.mipt.diht.maximdankovtsev.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentA extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        Button button = (Button) view.findViewById(R.id.button_a);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                Fragment fragmentB = ((MainActivity) getActivity()).getFragmentB();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragmentB, MainActivity.FRAGMENT_B_TAG);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
