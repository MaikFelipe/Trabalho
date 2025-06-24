package model;

public class usuario {

    private String usuario;
    private String senha;
    private String pergunta;
    private String resposta;

    public usuario(String usuario, String senha, String pergunta, String resposta) {
        this.usuario = usuario;
        this.senha = senha;
        this.pergunta = pergunta;
        this.resposta = resposta;
    }

    public usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }
    public String getPergunta() { return pergunta; }
    public String getResposta() { return resposta; }
}