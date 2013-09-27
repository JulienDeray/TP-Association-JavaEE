/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 27/09/13
 * Time: 08:36
 */
package com.dao;

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
                    md5(adherent.getPassword() + 666) + "','" +
                    adherent.getNom() + "','" +
                    adherent.getPrenom() + "','" +
                    adherent.getAdresse() + "','" +
                    adherent.getCodePostal() + "','" +
                    adherent.getVille() + "'," +
                    adherent.getPays_id() + ")");
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
            "select * from " + tableName
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
            "select * from " + tableName + " where ad_id = " + id
        );

        results.next();
        adherent = extractAdherent( results );

        return adherent;
    }

    public List<Adherent> getAdherentByLogin( String login, String password ) throws SQLException {
        List<Adherent> adherentList = new ArrayList<Adherent>();

        stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery(
            "select * from " + tableName + " where ad_login = " + login + " and ad_password = " + md5( password )
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
                resultSet.getInt(9)
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
            adherent = new Adherent(0, "error", "error", "error", "error", "error", "error", "error", -1);
        }
        return adherent;
    }

    public String md5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
