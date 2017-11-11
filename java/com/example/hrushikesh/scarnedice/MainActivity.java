package com.example.hrushikesh.scarnedice;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int usersScore=0;
    public int usersTurnScore = 0;
    public int compsScore=0;
    //public int compsTurnScore;
    Random random = new Random();
    Random random2 = new Random();
    ImageView imageView;
    ImageView imageView2;
    TextView turnScore;
    TextView userScore;
    TextView computerScore;
    TextView turn;
    Button rollButton;
    Button holdButton;
    boolean oneOnBoth = false;
    boolean equalNumber = false;
    boolean userTurn = true;

    public void rollDice(final View view) {
        int numberOnDice = random.nextInt(6) + 1;
        int numberOnDice2 = random2.nextInt(6) + 1;

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        turnScore = (TextView) findViewById(R.id.turnScore);
        turn = (TextView) findViewById(R.id.turn);
        rollButton = (Button) findViewById(R.id.rollButton);
        holdButton = (Button) findViewById(R.id.holdButton);
        //imageView.setImageResource(R.drawable.dice1);
        if (userTurn)
            turn.setText("Your");
        else
            turn.setText("Computer's");


        if(numberOnDice == 1 || numberOnDice2 == 1){
            usersTurnScore = 0;
            turnScore.setText(Integer.toString(numberOnDice));
            numberOnDice = 1;
            turnScore.setText("X");
            if (numberOnDice == 1 && numberOnDice2 == 1)
                oneOnBoth = true;
            holdScore(view);
        } else {

            usersTurnScore += numberOnDice+numberOnDice2;
            if(numberOnDice == numberOnDice2) {
                equalNumber = true;
                holdScore(view);
            }
            else
                equalNumber = false;
            turnScore.setText(Integer.toString(usersTurnScore));

            if (!userTurn) {
                rollButton.setEnabled(false);
                holdButton.setEnabled(false);
                if(usersTurnScore<20 && numberOnDice!=1 && numberOnDice2!=1 ) {
                    final android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rollDice(view);
                            System.out.println("Hi");
                        }
                    },2000);
                    //rollDice(view);
                }
                else {
                    final android.os.Handler handler = new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holdScore(view);
                            System.out.println("Hello");
                        }
                    },2000);
//                    holdScore(view);
                }
            }
        }



        String imageURL = "dice" + (Integer.toString(numberOnDice));
        int id = getResources().getIdentifier(imageURL, "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        imageView.setImageDrawable(drawable);

        String imageURL2 = "dice" + (Integer.toString(numberOnDice2));
        int id2 = getResources().getIdentifier(imageURL2, "drawable", getPackageName());
        Drawable drawable2 = getResources().getDrawable(id2);
        imageView2.setImageDrawable(drawable2);

    }


    public void holdScore(View view) {
        if (userTurn) {
            userScore = (TextView) findViewById(R.id.userScore);
            if(oneOnBoth) {
                usersScore -= usersScore;
                userTurn = false;
            } else {
                usersScore += usersTurnScore;
            }
            userScore.setText(Integer.toString(usersScore));

            turnScore.setText(Integer.toString(usersTurnScore));
            if(equalNumber)
                userTurn = true;
            else
                userTurn = false;
            turn.setText("Computer's");
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);
            rollDice(view);
        }
        else {
            computerScore = (TextView) findViewById(R.id.computerScore);
            if(oneOnBoth) {
                compsScore -= compsScore;

            } else {
                compsScore += usersTurnScore;
            }

            computerScore.setText(Integer.toString(compsScore));
            turnScore.setText(Integer.toString(usersTurnScore));
            if(!equalNumber)
                userTurn = true;
            else
                userTurn = false;

            turn.setText("Your");
        }
        oneOnBoth = false;
        usersTurnScore = 0;
        turnScore.setText(Integer.toString(usersTurnScore));

        if (compsScore>=100) {
            Toast.makeText(this, "Computer Won!", Toast.LENGTH_SHORT).show();
            rollButton = (Button) findViewById(R.id.rollButton);
            holdButton = (Button) findViewById(R.id.holdButton);
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);
        }
        else if (usersScore>=100) {
            Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();
            rollButton = (Button) findViewById(R.id.rollButton);
            holdButton = (Button) findViewById(R.id.holdButton);
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);
        }
        else {
            rollButton.setEnabled(true);
            holdButton.setEnabled(true);
        }

    }

    public void resetScore(View view) {

        turnScore = (TextView) findViewById(R.id.turnScore);
        turn = (TextView) findViewById(R.id.turn);

        userScore = (TextView) findViewById(R.id.userScore);
        computerScore = (TextView) findViewById(R.id.computerScore);

        rollButton = (Button) findViewById(R.id.rollButton);
        holdButton = (Button) findViewById(R.id.holdButton);
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);

        turnScore.setText("0");
        turn.setText("Your");

        userScore.setText("0");
        computerScore.setText("0");

        compsScore = 0;
        usersScore = 0;

        usersTurnScore = 0;
        userTurn = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
