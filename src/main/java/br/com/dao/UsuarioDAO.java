package br.com.dao;

import br.com.model.Usuario;
import br.com.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nome_usuario, email_usuario, senha_usuario FROM USUARIO ORDER BY id_usuario;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId_usuario(rs.getInt("id_usuario"));
                usu.setNome_usuario(rs.getString("nome_usuario"));
                usu.setEmail_usuario(rs.getString("email_usuario"));
                usu.setSenha_usuario(rs.getString("senha_usuario"));
                usuarios.add(usu);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuarios.", e);
        }
        return usuarios;
    }

    // CORRIGIDO: Alterado parâmetro de String cpf para int id
    public Usuario buscarIdUsuario(int id) {
        String sql = "SELECT id_usuario, nome_usuario, email_usuario, senha_usuario FROM USUARIO WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usu = new Usuario();
                    usu.setId_usuario(rs.getInt("id_usuario"));
                    usu.setNome_usuario(rs.getString("nome_usuario"));
                    usu.setEmail_usuario(rs.getString("email_usuario"));
                    usu.setSenha_usuario(rs.getString("senha_usuario"));
                    return usu;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Usuario por ID.", e);
        }
        return null;
    }

    public void inserirUsuario(Usuario usuario) {
        // CORRIGIDO: Adicionado parênteses e alterado de ITEM para USUARIO
        String sql = "INSERT INTO USUARIO (id_usuario, nome_usuario, email_usuario, senha_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, usuario.getId_usuario());
            stmt.setString(2, usuario.getNome_usuario());
            stmt.setString(3, usuario.getEmail_usuario());
            stmt.setString(4, usuario.getSenha_usuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Usuario.", e);
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        // CORRIGIDO: Alterado para a tabela USUARIO e removida a vírgula antes do WHERE
        String sql = "UPDATE USUARIO SET nome_usuario = ?, email_usuario = ?, senha_usuario = ? WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome_usuario());
            stmt.setString(2, usuario.getEmail_usuario());
            stmt.setString(3, usuario.getSenha_usuario());
            stmt.setInt(4, usuario.getId_usuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuario.", e);
        }
    }

    public void excluirUsuario(int id) {
        // CORRIGIDO: Alterado de ITEM para USUARIO
        String sql = "DELETE FROM USUARIO WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir USUARIO.", e);
        }
    }
}