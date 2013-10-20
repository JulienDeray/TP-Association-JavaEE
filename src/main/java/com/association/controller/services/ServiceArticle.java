package com.association.controller.services;

import java.util.ArrayList;
import java.util.List;

import com.model.bean.Article;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.ArticlePersistence;

public class ServiceArticle {

	private ArticlePersistence serviceAr;
	
	public ServiceArticle() {
		serviceAr = PersistenceServiceProvider.getService(ArticlePersistence.class);
	}
	
	public Article load(int id){
		return serviceAr.load(id);
	}
	
	public List<Article> loadAll(){
		return serviceAr.loadAll();
	}
	
	public List<Article> valideCommande(List<Article> articles){
		
		ArrayList<Article> articlesRestant = new ArrayList<Article>();
		for (Article article : articles) {
			Article tmp = load(article.getArId());
			System.out.println(tmp);
			//deducation dans le stock
			if(tmp.getArStock()>0){
				tmp.setArStock(tmp.getArStock() - 1);
				save(tmp);
				
			}else{
				articlesRestant .add(tmp);
			}
		}
		return articlesRestant;
	}
	public void save(Article article){
		serviceAr.save(article);
	}
	
}
