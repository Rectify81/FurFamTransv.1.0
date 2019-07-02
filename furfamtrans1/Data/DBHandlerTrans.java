package com.amarted.furfamtrans1.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.amarted.furfamtrans1.Activities.MainActivity;
import com.amarted.furfamtrans1.Model.Transaction;
import com.amarted.furfamtrans1.Util.Constants;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandlerTrans extends SQLiteOpenHelper {

    // Declare Context
    private Context ctx;

    public DBHandlerTrans(Context context) {
        this.ctx = context;
        //super(context, name, factory, version);
        super(context,Constants.DB_TRANS,null,Constants.DB_TRANS_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
         *      CREATE THE DATABASE (in SQL) - the columns of the whole table
         */
        String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + Constants.TABLE_TRANS + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_DATE_UPDATED = " LONG," + Constants.KEY_TIME_UPDATED + " TIME,"
                + Constants.KEY_USER + " TEXT," + Constants.KEY_DATE + " LONG," + Constants.KEY_AMOUNT + " TEXT," + Constants.KEY_DISCOUNT + " TEXT," + Constants.KEY_PORTION + " TEXT,"
                + Constants.KEY_TYPE + " TEXT," + Constants.KEY_PAIDBY + " TEXT," + Constants.KEY_PAIDTO + " TEXT," + Constants.KEY_CATEGORY + " TEXT," + Constants.KEY_NOTES + " TEXT,"
                + Constants.KEY_MANDIS + " BIT," + Constants.KEY_USER1_DISCOUNT + " TEXT," + Constants.KEY_USER2_DISCOUNT + " TEXT," + Constants.KEY_USER3_DISCOUNT + " TEXT);";
        // see here for visual structure: https://docs.google.com/spreadsheets/d/1JIhlZuM1rsuibb1RTxAGXYFhjWVb1p6X8lFTHlkFcEo

        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_TRANS);
        onCreate(db);
    }

    // Get count of entries in DB
    public int getTransactionCount(){
        // get instance of our database - READABLE
        SQLiteDatabase db = this.getReadableDatabase();
        // make a String to perform a rawQuery
        String countQuery = "SELECT * FROM " + Constants.TABLE_TRANS;
         // 'highlight' whole database via Query String above
        Cursor cursor = db.rawQuery(countQuery,null);
        // return the count of the selection (whole db)
        return cursor.getCount();
    }



    /*
     *      CRUD OPERATIONS: Create, Read, Update, Delete
     */


    // Add New Transaction (Crud)
    public void addTransaction(Transaction newTransaction){
        // get instance of our database - WRITABLE
        SQLiteDatabase db = this.getWritableDatabase();
        // create content values - a hashmap object for key-value pairs to add to the db
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_USER,newTransaction.getUser());
        values.put(Constants.KEY_AMOUNT,newTransaction.getAmount());
        values.put(Constants.KEY_LAST_UPDATED,System.currentTimeMillis());
        //ToDo: Continue populating the fields to add a new transaction

        // insert the row
        db.insert(Constants.TABLE_TRANS,null,values);

        // create confirmation that the method ran correctly
        Log.d("Saved???","Yes, it was correctly saved to the database");
        Toast.makeText(MainActivity.this,"Saving transaction to the database",Toast.LENGTH_SHORT).show();
    }


    // Get ALL Transactions (cRud)
    public List<Transaction> getAllTransactions(){
        // setup database instance - READABLE
        SQLiteDatabase db = this.getReadableDatabase();
        // instantiate the list of transactions as an array
        List<Transaction> transactionList = ArrayList<>();

        // create a cursor
        Cursor cursor = db.query(Constants.TABLE_USERS, new String[] {
                // pass in all the columns you want in this list
                Constants.KEY_ID, Constants.KEY_DATE_UPDATED, Constants.KEY_TIME_UPDATED,Constants.KEY_USER, Constants.KEY_DATE,Constants.KEY_AMOUNT,Constants.KEY_DISCOUNT,Constants.KEY_PORTION,
                Constants.KEY_TYPE,Constants.KEY_PAIDBY,Constants.KEY_PAIDTO,Constants.KEY_CATEGORY,Constants.KEY_NOTES,Constants.KEY_MANDIS},
                null,null,null,null,Constants.KEY_DATE + " DESC");

        // use cursor
        if (cursor.moveToFirst()) {
            do {
                // create another transaction object
                Transaction transaction = new Transaction();
                transaction.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                transaction.setAmount(cursor.getString(cursor.getColumnIndex(Constants.KEY_AMOUNT)));
                //ToDo: Continue filling out the setBlank fields from above

                // convert timestamps to something readable
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_UPDATED))).getTime());

                java.text.DateFormat timeFormat = java.text.DateFormat.getTimeInstance();
                String formattedTime = timeFormat.format(new Time(cursor.getLong(cursor.getColumnIndex(Constants.KEY_TIME_UPDATED))).getTime());

                transaction.setLastUpdated(formattedDate + " at " + formattedTime);

                // Add to the transactionList
                transactionList.add(transaction);

            }while (cursor.moveToNext())
        }
        cursor.close();
        return transactionList;
    }

    // Get A Transaction (cRud)
    private Transaction getTransaction(int id) {
        // Instantiate Your Database
        SQLiteDatabase db = this.getWritableDatabase();
        //'select' the data from the columns listed below with the id passed in via the method being called - if id 3 is being called, get the following columns for that item
        Cursor cursor = db.query(Constants.TABLE_TRANS, new String[]
                        {Constants.KEY_ID,Constants.KEY_NOTES},     // these are the columns of information you are asking for from the DB
                Constants.KEY_ID + "=?",new String[]{String.valueOf(id)}, // selects the row in the DB matching the id being called by getTransaction method
                null,null,null,null);  // other SQL options to select specific data - used in lists
        //ToDo: Continue populating the columns you want to grab

        //get the first item
        if (cursor != null)
            cursor.moveToFirst();
            // create a new transaction object
            Transaction transaction = new Transaction();
            transaction.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            transaction.setUser(cursor.getString(cursor.getColumnIndex(Constants.KEY_USER)));
            //ToDo: Add the rest of the columns

            // for time - it is saved in milliseconds and needs to be converted
            java.text.DateFormat dateTimeFormat = java.text.DateFormat.getDateTimeInstance();
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            java.text.DateFormat timeFormat = java.text.DateFormat.getTimeInstance();
// Create a   DateFormat object   called-^  ^- and set it to    ---------^
            // format that timestamp you just pulled...
            String formattedDateTime = dateTimeFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_LAST_UPDATED))).getTime());
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_LAST_UPDATED))).getTime());
            String formattedTime = timeFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_LAST_UPDATED))).getTime());
            //ToDo: Remove format for date and time independently ???

            transaction.setLastUpdated(formattedDateTime);

        return transaction;
    }


    // Edit a Transaction (crUd)
    public int updateTransaction(Transaction transaction) {
        // get instance of our database - WRITABLE
        SQLiteDatabase db = new this.getWritableDatabase();
        // create new contentValues to update db
        ContentValues values = new ContentValues();
        //values.put(String key, String value)
        values.put(Constants.KEY_USER,transaction.getUser());
        values.put(Constants.KEY_PAIDBY,transaction.getPaidBy());
        //values.put(String key, Long value)
        values.put(Constants.KEY_LAST_UPDATED, System.currentTimeMillis());  //get system time
        //values.put(String key, Double value)
        values.put(Constants.KEY_AMOUNT,transaction.getAmount();)
        //ToDo: Continue populating all the fields

        //update row - returns an integer to tell the system which entry you are updating
        return db.update(Constants.TABLE_TRANS,values,Constants.KEY_ID + "=?", new String[] {String.valueOf(transaction.getId())});
    }


    // Delete a Transaction (cruD)
    public void deleteTransaction(int id){
        // get instance of our database - WRITABLE
        SQLiteDatabase db = new this.getWritableDatabase();
        db.delete(Constants.TABLE_TRANS,Constants.KEY_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
    }
}
