/*
 * ConsultaMostruario.java
 *
 * Created on 8 de Setembro de 2011, 10:08
 */

package View;

import Relatorio.*;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import Controle.conexao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author  Familia-Silva
 */
public class ConsultaMostruario extends javax.swing.JFrame {
    private int numeroRegistros=0;
    private MaskFormatter FormatoData;
    private conexao con_produtoacbado1, con_produto, con_cor;
    /** Creates new form ConsultaMostruario */
    public ConsultaMostruario() {
        initComponents();
        preencheTabela();
         setResizable(false);
        con_produto=new conexao();
        con_produto.conecta();
        
        con_cor=new conexao();
        con_cor.conecta();
        
        con_produtoacbado1 = new conexao();
        con_produtoacbado1.conecta();
        
        
        con_cor.executeSQL("select * from cor order by nm_cor");
        con_produto.executeSQL("select * from Produto order by nm_produto");
        
        
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
		System.out.println("Início = " +inicio + "Fim = " + fim);
    }
    public void enter(){
// Enter simula tecla Tab
        HashSet conj = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }
    public void preencheTabela(){
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(260);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(72);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(72);
        
        
        modelo.setNumRows(0);
        numeroRegistros=1;
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        txtmostruario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdevolucao = new JFormattedTextField (FormatoData);
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnconsulta = new javax.swing.JButton();
        btnlimpa = new javax.swing.JButton();
        btnsair = new javax.swing.JButton();
        btnsalva = new javax.swing.JButton();
        btndeleta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtvendedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Mostru\u00e1rio");
        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("N\u00ba Mostru\u00e1rio"));
        txtmostruario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmostruarioKeyTyped(evt);
            }
        });

        jPanel1.add(txtmostruario);
        txtmostruario.setBounds(20, 40, 50, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 90, 70);

        txtobs.setColumns(20);
        txtobs.setRows(5);
        jScrollPane1.setViewportView(txtobs);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 410, 110);

        jLabel1.setText("Observa\u00e7\u00f5es");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 90, 90, 14);

        getContentPane().add(ftxtdevolucao);
        ftxtdevolucao.setBounds(430, 200, 80, 20);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Produto", "Cor", "P", "M", "G", "GG", "EG", "EXG", "P. Unit.", "Envio", "Devolução"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });

        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 230, 730, 130);

        btnconsulta.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnconsulta.setText("Consulta");
        btnconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultaActionPerformed(evt);
            }
        });

        getContentPane().add(btnconsulta);
        btnconsulta.setBounds(10, 370, 90, 30);

        btnlimpa.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnlimpa.setText("Limpar");
        btnlimpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpaActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpa);
        btnlimpa.setBounds(310, 370, 90, 30);

        btnsair.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnsair.setText("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(410, 370, 90, 30);

        btnsalva.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnsalva.setText("Salva");
        btnsalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalvaActionPerformed(evt);
            }
        });

        getContentPane().add(btnsalva);
        btnsalva.setBounds(110, 370, 90, 30);

        btndeleta.setFont(new java.awt.Font("Tahoma", 1, 12));
        btndeleta.setText("Deletar");
        btndeleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletaActionPerformed(evt);
            }
        });

        getContentPane().add(btndeleta);
        btndeleta.setBounds(210, 370, 90, 30);

        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Vendedor"));
        txtvendedor.setEditable(false);
        jPanel2.add(txtvendedor);
        txtvendedor.setBounds(10, 40, 390, 20);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(110, 10, 410, 70);

        jLabel2.setText("Devolu\u00e7\u00e3o ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(430, 180, 70, 14);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-755)/2, (screenSize.height-450)/2, 755, 450);
    }// </editor-fold>//GEN-END:initComponents

    private void txtmostruarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmostruarioKeyTyped
// TODO add your handling code here:
          if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtmostruarioKeyTyped
    
    private void btndeletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletaActionPerformed
