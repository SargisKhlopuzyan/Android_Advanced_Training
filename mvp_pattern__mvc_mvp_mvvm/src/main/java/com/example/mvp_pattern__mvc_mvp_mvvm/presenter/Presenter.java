package com.example.mvp_pattern__mvc_mvp_mvvm.presenter;

/**
 * Created by sargiskh on 12/8/2017.
 */

public interface Presenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}