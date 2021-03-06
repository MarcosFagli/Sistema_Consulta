/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import br.ufscar.dc.consulta.beans.Medico;
import br.ufscar.dc.consulta.dao.MedicoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marcos
 */
@Named
@ViewScoped
public class ListaMedicos implements Serializable {


    @Inject
    MedicoDAO medicoDAO;


    private List<Medico> medico;

    private List<Medico> medicoFiltrados;
    
    private List<String> especialidade;
    
    private List<String> crms;
    


    @PostConstruct
    public void init() {
        try {
            medico = medicoDAO.listarTodosMedicos();
            crms = new ArrayList<>();
            medico.forEach((m) -> {
                crms.add(m.getCrm());
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Medico> getMedicos() {
        return medico;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medico = medicos;
    }

    public List<Medico> getMedicoFiltrados() {
        return medicoFiltrados;
    }

    public void setMedicoFiltrados(List<Medico> medicoFiltrados) {
        this.medicoFiltrados = medicoFiltrados;
    }

    public List<String> getEspecialidade() {
        return especialidade;
    }
    
     public List<String> getCrms() {
        return crms;
    }

    
}