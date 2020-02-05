package com.apsu.tictactoe;

import android.content.Context;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GameBoard {
    private ImageButton[][] ibs = new ImageButton[3][3];
    private int ibid = 0;

    GameBoard(Context context, ConstraintLayout layout) {
        GridLayout gridLayout = new GridLayout(context);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.setGravity(Gravity.CENTER);
        layoutParams.rowSpec = GridLayout.spec(0, 3);
        layoutParams.columnSpec = GridLayout.spec(0, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ibs[i][j] = new ImageButton(context);
                ibs[i][j].setBackgroundResource(R.drawable.square);
                ibs[i][j].setId(ibid);
                gridLayout.addView(ibs[i][j]);
            }
        }
        layout.addView(gridLayout);
    }

    ImageButton[][] getImageButtonArray() {
        return ibs;
    }

    void setImageResource(int x, int y, int imageResource) {
        ibs[x][y].setImageResource(imageResource);
    }
}
