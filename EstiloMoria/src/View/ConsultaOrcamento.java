/*
 * ConsultaOrcamento.java
 *
 * Created on 11 de Setembro de 2011, 11:10
 */

package View;

import Relatorio.*;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import Controle.*;
import Modelo.*;
import Utilitarios.*;
import org.lavieri.modelutil.cep.WebServiceCep;
import java.sql.SQLException;
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
public class ConsultaOrcamento extends javax.swing.JFrame {
    private MaskFormatter FormatoData, FormatoCpf, FormatoCnpj, FormatoCep, FormatoTel1, FormatoTel2, FormatoTel3, FormatoTel4;
    private conexao con_cliente, con_produto, con_cor, con_email,con_vendedor, con_data, con_orcamento;
    final DefaultTableModel model = new DefaultTableModel();
    private int iniciacombo=0;
    private int numeroRegistros=1;
    data mostra_data;
    /** Creates new form ConsultaOrcamento */
    public ConsultaOrcamento() {
        initComponents();
        con_cliente=new conexao();
        con_cliente.conecta();
        con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
        txtcodigo.setVisible(false);
        txtcodvend.setVisible(false);
        Date data = new Date();
        timer1.start();
        mostra_data = new data();
        mostra_data.le_data();
        mostra_data.le_hora();
         setResizable(false);
        // this.setTitle("Consulta Orçamento                    "+ mostra_data.dia_semana+ ", "+mostra_data.dia + " de " + mostra_data.mes + " de "+ mostra_data.ano +"                     "+ mostra_data.hora );
        preenchejTabla1();
        con_orcamento =new conexao();
        con_orcamento.conecta();
        try {
            
            con_orcamento.executeSQL("select MAX (cd_pedido1+1) AS Codigo from pedido");
            con_orcamento.resultset.next();
            txtpedido.setText(con_orcamento.resultset.getString("Codigo"));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
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
    public void preenchejTabla1(){
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(43);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(46);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(68);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(68);
        
        modelo.setNumRows(0);
        
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        timer1 = new org.netbeans.examples.lib.timerbean.Timer();
        jPanel1 = new javax.swing.JPanel();
        txtorcamento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdataemissao = new JFormattedTextField (FormatoData);
        jLabel10 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdataentrada = new JFormattedTextField (FormatoData);
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtcodvend = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblvalor = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtpedido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnconsulta = new java.awt.Button();
        btngpedido = new java.awt.Button();
        btnlimpar = new java.awt.Button();
        btnimprimir = new java.awt.Button();
        btnsair = new java.awt.Button();

        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Or\u00e7amento");
        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("N\u00ba Or\u00e7amento"));
        txtorcamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtorcamentoKeyTyped(evt);
            }
        });

        jPanel1.add(txtorcamento);
        txtorcamento.setBounds(20, 40, 59, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 100, 70);

        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        txtcodigo.setEnabled(false);
        jPanel2.add(txtcodigo);
        txtcodigo.setBounds(260, 40, 50, 20);

        jLabel2.setText("Nome");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 20, 50, 14);

        txtcliente.setEditable(false);
        jPanel2.add(txtcliente);
        txtcliente.setBounds(10, 40, 320, 20);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(120, 10, 340, 70);

        jPanel3.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        ftxtdataemissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusLost(evt);
            }
        });

        jPanel3.add(ftxtdataemissao);
        ftxtdataemissao.setBounds(10, 40, 80, 20);

        jLabel10.setText("Pedido");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 20, 60, 14);

        ftxtdataentrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataentradaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataentradaFocusLost(evt);
            }
        });

        jPanel3.add(ftxtdataentrada);
        ftxtdataentrada.setBounds(110, 40, 80, 20);

        jLabel11.setText("Entrada");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(110, 20, 60, 14);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(470, 10, 210, 70);

        jPanel4.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Vendedor"));
        txtcodvend.setEnabled(false);
        jPanel4.add(txtcodvend);
        txtcodvend.setBounds(200, 40, 50, 20);

        jLabel3.setText("Nome");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(10, 20, 50, 14);

        txtvendedor.setEditable(false);
        jPanel4.add(txtvendedor);
        txtvendedor.setBounds(10, 40, 300, 20);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(10, 90, 320, 70);

        jPanel5.setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Total (R$)"));
        lblvalor.setFont(new java.awt.Font("Tahoma", 3, 15));
        lblvalor.setText("0.00");
        jPanel5.add(lblvalor);
        lblvalor.setBounds(30, 40, 70, 19);

        getContentPane().add(jPanel5);
        jPanel5.setBounds(340, 90, 120, 70);

        jPanel6.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedido"));
        txtpedido.setEditable(false);
        jPanel6.add(txtpedido);
        txtpedido.setBounds(10, 40, 59, 20);

        getContentPane().add(jPanel6);
        jPanel6.setBounds(470, 90, 80, 70);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Pedido", "Cod. P", "Produto", "Tam.", "Cod C.", "Cor", "Qtd.", "P. Unit.", "P. Total"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 170, 670, 210);

        btnconsulta.setFont(new java.awt.Font("Dialog", 1, 12));
        btnconsulta.setLabel("Consulta");
        btnconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultaActionPerformed(evt);
            }
        });

        getContentPane().add(btnconsulta);
        btnconsulta.setBounds(10, 390, 80, 30);

        btngpedido.setFont(new java.awt.Font("Dialog", 1, 12));
        btngpedido.setLabel("Gerar Pedido");
        btngpedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngpedidoActionPerformed(evt);
            }
        });

        getContentPane().add(btngpedido);
        btngpedido.setBounds(100, 390, 90, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(200, 390, 80, 30);

        btnimprimir.setEnabled(false);
        btnimprimir.setFont(new java.awt.Font("Dialog", 1, 12));
        btnimprimir.setLabel("Imprimir");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        getContentPane().add(btnimprimir);
        btnimprimir.setBounds(290, 390, 80, 30);

        btnsair.setActionCommand("Sair");
        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(380, 390, 80, 30);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-695)/2, (screenSize.height-470)/2, 695, 470);
    }// </editor-fold>//GEN-END:initComponents
 public static long carregarMemoria(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<1000000; i++){
		list.add(i);	
		}
		return Runtime.getRuntime().freeMemory();
	}   
    private void txtorcamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtorcamentoKeyTyped
