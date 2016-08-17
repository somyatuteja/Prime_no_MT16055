package com.example.hp.prime_no;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String TAG1 = "Saved Instance";
    static final String LAST_QUESTION="Last_Question";
   private Button mYesButton;
    private Button mNoButton;
    static boolean ans=false;
    static int ran;
 public boolean isPrime(int r)
 {
     if (r==2) return true;
     if(r%2==0) return false;
     for (int i=3;i<r;i=i+2)
     {
         if(r%i==0) return false;

     }
     return true;
 }

    public void changeQuestion()
    {
        Log.d(TAG,"in ChangeQuestion");
        Random r=new Random();
         ran=r.nextInt(1000);
        TextView QuestionTextView=(TextView)findViewById(R.id.abc);
        QuestionTextView.setText("Is "+ran+" a prime no?");
        ans=this.isPrime(ran);

    }
    public void showToast(boolean b)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(b==true)
        {
            Toast toast = Toast.makeText(context,"correct", duration);
            toast.show();
        }
        if(b==false)
        {
            Toast toast = Toast.makeText(context,"incorrect", duration);
            toast.show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "In onCreate");
        mYesButton=(Button)findViewById(R.id.YesButton);
        mNoButton=(Button)findViewById(R.id.NoButton);
        if(savedInstanceState==null) {
            this.changeQuestion();
            Log.v(TAG,"Null saved Instance");
        }
        else
        {
            ran=savedInstanceState.getInt(LAST_QUESTION);
            TextView QuestionTextView=(TextView)findViewById(R.id.abc);
            QuestionTextView.setText("Is "+ran+" a prime no?");
            Log.v(TAG1,"Restored");
        }
        mNoButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showToast(!ans);
               changeQuestion();
           }
       });
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(ans);
                changeQuestion();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
        savedInstanceState.putInt(LAST_QUESTION,ran);
        //savedInstanceState.putBoolean(LAST_QUESTION,ans);
    }

    @Override

 protected void onStart()
    {
        super.onStart();
        Log.v(TAG,"In onStart");
    }
protected void onResume() {
    super.onResume();
    Log.v(TAG, "In onResume");
}}