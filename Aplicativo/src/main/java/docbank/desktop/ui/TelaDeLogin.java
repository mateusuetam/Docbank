package docbank.desktop.ui;

import docbank.core.domain.Usuario;
import docbank.core.services.ServicoDeUsuarios;
import javax.swing.JOptionPane;

public class TelaDeLogin extends javax.swing.JFrame {

    private final TelaInicial inicial;
    private final ServicoDeUsuarios servicoUsuarios;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaDeLogin.class.getName());

    public TelaDeLogin(TelaInicial inicial, ServicoDeUsuarios servicoUsuarios) {
        this.inicial = inicial;
        this.servicoUsuarios = servicoUsuarios;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        botaoEntrar = new javax.swing.JButton();
        campoSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Login");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel1.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel2.setText("Email usado na criação da conta");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel3.setText("Senha:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 12)); // NOI18N
        jLabel4.setText("As senhas tem um mínimo de 8 caracteres");

        campoEmail.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoEmail.setToolTipText("Digite seu email de login.");
        campoEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 28)); // NOI18N
        jLabel5.setText("Login");

        botaoCancelar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setToolTipText("Cancelar login.");
        botaoCancelar.addActionListener(this::botaoCancelarActionPerformed);

        botaoEntrar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        botaoEntrar.setText("Entrar");
        botaoEntrar.setToolTipText("Entrar na conta.");
        botaoEntrar.addActionListener(this::botaoEntrarActionPerformed);

        campoSenha.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        campoSenha.setToolTipText("Senha vinculada a conta cadastrada.");
        campoSenha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoSenha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(campoEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(100, 100, 100))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoEntrar))
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

  private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
      this.dispose();
  }//GEN-LAST:event_botaoCancelarActionPerformed

  private void botaoEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEntrarActionPerformed
      String email = campoEmail.getText().trim();
      char[] senhaChars = campoSenha.getPassword();

      try {
          Usuario usuarioLogado = servicoUsuarios.autenticar(email, senhaChars);

          inicial.configurarSessao(usuarioLogado);
          JOptionPane.showMessageDialog(this,
                  "Login efetuado com sucesso!\nBem-vindo(a) " + usuarioLogado.getNome() + ".",
                  "Acesso Liberado",
                  JOptionPane.INFORMATION_MESSAGE);

          this.dispose();

      } catch (IllegalArgumentException | IllegalStateException e) {
          JOptionPane.showMessageDialog(this,
                  e.getMessage(),
                  "Aviso de Autenticação",
                  JOptionPane.WARNING_MESSAGE);

      } catch (Exception e) {
          JOptionPane.showMessageDialog(this,
                  "Falha ao conectar: " + e.getMessage(),
                  "Erro no Sistema",
                  JOptionPane.ERROR_MESSAGE);

      } finally {
          java.util.Arrays.fill(senhaChars, '0');
      }
  }//GEN-LAST:event_botaoEntrarActionPerformed

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
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoEntrar;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
