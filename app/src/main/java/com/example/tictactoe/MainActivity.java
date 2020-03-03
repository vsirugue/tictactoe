package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
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
    public String activePlayer = "1";
    public String winner ="0";
    public boolean readyPlayer1 = false;
    public boolean readyPlayer2 = false;
    public String player1name = "Player 1";
    public String player2name = "Player 2";
    public String playerNumber = "0";

    private boolean doubleBackToExitPressedOnce = false;

    private ImageButton[] ibGrid = {null, null, null, null, null, null, null, null, null};
    public TextView txtP1, txtP2, txtActive;

    private  ValueEventListener boardRefListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(int i = 0; i <= 8; i++) {
                grid[i] = Integer.parseInt(dataSnapshot.child("case"+ i).getValue().toString());
                if (grid[i] != 0) {
                    caseEmpty[i] = false;
                }
                setImg(ibGrid[i], i, grid[i]);
            }
            Integer winner = win();
            String str = winner.toString();
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            if (winner != 0){
                activePlayerRef.setValue(0);
                //trigger fin de partie
                if (winner == 1) {
                    Toast.makeText(MainActivity.this, "PLAYER 1 WIN", Toast.LENGTH_SHORT).show();
                    alert(player1name);
                }
                if (winner == 2) {
                    Toast.makeText(MainActivity.this, "PLAYER 2 WIN", Toast.LENGTH_SHORT).show();
                    alert(player2name);
                }
                //endGame();
            }
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
            txtActive.setText("Active player : "+activePlayer);
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
            txtP1.setText("Player 1 : " + player1name);
            player2name = dataSnapshot.child("player2").child("name").getValue().toString();
            txtP2.setText("Player 2 : " + player2name);
            if (dataSnapshot.child("player1").child("ready").getValue().toString().equals("1")) {
                readyPlayer1 = true;
            }
            if (dataSnapshot.child("player2").child("ready").getValue().toString().equals("1")) {
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
        if (playerNumber.equals("1")) {
            //playersRef.child("player1").child("name").setValue(extras.getString("name"));
            playersRef.child("player1").child("ready").setValue(1);
            if (readyPlayer1 && readyPlayer2) {
                activePlayerRef.setValue(1);
            }
        }
        if (playerNumber.equals("2")) {
            //playersRef.child("player2").child("name").setValue(extras.getString("name"));
            playersRef.child("player2").child("ready").setValue(1);
            if (readyPlayer1 && readyPlayer2) {
                activePlayerRef.setValue(1);
            }
        }
        txtP2.setText(playerNumber);



    }

    public void OnClickButtonListener(Bundle savedInstanceState) {
        ibGrid[0] = (ImageButton) findViewById(R.id.IB1);
        ibGrid[1] = (ImageButton) findViewById(R.id.IB2);
        ibGrid[2] = (ImageButton) findViewById(R.id.IB3);
        ibGrid[3] = (ImageButton) findViewById(R.id.IB4);
        ibGrid[4] = (ImageButton) findViewById(R.id.IB5);
        ibGrid[5] = (ImageButton) findViewById(R.id.IB6);
        ibGrid[6] = (ImageButton) findViewById(R.id.IB7);
        ibGrid[7] = (ImageButton) findViewById(R.id.IB8);
        ibGrid[8] = (ImageButton) findViewById(R.id.IB9);
        ibGrid[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[0], 0);
            }
        });
        ibGrid[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[1], 1);
            }
        });
        ibGrid[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[2], 2);
            }
        });
        ibGrid[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[3], 3);
            }
        });
        ibGrid[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[4], 4);
            }
        });
        ibGrid[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[5], 5);
            }
        });
        ibGrid[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[6], 6);
            }
        });
        ibGrid[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[7], 7);
            }
        });
        ibGrid[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCase(ibGrid[8], 8);
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
        /*if (activePlayer == "0" && readyPlayer1 && readyPlayer2){
            activePlayerRef.setValue(1);
        }*/
        if (activePlayer.toString().equals(playerNumber.toString())){
            if (caseEmpty[caseNum]){
                if (playerNumber.equals("1")) {
                    Drawable img = resize(getDrawable(R.drawable.un2), 2);
                    ib.setImageDrawable(img);
                    boardRef.child("case"+caseNum.toString()).setValue(Integer.parseInt(activePlayer));
                    activePlayerRef.setValue(2);
                } else if (playerNumber.equals("2")) {
                    Drawable img = resize(getDrawable(R.drawable.deux),2);
                    ib.setImageDrawable(img);
                    boardRef.child("case"+caseNum.toString()).setValue(Integer.parseInt(activePlayer));
                    activePlayerRef.setValue(1);
                }
                caseEmpty[caseNum] = false;
                ib.setClickable(false);
            }
        } else {
            //toast not your turn
            Toast.makeText(MainActivity.this, "Not your turn !", Toast.LENGTH_SHORT).show();
        }
    }

    private void setImg(ImageButton ib, int caseNum, int player) {
        if (player == 1) {
            Drawable img = resize(getDrawable(R.drawable.un2), 2);
            ib.setImageDrawable(img);
        } else if (player == 2) {
            Drawable img = resize(getDrawable(R.drawable.deux),2);
            ib.setImageDrawable(img);
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
        playersRef.child("player1").child("ready").setValue(0);
        playersRef.child("player1").child("name").setValue("Player1");
        playersRef.child("player2").child("ready").setValue(0);
        playersRef.child("player2").child("name").setValue("Player2");
        activePlayerRef.setValue(1);
    }

    private Integer win(){

        Integer winP = 0;

        if (grid[0].equals(grid[1]) && grid[0].equals(grid[2]) && grid[0] != 0) {
            winP = grid[0];
        }
        if (grid[3].equals(grid[4]) && grid[3].equals(grid[5]) && grid[3] != 0) {
            winP = grid[3];
        }
        if (grid[6].equals(grid[7]) && grid[6].equals(grid[8]) && grid[6] != 0) {
            winP = grid[6];
        }
        if (grid[0].equals(grid[3]) && grid[0].equals(grid[6]) && grid[0] != 0) {
            winP = grid[0];
        }
        if (grid[1].equals(grid[4]) && grid[1].equals(grid[7]) && grid[1] != 0) {
            winP = grid[1];
        }
        if (grid[2].equals(grid[5]) && grid[2].equals(grid[8]) && grid[2] != 0) {
            winP = grid[2];
        }
        if (grid[0].equals(grid[4]) && grid[0].equals(grid[8]) && grid[0] != 0) {
            winP = grid[0];
        }
        if (grid[2].equals(grid[4]) && grid[2].equals(grid[6]) && grid[2] != 0) {
            winP = grid[2];
        }
        return winP;
    }

    private void alert(String winner) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("WINNER : " + winner);
        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                resetGame();
                finish();
            }
        });
        // Set other dialog properties
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to quit the game", Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    //activePlayerRef.removeEventListener(vel);
}