// TODO add your handling code here:
          if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtorcamentoKeyTyped
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
// TODO add your handling code here:
        new Rel_Orcamento();
    }//GEN-LAST:event_btnimprimirActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        numeroRegistros = 1;
        txtvendedor.setText("");
        txtorcamento.setText("");
        ftxtdataentrada.setText("");
        ftxtdataemissao.setText("");
        txtorcamento.requestFocus();
        txtcliente.setText("");
        txtcodigo.setText("");
        txtcodvend.setText("");
        lblvalor.setText("0.00");
        btnimprimir.setEnabled(false);
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btngpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngpedidoActionPerformed
// TODO add your handling code here:
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        Date data1 = null;
        
        
        try {
            data = (Date) fmt.parse(ftxtdataemissao.getText());
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        try {
            data1 = (Date) fmt.parse(ftxtdataentrada.getText());
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        
        try {
            if(txtorcamento.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Selecione o orçamento!");
                txtorcamento.requestFocus();
            } else{
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
                String nome = "Gerar o pedido: "+txtpedido.getText()+"?";
               int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Atenção", JOptionPane.YES_NO_OPTION);
               if(opcao_escolha==JOptionPane.YES_OPTION){
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        
                        //  INSERT INTO MateriaPrima
                        
                        String sqlinsertmateria1 ="INSERT INTO Pedido (cd_cliente, cd_vendedor, cd_produto, cd_cor, qt_quantidade, vl_unitario, vl_total, vl_ttgeral, dt_pedido, dt_entrega, nm_tamanho, cd_pedido1 ) VALUES('"+
                                /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                                /* codigo Vendedor */   Integer.parseInt(txtcodvend.getText())+"','"+
                                /* cod. produto */         Integer.parseInt(jTable1.getValueAt(i, 2).toString().toUpperCase())+"','"+
                                /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                                /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 7).toString())+"','"+
                                /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                                /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 9).toString())+"','"+
                                /*V. geral */             Double.parseDouble(lblvalor.getText())+"','"+
                                /*data pedido */       data+"','"+
                                /*data entrega */      data1+"','"+
                                /*Tamanho */         jTable1.getValueAt(i, 4).toString().toUpperCase()+"','"+
                                /* codigo Cliente */   Integer.parseInt(txtpedido.getText())+"')";
                        int conseguiu_excluir =   con_orcamento.statement.executeUpdate(sqlinsertmateria1);
                         
                        
                        if(conseguiu_excluir==1){
                           
                        }
                        
                    }
              
                }
                
                }
            
               
                    new Rel_Pedido_2();
                ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                jTable1.updateUI();
                numeroRegistros = 1;
                txtvendedor.setText("");
                txtorcamento.setText("");
                ftxtdataentrada.setText("");
                ftxtdataemissao.setText("");
                txtorcamento.requestFocus();
                txtcliente.setText("");
                txtcodigo.setText("");
                txtcodvend.setText("");
                lblvalor.setText("0.00");
                
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_btngpedidoActionPerformed
     public static String converterDoubleString(double precoDouble) {  
   /*Transformando um double em 2 casas decimais*/  
   DecimalFormat fmt = new DecimalFormat("0.00");    //limita o número de casas decimais     
   String string = fmt.format(precoDouble);  
   String[] part = string.split("[,]");  
   String preco = part[0]+"."+part[1];  
   return preco;  
}  
    public void preenchejTablaOrcamento(){
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(43);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(46);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(47);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(68);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(68);
        
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                //    lblvalor.setText(con_cliente.resultset.getString("vl_ttgeral"));
                // txtvendedor.setText(con_cliente.resultset.getString("nm_vendedor"));
                modelo.addRow(new Object []{numeroRegistros++,txtpedido.getText(), con_cliente.resultset.getString("cd_produto"),con_cliente.resultset.getString("nm_produto"),con_cliente.resultset.getString("nm_tamanho"),con_cliente.resultset.getString("cd_cor"),con_cliente.resultset.getString("nm_cor"),
                con_cliente.resultset.getString("qt_quantidade"), converterDoubleString(con_cliente.resultset.getDouble("vl_unitario")),converterDoubleString(con_cliente.resultset.getDouble("vl_total"))});
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  vl_ttgeral FROM Orcamento1 where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                lblvalor.setText(converterDoubleString(con_cliente.resultset.getDouble("vl_ttgeral")));
            // txtvendedor.setText(con_cliente.resultset.getString("nm_vendedor"));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                // lblvalor.setText(con_cliente.resultset.getString("vl_ttgeral"));
                txtvendedor.setText(con_cliente.resultset.getString("nm_vendedor"));
            // txtcodvend.setText(con_cliente.resultset.getString("cd_vendedor"));
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                
                txtcodvend.setText(con_cliente.resultset.getString("cd_vendedor"));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                // lblvalor.setText(con_cliente.resultset.getString("vl_ttgeral"));
                txtcliente.setText(con_cliente.resultset.getString("nm_cliente"));
            // txtcodvend.setText(con_cliente.resultset.getString("cd_vendedor"));
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                // lblvalor.setText(con_cliente.resultset.getString("vl_ttgeral"));
                txtcodigo.setText(con_cliente.resultset.getString("cd_cliente"));
            // txtcodvend.setText(con_cliente.resultset.getString("cd_vendedor"));
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                
                ftxtdataemissao.setText(new SimpleDateFormat("dd/MM/yyyy").format(con_cliente.resultset.getDate("dt_pedido")));
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        try {
            con_cliente= new conexao();
            con_cliente.conecta();
            con_cliente.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + txtorcamento.getText() + "'");
            
            while(con_cliente.resultset.next())
                
                
                ftxtdataentrada.setText(new SimpleDateFormat("dd/MM/yyyy").format(con_cliente.resultset.getDate("dt_entrega")));
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
    }
    private void btnconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultaActionPerformed
        if(txtorcamento.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Selecione o orçamento!");
            txtorcamento.requestFocus();
        } else{
            preenchejTablaOrcamento();
            btnimprimir.setEnabled(true);
            
        }
    }//GEN-LAST:event_btnconsultaActionPerformed
    
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained
    
    private void ftxtdataentradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataentradaFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtdataentrada.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
        // ftxtdata1.setText("");
    }//GEN-LAST:event_ftxtdataentradaFocusLost
    
    private void ftxtdataentradaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataentradaFocusGained
