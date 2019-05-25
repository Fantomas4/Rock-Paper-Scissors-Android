package com.example.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView roundCounterTextView;
    TextView userNameTextView;
    TextView userPointsTextView;
    TextView botNameTextView;
    TextView botPointsTextView;
    ImageView userChoiceImageView;
    ImageView botChoiceImageView;
    TextView notificationTextView;
    ImageButton rockChoiceImageButton;
    ImageButton paperChoiceImageButton;
    ImageButton scissorsChoiceImageButton;
    Button actionButton;

    int roundLimit;
    int roundCounter;
    int userPoints;
    int botPoints;

    boolean roundEnded;


    private void userWinsRound() {
        userPoints++;
        notificationTextView.setText("You won this round!");

    }


    private void botWinsRound() {
        botPoints++;
        notificationTextView.setText("You lost this round!");
    }


    private void tieInRound() {
        notificationTextView.setText("There is a tie!");
    }


    private void playNextRound(PlayerChoice userChoice) {

        // First we get a random choice for the bot
        PlayerChoice botChoice = PlayerChoice.getRandomChoice();

        // Then we determine the winner by comparing userChoice
        // and botChoice, based on the game's rules
        if (userChoice == PlayerChoice.ROCK) {
            if (botChoice == PlayerChoice.ROCK) {
                tieInRound();
            } else if (botChoice == PlayerChoice.PAPER) {
                botWinsRound();
            } else if (botChoice == PlayerChoice.SCISSORS) {
                userWinsRound();
            }
        } else if (userChoice == PlayerChoice.PAPER) {
            if (botChoice == PlayerChoice.ROCK) {
                userWinsRound();
            } else if (botChoice == PlayerChoice.PAPER) {
                tieInRound();
            } else if (botChoice == PlayerChoice.SCISSORS) {
                botWinsRound();
            }
        } else if (userChoice == PlayerChoice.SCISSORS) {
            if (botChoice == PlayerChoice.ROCK) {
                botWinsRound();
            } else if (botChoice == PlayerChoice.PAPER) {
                userWinsRound();
            } else if (botChoice == PlayerChoice.SCISSORS) {
                tieInRound();
            }
        }

        // We increment the current round counter
        roundCounter++;
    }


    private void updateUI() {
        roundCounterTextView.setText(String.valueOf(roundCounter));
        userPointsTextView.setText(String.valueOf(userPoints));
        botPointsTextView.setText(String.valueOf(botPoints));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        roundCounterTextView = findViewById(R.id.roundCounterTextView);
        userNameTextView = findViewById(R.id.userNameTextView);
        userPointsTextView = findViewById(R.id.userPointsTextView);
        userChoiceImageView = findViewById(R.id.userChoiceImageView);

        botNameTextView = findViewById(R.id.botNameTextView);
        botPointsTextView = findViewById(R.id.botPointsTextView);
        botChoiceImageView = findViewById(R.id.botChoiceImageView);

        notificationTextView = findViewById(R.id.notificationTextView);

        rockChoiceImageButton = findViewById(R.id.rockChoiceButton);
        rockChoiceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoiceImageView.setImageResource(R.drawable.rock_choice);
            }
        });
        paperChoiceImageButton = findViewById(R.id.paperChoiceButton);
        paperChoiceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoiceImageView.setImageResource(R.drawable.paper_choice);
            }
        });
        scissorsChoiceImageButton = findViewById(R.id.scissorsChoiceButton);
        scissorsChoiceImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoiceImageView.setImageResource(R.drawable.scissors_choice);
            }
        });

        actionButton.setText("Submit!");
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        Bundle bundle = getIntent().getExtras();
//        if(bundle != null)
        roundLimit = bundle.getInt("roundLimit");
        userNameTextView.setText(bundle.getString("userName"));
        botNameTextView.setText("Bot");

        roundCounter = 0;
        userPoints = 0;
        botPoints = 0;

        roundEnded = false;

        updateUI();



    }

}
