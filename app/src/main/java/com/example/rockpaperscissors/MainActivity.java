package com.example.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nameInputField;
    EditText pointLimitInputField;
    TextView notificationMsg;
    Button startGameButton;


    private boolean checkUserInput(String userName, String roundLimitInput) {

        boolean userNameAccepted = false;
        boolean roundLimitAccepted = false;

        String notification = "";

        if (userName.length() > 25) {
            notification += "Wow! Try using a shorter name!\n";
            nameInputField.setText("");
        } else if (userName.length() == 0) {
            notification += "You have to input a name!\n";
            nameInputField.setText("");
        } else {
            userNameAccepted = true;
        }


        // Check if user round limit input is an integer

        try {
            int roundLimit;
            roundLimit = Integer.parseInt(roundLimitInput);
            if (roundLimit <= 0) {
                notification += "No no no! You have to enter a number > 0 \nas a round limit!\n";
                pointLimitInputField.setText("");
            } else {
                roundLimitAccepted = true;
            }
        } catch (NumberFormatException e) {
            notification += "Nope. You have to enter an integer \nas round limit!\n";
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
//                int roundLimit = Integer.parseInt(pointLimitInputField.getText().toString());
                String roundLimit = pointLimitInputField.getText().toString();

                boolean inputAccepted = checkUserInput(userName, roundLimit);

                if (inputAccepted) {
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
