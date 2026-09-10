package docbank.core.domain;

public class Usuario {

    private final int id;
    private final String nome;
    private final String email;
    private final String cargo;

    public Usuario(int id, String nome, String email, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }
}
