package com.sxau.novel.LoginUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserInfoManager {
    private MyDataBaseHelper myDataBaseHelper;
    public UserInfoManager(Context context){
        myDataBaseHelper=new MyDataBaseHelper(context);
    }

    public boolean login(String username,String password){
        SQLiteDatabase sdb=myDataBaseHelper.getReadableDatabase();
        String sql="select * from userinfo where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql,new String[]{username,password});
        if (cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean register(User user){
        SQLiteDatabase sdb=myDataBaseHelper.getReadableDatabase();
        String sql="insert into userinfo(Username,password,age,gender,nickname) values(?,?,?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword(),user.getAge(),user.getGender(),user.getNickname()};
        sdb.execSQL(sql,obj);
        return true;
    }

}
