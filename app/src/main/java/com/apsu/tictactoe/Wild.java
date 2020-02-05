package com.apsu.tictactoe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Wild extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /* Win Condition
        1. Both players plays O's and X's.
        2. If there are three O's or three X's in a row, last player to play wins
        3. Ends if draw if all pieces are filled.
     */

    // 0 = blank, 1 = X, 2 = O

    // variable to know who the current player's turn is
    private int currentPlayer = 1;
    private GameBoard gameBoard;
    private CompoundButton xSwitch;
    private CompoundButton oSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wild_layout);
        currentPlayer = 1;
        RelativeLayout layout = findViewById(R.id.wildLayout);
        gameBoard = new GameBoard(this, layout);
        xSwitch = findViewById(R.id.wildXSwitch);
        oSwitch = findViewById(R.id.wildOSwitch);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gameBoard.setImageResource(i, j, R.drawable.blank);
                gameBoard.getImageButtonArray()[i][j].setOnClickListener(this);
            }
        }

    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if there are 3 O's or 3 X's in each row, col, or diagonal
            2. If condition is true, return true otherwise, return false.
         */
        int count = 0;
        if(checkForRow(R.drawable.circle) || checkForRow(R.drawable.x)){
            return true;
        }
        else if(checkForCol(R.drawable.circle) || checkForCol(R.drawable.x)){
            return true;
        }
        else if(checkForDiag(R.drawable.circle) || checkForDiag((R.drawable.x))){
            return true;
        }

        return false;
    }

    private boolean checkForRow(int draw){
        int count = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[i][j].getDrawable()== getDrawable(draw)){
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

    private boolean checkForCol(int draw){
        int count = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[i][j].getDrawable()== getDrawable(draw)){
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

    private boolean checkForDiag(int draw){
        int count = 0;

        // left right diag check
        for(int i = 0; i < 3; i++){
            if(gameBoard.getImageButtonArray()[i][i].getDrawable()== getDrawable(draw)){
                count++;
            }
        }
        if(count >= 3){
            return true;
        }
        else{
            count = 0;
        }

        // right left diag check
        int j = 2;
        for(int i = 0; i < 3; i++){
            if(gameBoard.getImageButtonArray()[i][j].getDrawable()== getDrawable(draw)){
                count++;
            }
            j--;
        }
        if(count >= 3){
            return true;
        }

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
         3. if not blank, give toast saying that position has already been taken.
         */
        ImageButton button = (ImageButton) view;
        if(button.getDrawable() == getDrawable(R.drawable.blank)){
            if(xSwitch.isChecked()){
                ((ImageButton)view).setImageResource(R.drawable.x);
            }
            else if(oSwitch.isChecked()){
                ((ImageButton)view).setImageResource(R.drawable.circle);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Position Already Selected", Toast.LENGTH_SHORT).show();
        }

        if(checkWinCondition()){
            // lock board, display winner.
        }
        else{
            // swap player and display
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton == xSwitch){
            oSwitch.setChecked(!b);
        }
        if(compoundButton == oSwitch){
            xSwitch.setChecked(!b);
        }
    }
}
