package ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.mipt.diht.maximdankovtsev.multiscreenapplication.MainActivityView;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.MainPresenter;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.Person;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.R;
import ru.mipt.diht.maximdankovtsev.multiscreenapplication.ui.activity.getname.GetNameActivity;

//todo : compatActivity
public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    // Intent.extra ключ
    public static final String INTENT_EXTRA_PERSON = "person";

    // Код для запуска активности получения имени и фамилии
    private static final int REQUEST_NAME_CODE = 1;

    public static final String TAG = "MainActivity";

    @InjectPresenter
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NAME_CODE && resultCode == RESULT_OK) {
            // Сообщаем презентеру, что пользователь изменил свои данные
            Person person = data.getParcelableExtra(INTENT_EXTRA_PERSON);
            mMainPresenter.onPersonChanged(person);
        }
    }

    @Override
    public void showHelloMessage(final Person person) {
        TextView helloLabel = (TextView) findViewById(R.id.hello_label);
        helloLabel.setText(constructGreeting(person));
    }

    // Генерирует строку из приветствия и имени в зависимости от их наличия
    private String constructGreeting(final Person person) {
        // Если пользователь ничего не ввел в поля имя, фамилия, то игнорируем(подставим пустые строки)
        if (person != null) {
            String firstName = "";
            String secondName = "";
            if (person.getFirstName() != null) {
                firstName = person.getFirstName();
            }
            if (person.getSecondName() != null) {
                secondName = person.getSecondName();
            }
            return getResources().getString(R.string.hello) + ", "
                    + firstName + " " + secondName + "!";
        } else {
            return getResources().getString(R.string.hello);
        }
    }
}
