package com.example.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Оленька on 06.03.2017.
 */

public class FirstFragment extends android.app.Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first_fragment, container, false);
        Button button = (Button) view.findViewById(R.id.first_fragment_button_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment();
            }
        });

        EditText text = (EditText) view.findViewById(R.id.first_name_id);
        text.setText(((MainActivity) getActivity()).getText());
        text.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                    final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before,
                    final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                ((MainActivity) getActivity()).setText(s.toString());
            }
        });
        return view;
    }


}
