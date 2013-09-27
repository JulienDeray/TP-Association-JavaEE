/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 27/09/13
 * Time: 08:36
 */
package com.dao;

import com.core.tools;
import com.model.Adherent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdherentDao extends ConnectionManager {

    private String tableName = "ADHERENT";

    public void saveAdherent(Adherent adherent) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " (ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, ad_pa_id) " +
                    "values ('" +
                    adherent.getLogin() + "','" +
                    tools.md5(adherent.getPassword() + 666) + "','" +
                    adherent.getNom() + "','" +
                    adherent.getPrenom() + "','" +
                    adherent.getAdresse() + "','" +
                    adherent.getCodePostal() + "','" +
                    adherent.getVille() + "'," +
                    adherent.getPays() + ")");
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public List<Adherent> getAllAdherents() throws SQLException {
        List<Adherent> adherentList = new ArrayList<Adherent>();

        stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery(
            "SELECT ad_id, ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, pa_nom" +
            " FROM " + tableName +
            " LEFT JOIN PAYS " +
            " ON ADHERENT.ad_pa_id = PAYS.pa_id"
        );

        while(results.next()) {
            Adherent adherent = extractAdherent( results );
            adherentList.add( adherent );
        }

        results.close();
        stmt.close();

        return adherentList;
    }

    public Adherent getAdherentById( int id ) throws SQLException {
        Adherent adherent;

        stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery(
            "SELECT ad_id, ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, pa_nom" +
            " FROM " + tableName +
            " LEFT JOIN PAYS " +
            " ON ADHERENT.ad_pa_id = PAYS.pa_id" +
            " WHERE ADHERENT.ad_id = " + id
        );

        results.next();
        adherent = extractAdherent( results );

        return adherent;
    }

    public List<Adherent> getAdherentByLogin( String login, String password ) throws SQLException {
        List<Adherent> adherentList = new ArrayList<Adherent>();

        stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery(
            "SELECT ad_id, ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, pa_nom" +
            " FROM " + tableName +
            " LEFT JOIN PAYS " +
            " ON ADHERENT.ad_pa_id = PAYS.pa_id" +
            " WHERE ADHERENT.ad_login = '" + login + "' and ADHERENT.ad_password = '" + tools.md5(password + 666) + "'"
        );

        while(results.next()) {
            Adherent adherent = extractAdherent( results );
            adherentList.add( adherent );
        }

        results.close();
        stmt.close();

        return adherentList;
    }

    private Adherent extractAdherent( ResultSet resultSet ) {
        Adherent adherent;

        try {
            adherent = new Adherent(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9)
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
            adherent = new Adherent(0, "error", "error", "error", "error", "error", "error", "error", "NO");
        }
        return adherent;
    }
}
