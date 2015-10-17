package com.maric.vlajko.hercules;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Vlajko on 5/27/2015.
 */
public class Database extends SQLiteOpenHelper {

    //singleton pattern
    private static Database instance = null;
    private static Context mCxt;
    private final ArrayList<User> userList = new ArrayList<>();

    public static Database getInstance(Context cxt) {
        if (instance == null) {
        //    Toast.makeText(cxt.getApplicationContext(), "Pravi novu instancu", Toast.LENGTH_SHORT).show();
            instance = new Database(cxt.getApplicationContext());
        }
        mCxt = cxt;
        return instance;
    }

    private final ArrayList<User> users = new ArrayList<>();

    private Database(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    public void connect() {
        System.out.println("Connect");
    }

    public void disconnect() {
        System.out.println("Disconnect");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create our tables
        String CREATE_USERS_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.USER_ID + " TEXT UNIQUE, " + Constants.USER_NAME + " TEXT, " + Constants.USER_FIST_NAME + " TEXT, " + Constants.USER_LAST_NAME + " TEXT);";
        db.execSQL(CREATE_USERS_TABLE);
        String CREATE_RECORD_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_RECORS + "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Constants.KEY_RES_ID + " INTEGER ," + Constants.YEAR + " TEXT," + Constants.MONTH + " TEXT," + Constants.DAY + " TEXT," + Constants.HOUR + " TEXT," + Constants.MINUTE + " TEXT," + Constants.SECOND + " TEXT," + Constants.RESULT_ONE + " REAL, " + Constants.RESULT_TWO + " REAL, " + Constants.RESULT_THREE + " REAL, " + Constants.RESULT_FOUR + " REAL, " + Constants.RESULT_FIVE + " REAL, " + Constants.DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (" + Constants.KEY_RES_ID + ") REFERENCES " + Constants.TABLE_NAME + "(" + Constants.KEY_ID + ")  ON DELETE CASCADE);";
        db.execSQL(CREATE_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        //create a new one
        onCreate(db);
    }

    public void addUser() {
        Log.d("AddUser", "FIRST line");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("AddUser", "SECOND line");
        ContentValues values = new ContentValues(); //data-structure kao hashmap koristi key-vaule
        values.put(Constants.USER_NAME, User.getInstance().getName());
        values.put(Constants.USER_FIST_NAME, User.getInstance().getFName());
        values.put(Constants.USER_LAST_NAME, User.getInstance().getlName());
        values.put(Constants.USER_ID, User.getInstance().getToken());
        //  values.put(Constants.DATE_NAME, java.lang.System.currentTimeMillis());
        Log.d("AddUser", "INSERT VALUES");
        db.insert(Constants.TABLE_NAME, null, values);
        db.close();
    }

    public void addRecord(String token, float a1, float a2, float a3, float a4, float a5) {

        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);


        String[] columns = new String[]{
                Constants.KEY_ID,
                Constants.KEY_RES_ID,
                Constants.RESULT_ONE,
                Constants.RESULT_TWO,
                Constants.RESULT_THREE,
                Constants.RESULT_FOUR,
                Constants.RESULT_FIVE,
                Constants.DATE};
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues(); //data-structure kao hashmap koristi key-vaule
        values.put(Constants.KEY_RES_ID, token);
        values.put(Constants.RESULT_ONE, a1);
        values.put(Constants.RESULT_TWO, a2);
        values.put(Constants.RESULT_THREE, a3);
        values.put(Constants.RESULT_FOUR, a4);
        values.put(Constants.RESULT_FIVE, a5);
        values.put(Constants.DATE, getDateTime());
        values.put(Constants.YEAR, year);
        values.put(Constants.MONTH, month);
        values.put(Constants.DAY, day);
        values.put(Constants.HOUR, hour);
        values.put(Constants.MINUTE, minute);
        values.put(Constants.SECOND, second);


        // String query = "SELECT "+Constants.KEY_ID+" FROM "+Constants.TABLE_NAME+" WHERE "+Constants.USER_ID+"='"+token+"'";
        // Cursor cursor = db.query(db,);
        db.insert(Constants.TABLE_NAME_RECORS, null, values);
        db.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public ArrayList<User> getUsers() {

        // String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME;

        Toast.makeText(mCxt, "GET USERS", Toast.LENGTH_SHORT).show();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.USER_FIST_NAME, Constants.USER_PASSOWRD}, null, null, null, null, " DESC");
        //loop through cursor
        if (cursor.moveToFirst()) {
            do {
                User.getInstance().setFName(cursor.getString(cursor.getColumnIndex(Constants.USER_FIST_NAME))); //uzimamo pocetno ime korisnika
                User.getInstance().setPassword(cursor.getString(cursor.getColumnIndex(Constants.USER_PASSOWRD)));

                Toast.makeText(mCxt, cursor.getString(cursor.getColumnIndex(Constants.USER_FIST_NAME)), Toast.LENGTH_LONG).show();

                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                //String dateData = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.DATE))).getTime()); //uzimam vreme kreacije
                //TODO user.setDate(dateData);
                userList.add(User.getInstance());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }

    public ArrayList<String> getYears() {
            //API pogledati
        ArrayList<String> years = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT " + Constants.YEAR + " from " + Constants.TABLE_NAME_RECORS + " WHERE " + Constants.KEY_RES_ID + "='" + User.getInstance().getToken() + "'", null);
        if(cursor.moveToFirst()){
            do {
                years.add(cursor.getString(cursor.getColumnIndex(Constants.YEAR)));
            }
            while (cursor.moveToNext());
        }
        Collections.sort(years);
        return years;
        }

    public ArrayList<String> getMonths(String year) {
      //  String[] monthArray = mCxt.getResources().getStringArray(R.array.months);
        ArrayList<String> months = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT " + Constants.MONTH + " from " + Constants.TABLE_NAME_RECORS + " WHERE "+Constants.KEY_RES_ID+"='"+User.getInstance().getToken()+"'AND "+Constants.YEAR+"='"+year+"'", null);
        if(cursor.moveToFirst()){
            do {
                months.add(Integer.toString(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.MONTH)))+1));
            }
            while (cursor.moveToNext());
        }
      //  for(String month:months){

