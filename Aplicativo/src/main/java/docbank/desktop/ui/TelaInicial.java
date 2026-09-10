package docbank.desktop.ui;

import docbank.core.domain.Documento;
import docbank.core.domain.Usuario;
import docbank.core.services.ServicoDeDocumentos;
import docbank.core.services.ServicoDeUsuarios;
import docbank.desktop.utils.DesktopUIUtils;

import java.nio.file.Paths;
import java.util.List;
import javax.swing.JOptionPane;

public final class TelaInicial extends javax.swing.JFrame {

    private List<Documento> listaDocumentosArtigos;

    private final ServicoDeDocumentos servicoDocumentos;
    private final ServicoDeUsuarios servicoUsuarios;

    private Usuario usuarioLogado;

    private TelaDeCadastro telaCadastro;
    private TelaDeLogin telaLogin;
    private TelaDeSubmissao telaSub;

    public TelaInicial(ServicoDeDocumentos servicoDocumentos, ServicoDeUsuarios servicoUsuarios) {
        this.servicoDocumentos = servicoDocumentos;
        this.servicoUsuarios = servicoUsuarios;
        initComponents();
        javax.swing.table.DefaultTableCellRenderer alinhadorEsquerda = new javax.swing.table.DefaultTableCellRenderer();
        alinhadorEsquerda.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        tabelaArtigos.getColumnModel().getColumn(0).setCellRenderer(alinhadorEsquerda);
        configurarEventosIniciais();
        popularTabela();
    }

