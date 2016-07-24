package com.example.cryptme3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by breck on 7/7/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_PASSWORDS = "passwords";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_PASSWORD ="_password";

    private static final String DATABASE_NAME = "passwords";
    private static final int DATABASE_VERSION = 1;

    //Create DB-->

    private static final String DATABASE_CREATE = "CREATE TABLE " +
            TABLE_PASSWORDS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT " +
            COLUMN_PASSWORD + " TEXT );";

    private final String CREATE_PASSWORDS_TABLE = "CREATE TABLE " + TABLE_PASSWORDS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    //DB Constructor
    public MySQLiteHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("Debug:" , "Reached createDB");
        String CREATE_PASSWORDS_TABLE = "CREATE TABLE " + TABLE_PASSWORDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PASSWORDS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        Log.w(MySQLiteHelper.class.getName()," Upgrading database from version " +
                oldV +
                " to " +
                newV + " which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORDS);
            onCreate(db);
    }

    public void addPasswords(Passwords passwords) {
        Log.d("Debug:", "addPasswords");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, passwords.getID()); //
        values.put(COLUMN_NAME, passwords.getName()); //
        values.put(COLUMN_PASSWORD, passwords.getPassword());

        // Inserting Row
        db.insert(TABLE_PASSWORDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Passwords getPasword(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PASSWORDS, new String[] { COLUMN_ID,
                        COLUMN_NAME, COLUMN_PASSWORD }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Passwords password = new Passwords(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return password;
    }

    // Getting All Contacts
    public List<Passwords> getAllPasswords() {
        List<Passwords> passwordList = new ArrayList<Passwords>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PASSWORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Passwords password = new Passwords();
                password.setId(Integer.parseInt(cursor.getString(0)));
                password.setName(cursor.getString(1));
                password.setPassword(cursor.getString(2));
                // Adding contact to list
                passwordList.add(password);
            } while (cursor.moveToNext());
        }

        // return contact list
        return passwordList;
    }

    // Updating single contact
    public int updatePasswords(Passwords password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, password.getName());
        values.put(COLUMN_PASSWORD, password.getPassword());
        values.put(COLUMN_NAME, password.getName());

        // updating row
        return db.update(TABLE_PASSWORDS, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(password.getID()) });
    }

    // Deleting single contact
    public void deletePassword(Passwords password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PASSWORDS, COLUMN_ID + " = ?",
                new String[] { String.valueOf(password.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getPasswordCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PASSWORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}
