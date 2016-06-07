package com.mywikis.numguess;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class GuessActivity extends ActionBarActivity {
    private static String CORRECT_VAL;
    //public static int GUESS_TIMES;
    public final static String EXTRA_GUESS_ATTEMPT = "com.mywikis.numguess.GUESS_ATTEMPT";
    public final static String EXTRA_GUESS_ANSWER = "com.mywikis.numguess.GUESS_ANSWER";
    //public final static String EXTRA_GUESS_TIMES = "com.mywikis.numguess.GUESS_TIMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Intent intentStartGame = getIntent();
        CORRECT_VAL = intentStartGame.getStringExtra(MainActivity.EXTRA_ANSWER);
        //GUESS_TIMES = intentStartGame.getIntExtra(MainActivity.EXTRA_GUESS_TIMES, MainActivity.GUESS_TIMES);
    }

    public void checkGuess ( View view ) {
        Intent intentCheckGuess = new Intent ( this, CheckGuessActivity.class );
        EditText guess = (EditText) findViewById( R.id.prompt_guess );
        try {
            intentCheckGuess.putExtra(EXTRA_GUESS_ATTEMPT, guess.getText().toString());
            intentCheckGuess.putExtra(EXTRA_GUESS_ANSWER, CORRECT_VAL);
            startActivity(intentCheckGuess);
        } catch ( Exception e ) {
            // do nothing
        }
    }

    public void returnMain ( View view ) {
        Intent intentReturnMain = new Intent ( this, MainActivity.class );
        startActivity ( intentReturnMain );
    }

}
