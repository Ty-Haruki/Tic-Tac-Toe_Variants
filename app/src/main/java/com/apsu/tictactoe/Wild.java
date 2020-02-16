/*
Ethan McCrary
Tanner Jones
 */

package com.apsu.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

public class Wild extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /* Win Condition
        1. Both players plays O's and X's.
        2. If there are three O's or three X's in a row, last player to play wins
        3. Ends if draw if all pieces are filled.
     */

    // 0 = blank, 1 = X, 2 = O

    // variable to know who the current player's turn is
    private boolean player1;
    private GameBoard gameBoard;
    private CompoundButton xSwitch;
    private CompoundButton oSwitch;
    private TextView playerTurn;
    Boolean end = false;

    // Save Progress on App Close
    @Override
    protected void onStop() {
        super.onStop();
        if (!end) {
            try {
                FileOutputStream fos = openFileOutput("wild_save.txt", Context.MODE_PRIVATE);
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

        setContentView(R.layout.wild_layout);
        player1 = true;
        LinearLayout layout = findViewById(R.id.wild_layout);
        xSwitch = findViewById(R.id.wildXSwitch);
        xSwitch.setOnCheckedChangeListener(this);
        oSwitch = findViewById(R.id.wildOSwitch);
        oSwitch.setOnCheckedChangeListener(this);
        playerTurn = findViewById(R.id.wildPlayerTurn);
        File f = getFileStreamPath("wild_save.txt");


        if (f.length() == 0) {
            gameBoard = new GameBoard(this, layout);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    gameBoard.setDrawable(i, j, getDrawable(R.drawable.blank));
                    gameBoard.getImageButtonArray()[i][j].setTag("0");
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(this);
                }
            }
        } else { // Load Save if it Exists
            try {
                FileInputStream fis = openFileInput("wild_save.txt");
                Scanner scanner = new Scanner(fis);

                // Handle Turns
                if (scanner.next().equals("true")) {
                    player1 = true;
                    playerTurn.setText(R.string.p1_turn);
                } else {
                    player1 = false;
                    playerTurn.setText(R.string.p2_turn);
                }

                ArrayList<String> savedBtns = new ArrayList<>();
                while (scanner.hasNext()) {
                    String tag = scanner.next();
                    savedBtns.add(tag);
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
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.x);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                } else {
                    gameBoard.getImageButtonArray()[i][j].setImageResource(R.drawable.circle);
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                }
            }
        }
    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if there are 3 O's or 3 X's in each row, col, or diagonal
            2. If condition is true, return true otherwise, return false.
         */
        if(checkForRow("1") || checkForRow("2")){
            return true;
        }
        else if(checkForCol("1") || checkForCol("2")){
            return true;
        }
        else if(checkForDiag("1") || checkForDiag(("2"))){
            return true;
        }

        return false;
    }

    private boolean checkForRow(String tag){
        int count = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[i][j].getTag().equals(tag)){
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

    private boolean checkForCol(String tag){
        int count = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[j][i].getTag().equals(tag)){
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

    private boolean checkForDiag(String tag){
        int count = 0;

        // left right diag check
        for(int i = 0; i < 3; i++){
            if(gameBoard.getImageButtonArray()[i][i].getTag().equals(tag)){
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
            if(gameBoard.getImageButtonArray()[i][j].getTag().equals(tag)){
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
        ImageButton button = (ImageButton) view;
        if(button.getTag().equals("0")){
            if(xSwitch.isChecked()){
                ((ImageButton)view).setImageResource(R.drawable.x);
                button.setTag("1");
            }
            else if(oSwitch.isChecked()){
                ((ImageButton)view).setImageResource(R.drawable.circle);
                button.setTag("2");
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
            // Delete Saved File
            File save = new File(getFilesDir(), "wild_save.txt");
            save.delete();
            end = true;
            if(player1) {
                playerTurn.setText(R.string.p1_win);
            }
            else{
                playerTurn.setText(R.string.p2_win);
            }
        }
        else if(checkDrawCondition()){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    gameBoard.getImageButtonArray()[i][j].setOnClickListener(null);
                }
            }
            // Delete Saved File
            File save = new File(getFilesDir(), "wild_save.txt");
            save.delete();
            end = true;
            playerTurn.setText(R.string.draw);
        }
        else{
            player1 = !player1;
            if(player1) {
                playerTurn.setText(R.string.p1_turn);
            }
            else{
                playerTurn.setText(R.string.p2_turn);
            }
        }

    }

    private boolean checkDrawCondition(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard.getImageButtonArray()[i][j].getTag().equals("0")) {
                    return false;
                }
            }
        }

        return true;
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
