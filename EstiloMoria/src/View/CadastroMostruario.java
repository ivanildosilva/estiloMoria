/*
 * CadastroMostruario.java
 *
 * Created on 4 de Setembro de 2011, 22:34
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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import Controle.*;
import Modelo.*;
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
public class CadastroMostruario extends javax.swing.JFrame {
    MaskFormatter FormatoData;
    private int iniciacombo=0;
    final DefaultTableModel model = new DefaultTableModel();
    private conexao con_vendedor, con_cor, con_produto, con_mostruario;
    private int numeroRegistros=1;
    
    
    /** Creates new form CadastroMostruario */
    public CadastroMostruario() throws SQLException {
        initComponents();
        
        con_vendedor = new conexao();
        con_vendedor.conecta();
        
        con_cor=new conexao();
        con_cor.conecta();
        
        con_produto=new conexao();
        con_produto.conecta();
        
         setResizable(false);
        con_mostruario = new conexao();
        con_mostruario.conecta();
        con_mostruario.executeSQL("select MAX (cd_mostruario+1) AS Codigo from mostruario1");
        
        con_mostruario.resultset.next();
        txtcodigo.setText(con_mostruario.resultset.getString("Codigo"));
        
        con_vendedor.executeSQL("select * from vendedor order by nm_vendedor");
        con_cor.executeSQL("select * from cor order by nm_cor");
        con_produto.executeSQL("select * from produto order by nm_produto");
        try {
            while(con_vendedor.resultset.next())
                cmbvendedor.addItem(con_vendedor.resultset.getString("nm_vendedor"));
            while(con_cor.resultset.next())
                cmbcor.addItem(con_cor.resultset.getString("nm_cor"));
            while(con_produto.resultset.next())
                cmbproduto.addItem(con_produto.resultset.getString("nm_produto"));
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        
        
        preenchejTabla1();
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
   public static long carregarMemoria(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<1000000; i++){
		list.add(i);	
		}
		return Runtime.getRuntime().freeMemory();
	}     
    public void enter(){
// Enter simula tecla Tab
        HashSet conj = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }
    public void preenchejTabla1(){
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(37);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(210);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(50);
        // modelo.addRow(new Object []{"a",0,0,0,0,0,0,0,0,0,0,0});
        modelo.setNumRows(0);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        cmbvendedor = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        lblcodvendedor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtenvio = new JFormattedTextField (FormatoData);
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdevolucao = new JFormattedTextField (FormatoData);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        cmbproduto = new javax.swing.JComboBox();
        cmbcor = new javax.swing.JComboBox();
        lblcodproduto = new javax.swing.JLabel();
        lblcodcor = new javax.swing.JLabel();
        lblcodproduto1 = new javax.swing.JLabel();
        txtp = new javax.swing.JTextField();
        lblcodproduto2 = new javax.swing.JLabel();
        chbp = new javax.swing.JCheckBox();
        txtm = new javax.swing.JTextField();
        lblcodproduto3 = new javax.swing.JLabel();
        chbm = new javax.swing.JCheckBox();
        txtg = new javax.swing.JTextField();
        lblcodproduto4 = new javax.swing.JLabel();
        chbg = new javax.swing.JCheckBox();
        txtgg = new javax.swing.JTextField();
        lblcodproduto5 = new javax.swing.JLabel();
        chbgg = new javax.swing.JCheckBox();
        txteg = new javax.swing.JTextField();
        lblcodproduto6 = new javax.swing.JLabel();
        chbeg = new javax.swing.JCheckBox();
        txtexg = new javax.swing.JTextField();
        lblcodproduto7 = new javax.swing.JLabel();
        chbexg = new javax.swing.JCheckBox();
        lblcodproduto8 = new javax.swing.JLabel();
        lblcodproduto9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btninserir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtvalor = new javax.swing.JTextField();
        btnsalvar = new javax.swing.JButton();
        btnlimpar = new javax.swing.JButton();
        btnsair = new javax.swing.JButton();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Mostru\u00e1rio");
        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Vendedor"));
        cmbvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbvendedorActionPerformed(evt);
            }
        });

        jPanel2.add(cmbvendedor);
        cmbvendedor.setBounds(10, 50, 310, 20);

        jLabel1.setText("Nome");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 30, 60, 14);

        lblcodvendedor.setText("Nome");
        jPanel2.add(lblcodvendedor);
        lblcodvendedor.setBounds(140, 50, 60, 14);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 10, 330, 80);

        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        ftxtenvio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtenvioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtenvioFocusLost(evt);
            }
        });

        jPanel1.add(ftxtenvio);
        ftxtenvio.setBounds(10, 50, 80, 20);

        ftxtdevolucao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdevolucaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdevolucaoFocusLost(evt);
            }
        });

        jPanel1.add(ftxtdevolucao);
        ftxtdevolucao.setBounds(100, 50, 80, 20);

        jLabel2.setText("Envio");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 30, 60, 14);

        jLabel3.setText("Devolu\u00e7\u00e3o");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(100, 30, 60, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(350, 10, 190, 80);

        jPanel3.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Observa\u00e7\u00e3o"));
        jScrollPane1.setViewportView(txtobs);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(16, 25, 280, 100);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 100, 310, 140);

        jPanel4.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        txtcodigo.setEnabled(false);
        jPanel4.add(txtcodigo);
        txtcodigo.setBounds(10, 100, 50, 20);

        cmbproduto.setMaximumRowCount(5);
        cmbproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbprodutoActionPerformed(evt);
            }
        });

        jPanel4.add(cmbproduto);
        cmbproduto.setBounds(10, 40, 230, 20);

        cmbcor.setMaximumRowCount(5);
        cmbcor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcorActionPerformed(evt);
            }
        });

        jPanel4.add(cmbcor);
        cmbcor.setBounds(250, 40, 260, 20);

        lblcodproduto.setText("jLabel4");
        jPanel4.add(lblcodproduto);
        lblcodproduto.setBounds(140, 40, 34, 14);

        lblcodcor.setText("jLabel4");
        jPanel4.add(lblcodcor);
        lblcodcor.setBounds(280, 40, 34, 14);

        lblcodproduto1.setText("Lote");
        jPanel4.add(lblcodproduto1);
        lblcodproduto1.setBounds(10, 80, 50, 14);

        txtp.setText("0");
        txtp.setEnabled(false);
        txtp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpFocusGained(evt);
            }
        });
        txtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpKeyTyped(evt);
            }
        });

        jPanel4.add(txtp);
        txtp.setBounds(90, 100, 40, 20);

        lblcodproduto2.setText("P");
        jPanel4.add(lblcodproduto2);
        lblcodproduto2.setBounds(90, 80, 20, 14);

        chbp.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbp.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbpItemStateChanged(evt);
            }
        });

        jPanel4.add(chbp);
        chbp.setBounds(110, 80, 20, 13);

        txtm.setText("0");
        txtm.setEnabled(false);
        txtm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmFocusGained(evt);
            }
        });
        txtm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmKeyTyped(evt);
            }
        });

        jPanel4.add(txtm);
        txtm.setBounds(160, 100, 40, 20);

        lblcodproduto3.setText("M");
        jPanel4.add(lblcodproduto3);
        lblcodproduto3.setBounds(160, 80, 20, 14);

        chbm.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbm.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbmItemStateChanged(evt);
            }
        });

        jPanel4.add(chbm);
        chbm.setBounds(180, 80, 20, 13);

        txtg.setText("0");
        txtg.setEnabled(false);
        txtg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtgFocusGained(evt);
            }
        });
        txtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgKeyTyped(evt);
            }
        });

        jPanel4.add(txtg);
        txtg.setBounds(230, 100, 40, 20);

        lblcodproduto4.setText("G");
        jPanel4.add(lblcodproduto4);
        lblcodproduto4.setBounds(230, 80, 20, 14);

        chbg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbgItemStateChanged(evt);
            }
        });

        jPanel4.add(chbg);
        chbg.setBounds(250, 80, 20, 13);

        txtgg.setText("0");
        txtgg.setEnabled(false);
        txtgg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtggFocusGained(evt);
            }
        });
        txtgg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtggKeyTyped(evt);
            }
        });

        jPanel4.add(txtgg);
        txtgg.setBounds(300, 100, 40, 20);

        lblcodproduto5.setText("GG");
        jPanel4.add(lblcodproduto5);
        lblcodproduto5.setBounds(300, 80, 20, 14);

        chbgg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbgg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbgg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbggItemStateChanged(evt);
            }
        });

        jPanel4.add(chbgg);
        chbgg.setBounds(320, 80, 20, 13);

        txteg.setText("0");
        txteg.setEnabled(false);
        txteg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtegFocusGained(evt);
            }
        });
        txteg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtegKeyTyped(evt);
            }
        });

        jPanel4.add(txteg);
        txteg.setBounds(370, 100, 40, 20);

        lblcodproduto6.setText("EG");
        jPanel4.add(lblcodproduto6);
        lblcodproduto6.setBounds(370, 80, 20, 14);

        chbeg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbeg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbeg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbegItemStateChanged(evt);
            }
        });

        jPanel4.add(chbeg);
        chbeg.setBounds(390, 80, 20, 13);

        txtexg.setText("0");
        txtexg.setEnabled(false);
        txtexg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtexgFocusGained(evt);
            }
        });
        txtexg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtexgKeyTyped(evt);
            }
        });

        jPanel4.add(txtexg);
        txtexg.setBounds(440, 100, 40, 20);

        lblcodproduto7.setText("EXG");
        jPanel4.add(lblcodproduto7);
        lblcodproduto7.setBounds(440, 80, 30, 14);

        chbexg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbexg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbexg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbexgItemStateChanged(evt);
            }
        });

        jPanel4.add(chbexg);
        chbexg.setBounds(470, 80, 20, 13);

        lblcodproduto8.setText("Produto");
        jPanel4.add(lblcodproduto8);
        lblcodproduto8.setBounds(10, 20, 60, 14);

        lblcodproduto9.setText("Cor");
        jPanel4.add(lblcodproduto9);
        lblcodproduto9.setBounds(250, 20, 60, 14);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(330, 100, 520, 140);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Cod1", "Produto (A)", "Cod2", "Cor", "P", "M", "G", "GG", "EG", "EXG", "P. Unit."
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
        jScrollPane2.setBounds(10, 250, 840, 130);

        btninserir.setFont(new java.awt.Font("Tahoma", 1, 12));
        btninserir.setText("Inserir");
        btninserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninserirActionPerformed(evt);
            }
        });

        getContentPane().add(btninserir);
        btninserir.setBounds(10, 390, 80, 30);

        jPanel5.setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("V. Unit\u00e1rio"));
        txtvalor.setText("0.00");
        txtvalor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtvalorFocusGained(evt);
            }
        });
        txtvalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtvalorKeyReleased(evt);
            }
        });

        jPanel5.add(txtvalor);
        txtvalor.setBounds(20, 30, 50, 20);

        getContentPane().add(jPanel5);
        jPanel5.setBounds(550, 30, 90, 60);

        btnsalvar.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnsalvar.setText("Salvar");
        btnsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalvarActionPerformed(evt);
            }
        });

        getContentPane().add(btnsalvar);
        btnsalvar.setBounds(100, 390, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnlimpar.setText("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(190, 390, 80, 30);

        btnsair.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnsair.setText("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(280, 390, 80, 30);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-869)/2, (screenSize.height-466)/2, 869, 466);
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtvalorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalorFocusGained
// TODO add your handling code here:
        txtvalor.selectAll();
    }//GEN-LAST:event_txtvalorFocusGained
    
    private void txtvalorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorKeyReleased
// TODO add your handling code here:
        txtvalor.setText(txtvalor.getText().replace(",", ".")
        .replace("q", "").replace("w", "").replace("e", "")
        .replace("r", "").replace("t", "").replace("y", "")
        .replace("u", "").replace("i", "").replace("o", "")
        .replace("p", "").replace("´", "").replace("[", "")
        .replace("a", "").replace("s", "").replace("d", "")
        .replace("f", "").replace("g", "").replace("h", "")
        .replace("j", "").replace("k", "").replace("l", "")
        .replace("ç", "").replace("~", "").replace("]", "")
        .replace("|", "").replace("z", "").replace("x", "")
        .replace("c", "").replace("v", "").replace("b", "")
        .replace("n", "").replace("m", "").replace(";", "")
        .replace("<", "").replace(">", "").replace("/", "")
        .replace("^", "").replace("}", "").replace("º", "")
        .replace("{", "").replace("ª", "").replace("'", "")
        .replace("!", "").replace("@", "").replace("$", "")
        .replace("%", "").replace("¨", "").replace("&", "")
        .replace("*", "").replace("(", "").replace(")", "")
        .replace("+", "").replace("-", "").replace("_", "")
        .replace(")", "").replace("#", "").replace("?", "")
        .replace("°", ""));
    }//GEN-LAST:event_txtvalorKeyReleased
    
    private void txtexgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtexgFocusGained
// TODO add your handling code here:
        txtexg.selectAll();
    }//GEN-LAST:event_txtexgFocusGained
    
    private void txtegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtegFocusGained
