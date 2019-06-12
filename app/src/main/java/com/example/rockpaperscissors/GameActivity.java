package com.example.rockpaperscissors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The class used by the GameActivity screen, which is the the screen where the actual game takes place.
 */
public class GameActivity extends AppCompatActivity {

    private TextView roundCounterTextView;
    private TextView userNameTextView;
    private TextView userPointsTextView;
    private TextView botNameTextView;
    private TextView botPointsTextView;
    private ImageView userChoiceImageView;
    private ImageView botChoiceImageView;
    private TextView notificationTextView;
    private ImageButton rockChoiceImageButton;
    private ImageButton paperChoiceImageButton;
    private ImageButton scissorsChoiceImageButton;
    private Button actionButton;

    private int roundLimit;
    private int roundsCompleted;
    private String userName;
    private String botName;
    private int userPoints;
    private int botPoints;
    private String availableAction;
    private String notificationMsg;
    private PlayerChoice userChoice;
    private PlayerChoice botChoice;
    private boolean roundEnded;
    private boolean gameEnded;
    private boolean doubleBackToExitPressedOnce = false;

    /**
     * A method used to determine the Android UI's "Back Button" behavior.
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    /**
     * A method called by the UI every time the "Rock" choice image button is clicked
     */
    public void clickRockChoice(View view) {
        userChoice = PlayerChoice.ROCK;
        userChoiceImageView.setImageResource(R.drawable.rock_choice);
        actionButton.setEnabled(true);
    }

    /**
     * A method called by the UI every time the "Paper" choice image button is clicked
     */
    public void clickPaperChoice(View view) {
        userChoice = PlayerChoice.PAPER;
        userChoiceImageView.setImageResource(R.drawable.paper_choice);
        actionButton.setEnabled(true);
    }

    /**
     * A method called by the UI every time the "Scissors" choice image button is clicked
     */
    public void clickScissorsChoice(View view) {
        userChoice = PlayerChoice.SCISSORS;
        userChoiceImageView.setImageResource(R.drawable.scissors_choice);
        actionButton.setEnabled(true);
    }

    /**
     * A method called by the UI every time the action button is clicked
     */
    public void clickActionButton(View view) {
        performUserAction();
    }

    /**
     * A method that sets the "setEnabled" property of all the choice image buttons
     * to true when called, in order to enable them.
     */
    private void enableUserChoiceIcons() {
        rockChoiceImageButton.setEnabled(true);
        paperChoiceImageButton.setEnabled(true);
        scissorsChoiceImageButton.setEnabled(true);
    }

    /**
     * A method that sets the "setEnabled" property of all the choice image buttons
     * to false when called, in order to disable them.
     */
    private void disableUserChoiceIcons() {
        rockChoiceImageButton.setEnabled(false);
        paperChoiceImageButton.setEnabled(false);
        scissorsChoiceImageButton.setEnabled(false);
    }

    private void performUserAction() {
        // Check if the game has ended or not
        if (!gameEnded) {
            if (!roundEnded) {
                // If a game round is currently in process
                playCurrentRound();
                roundEnded = true;
                availableAction = "Continue!";
                updateUI();

            } else {
                // If no round in currently in process
                roundsCompleted++;

                // Check if the round limit has been reached
                if (roundsCompleted == roundLimit) {
                    if (userPoints > botPoints) {
                        gameEnded = true;
                        notificationMsg = "Congratulations! You are the king!";
                        availableAction = "Start a new game!";
                    } else if (userPoints < botPoints) {
                        gameEnded = true;
                        notificationMsg = "Oops! You have lost and now the bot is the king...";
                        availableAction = "Start a new game";
                    } else {
                        // We increment roundLimit by 1 so that an extra round is added until
                        // a clear winner occurs
                        roundEnded = false;
                        roundLimit++;
                        notificationMsg = "Wow! There is a tie and the final round has been reached, so an extra round has been added!\n\nChoose your move:";
                        availableAction = "Submit!";
                    }
                } else {
                    // Proceed to the next round
                    roundEnded = false;
                    notificationMsg = "Choose your move:";
                    availableAction = "Submit!";
                }
                updateUI();
            }
        } else {
            // The user has clicked the "Start new game" button
            // so we switch to the MainActivity's screen
            startActivity(new Intent(GameActivity.this, MainActivity.class));
            finish();
        }

    }

    /**
     * A method called when it is determined that the user is
     * the winner of the current game round.
     */
    private void userWinsRound() {
        userPoints++;
        notificationMsg = "You won this round!";
    }

    /**
     * A method called when it is determined that the bot is
     * the winner of the current game round.
     */
    private void botWinsRound() {
        botPoints++;
        notificationMsg = "You lost this round!";
    }

