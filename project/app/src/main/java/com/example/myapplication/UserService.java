package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




public class UserService {
    private DatabaseHelper dbHelper;
    public UserService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    public String searchRole(String username){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username= "+username;
        Cursor cursor=sdb.rawQuery(sql,null);
        String role = cursor.getString(cursor.getColumnIndex("role"));
        return role;
    }
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password,role,lastname,firstname,phone,email,address,city,state,zipcode,member,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword(),user.getRole(),user.getLastname(),user.getFirstname(),
        user.getPhone(),user.getEmail(),user.getAddress(),user.getCity(),user.getState(),user.getZipcode(),user.getMember(),user.getStatus()};
        sdb.execSQL(sql, obj);
        return true;
    }
}

