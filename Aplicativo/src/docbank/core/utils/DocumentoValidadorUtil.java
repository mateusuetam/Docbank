package docbank.core.utils;

import docbank.core.domain.Documento;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DocumentoValidadorUtil {

    private static final List<String> STATUS_PERMITIDOS = Arrays.asList("revisao", "aprovado");

    public static boolean isPreenchido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public static boolean isIdValido(int id) {
        return id > 0;
    }

    public static boolean isLinkOuArquivoPresente(String link, File arquivo) {
        return isPreenchido(link) || arquivo != null;
    }

    public static boolean isArquivoPdf(File arquivo) {
        if (arquivo == null) {
            return false;
        }
        return arquivo.getName().toLowerCase().endsWith(".pdf");
    }

    public static boolean ehPdf(String identificador) {
        if (!isPreenchido(identificador)) {
            return false;
        }
        return identificador.toLowerCase().endsWith(".pdf");
    }

    public static boolean isLinkValido(String link) {
        if (!isPreenchido(link)) {
            return false;
        }
        String linkTrim = link.trim().toLowerCase();
        return linkTrim.startsWith("http://") || linkTrim.startsWith("https://");
    }

    public static boolean isDocumentoPreenchido(Documento doc) {
        return doc != null;
    }

    public static boolean isStatusPermitido(String status) {
        if (!isPreenchido(status)) {
            return false;
        }
        return STATUS_PERMITIDOS.contains(status.trim().toLowerCase());
    }
}
