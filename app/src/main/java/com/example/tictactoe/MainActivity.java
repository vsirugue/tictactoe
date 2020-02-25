package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
    private static boolean getButton1, getButton2,getButton3,getButton4,getButton5,getButton6,getButton7,getButton8,getButton9;
    private boolean joueur1Joue = true;
    private boolean win = false;
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
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB1.setImageDrawable(img);
                    getButton1 = true;
                    IB1.setClickable(false);
                    winGame();
                    joueur1Joue = false;

                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB1.setImageDrawable(img);
                    getButton1 = false;
                    IB1.setClickable(false);
                    winGame();
                    joueur1Joue = true;
                }
            }
        });
        IB2.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB2.setImageDrawable(img);
                    getButton2 = true;
                    IB2.setClickable(false);
                    winGame();
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB2.setImageDrawable(img);
                    getButton2 = false;
                    IB2.setClickable(false);
                    winGame();
                    joueur1Joue = true;
                }
            }
        });
        IB3.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB3.setImageDrawable(img);
                    getButton3 = true;
                    IB3.setClickable(false);
                    winGame();
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB3.setImageDrawable(img);
                    getButton3 = false;
                    IB3.setClickable(false);
                    winGame();
                    joueur1Joue = true;
                }
            }
        });
        IB4.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB4.setImageDrawable(img);
                    getButton4 = true;
                    IB4.setClickable(false);
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB4.setImageDrawable(img);
                    getButton4 = false;
                    IB4.setClickable(false);
                    joueur1Joue = true;
                }
            }
        });
        IB5.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB5.setImageDrawable(img);
                    getButton5 = true;
                    IB5.setClickable(false);
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB5.setImageDrawable(img);
                    getButton5 = false;
                    IB5.setClickable(false);
                    joueur1Joue = true;
                }
            }
        });
        IB6.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB6.setImageDrawable(img);
                    getButton6 = true;
                    IB6.setClickable(false);
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB6.setImageDrawable(img);
                    getButton6 = false;
                    IB6.setClickable(false);
                    joueur1Joue = true;
                }
            }
        });
        IB7.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB7.setImageDrawable(img);
                    getButton7 = true;
                    IB7.setClickable(false);
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB7.setImageDrawable(img);
                    getButton7 = false;
                    IB7.setClickable(false);
                    joueur1Joue = true;
                }
            }
        });
        IB8.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB8.setImageDrawable(img);
                    getButton8 = true;
                    IB8.setClickable(false);
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB8.setImageDrawable(img);
                    getButton8 = false;
                    IB8.setClickable(false);
                    joueur1Joue = true;
                }
            }
        });
        IB9.setOnClickListener(new View.OnClickListener() {
            @
                    Override
            public void onClick(View v) {
                if (joueur1Joue){
                    Drawable img = resize(getDrawable(R.drawable.un2),2);
                    IB9.setImageDrawable(img);
                    getButton9 = true;

                    //IB9.setBackgroundResource(R.drawable.un2);
                    IB9.setClickable(false);
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB9.setImageDrawable(img);
                    getButton9 = false;

                    //B9.setBackgroundResource(R.drawable.deux);
                    IB9.setClickable(false);
                    joueur1Joue = true;
                }
            }
        });
    }
    private Drawable resize(Drawable image, Integer div) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, Math.round(image.getIntrinsicWidth()/div), Math.round(image.getIntrinsicHeight()/div), false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private void winGame() {
        if ((getButton1 == getButton2)&&(getButton2 == getButton3)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton4 == getButton5)&&(getButton5 == getButton6)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton7 == getButton8)&&(getButton8 == getButton9)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton1 == getButton4)&&(getButton4 == getButton7)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton2 == getButton5)&&(getButton5 == getButton8)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton3 == getButton6)&&(getButton6 == getButton9)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton1 == getButton5)&&(getButton5 == getButton9)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if ((getButton3 == getButton5)&&(getButton5 == getButton7)){
            if(joueur1Joue){
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 1 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Joueur 2 a gagné !", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
