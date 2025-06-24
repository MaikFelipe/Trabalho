package view;

import dao.BancoDados;
import model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaListagemAlunosGeral extends JFrame {

    private JTable tabela;
    private JButton btnVoltar;

    public TelaListagemAlunosGeral() {
        setTitle("Lista Completa de Alunos");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("LISTA DE TODOS OS ALUNOS");
        lblTitulo.setBounds(350, 10, 300, 25);
        add(lblTitulo);

        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 850, 250);
        add(scroll);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(400, 320, 100, 30);
        add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLogin().setVisible(true);
                dispose();
            }
        });

        carregarTabela();
    }

    private void carregarTabela() {
        String[] colunas = {"Nome Completo", "Endereço", "Telefone", "Mãe", "CPF", "E-mail", "Turma", "Turno", "Idade", "Login"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        ArrayList<Aluno> lista = BancoDados.listarTodos();

        for (Aluno a : lista) {
            modelo.addRow(new Object[]{
                    a.getNomeCompleto(),
                    a.getEndereco(),
                    a.getTelefone(),
                    a.getNomeMae(),
                    a.getCpf(),
                    a.getEmail(),
                    a.getTurma(),
                    a.getTurno(),
                    a.getIdade(),
                    a.getLogin()
            });
        }
        tabela.setModel(modelo);
    }
}
