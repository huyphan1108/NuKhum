package com.example.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert (String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String email, String emergencyContact){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.F_NAME, firstName);
        contentValues.put(DatabaseHelper.L_NAME, lastName);
        contentValues.put(DatabaseHelper.DOB, dateOfBirth);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.PNUMBER, phoneNumber);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.E_CONTACT, emergencyContact);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String [] columns = new String [] {DatabaseHelper.ID, DatabaseHelper.F_NAME, DatabaseHelper.L_NAME, DatabaseHelper.DOB, DatabaseHelper.GENDER, DatabaseHelper.PNUMBER, DatabaseHelper.EMAIL, DatabaseHelper.E_CONTACT};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch_name(){
        String [] columns = new String [] {DatabaseHelper.ID, DatabaseHelper.F_NAME};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }


    public int update(long _id, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String email, String emergencyContact){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.F_NAME, firstName);
        contentValues.put(DatabaseHelper.L_NAME, lastName);
        contentValues.put(DatabaseHelper.DOB, dateOfBirth);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.PNUMBER, phoneNumber);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.E_CONTACT, emergencyContact);
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.ID + "=" + _id, null);
        return ret;
    }

    public void delete (long _id){
        database.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.ID+ "=" + _id, null);
    }
}
