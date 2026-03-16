package docbank.app.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de conexão com o BD
 *
 * @author Mateus
 * @version 1.0
 * @since Primeira versão
 */
public class Conexao {

  private static final String URL = "jdbc:mysql://localhost:3306/docbank";
  private static final String USER = "root";
  private static final String PASS = "SenhaDoBancoDeDados"; // Substitua essa String com a senha definida para a conexão com o BD.

  public static Connection getConnection() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(URL, USER, PASS);
    } catch (ClassNotFoundException e) {
      throw new SQLException("Não foi possível localizar o driver: ", e);
    }
  }
}
