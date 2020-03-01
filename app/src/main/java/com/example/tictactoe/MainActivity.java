package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
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
    public boolean[] caseEmpty = {true,true,true,true,true,true,true,true,true};
    public String activePlayer = "0";
    public String winner ="0";
    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = false;
    public String player1name = "Player 1";
    public String player2name = "Player 2";
    public String playerNumber = "0";


    private ImageButton IB1, IB2, IB3, IB4, IB5, IB6, IB7, IB8, IB9 ;
    public TextView txtP1, txtP2, txtActive;

    private  ValueEventListener boardRefListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(int i = 0; i <= 8; i++) {
                grid[i] = Integer.parseInt(dataSnapshot.child("case"+ i).getValue().toString());
                if (grid[i] != 0) {
                    caseEmpty[i] = false;
                }
            }
            txtP1.setText(grid[5].toString());
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
            txtActive.setText(activePlayer);
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

        txtP1 = findViewById(R.id.textViewP1);
        txtP2 = findViewById(R.id.textViewP2);
        txtActive = findViewById(R.id.textViewActive);

        setListeners();
        resetBoard();

        Bundle extras = getIntent().getExtras();
        playerNumber = extras.getString("playerNumber");
        if (playerNumber == "1") {
            playersRef.child("player1").child("name").setValue(extras.getString("name"));
            playersRef.child("player1").child("ready").setValue("1");
            if (readyPlayer1 && readyPlayer2) {
                activePlayerRef.setValue("1");
            }
        }
        if (playerNumber == "2") {
            playersRef.child("player2").child("name").setValue(extras.getString("name"));
            playersRef.child("player2").child("ready").setValue("1");
            if (readyPlayer1 && readyPlayer2) {
                activePlayerRef.setValue("1");
            }
        }
        txtP2.setText(playerNumber);



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
            @Override
            public void onClick(View v) {
                clickCase(IB1, 0);
            }
        });
        IB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB2, 1);
            }
        });
        IB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB3, 2);
            }
        });
        IB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB4, 3);
            }
        });
        IB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB5, 4);
            }
        });
        IB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB6, 5);
            }
        });
        IB7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB7, 6);
            }
        });
        IB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB8, 7);
            }
        });
        IB9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(IB9, 8);
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

    public void clickCase(ImageButton ib, Integer caseNum){
        if (activePlayer == "0" && readyPlayer1 && readyPlayer2){
            activePlayerRef.setValue("1");
        }
        if (activePlayer == playerNumber){
            if (caseEmpty[caseNum]){
                if (playerNumber == "1") {
                    Drawable img = resize(getDrawable(R.drawable.un2), 2);
                    ib.setImageDrawable(img);
                    boardRef.child("case"+caseNum.toString()).setValue(activePlayer);
                    activePlayerRef.setValue("2");
                } else if (playerNumber == "2") {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    ib.setImageDrawable(img);
                    boardRef.child("case"+caseNum.toString()).setValue(activePlayer);
                    activePlayerRef.setValue("1");
                }
                caseEmpty[0] = false;
                ib.setClickable(false);

                if (win()!= 0){
                    //trigger fin de partie
                }
            }
        } else {
            //toast not your turn
            Toast.makeText(MainActivity.this, "Not your turn !", Toast.LENGTH_SHORT).show();
        }
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

    private void resetGame(){
        resetBoard();
        playersRef.child("player1").child("ready").setValue("0");
        playersRef.child("player1").child("name").setValue("Player1");
        playersRef.child("player1").child("ready").setValue("0");
        playersRef.child("player1").child("name").setValue("Player2");

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

startGame();

changement activity
Activity "accueil" avec un bouton join party + textfield pseudo (on gérera les pseudo plus tard);

activePlayer a 0 par defaut.
startGame() {
reset grid
set active player to 1.
}

txtfields "online" pour chaque joueur + active player


if p1 + p2 offline kick vers accueil

if winner
endGame() {
reset board
deco joueur
pop up victoire/defaite -> renvoi activite d'accueil


reset_firebase pseudo
}
*/