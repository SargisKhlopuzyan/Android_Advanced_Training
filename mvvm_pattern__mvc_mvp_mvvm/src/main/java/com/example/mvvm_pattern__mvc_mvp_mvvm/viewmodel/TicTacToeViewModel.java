package com.example.mvvm_pattern__mvc_mvp_mvvm.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.example.mvvm_pattern__mvc_mvp_mvvm.model.Board;
import com.example.mvvm_pattern__mvc_mvp_mvvm.model.Player;

/**
 * Created by sargiskh on 12/18/2017.
 */

public class TicTacToeViewModel implements ViewModel {

    private Board model;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModel() {
        model = new Board();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

}
