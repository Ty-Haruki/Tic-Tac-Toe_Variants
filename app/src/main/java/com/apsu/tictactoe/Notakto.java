package com.apsu.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
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

public class Notakto extends AppCompatActivity implements View.OnClickListener  {

    private TextView tv;
    private int no_of_gameboards;
    private int playerTurn;
    private GameBoard[] gameBoards;
    private ImageButton ibs[][];
    private int ibid = 0;

    // Save Progress on App Close
    @Override
    protected void onStop() {
        super.onStop();
        if (no_of_gameboards > 0) {
            try {
                FileOutputStream fos = openFileOutput("notakto_save.txt", Context.MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                PrintWriter pw = new PrintWriter(bw);

                // Print Number of Gameboards
                pw.println(no_of_gameboards);

                // Print Player Turn
                pw.println(playerTurn);

                // Print Saved Locations of Xs
                ibid = 0;

                // Loop through gameboards
                for (int i = 0; i < no_of_gameboards; i++) {
                    ibs = gameBoards[i].getImageButtonArray();

                    // Loop through imagebuttons of current gameboard
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            //
                            // If Button is Tagged
                            if (ibs[j][k].getTag() == "1") {
                                // Print to TXT
                                pw.println(((((i + 1) * 100)) + ibid));
                            }
                            ibid++;

                            if (ibid == 9) {
                                ibid = 0;
                            }
                        }
                    }
                }

                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /* Win condition
        1. Once all boards are completed, last player to play loses the game.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File f = getFileStreamPath("notakto_save.txt");
        playerTurn = 0;
        setContentView(R.layout.notakto_layout);
        LinearLayout layout = findViewById(R.id.notakto_layout);

        // Check if Saved File has Content
        if (f.length() == 0) {
            // Get no_of_gameboards from intent
            Intent intent = getIntent();
            no_of_gameboards = intent.getIntExtra("NO_OF_GAMEBOARDS", 1);
            gameBoards = new GameBoard[no_of_gameboards];

            createGameBoard(layout);
        } else {
            // Load Save if it Exists
            try {
                FileInputStream fis = openFileInput("notakto_save.txt");
                Scanner scanner = new Scanner(fis);

                // Recreate Saved Boards
                no_of_gameboards = Integer.parseInt(scanner.next());
                playerTurn = Integer.parseInt(scanner.next());
                Log.i("Player Turn", String.valueOf(playerTurn));

                tv = findViewById(R.id.current_player);
                if (playerTurn == 1) {
                    tv.setText(R.string.p2_turn);
                }

                ArrayList<Integer> savedBtns = new ArrayList<>();
                while (scanner.hasNext()) {
                    int btn = Integer.parseInt(scanner.next());
                    savedBtns.add(btn);
                }
                scanner.close();
                gameBoards = new GameBoard[no_of_gameboards];

                loadGameBoard(layout, savedBtns);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    private void loadGameBoard(LinearLayout layout, ArrayList<Integer> savedBtns) {
        int index = 0;
        for (int i = 0; i < no_of_gameboards; i++) {
            ibs = new ImageButton[3][3];
            gameBoards[i] = new GameBoard(this, layout);
            ibs = gameBoards[i].getImageButtonArray();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int id = ((i + 1) * 100) + ibid;
                    // Creates unique button ids (100, 201, 302, 403, etc)
                    ibs[j][k].setId(id);
                    Log.i("ID", String.valueOf(ibs[j][k].getId()));

                    if (savedBtns.size() > 0) {
                        // Compare new btn id to saved btn ids
                        if (ibs[j][k].getId() != savedBtns.get(index)) {
                            // New Buttons and Activate Clicks
                            ibs[j][k].setTag("0");
                            ibs[j][k].setOnClickListener(this);
                        } else {
                            // Set Tags Again and Deactivate Clicks
                            ibs[j][k].setTag("1");
                            ibs[j][k].setImageResource(R.drawable.x);
                            ibs[j][k].setOnClickListener(null);
                            if (index < (savedBtns.size() - 1)) {
                                index++;
                            }
                        }
                    }

                    ibid++;
                    if (ibid == 9) {
                        ibid = 0;
                    }
                }
            }
        }
    }

    private void createGameBoard(LinearLayout layout) {
        for (int i = 0; i < no_of_gameboards; i++) {
            ibs = new ImageButton[3][3];
            gameBoards[i] = new GameBoard(this, layout);
            ibs = gameBoards[i].getImageButtonArray();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    // Creates unique button ids (100, 201, 302, 403, etc)
                    ibs[j][k].setId(((i + 1) * 100) + ibid);
                    ibs[j][k].setTag("0");
                    ibs[j][k].setOnClickListener(this);
                    ibid++;
                    if (ibid == 9) {
                        ibid = 0;
                    }
                }
            }
        }
    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        // Loop through gameboards
        for (int i = 0; i < no_of_gameboards; i++) {
            ibs = gameBoards[i].getImageButtonArray();

            // Loop through imagebuttons of current gameboard
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                }
            }
        }

        return false;
    }


    @Override
    public void onClick(View view) {
        handleButton(view);
        handleTurns();
    }

    private void handleTurns() {
        // Setup TextView for Turns
        tv = findViewById(R.id.current_player);
        if (playerTurn == 0) {
            tv.setText(R.string.p2_turn);
            playerTurn++;
        } else {
            tv.setText(R.string.p1_turn);
            playerTurn--;
        }
    }

    private void handleButton(View view) {
        ibid = 0;

        // Loop through gameboards
        for (int i = 0; i < no_of_gameboards; i++) {
            ibs = gameBoards[i].getImageButtonArray();

            // Loop through imagebuttons of current gameboard
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {

                    // If view matches unique ID (100, 201, 302, etc.)
                    if (view.getId() == ((((i + 1) * 100)) + ibid)) {
                        ibs[j][k].setImageResource(R.drawable.x);
                        ibs[j][k].setTag("1");
                        ibs[j][k].setOnClickListener(null);
                        checkWinCondition();
                    }
                    ibid++;
                    if (ibid == 9) {
                        ibid = 0;
                    }
                }
            }
        }
    }
}
