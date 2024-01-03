package com.example.project_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ProfilDB";
    private static final int DATABASE_VERSION = 1;

    // Table et colonnes pour les profils
    public static final String TABLE_INGENIEURS = "ingenieurs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_FORMATIONS_DIPLOMES = "formations_diplomes";
    public static final String COLUMN_EXPERIENCES_STAGES = "experiences_stages";
    public static final String COLUMN_COMPETENCES_TECHNIQUES = "competences_techniques";
    public static final String COLUMN_COMPETENCES_LINGUISTIQUES = "competences_linguistiques";
    public static final String COLUMN_CENTRES_INTERETS = "centres_interets";

    // Requête de création de la table
    private static final String DATABASE_CREATE = "create table "
            + TABLE_INGENIEURS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NOM
            + " text not null, " + COLUMN_FORMATIONS_DIPLOMES
            + " text, " + COLUMN_EXPERIENCES_STAGES
            + " text, " + COLUMN_COMPETENCES_TECHNIQUES
            + " text, " + COLUMN_COMPETENCES_LINGUISTIQUES
            + " text, " + COLUMN_CENTRES_INTERETS
            + " text);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGENIEURS);
        onCreate(db);
    }
}
