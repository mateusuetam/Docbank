package docbank.desktop.ui;

import docbank.core.domain.Usuario;
import docbank.core.services.ServicoDeUsuarios;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;

public class TelaDeCadastro extends javax.swing.JFrame {

    private final TelaInicial inicial;
    private final ServicoDeUsuarios servicoUsuarios;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaDeCadastro.class.getName());

    public TelaDeCadastro(TelaInicial inicial, ServicoDeUsuarios servicoUsuarios) {
        this.inicial = inicial;
        this.servicoUsuarios = servicoUsuarios;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        botaoCadastrar = new javax.swing.JButton();
        campoSenha = new javax.swing.JPasswordField();
        campoConfSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Cadastro");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel2.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel4.setText("Senha:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel5.setText("Exemplo: usuario@exemplo.com");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel6.setText("Como aparecerá em seu perfil");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel8.setText("Mínimo de 8 caracteres");

        campoNome.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoNome.setToolTipText("Nome para seu perfil.");
        campoNome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        campoEmail.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoEmail.setToolTipText("Email que será usado para login.");
        campoEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 28)); // NOI18N
        jLabel9.setText("Cadastro de Usuário");

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel10.setText("Confime sua senha:");

        botaoCancelar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setToolTipText("Cancelar cadastro.");
        botaoCancelar.addActionListener(this::botaoCancelarActionPerformed);

        botaoCadastrar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.setToolTipText("Cadastre-se no Docbank.");
        botaoCadastrar.addActionListener(this::botaoCadastrarActionPerformed);

        campoSenha.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoSenha.setToolTipText("Senha para a conta.");
        campoSenha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        campoConfSenha.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoConfSenha.setToolTipText("Confirme a senha fornecida para a conta.");
        campoConfSenha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCadastrar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(126, 126, 126))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(campoEmail)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addComponent(campoNome)
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoConfSenha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoSenha))
                        .addGap(100, 100, 100))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoConfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar)
                    .addComponent(botaoCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
      this.dispose();
  }//GEN-LAST:event_botaoCancelarActionPerformed

    private boolean validacaoUI() {
        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        char[] senha = campoSenha.getPassword();
        char[] confSenha = campoConfSenha.getPassword();

        if (nome.isBlank() || email.isBlank() || senha.length == 0) {
            java.util.Arrays.fill(senha, '0');
            java.util.Arrays.fill(confSenha, '0');
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos antes de continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean senhasBatem = java.util.Arrays.equals(senha, confSenha);
        java.util.Arrays.fill(senha, '0');
        java.util.Arrays.fill(confSenha, '0');

        if (!senhasBatem) {
            JOptionPane.showMessageDialog(this,
                    "As senhas informadas não coincidem.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private char[] obterChaveMestraSegura() {
        File arquivo = new File("config.txt");
        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(this, "Arquivo de configuração 'config.txt' não encontrado.");
            return null;
        }
        try (Scanner scanner = new Scanner(arquivo)) {
            if (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                return linha.replace("Senha:", "").trim().toCharArray();
            }
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Erro ao ler config.txt", e);
        }
        return null;
    }

  private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
      if (!validacaoUI()) {
          return;
      }

      String nome = campoNome.getText().trim();
      String email = campoEmail.getText().trim();
      char[] senhaChars = campoSenha.getPassword();
      String cargo = "Usuário";

      Object[] opcoes = {"Usuário", "Moderador", "Administrador"};
      int escolha = JOptionPane.showOptionDialog(this, "Qual o nível de acesso desta conta?", "Tipo de Conta",
              JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

      if (escolha == JOptionPane.CLOSED_OPTION) {
          java.util.Arrays.fill(senhaChars, '0');
          return;
      }

      if (escolha == 1 || escolha == 2) {
          String cargoDesejado = (escolha == 1) ? "Moderador" : "Administrador";

          javax.swing.JPasswordField campoChaveMestra = new javax.swing.JPasswordField();
          int confirmacao = JOptionPane.showConfirmDialog(this, campoChaveMestra, "Chave Mestra para " + cargoDesejado,
                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

          if (confirmacao == JOptionPane.OK_OPTION) {
              char[] chaveDigitada = campoChaveMestra.getPassword();
              char[] chaveReal = obterChaveMestraSegura();

              if (chaveReal != null && java.util.Arrays.equals(chaveDigitada, chaveReal)) {
                  cargo = cargoDesejado;
                  java.util.Arrays.fill(chaveReal, '0');
              } else {
                  JOptionPane.showMessageDialog(this, "Chave mestra incorreta. O cadastro foi cancelado.");
                  java.util.Arrays.fill(chaveDigitada, '0');
                  java.util.Arrays.fill(senhaChars, '0');
                  return;
              }
              java.util.Arrays.fill(chaveDigitada, '0');
          } else {
              java.util.Arrays.fill(senhaChars, '0');
              return;
          }
      }

      try {
          servicoUsuarios.cadastrarNovoUsuario(nome, email, senhaChars, cargo);

          Usuario userLogado = servicoUsuarios.autenticar(email, senhaChars);
          inicial.configurarSessao(userLogado);

          JOptionPane.showMessageDialog(this,
                  "Cadastro de " + cargo + " realizado com sucesso!\nBem-vindo(a), " + nome,
                  "Sucesso",
                  JOptionPane.INFORMATION_MESSAGE);
          this.dispose();

      } catch (IllegalArgumentException | IllegalStateException e) {
          JOptionPane.showMessageDialog(this,
                  e.getMessage(),
                  "Atenção aos dados",
                  JOptionPane.WARNING_MESSAGE);
      } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Ocorreu um erro interno: " + e.getMessage(),
                  "Erro Crítico",
                  JOptionPane.ERROR_MESSAGE);
      } finally {
          java.util.Arrays.fill(senhaChars, '0');
      }
  }//GEN-LAST:event_botaoCadastrarActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException e) {
            logger.log(java.util.logging.Level.SEVERE, null, e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JPasswordField campoConfSenha;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoNome;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