       /*     for(int i = 0 ;i < months.size(); i++){
                if(months.get(i)!=null) {
                    months.set(i, monthArray[Integer.parseInt(months.get(i).trim())].toString());
                   // months.set(i,"Vlajko");
                    Toast.makeText(mCxt.getApplicationContext(), months.get(i), Toast.LENGTH_SHORT).show();
                }
            }*/


           // months.set(9,"October");

        /*    switch (month.trim().toString()){
                case "1": month = monthArray[0];
                   // break;
                case "2": month = monthArray[1];
                  //  break;
                case "3": month = monthArray[2];
                   // break;
                case "4": month = monthArray[3];
                  //  break;
                case "5": month = monthArray[4];
                  //  break;
                case "6": month = monthArray[5];
                 //   break;
                case "7": month = monthArray[6];
                  //  break;
                case "8": month = monthArray[7];
                  //  break;
                case "9": month = monthArray[8];
                  //  break;
                case "10": month = monthArray[9];
                  //  break;
                case "11": month = monthArray[10];
                  //  break;
                case "12": month = monthArray[11];
                  //  break;

            }

        }*/
        return months;
    }

    /*public ArrayList<String> getA1Values(String year, String month, String day) {
    }
    public ArrayList<String> getA2Values(String year, String month, String day) {
    }
    public ArrayList<String> getA3Values(String year, String month, String day) {
    }
    public ArrayList<String> getA4Values(String year, String month, String day) {
    }
    public ArrayList<String> getA5Values(String year, String month, String day) {
    }*/
}