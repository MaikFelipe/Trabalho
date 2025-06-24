package controller;

import dao.Conexao;
import dao.Conexao;
import model.usuario;
import java.sql.*;

public class UsuarioController {

    public boolean validarLogin(usuario usuario) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM login WHERE usuario = ? AND senha = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getSenha());
            
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public String buscarPergunta(String usuario) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT pergunta_secreta FROM login WHERE usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("pergunta_secreta");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public boolean verificarResposta(String usuario, String resposta) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "SELECT * FROM login WHERE usuario = ? AND resposta_secreta = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, resposta);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void atualizarSenha(String usuario, String novaSenha) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "UPDATE login SET senha = ? WHERE usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, novaSenha);
            ps.setString(2, usuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void cadastrarUsuario(usuario usuario) {
        try {
            Connection conexao = Conexao.getConexao();
            String sql = "INSERT INTO login (usuario, senha, pergunta_secreta, resposta_secreta) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getPergunta());
            ps.setString(4, usuario.getResposta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}