package com.example.diggreddit.utility;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Util {

    public static void hideKeyboard(Context context,View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputMethodManager!=null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
