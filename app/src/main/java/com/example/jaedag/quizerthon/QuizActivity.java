package com.example.jaedag.quizerthon;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    //splash Screen
    private static int SPLASH_TIME_OUT = 4000;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    public int quiz_results=0,count=0;
    int min = 0;
    int max = 22;

    //Randomise the question order with Random
    Random r = new Random();
    private int mCurrentIndex  = r.nextInt(max - min + 1) + min;

    //This code contains the Question Bank
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_1,false),
            new Question(R.string.question_2,true),
            new Question(R.string.question_3,false),
            new Question(R.string.question_4,true),
            new Question(R.string.question_5,true),
            new Question(R.string.question_6,false),
            new Question(R.string.question_7,false),
            new Question(R.string.question_8,true),
            new Question(R.string.question_9,false),
            new Question(R.string.question_10,true),
            new Question(R.string.question_11,true),
            new Question(R.string.question_12,false),
            new Question(R.string.question_13,true),
            new Question(R.string.question_14,true),
            new Question(R.string.question_15,false),
            new Question(R.string.question_16,true),
            new Question(R.string.question_17,false),
            new Question(R.string.question_18,true),
            new Question(R.string.question_19,false),
            new Question(R.string.question_20,false),
            new Question(R.string.question_21,true),
            new Question(R.string.question_22,true),
            new Question(R.string.question_23,false),
            new Question(R.string.question_24,true),
            new Question(R.string.question_25,false),
            new Question(R.string.question_26,true),
            new Question(R.string.question_27,true),







};


    // Update the Question
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        mFalseButton.setEnabled(true);
        mTrueButton.setEnabled(true);
        mFalseButton.setBackgroundResource(R.color.buttonColour);
        mTrueButton.setBackgroundResource(R.color.buttonColour);
        mNextButton.setVisibility(View.INVISIBLE);
    }

    private void showResults() {

            Intent i = new Intent(QuizActivity.this, ResultsActivity.class);
            i.putExtra("results_key",quiz_results);
            startActivity(i);
            finish();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            quiz_results++;

        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(QuizActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);*/

        setContentView(R.layout.activity_quiz);
        quiz_results=0;
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);


                mTrueButton = (Button) findViewById(R.id.true_button);
                mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        checkAnswer(true);
                        mTrueButton.setEnabled(false);
                        mFalseButton.setEnabled(false);
                mFalseButton.setBackgroundResource(R.color.usedButton);
                mTrueButton.setBackgroundResource(R.color.usedButton);
                ++count;
                if(count >= 10) {
                    showResults();
                }
                else {
                    mNextButton.setVisibility(View.VISIBLE);
                }
            }
        });
                mFalseButton = (Button) findViewById(R.id.false_button);
                mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       checkAnswer(false);
                    mFalseButton.setEnabled(false);
                    mTrueButton.setEnabled(false);
                mFalseButton.setBackgroundResource(R.color.usedButton);
                mTrueButton.setBackgroundResource(R.color.usedButton);
                ++count;
                if(count >= 10) {
                    showResults();
                }
                else{
                    mNextButton.setVisibility(View.VISIBLE);
                }
            }
                });


    mNextButton = (Button) findViewById(R.id.next_button);
    mNextButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            updateQuestion();
        }
    });
        //updateQuestion();
}
}
