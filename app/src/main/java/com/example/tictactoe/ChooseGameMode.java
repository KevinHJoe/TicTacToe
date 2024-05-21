package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChooseGameMode extends AppCompatActivity {

    //private Button[] buttons = new Button[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_game_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        buttons[0] = findViewById(R.id.pvp);
//        buttons[1] = findViewById(R.id.pvAi);
//
//        for (Button button : buttons) {
//            button.setOnClickListener(this);
//        }
        Button pvp = findViewById(R.id.pvp);
        Button pvAi = findViewById(R.id.pvAi);

        pvp.setOnClickListener(v -> startGame(true));
        pvAi.setOnClickListener(v -> startGame(false));
    }

//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(ChooseGameMode.this, MainActivity.class);
//        if (view.getId() == R.id.pvp) {
//            // Code to execute when the PvP button is clicked
//            intent.putExtra("isPVP", true);
//            startActivity(intent);
//        } else {
//            if (view.getId() == R.id.pvAi) {
//                // Code to execute when the PvAI button is clicked
//                intent.putExtra("isPVAi", false);
//                startActivity(intent);
//            }
//        }
//    }

    private void startGame(boolean gameMode){
        Intent intent = new Intent(ChooseGameMode.this,MainActivity.class);
        intent.putExtra("gameMode", gameMode);
        startActivity(intent);
        finish();
    }
}