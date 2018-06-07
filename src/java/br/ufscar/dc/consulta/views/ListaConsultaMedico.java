/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import br.ufscar.dc.consulta.beans.Consulta;
import br.ufscar.dc.consulta.beans.Medico;
import br.ufscar.dc.consulta.dao.ConsultaDAO;
import java.io.Serializable;
import java.sql.SQLException;
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
public class ListaConsultaMedico implements Serializable {


    @Inject
    ConsultaDAO consultaDAO;

    Medico medico;

    private List<Consulta> consulta;

    private List<Consulta> consultaFiltradas;


    @PostConstruct
    public void init() {
        medico = new Medico();
        try {
            System.out.println("PASOOOUUU MED");
            medico.setCrm("123456");
            System.out.println("PASOOOUUU MED2");
            consulta = consultaDAO.listarTodasConsultasMedico(medico.getCrm());
            System.out.println("PASOOOUUU MED3");
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    

    
}