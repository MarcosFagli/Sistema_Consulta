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
import javax.faces.component.UIInput;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marcos
 */
@Named
@ViewScoped
public class LoginMedico implements Serializable {
    
    @Inject
    MedicoDAO medicoDAO;
    
    @Inject
    SessaoAtiva sessaoAtiva;
    
    private String crm;
    Medico medicoEncontrado;
    
    UIInput senha;
    
    MensagemBootstrap mensagem;

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public UIInput getSenha() {
        return senha;
    }

    public void setSenha(UIInput senha) {
        this.senha = senha;
    }
    
    public Medico getMedicoEncontrado() {
        return medicoEncontrado;
    }

    public void setMedicoEncontrado(Medico medicoEncontrado) {
        this.medicoEncontrado = medicoEncontrado;
    }

    public MensagemBootstrap getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemBootstrap mensagem) {
        this.mensagem = mensagem;
    }
    
    
    public LoginMedico() {
        recomecar();
    }

    private void recomecar() {
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite seu CRM para acesso", MensagemBootstrap.TipoMensagem.TIPO_INFO);
    }
    
    public void procurarMedico() {
        simularDemora();
        try{
            medicoEncontrado = medicoDAO.buscarMedicoCRM(this.getCrm());
            if(medicoEncontrado == null){
                mensagem.setMensagem(true, "CRM não cadastrado!", MensagemBootstrap.TipoMensagem.TIPO_INFO);
            } else {
                mensagem.setMensagem(true, "CRM encontrado, digite a senha!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
            }
        }catch (SQLException ex){
            Logger.getLogger(LoginMedico.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }

    
    public String acesso() {
        if(medicoEncontrado != null){
            simularDemora();
            String senhatemp = (String) this.getSenha().getValue();
            if (senhatemp.equals(medicoEncontrado.getSenha())) {
                sessaoAtiva.setMedicoLogado(medicoEncontrado);
                return "listaConsultaMedico";
            } else {
                mensagem.setMensagem(true, "Senha incorreta! Informe novamente!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                return "loginMedico";
            }
        } else {
            return "loginMedico";
        }
    }
    
    private void simularDemora() {
        // Para testar chamadas AJAX
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
