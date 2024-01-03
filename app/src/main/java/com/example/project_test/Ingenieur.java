package com.example.project_test;

public class Ingenieur {

    private long id;
    private String nom;
    private String formationsEtDiplomes;
    private String experiencesEtStages;
    private String competencesTechniques;
    private String competencesLinguistiques;
    private String centresInterets;

    public Ingenieur() {
    }

    public Ingenieur(String nom, String formationsEtDiplomes, String experiencesEtStages,
                     String competencesTechniques, String competencesLinguistiques, String centresInterets) {
        this.nom = nom;
        this.formationsEtDiplomes = formationsEtDiplomes;
        this.experiencesEtStages = experiencesEtStages;
        this.competencesTechniques = competencesTechniques;
        this.competencesLinguistiques = competencesLinguistiques;
        this.centresInterets = centresInterets;
    }

    // Ajoutez les getters et setters nécessaires

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFormationsEtDiplomes() {
        return formationsEtDiplomes;
    }

    public void setFormationsEtDiplomes(String formationsEtDiplomes) {
        this.formationsEtDiplomes = formationsEtDiplomes;
    }

    public String getExperiencesEtStages() {
        return experiencesEtStages;
    }

    public void setExperiencesEtStages(String experiencesEtStages) {
        this.experiencesEtStages = experiencesEtStages;
    }

    public String getCompetencesTechniques() {
        return competencesTechniques;
    }

    public void setCompetencesTechniques(String competencesTechniques) {
        this.competencesTechniques = competencesTechniques;
    }

    public String getCompetencesLinguistiques() {
        return competencesLinguistiques;
    }

    public void setCompetencesLinguistiques(String competencesLinguistiques) {
        this.competencesLinguistiques = competencesLinguistiques;
    }

    public String getCentresInterets() {
        return centresInterets;
    }

    public void setCentresInterets(String centresInterets) {
        this.centresInterets = centresInterets;
    }
}
