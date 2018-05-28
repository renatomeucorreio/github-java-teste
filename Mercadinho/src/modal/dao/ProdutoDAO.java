/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produtos;

/**
 *
 * @author Renato
 */
public class ProdutoDAO {
    public void create(Produtos p){
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
       
        try {
            stmt = conexao.prepareStatement("INSERT INTO pedidos (descricao,qtd,preco)VALUE(?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQtd());
            stmt.setDouble(3, p.getPreco());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(conexao);
        }
    }
    
    public List<Produtos> read(){
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produtos> produto = new ArrayList<>();
       
        try {
            stmt = conexao.prepareStatement("SELECT * FROM pedidos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
             
                Produtos  produtos = new Produtos();
                produtos.setId(rs.getInt("id"));
                produtos.setDescricao(rs.getString("descricao"));
                produtos.setQtd(rs.getInt("qtd"));
                produtos.setPreco(rs.getDouble("preco"));
                produto.add(produtos);
              
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(conexao);
        }
        return produto;
    }
   
}
