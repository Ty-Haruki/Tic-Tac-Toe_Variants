package com.apsu.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notakto extends AppCompatActivity implements View.OnClickListener  {

    GameBoard gameBoard;


    /* Win condition
        1. Once all boards are completed, last player to play loses the game.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get no_of_gameboards from intent
        Intent intent = getIntent();
        int no_of_gameboards = intent.getIntExtra("NO_OF_GAMEBOARDS", 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notakto_layout);
        LinearLayout layout = findViewById(R.id.notakto_layout);

        for (int i = 0; i < no_of_gameboards; i++) {
            gameBoard = new GameBoard(this, layout);
        }


    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if all boards are locked.
            2. if all boards are not locked/complete, return false.
            3. if all boards are locked/complete, return true.
         */

        return false;
    }


    @Override
    public void onClick(View view) {
        
    }
}
