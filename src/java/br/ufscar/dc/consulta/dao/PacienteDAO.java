/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Paciente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 *
 * @author marcos
 */
@Named
@RequestScoped
public class PacienteDAO {

   private final static String CRIAR_PACIENTE_SQL = "insert into Paciente"
           + " (nome, senha, cpf, telefone, sexo, dataDeNascimento)"
           + " values (?,?,?,?,?,?)";

   private final static String BUSCAR_PACIENTE_PELO_CPF_SQL = "select"
           + " id, nome, senha, cpf, telefone, sexo, dataDeNascimento"
           + " from paciente"
           + " where cpf=?";
  
   @Resource(name = "jdbc/ConsultaDBLocal")
   DataSource dataSource;

   public Paciente gravarPaciente(Paciente p) throws SQLException {
       try (Connection con = dataSource.getConnection();
               PreparedStatement ps = con.prepareStatement(CRIAR_PACIENTE_SQL, Statement.RETURN_GENERATED_KEYS);) {
           ps.setString(1, p.getNome());
           ps.setString(2, p.getSenha());
           ps.setString(3, p.getCpf());
           ps.setString(4, p.getTelefone());
           ps.setString(5, p.getSexo());
           ps.setDate(6, new java.sql.Date(p.getDataDeNascimento().getTime()));
           ps.execute();

           try (ResultSet rs = ps.getGeneratedKeys()) {
               rs.next();
               p.setId(rs.getInt(1));
           }
       }
       return p;
   }

    public Paciente buscarPacienteCPF(String cpf) throws SQLException{
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_PACIENTE_PELO_CPF_SQL)) {
            ps.setString(1, cpf);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente p = new Paciente();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setSenha(rs.getString("senha"));
                    p.setCpf(rs.getString("cpf"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setSexo(rs.getString("sexo"));
                    p.setDataDeNascimento(new Date(rs.getDate("dataDeNascimento").getTime()));
                    return p;
                } else {
                    return null;
                }
            }
        }
    }
}
