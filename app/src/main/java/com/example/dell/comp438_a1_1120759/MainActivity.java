package com.example.dell.comp438_a1_1120759;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class MainActivity extends ActionBarActivity {
    // declaring the variables
    private int randNum; //to save the Rand
    private int minWithNum; // rand -5
    private int maxWithdNum;// rand+5
    EditText inputTxt; // users input
    int input_num; // save users iput after parsing
    TextView textViewResult; // a label to feedback the user


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gen(); // generation method is init during onCreate process to generate the rand#
    }


    // generation method for init a Random #
    // and simply calculate the maximum and minimum range
    // by means the + - margine of the random
    private void gen() {
        Random r = new Random();
        randNum = r.nextInt(1001); // between 0-1000
        minWithNum = randNum - 5;
        maxWithdNum = randNum + 5;
    }

    // The check method mainly will take the user's input and check it with the random number ,,
    // that generated in the gen method ,,
    // finally give a feedback to the user ,,
    public void check(View v) {
        textViewResult = (TextView) findViewById(R.id.textViewResult); // just a label to set the result later
        inputTxt = (EditText) findViewById(R.id.EditText4input);

        //to never provide an empty string check
        if(inputTxt.getText().toString().equals("")){
            textViewResult.setText("please provide input first ");
        }else{
             input_num = Integer.parseInt(inputTxt.getText().toString());// reading and casting the user's input
            //The main processing section
            if (input_num == randNum) { // Case 1 : "the user win "
                WinMethod(); //view the dialog message
                textViewResult.setText("");//ensure clearing text in the ui
                inputTxt.setText("");//ensure clearing text in the ui

            } else if (maxWithdNum > input_num && input_num > minWithNum) { //case 2 : "the user getting closer from the random # by +- 5 margin"
                textViewResult.setText("");
                Toast.makeText(getBaseContext(), "very close", Toast.LENGTH_SHORT).show(); // inform the user through a toast message

            } else if (input_num > randNum) // case 3 : the user provide a larger number
                textViewResult.setText(input_num + " is Larger ");
            else if (input_num < randNum)// case 4 : the user provide a smaller number
                textViewResult.setText(input_num + " is Smaller  ");



        }

    }

    // winmethod is invoked when the user won to view
    // the options of either exit the app or play again ,,
    public void WinMethod() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("congratulation ^_^ "); // dialog title
        alertDialog.setMessage("Corrrrect ! the answer is ".concat("" + randNum) + "\nWould you like another round : "); //message content

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                gen(); // to generate another random #
            }
        });

        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() { //to finish the app and exit
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });


        alertDialog.show();
    }

    // to clear the input area once the user Click it ,,,
    // and so the user never worry about clear the text manualy..
    public void clear (View v){
        if(v==inputTxt)
            inputTxt.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}



