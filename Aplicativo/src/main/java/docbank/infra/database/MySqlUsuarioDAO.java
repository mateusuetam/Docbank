package docbank.infra.database;

import docbank.core.domain.Usuario;
import docbank.core.ports.UsuarioDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUsuarioDAO implements UsuarioDAO {

    @Override
    public boolean cadastrar(String nome, String email, String senhaHash, String cargo) {
        String sql = "INSERT INTO usuarios (nome, email, senha, cargo) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senhaHash);
            stmt.setString(4, cargo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao cadastrar usuário.", e);
        }
    }

    @Override
    public boolean existeEmail(String email) {
        String sql = "SELECT 1 FROM usuarios WHERE email = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao verificar email.", e);
        }
    }

    @Override
    public Usuario autenticar(String email, String senhaHash) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senhaHash);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("cargo")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao autenticar.", e);
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT id, nome, email, cargo FROM usuarios";
        List<Usuario> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cargo")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao listar usuários.", e);
        }
        return lista;
    }

    @Override
    public boolean alterarCargo(int idUsuario, String novoCargo) {
        String sql = "UPDATE usuarios SET cargo = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoCargo);
            stmt.setInt(2, idUsuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao alterar cargo.", e);
        }
    }

    @Override
    public boolean excluir(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao excluir usuário.", e);
        }
    }
}
