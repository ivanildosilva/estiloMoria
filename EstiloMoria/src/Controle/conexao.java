/*
 * conexao.java
 *
 * Created on 30 de Agosto de 2011, 20:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package Controle;
import java.sql.*;
import javax.swing.*;


/**
 *
 * @author Familia-Silva
 */



public class conexao {
    final private String driver = "org.postgresql.Driver";
 //  final private String url = "jdbc:postgresql://localhost/Moria1";
final private String url = "jdbc:postgresql://FamiliaSilva:5432/Moria1";
    final private String usuario = "teste";
    final private String senha   = "123";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    
    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            // JOptionPane.showMessageDialog(null,"conectou");
        } catch(ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null,"Driver não localizado: "+Driver);
            result = false;
        } catch(SQLException Fonte) {
            JOptionPane.showMessageDialog(null,"Deu erro na conexão "+
                    "com a fonte de dados: "+Fonte);
            result = false;
        }
        return result;
    }
    
    public void desconecta() {
        boolean result = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null,"banco fechado");
        } catch(SQLException fecha) {
            JOptionPane.showMessageDialog(null,"Não foi possivel "+
                    "fechar o banco de dados: "+fecha);
            result = false;
        }
        
    }
    
    public void executeSQL(String sql) {
        try {
            statement = conexao.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        } catch(SQLException sqlex) {
            JOptionPane.showMessageDialog(null,"Não foi possível "+
                    "executar o comando sql,"+sqlex+", o sql passado foi "+sql);
        }
        
    }
    
    public static PreparedStatement prepareStatement(String sql) {
        return null;
    }
    
}
