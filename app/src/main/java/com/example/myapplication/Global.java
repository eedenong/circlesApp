package com.example.myapplication;

import android.app.Application;

public class Global extends Application {
    private boolean firstTime = true;

    public boolean getFirstTime() {
        return this.firstTime;
    }

    public void setSecondTime() {
        this.firstTime = false;
    }
}
