package docbank.core.ports;

import docbank.core.domain.Documento;
import java.util.List;

public interface DocumentoDAO {

    boolean salvar(String titulo, String topico, String identificador);

    void adicionarFavorito(int idUsuario, int idDocumento);

    List<Documento> listarFavoritos(int idUsuario);

    boolean removerFavorito(int idUsuario, int idDocumento);

    List<Documento> listarPorStatus(String status);

    boolean aprovar(int id);

    void atualizar(int id, String novoTitulo, String novoTopico);

    boolean excluir(int id);
}
