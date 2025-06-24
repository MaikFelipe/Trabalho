package view;

import dao.BancoDados;
import model.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaListagemGeral extends JFrame {

    private JTable tabela;
    private JButton btnVoltar;

    public TelaListagemGeral() {
        setTitle("LISTAGEM DE TODOS OS ALUNOS");
        setSize(950, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("LISTA COMPLETA DE ALUNOS");
        lblTitulo.setBounds(370, 10, 300, 25);
        add(lblTitulo);

        tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 900, 250);
        add(scroll);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(400, 320, 120, 30);
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