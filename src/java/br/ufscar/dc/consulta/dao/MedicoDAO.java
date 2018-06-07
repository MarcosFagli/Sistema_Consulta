/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;

/**
 *
 * @author marcos
 */
@RequestScoped
public class MedicoDAO {
    
   private final static String CRIAR_MEDICO_SQL = "insert into Medico"
           + " (crm, nome, senha, especialidade)"
           + " values (?,?,?,?)";

   private final static String LISTAR_MEDICO_SQL = "select"
           + " m.nome, m.especialidade"
           + " from Medico m";

   private final static String LISTAR_ESPECIALIDADES_SQL = "select"
           + " distinct(especialidade) from Medico"
           + " order by upper(especialidade)";
   
   private final static String BUSCAR_MEDICO_PELO_CRM_SQL = "select"
           + " id, crm, nome, senha, especialidade"
           + " from medico"
           + " where crm=?";
   
   
   @Resource(name = "jdbc/ConsultaDBLocal")
   DataSource dataSource;

   public Medico gravarMedico(Medico m) throws SQLException {
       try (Connection con = dataSource.getConnection();
               PreparedStatement ps = con.prepareStatement(CRIAR_MEDICO_SQL, Statement.RETURN_GENERATED_KEYS);) {
           ps.setString(1, m.getCrm());
           ps.setString(2, m.getNome());
           ps.setString(3, m.getSenha());
           ps.setString(4, m.getEspecialidade());
           ps.execute();
           
           try (ResultSet rs = ps.getGeneratedKeys()) {
               rs.next();
               m.setId(rs.getInt(1));
           }
       }
       return m;
   }
   
   
    public List<Medico> listarTodosMedicos() throws SQLException {
        List<Medico> ret = new ArrayList<>();
        try(Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_MEDICO_SQL)) {
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Medico m = new Medico();
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    ret.add(m);
                }
            }
        }
        return ret;
    }    
    
    
    public List<String> listarTodasEspecialidades() throws SQLException {
        List<String> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_ESPECIALIDADES_SQL)) {
            try(ResultSet rs = ps.executeQuery()){
                String s = rs.getString("especialidade");
                ret.add(s);
            }
        }
        return ret;
    }

    
    public Medico buscarMedicoCRM(String crm) throws SQLException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_MEDICO_PELO_CRM_SQL)) {
            ps.setString(1, crm);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("id"));
                    m.setNome(rs.getString("nome"));
                    m.setCrm(rs.getString("crm"));
                    m.setSenha(rs.getString("senha"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    return m;
                } else {
                    return null;
                }
            }
        }
    }
    
}