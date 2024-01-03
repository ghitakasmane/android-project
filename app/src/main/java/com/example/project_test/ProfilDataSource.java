package com.example.project_test;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfilDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ProfilDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Ajouter un ingénieur
    public long ajouterIngenieur(Ingenieur ingenieur) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOM, ingenieur.getNom());
        values.put(DBHelper.COLUMN_FORMATIONS_DIPLOMES, ingenieur.getFormationsEtDiplomes());
        values.put(DBHelper.COLUMN_EXPERIENCES_STAGES, ingenieur.getExperiencesEtStages());
        values.put(DBHelper.COLUMN_COMPETENCES_TECHNIQUES, ingenieur.getCompetencesTechniques());
        values.put(DBHelper.COLUMN_COMPETENCES_LINGUISTIQUES, ingenieur.getCompetencesLinguistiques());
        values.put(DBHelper.COLUMN_CENTRES_INTERETS, ingenieur.getCentresInterets());

        return database.insert(DBHelper.TABLE_INGENIEURS, null, values);
    }

    // Mettre à jour un ingénieur
    public int mettreAjourIngenieur(Ingenieur ingenieur) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOM, ingenieur.getNom());
        values.put(DBHelper.COLUMN_FORMATIONS_DIPLOMES, ingenieur.getFormationsEtDiplomes());
        values.put(DBHelper.COLUMN_EXPERIENCES_STAGES, ingenieur.getExperiencesEtStages());
        values.put(DBHelper.COLUMN_COMPETENCES_TECHNIQUES, ingenieur.getCompetencesTechniques());
        values.put(DBHelper.COLUMN_COMPETENCES_LINGUISTIQUES, ingenieur.getCompetencesLinguistiques());
        values.put(DBHelper.COLUMN_CENTRES_INTERETS, ingenieur.getCentresInterets());

        return database.update(DBHelper.TABLE_INGENIEURS, values,
                DBHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(ingenieur.getId())});
    }

    // Supprimer un ingénieur
    public void supprimerIngenieur(long ingenieurId) {
        database.delete(DBHelper.TABLE_INGENIEURS, DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(ingenieurId)});
    }
    // Récupérer tous les profils
    public List<Ingenieur> getAllProfils() {
        List<Ingenieur> ingenieurs = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_INGENIEURS, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Ingenieur ingenieur = cursorToIngenieur(cursor);
                ingenieurs.add(ingenieur);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return ingenieurs;
    }
    public Cursor getAllProfilsCursor() {
        return database.query(DBHelper.TABLE_INGENIEURS, null, null, null, null, null, null);
    }


    // Récupérer tous les ingénieurs
    public List<Ingenieur> getAllIngenieurs() {
        List<Ingenieur> ingenieurs = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_INGENIEURS, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Ingenieur ingenieur = cursorToIngenieur(cursor);
                ingenieurs.add(ingenieur);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return ingenieurs;
    }

    // Méthode utilitaire pour convertir un curseur en objet Ingenieur
    private Ingenieur cursorToIngenieur(Cursor cursor) {
        Ingenieur ingenieur = new Ingenieur();

        int idIndex = cursor.getColumnIndex(DBHelper.COLUMN_ID);
        if (idIndex != -1) {
            ingenieur.setId(cursor.getLong(idIndex));
        }

        int nomIndex = cursor.getColumnIndex(DBHelper.COLUMN_NOM);
        if (nomIndex != -1) {
            ingenieur.setNom(cursor.getString(nomIndex));
        }

        int formationsIndex = cursor.getColumnIndex(DBHelper.COLUMN_FORMATIONS_DIPLOMES);
        if (formationsIndex != -1) {
            ingenieur.setFormationsEtDiplomes(cursor.getString(formationsIndex));
        }

        int experiencesIndex = cursor.getColumnIndex(DBHelper.COLUMN_EXPERIENCES_STAGES);
        if (experiencesIndex != -1) {
            ingenieur.setExperiencesEtStages(cursor.getString(experiencesIndex));
        }

        int competencesTechniquesIndex = cursor.getColumnIndex(DBHelper.COLUMN_COMPETENCES_TECHNIQUES);
        if (competencesTechniquesIndex != -1) {
            ingenieur.setCompetencesTechniques(cursor.getString(competencesTechniquesIndex));
        }

        int competencesLinguistiquesIndex = cursor.getColumnIndex(DBHelper.COLUMN_COMPETENCES_LINGUISTIQUES);
        if (competencesLinguistiquesIndex != -1) {
            ingenieur.setCompetencesLinguistiques(cursor.getString(competencesLinguistiquesIndex));
        }

        int centresInteretsIndex = cursor.getColumnIndex(DBHelper.COLUMN_CENTRES_INTERETS);
        if (centresInteretsIndex != -1) {
            ingenieur.setCentresInterets(cursor.getString(centresInteretsIndex));
        }

        return ingenieur;
    }

}
