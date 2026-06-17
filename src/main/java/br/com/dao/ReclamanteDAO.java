package br.com.dao;

import br.com.model.Reclamante;
import br.com.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamanteDAO {

    public List<Reclamante> listarReclamante() {
        List<Reclamante> reclamantes = new ArrayList<>();
        String sql = "SELECT cpf_reclamante, nome_reclamante , telefone_reclamante, email_reclamante FROM RECLAMANTE ORDER BY cpf_reclamante;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reclamante reclamante = new Reclamante();
                reclamante.setCpf_reclamante(rs.getString("cpf_reclamante"));
                reclamante.setNome_reclamante(rs.getString("nome_reclamante"));
                reclamante.setTelefone_reclamante(rs.getString("telefone_reclamante"));
                reclamante.setEmail_reclamante(rs.getString("email_reclamante"));
                reclamantes.add(reclamante);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar reclamantes.", e);
        }
        return reclamantes;
    }

    public Reclamante buscarPorIdReclamante(String cpf) {
        String sql = "SELECT cpf_reclamante, nome_reclamante, telefone_reclamante, email_reclamante FROM RECLAMANTE WHERE cpf_reclamante = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reclamante reclamante = new Reclamante();
                    reclamante.setCpf_reclamante(rs.getString("cpf_reclamante"));
                    reclamante.setNome_reclamante(rs.getString("nome_reclamante"));
                    reclamante.setTelefone_reclamante(rs.getString("telefone_reclamante"));
                    reclamante.setEmail_reclamante(rs.getString("email_reclamante"));
                    return reclamante;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Reclamante por CPF.", e);
        }
        return null;
    }

    public void inserirReclamante(Reclamante reclamante) {
        String sql = "INSERT INTO RECLAMANTE (cpf_reclamante, nome_reclamante, telefone_reclamante, email_reclamante) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reclamante.getCpf_reclamante());
            stmt.setString(2, reclamante.getNome_reclamante());
            stmt.setString(3, reclamante.getTelefone_reclamante());
            stmt.setString(4, reclamante.getEmail_reclamante());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Reclamante.", e);
        }
    }
    
    public void atualizarReclamante(Reclamante reclamante) {
        // CORRIGIDO: Removida a vírgula antes do WHERE
        String sql = "UPDATE RECLAMANTE SET nome_reclamante = ?, telefone_reclamante = ?, email_reclamante = ? WHERE cpf_reclamante = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reclamante.getNome_reclamante());
            stmt.setString(2, reclamante.getTelefone_reclamante());
            stmt.setString(3, reclamante.getEmail_reclamante());
            stmt.setString(4, reclamante.getCpf_reclamante());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Reclamante.", e);
        }
    }

    public void excluirReclamante(String cpf) {
        String sql = "DELETE FROM RECLAMANTE WHERE cpf_reclamante = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir RECLAMANTE.", e);
        }
    }
}