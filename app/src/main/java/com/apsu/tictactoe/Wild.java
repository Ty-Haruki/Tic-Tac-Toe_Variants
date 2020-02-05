package com.apsu.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Wild extends AppCompatActivity implements View.OnClickListener {

    /* Win Condition
        1. Both players plays O's and X's.
        2. If there are three O's or three X's in a row, last player to play wins
        3. Ends if draw if all pieces are filled.
     */

    // 0 = blank, 1 = X, 2 = O

    // variable to know who the current player's turn is
    private int currentPlayer;
    GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPlayer = 1;
        ConstraintLayout wildLayout = findViewById(R.id.wildLayout);
        gameBoard = new GameBoard(this, wildLayout);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gameBoard.setImageResource(i, j, R.drawable.blank);
            }
        }

    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if there are 3 O's or 3 X's in a row
            2. If condition is true, return true otherwise, return false.
         */
        int count = 0;
        if(checkForRow(R.drawable.circle) || checkForRow(R.drawable.x)){
            return true;
        }

        return false;
    }

    private boolean checkForRow(int draw){
        String x = "x";
        String o = "o";

        int count = 0;

        // for O's
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[i][j].getDrawable().equals(draw)){
                    count++;
                }
            }
            if(count >= 3){
                return true;
            }
            else{
                count = 0;
            }
        }

        return false;
    }


    private boolean checkForCol(){

        return false;
    }

    @Override
    public void onClick(View view) {
        /*
        1. checks to see if spot is blank or not.
        2. if blank,
            1.put selected piece on board.
            2.check if winCondition
                1. if win condition, lock board and display current turn as winner
                2. If false, check to see if board is complete.
                    1. if false, change turns.
                    2. if true, display draw as the win.
         */

    }
}
