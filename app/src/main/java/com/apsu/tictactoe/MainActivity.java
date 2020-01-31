/*
Ethan McCrary
Tanner Jones
*/

package com.apsu.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Listeners for Play Buttons
        findViewById(R.id.play_game1).setOnClickListener(this);
        findViewById(R.id.play_game2).setOnClickListener(this);
        findViewById(R.id.play_game3).setOnClickListener(this);

        // Setup Listener for Info Buttons
        findViewById(R.id.info_game1).setOnClickListener(this);
        findViewById(R.id.info_game2).setOnClickListener(this);
        findViewById(R.id.info_game3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // This is a lot of new activities.
        // Maybe we can pass in different information to just two activities -- game and info --
        // based on what the user presses.
        if (view.getId() == R.id.play_game1) {
            Log.i("PRESSED", "Game 1");
            //Start new Activity
        } else if (view.getId() == R.id.play_game2) {
            Log.i("PRESSED", "Game 2");
            //Start new Activity
        } else if (view.getId() == R.id.play_game3) {
            Log.i("PRESSED", "Game 3");
            //Start new Activity
        } else if (view.getId() == R.id.info_game1) {
            Log.i("PRESSED", "Info 1");
            //Start new Activity
        } else if (view.getId() == R.id.info_game2) {
            Log.i("PRESSED", "Info 2");
            //Start new Activity
        } else {
            Log.i("PRESSED", "Info 3");
            //Start new Activity
        }
    }
}
