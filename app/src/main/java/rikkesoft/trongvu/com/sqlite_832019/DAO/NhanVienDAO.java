package rikkesoft.trongvu.com.sqlite_832019.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.ContextMenu;

import java.util.ArrayList;
import java.util.List;

import rikkesoft.trongvu.com.sqlite_832019.DTO.NhanVienDTO;
import rikkesoft.trongvu.com.sqlite_832019.SQLiteHelper.TaoDataBaseNhanVien;

public class NhanVienDAO {

    SQLiteDatabase database;
    TaoDataBaseNhanVien taoDataBaseNhanVien;
    String[] columnNhanVien = {taoDataBaseNhanVien.ID_TBNHANVIEN, taoDataBaseNhanVien.TENNHANVIEN_TBNHANVIEN};

    public NhanVienDAO(Context context) {
        taoDataBaseNhanVien = new TaoDataBaseNhanVien(context);
    }

    public void open() {
        database = taoDataBaseNhanVien.getWritableDatabase();
    }

    public void close() {
        taoDataBaseNhanVien.close();
    }

    public boolean themNhanVien(NhanVienDTO nhanVienDTO) {
        ContentValues contentValues = new ContentValues();
        //contentValues.put(taoDataBaseNhanVien.ID_TBNHANVIEN,""); bởi nó tự động tăng
        contentValues.put(taoDataBaseNhanVien.TENNHANVIEN_TBNHANVIEN, nhanVienDTO.getTenNhanVien().toString());
        long id_nhanvien = database.insert(taoDataBaseNhanVien.TABLE_NHANVIEN, null, contentValues);
        if (id_nhanvien != 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<NhanVienDTO> layDanhSachNhanVien() {
        List<NhanVienDTO> nhanVienDTOList = new ArrayList<>();
        String cautruyvan = "select * from " + taoDataBaseNhanVien.TABLE_NHANVIEN + ";";
        //Cursor cursor = database.rawQuery(cautruyvan,null);ó
        Cursor cursor = database.query(taoDataBaseNhanVien.TABLE_NHANVIEN, columnNhanVien, null,
                null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id_NV = cursor.getInt(cursor.getColumnIndex(taoDataBaseNhanVien.ID_TBNHANVIEN));
            String ten_NV = cursor.getString(cursor.getColumnIndex(taoDataBaseNhanVien.TENNHANVIEN_TBNHANVIEN));
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.set_id(id_NV);
            nhanVienDTO.setTenNhanVien(ten_NV);
            nhanVienDTOList.add(nhanVienDTO);
            cursor.moveToNext();
        }
        cursor.close();
        return nhanVienDTOList;
    }

    /*
    xóa dữ liệu trong database
     */
    public boolean xoaDuLieu(NhanVienDTO nhanVienDTO) {
        int kt = database.delete(TaoDataBaseNhanVien.TABLE_NHANVIEN,
                taoDataBaseNhanVien.ID_TBNHANVIEN + " = " + nhanVienDTO.get_id(), null);
        Log.d("NhanVienDAO", String.valueOf(nhanVienDTO.get_id()));
        if (kt != 0) {
            return true;
        } else {
            return false;
        }
    }
}
