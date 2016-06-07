package com.mywikis.numguess;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CheckGuessActivity extends ActionBarActivity {
    public static String GUESS_ATTEMPT, GUESS_ANSWER;
    //public static int GUESS_TIMES;
    public final static String EXTRA_ANSWER = "com.mywikis.numguess.GUESS_ANSWER";
    //public final static String EXTRA_GUESS_TIMES = "com.mywikis.numguess.GUESS_TIMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_guess);
        Intent intentCheckGuess = getIntent();
        try {
            GUESS_ATTEMPT = intentCheckGuess.getStringExtra(GuessActivity.EXTRA_GUESS_ATTEMPT);
            GUESS_ANSWER = intentCheckGuess.getStringExtra(GuessActivity.EXTRA_GUESS_ANSWER);
            //GUESS_TIMES = intentCheckGuess.getIntExtra(GuessActivity.EXTRA_GUESS_TIMES, GuessActivity.GUESS_TIMES);
        } catch ( Exception e ) {
            TextView textView = (TextView) findViewById( R.id.result );
            textView.setText( "Error: Unparseable input. Go back and try entering something in." );
        }
        TextView textView = (TextView) findViewById( R.id.result );
        textView.setText( checkGuess( Integer.parseInt( GUESS_ANSWER ), Integer.parseInt( GUESS_ATTEMPT ) ) );
//        RelativeLayout relativeLayout = new RelativeLayout(this);
//        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT);
//
//        //create widgets
//        TextView textView = new TextView(this);
//        // Create the text view
//        textView.setTextSize(20);
//        textView.setText(this.checkGuess(Integer.parseInt(GUESS_ANSWER), Integer.parseInt(GUESS_ATTEMPT)));
//
//        Button button_goBack = new Button(this);
//        button_goBack.setText();
//        button_goBack.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                guessAgain( v );
//            }
//        });
//
//        // finally add your TextView to the RelativeLayout
//        relativeLayout.addView(textView);
//        relativeLayout.addView(button_goBack);
//
//        // and set layout like main content
//        setContentView(relativeLayout, lParams);
    }

    public String checkGuess( int answer, int attempt ) {
        try {
            if (answer == attempt) {
                return this.guessCorrect();
            } else if (answer > attempt) {
                return this.guessHigher();
            } else if (answer < attempt) {
                return this.guessLower();
            }
            return "Error: checkGuess() could not deduce relative correctness.";
        } catch ( Exception e ) {
            return "One or more blanks (unparseable input), please try again.";
        }
    }

    public String guessLower() {
        return getString(R.string.guess_lower);
        //return "Try guessing lower.\n\nGo back and guess again.";
    }

    public String guessHigher() {
        return getString(R.string.guess_higher);
        //return "Try guessing higher.\n\nGo back and guess again.";
    }

    public String guessCorrect() {
        return getString(R.string.guess_correct);
        //return "Congratulations, your guess was correct!\n\nGo back twice and start a new game.";
    }

    public void guessAgain ( View view ) {
        Intent intentTryAgain = new Intent( this, GuessActivity.class );
        String answer = GUESS_ANSWER;
        intentTryAgain.putExtra( EXTRA_ANSWER, answer );
        startActivity(intentTryAgain);
    }

}
