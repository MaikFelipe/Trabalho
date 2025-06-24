package view;

import controller.UsuarioController;
import model.usuario;
import javax.swing.*;

public class TelaCadastro extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JComboBox<String> cmbPergunta;
    private JTextField txtResposta;
    private JButton btnCadastrar;

    public TelaCadastro() {
        initComponents();
    }

    private void initComponents() {
        txtUsuario = new JTextField();
        txtSenha = new JPasswordField();
        cmbPergunta = new JComboBox<>(new String[]{
            "Qual o nome da sua mãe?",
            "Qual sua cor favorita?",
            "Qual o nome do seu primeiro animal?"
        });
        txtResposta = new JTextField();
        btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> cadastrar());

        setTitle("Cadastro de Usuário");
        setLayout(new java.awt.GridLayout(5, 1));
        add(txtUsuario);
        add(txtSenha);
        add(cmbPergunta);
        add(txtResposta);
        add(btnCadastrar);

        pack();
        setLocationRelativeTo(null);
    }

    private void cadastrar() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        String pergunta = (String) cmbPergunta.getSelectedItem();
        String resposta = txtResposta.getText();

        UsuarioController controller = new UsuarioController();
        controller.cadastrarUsuario(new usuario(usuario, senha, pergunta, resposta));
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        this.dispose();
    }
}
