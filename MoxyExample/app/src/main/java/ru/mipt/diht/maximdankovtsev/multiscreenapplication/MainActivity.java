package ru.mipt.diht.maximdankovtsev.multiscreenapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String STATE_PERSON = "person";

    private static final int REQUEST_NAME_CODE = 1;

    private TextView helloLabel;

    private Person person = new Person();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button requestNameButton = (Button) findViewById(R.id.button_request_name);
        requestNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent requestNameIntent = new Intent(MainActivity.this, EnterNameActivity.class);
                startActivityForResult(requestNameIntent, REQUEST_NAME_CODE);
            }
        });
        helloLabel = (TextView) findViewById(R.id.hello_label);
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

    static class Person implements Parcelable {

        private String firstName;

        private String secondName;

        Person(String firstName, String secondName) {
            this.firstName = firstName;
            this.secondName = secondName;
        }

        Person() {
        }

        String getFirstName() {
            return firstName;
        }

        void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        String getSecondName() {
            return secondName;
        }

        void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        Person(Parcel in) {
            firstName = in.readString();
            secondName = in.readString();
        }

        public static final Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(firstName);
            dest.writeString(secondName);
        }
    }
}
