package rikkesoft.trongvu.com.sqlite_832019.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaoDataBaseNhanVien extends SQLiteOpenHelper {
    public static final String DB_QUANLYNHANVIEN = "QuanLyNhanVien";
    public static final int DB_VERSION = 1;
    public static  final String TABLE_NHANVIEN = "NHANVIEN";
    public  static  final String ID_TBNHANVIEN = "_ID";
    public static final String TENNHANVIEN_TBNHANVIEN = "TenNhanVien";
    public TaoDataBaseNhanVien(Context context) {
        super(context, DB_QUANLYNHANVIEN, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cautruyvan ="CREATE TABLE " + TABLE_NHANVIEN + " ("+ ID_TBNHANVIEN + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ TENNHANVIEN_TBNHANVIEN +" TEXT);";
        db.execSQL(cautruyvan);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NHANVIEN);
        onCreate(db);
    }
}
