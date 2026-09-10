package docbank.core.utils;

import docbank.core.domain.Documento;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentoValidadorUtilTest {

    @Test
    public void testIsPreenchido() {
        assertTrue(DocumentoValidadorUtil.isPreenchido("Título"));
        assertTrue(DocumentoValidadorUtil.isPreenchido("  Tópico  ")); 
        assertFalse(DocumentoValidadorUtil.isPreenchido(""));
        assertFalse(DocumentoValidadorUtil.isPreenchido("    ")); 
        assertFalse(DocumentoValidadorUtil.isPreenchido(null));
    }

    @Test
    public void testIsIdValido() {
        assertTrue(DocumentoValidadorUtil.isIdValido(1));
        assertTrue(DocumentoValidadorUtil.isIdValido(150));
        assertFalse(DocumentoValidadorUtil.isIdValido(0));
        assertFalse(DocumentoValidadorUtil.isIdValido(-5));
    }

    @Test
    public void testIsLinkOuArquivoPresente() {
        File fakeFile = new File("teste.pdf");
        assertTrue(DocumentoValidadorUtil.isLinkOuArquivoPresente("http://google.com", null));
        assertTrue(DocumentoValidadorUtil.isLinkOuArquivoPresente(null, fakeFile));
        assertTrue(DocumentoValidadorUtil.isLinkOuArquivoPresente("http://google.com", fakeFile));
        assertFalse(DocumentoValidadorUtil.isLinkOuArquivoPresente(null, null));
        assertFalse(DocumentoValidadorUtil.isLinkOuArquivoPresente("   ", null));
    }

    @Test
    public void testIsArquivoPdf() {
        assertTrue(DocumentoValidadorUtil.isArquivoPdf(new File("relatorio.pdf")));
        assertTrue(DocumentoValidadorUtil.isArquivoPdf(new File("RELATORIO.PDF")));
        assertFalse(DocumentoValidadorUtil.isArquivoPdf(new File("imagem.png")));
        assertFalse(DocumentoValidadorUtil.isArquivoPdf(new File("documento.docx")));
        assertFalse(DocumentoValidadorUtil.isArquivoPdf(null));
    }

    @Test
    public void testEhPdfPeloIdentificador() {
        assertTrue(DocumentoValidadorUtil.ehPdf("meu_arquivo.pdf"));
        assertTrue(DocumentoValidadorUtil.ehPdf("C:/pastas/MEU_ARQUIVO.PDF"));
        assertFalse(DocumentoValidadorUtil.ehPdf("http://link.com"));
        assertFalse(DocumentoValidadorUtil.ehPdf("foto.jpg"));
        assertFalse(DocumentoValidadorUtil.ehPdf(null));
    }

    @Test
    public void testIsLinkValido() {
        assertTrue(DocumentoValidadorUtil.isLinkValido("http://site.com"));
        assertTrue(DocumentoValidadorUtil.isLinkValido("https://seguro.com"));
        assertTrue(DocumentoValidadorUtil.isLinkValido("  HTTP://MAIUSCULO.COM  "));
        assertFalse(DocumentoValidadorUtil.isLinkValido("ftp://servidor.com"));
        assertFalse(DocumentoValidadorUtil.isLinkValido("www.site.com"));
        assertFalse(DocumentoValidadorUtil.isLinkValido("site.com"));
        assertFalse(DocumentoValidadorUtil.isLinkValido(null));
    }

    @Test
    public void testIsStatusPermitido() {
        assertTrue(DocumentoValidadorUtil.isStatusPermitido("revisao"));
        assertTrue(DocumentoValidadorUtil.isStatusPermitido("aprovado"));
        assertTrue(DocumentoValidadorUtil.isStatusPermitido("  Aprovado  ")); 
        assertFalse(DocumentoValidadorUtil.isStatusPermitido("excluido"));
        assertFalse(DocumentoValidadorUtil.isStatusPermitido("pendente"));
        assertFalse(DocumentoValidadorUtil.isStatusPermitido(null));
    }

    @Test
    public void testIsDocumentoPreenchido() {
        Documento docMock = new Documento(1, "Título de Teste", "Tópico Fictício", "http://fake.com"); 
        
        assertTrue(DocumentoValidadorUtil.isDocumentoPreenchido(docMock));
        assertFalse(DocumentoValidadorUtil.isDocumentoPreenchido(null));
    }
}