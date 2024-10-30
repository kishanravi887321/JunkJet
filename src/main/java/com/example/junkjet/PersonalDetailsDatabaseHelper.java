package com.example.junkjet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonalDetailsDatabaseHelper extends SQLiteOpenHelper {

    // Database and table information
    private static final String DATABASE_NAME = "PersonalDetailsDatabase.db";
    private static final String TABLE_NAME = "personal_details";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "EMAIL";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "ADDRESS";
    private static final String COL_5 = "BUSINESS_TYPE";

    private DatabaseHelper userDatabaseHelper;

    public PersonalDetailsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        userDatabaseHelper = new DatabaseHelper(context); // Initialize the user database helper
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the personal details table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "EMAIL TEXT, NAME TEXT, ADDRESS TEXT, BUSINESS_TYPE TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add personal details if the email exists
    public boolean addPersonalDetails(String email, String name, String address, String businessType) {
        if (userDatabaseHelper.doesEmailExist(email)) { // Check if the email exists in the user database
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, email);
            contentValues.put(COL_3, name);
            contentValues.put(COL_4, address);
            contentValues.put(COL_5, businessType);

            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1; // If result is -1, insertion failed
        } else {
            return false; // Email does not exist in the user database
        }
    }
}
