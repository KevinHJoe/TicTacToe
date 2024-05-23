# Android app Tic Tac Toe Game

This is a simple implementation of the classic game Tic Tac Toe in Java for Android. The game features an AI player that uses the Minimax algorithm to determine its moves.

## Classes

### AiPlayer

The `AiPlayer` class is responsible for the logic of the AI player in the game. It has two main methods: `HardaiTurn` and `ExpertaiTurn`, which represent two difficulty levels for the AI player. Both methods use the Minimax algorithm to determine the best move for the AI player.

The `HardaiTurn` method introduces some randomness to the AI's moves, making the game more unpredictable and challenging.

The `ExpertaiTurn` method always chooses the move with the highest score according to the Minimax algorithm, making it a very tough opponent to beat.

The `minimax` method is a private method used by both `HardaiTurn` and `ExpertaiTurn` to implement the Minimax algorithm. It recursively checks all possible moves and their outcomes to choose the best one.

The `checkWinner` method is used to check the game board for a win or a draw.

### ChooseGameMode

The `ChooseGameMode` class is an `AppCompatActivity` that allows the user to choose between two game modes: Player vs Player (PvP) and Player vs AI (PvAI). The chosen game mode is then passed to the `MainActivity` class through an `Intent`.

### MainActivity

The `MainActivity` class is the main activity of the game. It handles the game logic, updates the game board, and keeps track of the players' scores. It also handles the game mode (Player vs Player or Player vs AI) and implements the game's countdown feature.

## How to Run

You can run this game on any Android device or emulator. Simply import the project into your preferred IDE (like Android Studio), build the project, and run it on your device or emulator.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue.

## License

This project is licensed under the MIT License.
