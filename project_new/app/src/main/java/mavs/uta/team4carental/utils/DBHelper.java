package mavs.uta.team4carental.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import mavs.uta.team4carental.pojo.User;

public class DBHelper extends SQLiteOpenHelper {
    private static class TABLE_LIST {
        public static String USER = "tbl_user";
    }

    private ArrayList userFields = new ArrayList<String>();

    public static final String DB_NAME = "user_db";
    public static final int DB_VERSION = 1;

    private static final String USER_CREATE =
        "create table "+ TABLE_LIST.USER + " (" +
                EnumTable.User.ID           + " integer primary key autoincrement, " +
                EnumTable.User.USERNAME     + " varchar(30) not null, " +
                EnumTable.User.PASSWORD     + " varchar(30) not null," +
                EnumTable.User.ROLE         + " varchar(10) not null," +
                EnumTable.User.LASTNAME     + " varchar(30) not null," +
                EnumTable.User.FIRSTNAME    + " varchar(30) not null," +
                EnumTable.User.PHONE        + " varchar(20) not null," +
                EnumTable.User.EMAIL        + " varchar(30) not null," +
                EnumTable.User.ADDRESS      + " varchar(20)," +
                EnumTable.User.CITY         + " varchar(20)," +
                EnumTable.User.STATE        + " varchar(20)," +
                EnumTable.User.ZIPCODE      + " varchar(20)," +
                EnumTable.User.MEMBER       + " varchar(20)," +
                EnumTable.User.STATUS       + " varchar(20) not null" +
        ")";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Field[] fields = User.class.getDeclaredFields();
//        for (Field field: fields) {
//            String field_name = field.toString().split(" ")[2];
//            String[] group = field_name.split("\\.");
//            userFields.add(group[group.length - 1]);
//        }
//        Log.d("DBHelper", "constructor");
//        Log.d("DBHelper", userFields.toString());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBHelper", "create USER");
        db.execSQL(USER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST.USER);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public String addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EnumTable.User.USERNAME, user.getUsername());
        cv.put(EnumTable.User.PASSWORD, user.getPassword());
        cv.put(EnumTable.User.ROLE,     user.getRole());
        cv.put(EnumTable.User.LASTNAME, user.getLastname());
        cv.put(EnumTable.User.FIRSTNAME,user.getFirstname());
        cv.put(EnumTable.User.PHONE,    user.getPhone());
        cv.put(EnumTable.User.EMAIL,    user.getEmail());
        cv.put(EnumTable.User.ADDRESS,  user.getAddress());
        cv.put(EnumTable.User.CITY,     user.getCity());
        cv.put(EnumTable.User.STATE,    user.getState());
        cv.put(EnumTable.User.ZIPCODE,  user.getZipcode());
        cv.put(EnumTable.User.MEMBER,   user.getMember());
        cv.put(EnumTable.User.STATUS,   user.getStatus());

        long res = db.insert(TABLE_LIST.USER, null, cv);
        if(res == -1)
            return "failed";
        else
            return "Account Created Successfully";
    }

    public User[] queryUser(String columns) {
        ArrayList<User> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                "id >= 1",
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex(EnumTable.User.USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(EnumTable.User.PASSWORD));
            String role = cursor.getString(cursor.getColumnIndex(EnumTable.User.ROLE));
            String lastname = cursor.getString(cursor.getColumnIndex(EnumTable.User.LASTNAME));
            String firstname = cursor.getString(cursor.getColumnIndex(EnumTable.User.FIRSTNAME));
            // TODO: not finished
            result.add(new User(
                    username,
                    password,
                    role,
                    lastname,
                    firstname,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            ));
        }
        cursor.close();
        User[] result_user = new User[result.size()];
        result.toArray(result_user);
        return result_user;
    }

    public String queryLogin(String qusername, String qpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_LIST.USER,
                null,
                EnumTable.User.USERNAME + "=\"" + qusername + "\" AND " + EnumTable.User.PASSWORD + "=\"" + qpassword +"\"",
                null,
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String r = cursor.getString(cursor.getColumnIndex(EnumTable.User.ROLE));
            cursor.close();
            return  r;
        } else {
            cursor.close();
            return "None";
        }
    }
}
