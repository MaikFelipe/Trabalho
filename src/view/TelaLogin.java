package view;

import dao.BancoDados;
import model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JTextField txtPalavraChave;

    public TelaLogin() {
        setTitle("GESTÃO ESCOLAR");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTÃO ESCOLAR", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(50, 10, 300, 30);
        add(lblTitulo);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(50, 60, 100, 25);
        add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(150, 60, 180, 25);
        add(txtLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 100, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 100, 180, 25);
        add(txtSenha);

        JLabel lblPalavraChave = new JLabel("Palavra-chave:");
        lblPalavraChave.setBounds(50, 140, 100, 25);
        add(lblPalavraChave);

        txtPalavraChave = new JTextField();
        txtPalavraChave.setBounds(150, 140, 180, 25);
        add(txtPalavraChave);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(80, 190, 100, 30);
        add(btnEntrar);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(200, 190, 100, 30);
        add(btnSair);

        JButton btnEsqueceuSenha = new JButton("Esqueceu a Senha?");
        btnEsqueceuSenha.setBounds(110, 240, 160, 25);
        add(btnEsqueceuSenha);

        // Ação do botão entrar
        btnEntrar.addActionListener(e -> realizarLogin());

        // Ação do botão sair
        btnSair.addActionListener(e -> System.exit(0));

        // Ação do botão Esqueceu a Senha
        btnEsqueceuSenha.addActionListener(e -> exibirRecuperacaoSenha());

        setVisible(true);
    }

    private void realizarLogin() {
        String loginDigitado = txtLogin.getText().trim().toLowerCase();
        String senhaDigitada = new String(txtSenha.getPassword()).trim();
        String palavraChaveDigitada = txtPalavraChave.getText().trim();

        if (loginDigitado.equals("geral") && senhaDigitada.equals("Todos") && palavraChaveDigitada.equals("segurança")) {
            new TelaListagemAlunosGeral().setVisible(true);
            dispose();
        } else {
            Aluno aluno = BancoDados.buscarPorLogin(loginDigitado);
            if (aluno != null && senhaDigitada.equals(aluno.getSenha()) && palavraChaveDigitada.equals(aluno.getPalavraChave())) {
                new TelaPortalAluno(aluno).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login ou dados inválidos");
            }
        }
    }

    private void exibirRecuperacaoSenha() {
        String login = JOptionPane.showInputDialog("Digite seu login:");

        if (login != null && !login.trim().isEmpty()) {
            Aluno aluno = BancoDados.buscarPorLogin(login.toLowerCase());
            if (aluno != null) {
                JOptionPane.showMessageDialog(null, "Sua senha é: " + aluno.getSenha() + "\nSua palavra-chave é: " + aluno.getPalavraChave());
            } else {
                JOptionPane.showMessageDialog(null, "Login não encontrado.");
            }
        }
    }
}