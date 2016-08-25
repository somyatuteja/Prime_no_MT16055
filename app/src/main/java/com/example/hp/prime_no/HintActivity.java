package com.example.hp.prime_no;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    boolean tookhint=false;
    public static final String HINT="hint output";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button mYesButtonHint =(Button) findViewById(R.id.YesButtonHint);
        Button mNoButtonHint =(Button) findViewById(R.id.NoButtonHint);

        setSupportActionBar(toolbar);
        try {
            mYesButtonHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView mHintTextView=(TextView) findViewById(R.id.hint_tv);
                    mHintTextView.setText("Try Factoring the number");
                    tookhint=true;
                }
            });
        } catch (Exception e){}
        try {
            mNoButtonHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    intent.putExtra(HINT,tookhint);
                    startActivity(intent);
                }
            });
        } catch (Exception e){}

    }


    }

