/*
Ethan McCrary
Tanner Jones
 */

package com.apsu.tictactoe;

import android.os.Bundle;
import android.util.Log;
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
    private String oddTag;
    private String evenTag;
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
        oddTag = "1";
        evenTag = "2";
        player1 = true;
        oddPlayerRG = findViewById(R.id.oddPlayerRG);
        oddPlayerRG.setOnCheckedChangeListener(this);
        evenPlayerRG = findViewById(R.id.evenPlayerRG);
        evenPlayerRG.setOnCheckedChangeListener(this);
    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        if(checkForRow() || checkForCol() || checkForDiag()){
            return true;
        }

        return false;
    }

    public boolean addNums(int a, int b, int c){
        if(a == 0 || b == 0 || c == 0){
            return false;
        }

        int sum = a + b + c;
        Log.i("add", a+"+"+b+"+"+c+"= "+sum+".");

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

    private boolean checkForRow(){
        int j = 0;
        ArrayList<ImageButton> array;

        for(int i = 0; i < 3; i++){
            array = new ArrayList<>();
            array.add(gameBoard.getImageButtonArray()[i][j]);
            array.add(gameBoard.getImageButtonArray()[i][j+1]);
            array.add(gameBoard.getImageButtonArray()[i][j+2]);
            if(addNums(Integer.parseInt(array.get(0).getTag().toString()), Integer.parseInt(array.get(1).getTag().toString()), Integer.parseInt(array.get(2).getTag().toString()))){
                return true;
            }
        }

        return false;
    }

    private boolean checkForCol(){
        int j = 0;
        ArrayList<ImageButton> array;

        for(int i = 0; i < 3; i++){
            array = new ArrayList<>();
            array.add(gameBoard.getImageButtonArray()[j][i]);
            array.add(gameBoard.getImageButtonArray()[j+1][i]);
            array.add(gameBoard.getImageButtonArray()[j+2][i]);
            if(addNums(Integer.parseInt(array.get(0).getTag().toString()), Integer.parseInt(array.get(1).getTag().toString()), Integer.parseInt(array.get(2).getTag().toString()))){
                return true;
            }
        }

        return false;
    }

    private boolean checkForDiag(){

        ArrayList<ImageButton> array;
            array = new ArrayList<>();
            array.add(gameBoard.getImageButtonArray()[0][0]);
            array.add(gameBoard.getImageButtonArray()[1][1]);
            array.add(gameBoard.getImageButtonArray()[2][2]);
            if(addNums(Integer.parseInt(array.get(0).getTag().toString()), Integer.parseInt(array.get(1).getTag().toString()), Integer.parseInt(array.get(2).getTag().toString()))){
                return true;
            }

        array = new ArrayList<>();
        array.add(gameBoard.getImageButtonArray()[0][2]);
        array.add(gameBoard.getImageButtonArray()[1][1]);
        array.add(gameBoard.getImageButtonArray()[2][0]);
        if(addNums(Integer.parseInt(array.get(0).getTag().toString()), Integer.parseInt(array.get(1).getTag().toString()), Integer.parseInt(array.get(2).getTag().toString()))){
            return true;
        }
        return false;
    }

    private boolean checkForDraw(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[i][j].getTag().toString().equals("0")){
                    return false;
                }
            }
        }


        return true;
    }

    public void setPicture(String tag, ImageButton button){
        if(tag.equals("1")){
            button.setImageResource((R.drawable.one));
        }
        else if(tag.equals("2")){
            button.setImageResource((R.drawable.two));
        }
        else if(tag.equals("3")){
            button.setImageResource((R.drawable.three));
        }
        else if(tag.equals("4")){
            button.setImageResource((R.drawable.four));
        }
        else if(tag.equals("5")){
            button.setImageResource((R.drawable.five));
        }
        else if(tag.equals("6")){
            button.setImageResource((R.drawable.six));
        }
        else if(tag.equals("7")){
            button.setImageResource((R.drawable.seven));
        }
        else if(tag.equals("8")){
            button.setImageResource((R.drawable.eight));
        }
        else if(tag.equals("9")){
            button.setImageResource((R.drawable.nine));
        }

        button.setTag(tag);
    }


    @Override
    public void onClick(View v) {
        if(player1){
            tag = oddTag;
        }
        else{
            tag = evenTag;
        }
        ImageButton button = (ImageButton) v;
        if(button.getTag().equals("0")){
            if(playerCanPlay(tag) && canBePlaced(tag)){
                setPicture(tag, button);
                removeNum(tag);

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
                }
                else if(checkForDraw()){
                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++){
                            gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                        }
                    }
                    playerTurn.setText("Draw!");
                }
                else{
                    player1 = !player1;
                    if(player1) {
                        playerTurn.setText("Player 1 Turn");
                        tag = oddTag;
                    }
                    else{
                        playerTurn.setText("Player 2 Turn");
                        tag = evenTag;
                    }
                }
            }
            else if(!canBePlaced(tag)){
                Toast.makeText(getApplicationContext(), "Number already used", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            Toast.makeText(getApplicationContext(), "Position Already Selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(radioGroup == oddPlayerRG){
            if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton1){
                oddTag = "1";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton3){
                oddTag = "3";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton5){
                oddTag = "5";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton7){
                oddTag = "7";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton9){
                oddTag = "9";
            }

        }
        else if(radioGroup == evenPlayerRG){
            if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton2){
                evenTag = "2";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton4){
                evenTag = "4";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton6){
                evenTag = "6";
            }
            else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton8){
                evenTag = "8";
            }
        }

        if(player1){
            tag = oddTag;
        }
        else{
            tag = evenTag;
        }

    }
}
