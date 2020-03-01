package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference boardRef = database.getReference("board");
    DatabaseReference activePlayerRef = database.getReference("active");
    DatabaseReference winnerRef = database.getReference("winner");
    DatabaseReference playersRef = database.getReference("players");

    public Integer[] grid = {0,0,0,0,0,0,0,0,0,0};
    public String activePlayer = "0";
    public String winner ="0";
    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = false;
    public String player1name = "Player 1";
    public String player2name = "Player 2";
    public String playerNumber = "0";


    private ImageButton IB1, IB2, IB3, IB4, IB5, IB6, IB7, IB8, IB9 ;
    private TextView txt;
    private static boolean getButton1, getButton2,getButton3,getButton4,getButton5,getButton6,getButton7,getButton8,getButton9;
    private boolean joueur1Joue = true;
    private boolean win = false;

    private  ValueEventListener boardRefListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(int i = 0; i <= 8; i++) {
                grid[i] = Integer.parseInt(dataSnapshot.child("case"+ i).getValue().toString());
            }
            txt.setText(grid[5].toString());
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //toast error
            Toast.makeText(getApplicationContext(),"Couldn't get grid state", Toast.LENGTH_LONG).show();
        }
    };
    private ValueEventListener activePlayerListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            activePlayer = dataSnapshot.getValue().toString();
            txt.setText(activePlayer);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //toast error
            Toast.makeText(getApplicationContext(),"Couldn't get activePlayer state", Toast.LENGTH_LONG).show();
        }
    };
    private ValueEventListener winnerListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            winner = dataSnapshot.getValue().toString();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            //toast error
            Toast.makeText(getApplicationContext(),"Couldn't get winner state", Toast.LENGTH_LONG).show();
        }
    };
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

        setContentView(R.layout.activity_main);
        OnClickButtonListener(savedInstanceState);
        setListeners();

    }

    public void OnClickButtonListener(Bundle savedInstanceState) {
        txt = findViewById(R.id.textView);
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
                    //winGame();
                    joueur1Joue = false;
                    //getCurrentPlayer();

                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB1.setImageDrawable(img);
                    getButton1 = false;
                    IB1.setClickable(false);
                    //winGame();
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
                    //winGame();
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB2.setImageDrawable(img);
                    getButton2 = false;
                    IB2.setClickable(false);
                    //winGame();
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
                    //winGame();
                    joueur1Joue = false;
                }
                else {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    IB3.setImageDrawable(img);
                    getButton3 = false;
                    IB3.setClickable(false);
                    //winGame();
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

    private void setListeners() {
        boardRef.addValueEventListener(boardRefListener);
        activePlayerRef.addValueEventListener(activePlayerListener);
        winnerRef.addValueEventListener(winnerListener);
        playersRef.addValueEventListener(playersListener);
    }

    private void resetBoard(){
        boardRef.child("case0").setValue(0);
        boardRef.child("case1").setValue(0);
        boardRef.child("case2").setValue(0);
        boardRef.child("case3").setValue(0);
        boardRef.child("case4").setValue(0);
        boardRef.child("case5").setValue(0);
        boardRef.child("case6").setValue(0);
        boardRef.child("case7").setValue(0);
        boardRef.child("case8").setValue(0);
    }

    private Integer win(){

        Integer winP = 0;

        if (grid[0] == grid[1] && grid[0] == grid[2] && grid[0] != 0) {
            winP = grid[0];
        }
        if (grid[3] == grid[4] && grid[3] == grid[5] && grid[3] != 0) {
            winP = grid[3];
        }
        if (grid[6] == grid[7] && grid[6] == grid[8] && grid[6] != 0) {
            winP = grid[6];
        }
        if (grid[0] == grid[3] && grid[0] == grid[6] && grid[0] != 0) {
            winP = grid[0];
        }
        if (grid[1] == grid[4] && grid[1] == grid[7] && grid[1] != 0) {
            winP = grid[1];
        }
        if (grid[2] == grid[5] && grid[2] == grid[8] && grid[2] != 0) {
            winP = grid[2];
        }
        if (grid[0] == grid[4] && grid[0] == grid[8] && grid[0] != 0) {
            winP = grid[0];
        }
        if (grid[2] == grid[4] && grid[2] == grid[6] && grid[2] != 0) {
            winP = grid[2];
        }
        return winP;
    }

        //activePlayerRef.addValueEventListener(vel);
        //activePlayerRef.removeEventListener(vel);

}

/*TO DO
FIREBASE
player1connected
player2connected
activePlayer


startGame();

changement activity
Activity "accueil" avec un bouton join party + textfield pseudo (on gérera les pseudo plus tard);

activePlayer a 0 par defaut.
startGame() {
reset grid
set active player to 1.
}

assignPlayerNumber() {
si player 1 connected assigne a joueur 2
si joueurs 1et2 connected : partie full
}

if winner
endGame() {
reset board
deco joueur
renvoi activite d'accueil
}
*/