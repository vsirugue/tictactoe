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
        IB9 = (ImageButton) findViewById(R.id.IB9);
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
                    IB9.setImageResource(R.drawable.deux);
                    joueur1Joue = true;
                }
            }
        });
    }
}
