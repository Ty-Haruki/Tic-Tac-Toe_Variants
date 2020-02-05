package com.apsu.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class GameBoard implements View.OnClickListener {
    private ImageButton[][] ibs = new ImageButton[3][3];

    GameBoard(Context context) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ibs[i][j] = new ImageButton(context);
                ibs[i][j].setBackgroundResource(R.drawable.square);
                ibs[i][j].setId(i+j);
                ibs[i][j].setOnClickListener(this);
                Log.i("ID", Integer.toString(ibs[i][j].getId()));
            }
        }
    }

    ImageButton[][] getImageButtonArray() {
        return ibs;
    }

/*
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
    */

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        if (view.getId() == 0) {
            Log.i("ID", Integer.toString(ibs[0][0].getId()));
        } else if (view.getId() == 1) {
            Log.i("ID", Integer.toString(ibs[0][1].getId()));
        } else if (view.getId() == 2) {
            Log.i("ID", Integer.toString(ibs[0][2].getId()));
        } else if (view.getId() == 3) {
            Log.i("ID", Integer.toString(ibs[1][0].getId()));
        } else if (view.getId() == 4) {
            Log.i("ID", Integer.toString(ibs[1][1].getId()));
        } else if (view.getId() == 5) {
            Log.i("ID", Integer.toString(ibs[1][2].getId()));
        } else if (view.getId() == 6) {
            Log.i("ID", Integer.toString(ibs[2][0].getId()));
        } else if (view.getId() == 7) {
            Log.i("ID", Integer.toString(ibs[2][1].getId()));
        } else if (view.getId() == 8) {
            Log.i("ID", Integer.toString(ibs[2][2].getId()));
        }
    }
}
