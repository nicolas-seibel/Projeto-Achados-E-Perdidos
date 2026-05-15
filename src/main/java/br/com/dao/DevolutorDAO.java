package br.com.dao;

import br.com.model.Devolutor;
import br.com.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevolutorDAO {
    public List<Devolutor> listar() {
        List<Devolutor> devolutores = new ArrayList<>();
        String sql = "SELECT cpf_devolutor, nome_devolutor, telefone_devolutor, email_devolutor FROM DEVOLUTOR ORDER BY cpf_devolutor;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Devolutor devo = new Devolutor();
                devo.setCpf_devolutor(rs.getString("cpf_devolutor"));
                devo.setNome_devolutor(rs.getString("nome_devolutor"));
                devo.setTelefone_devolutor(rs.getString("telefone_devolutor"));
                devo.setEmail_devolutor(rs.getString("email_devolutor"));
                devolutores.add(devo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar devolutores.", e);
        }

        return devolutores;
    }

    public Devolutor buscarPorCpf(String cpf) {
        String sql = "SELECT cpf_devolutor FROM DEVOLUTOR WHERE cpf_devolutor = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Devolutor devo = new Devolutor();
                    devo.setCpf_devolutor(rs.getString("cpf_devolutor"));
                    devo.setNome_devolutor(rs.getString("nome_devolutor"));
                    return devo;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar devolutor por CPF.", e);
        }

        return null;
    }

    public void inserir(Devolutor Devolutor) {
        String sql = "INSERT INTO ITEM cpf_devolutor, nome_devolutor, telefone_devolutor, email_devolutor VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString (1, Devolutor.getCpf_devolutor    ());
            stmt.setString (2, Devolutor.getNome_devolutor  ());
            stmt.setString (3, Devolutor.getTelefone_devolutor  ());
            stmt.setObject (4, Devolutor.getEmail_devolutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir devolutor.", e);
        }
    }

    public void atualizar(Devolutor Devolutor) {
        String sql = "UPDATE ITEM SET nome_devolutor = ?, telefone_devolutor = ?, email_devolutor = ?, WHERE cpf_devolutor = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Devolutor.getNome_devolutor());
            stmt.setString(2, Devolutor.getTelefone_devolutor());
            stmt.setString(3, Devolutor.getEmail_devolutor());
            stmt.setString(4, Devolutor.getCpf_devolutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar devolutor.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM ITEM WHERE cpf_devolutor = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir DEVOLUTOR.", e);
        }
    }


}