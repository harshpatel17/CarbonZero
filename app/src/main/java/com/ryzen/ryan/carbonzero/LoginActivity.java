package com.ryzen.ryan.carbonzero;

import android.content.Intent;
import android.provider.ContactsContract;
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

public class LoginActivity extends AppCompatActivity {
    EditText loginText;
    EditText pwText;
    Button signIn;
    Boolean signInClicked = false;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference loginRef;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginRef = firebaseDatabase.getReference();
        users = loginRef.child("users");

        loginText = findViewById(R.id.loginText);
        pwText = findViewById(R.id.pwText);
        signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInClicked = true;
                Toast.makeText(LoginActivity.this, loginText.getText().toString(), Toast.LENGTH_SHORT).show();
                users.push();

//                if(tryLogin("","")) {
//                    Intent intent = new Intent(LoginActivity.this, EarthActivity.class);
//                    startActivity(intent);
//                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        loginRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(signInClicked) {
                if(dataSnapshot.child(loginText.getText().toString()).exists()){
                    Toast.makeText(LoginActivity.this, "IT EXISTS", Toast.LENGTH_SHORT).show();
                }else{
                    users.setValue(new UserData(loginText.getText().toString()));
                }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
