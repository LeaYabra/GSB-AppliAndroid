package fr.be2.gsbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import static android.content.ContentValues.TAG;

public class SQLhelper<AUTOINCREMENT> extends SQLiteOpenHelper {
    private static final String DB_NAME = "GSB2.db";
    private static final String DB_TABLE = "Frais_Table";
    public static final String ID = "ID";
    public static final String LIBELLE = "Libelle";
    public static final String QUANTITE = "Quantite";
    public static final String MONTANT = "Montant";
    public static final String DATE = "Date";
    public static final String DATESAISIE = "DateSaisie";

    private static final String CREATE_TABLE = "CREATE TABLE Frais_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, Libelle TEXT, Quantite Integer, Montant Float, Date String,DateSaisie DATETIME DEFAULT CURRENT_TIMESTAMP)";
    private SQLhelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context monContexte;

    public SQLhelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.monContexte = context;

    }

    public SQLhelper open() throws SQLException {
        mDbHelper = new SQLhelper(monContexte);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.w(TAG, CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String typeFrais1, Integer quantite1, String date1, double montant1, String libelle1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Libelle", libelle1);
        contentValues.put("Quantite", quantite1);
        contentValues.put("Montant", montant1);
        contentValues.put("Date", date1);


        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    public Cursor fetchSQLhelper(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;

        mCursor = mDb.query(true, CREATE_TABLE, new String[]{ID, LIBELLE, QUANTITE, MONTANT,
                        DATE, DATESAISIE},
                ID + " like '%" + inputText + "%'", null,
                null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllsynthese() {

        Cursor mCursor = mDb.query(DB_TABLE, new String[]{"rowid _id", ID, LIBELLE, MONTANT,
                DATE, QUANTITE, DATESAISIE}, null, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + DB_TABLE;
        Cursor pointeur = db.rawQuery(query, null);
        return pointeur;

    }


    public boolean deleteData(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(DB_TABLE, "ID=" + ID, null);

        return result != -1;

    }


}