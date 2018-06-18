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
public class LoginPaciente implements Serializable {
    
    @Inject
    PacienteDAO pacienteDAO;
    @Inject
    SessaoAtiva sessaoAtiva;
    
    private String cpf;
    Paciente pacienteEncontrado;
    
    UIInput senha;
    
    MensagemBootstrap mensagem;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UIInput getSenha() {
        return senha;
    }

    public void setSenha(UIInput senha) {
        this.senha = senha;
    }

    public Paciente getPacienteEncontrado() {
        return pacienteEncontrado;
    }

    public void setPacienteEncontrado(Paciente pacienteEncontrado) {
        this.pacienteEncontrado = pacienteEncontrado;
    }

    public MensagemBootstrap getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemBootstrap mensagem) {
        this.mensagem = mensagem;
    }
    
    
    public LoginPaciente() {
        pacienteEncontrado = new Paciente();
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite seu CPF para acesso", MensagemBootstrap.TipoMensagem.TIPO_INFO);
    }
    
    public void procurarMedico() {
        simularDemora();
        try{
            pacienteEncontrado = pacienteDAO.buscarPacienteCPF(this.getCpf());
            if(pacienteEncontrado == null){
                mensagem.setMensagem(true, "CPF não cadastrado!", MensagemBootstrap.TipoMensagem.TIPO_INFO);
            } else {
                mensagem.setMensagem(true, "CPF encontrado, digite a senha!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
            }
        }catch (SQLException ex){
            Logger.getLogger(LoginMedico.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }

    public String acesso() {
        String senhatemp = (String) this.getSenha().getValue();
        if (pacienteEncontrado != null){
            if (senhatemp.equals(pacienteEncontrado.getSenha())) {
                sessaoAtiva.setPacienteLogado(pacienteEncontrado);
                return "areaPaciente";
            } else {
                mensagem.setMensagem(true, "Senha incorreta! Informe novamente!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                return "loginPaciente";
            }     
        }
        return "loginPaciente";   
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
