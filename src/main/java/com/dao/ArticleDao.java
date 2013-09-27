/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 27/09/13
 * Time: 16:08
 */
package com.dao;

import com.model.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends ConnectionManager  {

    private String tableName = "ARTICLE";

    public void saveArticle(Article article) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " (ar_code, ar_prix, ar_stock) " +
                    "values ('" +
                    article.getCode() + "'," +
                    (int) article.getPrix() * 10 + "," +
                    article.getStock() + ")");
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public List<Article> getAllArticles() throws SQLException {
        List<Article> adherentList = new ArrayList<Article>();

        stmt = conn.createStatement();

        ResultSet results = stmt.executeQuery(
                "SELECT ar_id, ar_code, ar_prix, ar_stock" +
                        " FROM " + tableName
        );

        while(results.next()) {
            Article article = extractArticle(results);
            adherentList.add( article );
        }

        results.close();
        stmt.close();

        return adherentList;
    }

    // TODO : getArticleById
    // TODO : getArticleByCode

    private Article extractArticle(ResultSet results) {
        Article article;

        try {
            article = new Article(
                    results.getInt(1),
                    results.getString(2),
                    results.getDouble(3) / 10,
                    results.getInt(4)
            );
        }
        catch (SQLException e) {
            e.printStackTrace();
            article = new Article(0, "error", 0., 0);
        }
        return article;
    }

}
