package com.apsu.tictactoe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Wild  extends AppCompatActivity {

    /* Win Condition
        1. Both players plays O's and X's.
        2. If there are three O's or three X's in a row, last player to play wins
        3. Ends if draw if all pieces are filled.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if there are 3 O's or 3 X's in a row
            2. If condition is true, return true otherwise, return false.
         */

        return false;
    }
}
