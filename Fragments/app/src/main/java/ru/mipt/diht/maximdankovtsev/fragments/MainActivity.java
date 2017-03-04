package ru.mipt.diht.maximdankovtsev.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    // Теги фрагментов
    final static String FRAGMENT_A_TAG = "FRAGMENT_A";

    final static String FRAGMENT_B_TAG = "FRAGMENT_B";

    private Fragment mFragmentA;

    private Fragment mFragmentB;

    public Fragment getFragmentA() {
        return mFragmentA;
    }

    public Fragment getFragmentB() {
        return mFragmentB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_main);
        FragmentManager fragmentManager = getFragmentManager();

        // Пытаемся восстановить связь MainActivity с фрагментами
        // FragmentA
        mFragmentA = fragmentManager.findFragmentByTag(FRAGMENT_A_TAG);
        if (mFragmentA == null) {
            mFragmentA = new FragmentA();
        }
        // FragmentB
        mFragmentB = fragmentManager.findFragmentByTag(FRAGMENT_B_TAG);
        if (mFragmentB == null) {
            mFragmentB = new FragmentB();
        }

        // Если это первый запуск, то добавим FragmentA
        if (savedInstanceState == null) {
            // Добавление FragmentA
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, mFragmentA, FRAGMENT_A_TAG);
            fragmentTransaction.commit();
        }
    }

}
