package view;

import model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPortalAluno extends JFrame {

    private JLabel lblBemVindo;
    private JTextArea txtDados;
    private JButton btnVoltar;

    public TelaPortalAluno(Aluno aluno) {
        setTitle("PORTAL DO ALUNO");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        lblBemVindo = new JLabel("Bem-vindo, " + aluno.getNomeCompleto());
        lblBemVindo.setBounds(50, 20, 300, 25);
        add(lblBemVindo);

        txtDados = new JTextArea();
        txtDados.setBounds(50, 60, 300, 200);
        txtDados.setEditable(false);
        add(txtDados);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, 280, 100, 30);
        add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLogin().setVisible(true);
                dispose();
            }
        });

        exibirDados(aluno);
    }

    private void exibirDados(Aluno aluno) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(aluno.getNomeCompleto()).append("\n");
        sb.append("Endereço: ").append(aluno.getEndereco()).append("\n");
        sb.append("Telefone: ").append(aluno.getTelefone()).append("\n");
        sb.append("Mãe: ").append(aluno.getNomeMae()).append("\n");
        sb.append("CPF: ").append(aluno.getCpf()).append("\n");
        sb.append("Email: ").append(aluno.getEmail()).append("\n");
        sb.append("Turma: ").append(aluno.getTurma()).append("\n");
        sb.append("Turno: ").append(aluno.getTurno()).append("\n");
        sb.append("Idade: ").append(aluno.getIdade()).append("\n");

        txtDados.setText(sb.toString());
    }
}