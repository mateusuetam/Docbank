package docbank.app.func;

import docbank.app.sql.DocbankDAO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
* Classe responsável por operações em cima dos documentos
* @author Mateus
* @version 1.0
* @since Primeira versão
*/
public class ServicosDeDocumentos {

  public static final Path PASTA_REVISAO = Paths.get("pdfs", "Revisao");
  public static final Path PASTA_APROVADOS = Paths.get("pdfs", "Aprovados");
  private final DocbankDAO dao = new DocbankDAO();

  public void salvarNovoDoc(String titulo, String topico, String link, File arquivo) throws IOException {

    Files.createDirectories(PASTA_REVISAO);
    String identificador;

    if (arquivo != null) {
      identificador = "doc_" + System.currentTimeMillis() + "_" + arquivo.getName();
      Files.copy(arquivo.toPath(), PASTA_REVISAO.resolve(identificador), StandardCopyOption.REPLACE_EXISTING);
    } else {
      identificador = link;
    }

    dao.salvarDocumento(titulo, topico, identificador);
  }
  
    public static void abrirDocumento(String linkOuArquivo, Path pastaRaiz) {
    String os = System.getProperty("os.name").toLowerCase();

    try {

      if (os.contains("linux")) {
        String alvo;
        if (linkOuArquivo.toLowerCase().endsWith(".pdf")) {
          alvo = pastaRaiz.resolve(linkOuArquivo).toAbsolutePath().toString();
        } else {
          alvo = linkOuArquivo.startsWith("http") ? linkOuArquivo : "https://" + linkOuArquivo;
        }

        new ProcessBuilder("xdg-open", alvo).start();

      } else {

        if (linkOuArquivo.toLowerCase().endsWith(".pdf")) {
          java.io.File arquivo = pastaRaiz.resolve(linkOuArquivo).toFile();

          if (arquivo.exists()) {
            java.awt.Desktop.getDesktop().open(arquivo);
          } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Não foi possível encontrar o arquivo: " + arquivo.getAbsolutePath());
          }
        } else {
          String url = linkOuArquivo.startsWith("http") ? linkOuArquivo : "https://" + linkOuArquivo;

          if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
          } else if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "start", url).start();
          }
        }
      }
    } catch (IOException | URISyntaxException e) {
      javax.swing.JOptionPane.showMessageDialog(null, "Houve um erro ao tentar abrir o arquivo: " + e.getMessage());
    }
  }

  public void aprovar(Documento doc) throws IOException {

    Files.createDirectories(PASTA_APROVADOS);
    dao.aprovarDocumento(doc.getId());

    if (doc.getLinkOuArquivo() != null && doc.getLinkOuArquivo().toLowerCase().endsWith(".pdf")) {
      Path origem = PASTA_REVISAO.resolve(doc.getLinkOuArquivo());
      Path destino = PASTA_APROVADOS.resolve(doc.getLinkOuArquivo());
      if (Files.exists(origem)) {
        Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
      }
    }
  }

  public void excluir(Documento doc) throws IOException {
    
    dao.excluirDocumento(doc.getId());

    if (doc.getLinkOuArquivo() != null && doc.getLinkOuArquivo().toLowerCase().endsWith(".pdf")) {
      Files.deleteIfExists(PASTA_REVISAO.resolve(doc.getLinkOuArquivo()));
      Files.deleteIfExists(PASTA_APROVADOS.resolve(doc.getLinkOuArquivo()));
    }
  }

  public void favoritar(int idUsuario, int idDocumento) {
    dao.adicionarFavorito(idUsuario, idDocumento);
  }

  public List<Documento> listarDocumentosPorStatus(String status) {
    return dao.listarDocumentosPorStatus(status);
  }

  public void atualizarInformacoes(int id, String titulo, String topico) {
    dao.atualizarDocumento(id, titulo, topico);
  }
}
