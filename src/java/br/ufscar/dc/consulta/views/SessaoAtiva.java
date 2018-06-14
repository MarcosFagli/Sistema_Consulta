/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.views;

import br.ufscar.dc.consulta.beans.Medico;
import br.ufscar.dc.consulta.beans.Paciente;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author marcos
 */
@Named
@SessionScoped
public class SessaoAtiva implements Serializable{
    
    Medico medicoAtivo;
    Paciente pacienteAtivo;
    private boolean admin;

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin() {
        this.admin = true;
        System.out.println("admin");
        System.out.println(this.admin);
    }
    
    public void clearAdmin() {
        this.admin = false;
    }
       
    public void setMedicoLogado(Medico medico) {
        medicoAtivo = medico;
    }
    
    public void setPacienteLogado(Paciente paciente) {
        pacienteAtivo = paciente;
        System.out.println(pacienteAtivo.getNome());
    }
    
    public String verificar(){
        System.out.println("AEW GAROTO!");
        if(pacienteAtivo == null){
            return "index";
        } else {
            return null;
        }
    }
    
    public String deslogar(){
        medicoAtivo = null;
        pacienteAtivo = null;
        System.out.println("AKIIIII");
        return "index";
    }
}
