package docbank.core.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioValidadorUtilTest {

    @Test
    public void testIsPreenchido() {
        assertTrue(UsuarioValidadorUtil.isPreenchido("João"));
        assertTrue(UsuarioValidadorUtil.isPreenchido("  Maria  "));
        assertFalse(UsuarioValidadorUtil.isPreenchido(""));
        assertFalse(UsuarioValidadorUtil.isPreenchido("    "));
        assertFalse(UsuarioValidadorUtil.isPreenchido(null));
    }

    @Test
    public void testIsEmailValido() {
        assertTrue(UsuarioValidadorUtil.isEmailValido("usuario@docbank.com"));
        assertTrue(UsuarioValidadorUtil.isEmailValido("nome.sobrenome@dominio.com.br"));
        assertFalse(UsuarioValidadorUtil.isEmailValido("usuario_sem_arroba.com"));
        assertFalse(UsuarioValidadorUtil.isEmailValido("@dominio.com"));
        assertFalse(UsuarioValidadorUtil.isEmailValido("usuario@dominio"));
        assertFalse(UsuarioValidadorUtil.isEmailValido("   "));
        assertFalse(UsuarioValidadorUtil.isEmailValido(null));
    }

    @Test
    public void testIsSenhaPreenchida() {
        assertTrue(UsuarioValidadorUtil.isSenhaPreenchida(new char[]{'1', '2', '3'}));
        assertFalse(UsuarioValidadorUtil.isSenhaPreenchida(new char[]{}));
        assertFalse(UsuarioValidadorUtil.isSenhaPreenchida(null));
    }

    @Test
    public void testIsSenhaTemTamanhoMinimo() {
        assertTrue(UsuarioValidadorUtil.isSenhaTemTamanhoMinimo(new char[]{'1', '2', '3', '4', '5', '6', '7', '8'}));
        assertTrue(UsuarioValidadorUtil.isSenhaTemTamanhoMinimo(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'}));
        assertFalse(UsuarioValidadorUtil.isSenhaTemTamanhoMinimo(new char[]{'1', '2', '3', '4', '5', '6', '7'}));
        assertFalse(UsuarioValidadorUtil.isSenhaTemTamanhoMinimo(new char[]{}));
        assertFalse(UsuarioValidadorUtil.isSenhaTemTamanhoMinimo(null));
    }

    @Test
    public void testIsIdValido() {
        assertTrue(UsuarioValidadorUtil.isIdValido(1));
        assertTrue(UsuarioValidadorUtil.isIdValido(9999));
        assertFalse(UsuarioValidadorUtil.isIdValido(0));
        assertFalse(UsuarioValidadorUtil.isIdValido(-1));
        assertFalse(UsuarioValidadorUtil.isIdValido(-150));
    }

    @Test
    public void testIsCargoPermitido() {
        assertTrue(UsuarioValidadorUtil.isCargoPermitido("Usuário"));
        assertTrue(UsuarioValidadorUtil.isCargoPermitido("Moderador"));
        assertTrue(UsuarioValidadorUtil.isCargoPermitido("Administrador"));
        assertTrue(UsuarioValidadorUtil.isCargoPermitido("Suspenso"));
        assertTrue(UsuarioValidadorUtil.isCargoPermitido("  Usuário  "));
        
        assertFalse(UsuarioValidadorUtil.isCargoPermitido("Gerente"));
        assertFalse(UsuarioValidadorUtil.isCargoPermitido("usuario"));
        assertFalse(UsuarioValidadorUtil.isCargoPermitido(""));
        assertFalse(UsuarioValidadorUtil.isCargoPermitido(null));
    }
}