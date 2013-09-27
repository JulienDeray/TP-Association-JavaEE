/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 27/09/13
 * Time: 16:06
 */
package com.model;

public class Article {

    private int id;
    private String code;
    private double prix;
    private int stock;

    public Article(int id, String code, double prix, int stock) {
        this.id = id;
        this.code = code;
        this.prix = prix;
        this.stock = stock;
    }

    public Article(String code, double prix, int stock) {
        this.code = code;
        this.prix = prix;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public double getPrix() {
        return prix;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                '}';
    }
}
