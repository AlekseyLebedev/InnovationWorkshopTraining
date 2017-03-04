package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity.getname.GetNameActivity;

public class MainActivity extends Activity {

    // Bundle ключ
    public static final String STATE_PERSON = "person";

    // Код для запуска активности получения имени и фамилии
    private static final int REQUEST_NAME_CODE = 1;

    private TextView helloLabel;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = new Person();
        helloLabel = (TextView) findViewById(R.id.hello_label);
        Button requestNameButton = (Button) findViewById(R.id.button_request_name);
        requestNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestNameIntent = new Intent(MainActivity.this, GetNameActivity.class);
                startActivityForResult(requestNameIntent, REQUEST_NAME_CODE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_PERSON, person);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        person = savedInstanceState.getParcelable(STATE_PERSON);
        helloLabel.setText(constructGreeting());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NAME_CODE && resultCode == RESULT_OK) {
            person = data.getParcelableExtra(STATE_PERSON);
            helloLabel.setText(constructGreeting());
        }
    }

    private String constructGreeting() {
        // Если пользователь ничего не ввел в поля имя, фамилия, то игнорируем(подставим пустые строки)
        if (person.getFirstName() == null) {
            person.setFirstName("");
        }
        if (person.getSecondName() == null) {
            person.setSecondName("");
        }
        if (person.getFirstName().isEmpty() && person.getSecondName().isEmpty()) {
            return getResources().getString(R.string.hello);
        } else {
            return getResources().getString(R.string.hello) + ", " + person.getFirstName() + " "
                    + person.getSecondName() + "!";
        }
    }
}
