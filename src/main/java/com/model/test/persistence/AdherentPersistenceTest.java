/*
 * Created on 7 oct. 2013 ( Time 08:51:44 )
 * Generated by Telosys Tools Generator ( version 2.0.6 )
 */
package com.model.test.persistence;


import com.model.bean.Adherent ;
import com.model.mock.AdherentMock;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.AdherentPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Adherent persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class AdherentPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test Adherent persistence : delete + load ..." );
		
		AdherentPersistence service = PersistenceServiceProvider.getService(AdherentPersistence.class);
		
		AdherentMock mock = new AdherentMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(AdherentPersistence service, AdherentMock mock, int adId ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		Adherent entity = service.load( adId );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( adId ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Pays
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( adId );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
