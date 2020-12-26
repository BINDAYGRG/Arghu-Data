package com.arghudata.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Binaya Gurung on 25/03/2018.
 */

public class SQLiteHandler extends SQLiteOpenHelper {


    private static final String TAG = SQLiteHandler.class.getSimpleName();


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "arghu";

    private static final String TABLE_USER = "arghu";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAMEENG = "nameeng";
    private static final String KEY_NAMENEP = "namenep";
    private static final String KEY_OLDADDRESSENG = "oldaddresseng";
    private static final String KEY_OLDADDRESSNEP = "oldaddressnep";
    private static final String KEY_CURRENTADDRESSENG = "currentaddresseng";
    private static final String KEY_CURRENTADDRESSNEP = "currentaddressnep";
    private static final String KEY_VETIENG = "vetieng";
    private static final String KEY_VETINEP = "vetinep";
    private static final String KEY_SYAIENG = "syaieng";
    private static final String KEY_SYAINEP = "syainep";
    private static final String KEY_REFERENCE = "reference";

    public SQLiteHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAMEENG + " TEXT,"
                + KEY_NAMENEP + " TEXT,"
                + KEY_OLDADDRESSENG + " TEXT,"
                + KEY_OLDADDRESSNEP + " TEXT,"
                + KEY_CURRENTADDRESSENG + " TEXT,"
                + KEY_CURRENTADDRESSNEP + " TEXT,"
                + KEY_VETIENG + " TEXT,"
                + KEY_VETINEP + " TEXT,"
                + KEY_SYAIENG + " TEXT,"
                + KEY_SYAINEP + " TEXT,"
                + KEY_REFERENCE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addUser(String nameeng, String namenep, String oldaddresseng, String oldaddressnep, String currentaddresseng, String currentaddressnep, String vetieng, String vetinep, String syaieng, String syainep, String reference) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMEENG, nameeng); // Name
        values.put(KEY_NAMENEP, namenep); // Name
        values.put(KEY_OLDADDRESSNEP, oldaddressnep); // Email
        values.put(KEY_OLDADDRESSENG, oldaddresseng); // Email
        values.put(KEY_CURRENTADDRESSENG, currentaddresseng); // Email
        values.put(KEY_CURRENTADDRESSNEP, currentaddressnep); // Email
        values.put(KEY_VETIENG, vetieng); // Email
        values.put(KEY_VETINEP, vetinep); // Email
        values.put(KEY_SYAIENG, syaieng); // Email
        values.put(KEY_SYAINEP, syainep); // Email
        values.put(KEY_REFERENCE, reference); // Email

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New data inserted into sqlite: " + id);


    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("namenep", cursor.getString(1));
            user.put("oldaddressnep", cursor.getString(2));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }
///sqlite database function to list all the contacts
    public ArrayList<ContactsSQLite> listContacts() {
        String sql = "select * from " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ContactsSQLite> storeContacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String nameeng = cursor.getString(1);
                String namenep = cursor.getString(2);
                String oldaddresseng = cursor.getString(3);
                String oldaddressnep = cursor.getString(4);
                String currentaddresseng = cursor.getString(5);
                String currentaddressnep = cursor.getString(6);
                String vetieng = cursor.getString(7);
                String vetinep = cursor.getString(8);
                String syaieng = cursor.getString(9);
                String syainep = cursor.getString(10);
                String reference = cursor.getString(11);
                storeContacts.add(new ContactsSQLite(nameeng, namenep, oldaddresseng, oldaddressnep, currentaddresseng, currentaddressnep, vetieng, vetinep, syaieng, syainep, reference));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeContacts;
    }
//sqlite database function to search the datas
    public ArrayList<ContactsSQLite> search(String keyword) {

            String sql = "select * from " + TABLE_USER;
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<ContactsSQLite> storeContacts = new ArrayList<>();
        //Cursor cursor = db.rawQuery("select * from " + TABLE_USER + " where nameeng like ?", new String[] { "%" + keyword + "%" });
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER + " WHERE nameeng LIKE ? OR currentaddresseng LIKE ? OR oldaddresseng LIKE ? OR oldaddresseng LIKE ? ", new String[] {"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"});
            if (cursor.moveToFirst()) {
                do {
                    String nameeng = cursor.getString(1);
                    String namenep = cursor.getString(2);
                    String oldaddresseng = cursor.getString(3);
                    String oldaddressnep = cursor.getString(4);
                    String currentaddresseng = cursor.getString(5);
                    String currentaddressnep = cursor.getString(6);
                    String vetieng = cursor.getString(7);
                    String vetinep = cursor.getString(8);
                    String syaieng = cursor.getString(9);
                    String syainep = cursor.getString(10);
                    String reference = cursor.getString(11);
                    storeContacts.add(new ContactsSQLite(nameeng, namenep, oldaddresseng, oldaddressnep, currentaddresseng, currentaddressnep, vetieng, vetinep, syaieng, syainep, reference));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
            return storeContacts;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_USER + "'");
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
//check if it contains database or not
    public int getcount() {

        String selectQuery = "SELECT namenep FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int countnumber = cursor.getCount();
        // Move to first row
        cursor.close();
        db.close();
        // return user
        return countnumber;

    }


}
