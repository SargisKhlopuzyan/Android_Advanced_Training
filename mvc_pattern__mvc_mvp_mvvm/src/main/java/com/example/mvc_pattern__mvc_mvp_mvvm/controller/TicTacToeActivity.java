package com.example.mvc_pattern__mvc_mvp_mvvm.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvc_pattern__mvc_mvp_mvvm.R;
import com.example.mvc_pattern__mvc_mvp_mvvm.model.Board;
import com.example.mvc_pattern__mvc_mvp_mvvm.model.Player;

public class TicTacToeActivity extends AppCompatActivity {

    private Board model;

    private TextView winnerPlayerLabel;
    private View winnerPlayerViewGroup;
    private ViewGroup buttonGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);

        model = new Board();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void reset() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        model.restart();

        for( int i = 0; i < buttonGrid.getChildCount(); i++ ) {
            if (buttonGrid.getChildAt(i) instanceof  Button) {
                ((Button) buttonGrid.getChildAt(i)).setText("");
            } else {
                ViewGroup viewGroup = (ViewGroup) buttonGrid.getChildAt(i);
                for( int k = 0; k < viewGroup.getChildCount(); k++ ) {
                    if (viewGroup.getChildAt(k) instanceof  Button) {
                        ((Button) viewGroup.getChildAt(k)).setText("");
                    }
                }
            }

        }
    }

    public void onCellClicked(View view) {

        Button button = (Button) view;
        String tag =(String) button.getTag();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));

        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (model.getWinner() != null) {
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }
    }
}
