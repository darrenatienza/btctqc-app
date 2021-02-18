package com.example.btctqc_app.misc;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(value= SharedPref.Scope.UNIQUE)
public interface MyPrefs {

    @DefaultString("")
    String currentUserName();

    @DefaultInt(0)
    int currentUserID();

}
