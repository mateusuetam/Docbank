package docbank.desktop.utils;

import docbank.core.domain.Documento;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

public class DesktopUIUtils {

    public static void atualizarTabelaDocumentos(JTable tabela, List<Documento> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (Documento doc : lista) {
            modelo.addRow(new Object[]{
                doc.getId(),
                doc.getTitulo(),
                doc.getTopico(),
                doc.getLinkOuArquivo()
            });
        }
    }

    public static void atualizarTabelaUsuarios(javax.swing.JTable tabela, List<docbank.core.domain.Usuario> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (docbank.core.domain.Usuario user : lista) {
            modelo.addRow(new Object[]{
                user.getId(),
                user.getNome(),
                user.getCargo()
            });
        }
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
                    File arquivo = pastaRaiz.resolve(linkOuArquivo).toFile();
                    if (arquivo.exists()) {
                        java.awt.Desktop.getDesktop().open(arquivo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o arquivo: " + arquivo.getAbsolutePath());
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
            JOptionPane.showMessageDialog(null, "Houve um erro ao tentar abrir o arquivo: " + e.getMessage());
        }
    }
}
