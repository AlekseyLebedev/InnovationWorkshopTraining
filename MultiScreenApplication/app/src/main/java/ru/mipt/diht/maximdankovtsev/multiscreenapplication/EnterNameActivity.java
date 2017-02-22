package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterNameActivity extends Activity {

    private EditText firstNameEdit;
    private EditText secondNameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        firstNameEdit = (EditText) findViewById(R.id.edit_first_name);
        secondNameEdit = (EditText) findViewById(R.id.edit_second_name);

        Button okButton = (Button) findViewById(R.id.button_confirm_name);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirm = new Intent();
                confirm.putExtra(MainActivity.STATE_FIRST_NAME, firstNameEdit.getText().toString());
                confirm.putExtra(MainActivity.STATE_SECOND_NAME, secondNameEdit.getText().toString());

                EnterNameActivity.this.setResult(RESULT_OK, confirm);
                EnterNameActivity.this.finish();
            }
        });
    }

}
