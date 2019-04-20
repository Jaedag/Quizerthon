package com.example.jaedag.quizerthon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Button StartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        StartButton = (Button) findViewById(R.id.start_button);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), QuizActivity.class);
                view.getContext().startActivity(Intent);
                finish();
            }
        });


    }
}
