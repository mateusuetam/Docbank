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
 *
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

  public static void abrirDocumento(String linkOuArquivo, Path pasta) {
    boolean checagemDePdf = linkOuArquivo.toLowerCase().endsWith(".pdf");
    String alvo;

    if (checagemDePdf) {
      alvo = pasta.resolve(linkOuArquivo).toAbsolutePath().toString();
    } else if (linkOuArquivo.startsWith("http://") || linkOuArquivo.startsWith("https://")) {
      alvo = linkOuArquivo;
    } else {
      alvo = "https://" + linkOuArquivo;
    }

    try {

      if (java.awt.Desktop.isDesktopSupported()) {
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        if (checagemDePdf) {
          desktop.open(new java.io.File(alvo));
        } else {
          desktop.browse(new java.net.URI(alvo));
        }
      }

    } catch (IOException | URISyntaxException e) {
      javax.swing.JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar abrir o documento: " + e.getMessage());
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
