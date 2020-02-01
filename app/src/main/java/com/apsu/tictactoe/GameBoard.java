package com.apsu.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class GameBoard extends AppCompatActivity implements View.OnClickListener {

    ImageButton[][] ib;
    int ibid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create Linear Layout to hold rows
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        layout.setGravity(Gravity.CENTER);
        createGameBoard(layout);
        setContentView(layout);
    }

    void createGameBoard(LinearLayout layout) {
        // Create Linear Layout for each row
        LinearLayout row1 = new LinearLayout(this);
        row1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        row1.setGravity(Gravity.CENTER);

        LinearLayout row2 = new LinearLayout(this);
        row2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        row2.setGravity(Gravity.CENTER);

        LinearLayout row3 = new LinearLayout(this);
        row3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        row3.setGravity(Gravity.CENTER);

        ib = new ImageButton[3][3];
        ibid = 0;
        generateImageButtons(row1, row2, row3);

        // Add rows to container
        layout.addView(row1);
        layout.addView(row2);
        layout.addView(row3);
    }

    private void generateImageButtons(LinearLayout row1, LinearLayout row2, LinearLayout row3) {
        // Initialize and setup each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ib[i][j] = new ImageButton(this);
                ib[i][j].setBackgroundResource(R.drawable.square);
                ib[i][j].setLayoutParams(new LayoutParams(200,200));
                ib[i][j].setOnClickListener(this);
                ib[i][j].setId(ibid);
                ibid++;

                // Add buttons to rows
                if (i == 0) {
                    row1.addView(ib[i][j]);
                } else if (i == 1) {
                    row2.addView(ib[i][j]);
                } else {
                    row3.addView(ib[i][j]);
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        if (view.getId() == 0) {
            Log.i("ID", Integer.toString(ib[0][0].getId()));
        } else if (view.getId() == 1) {
            Log.i("ID", Integer.toString(ib[0][1].getId()));
        } else if (view.getId() == 2) {
            Log.i("ID", Integer.toString(ib[0][2].getId()));
        } else if (view.getId() == 3) {
            Log.i("ID", Integer.toString(ib[1][0].getId()));
        } else if (view.getId() == 4) {
            Log.i("ID", Integer.toString(ib[1][1].getId()));
        } else if (view.getId() == 5) {
            Log.i("ID", Integer.toString(ib[1][2].getId()));
        } else if (view.getId() == 6) {
            Log.i("ID", Integer.toString(ib[2][0].getId()));
        } else if (view.getId() == 7) {
            Log.i("ID", Integer.toString(ib[2][1].getId()));
        } else if (view.getId() == 8) {
            Log.i("ID", Integer.toString(ib[2][2].getId()));
        }
    }
}
