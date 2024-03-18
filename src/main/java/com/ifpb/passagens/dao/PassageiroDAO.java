package com.ifpb.passagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.passagens.entities.Passageiro;
import com.ifpb.passagens.conexao.FabricaDeConexao;

public class PassageiroDAO {
    private final Connection connection;

    public PassageiroDAO() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void inserirPassageiro(Passageiro passageiro) {
        String sql = "INSERT INTO passageiros (nome, cpf) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getCpf());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Passageiro buscarPorId(Long id) {
        String sql = "SELECT * FROM passageiros WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Passageiro passageiro = new Passageiro();
            while (rs.next()) {
                passageiro.setId(rs.getLong("id"));
                passageiro.setNome(rs.getString("nome"));
                passageiro.setCpf(rs.getString("cpf"));
            }
            rs.close();
            stmt.close();
            return passageiro;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Passageiro> listarTodos() {
        String sql = "SELECT * FROM passageiros";
        try {
            List<Passageiro> passageiros = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setId(rs.getLong("id"));
                passageiro.setNome(rs.getString("nome"));
                passageiro.setCpf(rs.getString("cpf"));
                passageiros.add(passageiro);
            }
            rs.close();
            stmt.close();
            return passageiros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarPassageiro(Passageiro passageiro) {
        String sql = "UPDATE passageiros SET nome=?, cpf=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, passageiro.getNome());
            stmt.setString(2, passageiro.getCpf());
            stmt.setLong(3, passageiro.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirPassageiro(Long id) {
        String sql = "DELETE FROM passageiros WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

