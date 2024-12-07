package com.example.termproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "MealPlanner.db";
    static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table if not exists Userbase (_id INTEGER primary key autoincrement, username TEXT not null, password TEXT not null)");
        db.execSQL("create table if not exists LikedFoods (_id INTEGER primary key autoincrement, userID INTEGER not null, mealType TEXT not null, food TEXT not null)");
        db.execSQL("create table if not exists DislikedFoods (_id INTEGER primary key autoincrement, userID INTEGER not null, mealType TEXT not null, food TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists Userbase");
        db.execSQL("drop table if exists LikedFoods");
        db.execSQL("drop table if exists DislikedFoods");
        onCreate(db);
    }


    public long insertNewUser(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues providedValues = new ContentValues();

        providedValues.put("username", username);
        providedValues.put("password", password);

        long insertID = db.insert("Userbase", null, providedValues);
        db.close();

        return insertID;
    }

    public boolean userValidation(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM Userbase WHERE username=? and password=?", new String[] {username, password});
        if (mCursor.getCount() > 0)
        {
            mCursor.close();
            return true;
        }
        else
        {
            mCursor.close();
            return false;
        }
    }

    public int getUserID(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id FROM Userbase WHERE username=? AND password=?", new String[] {username, password});
        if (cursor != null)
        {
            cursor.moveToFirst();
            int usersID = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            cursor.close();
            return usersID;
        }
        else
        {
           return -1;
        }

    }

    public long insertLikedFood(String mealType, String food, int userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues providedValues = new ContentValues();
        providedValues.put("userID", userID);
        providedValues.put("mealType", mealType);
        providedValues.put("food", food);

        long insertID = db.insert("LikedFoods", null, providedValues);
        db.close();

        return insertID;

    }

    public long insertDislikedFood(String mealType, String food, int userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues providedValues = new ContentValues();
        providedValues.put("userID", userID);
        providedValues.put("mealType", mealType);
        providedValues.put("food", food);

        long insertID = db.insert("DislikedFoods", null, providedValues);
        db.close();

        return insertID;

    }

    public Cursor getLikedFoods(int userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT food, mealType FROM LikedFoods WHERE userID=?", new String[]{String.valueOf(userID)});
    }

    public Cursor getDislikedFoods(int userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT food, mealType FROM DislikedFoods WHERE userID=?", new String[]{String.valueOf(userID)});
    }


    public void emptyTable(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + table);
        db.close();
    }

    public boolean isDislikedFood(int userID, String food)
    {
        SQLiteDatabase db = this.getWritableDatabase();

         Cursor c = db.rawQuery("SELECT * FROM DislikedFoods WHERE userID=? AND food=?", new String[] {String.valueOf(userID), food});

         if(c.getCount() == 0) {
             c.close();
             return false;
         }
         else
             return true;
    }

    public boolean isLikedFood(int userID, String food)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM LikedFoods WHERE userID=? AND food=?", new String[] {String.valueOf(userID), food});

        if(c.getCount() == 0) {
            c.close();
            return false;
        }
        else
            return true;
    }



}

