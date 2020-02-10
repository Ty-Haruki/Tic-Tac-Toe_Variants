package com.apsu.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class Numerical extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private GameBoard gameBoard;
    private TextView playerTurn;
    private String tag;
    private boolean player1;
    private ArrayList<String> number = new ArrayList<>();
    private RadioGroup oddPlayerRG;
    private RadioGroup evenPlayerRG;

    /* Win Condition
        1. Player 1 plays odd numbers, Player 2 plays even numbers (numbers 1-9). Each number can only be played once.
        2. If the sum of three numbers in a row = 15, the player who played last wins.
        3. If there is no sum of 15 by the end of the game, game ends in draw.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerical_layout);
        LinearLayout layout = findViewById(R.id.numerical_layout);
        gameBoard = new GameBoard(this, layout);
        playerTurn = findViewById(R.id.numPlayerTurn);
        for(int i = 1; i <= 9; i++){
            number.add(Integer.toString(i));
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gameBoard.setDrawable(i, j, getDrawable(R.drawable.blank));
                gameBoard.getImageButtonArray()[i][j].setTag("0");
                gameBoard.getImageButtonArray()[i][j].setOnClickListener(this);
            }
        }
        tag = "1";
        player1 = true;
        oddPlayerRG = findViewById(R.id.oddPlayerRG);
        oddPlayerRG.setOnCheckedChangeListener(this);
        evenPlayerRG = findViewById(R.id.evenPlayerRG);
        evenPlayerRG.setOnCheckedChangeListener(this);
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

    public boolean addNums(int a, int b, int c){
        if(a == 0 || b == 0 || c == 0){
            return false;
        }

        if(a+b+c == 15){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean canBePlaced(String tag){
        for(int i = 0; i < number.size(); i++){
            if(number.get(i).equals(tag)){
                return true;
            }
        }
        return false;
    }

    public boolean playerCanPlay(String tag){
        if(player1){
            if(tag.equals("1") || tag.equals("3") || tag.equals("5") || tag.equals("7") || tag.equals("9")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(tag.equals("2") || tag.equals("4") || tag.equals("6") || tag.equals("8")){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public void removeNum(String tag){
        for(int i = 0; i < number.size(); i++){
            if(number.get(i).equals(tag)) {
                number.remove(tag);
            }
        }
    }

    public boolean checkLength(){
        if(number.size() == 0){
            return true;
        }
        return false;
    }

    public void setPicture(String tag, ImageButton button){
        if(tag.equals("1")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("2")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("3")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("4")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("5")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("6")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("7")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("8")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("9")){
            button.setImageResource((R.drawable.one));
        }

        button.setTag(tag);
    }


    @Override
    public void onClick(View v) {
        ImageButton button = (ImageButton) v;
        if(button.getTag().equals("0")){
            if(playerCanPlay(tag) && canBePlaced(tag)){
                setPicture(tag, button);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Position Already Selected", Toast.LENGTH_SHORT).show();
        }

        if(checkWinCondition()){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                }
            }

            if(player1){
                playerTurn.setText("Player 1 Wins");
            }
            else{
                playerTurn.setText("Player 2 Wins");
            }

            // else if check draw condition
        }
        else{
            player1 = !player1;
            if(player1) {
                playerTurn.setText("Player 1 Turn");
            }
            else{
                playerTurn.setText("Player 2 Turn");
            }
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(radioGroup == oddPlayerRG){
            if(i == 0){
                tag = "1";
            }
            else if(i == 1){
                tag = "3";
            }
            else if(i == 2){
                tag = "5";
            }
            else if(i == 3){
                tag = "7";
            }
            else if(i == 4){
                tag = "9";
            }

        }
        else if(radioGroup == evenPlayerRG){
            if(i == 0){
                tag = "2";
            }
            else if(i == 1){
                tag = "4";
            }
            else if(i == 2){
                tag = "6";
            }
            else if(i == 3){
                tag = "8";
            }
        }

    }
}
