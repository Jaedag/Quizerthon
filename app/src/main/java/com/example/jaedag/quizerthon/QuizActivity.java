package com.example.jaedag.quizerthon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.Random;

import static com.example.jaedag.quizerthon.R.id.question_text_view;
import static com.example.jaedag.quizerthon.R.id.textView;

public class QuizActivity extends AppCompatActivity {

    //splash Screen
    private static int SPLASH_TIME_OUT = 4000;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    public int quiz_results=0,count=0;
    int min = 1;
    int max = 101;

    //Randomise the question order with Random
    Random r = new Random();
    private int mCurrentIndex  = r.nextInt(max - min + 1) + min;//min is inclusive max is exclusive1

    //This code contains the Question Bank
    private Question[] mQuestionBank = new Question[] {
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
            new Question(R.string.question_28,true),
            new Question(R.string.question_29,true),
            new Question(R.string.question_30,false),
            new Question(R.string.question_31,true),
            new Question(R.string.question_32,false),
            new Question(R.string.question_33,false),
            new Question(R.string.question_34,true),
            new Question(R.string.question_35,true),
            new Question(R.string.question_36,true),
            new Question(R.string.question_37,false),
            new Question(R.string.question_38,true),
            new Question(R.string.question_39,false),
            new Question(R.string.question_40,false),
            new Question(R.string.question_41,true),
            new Question(R.string.question_42,false),
            new Question(R.string.question_43,true),
            new Question(R.string.question_44,false),
            new Question(R.string.question_45,true),
            new Question(R.string.question_46,true),
            new Question(R.string.question_47,false),
            new Question(R.string.question_48,false),
            new Question(R.string.question_49,true),
            new Question(R.string.question_50,false),
            new Question(R.string.question_51,true),
            new Question(R.string.question_52,false),
            new Question(R.string.question_53,true),
            new Question(R.string.question_54,true),
            new Question(R.string.question_55,false),
            new Question(R.string.question_56,false),
            new Question(R.string.question_57,false),
            new Question(R.string.question_58,true),
            new Question(R.string.question_59,true),
            new Question(R.string.question_60,false),
            new Question(R.string.question_61,true),
            new Question(R.string.question_62,true),
            new Question(R.string.question_63,false),
            new Question(R.string.question_64,false),
            new Question(R.string.question_65,true),
            new Question(R.string.question_66,true),
            new Question(R.string.question_67,true),
            new Question(R.string.question_68,false),
            new Question(R.string.question_69,true),
            new Question(R.string.question_70,true),
            new Question(R.string.question_71,true),
            new Question(R.string.question_72,true),
            new Question(R.string.question_73,false),
            new Question(R.string.question_74,true),
            new Question(R.string.question_75,false),
            new Question(R.string.question_76,false),
            new Question(R.string.question_77,false),
            new Question(R.string.question_78,true),
            new Question(R.string.question_79,false),
            new Question(R.string.question_80,false),
            new Question(R.string.question_81, true),
            new Question(R.string.question_82, false),
            new Question(R.string.question_83, false),
            new Question(R.string.question_84, true),
            new Question(R.string.question_85, true),
            new Question(R.string.question_86, true),
            new Question(R.string.question_87, true),
            new Question(R.string.question_88, false),
            new Question(R.string.question_89, false),
            new Question(R.string.question_90, true),
            new Question(R.string.question_91, true),
            new Question(R.string.question_92, false),
            new Question(R.string.question_93, false),
            new Question(R.string.question_94, true),
            new Question(R.string.question_95, true),
            new Question(R.string.question_96, false),
            new Question(R.string.question_97, true),
            new Question(R.string.question_98, true),
            new Question(R.string.question_99, true),
            new Question(R.string.question_100, true),
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
        mQuestionTextView = (TextView) findViewById(question_text_view);
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
            mCurrentIndex = (mCurrentIndex + 8) % mQuestionBank.length;
            updateQuestion();
        }
    });
        //updateQuestion();
}
}
