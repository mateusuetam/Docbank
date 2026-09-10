package docbank.core.ports;

import docbank.core.domain.Usuario;
import java.util.List;

public interface UsuarioDAO {

    boolean cadastrar(String nome, String email, String senhaHash, String cargo);

    boolean existeEmail(String email);

    Usuario autenticar(String email, String senhaHash);

    List<Usuario> listarTodos();

    boolean alterarCargo(int idUsuario, String novoCargo);

    boolean excluir(int id);
}
