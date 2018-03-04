package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionFour extends AppCompatActivity {

    Button choiceOne;
    Button choiceTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_four);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        choiceOne = (Button) findViewById(R.id.choiceOne);
        choiceTwo = (Button) findViewById(R.id.choiceTwo);

        //Keep
        Intent intent = getIntent();
        final String q3 = intent.getStringExtra("qThree");

        choiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionFour.this, QuestionFive.class).putExtra("qFour", q3+" 0");
                startActivity(intent);
            }
        });

        choiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionFour.this, QuestionFive.class).putExtra("qFour", q3+" 1");
                startActivity(intent);
            }
        });
    }
}
