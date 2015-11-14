package com.roy_sun.infomanager.infodb.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.roy_sun.infomanager.domain.Person;
import com.roy_sun.infomanager.infodb.InfoDBOpenHelper;

/**
 * 数据库的Dao类
 * Created by Roy_Sun on 2015/11/13 0013.
 */
public class PersonDao {
    private InfoDBOpenHelper helper;

    public PersonDao (Context context) {
        helper = new InfoDBOpenHelper(context);
    }


    /**
     * 添加一条数据
     */
    public boolean add (String person_id, String name, String phone) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("person_id", person_id);
        values.put("name", name);
        values.put("phone", phone);

        long rowid = db.insert("info", null, values);
        db.close();
        return rowid != -1;
    }

    /**
     * 查询输入的号码是否存在
     */
    public boolean find (String person_id) {
        boolean result = false;

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("info", null, "person_id=?", new String[]{person_id}, null, null, null);

        if (cursor.moveToFirst()) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 获取数据库里有多少数据
     */
    public int getTotal () {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null, null);
        int total = cursor.getCount();
        cursor.close();
        db.close();
        return total;
    }

    /**
     * 返回数据库中某个位置的信息
     *
     * @param position 在数据库中的位置
     * @return 对象
     */
    public Person getPersonInfo (int position) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("info", new String[]{ "person_id", "name", "phone" }, null, null, null, null, null);
        cursor.moveToPosition(position);
        String person_id = cursor.getString(0);
        String name = cursor.getString(1);
        String phone = cursor.getString(2);

        Person person = new Person(person_id, name, phone);
        cursor.close();
        db.close();
        return person;
    }

}
