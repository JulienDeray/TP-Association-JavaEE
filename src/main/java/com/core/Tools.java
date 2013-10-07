/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 27/09/13
 * Time: 16:37
 */
package com.core;

import com.model.bean.Adherent;
import com.model.bean.Article;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.AdherentPersistence;
import com.model.persistence.services.ArticlePersistence;

public abstract class Tools {

    public static String md5(String md5) {
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

    public static void init() {

	}
    
    private static void initAdherent() {
        AdherentPersistence adService = PersistenceServiceProvider.getService(AdherentPersistence.class);

        Adherent adherent = new Adherent();
        adherent.setAdLogin("toto");
        adherent.setAdPassword(md5("toto"));

        adherent.setAdNom("Golé");
        adherent.setAdPrenom("Jerry");

        adService.insert( adherent );
    }

    private static void initArticle() {
        ArticlePersistence service = PersistenceServiceProvider.getService(ArticlePersistence.class);

        Article article = new Article();
        article.setArNom("Le premier article");
        article.setArCode("Code1");
        article.setArPrix(1200);
        article.setArStock(999);
        service.insert(article);
        System.out.println("insert");
    }
}
