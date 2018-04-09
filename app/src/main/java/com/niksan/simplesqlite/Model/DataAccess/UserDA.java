package com.niksan.simplesqlite.Model.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.niksan.simplesqlite.Model.TableObject.User;
import com.niksan.simplesqlite.MysqliteDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class UserDA {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private String[] userColumns = {MysqliteDatabase.USER_ID, MysqliteDatabase.USER_NAME};

    public UserDA(Context context) {
        sqLiteOpenHelper = new MysqliteDatabase(context);
    }

    public void addUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MysqliteDatabase.USER_NAME, user.getName());
        database.insert(MysqliteDatabase.TABLE_NAME, null, contentValues);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(MysqliteDatabase.TABLE_NAME, userColumns,
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                //user.setId(cursor.getInt(0));
                user.setId(cursor.getInt(cursor.getColumnIndex(MysqliteDatabase.USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(MysqliteDatabase.USER_NAME)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public User getUserById(int id) {
        User user = new User();
        Cursor cursor = database.query(MysqliteDatabase.TABLE_NAME, userColumns,
                MysqliteDatabase.USER_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex(MysqliteDatabase.USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(MysqliteDatabase.USER_NAME)));
         }
        cursor.close();
        return user ;
    }

    public List<User> getUserByName(String name) {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(MysqliteDatabase.TABLE_NAME, userColumns,
                MysqliteDatabase.USER_NAME + "=?",
                new String[] {name}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                //user.setId(cursor.getInt(0));
                user.setId(cursor.getInt(cursor.getColumnIndex(MysqliteDatabase.USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(MysqliteDatabase.USER_NAME)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }


    public void updateUserById(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MysqliteDatabase.USER_NAME, user.getName());
        database.insert(MysqliteDatabase.TABLE_NAME, null, contentValues);

        database.update(MysqliteDatabase.TABLE_NAME, contentValues,
                         MysqliteDatabase.USER_ID + "=?",
                         new String[] {String.valueOf(user.getId())} );
    }

    public void updateUserByName(User user, String userName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MysqliteDatabase.USER_NAME, user.getName());
        database.insert(MysqliteDatabase.TABLE_NAME, null, contentValues);

        database.update(MysqliteDatabase.TABLE_NAME, contentValues,
                MysqliteDatabase.USER_NAME + "=?",
                new String[] {userName} );
    }

    public void deleteUserById(int id){

        database.delete(MysqliteDatabase.TABLE_NAME,
                MysqliteDatabase.USER_ID + "=?",
                new String[] {String.valueOf(id)} );
    }

    public void openDb() {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void closeDb() {
        database.close();
    }

}
