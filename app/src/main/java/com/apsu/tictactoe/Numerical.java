/*
Ethan McCrary
Tanner Jones
 */

package com.apsu.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
    private Boolean end = false;

    // Save Progress on App Close
    @Override
    protected void onStop() {
        super.onStop();
        if (!end) {
            try {
                FileOutputStream fos = openFileOutput("numerical_save.txt", Context.MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                PrintWriter pw = new PrintWriter(bw);

                // Print Player Turn
                pw.println(player1);

                // Loop through imagebuttons of current gameboard
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        pw.println(gameBoard.getImageButtonArray()[i][j].getTag());
                    }
                }
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerical_layout);
        LinearLayout layout = findViewById(R.id.numerical_layout);
        playerTurn = findViewById(R.id.numPlayerTurn);
        File f = getFileStreamPath("numerical_save.txt");
        tag = "1";
        oddTag = "1";
        evenTag = "2";
        oddPlayerRG = findViewById(R.id.oddPlayerRG);
        oddPlayerRG.setOnCheckedChangeListener(this);
        evenPlayerRG = findViewById(R.id.evenPlayerRG);
        evenPlayerRG.setOnCheckedChangeListener(this);
        for (int i = 1; i <= 9; i++) {
            number.add(Integer.toString(i));
        }

        if (f.length() == 0) {
            gameBoard = new GameBoard(this, layout);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    gameBoard.setDrawable(i, j, getDrawable(R.drawable.blank));
                    gameBoard.getImageButtonArray()[i][j].setTag("0");
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(this);
                }
            }
            player1 = true;
        } else { // Load Save if it Exists
            try {
                FileInputStream fis = openFileInput("numerical_save.txt");
                Scanner scanner = new Scanner(fis);

                // Handle Turns
                if (scanner.next().equals("true")) {
                    player1 = true;
                    playerTurn.setText(R.string.p1_turn);
                    tag = oddTag;
                } else {
                    player1 = false;
                    playerTurn.setText(R.string.p2_turn);
                    tag = evenTag;
                }

                ArrayList<String> savedBtns = new ArrayList<>();
                while (scanner.hasNext()) {
                    String tags = scanner.next();
                    savedBtns.add(tags);
                }
                scanner.close();

                loadGameBoard(layout, savedBtns);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



    private void loadGameBoard(LinearLayout layout, ArrayList<String> savedBtns) {
        gameBoard = new GameBoard(this, layout);
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Set Tags for Each Button in savedBtns
                gameBoard.getImageButtonArray()[i][j].setTag(savedBtns.get(count));
                count++;

                // Handle Image Resources and onClicks
                if (gameBoard.getImageButtonArray()[i][j].getTag().equals("0")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.blank);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(this);
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("1")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.one);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("1");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("2")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.two);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("2");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("3")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.three);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("3");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("4")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.four);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("4");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("5")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.five);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("5");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("6")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.six);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("6");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("7")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.seven);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("7");
                } else if (gameBoard.getImageButtonArray()[i][j].getTag().equals("8")) {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.eight);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("8");
                } else {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.nine);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                    number.remove("9");
                }
            }
        }
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

        end = true;
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
                        end = true;
                    }

                    if(player1){
                        playerTurn.setText(R.string.p1_win);
                    }
                    else{
                        playerTurn.setText(R.string.p2_win);
                    }
                    // Delete Saved File
                    File save = new File(getFilesDir(), "numerical_save.txt");
                    save.delete();
                }
                else if(checkForDraw()){
                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++){
                            gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                        }
                    }
                    playerTurn.setText(R.string.draw);
                    // Delete Saved File
                    File save = new File(getFilesDir(), "numerical_save.txt");
                    save.delete();
                }
                else{
                    player1 = !player1;
                    if(player1) {
                        playerTurn.setText(R.string.p1_turn);
                        tag = oddTag;
                    }
                    else{
                        playerTurn.setText(R.string.p2_turn);
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
