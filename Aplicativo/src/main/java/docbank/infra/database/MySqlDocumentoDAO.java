package docbank.infra.database;

import docbank.core.domain.Documento;
import docbank.core.ports.DocumentoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDocumentoDAO implements DocumentoDAO {

    @Override
    public boolean salvar(String titulo, String topico, String identificador) {
        String sql = "INSERT INTO documentos (titulo, topico, identificador, status) VALUES (?, ?, ?, 'revisao')";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.setString(2, topico);
            stmt.setString(3, identificador);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao salvar o documento.", e);
        }
    }

    @Override
    public void adicionarFavorito(int idUsuario, int idDocumento) {
        String sql = "INSERT INTO favoritos (usuario_id, documento_id) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idDocumento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new IllegalStateException("Este documento já está nos seus favoritos.");
            } else {
                throw new RuntimeException("Erro de comunicação com o banco ao favoritar: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public List<Documento> listarFavoritos(int idUsuario) {
        String sql = "SELECT d.* FROM documentos d JOIN favoritos f ON d.id = f.documento_id WHERE f.usuario_id = ?";
        List<Documento> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Documento(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("topico"),
                            rs.getString("identificador")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao listar favoritos.", e);
        }
        return lista;
    }

    @Override
    public boolean removerFavorito(int idUsuario, int idDocumento) {
        String sql = "DELETE FROM favoritos WHERE usuario_id = ? AND documento_id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idDocumento);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao remover favorito.", e);
        }
    }

    @Override
    public List<Documento> listarPorStatus(String status) {
        String sql = "SELECT * FROM documentos WHERE status = ?";
        List<Documento> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Documento(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("topico"),
                            rs.getString("identificador")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao listar documentos por status.", e);
        }
        return lista;
    }

    @Override
    public boolean aprovar(int id) {
        String sql = "UPDATE documentos SET status = 'aprovado' WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao aprovar documento.", e);
        }
    }

    @Override
    public void atualizar(int id, String novoTitulo, String novoTopico) {
        String sql = "UPDATE documentos SET titulo = ?, topico = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoTitulo);
            stmt.setString(2, novoTopico);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao atualizar documento.", e);
        }
    }

    @Override
    public boolean excluir(int id) {
        String sql = "DELETE FROM documentos WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro de banco de dados ao excluir documento.", e);
        }
    }
}
