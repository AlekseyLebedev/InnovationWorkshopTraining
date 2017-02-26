package com.example.kiric.sprint2_task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE = 7;

    private String mFirstName = "";
    private String mLastName = "";
    TextView mText;
    String textKey = "textKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = (Button) findViewById(R.id.button);
        mText = (TextView) findViewById(R.id.textView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LastActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putCharSequence(textKey,mText.getText());
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mText.setText(savedInstanceState.getCharSequence(textKey));
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mFirstName = data.getStringExtra("first name");
            mLastName = data.getStringExtra("last name");
        }
        mText.setText("Hello "+ mFirstName+ " " + mLastName + "!");
    }

}
