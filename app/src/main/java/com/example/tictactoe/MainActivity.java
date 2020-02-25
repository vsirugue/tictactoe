package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference boardRef = database.getReference("board");

    private static ImageButton IB1, IB2, IB3, IB4, IB5, IB6, IB7, IB8, IB9 ;
    private boolean joueur1Joue = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        OnClickButtonListener(savedInstanceState);
    }

    public void OnClickButtonListener(Bundle savedInstanceState) {
        IB1 = (ImageButton) findViewById(R.id.IB1);
        IB2 = (ImageButton) findViewById(R.id.IB2);
        IB3 = (ImageButton) findViewById(R.id.IB3);
        IB4 = (ImageButton) findViewById(R.id.IB4);
        IB5 = (ImageButton) findViewById(R.id.IB5);
        IB6 = (ImageButton) findViewById(R.id.IB6);
        IB7 = (ImageButton) findViewById(R.id.IB7);
        IB8 = (ImageButton) findViewById(R.id.IB8);
        IB9 = (ImageButton) findViewById(R.id.IB9);
        IB1.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB1.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB1.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB2.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB2.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB2.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB3.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB3.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB3.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB4.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB4.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB4.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB5.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB5.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB5.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB6.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB6.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB6.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB7.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB7.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB7.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB8.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB8.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB8.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
        IB9.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Bip ! Bip ! I'm a toast !", Toast.LENGTH_SHORT);
                toast.show();
                if (joueur1Joue){
                    IB9.setBackgroundResource(R.drawable.un);
                    joueur1Joue = false;
                }
                else {
                    IB9.setBackgroundResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
    }
}