// TODO add your handling code here:
        txteg.selectAll();
    }//GEN-LAST:event_txtegFocusGained
    
    private void txtggFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtggFocusGained
// TODO add your handling code here:
        txtgg.selectAll();
    }//GEN-LAST:event_txtggFocusGained
    
    private void txtgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtgFocusGained
// TODO add your handling code here:
        txtg.selectAll();
    }//GEN-LAST:event_txtgFocusGained
    
    private void txtmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmFocusGained
// TODO add your handling code here:
        txtm.selectAll();
    }//GEN-LAST:event_txtmFocusGained
    
    private void txtexgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtexgKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtexgKeyTyped
    
    private void txtegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtegKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtegKeyTyped
    
    private void txtggKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtggKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtggKeyTyped
    
    private void txtgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtgKeyTyped
    
    private void txtmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtmKeyTyped
    
    private void txtpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpFocusGained
// TODO add your handling code here:
        txtp.selectAll();
    }//GEN-LAST:event_txtpFocusGained
    
    private void txtpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpKeyTyped
// TODO add your handling code here:
        // Só aceita numero
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtpKeyTyped
    
    private void chbexgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbexgItemStateChanged
// TODO add your handling code here:
        if (chbexg.isSelected()){
            
            txtexg.setEnabled(true);
            txtexg.requestFocus();
        }
        if (!chbexg.isSelected()){
            txtexg.setEnabled(false);
            txtexg.setText("0");
        }
    }//GEN-LAST:event_chbexgItemStateChanged
    
    private void chbegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbegItemStateChanged
