package docbank.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    public static String hashSenha(char[] senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] senhaBytes = new String(senha).getBytes(java.nio.charset.StandardCharsets.UTF_8);
            byte[] hashBytes = md.digest(senhaBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao processar criptografia da senha", e);
        }
    }
}
