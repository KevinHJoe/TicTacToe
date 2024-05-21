Tic Tac Toe Game

This is a simple Tic Tac Toe game implemented in Android. The game supports two modes: Player vs Player (PvP) and Player vs AI (PvAi).

Classes
The game consists of three main classes:
1. MainActivity: This is the main activity class that handles the game logic. It keeps track of the game state, handles button clicks, and updates the UI.
2. ChooseGameMode: This class is an activity that allows the user to choose the game mode (PvP or PvAi).
3. AiPlayer: This class represents the AI player in the PvAi mode. It decides the AI’s move based on the current state of the game board.

How to Play
When the game starts, the ChooseGameMode activity is launched. The user can choose to play in PvP mode or PvAi mode.
In PvP mode, two players take turns to place their mark (either “X” or “O”) on the grid. The first player to get three of their marks in a row (vertically, horizontally, or diagonally) wins the game.
In PvAi mode, the user plays against the AI. The AI chooses its move based on the current state of the game board. The AI’s strategy is to choose any open spot on the board.
