package docbank.app.telas;

import docbank.app.func.Documento;
import docbank.app.func.ServicosDeDocumentos;
import docbank.app.func.Util;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe da tela de painel de controle
 *
 * @author Mateus
 * @version 1.0
 * @since Primeira versão
 */
public class PainelDeControle extends javax.swing.JFrame {

  private final ServicosDeDocumentos service = new ServicosDeDocumentos();
  private List<Documento> documentosAtuais;
  private final TelaInicial inicial;
  private final String cargoUsuario;
  private TelaDoAdmin telaAdminAtiva;

  private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PainelDeControle.class.getName());

  public PainelDeControle(TelaInicial inicial) {
    this.inicial = inicial;
    this.cargoUsuario = inicial.getCargoUsuario();
    
    initComponents();
   
    tabelaRevisao.setAutoCreateRowSorter(true);
    
    configurarPermissoes();
    
    popularTabela();

    tabelaRevisao.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
          int linhaSelecionada = tabelaRevisao.getSelectedRow();
          if (linhaSelecionada != -1) {
            int linhaReal = tabelaRevisao.convertRowIndexToModel(linhaSelecionada);
            String identificador = tabelaRevisao.getModel().getValueAt(linhaReal, 3).toString();

            ServicosDeDocumentos.abrirDocumento(identificador, ServicosDeDocumentos.PASTA_REVISAO);
          }
        }
      }
    });
  }

  private void popularTabela() {
    this.documentosAtuais = service.listarDocumentosPorStatus("revisao");
    Util.atualizarTabelaDocumentos(tabelaRevisao, documentosAtuais);
  }

  private void configurarPermissoes() {
    boolean admin = "Administrador".equalsIgnoreCase(cargoUsuario);
    botaoAdmin.setEnabled(admin);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    botaoVoltar = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tabelaRevisao = new javax.swing.JTable();
    botaoAprovar = new javax.swing.JButton();
    botaoRejeitar = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    botaoAdmin = new javax.swing.JButton();
    botaoAbrir = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Painel de Controle");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosed(java.awt.event.WindowEvent evt) {
        formWindowClosed(evt);
      }
    });

    botaoVoltar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
    botaoVoltar.setText("< Voltar");
    botaoVoltar.setToolTipText("Voltar para página inicial.");
    botaoVoltar.addActionListener(this::botaoVoltarActionPerformed);

    tabelaRevisao.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    tabelaRevisao.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
    tabelaRevisao.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "ID", "Título", "Tópico", "LINK/DOI/PDF"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tabelaRevisao.setToolTipText("Tabela para exibição de artigos do banco de dados.");
    jScrollPane1.setViewportView(tabelaRevisao);

    botaoAprovar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
    botaoAprovar.setText("Aprovar Documento");
    botaoAprovar.setToolTipText("Botão para aprovar um documento e disponibilizá-lo ao público");
    botaoAprovar.addActionListener(this::botaoAprovarActionPerformed);

    botaoRejeitar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
    botaoRejeitar.setText("Rejeitar Documento");
    botaoRejeitar.setToolTipText("Esse botão rejeita e deleta um documento que está em revisão.");
    botaoRejeitar.addActionListener(this::botaoRejeitarActionPerformed);

    jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 28)); // NOI18N
    jLabel1.setText("Docbank > Painel de Controle");

    botaoAdmin.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
    botaoAdmin.setText("Sessão do Administrador");
    botaoAdmin.setToolTipText("Tela de gerênciamento de usuários dedicada a Administração");
    botaoAdmin.setEnabled(false);
    botaoAdmin.addActionListener(this::botaoAdminActionPerformed);

    botaoAbrir.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
    botaoAbrir.setText("Abrir");
    botaoAbrir.addActionListener(this::botaoAbrirActionPerformed);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addComponent(botaoVoltar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(botaoAdmin)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botaoRejeitar)
            .addGap(18, 18, 18)
            .addComponent(botaoAprovar)
            .addGap(18, 18, 18)
            .addComponent(botaoAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(botaoAprovar)
          .addComponent(botaoVoltar)
          .addComponent(botaoAdmin)
          .addComponent(botaoRejeitar)
          .addComponent(botaoAbrir))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
    
    if (telaAdminAtiva != null) {
      telaAdminAtiva.dispose();
    }
    
    inicial.popularTabela();
    inicial.setVisible(true);
    this.dispose();
  }//GEN-LAST:event_botaoVoltarActionPerformed

  private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    
    if (telaAdminAtiva != null) {
      telaAdminAtiva.dispose();
    }
    
    inicial.setVisible(true);
    this.dispose();
  }//GEN-LAST:event_formWindowClosed

  private void botaoAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdminActionPerformed
    
    if (telaAdminAtiva != null && telaAdminAtiva.isVisible()) {
      telaAdminAtiva.toFront();
    } else {
      telaAdminAtiva = new TelaDoAdmin();
      telaAdminAtiva.setVisible(true);
    }
  }//GEN-LAST:event_botaoAdminActionPerformed

  private void botaoAprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAprovarActionPerformed
    int linhaSelecionada = tabelaRevisao.getSelectedRow();

    if (linhaSelecionada == -1) {
      JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para aprovar.");
      return;
    }

    int linhaReal = tabelaRevisao.convertRowIndexToModel(linhaSelecionada);

    int id = (int) tabelaRevisao.getModel().getValueAt(linhaReal, 0);

    Documento doc = documentosAtuais.stream()
            .filter(d -> d.getId() == id)
            .findFirst()
            .orElse(null);

    if (doc != null) {
      int confirm = JOptionPane.showConfirmDialog(this,
              "Realmente desejas aprovar o documento: '" + doc.getTitulo() + "' ?",
              "Confirmar Aprovação", JOptionPane.YES_NO_OPTION);

      if (confirm == JOptionPane.YES_OPTION) {
        try {
          service.aprovar(doc);
          JOptionPane.showMessageDialog(this, "Documento '" + doc.getTitulo() + "' aprovado!");
          popularTabela();
        } catch (IOException e) {
          JOptionPane.showMessageDialog(this, "Erro ao tentar aprovar o documento: " + e.getMessage());
        }
      }
    }
  }//GEN-LAST:event_botaoAprovarActionPerformed

  private void botaoRejeitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRejeitarActionPerformed
    int linhaSelecionada = tabelaRevisao.getSelectedRow();

    if (linhaSelecionada == -1) {
      JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para fazer a deleção.");
      return;
    }

    int linhaReal = tabelaRevisao.convertRowIndexToModel(linhaSelecionada);
    int id = (int) tabelaRevisao.getModel().getValueAt(linhaReal, 0);

    Documento doc = documentosAtuais.stream()
            .filter(d -> d.getId() == id)
            .findFirst()
            .orElse(null);

    if (doc != null) {
      int confirm = JOptionPane.showConfirmDialog(this,
              "Realmente desejas excluir o documento: '" + doc.getTitulo() + "' ?",
              "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

      if (confirm == JOptionPane.YES_OPTION) {
        try {
          service.excluir(doc);
          JOptionPane.showMessageDialog(this, "Documento '" + doc.getTitulo() + "' excluído.");
          popularTabela();
        } catch (IOException e) {
          JOptionPane.showMessageDialog(this, "Erro ao tentar deletar o documento: " + e.getMessage());
        }
      }
    }
  }//GEN-LAST:event_botaoRejeitarActionPerformed

  private void botaoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbrirActionPerformed
    int linhaSelecionada = tabelaRevisao.getSelectedRow();

    if (linhaSelecionada == -1) {
      JOptionPane.showMessageDialog(null, "Selecione um documento na tabela para tentar abri-lo.");
      return;
    }

    int linhaReal = tabelaRevisao.convertRowIndexToModel(linhaSelecionada);
    String identificador = tabelaRevisao.getModel().getValueAt(linhaReal, 3).toString();

    ServicosDeDocumentos.abrirDocumento(identificador, ServicosDeDocumentos.PASTA_REVISAO);
  }//GEN-LAST:event_botaoAbrirActionPerformed

  public static void main(String args[]) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
      logger.log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton botaoAbrir;
  private javax.swing.JButton botaoAdmin;
  private javax.swing.JButton botaoAprovar;
  private javax.swing.JButton botaoRejeitar;
  private javax.swing.JButton botaoVoltar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tabelaRevisao;
  // End of variables declaration//GEN-END:variables
}
