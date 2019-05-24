package com.example.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView playerNameTextView;
    TextView playerPointsTextView;
    TextView botNameTextView;
    TextView botPointsTextView;
    ImageView playerChoiceImageView;
    ImageView botChoiceImageView;
    TextView notificationTextView;

    int pointLimit;
    int playerPoints;
    int botPoints;

    private void updatePointsUI() {
        playerPointsTextView.setText(String.valueOf(playerPoints));
        botPointsTextView.setText(String.valueOf(botPoints));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerNameTextView = (TextView) findViewById(R.id.playerNameTextView);
        playerPointsTextView = (TextView) findViewById(R.id.playerPointsTextView);
        botNameTextView = (TextView) findViewById(R.id.botNameTextView);
        botPointsTextView = (TextView) findViewById(R.id.botPointsTextView);
        notificationTextView = (TextView) findViewById(R.id.notificationTextView);

        Bundle bundle = getIntent().getExtras();
//        if(bundle != null)
        pointLimit = bundle.getInt("pointLimit");
        playerNameTextView.setText(bundle.getString("playerName"));
        botNameTextView.setText("Bot");

        playerPoints = 0;
        botPoints = 0;

        updatePointsUI();



    }

}
