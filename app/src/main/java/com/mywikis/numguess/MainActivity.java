package com.mywikis.numguess;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_ANSWER = "com.mywikis.numguess.GUESS_ANSWER";
    //public final static String EXTRA_GUESS_TIMES = "com.mywikis.numguess.GUESS_TIMES";
    //public static int GUESS_TIMES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void launchGame ( View view, int min, int max ) {
        Intent intentStartGame = new Intent( this, GuessActivity.class );
        String answer = "" + generateAnswer( min, max );
        intentStartGame.putExtra( EXTRA_ANSWER, answer );
        //intentStartGame.putExtra( EXTRA_GUESS_TIMES, 0 );
        startActivity(intentStartGame);
    }

    public void launchGamePreset1 ( View view ) {
        this.launchGame( view, 0, 10 );
    }

    public void launchGamePreset2 ( View view ) {
        this.launchGame( view, 0, 100 );
    }

    public void launchGameCustom ( View view ) {
        EditText minValue = (EditText) findViewById( R.id.inputMinValue );
        EditText maxValue = (EditText) findViewById( R.id.inputMaxValue );
        if ( Integer.parseInt(minValue.getText().toString()) > Integer.parseInt(maxValue.getText().toString()) ) {
            this.launchGame(view, 0, 10);
        }
        try {
            this.launchGame(view, Integer.parseInt(minValue.getText().toString()), Integer.parseInt(maxValue.getText().toString()));
        } catch ( Exception e ) {
            // do nothing really
        }
    }

    private int generateAnswer ( int minVal, int maxVal ) {
        return (int)(Math.random() * (maxVal - minVal) + minVal);
    }
}
