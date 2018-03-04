package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class whatiscarbon extends AppCompatActivity {

    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatiscarbon);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        layout = findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(whatiscarbon.this, QuestionOne.class);
                startActivity(intent);
            }
        });
    }
}