    /**
     * A method called when it is determined that there is a
     * tie between the user and the bot at the current game round.
     */
    private void tieInRound() {
        notificationMsg = "There is a tie!";
    }

    /**
     * A method called to determine the winner of the current
     * game round.
     */
    private void playCurrentRound() {
        // First we get a random choice for the bot
        botChoice = PlayerChoice.getRandomChoice();

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
    }

    /**
     * A method called to update the UI's elements according to the current
     * state of the game and its data.
     */
    private void updateUI() {
        int currentRound;

        if (gameEnded) {
            currentRound = roundsCompleted;
        } else {
            currentRound = roundsCompleted + 1;
        }

        roundCounterTextView.setText(String.valueOf(currentRound));
        userNameTextView.setText(userName);
        userPointsTextView.setText(String.valueOf(userPoints));
        botNameTextView.setText(botName);
        botPointsTextView.setText(String.valueOf(botPoints));
        notificationTextView.setText(notificationMsg);
        actionButton.setText(availableAction);

        // Check if the game has ended or not
        if (!gameEnded) {
            // We check the round state we are currently in
            if (roundEnded) {
                disableUserChoiceIcons();

                // Update the bot's choice icon with the current random choice
                if (botChoice == PlayerChoice.ROCK) {
                    botChoiceImageView.setImageResource(R.drawable.rock_choice);
                } else if (botChoice == PlayerChoice.PAPER) {
                    botChoiceImageView.setImageResource(R.drawable.paper_choice);
                } else if (botChoice == PlayerChoice.SCISSORS) {
                    botChoiceImageView.setImageResource(R.drawable.scissors_choice);
                }
            } else {
                enableUserChoiceIcons();

                // Update the user's choice icon with the question mark icon
                userChoice = PlayerChoice.NONE;
                userChoiceImageView.setImageResource(R.drawable.question_mark);

                // Update the bot's choice icon with the question mark icon
                botChoice = PlayerChoice.NONE;
                botChoiceImageView.setImageResource(R.drawable.question_mark);

                // Disable the action button currently set to "Submit!"
                // until the user clicks on one of the available moves
                actionButton.setEnabled(false);
            }
        } else {
            disableUserChoiceIcons();
            actionButton.setText(availableAction);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundLimit", roundLimit);
        outState.putInt("roundsCompleted", roundsCompleted);
        outState.putString("userName", userName);
        outState.putString("botName", botName);
        outState.putInt("userPoints", userPoints);
        outState.putInt("botPoints", botPoints);

        outState.putString("userChoice", userChoice.name());
        outState.putString("botChoice", botChoice.name());

        outState.putString("notificationMsg", notificationMsg);
        outState.putString("availableAction", availableAction);


        outState.putBoolean("roundEnded", roundEnded);
        outState.putBoolean("gameEnded", gameEnded);
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
        notificationTextView.setMovementMethod(new ScrollingMovementMethod());

        rockChoiceImageButton = findViewById(R.id.rockChoiceButton);
        paperChoiceImageButton = findViewById(R.id.paperChoiceButton);
        scissorsChoiceImageButton = findViewById(R.id.scissorsChoiceButton);

        actionButton = findViewById(R.id.actionButton);

        // If we enter GameActivity with a saved instance
        // of the UI
        if (savedInstanceState != null) {
            roundLimit = savedInstanceState.getInt("roundLimit");
            roundsCompleted = savedInstanceState.getInt("roundsCompleted");
            userName = savedInstanceState.getString("userName");
            botName = savedInstanceState.getString("botName");
            userPoints = savedInstanceState.getInt("userPoints");
            botPoints = savedInstanceState.getInt("botPoints");

            userChoice = PlayerChoice.valueOf(savedInstanceState.getString("userChoice"));
            botChoice = PlayerChoice.valueOf(savedInstanceState.getString("botChoice"));

            notificationMsg = savedInstanceState.getString("notificationMsg");
            availableAction = savedInstanceState.getString("availableAction");

            roundEnded = savedInstanceState.getBoolean("roundEnded");
            gameEnded = savedInstanceState.getBoolean("gameEnded");

        } else {
            // Else, if we enter GameActivity for the first time
            notificationMsg = "Choose your move:";

            availableAction = "Submit!";

            Bundle bundle = getIntent().getExtras();

            roundLimit = Integer.parseInt(bundle.getString("roundLimit"));
            userName = bundle.getString("userName");
            botName = "Bot";
            roundsCompleted = 0;
            userPoints = 0;
            botPoints = 0;

            userChoice = PlayerChoice.NONE;
            botChoice = PlayerChoice.NONE;

            roundEnded = false;
            gameEnded = false;
        }
        updateUI();
    }
}
