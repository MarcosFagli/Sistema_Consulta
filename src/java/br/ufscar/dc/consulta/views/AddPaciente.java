/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import br.ufscar.dc.consulta.beans.Paciente;
import br.ufscar.dc.consulta.dao.PacienteDAO;
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
 * @author Paulo Betareli
 */
@Named
@ViewScoped
public class AddPaciente implements Serializable {
    
    @Inject
    PacienteDAO pacienteDAO;
 
    Paciente paciente;
    
    MensagemBootstrap mensagem;

    UIInput senhaInput;
    
    public UIInput getSenhaInput() {
        return senhaInput;
    }

    public void setSenhaInput(UIInput senhaInput) {
        this.senhaInput = senhaInput;
    }
    
    public AddPaciente(){
        paciente = new Paciente();
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Preencha os dados do Paciente", MensagemBootstrap.TipoMensagem.TIPO_INFO);
    }
    
    @PostConstruct
    public void cadastrarPaciente() {
        System.out.println("nome1");
        System.out.println(paciente.getNome());
        if(paciente.getNome() != null){
            
            try {
                System.out.println("senha");
                pacienteDAO.gravarPaciente(paciente);
            } catch (SQLException ex) {
                Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void validarConfirmacaoDeSenha(FacesContext context, UIComponent toValidate, String value) {
        if(paciente.getNome() != null){
            System.out.println("Csenha");
            String senha1 = (String) senhaInput.getValue();
            System.out.println("Psenha");
            if (!value.equals(senha1)) {
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage("Senha não confere!");
                context.addMessage(toValidate.getClientId(context), message);
            }
            else{
                paciente.setSenha(senha1);
            }
        }
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
}