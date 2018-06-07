/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIInput;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author marcos
 */
@Named
@ViewScoped
public class LoginAdmin implements Serializable {
        
    private String email;
    
    UIInput senha;
    
    MensagemBootstrap mensagem;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UIInput getSenha() {
        return senha;
    }

    public void setSenha(UIInput senha) {
        this.senha = senha;
    }

    public MensagemBootstrap getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemBootstrap mensagem) {
        this.mensagem = mensagem;
    }
    
    
    public LoginAdmin() {
        recomecar();
    }

    private void recomecar() {
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite o E-mail de Adminstrador para acesso", MensagemBootstrap.TipoMensagem.TIPO_INFO);
    }
    
    public void logarAdmin() {
        simularDemora();
        if (email.equals("admin@ufscar.br")){
            mensagem.setMensagem(true, "Admin encontrado, digite a senha!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
        } else {
            mensagem.setMensagem(true, "Admin errado!", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        }
    }

    
    public void conferirSenha() {
        simularDemora();
        String senhatemp = (String) this.getSenha().getValue();
        if (senhatemp.equals("1234")) {
            mensagem.setMensagem(true, "Senha correta!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
        } else {
            mensagem.setMensagem(true, "Senha incorreta! Informe novamente!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
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
