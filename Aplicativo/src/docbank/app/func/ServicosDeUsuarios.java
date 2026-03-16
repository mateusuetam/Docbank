package docbank.app.func;

import docbank.app.sql.DocbankDAO;
import java.util.List;

/**
* Classe responsável por operações em cima dos usuários
* @author Mateus
* @version 1.0
* @since Primeira versão
*/
public class ServicosDeUsuarios {

  private final DocbankDAO dao = new DocbankDAO();

  public List<Usuario> listarUsuarios() {
    return dao.listarTodosUsuarios();
  }

  public boolean atualizarCargo(int id, String novoCargo) {
    return dao.alterarCargo(id, novoCargo);
  }
}
