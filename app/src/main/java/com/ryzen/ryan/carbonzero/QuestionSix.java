package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class QuestionSix extends AppCompatActivity {

    Button choiceOne;
    Button choiceTwo;
    Button choiceThree;
    Button choiceFour;
    Button choiceFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_six);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        choiceOne = (Button) findViewById(R.id.choiceOne);
        choiceTwo = (Button) findViewById(R.id.choiceTwo);
        choiceThree = (Button) findViewById(R.id.choiceThree);
        choiceFour = (Button) findViewById(R.id.choiceFour);
        choiceFive = (Button) findViewById(R.id.choiceFive);

        //Keep
        Intent intent = getIntent();
        final String q5 = intent.getStringExtra("qFive");

        choiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceOne.setBackgroundColor(Color.parseColor("#ede21b"));
                Intent intent = new Intent(QuestionSix.this, QuestionSeven.class).putExtra("qSix", q5+" 0");
                startActivity(intent);
            }
        });

        choiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceTwo.setBackgroundColor(Color.parseColor("#ede21b"));
                Intent intent = new Intent(QuestionSix.this, QuestionSeven.class).putExtra("qSix", q5+" 1");
                startActivity(intent);
            }
        });

        choiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceThree.setBackgroundColor(Color.parseColor("#ede21b"));
                Intent intent = new Intent( QuestionSix.this, QuestionSeven.class).putExtra("qSix", q5+" 2");
                startActivity(intent);
            }
        });

        choiceFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceFour.setBackgroundColor(Color.parseColor("#ede21b"));
                Intent intent = new Intent(QuestionSix.this, QuestionSeven.class).putExtra("qSix",q5+ " 3");
                startActivity(intent);
            }
        });

        choiceFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceFive.setBackgroundColor(Color.parseColor("#ede21b"));
                Intent intent = new Intent(QuestionSix.this, QuestionSeven.class).putExtra("qSix",q5+" 4");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        choiceOne.setBackgroundColor(Color.parseColor("#26ffffff"));
        choiceTwo.setBackgroundColor(Color.parseColor("#26ffffff"));
        choiceThree.setBackgroundColor(Color.parseColor("#26ffffff"));
        choiceFour.setBackgroundColor(Color.parseColor("#26ffffff"));
        choiceFive.setBackgroundColor(Color.parseColor("#26ffffff"));
    }
}
