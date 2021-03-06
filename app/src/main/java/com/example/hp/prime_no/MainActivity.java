package com.example.hp.prime_no;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String LAST_QUESTION="Last Question";
   private static final String LAST_ANSWER="Last Answer";
   private static final String TOTAL="No of Correct Answers";
    private int mRidH=100;
    private int mRidC=10;
    private TextView cor_tv;
   private static boolean ans=false;
   private static int ran;
   private static int c;
 private boolean isPrime(int r)
 {
     if (r==2) return true;
     if(r%2==0) return false;
     for (int i=3;i<r;i=i+2)
     {
         if(r%i==0) return false;

     }
     return true;
 }

    private void changeQuestion()
    {
        Log.d(TAG,"in ChangeQuestion");
        Random r=new Random();
         ran=r.nextInt(1000);
        TextView QuestionTextView=(TextView)findViewById(R.id.ques);
        try {
            if( QuestionTextView != null) {
                QuestionTextView.setText("Is " + ran + " a prime no?");
            }
        }
        catch(Exception e)
        {
   Log.d(TAG,e.getMessage());
        }
        ans=this.isPrime(ran);

    }
    private void showToast(boolean b)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(b)
        {
            Toast toast = Toast.makeText(context,"correct", duration);
            toast.show();
            cor_tv=(TextView)findViewById(R.id.correct_tv);
            try

            {
                c = Integer.parseInt(cor_tv.getText().toString());
            }
            catch(Exception e)
            {Log.d(TAG,e.getMessage());}
            c=c+1;
            cor_tv.setText(String.valueOf(c));


        }
        if(!b)
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

        Button mYesButton = (Button) findViewById(R.id.YesButton);
        Button mNoButton = (Button) findViewById(R.id.NoButton);
        Button mNextButton = (Button) findViewById(R.id.NextButton);
        Button mHintButton=(Button) findViewById(R.id.HintButton);
        Button mCheatButton=(Button)findViewById(R.id.CheatButton);


        if (savedInstanceState == null) {
            this.changeQuestion();
            Log.v(TAG, "Null saved Instance");
        } else {
            ran = savedInstanceState.getInt(LAST_QUESTION);
            ans = savedInstanceState.getBoolean(LAST_ANSWER);
            TextView QuestionTextView = (TextView) findViewById(R.id.ques);
            try {
                QuestionTextView.setText("Is " + ran + " a prime no?");
            } catch (Exception e) {
                Log.d(TAG,e.getMessage());
            }
            Log.v(TAG, "Restored");
            cor_tv = (TextView) findViewById(R.id.correct_tv);
            cor_tv.setText(String.valueOf(c));

        }
        try {
            mNoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast(!ans);
                     changeQuestion();
                }
            });
        } catch (Exception e) {
        }
        try {
            mYesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast(ans);
                    changeQuestion();
                }
            });
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeQuestion();
                }
            });
        } catch (Exception e){}

        try {
            mHintButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(),HintActivity.class);
                    startActivityForResult(intent,mRidH);
                }
            });
        } catch (Exception e){}
        try {
            mCheatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(),CheatActivity.class);
                    intent.putExtra("Question",ran);
                    intent.putExtra("Answer",ans);
                    startActivityForResult(intent,mRidC);
                }
            });
        } catch (Exception e){}
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
        savedInstanceState.putInt(LAST_QUESTION,ran);
        savedInstanceState.putBoolean(LAST_ANSWER,ans);
        savedInstanceState.putInt(TOTAL,c);
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

}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==mRidH)
        {
            if(resultCode==RESULT_OK)
            {
                boolean b=data.getBooleanExtra(HintActivity.HINT,false);
                if(!b) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Good, You didn't take the hint.", duration);
                    toast.show();
                }
                if(b)
                {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "You took the hint.", duration);
                    toast.show();

                }
            }

        }
        if(requestCode==mRidC)
        {
            if(resultCode==RESULT_OK)
            {
                boolean b=data.getBooleanExtra("CHEAT",false);
                if(!b) {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "Good, You didn't Cheat", duration);
                    toast.show();
                }
                if(b)
                {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "You Cheated.", duration);
                    toast.show();

                }
            }

        }
    }
}