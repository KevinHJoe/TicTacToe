package com.example.tictactoe;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class AiPlayer {

    public int playerAiPoint = 0;
    private TextView textViewPlayerAi;

    public AiPlayer() {
    }

    public int aiTurn(String[][] gameBoard, int difficulty){
        int move;
        if(difficulty == 2)
            move = HardaiTurn(gameBoard);
        else
            move = ExpertaiTurn(gameBoard);
        return move;
    }

    public int HardaiTurn(String[][] gameBoard) {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;
        int hardCounter = 0;
        List<Integer> topScores = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (gameBoard[i][j].isEmpty()) {
                    // Make the move
                    gameBoard[i][j] = "O";
                    // Compute the score using Minimax
                    int score = minimax(gameBoard,false);
                    // Undo the move
                    gameBoard[i][j] = "";
                    // If this score is better than the current best score, update best score and move
                    if (score > bestScore) {
                        bestScore = score;
                        move = 3 * i + j;
                        if(hardCounter < 6) {
                            topScores.add(move);
                            hardCounter++;
                        }
                    }
                }
            }
        }
        if(move == -1)  //draw
            return move;
        Random random = new Random();
        return topScores.get(random.nextInt(topScores.size()));   //this works, but need edge case to avoid draw crash
    }

    public int ExpertaiTurn(String[][] gameBoard) {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;
        for (int i = 0; i < 3; i++) {                                       // 1. build a hypothetical game board to predict the outcome of future moves
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (gameBoard[i][j].isEmpty()) {
                    // Make the move
                    gameBoard[i][j] = "O";                                  // 2. attempt move i j to predict if it will be a winning move
                    // Compute the score using Minimax
                    //int score = minimax(gameBoard, false);  // 3. minimax will run that move to fill out the board and return the score of that move
                    int score = minimax(gameBoard, false);
                    // Undo the move
                    gameBoard[i][j] = "";
                    // If this score is better than the current best score, update best score and move
                    if (score > bestScore) {                                // 4. if that score is > bestScore, it is determined to be a better move
                        bestScore = score;
                        move = 3 * i + j;
                    }
                }
            }
        }
        return move;                                                        // 5. the algorithm has iterated over every possible move and found the best move to use
    }
    private int minimax(String[][] gameBoard, boolean isMaxPlayer){
        //check terminal state
        String result = checkWinner(gameBoard);                         // 3. once the board has 3 in a row, this hypothetical game board is over
        if(result.equals("X"))                                              // result will hold the winning player and return a score
            return -1;                                                  //-1 = losing move
        else if(result.equals("O"))
            return 1;                                                   // 1 = winning move
        else if(result.equals("draw"))
            return 0;                                                   // 0 = draw move

        //create hypothetical moves
        if(isMaxPlayer){
            int bestMove = Integer.MIN_VALUE;                           //sets best score to the lowest int possible for worst case.
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(gameBoard[i][j].isEmpty()) {
                        gameBoard[i][j] = "O";                              // 2. O fills out a cell second, then linearly every other turn
                        // Compute the score using Minimax
                        int score = minimax(gameBoard, false);
                        gameBoard[i][j] = "";   //clear the board
                        bestMove = Math.max(score, bestMove);
                    }
                }
            }
            return bestMove;
        }else{
            int bestMove = Integer.MAX_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(gameBoard[i][j].isEmpty()) {
                        gameBoard[i][j] = "X";                              // 1. X fills out a cell first since the AiTurn method made a hypothetical move,
                        // Compute the score using Minimax                              //then X will continue linearly every other turn
                        int score = minimax(gameBoard, true);
                        gameBoard[i][j] = "";
                        bestMove = Math.min(score, bestMove);
                    }
                }
            }
            return bestMove;
        }

    }

    private String checkWinner(String[][] gameBoard) {
        // Check rows, columns, and diagonals for a win or a draw
        // Return "X" if player X wins, "O" if player O wins, "draw" if it's a draw, or "" if the game is still ongoing
        //check rows for winning moves
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0].equals(gameBoard[i][1]) && gameBoard[i][1].equals(gameBoard[i][2])) {
                if (gameBoard[i][0].equals("X")) {
                    return "X";                          //player 1 has 3 in a row
                } else if (gameBoard[i][0].equals("O")) {
                    return "O";                         //player ai has 3 in a row
                }
            }
        }
        //check col for winning moves
        for (int j = 0; j < 3; j++) {
            if (gameBoard[0][j].equals(gameBoard[1][j]) && gameBoard[1][j].equals(gameBoard[2][j])) {
                if (gameBoard[0][j].equals("X")) {
                    return "X";
                } else if (gameBoard[0][j].equals("O")) {
                    return "O";
                }
            }
        }
        //check diagonal right to left for winning moves
        if (gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][0])){
            if (gameBoard[0][2].equals("X")) {
                return "X";
            } else if (gameBoard[0][2].equals("O")) {
                return "O";
            }
        }
        //check diagonal left to right for winning moves
        if (gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[1][1].equals(gameBoard[2][2])) {
            if (gameBoard[0][0].equals("X")) {
                return "X";
            } else if (gameBoard[0][0].equals("O")) {
                return "O";
            }
        }

        // Check for a draw
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j].isEmpty()) {
                    return "";  // If there's an empty spot, the game is still ongoing
                }
            }
        }

        // If no empty spots are found and no player has won, it's a draw
        return "draw";
    }
}


