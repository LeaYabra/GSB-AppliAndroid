package fr.be2.gsbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    //constructeur
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

    // mise a jour la bdd si version evolue
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * insere dans la base de donnée les elements passés en parametre
     *
     * @param libelle1
     * @param typeFrais1
     * @param quantite1
     * @param montant1
     * @param date1
     * @return null
     */
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

    public long createlist(String libelle, String quantite,
                           String montant, String date, String datesaisie) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(LIBELLE, libelle);
        initialValues.put(QUANTITE, quantite);
        initialValues.put(MONTANT, montant);
        initialValues.put(DATE, date);
        initialValues.put(DATESAISIE, datesaisie);
        return mDb.insert(DB_TABLE, null, initialValues);
    }

    /**
     * renvoie tous les frais dont la date est rentrée en parametre
     *
     * @return mCursor
     */


    public Cursor fetchAllsynthese() {

        Cursor mCursor = mDb.query(DB_TABLE, new String[]{"rowid _id",
                        ID, LIBELLE, MONTANT, DATE, QUANTITE, DATESAISIE},
                null, null, null, null, null, null);


        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * supprime des info en fonction de l'id renseigne
     *
     * @param ID
     * @return
     */
    public boolean deleteData(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(DB_TABLE, "ID=" + ID, null);

        return result != -1;

    }












}




