// TODO add your handling code here:
        if (chbeg.isSelected()){
            
            txteg.setEnabled(true);
            txteg.requestFocus();
        }
        if (!chbeg.isSelected()){
            txteg.setEnabled(false);
            txteg.setText("0");
        }
    }//GEN-LAST:event_chbegItemStateChanged
    
    private void chbggItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbggItemStateChanged
// TODO add your handling code here:
        if (chbgg.isSelected()){
            
            txtgg.setEnabled(true);
            txtgg.requestFocus();
        }
        if (!chbgg.isSelected()){
            txtgg.setEnabled(false);
            txtgg.setText("0");
        }
    }//GEN-LAST:event_chbggItemStateChanged
    
    private void chbgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbgItemStateChanged
// TODO add your handling code here:
        if (chbg.isSelected()){
            
            txtg.setEnabled(true);
            txtg.requestFocus();
        }
        if (!chbg.isSelected()){
            txtg.setEnabled(false);
            txtg.setText("0");
        }
    }//GEN-LAST:event_chbgItemStateChanged
    
    private void chbmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbmItemStateChanged
// TODO add your handling code here:
        if (chbm.isSelected()){
            
            txtm.setEnabled(true);
            txtm.requestFocus();
            
        }
        if (!chbm.isSelected()){
            txtm.setEnabled(false);
            txtm.setText("0");
        }
    }//GEN-LAST:event_chbmItemStateChanged
    
    private void chbpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chbpItemStateChanged
