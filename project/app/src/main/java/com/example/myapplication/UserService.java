package com.example.myapplication;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class UserService extends IntentService {
    private DatabaseHelper dbHelper;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    public UserService(Context context){
        super("[UserService] startup user service");
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        
    }

    public boolean login(String username, String password){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    public String searchRole(String username){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql="select * from user where username= "+username;
        Cursor cursor=sdb.rawQuery(sql,null);
        String role = cursor.getString(cursor.getColumnIndex("role"));
        return role;
    }

    public boolean register(User user){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql="insert into user(username,password,role,lastname,firstname,phone,email,address,city,state,zipcode,member,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword(),user.getRole(),user.getLastname(),user.getFirstname(),
        user.getPhone(),user.getEmail(),user.getAddress(),user.getCity(),user.getState(),user.getZipcode(),user.getMember(),user.getStatus()};
        sdb.execSQL(sql, obj);
        return true;
    }

}

