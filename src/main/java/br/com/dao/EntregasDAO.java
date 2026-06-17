package br.com.dao;

import br.com.model.Entregas;
import br.com.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntregasDAO {

    public List<Entregas> listarEntregas() {
        List<Entregas> entregass = new ArrayList<>();
        String sql = "SELECT id_entrega, id_item, cpf_reclamante, cpf_devolutor, data_entrega FROM ENTREGAS ORDER BY id_entrega;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entregas entregas = new Entregas();
                entregas.setId_entrega(rs.getInt("id_entrega"));
                entregas.setId_item(rs.getInt("id_item"));
                entregas.setCpf_reclamante(rs.getString("cpf_reclamante"));
                entregas.setCpf_devolutor(rs.getString("cpf_devolutor"));
                entregas.setData_entrega(rs.getObject("data_entrega", LocalDate.class));
                entregass.add(entregas);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar entrega.", e);
        }
        return entregass;
    }

    // CORRIGIDO: Tabela alterada para ENTREGAS e parâmetro alterado de String cpf para int idEntrega
    public Entregas buscarPorIdEntregas(int idEntrega) {
        String sql = "SELECT id_entrega, id_item, cpf_reclamante, cpf_devolutor, data_entrega FROM ENTREGAS WHERE id_entrega = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrega);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Entregas entregas = new Entregas();
                    entregas.setId_entrega(rs.getInt("id_entrega"));                  
                    entregas.setId_item(rs.getInt("id_item"));                  
                    entregas.setCpf_reclamante(rs.getString("cpf_reclamante"));
                    entregas.setCpf_devolutor(rs.getString("cpf_devolutor"));
                    entregas.setData_entrega(rs.getObject("data_entrega", LocalDate.class));
                    return entregas;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Entrega por ID.", e);
        }
        return null;
    }

    public void inserirEntrega(Entregas entregas) {
        String sql = "INSERT INTO ENTREGAS (id_entrega, id_item, cpf_reclamante, cpf_devolutor, data_entrega) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt   (1, entregas.getId_entrega());
            stmt.setInt   (2, entregas.getId_item());
            stmt.setString(3, entregas.getCpf_reclamante());
            stmt.setString(4, entregas.getCpf_devolutor());
            stmt.setObject(5, entregas.getData_entrega());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Entrega.", e);
        }
    }
    
    public void atualizarEntrega(Entregas entregas) {
        // CORRIGIDO: Removida a vírgula antes do WHERE e adicionado o 5º parâmetro para o WHERE
        String sql = "UPDATE ENTREGAS SET id_item = ?, cpf_reclamante = ?, cpf_devolutor = ? WHERE id_entrega = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entregas.getId_item());
            stmt.setString(2, entregas.getCpf_reclamante());
            stmt.setString(3, entregas.getCpf_devolutor());
            stmt.setInt(4, entregas.getId_entrega());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Entrega.", e);
        }
    }

    public void excluirEntrega(int idEntrega) {
        // CORRIGIDO: Apagava de RECLAMANTE por engano, corrigido para ENTREGAS
        String sql = "DELETE FROM ENTREGAS WHERE id_entrega = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrega);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir Entrega.", e);
        }
    }
}