// TODO add your handling code here:
        if (chbp.isSelected()){
            txtp.setEnabled(true);
            txtp.requestFocus();
            
            
        }
        if (!chbp.isSelected()){
            txtp.setEnabled(false);
            txtp.setText("0");
        }
    }//GEN-LAST:event_chbpItemStateChanged
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
        cmbvendedor.setSelectedIndex(0);
        cmbcor.setSelectedIndex(0);
        cmbcor.setVisible(true);
        cmbproduto.setSelectedIndex(0);
        cmbproduto.setVisible(true);
        txtobs.setText("");
        txtvalor.setText("0.00");
        numeroRegistros=1;
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        txtp.setText("0");
        txtp.setEnabled(false);
        txtm.setText("0");
        txtm.setEnabled(false);
        txtg.setText("0");
        txtg.setEnabled(false);
        txtgg.setText("0");
        txtgg.setEnabled(false);
        txteg.setText("0");
        txteg.setEnabled(false);
        txtexg.setText("0");
        txtexg.setEnabled(false);
        chbp.setSelected(false);
        chbm.setSelected(false);
        chbg.setSelected(false);
        chbgg.setSelected(false);
        chbeg.setSelected(false);
        chbexg.setSelected(false);
        ftxtenvio.setText("");
        ftxtdevolucao.setText("");
        
        
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btnsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalvarActionPerformed
// TODO add your handling code here:
        
        
        
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        Date data1 = null;
        
        try {
            data = (Date) fmt.parse(ftxtenvio.getText());
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        try {
            
            data1 = (Date) fmt.parse(ftxtdevolucao.getText());
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        try {
            
            if (cmbvendedor.getSelectedItem().equals("-")){
                JOptionPane.showMessageDialog(null,"Selecione o vendedor!");
            }
            
            else{
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    numeroRegistros = Integer.parseInt(txtcodigo.getText());
                    String sqlinsertmostruario ="INSERT INTO Mostruario1 (cd_mostruario, cd_vendedor, cd_produtoacabado, cd_cor, qt_p, qt_m, qt_g, qt_gg, qt_eg, qt_exg, dt_envio_mostruario, dt_devolucao_mostruario, ds_observacao, vl_unitario) VALUES('"+
                            /* codigo Fornecedor */   Integer.parseInt(txtcodigo.getText())+"','"+
                            /* nome tipo */          Integer.parseInt(lblcodvendedor.getText())+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*Tamanho */         Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            /*Metro */            Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                            /*Kilo */             Integer.parseInt(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 8).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 10).toString())+"','"+
                            data+"','"+
                            data1+"','"+
                            txtobs.getText().toUpperCase()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"')";
                    
                    con_mostruario.statement.executeUpdate(sqlinsertmostruario);
                                       
                }
               JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
                String nome = "Deseja imprimir o mostruário: "+Integer.parseInt(txtcodigo.getText())+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    new Rel_Mostruario();
                    
                    cmbvendedor.requestFocus();
                    numeroRegistros++;
                    txtcodigo.setText(String.valueOf(numeroRegistros));
                    cmbvendedor.setSelectedIndex(0);
                    cmbcor.setSelectedIndex(0);
                    cmbproduto.setSelectedIndex(0);
                    
                    cmbvendedor.setSelectedIndex(0);
                    cmbcor.setSelectedIndex(0);
                    cmbcor.setVisible(true);
                    cmbproduto.setSelectedIndex(0);
                    cmbproduto.setVisible(true);
                    txtobs.setText("");
                    txtvalor.setText("0.00");
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    txtp.setText("0");
                    txtp.setEnabled(false);
                    txtm.setText("0");
                    txtm.setEnabled(false);
                    txtg.setText("0");
                    txtg.setEnabled(false);
                    txtgg.setText("0");
                    txtgg.setEnabled(false);
                    txteg.setText("0");
                    txteg.setEnabled(false);
                    txtexg.setText("0");
                    txtexg.setEnabled(false);
                    chbp.setSelected(false);
                    chbm.setSelected(false);
                    chbg.setSelected(false);
                    chbgg.setSelected(false);
                    chbeg.setSelected(false);
                    chbexg.setSelected(false);
                    ftxtenvio.setText("");
                    ftxtdevolucao.setText("");
                    
                    
                    
                }else{
                    cmbvendedor.requestFocus();
                    numeroRegistros++;
                    txtcodigo.setText(String.valueOf(numeroRegistros));
                    cmbvendedor.setSelectedIndex(0);
                    cmbcor.setSelectedIndex(0);
                    cmbproduto.setSelectedIndex(0);
                    
                    cmbvendedor.setSelectedIndex(0);
                    cmbcor.setSelectedIndex(0);
                    cmbcor.setVisible(true);
                    cmbproduto.setSelectedIndex(0);
                    cmbproduto.setVisible(true);
                    txtobs.setText("");
                    txtvalor.setText("0.00");
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    txtp.setText("0");
                    txtp.setEnabled(false);
                    txtm.setText("0");
                    txtm.setEnabled(false);
                    txtg.setText("0");
                    txtg.setEnabled(false);
                    txtgg.setText("0");
                    txtgg.setEnabled(false);
                    txteg.setText("0");
                    txteg.setEnabled(false);
                    txtexg.setText("0");
                    txtexg.setEnabled(false);
                    chbp.setSelected(false);
                    chbm.setSelected(false);
                    chbg.setSelected(false);
                    chbgg.setSelected(false);
                    chbeg.setSelected(false);
                    chbexg.setSelected(false);
                    ftxtenvio.setText("");
                    ftxtdevolucao.setText("");
                }
            }
            
        } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null,erro);
              
            
        }
    }//GEN-LAST:event_btnsalvarActionPerformed
     public static String converterDoubleString(double precoDouble) {  
   /*Transformando um double em 2 casas decimais*/  
   DecimalFormat fmt = new DecimalFormat("0.00");    //limita o número de casas decimais     
   String string = fmt.format(precoDouble);  
   String[] part = string.split("[,]");  
   String preco = part[0]+"."+part[1];  
   return preco;  
}  
    private void btninserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninserirActionPerformed
