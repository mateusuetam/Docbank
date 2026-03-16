package docbank.app.func;

import java.io.File;

/**
 * Classe construtora para objetos do tipo "documento"
 *
 * @author Mateus
 * @version 1.0
 * @since Primeira versão
 */
public class Documento {

  private int id;
  private String titulo;
  private String topico;
  private final String linkOuArquivo;
  private final File arquivoMetadados;

  public Documento(int id, String titulo, String topico, String linkOuArquivo, File arquivoMetadados) {
    this.id = id;
    this.titulo = titulo;
    this.topico = topico;
    this.linkOuArquivo = linkOuArquivo;
    this.arquivoMetadados = arquivoMetadados;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getTopico() {
    return topico;
  }

  public void setTopico(String topico) {
    this.topico = topico;
  }

  public String getLinkOuArquivo() {
    return linkOuArquivo;
  }

  public File getArquivoMetadados() {
    return arquivoMetadados;
  }
}
