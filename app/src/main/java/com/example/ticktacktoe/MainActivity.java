package com.example.ticktacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 : red, 1 : yellow, 2 : empty;
    MediaPlayer mediaPlayer;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    int count; // for reset button
    Boolean activeGame = true;
    Boolean rstBtnAppear; // fro reset button show after non match;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tapCounter = Integer.parseInt(counter.getTag().toString());
        rstBtnAppear = true;

        if (gameState[tapCounter] == 2 && activeGame) {
            counter.setTranslationY(-1500);
            gameState[tapCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setVisibility(View.VISIBLE);
                    winnerTextView.setText(winner + " has won..!");
                    activeGame = false;
                    rstBtnAppear = false;

                    mediaPlayer = MediaPlayer.create(this, R.raw.winning_sound);
                    mediaPlayer.start();


                    TextView playAgainTextView = (TextView) findViewById(R.id.playAgainTextView);
                    playAgainTextView.setVisibility(View.VISIBLE);

                    Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
                    playAgainBtn.setVisibility(View.VISIBLE);
                }
            }
                count = 0;
            for(int i=0; i<gameState.length; i++){
                if(gameState[i] !=2){
                    count++;
                }
            }
            if(count == 9 && rstBtnAppear){
                Button resetBtn = (Button) findViewById(R.id.resetBtn);
                resetBtn.setVisibility(View.VISIBLE);
            }

            counter.animate().rotation(3600).setDuration(300).translationYBy(1500);
        }
    }

    public void playAgain(View view) {
        TextView playAgainTextView = (TextView) findViewById(R.id.playAgainTextView);
        playAgainTextView.setVisibility(View.INVISIBLE);

        Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        playAgainBtn.setVisibility(View.INVISIBLE);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        count = 0;
        activeGame = true;
        Arrays.fill(gameState, 2);
    }

    public void reset(View view) {
        TextView playAgainTextView = (TextView) findViewById(R.id.playAgainTextView);
        playAgainTextView.setVisibility(View.INVISIBLE);

        Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        playAgainBtn.setVisibility(View.INVISIBLE);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);

        Button resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        activeGame = true;
        count = 0;
        Arrays.fill(gameState, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}