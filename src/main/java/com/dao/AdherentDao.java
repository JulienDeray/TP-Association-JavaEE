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
            stmt.execute("insert into " + tableName + " values (" +
                    adherent.getLogin() + ",'" +
                    adherent.getPassword() + "','" +
                    adherent.getNom() + "','" +
                    adherent.getPrenom() + "','" +
                    adherent.getAdresse() + "','" +
                    adherent.getCodePostal() + "','" +
                    adherent.getVille() + "','" +
                    adherent.getPays_id() + "')");
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
            results.next();
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
}
