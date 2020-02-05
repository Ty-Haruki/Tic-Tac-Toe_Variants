package com.apsu.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Numerical extends AppCompatActivity {

    /* Win Condition
        1. Player 1 plays odd numbers, Player 2 plays even numbers (numbers 1-9). Each number can only be played once.
        2. If the sum of three numbers in a row = 15, the player who played last wins.
        3. If there is no sum of 15 by the end of the game, game ends in draw.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout layout = findViewById(R.id.main_layout);
        GameBoard gameBoard = new GameBoard(this, layout);

    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if each row equals 15
            2. Check if each column equals 15
            3. Check if each diagonal equals 15
            4. If one of those conditions are true, lock board and return true
            5. If none of those conditions are true, return false
         */

        return false;
    }
}
