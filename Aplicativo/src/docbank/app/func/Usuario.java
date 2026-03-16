package docbank.app.func;

import java.io.File;

/**
 * Classe construtora para objetos do tipo "usuario"
 *
 * @author Mateus
 * @version 1.0
 * @since Primeira versão
 */
public class Usuario {

  private int id;
  private final String nome;
  private final String email;
  private final String cargo;
  private final File arquivoOrigem;

  public Usuario(int id, String nome, String email, String cargo, File arquivoOrigem) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cargo = cargo;
    this.arquivoOrigem = arquivoOrigem;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getCargo() {
    return cargo;
  }

  public File getArquivoOrigem() {
    return arquivoOrigem;
  }

}
