/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import br.ufscar.dc.consulta.beans.Consulta;
import br.ufscar.dc.consulta.beans.Medico;
import br.ufscar.dc.consulta.dao.ConsultaDAO;
import br.ufscar.dc.consulta.dao.MedicoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marcos
 */
@Named
@ViewScoped
public class AddConsulta implements Serializable {

    @Inject
    ConsultaDAO consultaDAO;

    Consulta consulta;

    MensagemBootstrap mensagem;



    private boolean liberado = false;

    public boolean isLiberado() {
        return liberado;
    }

    public void setLiberado(boolean liberado) {
        this.liberado = liberado;
    }

    public AddConsulta() {
        consulta = new Consulta();
        mensagem = new MensagemBootstrap();

        mensagem.setMensagem(true, "Preencha os dados da Consulta", MensagemBootstrap.TipoMensagem.TIPO_INFO);

    }


    public void cadastrarConsulta() {
        try {
            consultaDAO.gravarConsulta(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public String adicionar() {
        if (isLiberado()) {
            System.out.println("testaa2");
            cadastrarConsulta();
            return "areaAdmin";
        }

        return "addConsulta";
    }

    public void setMedico(Consulta consulta) {
        this.consulta = consulta;
    }

}
