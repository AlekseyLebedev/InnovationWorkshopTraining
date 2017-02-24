package com.example.ibirby.homework2;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    final static String LOG_TAG = "myLogs";

    public String mFirstName, mLastName;

    public Person(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mFirstName);
        parcel.writeString(mLastName);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private Person(Parcel parcel) {
        mFirstName = parcel.readString();
        mLastName = parcel.readString();
    }
}
