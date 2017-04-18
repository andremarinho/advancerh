/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.advancerh.DAO;

import br.com.advancerh.vaga.Vaga;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre
 */
public class SqliteTest {
    
    private static Connection con;
    private static boolean hasData = false;
    
    public void getConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqliteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con = DriverManager.getConnection("jdbc:sqlite:advancebd.db");
            System.out.println("Conexão bem sucedida");
        } catch (SQLException ex) {
            Logger.getLogger(SqliteTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
            
        
    }
    
    public void gravaLista(List<Vaga> vagas){
        if(con == null){
            getConnection();
        }
        
        String query="insert into vaga(cargo, email, descricao, data_inserido, data_fim) values (?,?,?,?,?)";
        PreparedStatement pst = null;
        
        try {
            pst = con.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(SqliteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Vaga vaga:vagas){
            try {
                pst.setString(1, vaga.getCargo());
                pst.setString(2, vaga.getEmail());
                pst.setString(3, vaga.getDescricao());
                pst.setString(4, vaga.getDataCadastro().toString());
                pst.setString(5, vaga.getDataFim().toString());
                pst.execute();
            } catch (SQLException ex) {
                Logger.getLogger(SqliteTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
        
    }

    
    
}