// TODO add your handling code here:
        ftxtdataentrada.selectAll();
    }//GEN-LAST:event_ftxtdataentradaFocusGained
    
    private void ftxtdataemissaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataemissaoFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtdataemissao.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
        // ftxtdata1.setText("");
    }//GEN-LAST:event_ftxtdataemissaoFocusLost
    
    private void ftxtdataemissaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataemissaoFocusGained
// TODO add your handling code here:
        ftxtdataemissao.selectAll();
    }//GEN-LAST:event_ftxtdataemissaoFocusGained
    
    private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime
// TODO add your handling code here:
        mostra_data.le_hora();
        // lblhora.setText(mostra_data.hora);
        //   this.setTitle("Consulta Orçamento                    "+ mostra_data.dia_semana+ ", "+mostra_data.dia + " de " + mostra_data.mes + " de "+ mostra_data.ano +"                     "+ mostra_data.hora );
    }//GEN-LAST:event_timer1OnTime
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaOrcamento().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnconsulta;
    private java.awt.Button btngpedido;
    private java.awt.Button btnimprimir;
    private java.awt.Button btnlimpar;
    private java.awt.Button btnsair;
    private javax.swing.JFormattedTextField ftxtdataemissao;
    private javax.swing.JFormattedTextField ftxtdataentrada;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblvalor;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcodvend;
    public static javax.swing.JTextField txtorcamento;
    public static javax.swing.JTextField txtpedido;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables
    
}
