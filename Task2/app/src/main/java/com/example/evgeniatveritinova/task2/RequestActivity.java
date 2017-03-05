package com.example.evgeniatveritinova.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        final EditText firstName = (EditText) findViewById(R.id.edit_text1);
        final EditText secondName = (EditText) findViewById(R.id.edit_text2);

        Button okButton = (Button) findViewById(R.id.button_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirm = new Intent();

                confirm.putExtra(MainActivity.FIRST_NAME, firstName.getText().toString());
                confirm.putExtra(MainActivity.SECOND_NAME, secondName.getText().toString());

                RequestActivity.this.setResult(RESULT_OK, confirm);
                RequestActivity.this.finish();
            }
        });
    }
}
