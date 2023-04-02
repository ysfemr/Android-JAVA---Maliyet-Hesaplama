package com.example.ozgrmtl_v3.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.ozgrmtl_v3.listeler.Cesitlist;
import com.example.ozgrmtl_v3.listeler.Hammaddelist;
import com.example.ozgrmtl_v3.listeler.Nihailist;
import com.example.ozgrmtl_v3.listeler.Olculist;
import com.example.ozgrmtl_v3.listeler.Urunlist;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int db_v = 1;
    private static final String db_name = "ozgrmtl_v3_db";
    private static final String db_hammadde_tablo = "hammadde";
    private static final String db_urun_tablo = "urun";
    private static final String db_cesit_tablo = "cesit";
    private static final String db_olcu_tablo = "olcu";
    private static final String db_nihai_tablo = "NIHAI";
    public static final String col_1 = "ID";
    public static final String sut_name = "name";
    public static final String col_2 = "URUN";
    public static final String col_3 = "CESIT";
    public static final String col_4 = "OLCU";
    public static final String col_5 = "FATURA";
    public static final String col_6 = "TOPPER";
    public static final String col_7 = "MALIYET";


    public DbHandler(Context context) {
        super(context, db_name, null, db_v);
        Log.i("çalıştı", "db oluştu");

    } // db oluşturma

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("çalıştı", "on create aktif");
        String table1= "CREATE TABLE " + db_hammadde_tablo + "("
                + col_1 + " INTEGER PRIMARY KEY," + sut_name + " TEXT)";
        db.execSQL(table1);
        String table2= "CREATE TABLE " + db_urun_tablo + "("
                + col_1 + " INTEGER PRIMARY KEY," + sut_name + " TEXT)";
        db.execSQL(table2);
        String table3 = "CREATE TABLE " + db_cesit_tablo + "("
                + col_1 + " INTEGER PRIMARY KEY," + sut_name + " TEXT)";
        db.execSQL(table3);
        String table4 = "CREATE TABLE " + db_olcu_tablo + "("
                + col_1 + " INTEGER PRIMARY KEY," + sut_name + " TEXT)";
        db.execSQL(table4);
        db.execSQL("create table " + db_nihai_tablo +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, URUN TEXT, CESIT TEXT, OLCU TEXT, FATURA TEXT, TOPPER TEXT, MALIYET TEXT)");

        Log.i("çalıştı", "tablo 5 oluştu");

    } // tabloları oluşturma

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("çalıştı", "on upgrade aktif");

        db.execSQL("DROP TABLE IF EXISTS " + db_hammadde_tablo);
        db.execSQL("DROP TABLE IF EXISTS " + db_urun_tablo);
        db.execSQL("DROP TABLE IF EXISTS " + db_cesit_tablo);
        db.execSQL("DROP TABLE IF EXISTS " + db_olcu_tablo);
        db.execSQL("DROP TABLE IF EXISTS " + db_nihai_tablo);
        onCreate(db);

    } // tablo güncelleme



    public List<Hammaddelist> gethammaddedata(String hammadde_spin_table) {
        List<Hammaddelist> hamlist = new ArrayList<>();
        String hammaddesorgu = " select * from " + hammadde_spin_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(hammaddesorgu, null);
        if (cursor.moveToFirst()) {
            do {
                Hammaddelist hammaddelist = new Hammaddelist();
                hammaddelist.name = cursor.getString(1);
                hammaddelist.id = cursor.getInt(0);
                hamlist.add(hammaddelist);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return hamlist;

    }   //hammadde veri alma
    public List<Urunlist> geturundata(String urun_spin_table) {
        List<Urunlist> urlist = new ArrayList<>();
        String urunsorgu = " select * from " + urun_spin_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(urunsorgu, null);
        if (cursor.moveToFirst()) {
            do {
                Urunlist urunlist = new Urunlist();
                urunlist.name = cursor.getString(1);
                urunlist.id = cursor.getInt(0);
                urlist.add(urunlist);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return urlist;
    }   //urun veri alma
    public List<Cesitlist> getcesitdata(String cesit_spin_table) {
        List<Cesitlist> celist = new ArrayList<>();
        String cesitsorgu = " select * from " + cesit_spin_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(cesitsorgu, null);
        if (cursor.moveToFirst()) {
            do {
                Cesitlist cesitlist = new Cesitlist();
                cesitlist.name = cursor.getString(1);
                cesitlist.id = cursor.getInt(0);
                celist.add(cesitlist);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return celist;
    }   //cesit veri alma
    public List<Olculist> getolcudata(String olcu_spin_table) {
        List<Olculist> ollist = new ArrayList<>();
        String olcusorgu = " select * from " + olcu_spin_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(olcusorgu, null);
        if (cursor.moveToFirst()) {
            do {
                Olculist olculist = new Olculist();
                olculist.name = cursor.getString(1);
                olculist.id = cursor.getInt(0);
                ollist.add(olculist);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ollist;
    }   //olcu veri alma
    public List<Nihailist> getnihaidata(String nihai_spin_table){
        List<Nihailist> nihlist = new ArrayList<>();
        String nihaisorgu = "select * from " + nihai_spin_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(nihaisorgu, null);
        if (cursor.moveToFirst()){
            do {
                Nihailist nihailist = new Nihailist();
                nihailist.id = cursor.getInt(0);
                nihailist.uname = cursor.getString(1);
                nihailist.cname = cursor.getString(2);
                nihailist.oname = cursor.getString(3);
                nihailist.mname = cursor.getString(6);
                nihlist.add(nihailist);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nihlist;

    }   //nihai veri alma
    public List<Nihailist> getnihaiiddata(String nihai_spin_table){
        List<Nihailist> nihlist = new ArrayList<>();
        String nihaisorgu = "select * from " + nihai_spin_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(nihaisorgu, null);
        if (cursor.moveToFirst()){
            do {
                Nihailist nihailist = new Nihailist();
                nihailist.id = cursor.getInt(0);
                nihailist.mname = cursor.getString(6);
                nihlist.add(nihailist);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nihlist;

    }   //nihai veri alma
    public void insertLabel(String label, String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(sut_name, label);//column name, column value
        // Inserting Row
        db.insert(TABLE_NAME, null, values);//tableName, nullColumnHack, CotentValues
        db.close(); // Closing database connection
    }   //veri ekleme
    public boolean updateLabel(String id ,String label, String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(sut_name, label);//column name, column value
        Integer res = db.update(TABLE_NAME, values, "ID = ? ", new String[] {id});

        return res > 0;

    }   //veri güncelleme
    public boolean updatemaliyetLabel(String id ,String label, String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_7, label);//column name, column value
        // Inserting Row
        Integer res = db.update(TABLE_NAME, values, "ID = ? ", new String[] {id});

        return res > 0;

    }   //maliyet veri güncelleme
    public boolean deleteLabel(String id, String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(TABLE_NAME, "ID = ? ", new String[]{id});
            return true;
        }
        catch (Exception e){
            Log.e("error", "deleteLabel: ekleme hatası" );
            return false;
        }

    }   //veri silme
    public void  nihaiinsert(String urundata, String cesitdata, String olcudata, String faturadata, String toppardata, String maliyetdata, String TABLE_NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_2, urundata);
        values.put(col_3, cesitdata);
        values.put(col_4, olcudata);
        values.put(col_5, faturadata);
        values.put(col_6, toppardata);
        values.put(col_7, maliyetdata);
        db.insert(TABLE_NAME, null, values);
        db.close();

    } //insert data
    public Cursor nihaigetdataurun(String urun){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from NIHAI WHERE URUN LIKE '%"+urun+"%'" , null);
        return cursor;
    }   //nihai ürün data alma

}