// TODO add your handling code here:
        
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        
        if(txtvalor.getText().equals("0.00")){
            JOptionPane.showMessageDialog(null,"Digite um valor!");
            txtvalor.requestFocus();
        } else{
            modelo.addRow(new Object []{numeroRegistros++, lblcodproduto.getText(),cmbproduto.getSelectedItem(),lblcodcor.getText(),cmbcor.getSelectedItem(),txtp.getText(),txtm.getText(),txtg.getText(),txtgg.getText(),txteg.getText(),txtexg.getText(),txtvalor.getText()});
            
            txtp.setText("0");
            txtp.setEnabled(false);
            txtm.setText("0");
            txtm.setEnabled(false);
            txtg.setText("0");
            txtg.setEnabled(false);
            txtgg.setText("0");
            txtgg.setEnabled(false);
            txteg.setText("0");
            txteg.setEnabled(false);
            txtexg.setText("0");
            txtexg.setEnabled(false);
            txtvalor.setText("0.00");
            cmbproduto.setSelectedIndex(0);
            cmbcor.setSelectedIndex(0);
            chbp.setSelected(false);
            chbm.setSelected(false);
            chbg.setSelected(false);
            chbgg.setSelected(false);
            chbeg.setSelected(false);
            chbexg.setSelected(false);
            
        }
    }//GEN-LAST:event_btninserirActionPerformed
    
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained
    
    private void cmbcorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcorActionPerformed
// TODO add your handling code here:
        if(iniciacombo == 1){
            try {
                
                con_cor.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_cor.resultset.getString("nm_cor").equals(cmbcor.getSelectedItem())){
                        igual = "s";
                    } else
                        con_cor.resultset.next();
                }
                lblcodcor.setText(con_cor.resultset.getString(String.valueOf("cd_cor")));
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        iniciacombo=1;
        
    }//GEN-LAST:event_cmbcorActionPerformed
    
    private void cmbprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbprodutoActionPerformed
// TODO add your handling code here:
        if(iniciacombo == 1){
            try {
                
                con_produto.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_produto.resultset.getString("nm_produto").equals(cmbproduto.getSelectedItem())){
                        igual = "s";
                    } else
                        con_produto.resultset.next();
                }
                lblcodproduto.setText(con_produto.resultset.getString(String.valueOf("cd_produto")));
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        iniciacombo=1;
    }//GEN-LAST:event_cmbprodutoActionPerformed
    
    private void cmbvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbvendedorActionPerformed
// TODO add your handling code here:
        if(iniciacombo == 1){
            try {
                
                con_vendedor.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_vendedor.resultset.getString("nm_vendedor").equals(cmbvendedor.getSelectedItem())){
                        igual = "s";
                    } else
                        con_vendedor.resultset.next();
                }
                lblcodvendedor.setText(con_vendedor.resultset.getString(String.valueOf("cd_vendedor")));
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        iniciacombo=1;
    }//GEN-LAST:event_cmbvendedorActionPerformed
    
    private void ftxtdevolucaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdevolucaoFocusGained
