package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class UsernameActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference playersRef = database.getReference("players");

    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = false;
    public String player1name = "Player 1";
    public String player2name = "Player 2";

    private ValueEventListener playersListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            player1name = dataSnapshot.child("player1").child("name").getValue().toString();
            player2name = dataSnapshot.child("player2").child("name").getValue().toString();
            if (dataSnapshot.child("player1").child("ready").getValue().toString() == "1") {
                readyPlayer1 = true;
            }
            if (dataSnapshot.child("player2").child("ready").getValue().toString() == "1") {
                readyPlayer2 = true;
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //toast error
            Toast.makeText(getApplicationContext(),"Couldn't get winner state", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        playersRef.addValueEventListener(playersListener);

        Button usrBtn = findViewById(R.id.usernameButton);
        final EditText usrEdit = findViewById(R.id.usernameEditText);
        final Intent callActivity = new Intent(getApplicationContext(), MainActivity.class);
        usrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UsernameActivity.this, "Username : "+ usrEdit.getText(), Toast.LENGTH_SHORT).show();
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", usrEdit.getText().toString());
                editor.apply();

                if (!readyPlayer1) {
                    callActivity.putExtra("name", usrEdit.getText());
                    callActivity.putExtra("playerNumber", "1");
                    startActivity(callActivity);
                }
                else {
                    if (!readyPlayer2) {
                        callActivity.putExtra("name", usrEdit.getText());
                        callActivity.putExtra("playerNumber", "2");
                        startActivity(callActivity);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Room FULL, please wait.", Toast.LENGTH_LONG).show();
                    }
                }
                callActivity.putExtra("name", usrEdit.getText());
                startActivity(callActivity);
            }
        });
    }
}
