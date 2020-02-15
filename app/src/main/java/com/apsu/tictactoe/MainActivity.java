/*
Ethan McCrary
Tanner Jones
*/

package com.apsu.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int no_of_gameboards;
    int choice;

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
            // Start Numerical Game

            File save = new File(getFilesDir(), "numerical_save.txt");
            if (save.exists()) {
                // Create AlertDialog to Inform user of Loaded Game
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Saved Game");
                builder.setMessage("A current save already exists. It has been loaded.");

                // Setup builder for closing
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int choice) {
                        Intent intent = new Intent(getApplicationContext(), Numerical.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Intent intent = new Intent(getApplicationContext(), Numerical.class);
                startActivity(intent);
            }
        } else if (view.getId() == R.id.play_game2) {
            // Start Wild Game

            File save = new File(getFilesDir(), "wild_save.txt");
            if (save.exists()) {
                // Create AlertDialog to Inform user of Loaded Game
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Saved Game");
                builder.setMessage("A current save already exists. It has been loaded.");

                // Setup builder for closing
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int choice) {
                        Intent intent = new Intent(getApplicationContext(), Wild.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Intent intent = new Intent(getApplicationContext(), Wild.class);
                startActivity(intent);
            }

        } else if (view.getId() == R.id.play_game3) {

            File save = new File(getFilesDir(), "notakto_save.txt");
            if (save.exists()) {
                // Create AlertDialog to Inform user of Loaded Game
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Saved Game");
                builder.setMessage("A current save already exists. It has been loaded.");

                // Setup builder for closing
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int choice) {
                        Intent intent = new Intent(getApplicationContext(), Notakto.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            } else {

                // Create AlertDialog to get no_of_gameboards
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Number of Boards");
                builder.setSingleChoiceItems(R.array.no_of_boards, choice, null);

                // Setup builder for closing and saving data
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int choice) {
                        // Get Selected Item
                        ListView listView = ((AlertDialog) dialog).getListView();
                        Object checkedItem = listView.getAdapter().getItem(
                                listView.getCheckedItemPosition()
                        );

                        no_of_gameboards = Integer.valueOf((String) checkedItem);

                        // Start Notakto Game and pass in no_of_gameboards
                        Intent intent = new Intent(getApplicationContext(), Notakto.class);
                        intent.putExtra("NO_OF_GAMEBOARDS", no_of_gameboards);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        } else if (view.getId() == R.id.info_game1) {
            infoDialog("Numerical", R.string.NUMERICAL);

        } else if (view.getId() == R.id.info_game2) {
            infoDialog("Wild", R.string.WILD);

        } else {
            infoDialog("Notakto", R.string.NOTAKTO);
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
