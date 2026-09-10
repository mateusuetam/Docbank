package docbank.desktop.ui;

import docbank.core.domain.Documento;
import docbank.core.domain.Usuario;
import docbank.core.services.ServicoDeDocumentos;
import docbank.desktop.utils.DesktopUIUtils;

import java.nio.file.Paths;
import java.util.List;
import javax.swing.JOptionPane;

public class BibliotecaPessoal extends javax.swing.JFrame {

    private final TelaInicial inicial;
    private final ServicoDeDocumentos servicoDocumentos;
    private List<Documento> favoritosAtuais;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BibliotecaPessoal.class.getName());

    public BibliotecaPessoal(TelaInicial inicial, ServicoDeDocumentos servicoDocumentos) {
        this.inicial = inicial;
        this.servicoDocumentos = servicoDocumentos;

        initComponents();
        javax.swing.table.DefaultTableCellRenderer alinhadorEsquerda = new javax.swing.table.DefaultTableCellRenderer();
        alinhadorEsquerda.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        tabelaArtigos.getColumnModel().getColumn(0).setCellRenderer(alinhadorEsquerda);
        configurarEventosTabela();
    }

    private void configurarEventosTabela() {
        tabelaArtigos.setAutoCreateRowSorter(true);

        tabelaArtigos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    abrirDocumentoSelecionado();
                }
            }
        });

        campoPesquisar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }
        });
    }

    public void popularTabela() {
        Usuario usuarioLogado = inicial.getUsuarioLogado();
        if (usuarioLogado != null) {
            try {
                this.favoritosAtuais = servicoDocumentos.listarFavoritos(usuarioLogado.getId());
                DesktopUIUtils.atualizarTabelaDocumentos(tabelaArtigos, favoritosAtuais);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar favoritos: " + e.getMessage());
            }
        }
    }

    public void setLabel(String user, String email) {
        labelLogin.setText(user);

        if (inicial.getUsuarioLogado() != null) {
            popularTabela();
        } else {
            logger.warning("Essa tela foi programada para só ser acessada caso haja um usuário logado.");
        }
    }

    private void filtrar() {
        String termo = campoPesquisar.getText().toLowerCase().trim();
        if (termo.isEmpty()) {
            DesktopUIUtils.atualizarTabelaDocumentos(tabelaArtigos, favoritosAtuais);
            return;
        }

        if (favoritosAtuais != null) {
            List<Documento> filtrados = favoritosAtuais.stream()
                    .filter(d
                            -> String.valueOf(d.getId()).contains(termo)
                            || d.getTitulo().toLowerCase().contains(termo)
                            || d.getTopico().toLowerCase().contains(termo)
                            || d.getLinkOuArquivo().toLowerCase().contains(termo))
                    .toList();
            DesktopUIUtils.atualizarTabelaDocumentos(tabelaArtigos, filtrados);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelLogin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaArtigos = new javax.swing.JTable();
        botaoRemover = new javax.swing.JButton();
        campoPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        botaoVoltar = new javax.swing.JButton();
        botaoAbrir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca Pessoal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 28)); // NOI18N
        jLabel1.setText("Docbank > Biblioteca Pessoal");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel2.setText("Usuário:");

        labelLogin.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        labelLogin.setText("Nenhum usuário logado");

        tabelaArtigos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaArtigos.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tabelaArtigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Título", "Tópico", "LINK/DOI/PDF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaArtigos.setToolTipText("Lista de documentos favoritados.");
        jScrollPane1.setViewportView(tabelaArtigos);

        botaoRemover.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoRemover.setText("Remover dos Favoritos");
        botaoRemover.setToolTipText("Remove um documento dos favoritos.");
        botaoRemover.addActionListener(this::botaoRemoverActionPerformed);

        campoPesquisar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoPesquisar.setToolTipText("Pesquise por seus documentos favoritados.");
        campoPesquisar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel4.setText("Pesquisar nos favoritos:");

        botaoVoltar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoVoltar.setText("< Voltar");
        botaoVoltar.setToolTipText("Voltar para página inicial.");
        botaoVoltar.addActionListener(this::botaoVoltarActionPerformed);

        botaoAbrir.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoAbrir.setText("Abrir Documento");
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
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoRemover)
                                .addGap(18, 18, 18)
                                .addComponent(botaoAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(327, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelLogin)
                    .addComponent(botaoVoltar)
                    .addComponent(botaoRemover)
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

    private void voltarParaTelaInicial() {
        inicial.popularTabela();
        inicial.setVisible(true);
        this.dispose();
    }

  private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarActionPerformed
      voltarParaTelaInicial();
  }//GEN-LAST:event_botaoVoltarActionPerformed

  private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
      int linha = tabelaArtigos.getSelectedRow();

      if (linha == -1) {
          JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para remover dos favoritos.");
          return;
      }

      int linhaReal = tabelaArtigos.convertRowIndexToModel(linha);
      int idDoc = (int) tabelaArtigos.getModel().getValueAt(linhaReal, 0);
      String titulo = tabelaArtigos.getModel().getValueAt(linhaReal, 1).toString();

      int confirm = JOptionPane.showConfirmDialog(this,
              "Remover '" + titulo + "' dos favoritos?", "Confirmar", JOptionPane.YES_NO_OPTION);

      if (confirm == JOptionPane.YES_OPTION) {
          try {
              servicoDocumentos.removerFavorito(inicial.getUsuarioLogado().getId(), idDoc);
              JOptionPane.showMessageDialog(this, "Documento removido dos favoritos!");
              popularTabela();
              campoPesquisar.setText("");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, "Erro ao remover favorito: " + e.getMessage());
          }
      }
  }//GEN-LAST:event_botaoRemoverActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        voltarParaTelaInicial();
    }//GEN-LAST:event_formWindowClosed

    private void abrirDocumentoSelecionado() {
        int linha = tabelaArtigos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para tentar abri-lo.");
            return;
        }

        int linhaReal = tabelaArtigos.convertRowIndexToModel(linha);
        String identificador = tabelaArtigos.getModel().getValueAt(linhaReal, 3).toString();

        DesktopUIUtils.abrirDocumento(identificador, Paths.get("pdfs", "Aprovados"));
    }

  private void botaoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbrirActionPerformed
      abrirDocumentoSelecionado();
  }//GEN-LAST:event_botaoAbrirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAbrir;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JTextField campoPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JTable tabelaArtigos;
    // End of variables declaration//GEN-END:variables
}
