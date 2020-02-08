package com.apsu.tictactoe;

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

public class Notakto extends AppCompatActivity implements View.OnClickListener  {

    TextView tv;
    int no_of_gameboards;
    int playerTurn;
    GameBoard[] gameBoards;
    ImageButton ibs[][];
    int ibid = 0;


    /* Win condition
        1. Once all boards are completed, last player to play loses the game.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Get no_of_gameboards from intent
        Intent intent = getIntent();
        no_of_gameboards = intent.getIntExtra("NO_OF_GAMEBOARDS", 1);
        playerTurn = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notakto_layout);
        LinearLayout layout = findViewById(R.id.notakto_layout);

        gameBoards = new GameBoard[no_of_gameboards];

        for (int i = 0; i < no_of_gameboards; i++) {
            ibs = new ImageButton[3][3];
            gameBoards[i] = new GameBoard(this, layout);
            ibs = gameBoards[i].getImageButtonArray();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // Creates unique button ids (100, 201, 302, 403, etc)
                    ibs[j][k].setId(((i + 1) * 100) + ibid);
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
