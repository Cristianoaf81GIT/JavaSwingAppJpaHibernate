/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.profcristianoaf81.appswingjpa.dao;

import br.com.profcristianoaf81.appswingjpa.models.Carro;
import javax.persistence.EntityManager;
import br.com.profcristianoaf81.appswingjpa.util.JpaUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

/**
 *
 * @author cristianoaf81
 */
public class JpaDaoHibernateDerby implements JpaHibernateDAO{
    private List<Carro> carros;
    
    @Override
    public void salvar(Carro c) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        
        if(c.getMarca() != null || !c.getMarca().equals("")){
            em.persist(c);
            em.getTransaction().commit();
            em.close();
        }else{
            em.close();
            System.out.println(c.getId());
        }
        
    }

    @Override
    public List<Carro> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        carros = new ArrayList<>();
        Query find_carros;
        find_carros = em.createQuery("select c from Carro c",Carro.class);
        carros = find_carros.getResultList();
        em.getTransaction().commit();
        em.close();
        return (ArrayList<Carro>) carros;
    }

    @Override
    public void deletar(Carro c) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.setFlushMode(FlushModeType.COMMIT);
        c = em.merge(c);
        em.remove(c);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void atualizar(Carro c) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.setFlushMode(FlushModeType.COMMIT);
        Carro carro = em.find(Carro.class, c.getId());
        carro.setId(c.getId());
        carro.setMarca(c.getMarca());
        carro.setModelo(c.getModelo());
        carro.setData_fabricacao(c.getData_fabricacao());
        //carro = em.merge(c);
        //em.persist(carro);
        em.getTransaction().commit();
        em.close();
    }

   
}
