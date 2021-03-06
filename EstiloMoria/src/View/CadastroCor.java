/*
 * Tecido.java
 *
 * Created on 1 de Setembro de 2011, 21:03
 */

package View;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.MaskFormatter;
import Controle.*;
import Modelo.*;
/**
 *
 * @author  Familia-Silva
 */
public class CadastroCor extends javax.swing.JFrame {
    
    private conexao con_cor;
    private int numeroRegistros = 0;
    /** Creates new form Tecido */
    public CadastroCor() throws SQLException {
        initComponents();
    
    con_cor= new conexao();
        con_cor.conecta();
        con_cor.executeSQL("select MAX (cd_cor+1) AS Codigo from cor");
        con_cor.resultset.next();
        txtcodigo.setText(con_cor.resultset.getString("Codigo"));
         setResizable(false);
        txtcor.requestFocus();
    enter();
    Runtime rt = Runtime.getRuntime();
		int MB = 1048576; // total de bytes em 1MB

		double total = rt.maxMemory() / MB;
		double inicio = total - (carregarMemoria()/MB);
		
		//System.out.println(total);
		//System.out.println(inicio);
		
		// Metodo de limpaza
		rt.runFinalization();
		rt.gc();
		
		double fim = total - rt.freeMemory()/ MB;
		System.out.println("In�cio = " +inicio + "Fim = " + fim);
    }
    public void enter(){
// Enter simula tecla Tab
        HashSet conj = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    } 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtcodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtcor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btncadastrar = new java.awt.Button();
        btnlimpar = new java.awt.Button();
        btnsair = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Cor");
        getContentPane().setLayout(null);

        txtcodigo.setEnabled(false);
        getContentPane().add(txtcodigo);
        txtcodigo.setBounds(10, 30, 40, 20);

        jLabel1.setText("C�d.");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 30, 14);
        getContentPane().add(txtcor);
        txtcor.setBounds(60, 30, 210, 20);

        jLabel2.setText("Cor");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 10, 60, 14);

        btncadastrar.setFont(new java.awt.Font("Dialog", 1, 12));
        btncadastrar.setLabel("Cadastrar");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btncadastrar);
        btncadastrar.setBounds(10, 70, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(100, 70, 80, 30);

        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });
        getContentPane().add(btnsair);
        btnsair.setBounds(190, 70, 80, 30);

        getAccessibleContext().setAccessibleName("CadastroTecido");

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-285)/2, (screenSize.height-135)/2, 285, 135);
    }// </editor-fold>//GEN-END:initComponents
 public static long carregarMemoria(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<1000000; i++){
		list.add(i);	
		}
		return Runtime.getRuntime().freeMemory();
	}   
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed

    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
        txtcor.setText("");
       
    }//GEN-LAST:event_btnlimparActionPerformed

    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
// TODO add your handling code here:
        
        numeroRegistros= Integer.parseInt(txtcodigo.getText());
        try {
            
            if(txtcor.getText().equals("")){
                
                JOptionPane.showMessageDialog(null,"Digite uma cor!");
                txtcor.requestFocus();
            } else{
                
                String sqlinsertcor ="insert into cor (cd_cor, nm_cor) values ('"+Integer.parseInt(txtcodigo.getText())+"','"+
                        txtcor.getText().toUpperCase()+"')";
                
                            
                
                // JOptionPane.showMessageDialog(null,"sql = "+sqlinsertcor);
                con_cor.statement.executeUpdate(sqlinsertcor);
                
                JOptionPane.showMessageDialog(null,"Grava��o realizado com sucesso!");
                numeroRegistros++;
                txtcodigo.setText(String.valueOf(numeroRegistros));
                txtcor.setText("");
                
                
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro a tentar Gravar o registro..."+erro);
            
        }
    }//GEN-LAST:event_btncadastrarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroCor().setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btncadastrar;
    private java.awt.Button btnlimpar;
    private java.awt.Button btnsair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcor;
    // End of variables declaration//GEN-END:variables
    
}
