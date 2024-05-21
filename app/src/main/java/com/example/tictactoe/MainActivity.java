package com.example.tictactoe;


import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity implements View.OnClickListener {

    // Declare the buttons for the Tic Tac Toe grid
    private Button[] buttons = new Button[9];
    private boolean player1Turn = true;     // Boolean to keep track of whose turn it is
    private int roundCount;                 // Integer to keep track of the number of rounds
    private int player1Points;              // Integers to keep track of the points for each player
    private int player2Points;
    private TextView textViewPlayer1;       // TextViews to display the points for each player
    private TextView textViewPlayer2;
    private TextView countdownText;         // TextView to display the countdown
    private boolean playerMode = true;      //if true it's 2 players, if false it's player vs ai
    AiPlayer ai = new AiPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //playerMode = getIntent().getBooleanExtra("isPVAi", false);
        playerMode = getIntent().getBooleanExtra("gameMode", true);


        // Initialize the TextViews
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        // Initialize the countdown TextView
        countdownText = findViewById(R.id.countdown_text);

        // Initialize the buttons and set their onClickListeners
        buttons[0] = findViewById(R.id.button_0);
        buttons[1] = findViewById(R.id.button_1);
        buttons[2] = findViewById(R.id.button_2);
        buttons[3] = findViewById(R.id.button_3);
        buttons[4] = findViewById(R.id.button_4);
        buttons[5] = findViewById(R.id.button_5);
        buttons[6] = findViewById(R.id.button_6);
        buttons[7] = findViewById(R.id.button_7);
        buttons[8] = findViewById(R.id.button_8);

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        // Initialize the reset button and set its onClickListener
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // If the button is already clicked, don't do anything
        if (!((Button) v).getText().toString().isEmpty()) {
            return;
        }
        // Set the text of the button based on whose turn it is
        if(playerMode) {            //if player vs player
            if (player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }
        }
        else{                       //else if player vs ai
            if (player1Turn) {
                ((Button) v).setText("X");
                player1Turn = false;
                aiPlayerTurn();
            }
        }
        // Increment the round count
        roundCount++;

        // Check if there's a win
        if (checkForWin()) {
            if(playerMode) {        //if player vs player
                if (player1Turn) {
                    player1Wins();
                } else {
                    player2Wins();
                }
            }else{                  //else if player vs ai
                if (player1Turn) {
                    player1Wins();
                } else {
                    playerAiWins();
                }
            }
        } else if (roundCount == 9) {   // If all buttons are clicked and there's no win, it's a draw
            draw();
        } else {                        // If there's no win and it's not a draw, switch the turn to the other player
            player1Turn = !player1Turn;
        }
    }

    private void aiPlayerTurn(){
        int e = ai.aiTurn(buttons);
        buttons[e].setText("O");        //ai chooses a button at the eth element
    }

    // This method checks if there's a win
    private boolean checkForWin() {
        // Create a 2D array to represent the game board
        String[][] field = new String[3][3];

        // Fill the array with the current state of the game board
        // Convert the 1D index to 2D coordinates and get the text from the corresponding button
        // This text represents the current state of the cell (either "X", "O", or an empty string)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i * 3 + j].getText().toString();
            }
        }

        // Check each row for a win
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].isEmpty()) {
                return true;
            }
        }

        // Check each column for a win
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].isEmpty()) {
                return true;
            }
        }

        // Check the main diagonal for a win
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].isEmpty()) {
            return true;
        }

        // Check the other diagonal for a win
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].isEmpty()) {
            return true;
        }

        // If no win is found, return false
        return false;
    }


    private void player1Wins() {
        player1Points++;
        updatePointsText();
        startCountdown("Player 1 Wins!\n\n");
    }

    private void player2Wins() {
        player2Points++;
        updatePointsText();
        startCountdown("Player 2 Wins!\n\n");
    }

    private void playerAiWins(){
        ai.playerAiPoint++;
        updatePointsText();
        startCountdown("Player Ai Wins!\n\n");
    }

    private void draw() {
        startCountdown("It's a Draw!\n\n");
    }

    private void startCountdown(String p) {
        countdownText.setVisibility(View.VISIBLE);
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                //print who won and the countdown
                countdownText.setText(p + "Next game: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countdownText.setVisibility(View.GONE);
                resetBoard();
            }
        }.start();
    }

    private void updatePointsText() {
        if(playerMode) {
            textViewPlayer1.setText("Player 1: " + player1Points);
            textViewPlayer2.setText("Player 2: " + player2Points);
        }else{
            textViewPlayer1.setText("Player 1: " + player1Points);
            textViewPlayer2.setText("Player Ai: " + ai.playerAiPoint);
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");     //clear each button for next game
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        //start by choosing game mode
        Intent intent = new Intent(MainActivity.this, ChooseGameMode.class);
        startActivity(intent);
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
}
//