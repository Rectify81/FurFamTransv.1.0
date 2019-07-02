package com.amarted.furfamtrans1.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.amarted.furfamtrans1.R;

public class TransactionsAllActivity extends AppCompatActivity {

    // variables needed for popup - adding new item
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog newTransaction;

    // Declare variables that you will pass onto the popup
    private EditText date;
    private EditText amount;
    private Switch splitSwitch;
    private RadioButton type1;
    private RadioButton type2;
    private RadioButton type3;
    private RadioButton ap1;
    private RadioButton ap2;
    private RadioButton ap3;
    private RadioButton ap4;
    private RadioButton ar1;
    private RadioButton ar2;
    private RadioButton ar3;
    private RadioButton ar4;
    private EditText comments;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                addNewTransaction();
            }
        });
    }

    private void addNewTransaction(){
        // Instantiate the dialog builder
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.new_transaction,null);
        // Instantiate declared variables to the objects in the popup
        date = view.findViewById(R.id.newDateID);
        amount = view.findViewById(R.id.newAmtID);
        splitSwitch = view.findViewById(R.id.newAutoSplitID);
        type1 = view.findViewById(R.id.newTypeSelection1);
        type2 = view.findViewById(R.id.newTypeSelection2);
        type3 = view.findViewById(R.id.newTypeSelection3);
        ap1 = view.findViewById(R.id.newUserBy1);
        ap2 = view.findViewById(R.id.newUserBy2);
        ap3 = view.findViewById(R.id.newUserBy3);
        ar1 = view.findViewById(R.id.newUserTo1);
        ar2 = view.findViewById(R.id.newUserTo2);
        ar3 = view.findViewById(R.id.newUserTo3);
        comments = view.findViewById(R.id.newNotesID);
        buttonSave = view.findViewById(R.id.newButtonSave);
        // Set the view
        dialogBuilder.setView(view);
        // Invoke actually dialog
        newTransaction = dialogBuilder.create();
        newTransaction.show();

        // Implement 'Save' Button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo:  Save to DB
                saveTransactionToDB(v);
                //ToDo: Go To Next Screen - Recycler View


            }
        });
    }

    // Implement Saving to DB
    private void saveTransactionToDB(View v){

    }

}
