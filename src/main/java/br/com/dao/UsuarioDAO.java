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
                usu.setId_usuario(rs.getInt("Id_usuario"));
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

    public Usuario buscarIdUsuario(String cpf) {
        String sql = "SELECT id_usuario, nome_usuario FROM USUARIO WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usu = new Usuario();
                    usu.setId_usuario(rs.getInt("id_usuario"));
                    usu.setNome_usuario(rs.getString("nome_usuario"));
                    return usu;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Usuario por ID.", e);
        }

        return null;
    }

    public void inserirUsuario(Usuario Usuario) {
        String sql = "INSERT INTO ITEM id_usuario, nome_usuario, email_usuario, senha_usuario VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt (1, Usuario.getId_usuario    ());
            stmt.setString (2, Usuario.getNome_usuario  ());
            stmt.setString (3, Usuario.getEmail_usuario  ());
            stmt.setObject (4, Usuario.getSenha_usuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Usuario.", e);
        }
    }

    public void atualizarUsuario(Usuario Usuario) {
        String sql = "UPDATE ITEM SET nome_usuario = ?, email_usuario = ?, senha_usuario = ?, WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Usuario.getNome_usuario());
            stmt.setString(2, Usuario.getEmail_usuario());
            stmt.setString(3, Usuario.getSenha_usuario());
            stmt.setInt(4, Usuario.getId_usuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuario.", e);
        }
    }

    public void excluirUsuario(int id) {
        String sql = "DELETE FROM ITEM WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir USUARIO.", e);
        }
    }


}