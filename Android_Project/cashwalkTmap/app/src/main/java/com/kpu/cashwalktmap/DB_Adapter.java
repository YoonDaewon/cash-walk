package com.kpu.cashwalktmap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ydwin on 2016-08-19.
 */
public class DB_Adapter {
    private static final String DATABASE_NAME = "CW_DB";
    private static final String DATABASE_TABLE = "CW_DT";
    private static final int DATABASE_VERSION = 1;
    private final Context mCtx;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    //생성테이블
    private static final String DATABASE_CREATE="CREATE TABLE " +DATABASE_TABLE+ "" +
            " (    ID    TEXT PRIMARY    KEY AUTOINCREMENT, CASH INTEGER,   RECORD TEXT)";

    //테이블  드랍
/*
   private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + DATABASE_NAME;
*/

    public static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //데이터베이스 최초 생성될때 실행 디비가 생성될때 실행된다
            Log.d("TEST","onCreate DATABSE_CREATE");
            db.execSQL(DATABASE_CREATE);
        }

        /**
         *
         * @param db         The database.
         * @param oldVersion The old database version.
         * @param newVersion The new database version.
         */
        @Override
        //데이터베이스가 업그레이드가 필요할때
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // db.execSQL( SQL_DELETE_TABLE);
        }





    }

    //
    public void open() throws SQLException {

        mDbHelper = new DatabaseHelper(mCtx);
/*
        DB가 없다면 onCreate가 호출 후 생성, version이 바뀌었다면 onUpgrade 메소드 호출
        mDb = mDbHelper.getWritableDatabase();
*/
        // 권한부여 읽고 쓰기를 위해
        mDb = mDbHelper.getWritableDatabase();
        Log.d("TEST","open");
    }

    public DB_Adapter(Context ctx) {
        this.mCtx = ctx;
    }

    //닫기
    public void close() {
        mDbHelper.close();
    }


    //넣기
    public long insert(String name, int cash, String record) {
        ContentValues insertValues = new ContentValues();

        insertValues.put("NAME", name);
        insertValues.put("CASH", cash);
        insertValues.put("RECORD", record);
        Log.d("TEST","insert suc");
        return mDb.insert(DATABASE_TABLE, null, insertValues);
    }
    //업데이트~
    public long update(String  id, String name, int cash, String record) {
        ContentValues updateValues = new ContentValues();
        updateValues.put("NAME", name);
        updateValues.put("CASH", cash);
        updateValues.put("RECORD", record);
        return mDb.update(DATABASE_TABLE, updateValues, "ID" + "=?", new String[]{id});
    }

    //한개씩삭제
    public boolean deleteRow(String id) {
        return mDb.delete(DATABASE_TABLE, "ID" + "=?", new String[]{id}) > 0;

    }

    //다삭제
    public boolean deleteAll() {
        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

    public Cursor AllRows() {
        return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);

    }
}
