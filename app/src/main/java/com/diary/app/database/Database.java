package com.diary.app.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.diary.app.Models.DiaryModel;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="appDatabase.db";
    private static final int DATABASE_Version = 1;
    private static final String DIARY_TABLE="diary_table";
    private static final String DIARY_ID="diary_id";
    private static final String DIARY_TEXT="diary_text";
    private static final String DIARY_DATE="diary_date";
    public String CREATE_DIARY_TABLE = "CREATE TABLE " + DIARY_TABLE + "("
            + DIARY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " //index 0
            + DIARY_TEXT+ " TEXT, "       //index 1
            + DIARY_DATE+ " TEXT )";     //index 2
    private static final String DROP_DIARY_TABLE ="DROP TABLE IF EXISTS "+DIARY_TABLE;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DIARY_TABLE);
        db.execSQL(CREATE_DIARY_TABLE);
        onCreate(db);
    }
    public boolean insertDiary(DiaryModel diaryModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
            contentValues.put(DIARY_TEXT,diaryModel.getText());
            contentValues.put(DIARY_DATE,diaryModel.getDate());
        long result=db.insert(DIARY_TABLE,null,contentValues);
        db.close();
        return result != -1;
    }
    public List<DiaryModel> getALL(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select * from "+DIARY_TABLE,null );
        List<DiaryModel> diaryModels=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                DiaryModel diaryModel=new DiaryModel();
                diaryModel.setId(cursor.getString(0));
                diaryModel.setText(cursor.getString(1));
                diaryModel.setDate(cursor.getString(2));
                diaryModels.add(diaryModel);
            }while (cursor.moveToNext());
        }
        return diaryModels;
    }
}