package com.example.sparkbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9764587245,'Ghatotkacha',1890.00,'ghatotkach.DeamonSon@gmail.com','XXXXXXXXXXXX9865','ABC09876543')");
        db.execSQL("insert into user_table values(8857681004,'Karna',2500.45,'karna.SunSon@gmail.com','XXXXXXXXXXXX4686','PQR98765432')");
        db.execSQL("insert into user_table values(7850836855,'Yudhishtir',2356.56,'yudhishtir.Intelligent@gmail.com','XXXXXXXXXXXX3647','STU87654321')");
        db.execSQL("insert into user_table values(7584009224,'Dronacharya',9876.98,'dronacharya.OverPowerd@gmail.com','XXXXXXXXXXXX4842','SBI76543210')");
        db.execSQL("insert into user_table values(8646293008,'Draupati',7864.01,'draupati.CozOfMahabharat@gmail.com','XXXXXXXXXXXX9429','ICI65432109')");
        db.execSQL("insert into user_table values(7217998355,'Gandhari',5936.00,'gandhari.ShambharPutriBhava@gmail.com','XXXXXXXXXXXX9242','JSB43210987')");
        db.execSQL("insert into user_table values(8907131898,'Durhyaodhan',9134.32,'durhyodhan.SayNoToPadavavs@gmail.com','XXXXXXXXXXXX6987','HFC32109876')");
        db.execSQL("insert into user_table values(9011386553,'Arjun',4398.46,'arjun.overhyped@gmail.com','XXXXXXXXXXXX7242','JKL21098765')");
        db.execSQL("insert into user_table values(8185118075,'Bhishma',2973.90,'bhishma.IamImmortal@gmail.com','XXXXXXXXXXXX8151','QWE10987654')");
        db.execSQL("insert into user_table values(8187465428,'Shakuni',3675.80,'shakuniMama.MereBachee@gmail.com','XXXXXXXXXXXX9972','RTY185687654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
