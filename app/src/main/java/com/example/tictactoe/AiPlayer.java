package com.example.tictactoe;

import android.widget.Button;
import android.widget.TextView;

public class AiPlayer {

    public int playerAiPoint = 0;
    private TextView textViewPlayerAi;
    public AiPlayer() {
    }

    private String[][] buildGameBoard(Button[] buttons){
        String[][] field = new String[3][3];

        // Fill the array with the current state of the game board
        // Convert the 1D index to 2D coordinates and get the text from the corresponding button
        // This text represents the current state of the cell (either "X", "O", or an empty string)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i * 3 + j].getText().toString();
            }
        }
        return field;
    }

    //choose button to click
    public int aiTurn(Button[] buttons){
        String[][] gameBoard;
        gameBoard = buildGameBoard(buttons);
        return decideMove(gameBoard);
    }
    //check which buttons have already been clicked
    //for now, just click any button that is open
    private int decideMove(String[][] gameBoard){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(gameBoard[i][j].isEmpty()){
                    return i * 3 + j;           //flattening the 2d array elements to choose the
                }                               //corresponding element in the 1d array buttons
            }
        }
        return 0;
    }


    //what was player1's last turn
    //compare it with their turn before that
    //detect any pattern
    //example:
    //if turn 1 is 0.0
    //and turn 2 is 0.1
    //and button 0.2 is open
    //choose button 0.2

}
