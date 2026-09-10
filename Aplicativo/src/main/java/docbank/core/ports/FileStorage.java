package docbank.core.ports;

import java.io.File;
import java.io.IOException;

public interface FileStorage {

    String salvarArquivoRevisao(File arquivo) throws IOException;

    void moverParaAprovados(String nomeArquivo) throws IOException;

    void excluirArquivos(String nomeArquivo) throws IOException;
}
