package tecnm.chihuahua2.desarrollomovil.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table gramineas" +
                "(id integer primary key autoincrement," +
                " nombre text, " +
                "descripcion text" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("create table gramineas" +
                "(id integer primary key autoincrement," +
                " nombre text, " +
                "descripcion text)");
    }


    //metodo para consultar la tabla gramineas
    //obtener todo los recursos de la tabla gramineas

    public Cursor consultaGra(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from gramineas", null);
        return cursor;
    }
}