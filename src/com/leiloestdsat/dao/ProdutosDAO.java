package com.leiloestdsat.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import com.leiloestdsat.dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        
        if (conn != null) {
            String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, produto.getNome());
                pstmt.setDouble(2, produto.getValor());

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    return 1;
                } else {
                    return 0; 
                }
            } catch (SQLException e) {
                System.err.println("Erro ao cadastrar produto: " + e.getMessage());
                return 0;
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        } else {
            System.err.println("Falha ao conectar ao banco de dados.");
            return 0;
        }

    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

