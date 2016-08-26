package com.example.hp.prime_no;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    boolean  tookcheat=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Button mYesButtonCheat =(Button) findViewById(R.id.YesButtonCheat);
        Button mNoButtonCheat =(Button) findViewById(R.id.NoButtonCheat);

        try {
            mYesButtonCheat.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int ques=getIntent().getIntExtra("Question",0);
                    boolean b=getIntent().getBooleanExtra("Answer",false);
                    TextView mCheatTextView =(TextView) findViewById(R.id.CheatTextView);
                    mCheatTextView.setText(ques +"is prime no. ="+b);

                    tookcheat=true;
                }
            });
        } catch (Exception e){}
        try {
            mNoButtonCheat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    intent.putExtra("CHEAT",tookcheat);
                    setResult(Activity.RESULT_OK,intent);

                    finish();

                }
            });
        } catch (Exception e){}

    }
    @Override
    public void onBackPressed()
    {
        Log.v("MainActivity","in onBackPressed");

        try {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("CHEAT", tookcheat);
            setResult(Activity.RESULT_OK, intent);
            Log.v("MainActivity", "In HINT");
            finish();
        }
        catch (Exception e)

        {
            e.printStackTrace();
        }
    }



}