// TODO add your handling code here:
        try {
            String nome = "Deletar o mostruário: "+txtmostruario.getText()+"?";
            int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Exlusão", JOptionPane.YES_NO_OPTION);
            if(opcao_escolha==JOptionPane.YES_OPTION){
                String sql = "DELETE from Mostruario1 where cd_mostruario="+Integer.parseInt(txtmostruario.getText());
                int conseguiu_excluir = con_produtoacbado1.statement.executeUpdate(sql);
                
                
                if(conseguiu_excluir==1){
                    JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!!");
                    
                    // con_produtoacbado1.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
                    con_produtoacbado1.executeSQL("Select p.nm_produto,c.nm_cor, f.qt_p,f.qt_m,f.qt_g,f.qt_gg,f.qt_eg,f.qt_exg,f.vl_unitario,f.ds_observacao,f.dt_envio_mostruario,f.dt_devolucao_mostruario FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) left join produto p on (f.cd_produtoacabado=p.cd_produto) left join cor c on (f.cd_cor=c.cd_cor) order by cd_mostruario");
                    con_produtoacbado1.resultset.first();
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    numeroRegistros = 1;
                    txtvendedor.setText("");
                    txtmostruario.setText("");
                    txtobs.setText("");
                    ftxtdevolucao.setText("");
                    
                    txtobs.setBackground(getBackground().white);
                    ftxtdevolucao.setBackground(getBackground().white);
                    txtmostruario.requestFocus();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
            
        }
    }//GEN-LAST:event_btndeletaActionPerformed
   public static long carregarMemoria(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<1000000; i++){
		list.add(i);	
		}
		return Runtime.getRuntime().freeMemory();
	}     
    private void btnsalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalvaActionPerformed
// TODO add your handling code here:
        
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        
        
        try {
            
            data = (Date) fmt.parse(ftxtdevolucao.getText());
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
            
            
        }
        try{
            if(!txtmostruario.getText().equals("")){
                
                
                String sql4 = "UPDATE Mostruario1 SET ds_observacao ='"+txtobs.getText().toUpperCase()+"',"+
                        "dt_devolucao_mostruario='"+data+"' where cd_mostruario ='"+txtmostruario.getText()+"'";
                con_produtoacbado1.statement.executeUpdate(sql4);
                con_produtoacbado1.executeSQL("Select p.nm_produto,c.nm_cor, f.qt_p,f.qt_m,f.qt_g,f.qt_gg,f.qt_eg,f.qt_exg,f.vl_unitario,f.ds_observacao,f.dt_envio_mostruario,f.dt_devolucao_mostruario FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) left join produto p on (f.cd_produtoacabado=p.cd_produto) left join cor c on (f.cd_cor=c.cd_cor) where cd_mostruario='"
                        + txtmostruario.getText() + "'");
                con_produtoacbado1.resultset.next();
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!!!");
                
                String nome = "Deseja imprimir o mostruário: "+Integer.parseInt(txtmostruario.getText())+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    new Rel_Mostruario_Consulta();
                    
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    numeroRegistros = 1;
                    txtvendedor.setText("");
                    txtmostruario.setText("");
                    txtobs.setText("");
                    ftxtdevolucao.setText("");
                    txtmostruario.requestFocus();
                    txtobs.setBackground(getBackground().white);
                    ftxtdevolucao.setBackground(getBackground().white);
                } else{
                    jTable1.updateUI();
                    numeroRegistros = 1;
                    txtvendedor.setText("");
                    txtmostruario.setText("");
                    txtobs.setText("");
                    ftxtdevolucao.setText("");
                    txtmostruario.requestFocus();
                    txtobs.setBackground(getBackground().white);
                    ftxtdevolucao.setBackground(getBackground().white);
                }
                
            }
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        
    }//GEN-LAST:event_btnsalvaActionPerformed
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
     public static String converterDoubleString(double precoDouble) {  
   /*Transformando um double em 2 casas decimais*/  
   DecimalFormat fmt = new DecimalFormat("0.00");    //limita o número de casas decimais     
   String string = fmt.format(precoDouble);  
   String[] part = string.split("[,]");  
   String preco = part[0]+"."+part[1];  
   return preco;  
}  
    private void btnlimpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpaActionPerformed
