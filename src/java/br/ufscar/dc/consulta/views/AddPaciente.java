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
    UIInput senhacInput;
    
    private boolean liberado = false;

    public UIInput getSenhacInput() {
        return senhacInput;
    }

    public void setSenhacInput(UIInput senhacInput) {
        this.senhacInput = senhacInput;
    }
    
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

    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public AddPaciente(){
        System.out.println("Aki paciente 1: ");
        paciente = new Paciente();
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Preencha os dados do Paciente", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        System.out.println("Aki paciente 2: ");
    }
    
    public void cadastrarPaciente() {
        System.out.println("Aki cadastrar : nome");
        try {
            pacienteDAO.gravarPaciente(paciente);
        } catch (SQLException ex) {
            Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validarConfirmacaoDeSenha(FacesContext context, UIComponent toValidate, String value) {
        System.out.println("Aki 1 : nome");
        System.out.println(paciente.getNome());
        if(paciente.getNome() != null){
            System.out.println("Aki 2 : convertendo senha");
            String senha1 = (String) senhaInput.getValue();
            String senhac = (String) senhacInput.getValue();
            System.out.println("Aki 3 : senha");
            System.out.println(senha1);
            System.out.println("Aki 4 : Aew mlq");
            if (!senhac.equals(senha1)) {
                System.out.println("Aki 5 : Hey, deu ruim");
                FacesMessage message = new FacesMessage("Senha não confere!");
                context.addMessage(toValidate.getClientId(context), message);
                setLiberado(false);
            }
            else {
                System.out.println("Aki 5 : Hey, deu bom");
                paciente.setSenha(senha1);
                setLiberado(true);  
            }
        }
        System.out.println("Aki 6 : mas nem paciente tem");
    }
    
    public String adicionar(){
        System.out.println("Aki add 1 : liberado");
        System.out.println(liberado);
        System.out.println("Aki add 2 : vai para o if");
        if(liberado){
            System.out.println("Aki add 3: liberou");
            cadastrarPaciente();
            return "areaAdmin";
        } else {
            return "addPaciente";
        }
    }
}