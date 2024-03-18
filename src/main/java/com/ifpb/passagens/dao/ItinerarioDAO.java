package com.ifpb.passagens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpb.passagens.entities.Itinerario;
import com.ifpb.passagens.conexao.FabricaDeConexao;

public class ItinerarioDAO {
    private Connection connection;

    public ItinerarioDAO() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void inserirItinerario(Itinerario itinerario) {
        String sql = "INSERT INTO itinerarios (origem, destino) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, itinerario.getOrigem());
            stmt.setString(2, itinerario.getDestino());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Itinerario buscarPorId(Long id) {
        String sql = "SELECT * FROM itinerarios WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Itinerario itinerario = new Itinerario();
            while (rs.next()) {
                itinerario.setId(rs.getLong("id"));
                itinerario.setOrigem(rs.getString("origem"));
                itinerario.setDestino(rs.getString("destino"));
            }
            rs.close();
            stmt.close();
            return itinerario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Itinerario> listarTodos() {
        String sql = "SELECT * FROM itinerarios";
        try {
            List<Itinerario> itinerarios = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Itinerario itinerario = new Itinerario();
                itinerario.setId(rs.getLong("id"));
                itinerario.setOrigem(rs.getString("origem"));
                itinerario.setDestino(rs.getString("destino"));
                itinerarios.add(itinerario);
            }
            rs.close();
            stmt.close();
            return itinerarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarItinerario(Itinerario itinerario) {
        String sql = "UPDATE itinerarios SET origem=?, destino=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, itinerario.getOrigem());
            stmt.setString(2, itinerario.getDestino());
            stmt.setLong(3, itinerario.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirItinerario(Long id) {
        String sql = "DELETE FROM itinerarios WHERE id=?";
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
