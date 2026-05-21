package br.com.dao;

import br.com.model.Item;
import br.com.util.ConnectionFactory;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public List<Item> listar() {
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
                it.setStatus_item(rs.getBoolean("status_item"));
                itens.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens.", e);
        }
        return itens;
    }

    public Item buscarPorId(int id) {
        // CORRIGIDO: Agora busca todas as colunas necessárias
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
                    it.setStatus_item(rs.getBoolean("status_item"));
                    return it;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item por ID.", e);
        }
        return null;
    }

    public void inserir(Item item) {
        // CORRIGIDO: Adicionado as 6 interrogações corretas e removido o id do insert caso seja auto_increment
        String sql = "INSERT INTO ITEM (nome_item, descricao, data_achado, local_achado, status_item) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString (1, item.getNome_item  ());
            stmt.setString (2, item.getDescricao  ());
            stmt.setObject (3, item.getData_achado());
            stmt.setString (4, item.getLocal_achado());
            stmt.setBoolean(5, item.getStatus_item());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item.", e);
        }
    }

    public void atualizar(Item item) {
        // CORRIGIDO: Removida a vírgula antes do WHERE e adicionado o status_item para permitir a devolução
        String sql = "UPDATE ITEM SET nome_item = ?, descricao = ?, status_item = ? WHERE id_item = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getNome_item());
            stmt.setString(2, item.getDescricao());
            stmt.setBoolean(3, item.getStatus_item());
            stmt.setInt(4, item.getId_item());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM ITEM WHERE id_item = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ITEM.", e);
        }
    }

    public List<Item> listarA() {
        List<Item> itemA = new ArrayList<>();
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM ORDER BY status = 'DISPONIVEL' ;";

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
                it.setStatus_item(rs.getBoolean("status_item"));
                itemA.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens.", e);
        }
        return itemA;
    }

    public List<Item> listarD() {
        List<Item> itemD = new ArrayList<>();
        String sql = "SELECT id_item, nome_item, descricao, data_achado, local_achado, status_item FROM ITEM ORDER BY status = 'DEVOLVIDO' ;";

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
                it.setStatus_item(rs.getBoolean("status_item"));
                itemD.add(it);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens.", e);
        }
        return itemD;
    }
    
    
    public void cadastrar(Item item) {
        // TODO Auto-generated method stub
        
    }

	public List<Item> listar(String busca, String status) {
		// TODO Auto-generated method stub
		return null;
	}
}