package docbank.core.services;

import docbank.core.domain.Usuario;
import docbank.core.ports.UsuarioDAO;
import docbank.core.utils.PasswordUtil;
import docbank.core.utils.UsuarioValidadorUtil;
import java.util.List;

public class ServicoDeUsuarios {

    private final UsuarioDAO usuarioDAO;

    public ServicoDeUsuarios(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String email, char[] senha) {
        if (!UsuarioValidadorUtil.isPreenchido(email) || !UsuarioValidadorUtil.isSenhaPreenchida(senha)) {
            throw new IllegalArgumentException("E-mail e senha são obrigatórios para autenticação.");
        }

        String senhaHash = PasswordUtil.hashSenha(senha);
        Usuario usuario = usuarioDAO.autenticar(email.trim(), senhaHash);

        if (usuario == null) {
            throw new IllegalArgumentException("Falha na autenticação. Verifique se suas credenciais estão corretas.");
        }
        if ("Suspenso".equalsIgnoreCase(usuario.getCargo())) {
            throw new IllegalStateException("Usuário suspenso. Acesso negado.");
        }
        return usuario;
    }

    public void cadastrarNovoUsuario(String nome, String email, char[] senha, String cargo) {
        if (!UsuarioValidadorUtil.isPreenchido(nome)) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (!UsuarioValidadorUtil.isEmailValido(email)) {
            throw new IllegalArgumentException("Um e-mail válido é obrigatório (ex: seu.nome@dominio.com).");
        }
        if (!UsuarioValidadorUtil.isSenhaPreenchida(senha)) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }
        if (!UsuarioValidadorUtil.isSenhaTemTamanhoMinimo(senha)) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }
        if (!UsuarioValidadorUtil.isPreenchido(cargo)) {
            throw new IllegalArgumentException("O cargo do usuário é obrigatório.");
        }
        if (usuarioDAO.existeEmail(email.trim())) {
            throw new IllegalArgumentException("Este e-mail já está cadastrado. Tente outro.");
        }

        String senhaHash = PasswordUtil.hashSenha(senha);
        boolean sucesso = usuarioDAO.cadastrar(nome.trim(), email.trim(), senhaHash, cargo.trim());

        if (!sucesso) {
            throw new RuntimeException("Houve um erro interno ao tentar realizar o cadastro.");
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarTodos();
    }

    public void alterarCargo(int idUser, String novoCargo) {
        if (!UsuarioValidadorUtil.isIdValido(idUser)) {
            throw new IllegalArgumentException("ID de usuário inválido.");
        }
        if (!UsuarioValidadorUtil.isPreenchido(novoCargo)) {
            throw new IllegalArgumentException("O novo cargo é obrigatório.");
        }
        if (!UsuarioValidadorUtil.isCargoPermitido(novoCargo)) {
            throw new IllegalArgumentException("Cargo inválido. Cargos permitidos: " + UsuarioValidadorUtil.getCargosPermitidos());
        }

        boolean sucesso = usuarioDAO.alterarCargo(idUser, novoCargo.trim());
        if (!sucesso) {
            throw new IllegalStateException("Usuário não encontrado para alteração de cargo.");
        }
    }

    public void excluirUsuario(int idUser) {
        if (!UsuarioValidadorUtil.isIdValido(idUser)) {
            throw new IllegalArgumentException("ID de usuário inválido.");
        }

        boolean sucesso = usuarioDAO.excluir(idUser);
        if (!sucesso) {
            throw new IllegalStateException("Usuário não encontrado para exclusão.");
        }
    }
}
