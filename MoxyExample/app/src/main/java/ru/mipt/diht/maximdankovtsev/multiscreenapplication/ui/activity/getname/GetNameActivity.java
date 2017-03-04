package ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity.getname;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.Person;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.R;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.presentation.presenter.getname.GetNamePresenter;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity.MainActivity;

public class GetNameActivity extends MvpActivity implements MvpView {

    public static final String TAG = "GetName";

    @InjectPresenter
    GetNamePresenter mGetNamePresenter;

    private EditText mFirstNameEdit;

    private EditText mSecondNameEdit;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_name_activity);
        mFirstNameEdit = (EditText) findViewById(R.id.edit_first_name);
        mSecondNameEdit = (EditText) findViewById(R.id.edit_second_name);

        Button okButton = (Button) findViewById(R.id.button_confirm_name);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirm = new Intent();

                Person person = new Person(mFirstNameEdit.getText().toString(),
                        mSecondNameEdit.getText().toString());

                confirm.putExtra(MainActivity.STATE_PERSON, person);
                GetNameActivity.this.setResult(RESULT_OK, confirm);
                GetNameActivity.this.finish();
            }
        });
    }
}
