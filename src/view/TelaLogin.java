package view;

import dao.BancoDados;
import model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JTextField txtPalavraChave;
    private JButton btnEntrar;

    public TelaLogin() {
        setTitle("GESTÃO ESCOLAR");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTÃO ESCOLAR");
        lblTitulo.setBounds(140, 20, 200, 30);
        add(lblTitulo);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(50, 70, 100, 25);
        add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(150, 70, 150, 25);
        add(txtLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 110, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 110, 150, 25);
        add(txtSenha);

        JLabel lblPalavraChave = new JLabel("Palavra-chave:");
        lblPalavraChave.setBounds(50, 150, 100, 25);
        add(lblPalavraChave);

        txtPalavraChave = new JTextField();
        txtPalavraChave.setBounds(150, 150, 150, 25);
        add(txtPalavraChave);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(150, 200, 100, 30);
        add(btnEntrar);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
    }

    private void realizarLogin() {
        String login = txtLogin.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();
        String palavraChave = txtPalavraChave.getText().trim();

        if (login.equalsIgnoreCase("Geral") && senha.equals("Todos") && palavraChave.equals("segurança")) {
            new TelaListagemAlunosGeral().setVisible(true);
            this.dispose();
            return;
        }

        Aluno aluno = BancoDados.buscarPorLogin(login);

        if (aluno != null && aluno.getSenha().equals(senha) && aluno.getPalavraChave().equals(palavraChave)) {
            new TelaPortalAluno(aluno).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Login ou dados inválidos!");
        }
    }
}