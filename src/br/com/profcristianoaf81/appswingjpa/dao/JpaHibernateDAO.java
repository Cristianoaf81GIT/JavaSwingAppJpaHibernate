/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.profcristianoaf81.appswingjpa.dao;

import br.com.profcristianoaf81.appswingjpa.models.Carro;
import java.util.List;

/**
 *
 * @author cristianoaf81
 */
public interface JpaHibernateDAO {
    
    /**
     *
     * @param c object type Carro
     */
    public void salvar(Carro c);
    
    
    /**
     *
     * @return ArrayList Carro for Table
     */
    public List<Carro> listar();
    
    
    /**
     *
     * @param c object type carro
     */
    public void deletar(Carro c);
    
    /**
     *
     * @param c object type carro
     */
    public void atualizar(Carro c);
}
