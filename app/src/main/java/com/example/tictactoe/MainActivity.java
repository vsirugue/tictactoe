package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference boardRef = database.getReference("board");

    private static ImageButton IB1, IB2, IB3, IB4, IB5, IB6, IB7, IB8, IB9 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean joueur1Joue = true;
        setContentView(R.layout.activity_main);

        IB1

    }



}
