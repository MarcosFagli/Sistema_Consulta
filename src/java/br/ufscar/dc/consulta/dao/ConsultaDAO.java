/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;

/**
 *
 * @author marcos
 */
@RequestScoped
public class ConsultaDAO {
    
    private final static String CRIAR_CONSULTA_SQL = "insert into Consulta"
           + " (cpf, crm, data)"
           + " values (?,?,?)";

    private final static String LISTAR_CONSULTA_SQL = "select"
           + " c.cpf, c.crm, c.data"
           + " from Consulta c";
    
    private final static String LISTAR_CONSULTA_MEDICO_SQL = "select"
            + " c.cpf, c.crm, c.datadaconsulta"
            + " from Consulta c";
    
    private final static String LISTAR_CONSULTA_PACIENTE_SQL = "select"
            + " c.cpf, c.crm, c.datadaconsulta"
            + " from Consulta c";
    
    
    @Resource(name = "jdbc/ConsultaDBLocal")
    DataSource dataSource;
    
    public Consulta gravarConsulta(Consulta c) throws SQLException {
       try (Connection con = dataSource.getConnection();
               PreparedStatement ps = con.prepareStatement(CRIAR_CONSULTA_SQL, Statement.RETURN_GENERATED_KEYS);) {
           ps.setString(1, c.getCpf());
           ps.setString(2, c.getCrm());
           ps.setDate(3, new java.sql.Date(c.getDataDeConsulta().getTime()));
           ps.execute();
           
           try (ResultSet rs = ps.getGeneratedKeys()) {
               rs.next();
               c.setId(rs.getInt(1));
           }
       }
       return c;
   }
    
    public List<Consulta> listarTodasConsultas() throws SQLException{
        List<Consulta> ret = new ArrayList<>();
        try(Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_CONSULTA_SQL)) {
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Consulta c = new Consulta();
                    c.setCpf(rs.getString("cpf"));
                    c.setCrm(rs.getString("crm"));
                    c.setDataDeConsulta(new Date(rs.getDate("dataDaConsulta").getTime()));
                    ret.add(c);
                }
            }
        }
        return ret;
    }
    
    public List<Consulta> listarTodasConsultasMedico(String crm) throws SQLException{
        List<Consulta> ret = new ArrayList<>();
        try(Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_CONSULTA_MEDICO_SQL)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Consulta c = new Consulta();
                    c.setCpf(rs.getString("cpf"));
                    c.setCrm(rs.getString("crm"));
                    c.setDataDeConsulta(new Date(rs.getDate("dataDeConsulta").getTime()));
                    if(c.getCrm().equals(crm)){
                        ret.add(c);
                    }
                }
            }
        }
        return ret;
    }
    
    public List<Consulta> listarTodasConsultasPaciente(String cpf) throws SQLException{
        System.out.println(cpf);
        List<Consulta> ret = new ArrayList<>();
        try(Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_CONSULTA_PACIENTE_SQL)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Consulta c = new Consulta();
                    c.setCpf(rs.getString("cpf"));
                    c.setCrm(rs.getString("crm"));
                    c.setDataDeConsulta(new Date(rs.getDate("dataDaConsulta").getTime()));
                    if(c.getCpf().equals(cpf)){
                        ret.add(c);
                    }
                }
            }
        }
        return ret;
    }
}