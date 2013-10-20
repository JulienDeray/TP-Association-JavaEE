package com.association.controller.services.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.association.controller.services.ServiceLogin;
import com.model.bean.Adherent;

public class ServiceLoginTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLogin() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		Adherent  adh = new Adherent();
		adh.setAdLogin("log");
		adh.setAdPassword("pass");
		adherents.add(adh);
		//test avec user ok
		ServiceLogin serviceLogin = spy(new ServiceLogin());
		when(serviceLogin.find("log")).thenReturn(adherents);
		Adherent ad = serviceLogin.login("log","pass");
		assertNotNull(ad);
		assertSame(adh,ad);
		
		//test avec mauvais mdp
		Adherent ad2 = serviceLogin.login("log","pass2");
		assertNull(ad2);
		
		
		//test avec mauvais login
		when(serviceLogin.find("log2")).thenReturn(new ArrayList<Adherent>());
		Adherent ad3 = serviceLogin.login("log2","pass");
		assertNull(ad3);
		
	}

	@Test
	public void testIsExist() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		Adherent  adh = new Adherent();
		adh.setAdLogin("log");
		adh.setAdPassword("pass");
		adherents.add(adh);
		//test avec user ok
		ServiceLogin serviceLogin = spy(new ServiceLogin());
		when(serviceLogin.find("log")).thenReturn(adherents);
		assertTrue(serviceLogin.isExist("log"));
		
		//test avec mauvais login
		when(serviceLogin.find("log2")).thenReturn(new ArrayList<Adherent>());
		assertTrue(serviceLogin.isExist("log2"));
		
	}

}
