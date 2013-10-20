/**
 * Created with IntelliJ IDEA.
 * Autor: julienderay
 * Company : SERLI
 * Date: 07/10/13
 * Time: 10:15
 */
package com.association.controller.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.bean.Adherent;
import com.model.persistence.PersistenceServiceProvider;
import com.model.persistence.services.AdherentPersistence;

public class ServiceLogin {
	
    public ServiceLogin() {
    }

    public Adherent login(String login, String password) {
    	
    	if(login==null || password==null){
    		return null;
    	}
        List<Adherent> adherant = find(login);

        if(adherant.size() == 1 && adherant.get(0).getAdPassword().equals( password )) {
        	return adherant.get(0);
        }
        else{
            return null;
        }
    }

	public List<Adherent> find(String login) {
		AdherentPersistence serviceAdh = PersistenceServiceProvider.getService(AdherentPersistence.class);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("adLogin = ", login);
        
        List<Adherent> adherant = serviceAdh.search(param);
        
		return adherant;
	}
    

    public boolean isExist(String login) {
    	if(login==null){
    		return false;
    	}
        List<Adherent> adherant = find(login);

        if(adherant.size() == 1 ) {
            return true;
        }
        else{
            return false;
        }
    }

}
