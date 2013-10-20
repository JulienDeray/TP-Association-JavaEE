package com.association.controller.services.test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.association.controller.services.ServiceArticle;
import com.model.bean.Article;

public class ServiceArticleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValideCommande() {
		List<Article> articlesTemp = new ArrayList<Article>();
		Article article  = new Article();
		article.setArStock(1);
		articlesTemp.add(article);
		//test avec stock ok
		ServiceArticle serviceArticle = spy(new ServiceArticle());
		when(serviceArticle.load(anyInt())).thenReturn(article);
		Mockito.doNothing().when(serviceArticle).save((Article)any());
		List<Article> articles = serviceArticle.valideCommande(articlesTemp);
		assertSame(0,articles.size());
	
		
		//test avec stock ==0
		List<Article> articlesTemp2 = new ArrayList<Article>();
		Article article2  = new Article();
		article.setArStock(0);
		articlesTemp2.add(article2);
		when(serviceArticle.load(anyInt())).thenReturn(article2);
		List<Article> articles2 = serviceArticle.valideCommande(articlesTemp2);
		assertSame(1,articles2.size());
		assertSame(article2, articles2.get(0));
	
	}

}
