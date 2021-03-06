/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import br.ufscar.dc.consulta.beans.Consulta;
import br.ufscar.dc.consulta.beans.Paciente;
import br.ufscar.dc.consulta.dao.ConsultaDAO;
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
public class ListaConsultaPaciente implements Serializable {


    @Inject
    ConsultaDAO consultaDAO;

    @Inject
    SessaoAtiva sessaoAtiva;
    
    Paciente paciente;

    private List<Consulta> consulta;

    private List<Consulta> consultaFiltradas;

    private List<String> Crmconsulta;

    public List<String> getCrmconsulta() {
        return Crmconsulta;
    }

    public void setMedicoconsulta(List<String> medicoconsulta) {
        this.Crmconsulta = medicoconsulta;
    }

    @PostConstruct
    public void init() {
        try {
            paciente = sessaoAtiva.getPacienteAtivo();
            consulta = consultaDAO.listarTodasConsultasPaciente(paciente.getCpf());
            Crmconsulta = new ArrayList<>();
            consulta.forEach((mc) -> {
               Crmconsulta.add(mc.getCrm());
            });
        } catch (SQLException ex) {
            Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Consulta> getConsulta() {
        return consulta;
    }

    public void setConsulta(List<Consulta> consulta) {
        this.consulta = consulta;
    }

    public List<Consulta> getConsultaFiltrados() {
        return consultaFiltradas;
    }

    public void setConsultaFiltrados(List<Consulta> consultaFiltrados) {
        this.consultaFiltradas = consultaFiltrados;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}