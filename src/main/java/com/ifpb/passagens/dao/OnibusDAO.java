package com.ifpb.passagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.passagens.entities.Onibus;
import com.ifpb.passagens.conexao.FabricaDeConexao;

public class OnibusDAO {
    private Connection connection;

    public OnibusDAO() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void inserirOnibus(Onibus onibus) {
        String sql = "INSERT INTO onibus (placa, capacidade) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, onibus.getPlaca());
            stmt.setInt(2, onibus.getCapacidade());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Onibus buscarPorId(Long id) {
        String sql = "SELECT * FROM onibus WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Onibus onibus = new Onibus();
            while (rs.next()) {
                onibus.setId(rs.getLong("id"));
                onibus.setPlaca(rs.getString("placa"));
                onibus.setCapacidade(rs.getInt("capacidade"));
            }
            rs.close();
            stmt.close();
            return onibus;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Onibus> listarTodos() {
        String sql = "SELECT * FROM onibus";
        try {
            List<Onibus> onibusList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Onibus onibus = new Onibus();
                onibus.setId(rs.getLong("id"));
                onibus.setPlaca(rs.getString("placa"));
                onibus.setCapacidade(rs.getInt("capacidade"));
                onibusList.add(onibus);
            }
            rs.close();
            stmt.close();
            return onibusList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarOnibus(Onibus onibus) {
        String sql = "UPDATE onibus SET placa=?, capacidade=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, onibus.getPlaca());
            stmt.setInt(2, onibus.getCapacidade());
            stmt.setLong(3, onibus.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirOnibus(Long id) {
        String sql = "DELETE FROM onibus WHERE id=?";
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
