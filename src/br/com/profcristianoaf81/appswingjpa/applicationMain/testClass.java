/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.profcristianoaf81.appswingjpa.applicationMain;

import br.com.profcristianoaf81.appswingjpa.dao.JpaDaoHibernateDerby;
import br.com.profcristianoaf81.appswingjpa.models.Carro;
import br.com.profcristianoaf81.appswingjpa.util.JpaUtil;
import java.util.List;

/**
 * 
 * @author cristianoaf81
 */
public class testClass {
    public static void main(String[] args){
        JpaDaoHibernateDerby  test = new JpaDaoHibernateDerby();
        List<Carro> lista =  test.listar();
        for(Carro c : lista){
            System.out.println("Codigo:  " + c.getId());
            System.out.println("Marca:   " + c.getMarca());
            System.out.println("Modelo:  " + c.getModelo());
            System.out.println("Data:    " + c.getData_formatada());
        }
        
        JpaUtil.closeEntityManagerFactory();
        
    }
}
