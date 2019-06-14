package com.example.rockpaperscissors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The class used by the MainActivity screen, which is the first screen of the application shown to the user.
 */
public class MainActivity extends AppCompatActivity {

    private EditText nameInputField;
    private EditText pointLimitInputField;
    private TextView notificationMsg;
    private Button startGameButton;

    boolean doubleBackToExitPressedOnce = false;


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
     * A method used to check the validity of the user's name and round limit input.
     * @param userName The name given by the user.
     * @param roundLimitInput The round limit given by the user.
     * @return True if the user's input is valid, or false if the user's input is invalid.
     */
    private boolean checkUserInput(String userName, String roundLimitInput) {

        boolean userNameAccepted = false;
        boolean roundLimitAccepted = false;

        String notification = "";

        if (userName.length() > 8) {
            notification += "- Wow! Try using a shorter name!\n(Up to 8 characters)\n";
            nameInputField.setText("");
        } else if (userName.length() == 0) {
            notification += "- You have to input a name!\n\n";
            nameInputField.setText("");
        } else {
            userNameAccepted = true;
        }


        // Check if user round limit input is an integer
        try {
            int roundLimit;
            roundLimit = Integer.parseInt(roundLimitInput);
            if (roundLimit <= 0) {
                notification += "- You have to enter a number > 0 \nas a round limit!\n";
                pointLimitInputField.setText("");
            } else {
                roundLimitAccepted = true;
            }
        } catch (NumberFormatException e) {
            notification += "- You have to enter an integer \nfor the number of rounds!\n";
        }

        notificationMsg.setText(notification);

        return userNameAccepted && roundLimitAccepted;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInputField = findViewById(R.id.nameInputField);
        pointLimitInputField = findViewById(R.id.pointLimitInputField);
        notificationMsg = findViewById(R.id.notificationMsg);

        startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = nameInputField.getText().toString();
                String roundLimit = pointLimitInputField.getText().toString();

                boolean inputAccepted = checkUserInput(userName, roundLimit);

                if (inputAccepted) {
                    // User input is valid so we keep the input data and switch to the GameActivity screen.
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    bundle.putString("roundLimit", roundLimit);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
