package com.example.mvp_pattern__mvc_mvp_mvvm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvp_pattern__mvc_mvp_mvvm.R;
import com.example.mvp_pattern__mvc_mvp_mvvm.presenter.TicTacToePresenter;

public class MainActivity extends AppCompatActivity implements TicTacToeView {

    private static String TAG = "LOG_TAG";

    private ViewGroup gridLayout;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    TicTacToePresenter presenter = new TicTacToePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        gridLayout = (ViewGroup) findViewById(R.id.gridLayout);
        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCellClicked(View v) {
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        presenter.onButtonSelected(row, col);
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) gridLayout.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    @Override
    public void clearButtons() {
        for(int i = 0; i < gridLayout.getChildCount(); i++ ) {
            ((Button) gridLayout.getChildAt(i)).setText("");
        }
    }

    @Override
    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }
}