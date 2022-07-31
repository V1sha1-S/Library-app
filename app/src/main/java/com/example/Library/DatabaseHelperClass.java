package com.example.Library;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BookLibrary.db";

    private static final String TABLE_NAME = "my_library";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";
    private SQLiteDatabase sqLiteDatabase;


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_AUTHOR + " TEXT, " +
            COLUMN_PAGES + " INTEGER);";

    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(LibraryModelClass libraryModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.COLUMN_TITLE, libraryModelClass.getBook_title());
        contentValues.put(DatabaseHelperClass.COLUMN_AUTHOR, libraryModelClass.getBook_author());
        contentValues.put(DatabaseHelperClass.COLUMN_PAGES, libraryModelClass.getPages());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<LibraryModelClass> getBookList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<LibraryModelClass> storeBook = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        try{
            if (cursor.moveToFirst()){
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    String Book_title = cursor.getString(1);
                    String Book_author = cursor.getString(2);
                    String Pages = cursor.getString(3);
                    storeBook.add(new LibraryModelClass(id,Book_title,Book_author,Pages));
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){

        }
        return storeBook;
    }

    public void updateEmployee(LibraryModelClass libraryModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.COLUMN_TITLE, libraryModelClass.getBook_title());
        contentValues.put(DatabaseHelperClass.COLUMN_AUTHOR, libraryModelClass.getBook_author());
        contentValues.put(DatabaseHelperClass.COLUMN_PAGES, libraryModelClass.getPages());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID + " = ?" , new String[]
                {String.valueOf(libraryModelClass.getId())});
    }

    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
