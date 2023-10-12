package com.example.payrollmanagementsystemminorproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Emp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Tblemp(id TEXT primary key,name TEXT,age TEXT,gross TEXT)");
        DB.execSQL("create Table Tblsalary(salary TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i2) {
        DB.execSQL("drop Table if exists Tblemp");
        DB.execSQL("drop Table if exists Tblsalary");

    }

    public Boolean insertempdata(String id, String name, String age, String gross) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("Name", name);
        contentValues.put("Age", age);
        contentValues.put("Gross", gross);
        long result = DB.insert("Tblemp", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateempdata(String id, String name, String age, String gross) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Age", age);
        contentValues.put("Gross", gross);
        Cursor cursor = DB.rawQuery("Select * from Tblemp where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Tblemp", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Boolean deleteempdata(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tblemp where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Tblemp", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor searchdata(String id){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Tblemp where id=?",new String[]{id});
        return cursor;

    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tblemp", null);
        return cursor;
    }

    public Boolean insert(String salary) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Salary", salary);
        long result = DB.insert("Tblsalary", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean delete(String salary) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tblsalary where salary=?", new String[]{salary});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Tblsalary", "salary=?", new String[]{salary});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    public Cursor getall() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Tblsalary", null);
        return cursor;

    }

}

