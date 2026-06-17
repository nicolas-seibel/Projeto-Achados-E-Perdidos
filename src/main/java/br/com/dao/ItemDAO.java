package br.com.dao;

import br.com.model.Item;
import br.com.util.ConnectionFactory;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public List<Item> listarItem() {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM ORDER BY id_item;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item it = new Item();
                it.setId_item(rs.getInt("id_item"));
                it.setNome_item(rs.getString("nome_item"));
                it.setDescricao(rs.getString("descricao"));
                it.setData_achado(rs.getObject("data_achado", LocalDate.class));
                it.setLocal_achado(rs.getString("local_achado"));
                it.setStatus_item(rs.getString("status_item"));
                itens.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens.", e);
        }
        return itens;
    }

    public Item buscarPorIdItem(int id) {
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM WHERE id_item = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Item it = new Item();
                    it.setId_item(rs.getInt("id_item"));
                    it.setNome_item(rs.getString("nome_item"));
                    it.setDescricao(rs.getString("descricao"));
                    it.setData_achado(rs.getObject("data_achado", LocalDate.class));
                    it.setLocal_achado(rs.getString("local_achado"));
                    it.setStatus_item(rs.getString("status_item"));
                    return it;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item por ID.", e);
        }
        return null;
    }

    public void inserirItem(Item item) {
        String sql = "INSERT INTO ITEM (nome_item, descricao, data_achado, local_achado, status_item) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, item.getNome_item());
            stmt.setString(2, item.getDescricao());
            stmt.setObject(3, item.getData_achado());  
            stmt.setString(4, item.getLocal_achado()); 
            stmt.setString(5, item.getStatus_item());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item no banco: " + e.getMessage(), e);
        }
    }

    public void atualizarItem(Item item) {
        String sql = "UPDATE ITEM SET nome_item = ?, descricao = ?, status_item = ? WHERE id_item = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome_item());
            stmt.setString(2, item.getDescricao());
            stmt.setString(3, item.getStatus_item());
            stmt.setInt(4, item.getId_item());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item.", e);
        }
    }

    public void excluirItem(int id) {
        String sql = "DELETE FROM ITEM WHERE id_item = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ITEM.", e);
        }
    }

    // CORRIGIDO: Alterado para filtrar com WHERE para trazer apenas os 'disponivel'
    public List<Item> listarAItem() {
        List<Item> itemA = new ArrayList<>();
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM WHERE status_item = 'disponivel' ORDER BY id_item;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item it = new Item();
                it.setId_item(rs.getInt("id_item"));
                it.setNome_item(rs.getString("nome_item"));
                it.setDescricao(rs.getString("descricao"));
                it.setData_achado(rs.getObject("data_achado", LocalDate.class));
                it.setLocal_achado(rs.getString("local_achado"));
                it.setStatus_item(rs.getString("status_item"));
                itemA.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens disponíveis.", e);
        }
        return itemA;
    }

    // CORRIGIDO: Alterado para filtrar com WHERE para trazer apenas os 'devolvido'
    public List<Item> listarDItem() {
        List<Item> itemD = new ArrayList<>();
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM WHERE status_item = 'devolvido' ORDER BY id_item;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item it = new Item();
                it.setId_item(rs.getInt("id_item"));
                it.setNome_item(rs.getString("nome_item"));
                it.setDescricao(rs.getString("descricao"));
                it.setData_achado(rs.getObject("data_achado", LocalDate.class));
                it.setLocal_achado(rs.getString("local_achado"));
                it.setStatus_item(rs.getString("status_item"));
                itemD.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens devolvidos.", e);
        }
        return itemD;
    }
    
    public List<Item> listarDIA() {
        List<Item> itemDIA = new ArrayList<>();
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM ORDER BY data_achado DESC;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item it = new Item();
                it.setId_item(rs.getInt("id_item"));
                it.setNome_item(rs.getString("nome_item"));
                it.setDescricao(rs.getString("descricao"));
                it.setData_achado(rs.getObject("data_achado", LocalDate.class));
                it.setLocal_achado(rs.getString("local_achado"));
                it.setStatus_item(rs.getString("status_item"));
                itemDIA.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens por data.", e);
        }
        return itemDIA;
    }

    public void marcarComoDevolvido(int id) {
        String sql = "UPDATE ITEM SET status_item = 'devolvido' WHERE id_item = ? AND status_item = 'disponivel'";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas == 0) {
                System.out.println("Aviso: O item não foi alterado. Motivo: ID inexistente ou já devolvido.");
            } else {
                System.out.println("Item marcado como Devolvido com sucesso!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Marcar Como Devolvido.", e);
        }
    }

    public void marcarComoAchado(int id) {
        String sql = "UPDATE ITEM SET status_item = 'disponivel' WHERE id_item = ? AND status_item = 'devolvido'";
    
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
          
            if (linhasAfetadas == 0) {
                System.out.println("Aviso: O item não foi alterado. Motivo: ID inexistente ou já está disponível.");
            } else {
                System.out.println("Item marcado como Achado com sucesso!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao Marcar Como Achado.", e);
        }
    }
}