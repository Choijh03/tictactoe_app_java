package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView headerText;

    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_O;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean isGameActive = true;
    boolean isWinnerThere = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.header_text);
        headerText.setText("O turn");
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //Logic for button press
        if(!isGameActive)
            return;

        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        if(filledPos[clickedTag]!= -1){
            return;
        }

        filledPos[clickedTag] = activePlayer;

        if(activePlayer == PLAYER_O){
            clickedBtn.setText("O");
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.teal_200));
            activePlayer = PLAYER_X;
            headerText.setText("X turn");
        }else{
            clickedBtn.setText("X");
            clickedBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
            activePlayer = PLAYER_O;
            headerText.setText("O turn");
        }
        
        checkForWin();
    }

    private void checkForWin(){
        //we will check who is winner and show
        int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if(filledPos[val0] != -1){
                    //winner declare
                    isGameActive = false;
                    isWinnerThere = true;

                    if(filledPos[val0] == PLAYER_O){
                        showDialog("O is winner");
                    }else{
                        showDialog("X is winner");
                    }

                }

            }
        }

        if(!isWinnerThere){
            int count=0;
            for(int i=0;i<9;i++) {
                if(filledPos[i] != -1) {
                    count++;
                }
            }
            if(count==9) {
                isGameActive = false;
                showDialog("Match is drawn");
            }
        }

    }

    private void showDialog(String winnerText){
        new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        }).show();
    }

    private void restartGame(){
        activePlayer = PLAYER_O;
        headerText.setText("O turn");
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn1.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn2.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn3.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn4.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn5.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn6.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn7.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        btn8.setBackgroundColor(getResources().getColor(R.color.darker_gray));
        isGameActive = true;
        isWinnerThere= false;
    }
}