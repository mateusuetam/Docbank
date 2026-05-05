package docbank.app.sql;

import docbank.app.func.Documento;
import docbank.app.func.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para as operações CRUD e de autenticação
 *
 * @author Mateus
 * @version 1.0
 * @since Primeira versão
 */
public class DocbankDAO {

  private String hashSenha(char[] senha) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] senhaBytes = new String(senha).getBytes(java.nio.charset.StandardCharsets.UTF_8);
      byte[] hashBytes = md.digest(senhaBytes);
      StringBuilder sb = new StringBuilder();

      for (byte b : hashBytes) {
        sb.append(String.format("%02x", b));
      }

      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar processar criptografia da senha: " + e.getMessage(), e);
    }
  }

  public boolean cadastrarUsuario(String nome, String email, char[] senha, String cargo) {
    String sql = "INSERT INTO usuarios (nome, email, senha, cargo) VALUES (?, ?, ?, ?)";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, nome);
      stmt.setString(2, email);
      stmt.setString(3, hashSenha(senha));
      stmt.setString(4, cargo);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar realizar o cadastro do usuário: " + e.getMessage(), e);
    }
  }

  public boolean existeEmail(String email) {
    String sql = "SELECT 1 FROM usuarios WHERE email = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, email);
      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar verificar o email fornecido: " + e.getMessage(), e);
    }
  }

  public Usuario autenticarUsuario(String email, char[] senha) {
    String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, email);
      stmt.setString(2, hashSenha(senha));
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          String cargo = rs.getString("cargo");
          if ("Suspenso".equalsIgnoreCase(cargo)) {
            return null;
          }
          return new Usuario(
                  rs.getInt("id"),
                  rs.getString("nome"),
                  rs.getString("email"),
                  cargo, null
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar autenticar o login: " + e.getMessage(), e);
    }
    return null;
  }

  public List<Usuario> listarTodosUsuarios() {
    String sql = "SELECT id, nome, email, cargo FROM usuarios";
    List<Usuario> lista = new ArrayList<>();
    try (Connection conn = Conexao.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        lista.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("cargo"), null));
      }
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar buscar informações dos usuários: " + e.getMessage(), e);
    }
    return lista;
  }

  public boolean alterarCargo(int idUsuario, String novoCargo) {
    String sql = "UPDATE usuarios SET cargo = ? WHERE id = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, novoCargo);
      stmt.setInt(2, idUsuario);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar alterar o cargo do usuário: " + e.getMessage(), e);
    }
  }

  public boolean excluirUsuario(int id) {
    String sql = "DELETE FROM usuarios WHERE id = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar excluir o usuário: " + e.getMessage(), e);
    }
  }

  public boolean salvarDocumento(String titulo, String topico, String identificador) {
    String sql = "INSERT INTO documentos (titulo, topico, identificador, status) VALUES (?, ?, ?, 'revisao')";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, titulo);
      stmt.setString(2, topico);
      stmt.setString(3, identificador);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar salvar o documento: " + e.getMessage(), e);
    }
  }

  public void adicionarFavorito(int idUsuario, int idDocumento) throws RuntimeException {
    String sql = "INSERT INTO favoritos (usuario_id, documento_id) VALUES (?, ?)";

    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idUsuario);
      stmt.setInt(2, idDocumento);
      stmt.executeUpdate();
    } catch (SQLException e) {
      if (e.getErrorCode() == 1062) {
        throw new RuntimeException("Este documento já está nos seus favoritos.");
      } else {
        throw new RuntimeException("Ocorreu um erro ao tentar favoritar o documento: " + e.getMessage(), e);
      }
    }
  }

  public List<Documento> listarFavoritos(int idUsuario) {
    String sql = "SELECT d.* FROM documentos d "
            + "JOIN favoritos f ON d.id = f.documento_id "
            + "WHERE f.usuario_id = ?";
    List<Documento> lista = new ArrayList<>();
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idUsuario);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          lista.add(new Documento(
                  rs.getInt("id"),
                  rs.getString("titulo"),
                  rs.getString("topico"),
                  rs.getString("identificador"),
                  null
          ));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar acessar os documentos favoritos: " + e.getMessage(), e);
    }
    return lista;
  }

  public boolean removerFavorito(int idUsuario, int idDocumento) {
    String sql = "DELETE FROM favoritos WHERE usuario_id = ? AND documento_id = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idUsuario);
      stmt.setInt(2, idDocumento);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar remover o documento dos favoritos: " + e.getMessage(), e);
    }
  }

  public List<Documento> listarDocumentosPorStatus(String status) {
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
                  rs.getString("identificador"),
                  null
          ));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar buscar documentos por status: " + e.getMessage(), e);
    }
    return lista;
  }

  public boolean aprovarDocumento(int id) {
    String sql = "UPDATE documentos SET status = 'aprovado' WHERE id = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar aprovar o documento: "+ e.getMessage(), e);
    }
  }

  public void atualizarDocumento(int id, String novoTitulo, String novoTopico) {
    String sql = "UPDATE documentos SET titulo = ?, topico = ? WHERE id = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, novoTitulo);
      stmt.setString(2, novoTopico);
      stmt.setInt(3, id);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar atualizar o documento: " + e.getMessage(), e);
    }
  }

  public boolean excluirDocumento(int id) {
    String sql = "DELETE FROM documentos WHERE id = ?";
    try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
      throw new RuntimeException("Ocorreu um erro ao tentar excluir o documento: " + e.getMessage(), e);
    }
  }
}
