/*
Ethan McCrary
Tanner Jones
*/

package com.apsu.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
            // Create Dialog for Game Information
            infoDialog("Game 1", R.string.game1_info);
        } else if (view.getId() == R.id.info_game2) {
            // Create Dialog for Game Information
            infoDialog("Game 2", R.string.game2_info);
        } else {
            // Create Dialog for Game Information
            infoDialog("Game 3", R.string.game3_info);
        }
    }

    private void infoDialog(String s, int string_id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Alert Dialog Message
        builder.setMessage(string_id);
        // Alert Dialog Title
        builder.setTitle(s);
        // Close Button
        builder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked Close Button
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
