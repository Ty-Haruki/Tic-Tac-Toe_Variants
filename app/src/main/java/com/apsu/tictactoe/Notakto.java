package com.apsu.tictactoe;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Notakto extends AppCompatActivity implements View.OnClickListener  {

    /* Win condition
        1. Once all boards are completed, last player to play loses the game.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton button = new ImageButton(this);



/*
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        layout.setGravity(Gravity.CENTER);

        GameBoard board = new GameBoard();
*/


    }

    // Checks if win condition will be met.
    public boolean checkWinCondition(){
        /*
            1. Check if all boards are locked.
            2. if all boards are not locked/complete, return false.
            3. if all boards are locked/complete, return true.
         */

        return false;
    }


    @Override
    public void onClick(View view) {
        
    }
}
