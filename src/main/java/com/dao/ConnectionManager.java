/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 26/09/13
 * Time: 22:13
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionManager {

    protected static String dbURL = "jdbc:derby:Association";
    protected static Connection conn = null;
    protected static Statement stmt = null;

    public ConnectionManager() {
        createConnection();
    }

    private void createConnection() {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            conn = DriverManager.getConnection(dbURL + ";create=true");
            System.out.println( ( "Connected to " + dbURL ) );
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init() throws SQLException {
        stmt = conn.createStatement();
        stmt.execute("create table PAYS (\n" +
                "\tpa_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                "\tpa_code varchar(255) NOT NULL,\n" +
                "\tpa_nom varchar(255) NOT NULL,\n" +
                "\tCONSTRAINT pays_pk PRIMARY KEY (pa_id)\n" +
                "\t)");

        stmt.execute("create table ADHERENT (\n" +
                "\tad_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), \n" +
                "\tad_login varchar(255) NOT NULL, \n" +
                "\tad_password varchar(255) NOT NULL, \n" +
                "\tad_nom varchar(255) NOT NULL, \n" +
                "\tad_prenom varchar(255) NOT NULL, \n" +
                "\tad_adresse varchar(255), \n" +
                "\tad_codepostal varchar(255),\n" +
                "\tad_ville varchar(255),\n" +
                "\tad_pa_id int,\n" +
                "\tCONSTRAINT adherent_pk PRIMARY KEY (ad_id),\n" +
                "\tCONSTRAINT pays_fk FOREIGN KEY (ad_pa_id) REFERENCES pays (pa_id)\n" +
                "\t)");

        stmt.execute("create table ARTICLE (\n" +
                "\tar_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                "\tar_code varchar(255) NOT NULL,\n" +
                "\tar_prix int NOT NULL,\n" +
                "\tar_stock int NOT NULL,\n" +
                "\tCONSTRAINT article_pk PRIMARY KEY (ar_id)\n" +
                "\t)");

        stmt.execute("insert into PAYS (pa_code, pa_nom)\n" +
                "values ('FR', 'France')");

        stmt.execute("insert into ADHERENT (ad_login, ad_password, ad_nom, ad_prenom, ad_adresse, ad_codepostal, ad_ville, ad_pa_id)\n" +
                "values ('juju', 'juju', 'DERAY', 'Julien', 'Rue du bambou qui fuit', '17000', 'LA ROCHELLE', 1)");

        stmt.execute("insert into ARTICLE (ar_code, ar_prix, ar_stock)\n" +
                "values ('1A', 180, 1)");

        stmt.close();
    }

    public void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        }
        catch (SQLException sqlExcept) {

        }
    }
}
