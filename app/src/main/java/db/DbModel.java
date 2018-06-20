package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbModel extends SQLiteOpenHelper {

    public static String dbName = "super.db";
    public static String connectionTable = "connection";
    public static String pinTable = "pinTable";
    public static String hostColumn = "host";
    public static String portColumn = "port";
    public static String pinName = "pinName";
    public static String pinId = "pinId";
    public SQLiteDatabase db;

    public static String createTable = "CREATE TABLE if not exists " + connectionTable + "("
            + hostColumn + " TEXT,"
            + portColumn + " TEXT)";

    public static String createPinTable = "CREATE TABLE if not exists " + pinTable + "("
            + pinName + " TEXT,"
            + pinId + " TEXT )";



    public DbModel(Context context) {
        super(context, dbName, null, 1);
    }


    public static String getConnectionTable() {
        return connectionTable;
    }

    public static void setConnectionTable(String connectionTable) {
        DbModel.connectionTable = connectionTable;
    }

    public static String getHostColumn() {
        return hostColumn;
    }

    public static void setHostColumn(String hostColumn) {
        DbModel.hostColumn = hostColumn;
    }

    public static String getPortColumn() {
        return portColumn;
    }

    public static void setPortColumn(String portColumn) {
        DbModel.portColumn = portColumn;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS connection");
        db.execSQL("DROP TABLE IF EXISTS pinTable");
        db.execSQL(createTable);
        db.execSQL(createPinTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS connection");
        db.execSQL("DROP TABLE IF EXISTS pinTable");
        onCreate(db);
    }

    public boolean insertData(String hostName, String portName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS connection");
        onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(hostColumn,hostName);
        contentValues.put(portColumn,portName);
        db.insert(connectionTable,null,contentValues);
        return true;
    }

    public boolean insertPinData(String pinText, String pinNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(pinName,pinText);
        contentValues.put(pinId,pinNumber);
        db.insert(pinTable,null,contentValues);
        return true;

    }

    public Cursor getHostAndPort(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from connection",null);
        return cursor;
    }

    public Cursor getPins(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from pinTable",null);
        return cursor;
    }

}
