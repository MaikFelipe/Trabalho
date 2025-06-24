package gerador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class GerarAlunos {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/escola";
        String usuarioBD = "root";
        String senhaBD = "";

        String[] nomesExemplo = {
            "Carlos Silva", "Ana Souza", "Marcos Pereira", 
            "Juliana Costa", "Lucas Almeida",
            "Fernanda Rocha", "Bruno Santos", 
            "Amanda Lima", "Pedro Gomes", "Leticia Carvalho"
        };

        String[] nomesMae = {"Maria", "Josefa", "Lucia", "Camila", "Patricia", "Juliana"};
        String[] ruas = {"Rua A", "Rua B", "Av. Central", "Rua das Flores", "Rua do Sol"};

        Random rand = new Random();

        try {
            Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD);

            for (int turma = 1; turma <= 3; turma++) {
                String turmaTexto = turma + "º Ano";
                String turno = turma == 1 ? "Manhã" : turma == 2 ? "Tarde" : "Noite";
                int idadeMin = turma == 1 ? 16 : turma == 2 ? 20 : 24;
                int idadeMax = turma == 1 ? 19 : turma == 2 ? 23 : 26;

                for (int i = 0; i < 30; i++) {
                    String nomeCompleto = nomesExemplo[rand.nextInt(nomesExemplo.length)] + " " + (rand.nextInt(899) + 100);
                    String primeiroNome = nomeCompleto.split(" ")[0] + i; 
                    String endereco = ruas[rand.nextInt(ruas.length)] + ", " + (rand.nextInt(999) + 1);
                    String telefone = "(91) 9" + (rand.nextInt(90000000) + 10000000);
                    String nomeMae = nomesMae[rand.nextInt(nomesMae.length)];
                    String cpf = (rand.nextInt(900000000) + 100000000) + "";
                    String email = primeiroNome.toLowerCase() + "@escola.com";
                    int idade = rand.nextInt(idadeMax - idadeMin + 1) + idadeMin;

                    String sql = "INSERT INTO alunos (nome_completo, endereco, telefone, nome_mae, cpf, email, turma, turno, idade, login, senha, palavra_chave) "
                               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement ps = conexao.prepareStatement(sql);
                    ps.setString(1, nomeCompleto);
                    ps.setString(2, endereco);
                    ps.setString(3, telefone);
                    ps.setString(4, nomeMae);
                    ps.setString(5, cpf);
                    ps.setString(6, email);
                    ps.setString(7, turmaTexto);
                    ps.setString(8, turno);
                    ps.setInt(9, idade);
                    ps.setString(10, primeiroNome);
                    ps.setString(11, "12345");
                    ps.setString(12, "minhaseguranca");

                    ps.executeUpdate();

                    System.out.println("Aluno gerado: Login = " + primeiroNome + " | Senha = 12345 | Turma = " + turmaTexto + " | Turno = " + turno);
                }
            }

            System.out.println("Todos os alunos foram inseridos com sucesso!");
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
