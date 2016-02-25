package com.helloworld.tarjinder.sqlitedemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db"; //.db extension tells android that there is database file stored
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query ="CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_PRODUCTNAME+" TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //i=oldversion,i1=new version
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS );
        onCreate(sqLiteDatabase);
    }

    //Add new row to the database
    public void addProduct(Products products){
        ContentValues values=new ContentValues();
        values.put(COLUMN_PRODUCTNAME, products.get_productName());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();  // gives reference to database
        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values);
        sqLiteDatabase.close();
    }

    //Delete row from db
    public void deleteProduct(String productName){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //Print out db as string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();  // gives reference to database
        String query="SELECT * FROM "+ TABLE_PRODUCTS+" WHERE 1;"; // WHERE 1 = when every condition is met and SELECT every row

        //Cursor point to location in your results
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);

        //move to first in your results
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if (cursor.getString(cursor.getColumnIndex("productname"))!=null){
                dbString +=cursor.getString(cursor.getColumnIndex("productname"));
                dbString += "\n";
            }
        }
        sqLiteDatabase.close();
        return dbString;
    }
}
