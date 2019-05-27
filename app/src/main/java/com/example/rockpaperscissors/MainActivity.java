package com.example.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startGameButton;
    EditText nameInputField;
    EditText pointLimitInputField;

    private boolean checkUserInput(String userName, int roundLimit) {
        if (userName.length() > 25) {
            Toast.makeText(getApplicationContext(), "Wow! Try using a shorter name!", Toast.LENGTH_LONG).show();
            nameInputField.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = (Button) findViewById(R.id.startGameButton);
        nameInputField = (EditText) findViewById(R.id.nameInputField);
        pointLimitInputField = (EditText) findViewById(R.id.pointLimitInputField);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = nameInputField.getText().toString();
                int roundLimit = Integer.parseInt(pointLimitInputField.getText().toString());

                boolean inputAccepted = checkUserInput(userName, roundLimit);

                if (inputAccepted) {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    bundle.putInt("roundLimit", roundLimit);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        });
    }
}
