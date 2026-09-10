
import docbank.core.ports.DocumentoDAO;
import docbank.core.ports.UsuarioDAO;
import docbank.core.services.ServicoDeDocumentos;
import docbank.core.services.ServicoDeUsuarios;
import docbank.desktop.ui.TelaInicial;
import docbank.infra.database.MySqlDocumentoDAO;
import docbank.infra.database.MySqlUsuarioDAO;
import docbank.infra.storage.LocalFileStorage;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível carregar o LookAndFeel.");
        }

        SwingUtilities.invokeLater(() -> {
            try {
                LocalFileStorage fileStorage = new LocalFileStorage();
                DocumentoDAO documentoDAO = new MySqlDocumentoDAO();
                UsuarioDAO usuarioDAO = new MySqlUsuarioDAO();
                ServicoDeDocumentos servicoDoc = new ServicoDeDocumentos(documentoDAO, fileStorage);
                ServicoDeUsuarios servicoUsu = new ServicoDeUsuarios(usuarioDAO);

                System.out.println("[Iniciando verificações do sistema]");

                int qtdUsuarios = servicoUsu.listarUsuarios().size();
                System.out.println("[SUCESSO] Conexão com banco de dados estabelecida.");
                System.out.println("[INFO] Usuários registrados no sistema: " + qtdUsuarios);

                int qtdAprovados = servicoDoc.listarPorStatus("aprovado").size();
                System.out.println("[SUCESSO] Leitura de documentos aprovados funcionando.");
                System.out.println("[INFO] Documentos aprovados no sistema: " + qtdAprovados);

                int qtdRevisao = servicoDoc.listarPorStatus("revisao").size();
                System.out.println("[SUCESSO] Leitura de documentos em revisão funcionando.");
                System.out.println("[INFO] Documentos aguardando revisão: " + qtdRevisao);

                System.out.println("[Testes concluídos]");

                TelaInicial telaInicial = new TelaInicial(servicoDoc, servicoUsu);
                telaInicial.setLocationRelativeTo(null);
                telaInicial.setVisible(true);
            } catch (Exception e) {
                System.err.println("Erro ao iniciar aplicação: " + e.getMessage());
                JOptionPane.showMessageDialog(
                        null,
                        "Falha ao iniciar o sistema (verifique a conexão com o banco de dados):\n" + e.getMessage(),
                        "Erro Crítico",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
