package dao;

import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class BancoDados {

    public static void gerarAlunosSeNaoExistirem() {
        try (Connection con = Conexao.conectar();
             Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM alunos");
            rs.next();
            if (rs.getInt(1) == 0) {
                gerarAlunos();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void gerarAlunos() {
        String[] nomes = {"Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Helena", "Igor", "Julia",
                          "Kaio", "Larissa", "Marcos", "Natalia", "Otavio", "Paula", "Rafael", "Sara", "Thiago", "Vanessa",
                          "Wesley", "Xuxa", "Yuri", "Zuleika", "Felipe", "Luana", "Pedro", "Camila", "Lucas", "Beatriz"};
        Random rand = new Random();

        try (Connection con = Conexao.conectar()) {
            String sql = "INSERT INTO alunos (nome_completo, endereco, telefone, nome_mae, cpf, email, turma, turno, idade, login, senha, palavra_chave) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < 90; i++) {
                String nomeBase = nomes[i % nomes.length];
                String nomeCompleto = nomeBase + " Silva " + (i + 1);
                String endereco = "Rua " + (i + 1);
                String telefone = "(11) 9" + (rand.nextInt(90000000) + 10000000);
                String nomeMae = "Mãe de " + nomeCompleto;
                String cpf = String.format("%011d", Math.abs(rand.nextLong()) % 100000000000L);
                String email = nomeCompleto.replace(" ", ".").toLowerCase() + "@escola.com";
                String turma, turno;
                int idade;

                if (i < 30) {
                    turma = "1º Ano";
                    turno = "Manhã";
                    idade = 16 + rand.nextInt(4);
                } else if (i < 60) {
                    turma = "2º Ano";
                    turno = "Tarde";
                    idade = 20 + rand.nextInt(4);
                } else {
                    turma = "3º Ano";
                    turno = "Noite";
                    idade = 24 + rand.nextInt(3);
                }

                String login = nomeBase.toLowerCase() + (i + 1); // Exemplo: ana1, bruno2...
                String senha = "12345";
                String palavraChave = "minhaseguranca";

                ps.setString(1, nomeCompleto);
                ps.setString(2, endereco);
                ps.setString(3, telefone);
                ps.setString(4, nomeMae);
                ps.setString(5, cpf);
                ps.setString(6, email);
                ps.setString(7, turma);
                ps.setString(8, turno);
                ps.setInt(9, idade);
                ps.setString(10, login);
                ps.setString(11, senha);
                ps.setString(12, palavraChave);

                ps.executeUpdate();
            }

            System.out.println("Alunos gerados com sucesso no banco de dados!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Aluno> listarTodos() {
        ArrayList<Aluno> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM alunos")) {

            while (rs.next()) {
                lista.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("nome_mae"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("turma"),
                        rs.getString("turno"),
                        rs.getInt("idade"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("palavra_chave")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static Aluno buscarPorLogin(String login) {
        String sql = "SELECT * FROM alunos WHERE login = ?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("nome_mae"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("turma"),
                        rs.getString("turno"),
                        rs.getInt("idade"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("palavra_chave")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Iterable<Aluno> getListaAlunos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}