// TODO add your handling code here:
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        numeroRegistros = 1;
        txtvendedor.setText("");
        txtmostruario.setText("");
        txtobs.setText("");
        ftxtdevolucao.setText("");
        txtmostruario.requestFocus();
        txtobs.setBackground(getBackground().white);
        ftxtdevolucao.setBackground(getBackground().white);
    }//GEN-LAST:event_btnlimpaActionPerformed
    public void preenchejTablaMostruario(){
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
       
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(260);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(72);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(72);
        
        
        // modelo.setNumRows(0);
        // numeroRegistros=1;
        con_produtoacbado1 = new conexao();
        con_produtoacbado1.conecta();
        
        
        
        try {
            con_produtoacbado1 = new conexao();
            con_produtoacbado1.conecta();
            con_produtoacbado1.executeSQL("Select p.nm_produto,c.nm_cor, f.qt_p,f.qt_m,f.qt_g,f.qt_gg,f.qt_eg,f.qt_exg,f.vl_unitario,f.ds_observacao,f.dt_envio_mostruario,f.dt_devolucao_mostruario FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) left join produto p on (f.cd_produtoacabado=p.cd_produto) left join cor c on (f.cd_cor=c.cd_cor) where cd_mostruario='"
                    + txtmostruario.getText() + "'");
            
            while(con_produtoacbado1.resultset.next())
                
                
                modelo.addRow(new Object []{numeroRegistros++,con_produtoacbado1.resultset.getString("nm_produto"), con_produtoacbado1.resultset.getString("nm_cor"),con_produtoacbado1.resultset.getString("qt_p"),con_produtoacbado1.resultset.getString("qt_m"),con_produtoacbado1.resultset.getString("qt_g"),con_produtoacbado1.resultset.getString("qt_gg"),
                con_produtoacbado1.resultset.getString("qt_eg"), con_produtoacbado1.resultset.getString("qt_exg"),converterDoubleString(con_produtoacbado1.resultset.getDouble("vl_unitario")), new SimpleDateFormat("dd/MM/yyyy").format(con_produtoacbado1.resultset.getDate("dt_envio_mostruario")), new SimpleDateFormat("dd/MM/yyyy").format(con_produtoacbado1.resultset.getDate("dt_devolucao_mostruario"))});
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
            
            
        }
        try {
            con_produtoacbado1 = new conexao();
            con_produtoacbado1.conecta();
            con_produtoacbado1.executeSQL("Select f.ds_observacao,t.nm_vendedor FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) where cd_mostruario='"
                    + txtmostruario.getText() + "'");
            
            while(con_produtoacbado1.resultset.next())
                txtobs.setText(con_produtoacbado1.resultset.getString("ds_observacao"));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
            
            
        }
        try {
            con_produtoacbado1 = new conexao();
            con_produtoacbado1.conecta();
            con_produtoacbado1.executeSQL("Select t.nm_vendedor FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) where cd_mostruario='"
                    + txtmostruario.getText() + "'");
            
            while(con_produtoacbado1.resultset.next())
                
                txtvendedor.setText(con_produtoacbado1.resultset.getString("nm_vendedor"));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
            
            
        }
        try {
            con_produtoacbado1 = new conexao();
            con_produtoacbado1.conecta();
            con_produtoacbado1.executeSQL("Select p.nm_produto,c.nm_cor, f.qt_p,f.qt_m,f.qt_g,f.qt_gg,f.qt_eg,f.qt_exg,f.vl_unitario,f.ds_observacao,f.dt_envio_mostruario,f.dt_devolucao_mostruario FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) left join produto p on (f.cd_produtoacabado=p.cd_produto) left join cor c on (f.cd_cor=c.cd_cor) where cd_mostruario='"
                    + txtmostruario.getText() + "'");
            
            while(con_produtoacbado1.resultset.next())
                
                ftxtdevolucao.setText(new SimpleDateFormat("dd/MM/yyyy").format(con_produtoacbado1.resultset.getDate("dt_devolucao_mostruario")));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
            
            
        }
    }
    
    private void btnconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultaActionPerformed
// TODO add your handling code here:
        
        if(txtmostruario.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione o Mostruário!");
            txtmostruario.requestFocus();
        }
        if(!txtmostruario.getText().equals("")){
            preenchejTablaMostruario();
            txtobs.setBackground(getBackground().yellow);
            ftxtdevolucao.setBackground(getBackground().yellow);
        }
        if(ftxtdevolucao.getText().equals("  /  /    ")){
            txtobs.setBackground(getBackground().WHITE);
            ftxtdevolucao.setBackground(getBackground().WHITE);
            JOptionPane.showMessageDialog(null,
                    "Mostruário não Encontrado!!!");
            txtmostruario.requestFocus();
            
        }
        
    }//GEN-LAST:event_btnconsultaActionPerformed
    
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaMostruario().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnconsulta;
    private javax.swing.JButton btndeleta;
    private javax.swing.JButton btnlimpa;
    private javax.swing.JButton btnsair;
    private javax.swing.JButton btnsalva;
    private javax.swing.JFormattedTextField ftxtdevolucao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField txtmostruario;
    private javax.swing.JTextArea txtobs;
    public static javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
    
}
