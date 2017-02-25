package com.rtrsdk.arsentii.severalactivitiestask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Button okBtn = (Button) findViewById(R.id.button_ok);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
                EditText lastName = (EditText) findViewById(R.id.editTextLastName);

                Intent intent = new Intent(Activity3.this, Activity2.class);

                intent.putExtra("Person", new Person(firstName.getText().toString(), lastName.getText().toString()));
                startActivity(intent);
                finish();
            }
        });
    }
}
