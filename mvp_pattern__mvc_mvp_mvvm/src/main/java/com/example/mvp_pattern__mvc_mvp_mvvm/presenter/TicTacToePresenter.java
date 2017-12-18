package com.example.mvp_pattern__mvc_mvp_mvvm.presenter;

import com.example.mvp_pattern__mvc_mvp_mvvm.model.Board;
import com.example.mvp_pattern__mvc_mvp_mvvm.model.Player;
import com.example.mvp_pattern__mvc_mvp_mvvm.view.TicTacToeView;

/**
 * Created by sargiskh on 12/15/2017.
 */

public class TicTacToePresenter implements Presenter {

    private TicTacToeView view;
    private Board model;

    public TicTacToePresenter(TicTacToeView view) {
        this.view = view;
        this.model = new Board();
    }

    @Override
    public void onCreate() {
        model = new Board();
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

    public void onButtonSelected(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            }
        }
    }

    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        model.restart();
    }
}
