package docbank.core.services;

import docbank.core.domain.Documento;
import docbank.core.ports.DocumentoDAO;
import docbank.core.ports.FileStorage;
import docbank.core.utils.DocumentoValidadorUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServicoDeDocumentos {

    private final DocumentoDAO documentoDAO;
    private final FileStorage fileStorage;

    public ServicoDeDocumentos(DocumentoDAO documentoDAO, FileStorage fileStorage) {
        this.documentoDAO = documentoDAO;
        this.fileStorage = fileStorage;
    }

    public void salvarNovoDoc(String titulo, String topico, String link, File arquivo) throws IOException {
        if (!DocumentoValidadorUtil.isPreenchido(titulo)) {
            throw new IllegalArgumentException("O título do documento é obrigatório.");
        }
        if (!DocumentoValidadorUtil.isPreenchido(topico)) {
            throw new IllegalArgumentException("O tópico do documento é obrigatório.");
        }
        if (!DocumentoValidadorUtil.isLinkOuArquivoPresente(link, arquivo)) {
            throw new IllegalArgumentException("É necessário informar um link ou anexar um arquivo PDF.");
        }
        if (arquivo != null && !DocumentoValidadorUtil.isArquivoPdf(arquivo)) {
            throw new IllegalArgumentException("Apenas arquivos no formato PDF são permitidos.");
        }
        if (DocumentoValidadorUtil.isPreenchido(link) && !DocumentoValidadorUtil.isLinkValido(link)) {
            throw new IllegalArgumentException("O link deve começar com http:// ou https://");
        }

        String identificador = link;
        if (arquivo != null) {
            identificador = fileStorage.salvarArquivoRevisao(arquivo);
        }

        boolean sucesso = documentoDAO.salvar(titulo.trim(), topico.trim(), identificador);
        if (!sucesso) {
            throw new IllegalStateException("Nenhum documento foi salvo no banco de dados.");
        }
    }

    public void aprovar(Documento doc) throws IOException {
        if (!DocumentoValidadorUtil.isDocumentoPreenchido(doc)) {
            throw new IllegalArgumentException("Documento não pode ser nulo.");
        }

        boolean sucesso = documentoDAO.aprovar(doc.getId());
        if (!sucesso) {
            throw new IllegalStateException("O documento informado não foi encontrado para aprovação.");
        }

        if (DocumentoValidadorUtil.ehPdf(doc.getLinkOuArquivo())) {
            fileStorage.moverParaAprovados(doc.getLinkOuArquivo());
        }
    }

    public void excluir(Documento doc) throws IOException {
        if (!DocumentoValidadorUtil.isDocumentoPreenchido(doc)) {
            throw new IllegalArgumentException("Documento não pode ser nulo.");
        }

        boolean sucesso = documentoDAO.excluir(doc.getId());
        if (!sucesso) {
            throw new IllegalStateException("O documento informado não foi encontrado para exclusão.");
        }

        if (DocumentoValidadorUtil.ehPdf(doc.getLinkOuArquivo())) {
            fileStorage.excluirArquivos(doc.getLinkOuArquivo());
        }
    }

    public List<Documento> listarPorStatus(String status) {
        if (!DocumentoValidadorUtil.isStatusPermitido(status)) {
            throw new IllegalArgumentException("Status inválido. Use 'revisao' ou 'aprovado'.");
        }
        return documentoDAO.listarPorStatus(status.trim().toLowerCase());
    }

    public void atualizarInformacoes(int id, String novoTitulo, String novoTopico) {
        if (!DocumentoValidadorUtil.isIdValido(id)) {
            throw new IllegalArgumentException("ID de documento inválido.");
        }
        if (!DocumentoValidadorUtil.isPreenchido(novoTitulo)) {
            throw new IllegalArgumentException("O novo título é obrigatório.");
        }
        if (!DocumentoValidadorUtil.isPreenchido(novoTopico)) {
            throw new IllegalArgumentException("O novo tópico é obrigatório.");
        }

        documentoDAO.atualizar(id, novoTitulo.trim(), novoTopico.trim());
    }

    public void favoritar(int idUsuario, int idDoc) {
        if (!DocumentoValidadorUtil.isIdValido(idUsuario) || !DocumentoValidadorUtil.isIdValido(idDoc)) {
            throw new IllegalArgumentException("IDs de usuário ou documento inválidos.");
        }
        documentoDAO.adicionarFavorito(idUsuario, idDoc);
    }

    public List<Documento> listarFavoritos(int idUsuario) {
        if (!DocumentoValidadorUtil.isIdValido(idUsuario)) {
            throw new IllegalArgumentException("ID de usuário inválido.");
        }
        return documentoDAO.listarFavoritos(idUsuario);
    }

    public void removerFavorito(int idUsuario, int idDoc) {
        if (!DocumentoValidadorUtil.isIdValido(idUsuario) || !DocumentoValidadorUtil.isIdValido(idDoc)) {
            throw new IllegalArgumentException("IDs de usuário ou documento inválidos.");
        }

        boolean sucesso = documentoDAO.removerFavorito(idUsuario, idDoc);
        if (!sucesso) {
            throw new IllegalStateException("Não foi possível remover o documento dos favoritos. Ele pode já ter sido removido.");
        }
    }
}
