package com.apsu.tictactoe;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notakto extends AppCompatActivity  {

    /* Win condition
        1. Once all boards are completed, last player to play loses the game.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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


}
