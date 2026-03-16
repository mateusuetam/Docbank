package docbank.app.func;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Classe com funções auxiliares para as tabelas
 *
 * @author Mateus
 * @version 1.0
 * @since Primeira versão
 */
public class Util {

  public static void atualizarInterface(javax.swing.JTable tabela, List<Object[]> dados) {

    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    modelo.setNumRows(0);

    for (Object[] linha : dados) {
      modelo.addRow(linha);
    }
  }

  public static void atualizarTabelaDocumentos(javax.swing.JTable tabela, List<Documento> lista) {

    List<Object[]> dados = new ArrayList<>();

    for (Documento doc : lista) {
      dados.add(new Object[]{
        doc.getId(),
        doc.getTitulo(),
        doc.getTopico(),
        doc.getLinkOuArquivo()
      });
    }

    atualizarInterface(tabela, dados);
  }

  public static void atualizarTabelaUsuarios(javax.swing.JTable tabela, List<Usuario> lista) {
    List<Object[]> dadosParaTabela = new ArrayList<>();

    for (Usuario u : lista) {
      dadosParaTabela.add(new Object[]{
        u.getId(),
        u.getNome(),
        u.getCargo()});
    }

    atualizarInterface(tabela, dadosParaTabela);
  }
}
