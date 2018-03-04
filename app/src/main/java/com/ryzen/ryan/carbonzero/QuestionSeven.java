package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class QuestionSeven extends AppCompatActivity {


    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_seven);

        test = findViewById(R.id.test);

        Intent intent = getIntent();
        final String q6 = intent.getStringExtra("qSix");
        test.setText(q6);
    }
}
