package com.example.mvvm_pattern__mvc_mvp_mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mvvm_pattern__mvc_mvp_mvvm.R;
import com.example.mvvm_pattern__mvc_mvp_mvvm.databinding.TicTacToeBinding2;
import com.example.mvvm_pattern__mvc_mvp_mvvm.viewmodel.TicTacToeViewModel;

public class TicTacToeActivity extends AppCompatActivity {

    TicTacToeViewModel viewModel = new TicTacToeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TictactoeBinding binding = DataBindingUtil.setContentView(this, R.layout.tictactoe); */
        //TicTacToeBinding binding = DataBindingUtil.setContentView(TicTacToeActivity.this, R.layout.tictactoe);
        //binding.setViewModel(viewModel);
        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
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
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
