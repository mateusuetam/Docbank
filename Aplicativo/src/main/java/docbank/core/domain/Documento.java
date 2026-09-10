package docbank.core.domain;

public class Documento {

    private int id;
    private String titulo;
    private String topico;
    private String linkOuArquivo;

    public Documento(int id, String titulo, String topico, String linkOuArquivo) {
        this.id = id;
        this.titulo = titulo;
        this.topico = topico;
        this.linkOuArquivo = linkOuArquivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getLinkOuArquivo() {
        return linkOuArquivo;
    }

    public void setLinkOuArquivo(String linkOuArquivo) {
        this.linkOuArquivo = linkOuArquivo;
    }
}
