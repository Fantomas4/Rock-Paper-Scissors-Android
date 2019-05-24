package com.example.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button startGameButton;
    EditText nameInputField;
    EditText pointLimitInputField;

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
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("playerName", nameInputField.getText().toString());
                bundle.putInt("pointLimit", Integer.parseInt(pointLimitInputField.getText().toString()));
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}
