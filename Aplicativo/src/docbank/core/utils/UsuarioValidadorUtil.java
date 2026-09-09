package docbank.core.utils;

import java.util.Arrays;
import java.util.List;

public class UsuarioValidadorUtil {

    private static final List<String> CARGOS_PERMITIDOS = Arrays.asList("Usuário", "Moderador", "Administrador", "Suspenso");
    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static boolean isPreenchido(String email) {
        return email != null && !email.trim().isEmpty();
    }

    public static boolean isSenhaPreenchida(char[] senha) {
        return senha != null && senha.length > 0;
    }

    public static boolean isEmailValido(String email) {
        if (!isPreenchido(email)) {
            return false;
        }
        return email.trim().matches(REGEX_EMAIL);
    }

    public static boolean isSenhaTemTamanhoMinimo(char[] senha) {
        return isSenhaPreenchida(senha) && senha.length >= 8;
    }

    public static boolean isIdValido(int id) {
        return id > 0;
    }

    public static boolean isCargoPermitido(String cargo) {
        if (!isPreenchido(cargo)) {
            return false;
        }
        return CARGOS_PERMITIDOS.contains(cargo.trim());
    }

    public static List<String> getCargosPermitidos() {
        return CARGOS_PERMITIDOS;
    }
}
