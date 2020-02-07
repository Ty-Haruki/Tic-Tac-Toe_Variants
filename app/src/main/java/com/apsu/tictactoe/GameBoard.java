package com.apsu.tictactoe;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class GameBoard {
    // Create 2D array of ImageButtons
    private ImageButton[][] ibs = new ImageButton[3][3];
    private int ibid = 0;

    // Must pass in context from mainActivity
    GameBoard(Context context, LinearLayout layout) {
        // Create LinearLayout for Columns
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        layoutParams.setMargins(10,10,10,10);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 3; i++) {
            // Create LinearLayout for Rows
            LinearLayout row = new LinearLayout(context);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            row.setGravity(Gravity.CENTER);
            row.setPadding(0, 0, 0, 0);

            for (int j = 0; j < 3; j++) {
                // Generate 3x3 ImageButtons
                ibs[i][j] = new ImageButton(context);
                ibs[i][j].setBackgroundResource(R.drawable.square);
                ibs[i][j].setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                ibs[i][j].setId(ibid);

                // Add Buttons to Row
                row.addView(ibs[i][j]);
            }
            // Add Rows to Columns
            linearLayout.addView(row);
        }
        // Add Grid to Original Layout
        layout.addView(linearLayout, layoutParams);
    }

    // Returns the entire ibs array
    public ImageButton[][] getImageButtonArray() {
        return ibs;
    }

    // Sets the imageResource for each ImageButton
    public void setDrawable(int x, int y, Drawable imageResource) {
        ibs[x][y].setImageDrawable(imageResource);
    }
}