    private void configurarEventosIniciais() {
        tabelaArtigos.setAutoCreateRowSorter(true);

        campoPesquisar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarPesquisa();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarPesquisa();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarPesquisa();
            }
        });

        tabelaArtigos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    abrirDocumentoSelecionado();
                }
            }
        });
    }

    public void popularTabela() {
        this.listaDocumentosArtigos = servicoDocumentos.listarPorStatus("aprovado");
        DesktopUIUtils.atualizarTabelaDocumentos(tabelaArtigos, listaDocumentosArtigos);
    }

    private void filtrarPesquisa() {
        String termo = campoPesquisar.getText().toLowerCase().trim();

        if (termo.isEmpty()) {
            DesktopUIUtils.atualizarTabelaDocumentos(tabelaArtigos, listaDocumentosArtigos);
            return;
        }

        List<Documento> filtrados = listaDocumentosArtigos.stream()
                .filter(doc
                        -> String.valueOf(doc.getId()).contains(termo)
                        || doc.getTitulo().toLowerCase().contains(termo)
                        || doc.getTopico().toLowerCase().contains(termo)
                        || doc.getLinkOuArquivo().toLowerCase().contains(termo)
                )
                .toList();

        DesktopUIUtils.atualizarTabelaDocumentos(tabelaArtigos, filtrados);
    }

    public void configurarSessao(Usuario usuario) {
        this.usuarioLogado = usuario;
        labelLogin.setText(usuario.getNome());

        boolean acessoPermitido = usuario.getCargo().equalsIgnoreCase("Administrador")
                || usuario.getCargo().equalsIgnoreCase("Moderador");

        botaoBiblioteca.setEnabled(true);
        botaoSubmeter.setEnabled(true);
        botaoControle.setEnabled(acessoPermitido);
        botaoDeletar.setEnabled(acessoPermitido);
        botaoEditar.setEnabled(acessoPermitido);
        botaoFavoritar.setEnabled(true);
        botaoSair.setEnabled(true);

        botaoEntrar.setEnabled(false);
        botaoCadastrar.setEnabled(false);
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public String getCargoUsuario() {
        return (usuarioLogado != null) ? usuarioLogado.getCargo() : "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaArtigos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        botaoEntrar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        botaoBiblioteca = new javax.swing.JButton();
        botaoSubmeter = new javax.swing.JButton();
        botaoControle = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labelLogin = new javax.swing.JLabel();
        botaoSair = new javax.swing.JButton();
        botaoAbrirDoc = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();
        botaoFavoritar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Inicial");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 28)); // NOI18N
        jLabel1.setText("Docbank");

        campoPesquisar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoPesquisar.setToolTipText("Pesquise por documentos com base em: título, tópico, link ou nome do arquivo.");
        campoPesquisar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        tabelaArtigos.setToolTipText("Tabela para exibição de documentos aprovados.");
        jScrollPane1.setViewportView(tabelaArtigos);

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel2.setText("Pesquisar:");

        botaoEntrar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoEntrar.setText("Entrar");
        botaoEntrar.setToolTipText("Clique aqui para entrar na sua conta.");
        botaoEntrar.addActionListener(this::botaoEntrarActionPerformed);

        botaoCadastrar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.setToolTipText("Clique aqui para se cadastrar.");
        botaoCadastrar.addActionListener(this::botaoCadastrarActionPerformed);

        botaoBiblioteca.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoBiblioteca.setText("Biblioteca Pessoal");
        botaoBiblioteca.setToolTipText("Sessão da biblioteca pessoal.");
        botaoBiblioteca.setEnabled(false);
        botaoBiblioteca.addActionListener(this::botaoBibliotecaActionPerformed);

        botaoSubmeter.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoSubmeter.setText("Submeter documento");
        botaoSubmeter.setToolTipText("Tela de submissão de documentos.");
        botaoSubmeter.setEnabled(false);
        botaoSubmeter.addActionListener(this::botaoSubmeterActionPerformed);

        botaoControle.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoControle.setText("Painel de controle");
        botaoControle.setToolTipText("Sessão para uso exclusivo de Administradores e Moderadores.");
        botaoControle.setEnabled(false);
        botaoControle.addActionListener(this::botaoControleActionPerformed);

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel6.setText("Usuário:");

        labelLogin.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        labelLogin.setText("Nenhum usuário logado");

        botaoSair.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoSair.setText("Sair");
        botaoSair.setToolTipText("Desconecta a conta conectada.");
        botaoSair.setEnabled(false);
        botaoSair.addActionListener(this::botaoSairActionPerformed);

        botaoAbrirDoc.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoAbrirDoc.setText("Abrir Documento");
        botaoAbrirDoc.setToolTipText("Abra PDF's e links com seu visualizador padrão.");
        botaoAbrirDoc.addActionListener(this::botaoAbrirDocActionPerformed);

        botaoDeletar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoDeletar.setText("Deletar");
        botaoDeletar.setToolTipText("Funcionalidade de remoção de documentos destinada a Administradores e Moderadores");
        botaoDeletar.setEnabled(false);
        botaoDeletar.addActionListener(this::botaoDeletarActionPerformed);

        botaoEditar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoEditar.setText("Editar");
        botaoEditar.setToolTipText("Funcionalidade de edição de título e tópico destinada a Administradores e Moderadores");
        botaoEditar.setEnabled(false);
        botaoEditar.addActionListener(this::botaoEditarActionPerformed);

        botaoFavoritar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoFavoritar.setText("Adicionar aos Favoritos");
        botaoFavoritar.setToolTipText("Adiciona um documento aos favoritos.");
        botaoFavoritar.setEnabled(false);
        botaoFavoritar.addActionListener(this::botaoFavoritarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoFavoritar)
                .addGap(18, 18, 18)
                .addComponent(botaoAbrirDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelLogin))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(botaoBiblioteca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botaoSubmeter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botaoControle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoCadastrar)
                        .addComponent(botaoEntrar)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoBiblioteca)
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoSubmeter)
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoControle)
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(labelLogin)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSair)
                    .addComponent(botaoAbrirDoc)
                    .addComponent(botaoFavoritar)
                    .addComponent(botaoEditar)
                    .addComponent(botaoDeletar))
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

  private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
      if (telaLogin != null && telaLogin.isVisible()) {
          telaLogin.dispose();
      }
      if (telaCadastro == null || !telaCadastro.isVisible()) {
          telaCadastro = new TelaDeCadastro(this, this.servicoUsuarios);
          telaCadastro.setVisible(true);
      } else {
          telaCadastro.toFront();
      }
  }//GEN-LAST:event_botaoCadastrarActionPerformed

  private void botaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEntrarActionPerformed
      if (telaCadastro != null && telaCadastro.isVisible()) {
          telaCadastro.dispose();
      }
      if (telaLogin == null || !telaLogin.isVisible()) {
          telaLogin = new TelaDeLogin(this, this.servicoUsuarios);
          telaLogin.setVisible(true);
      } else {
          telaLogin.toFront();
      }
  }//GEN-LAST:event_botaoEntrarActionPerformed

  private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
      this.usuarioLogado = null;
      if (telaSub != null) {
          telaSub.dispose();
      }

      labelLogin.setText("Nenhum usuário logado");
      botaoBiblioteca.setEnabled(false);
      botaoSubmeter.setEnabled(false);
      botaoControle.setEnabled(false);
      botaoSair.setEnabled(false);
      botaoDeletar.setEnabled(false);
      botaoEditar.setEnabled(false);
      botaoFavoritar.setEnabled(false);
      botaoEntrar.setEnabled(true);
      botaoCadastrar.setEnabled(true);

      JOptionPane.showMessageDialog(this, "Sessão finalizada.");
  }//GEN-LAST:event_botaoSairActionPerformed

    private void abrirDocumentoSelecionado() {
        int linhaSelecionada = tabelaArtigos.getSelectedRow();
        if (linhaSelecionada != -1) {
            int linhaReal = tabelaArtigos.convertRowIndexToModel(linhaSelecionada);
            String identificador = tabelaArtigos.getModel().getValueAt(linhaReal, 3).toString();
            DesktopUIUtils.abrirDocumento(identificador, Paths.get("pdfs", "Aprovados"));
        }
    }

  private void botaoAbrirDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbrirDocActionPerformed
      if (tabelaArtigos.getSelectedRow() == -1) {
          JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para tentar abri-lo.");
          return;
      }
      abrirDocumentoSelecionado();
  }//GEN-LAST:event_botaoAbrirDocActionPerformed

  private void botaoSubmeterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSubmeterActionPerformed
      if (telaSub == null || !telaSub.isVisible()) {
          telaSub = new TelaDeSubmissao(this.servicoDocumentos);
          telaSub.setVisible(true);
      } else {
          telaSub.toFront();
      }
  }//GEN-LAST:event_botaoSubmeterActionPerformed

  private void botaoControleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoControleActionPerformed
      if (telaSub != null) {
          telaSub.dispose();
      }
      PainelDeControle painel = new PainelDeControle(this, this.servicoDocumentos, this.servicoUsuarios);
      painel.setVisible(true);
      this.dispose();
  }//GEN-LAST:event_botaoControleActionPerformed

  private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
      int linhaSelecionada = tabelaArtigos.getSelectedRow();
      if (linhaSelecionada == -1) {
          JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para fazer a deleção.");
          return;
      }

      int linhaReal = tabelaArtigos.convertRowIndexToModel(linhaSelecionada);
      int id = (int) tabelaArtigos.getModel().getValueAt(linhaReal, 0);
      String titulo = tabelaArtigos.getModel().getValueAt(linhaReal, 1).toString();
      String topico = tabelaArtigos.getModel().getValueAt(linhaReal, 2).toString();
      String identificador = tabelaArtigos.getModel().getValueAt(linhaReal, 3).toString();

      int confirm = JOptionPane.showConfirmDialog(this, "Realmente desejas excluir o documento: '" + titulo + "' ?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
      if (confirm == JOptionPane.YES_OPTION) {
          try {
              Documento docParaExcluir = new Documento(id, titulo, topico, identificador);
              servicoDocumentos.excluir(docParaExcluir);
              JOptionPane.showMessageDialog(this, "Documento excluído.");
              popularTabela();
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, "Erro ao deletar o documento: " + e.getMessage());
          }
      }
   }//GEN-LAST:event_botaoDeletarActionPerformed

  private void botaoBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBibliotecaActionPerformed
      if (telaSub != null) {
          telaSub.dispose();
      }
      BibliotecaPessoal biblioteca = new BibliotecaPessoal(this, this.servicoDocumentos);
      biblioteca.setLabel(usuarioLogado.getNome(), usuarioLogado.getEmail());
      biblioteca.setVisible(true);
      this.dispose();
  }//GEN-LAST:event_botaoBibliotecaActionPerformed

  private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
      int linhaSelecionada = tabelaArtigos.getSelectedRow();
      if (linhaSelecionada == -1) {
          JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para editar.");
          return;
      }

      int linhaReal = tabelaArtigos.convertRowIndexToModel(linhaSelecionada);
      int id = (int) tabelaArtigos.getModel().getValueAt(linhaReal, 0);
      String tituloAtual = tabelaArtigos.getModel().getValueAt(linhaReal, 1).toString();
      String topicoAtual = tabelaArtigos.getModel().getValueAt(linhaReal, 2).toString();

      String novoTitulo = JOptionPane.showInputDialog(this, "Novo Título:", tituloAtual);
      if (novoTitulo == null || novoTitulo.trim().isEmpty()) {
          return;
      }

      String novoTopico = JOptionPane.showInputDialog(this, "Novo Tópico:", topicoAtual);
      if (novoTopico == null || novoTopico.trim().isEmpty()) {
          return;
      }

      try {
          servicoDocumentos.atualizarInformacoes(id, novoTitulo, novoTopico);
          JOptionPane.showMessageDialog(this, "Modificações aplicadas!");
          popularTabela();
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, "Erro ao editar o documento: " + e.getMessage());
      }
  }//GEN-LAST:event_botaoEditarActionPerformed

  private void botaoFavoritarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFavoritarActionPerformed
      int linhaSelecionada = tabelaArtigos.getSelectedRow();
      if (linhaSelecionada == -1) {
          JOptionPane.showMessageDialog(this, "Selecione um documento na tabela para favoritar.");
          return;
      }

      int linhaReal = tabelaArtigos.convertRowIndexToModel(linhaSelecionada);
      int idDoc = (int) tabelaArtigos.getModel().getValueAt(linhaReal, 0);
      String titulo = tabelaArtigos.getModel().getValueAt(linhaReal, 1).toString();

      try {
          servicoDocumentos.favoritar(this.usuarioLogado.getId(), idDoc);
          JOptionPane.showMessageDialog(this, "Documento '" + titulo + "' foi adicionado aos favoritos.");
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
      }
  }//GEN-LAST:event_botaoFavoritarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAbrirDoc;
    private javax.swing.JButton botaoBiblioteca;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoControle;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JButton botaoEntrar;
    private javax.swing.JButton botaoFavoritar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JButton botaoSubmeter;
    private javax.swing.JTextField campoPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JTable tabelaArtigos;
    // End of variables declaration//GEN-END:variables
}
