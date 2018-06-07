/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.beans;

import java.util.Date;

/**
 *
 * @author marcos
 */
public class Consulta {
    
    private String cpf, crm;
    private Date dataDeConsulta;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Date getDataDeConsulta() {
        return dataDeConsulta;
    }

    public void setDataDeConsulta(Date dataDeConsulta) {
        this.dataDeConsulta = dataDeConsulta;
    }
}

