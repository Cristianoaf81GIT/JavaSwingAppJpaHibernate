/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.profcristianoaf81.appswingjpa.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author crist
 */
@Entity
public class Carro implements Serializable {
  /* @SequenceGenerator(name="carroGen"
           ,sequenceName = "AUTOMOVEL_SEQ",allocationSize = 30) */
    
   @TableGenerator(
           name="CARRO_GERADOR",
           table = "CAR_GENERATOR",
           pkColumnValue = "CARRO",
           initialValue = 1
   )
    
   @Id
   @GeneratedValue(strategy = GenerationType.TABLE
           ,generator = "CARRO_GERADOR")
   private Long id;
   
   private String marca;
   
   private String modelo;
   
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date data_fabricacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(Date data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }
   
    public String getData_formatada(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"
                , Locale.getDefault());
        String data_formatada = sdf.format(data_fabricacao);
        return data_formatada;
    }
}
