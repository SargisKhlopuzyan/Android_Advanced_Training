package com.example.mvvm_pattern__mvc_mvp_mvvm.databinding;

import android.databinding.BaseObservable;

import com.example.mvvm_pattern__mvc_mvp_mvvm.viewmodel.TicTacToeViewModel;

/**
 * Created by sargiskh on 12/19/2017.
 */

public class TicTacToeBinding2 extends BaseObservable {
    public TicTacToeViewModel viewModel;

    public void setViewModel(TicTacToeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public TicTacToeViewModel getViewModel() {
        return viewModel;
    }
}
