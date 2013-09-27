/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 27/09/13
 * Time: 08:49
 */
package com.model;

public class Adherent {

    int id;
    String login;
    String password;
    String nom;
    String prenom;
    String adresse;
    String codePostal;
    String ville;
    int pays_id;

    public Adherent(String login, String password, String nom, String prenom, String adresse, String codePostal, String ville, int pays_id) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays_id = pays_id;
    }

    public Adherent(int id, String login, String password, String nom, String prenom, String adresse, String codePostal, String ville, int pays_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays_id = pays_id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public int getPays_id() {
        return pays_id;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", pays_id=" + pays_id +
                '}';
    }
}
