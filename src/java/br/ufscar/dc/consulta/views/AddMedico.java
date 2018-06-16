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
public class AddMedico implements Serializable {
    
    @Inject
    MedicoDAO medicoDAO;
 
    Medico medico;
    
    MensagemBootstrap mensagem;

    UIInput senhaInput;
    
    private boolean liberado = false;

    public boolean isLiberado() {
        return liberado;
    }

    public void setLiberado(boolean liberado) {
        this.liberado = liberado;
    }
    
    public UIInput getSenhaInput() {
        return senhaInput;
    }


    public void setSenhaInput(UIInput senhaInput) {
        this.senhaInput = senhaInput;
    }
    
    public AddMedico(){
    medico = new Medico();
    
    mensagem = new MensagemBootstrap();

    mensagem.setMensagem(true, "Preencha os dados dos Médicos", MensagemBootstrap.TipoMensagem.TIPO_INFO);

    
    }
    
    @PostConstruct
    public void cadastrarMedico() {
        try {
            medicoDAO.gravarMedico(medico);
        } catch (SQLException ex) {
            Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Medico getMedico() {
        return medico;
    }

    public void validarConfirmacaoDeSenha(FacesContext context,
            UIComponent toValidate,
            String value) {
        String senha1 = (String) senhaInput.getValue();
        if (!value.equals(senha1)) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Senha não confere!");
            context.addMessage(toValidate.getClientId(context), message);
            setLiberado(false);
            
        } else {
            medico.setSenha(senha1);
            setLiberado(true);
        }
    }
    
    public String adicionar(){
        if(isLiberado()){
            cadastrarMedico();
            return "areaAdmin";
        }
        return "addMedico";
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
}
