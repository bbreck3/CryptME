package com.example.cryptme3;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Comment;

/**
 * Created by breck on 7/7/2016.
 */
public class PasswordsDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] columns = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,
            MySQLiteHelper.COLUMN_PASSWORD

    };

    public PasswordsDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);

    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Passwords createPassword(String password){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ID, password);
        long insertID = database.insert(MySQLiteHelper.TABLE_PASSWORDS,null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PASSWORDS, columns,MySQLiteHelper.COLUMN_ID+
                " = " + insertID, null,null,null,null);

        cursor.moveToFirst();
        Passwords newPassword = cursorToPasswords(cursor);
        cursor.close();
        return newPassword;
    }

    public void deletePasswords(Passwords password){
        long id = password.getID();

        Log.d("Debug", "Comment deleted from Database" + id);
        database.delete(MySQLiteHelper.TABLE_PASSWORDS,
                MySQLiteHelper.COLUMN_ID  + " = " + id, null);
    }

    public List<Passwords> getAllPasswords(){
        List<Passwords> passwords = new ArrayList<Passwords>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PASSWORDS, columns, null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Passwords password = cursorToPasswords(cursor);
            passwords.add(password);
            cursor.moveToNext();
            }
            cursor.close();
            return passwords;
    }

    private Passwords cursorToPasswords(Cursor cursor) {
        Passwords password = new Passwords();
        password.setId(cursor.getLong(0));
        password.setPassword(cursor.getString(1));
        return password;
    }


}
