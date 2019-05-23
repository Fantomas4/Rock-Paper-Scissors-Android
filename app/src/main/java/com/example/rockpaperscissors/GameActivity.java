package com.example.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView playerNameTextView;
    TextView playerPointsTextView;
    TextView notificationTextView;
    ImageView playerChoiceImageView;
    ImageView botChoiceImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerNameTextView = (TextView) findViewById(R.id.playerNameTextView);
        playerPointsTextView = (TextView) findViewById(R.id.playerPointsTextView);
        notificationTextView = (TextView) findViewById(R.id.notificationTextView);

//        Bundle bundle = getIntent().getExtras();
//        int value = -1; // or other values
//        if(bundle != null)
//            value = bundle.getInt("key");

    }
}
