package com.example.mvp_pattern__mvc_mvp_mvvm.view;

/**
 * Created by sargiskh on 12/15/2017.
 */

public interface TicTacToeView {
    void showWinner(String winningPlayerDisplayLabel);
    void clearWinnerDisplay();
    void clearButtons();
    void setButtonText(int row, int col, String text);
}
