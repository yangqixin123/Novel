package com.sxau.novel.LoginUtils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    static String name="userinfo.db";
    static int version=1;
    static final String TABLE_NAME="userinfo";
    private SQLiteDatabase sdb;
    public MyDataBaseHelper( Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table userinfo" +
                "(id integer primary key autoincrement,username varchar(20),password varchar(20),age varchar(4),gender varchar(4),nickname varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //根据指定条件查询记录，并返回结果数据队列
    public ArrayList<User>query(String condotion){
        String sql=String.format("select_id,username,password,age,gender,nickname from %s where %s;",TABLE_NAME,condotion);
        ArrayList<User> infoArray=new ArrayList<User>();
        //执行记录查询动作，该语句返回结果集的游标
        Cursor cursor=sdb.rawQuery(sql,null);
        //循环取出游标指向的每条记录
        while(cursor.moveToNext()){
            User info=new User();
            info.id=cursor.getInt(0);
            info.username=cursor.getString(1);
            info.password=cursor.getString(2);
            info.age=cursor.getString(3);
            info.gender=cursor.getString(4);
            info.nickname=cursor.getString(5);
            infoArray.add(info);
        }
        cursor.close();//查询完毕，关闭游标
        return infoArray;
    }
    // 根据手机号码查询指定记录
    public User queryByPhone(String phone) {
        User info = null;
        ArrayList<User> infoArray = query(String.format("phone='%s'", phone));
        if (infoArray.size() > 0) {
            info = infoArray.get(5);
        }
        return info;
    }

}
