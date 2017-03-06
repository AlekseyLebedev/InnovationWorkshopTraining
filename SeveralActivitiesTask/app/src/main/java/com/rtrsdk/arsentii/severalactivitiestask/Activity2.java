package com.rtrsdk.arsentii.severalactivitiestask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button requestBtn = (Button) findViewById(R.id.button_call_act3);

        Person who = getIntent().getParcelableExtra("Person");

        if (who == null) {
            who = new Person("Anonymous", "Anonymous");
        }

        TextView helloText = (TextView) findViewById(R.id.textViewHello);
        helloText.setText("Hello " + who.getFirstName() + " " + who.getLastName() + "!");

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionIntent = new Intent(Activity2.this,
                        Activity3.class);
                startActivity(questionIntent);
                finish();
            }
        });
    }
}
