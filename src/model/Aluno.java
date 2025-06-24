package model;

public class Aluno {

    private int id;
    private String nomeCompleto;
    private String endereco;
    private String telefone;
    private String nomeMae;
    private String cpf;
    private String email;
    private String turma;
    private String turno;
    private int idade;
    private String login;
    private String senha;
    private String palavraChave;

    public Aluno(int id, String nomeCompleto, String endereco, String telefone, String nomeMae,
                 String cpf, String email, String turma, String turno, int idade,
                 String login, String senha, String palavraChave) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
        this.telefone = telefone;
        this.nomeMae = nomeMae;
        this.cpf = cpf;
        this.email = email;
        this.turma = turma;
        this.turno = turno;
        this.idade = idade;
        this.login = login;
        this.senha = senha;
        this.palavraChave = palavraChave;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTurma() {
        return turma;
    }

    public String getTurno() {
        return turno;
    }

    public int getIdade() {
        return idade;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getPalavraChave() {
        return palavraChave;
    }
}