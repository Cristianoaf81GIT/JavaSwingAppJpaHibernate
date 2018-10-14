/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.profcristianoaf81.appswingjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

/**
 *
 * @author cristianoaf81
 */
public class JpaUtil {
    @SuppressWarnings("FieldMayBeFinal")
    private static final  EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("CONCESSIONARIA");
    
    /**
     *
     * @return entity manager object
     */
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public static void closeEntityManagerFactory(){
        emf.close();
    }
}
