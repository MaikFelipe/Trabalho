package view;

import model.Aluno;

import javax.swing.*;
import java.awt.event.*;

public class PortalAluno extends JFrame {

    public PortalAluno(Aluno aluno) {
        setTitle("Portal do Aluno");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBounds(20, 20, 350, 230);
        area.setText(
                "Nome completo: " + aluno.getNomeCompleto() + "\n" +
                "Endereço: " + aluno.getEndereco() + "\n" +
                "Telefone: " + aluno.getTelefone() + "\n" +
                "Nome da mãe: " + aluno.getNomeMae() + "\n" +
                "CPF: " + aluno.getCpf() + "\n" +
                "E-mail: " + aluno.getEmail() + "\n" +
                "Turma: " + aluno.getTurma() + "\n" +
                "Turno: " + aluno.getTurno() + "\n" +
                "Idade: " + aluno.getIdade()
        );

        add(area);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, 270, 100, 30);
        add(btnVoltar);

        btnVoltar.addActionListener(e -> {
            new TelaLogin();
            dispose();
        });

        setVisible(true);
    }
}