// TODO add your handling code here:
        ftxtdevolucao.selectAll();
    }//GEN-LAST:event_ftxtdevolucaoFocusGained
    
    private void ftxtenvioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtenvioFocusGained
// TODO add your handling code here:
        ftxtenvio.selectAll();
    }//GEN-LAST:event_ftxtenvioFocusGained
    
    private void ftxtdevolucaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdevolucaoFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtdevolucao.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtdevolucaoFocusLost
    
    private void ftxtenvioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtenvioFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtenvio.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtenvioFocusLost
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroMostruario().setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btninserir;
    private javax.swing.JButton btnlimpar;
    private javax.swing.JButton btnsair;
    private javax.swing.JButton btnsalvar;
    private javax.swing.JCheckBox chbeg;
    private javax.swing.JCheckBox chbexg;
    private javax.swing.JCheckBox chbg;
    private javax.swing.JCheckBox chbgg;
    private javax.swing.JCheckBox chbm;
    private javax.swing.JCheckBox chbp;
    private javax.swing.JComboBox cmbcor;
    private javax.swing.JComboBox cmbproduto;
    private javax.swing.JComboBox cmbvendedor;
    private javax.swing.JFormattedTextField ftxtdevolucao;
    private javax.swing.JFormattedTextField ftxtenvio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblcodcor;
    private javax.swing.JLabel lblcodproduto;
    private javax.swing.JLabel lblcodproduto1;
    private javax.swing.JLabel lblcodproduto2;
    private javax.swing.JLabel lblcodproduto3;
    private javax.swing.JLabel lblcodproduto4;
    private javax.swing.JLabel lblcodproduto5;
    private javax.swing.JLabel lblcodproduto6;
    private javax.swing.JLabel lblcodproduto7;
    private javax.swing.JLabel lblcodproduto8;
    private javax.swing.JLabel lblcodproduto9;
    private javax.swing.JLabel lblcodvendedor;
    public static javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txteg;
    private javax.swing.JTextField txtexg;
    private javax.swing.JTextField txtg;
    private javax.swing.JTextField txtgg;
    private javax.swing.JTextField txtm;
    private javax.swing.JTextPane txtobs;
    private javax.swing.JTextField txtp;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
    
}
