package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText loginText;
    EditText pwText;
    Button signIn;
    Boolean signInClicked = false;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference loginRef;
    DatabaseReference users;

    List<UserData> userDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hides action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Hides status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        loginRef = firebaseDatabase.getReference();
        users = loginRef.child("users");

        loginText = findViewById(R.id.loginText);
        pwText = findViewById(R.id.pwText);
        signIn = findViewById(R.id.signIn);

        userDataList = new ArrayList<>();


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInClicked = true;
//                Toast.makeText(LoginActivity.this, loginText.getText().toString(), Toast.LENGTH_SHORT).show();


//                id = loginText.getText().toString();
//                users.child(id).setValue(new UserData(loginText.getText().toString()));
                Intent intent = new Intent(LoginActivity.this, QuestionOne.class);
//                startActivity(intent);
//                signInClicked = true;
////                Toast.makeText(LoginActivity.this, loginText.getText().toString(), Toast.LENGTH_SHORT).show();
//
//
//                String id = loginText.getText().toString();
//                users.child(id).setValue(new UserData(loginText.getText().toString()));
//                if(tryLogin("","")) {
//                    Intent intent = new Intent(LoginActivity.this, EarthActivity.class);
                    startActivity(intent);
//                }
            }
        });
    }

    String key;

    @Override
    protected void onStart() {
        super.onStart();
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(signInClicked) {
                    for(DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                        UserData userData = userSnapshot.getValue(UserData.class);

                        if(userData.getEmailID().equals("hayho"))
                            Toast.makeText(LoginActivity.this, "IT EXISTS!!! ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
