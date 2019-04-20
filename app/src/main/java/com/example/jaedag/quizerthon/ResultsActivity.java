package com.example.jaedag.quizerthon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class ResultsActivity extends AppCompatActivity {

    private TextView mResultsTextView;
    private Button TryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        int value = getIntent().getIntExtra("results_key",0);
        String outof = ("/10");
        String valueoutof = value + outof;

        mResultsTextView = (TextView) findViewById(R.id.results_score_text_view);

       mResultsTextView.setText(String.valueOf(valueoutof));

        TryAgainButton = (Button) findViewById(R.id.try_again_button);
        TryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), WelcomeActivity.class);
                view.getContext().startActivity(Intent);
                finish();
            }

    });
}
}