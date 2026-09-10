package docbank.infra.storage;

import docbank.core.ports.FileStorage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LocalFileStorage implements FileStorage {

    private static final Path PASTA_REVISAO = Paths.get("pdfs", "Revisao");
    private static final Path PASTA_APROVADOS = Paths.get("pdfs", "Aprovados");

    @Override
    public String salvarArquivoRevisao(File arquivo) throws IOException {
        Files.createDirectories(PASTA_REVISAO);
        String identificador = "doc_" + System.currentTimeMillis() + "_" + arquivo.getName();
        Files.copy(arquivo.toPath(), PASTA_REVISAO.resolve(identificador), StandardCopyOption.REPLACE_EXISTING);
        return identificador;
    }

    @Override
    public void moverParaAprovados(String nomeArquivo) throws IOException {
        Files.createDirectories(PASTA_APROVADOS);
        Path origem = PASTA_REVISAO.resolve(nomeArquivo);
        Path destino = PASTA_APROVADOS.resolve(nomeArquivo);
        if (Files.exists(origem)) {
            Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Override
    public void excluirArquivos(String nomeArquivo) throws IOException {
        Files.deleteIfExists(PASTA_REVISAO.resolve(nomeArquivo));
        Files.deleteIfExists(PASTA_APROVADOS.resolve(nomeArquivo));
    }
}
