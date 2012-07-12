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
public class CadastroMostruario_1 extends javax.swing.JFrame {
    MaskFormatter FormatoData;
    private int iniciacombo=0;
    final DefaultTableModel model = new DefaultTableModel();
    private conexao con_vendedor, con_cor, con_produto, con_saldo, con_mostruario, con_cliente, con_fechamento;
    private int numeroRegistros=1;
    
    
    /** Creates new form CadastroMostruario */
    public CadastroMostruario_1() throws SQLException {
        initComponents();
       txtvalor1.setVisible(false);
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        ftxtenvio.setText(formatador.format(data));
        ftxtdevolucao.setText(formatador.format(data));
        ftxtemprestimo.setText(formatador.format(data));
        ftxtedevolucao.setText(formatador.format(data));
        lblcodcor.setVisible(false);
        lblcodproduto.setVisible(false);
        con_saldo = new conexao();
        con_saldo.conecta();
        
        con_vendedor = new conexao();
        con_vendedor.conecta();
        
        con_cliente= new conexao();
        con_cliente.conecta();
        
        con_cor=new conexao();
        con_cor.conecta();
        
        con_produto=new conexao();
        con_produto.conecta();
        
        setResizable(false);
        con_mostruario = new conexao();
        con_mostruario.conecta();
        con_mostruario.executeSQL("select MAX (cd_numlote1+1) AS Codigo from fechprod");
        
        con_mostruario.resultset.next();
        txtlote.setText(con_mostruario.resultset.getString("Codigo"));
        cmbnome.removeAllItems();
        con_cliente.executeSQL("Select c.cd_terceirizada, c.cd_cpf_terceirizada, c.nm_contato, t.*, en.*, e.nm_email1 FROM Terceirizada c Left Join Telefone t ON (c.cd_terceirizada=t.cd_terceirizada) left join email e on (c.cd_terceirizada=e.cd_terceirizada) left join endereco en on (c.cd_terceirizada=en.cd_terceirizada) order by nm_contato");
        con_fechamento= new conexao();
        con_fechamento.conecta();
        con_fechamento.executeSQL("Select  f.*, t.nm_contato, c.nm_cor,p.nm_produto FROM fechprod as f natural join Terceirizada as t natural join Cor  as c natural join Produto as p order by cd_numlote1");
        con_cor.executeSQL("select * from cor order by nm_cor");
        con_produto.executeSQL("select * from produto order by nm_produto");
        try {
            while(con_cliente.resultset.next())
                
                cmbnome.addItem(con_cliente.resultset.getString("nm_contato"));
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
        
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(222);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(38);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(38);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(38);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(38);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(38);
        jTable1.getColumnModel().getColumn(11).setPreferredWidth(55);
        
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btninserir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtvalor = new javax.swing.JTextField();
        txtvalor1 = new javax.swing.JTextField();
        btnsalvar = new javax.swing.JButton();
        btnlimpar = new javax.swing.JButton();
        btnsair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtcodigo1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbnome = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtenvio = new JFormattedTextField (FormatoData);
        jLabel10 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdevolucao = new JFormattedTextField (FormatoData);
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtequipamento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtedevolucao = new JFormattedTextField (FormatoData);
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtemprestimo = new JFormattedTextField (FormatoData);
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblcodcor = new javax.swing.JLabel();
        lblcodproduto = new javax.swing.JLabel();
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
        cmbproduto = new javax.swing.JComboBox();
        cmbcor = new javax.swing.JComboBox();
        txtlote = new javax.swing.JTextField();
        txtp1 = new javax.swing.JTextField();
        txtm1 = new javax.swing.JTextField();
        txtg1 = new javax.swing.JTextField();
        txtgg1 = new javax.swing.JTextField();
        txteg1 = new javax.swing.JTextField();
        txtexg1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        lblsaldo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblsaldopeca = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Envio Fechamento Produto");
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
        jScrollPane2.setBounds(10, 230, 840, 130);

        btninserir.setFont(new java.awt.Font("Tahoma", 1, 12));
        btninserir.setText("Inserir");
        btninserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninserirActionPerformed(evt);
            }
        });

        getContentPane().add(btninserir);
        btninserir.setBounds(10, 370, 80, 30);

        jPanel5.setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("V. Unit."));
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
        txtvalor.setBounds(10, 30, 50, 20);

        txtvalor1.setText("0.00");
        txtvalor1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtvalor1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtvalor1FocusLost(evt);
            }
        });
        txtvalor1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtvalor1KeyReleased(evt);
            }
        });

        jPanel5.add(txtvalor1);
        txtvalor1.setBounds(10, 30, 50, 20);

        getContentPane().add(jPanel5);
        jPanel5.setBounds(640, 10, 70, 70);

        btnsalvar.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnsalvar.setText("Salvar");
        btnsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalvarActionPerformed(evt);
            }
        });

        getContentPane().add(btnsalvar);
        btnsalvar.setBounds(100, 370, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnlimpar.setText("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(190, 370, 80, 30);

        btnsair.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnsair.setText("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(280, 370, 80, 30);

        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Terceirizada"));
        txtcodigo1.setEditable(false);
        jPanel2.add(txtcodigo1);
        txtcodigo1.setBounds(10, 40, 50, 20);

        jLabel1.setText("C\u00f3digo");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 20, 50, 14);

        cmbnome.setMaximumRowCount(5);
        cmbnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnomeActionPerformed(evt);
            }
        });

        jPanel2.add(cmbnome);
        cmbnome.setBounds(70, 40, 360, 20);

        jLabel4.setText("Nome");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(70, 20, 50, 14);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 10, 440, 70);

        jPanel3.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datas"));
        ftxtenvio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtenvioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtenvioFocusLost(evt);
            }
        });

        jPanel3.add(ftxtenvio);
        ftxtenvio.setBounds(10, 40, 80, 20);

        jLabel10.setText("Envio");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 20, 60, 14);

        ftxtdevolucao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdevolucaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdevolucaoFocusLost(evt);
            }
        });

        jPanel3.add(ftxtdevolucao);
        ftxtdevolucao.setBounds(100, 40, 80, 20);

        jLabel11.setText("Devolu\u00e7\u00e3o");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(100, 20, 60, 14);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(450, 10, 190, 70);

        jPanel4.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamento Empr\u00e9stimo"));
        jPanel4.add(txtequipamento);
        txtequipamento.setBounds(10, 50, 170, 20);

        jLabel3.setText("Tipo Equipamento");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(10, 30, 150, 14);

        ftxtedevolucao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtedevolucaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtedevolucaoFocusLost(evt);
            }
        });

        jPanel4.add(ftxtedevolucao);
        ftxtedevolucao.setBounds(100, 100, 80, 20);

        ftxtemprestimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtemprestimoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtemprestimoFocusLost(evt);
            }
        });

        jPanel4.add(ftxtemprestimo);
        ftxtemprestimo.setBounds(10, 100, 80, 20);

        jLabel13.setText("Envio");
        jPanel4.add(jLabel13);
        jLabel13.setBounds(10, 80, 60, 14);

        jLabel12.setText("Devolu\u00e7\u00e3o");
        jPanel4.add(jLabel12);
        jLabel12.setBounds(100, 80, 60, 14);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(140, 90, 190, 130);

        jPanel6.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        lblcodcor.setText("cor");
        jPanel6.add(lblcodcor);
        lblcodcor.setBounds(280, 40, 30, 14);

        lblcodproduto.setText("produto");
        jPanel6.add(lblcodproduto);
        lblcodproduto.setBounds(120, 40, 50, 14);

        lblcodproduto1.setText("Lote");
        jPanel6.add(lblcodproduto1);
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

        jPanel6.add(txtp);
        txtp.setBounds(70, 100, 40, 20);

        lblcodproduto2.setText("P");
        jPanel6.add(lblcodproduto2);
        lblcodproduto2.setBounds(70, 80, 20, 14);

        chbp.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbp.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbpItemStateChanged(evt);
            }
        });

        jPanel6.add(chbp);
        chbp.setBounds(90, 80, 20, 13);

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

        jPanel6.add(txtm);
        txtm.setBounds(140, 100, 40, 20);

        lblcodproduto3.setText("M");
        jPanel6.add(lblcodproduto3);
        lblcodproduto3.setBounds(140, 80, 20, 14);

        chbm.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbm.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbmItemStateChanged(evt);
            }
        });

        jPanel6.add(chbm);
        chbm.setBounds(160, 80, 20, 13);

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

        jPanel6.add(txtg);
        txtg.setBounds(220, 100, 40, 20);

        lblcodproduto4.setText("G");
        jPanel6.add(lblcodproduto4);
        lblcodproduto4.setBounds(220, 80, 20, 14);

        chbg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbgItemStateChanged(evt);
            }
        });

        jPanel6.add(chbg);
        chbg.setBounds(240, 80, 20, 13);

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

        jPanel6.add(txtgg);
        txtgg.setBounds(300, 100, 40, 20);

        lblcodproduto5.setText("GG");
        jPanel6.add(lblcodproduto5);
        lblcodproduto5.setBounds(300, 80, 20, 14);

        chbgg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbgg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbgg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbggItemStateChanged(evt);
            }
        });

        jPanel6.add(chbgg);
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

        jPanel6.add(txteg);
        txteg.setBounds(380, 100, 40, 20);

        lblcodproduto6.setText("EG");
        jPanel6.add(lblcodproduto6);
        lblcodproduto6.setBounds(380, 80, 20, 14);

        chbeg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbeg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbeg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbegItemStateChanged(evt);
            }
        });

        jPanel6.add(chbeg);
        chbeg.setBounds(400, 80, 20, 13);

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

        jPanel6.add(txtexg);
        txtexg.setBounds(460, 100, 40, 20);

        lblcodproduto7.setText("EXG");
        jPanel6.add(lblcodproduto7);
        lblcodproduto7.setBounds(460, 80, 30, 14);

        chbexg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        chbexg.setMargin(new java.awt.Insets(0, 0, 0, 0));
        chbexg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chbexgItemStateChanged(evt);
            }
        });

        jPanel6.add(chbexg);
        chbexg.setBounds(490, 80, 20, 13);

        lblcodproduto8.setText("Produto");
        jPanel6.add(lblcodproduto8);
        lblcodproduto8.setBounds(10, 20, 60, 14);

        lblcodproduto9.setText("Cor");
        jPanel6.add(lblcodproduto9);
        lblcodproduto9.setBounds(250, 20, 60, 14);

        cmbproduto.setMaximumRowCount(5);
        cmbproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbprodutoActionPerformed(evt);
            }
        });

        jPanel6.add(cmbproduto);
        cmbproduto.setBounds(10, 40, 230, 20);

        cmbcor.setMaximumRowCount(5);
        cmbcor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcorActionPerformed(evt);
            }
        });
        cmbcor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbcorFocusLost(evt);
            }
        });

        jPanel6.add(cmbcor);
        cmbcor.setBounds(250, 40, 260, 20);

        txtlote.setEditable(false);
        jPanel6.add(txtlote);
        txtlote.setBounds(10, 100, 50, 20);

        txtp1.setText("0");
        txtp1.setEnabled(false);
        txtp1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtp1FocusGained(evt);
            }
        });
        txtp1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtp1KeyTyped(evt);
            }
        });

        jPanel6.add(txtp1);
        txtp1.setBounds(70, 100, 40, 20);

        txtm1.setText("0");
        txtm1.setEnabled(false);
        txtm1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtm1FocusGained(evt);
            }
        });
        txtm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtm1KeyTyped(evt);
            }
        });

        jPanel6.add(txtm1);
        txtm1.setBounds(140, 100, 40, 20);

        txtg1.setText("0");
        txtg1.setEnabled(false);
        txtg1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtg1FocusGained(evt);
            }
        });
        txtg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtg1KeyTyped(evt);
            }
        });

        jPanel6.add(txtg1);
        txtg1.setBounds(220, 100, 40, 20);

        txtgg1.setText("0");
        txtgg1.setEnabled(false);
        txtgg1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtgg1FocusGained(evt);
            }
        });
        txtgg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtgg1KeyTyped(evt);
            }
        });

        jPanel6.add(txtgg1);
        txtgg1.setBounds(300, 100, 40, 20);

        txteg1.setText("0");
        txteg1.setEnabled(false);
        txteg1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txteg1FocusGained(evt);
            }
        });
        txteg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txteg1KeyTyped(evt);
            }
        });

        jPanel6.add(txteg1);
        txteg1.setBounds(380, 100, 40, 20);

        txtexg1.setText("0");
        txtexg1.setEnabled(false);
        txtexg1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtexg1FocusGained(evt);
            }
        });
        txtexg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtexg1KeyTyped(evt);
            }
        });

        jPanel6.add(txtexg1);
        txtexg1.setBounds(460, 100, 40, 20);

        getContentPane().add(jPanel6);
        jPanel6.setBounds(330, 90, 520, 130);

        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Observa\u00e7\u00e3o"));
        txtobs.setColumns(20);
        txtobs.setRows(5);
        jScrollPane1.setViewportView(txtobs);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(7, 15, 120, 120);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 80, 130, 140);

        jPanel7.setLayout(null);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("V. Total"));
        lblsaldo.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblsaldo.setText("0.00");
        jPanel7.add(lblsaldo);
        lblsaldo.setBounds(10, 30, 70, 17);

        getContentPane().add(jPanel7);
        jPanel7.setBounds(710, 10, 80, 70);

        jPanel8.setLayout(null);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Pe\u00e7as"));
        lblsaldopeca.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblsaldopeca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsaldopeca.setText("0");
        jPanel8.add(lblsaldopeca);
        lblsaldopeca.setBounds(10, 30, 40, 17);

        getContentPane().add(jPanel8);
        jPanel8.setBounds(790, 10, 60, 70);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-862)/2, (screenSize.height-449)/2, 862, 449);
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtvalor1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalor1FocusLost
// TODO add your handling code here:
        
    }//GEN-LAST:event_txtvalor1FocusLost
    
    private void txtvalor1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalor1KeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_txtvalor1KeyReleased
    
    private void txtvalor1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalor1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtvalor1FocusGained
    
    private void txtexg1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtexg1KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_txtexg1KeyTyped
    
    private void txtexg1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtexg1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtexg1FocusGained
    
    private void txteg1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txteg1KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_txteg1KeyTyped
    
    private void txteg1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txteg1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txteg1FocusGained
    
    private void txtgg1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgg1KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_txtgg1KeyTyped
    
    private void txtgg1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtgg1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtgg1FocusGained
    
    private void txtg1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtg1KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_txtg1KeyTyped
    
    private void txtg1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtg1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtg1FocusGained
    
    private void txtm1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtm1KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_txtm1KeyTyped
    
    private void txtm1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtm1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtm1FocusGained
    
    private void txtp1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtp1KeyTyped
// TODO add your handling code here:
    }//GEN-LAST:event_txtp1KeyTyped
    
    private void txtp1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtp1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtp1FocusGained
    
    private void cmbcorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbcorFocusLost
// TODO add your handling code here:
        
        try {
            con_saldo= new conexao();
            con_saldo.conecta();
            con_saldo.executeSQL("SELECT qt_p, qt_m,qt_g,qt_gg,qt_eg,qt_exg FROM produtoacabado where cd_produto='"
                    + Integer.parseInt(lblcodproduto.getText())
                    + "' and cd_cor = '"
                    + Integer.parseInt(lblcodcor.getText())
                    + "'");
            
            con_saldo.resultset.first();
            
            txtp1.setText(con_saldo.resultset.getString("qt_p"));
            txtm1.setText(con_saldo.resultset.getString("qt_m"));
            txtg1.setText(con_saldo.resultset.getString("qt_g"));
            txtgg1.setText(con_saldo.resultset.getString("qt_gg"));
            txteg1.setText(con_saldo.resultset.getString("qt_eg"));
            txtexg1.setText(con_saldo.resultset.getString("qt_exg"));
//qtdestoquetinha = (con_saldo.resultset.getDouble("qt_metro"));
            //qtdestoquetinha1 = (con_saldo.resultset.getDouble("qt_kilo"));
            txtp1.setText("0");
            txtm1.setText("0");
            txtg1.setText("0");
            txtgg1.setText("0");
            txteg1.setText("0");
            txtexg1.setText("0");
            //JOptionPane.showMessageDialog(null,qtdestoquetinha);
            //JOptionPane.showMessageDialog(null,qtdestoquetinha1);
            
        } catch(SQLException erro) {
            if(txtp1.getText().equals("0") && txtm1.getText().equals("0")&& txtg1.getText().equals("0")&& txtgg1.getText().equals("0")&& txteg1.getText().equals("0")&& txtexg1.getText().equals("0")){
                
                JOptionPane.showMessageDialog(null,"Combinação Produto/ Cor encontrada.\n   Combinação incluída com sucesso!");
                
                String sqlinsertsaldo ="INSERT INTO produtoacabado (cd_numlote, cd_produto, cd_cor, qt_p, qt_m, qt_g, qt_gg, qt_eg, qt_exg) VALUES('"+
                        0 +"','"+
                        Integer.parseInt(lblcodproduto.getText())+"','"+
                        Integer.parseInt(lblcodcor.getText())+"','"+
                        Integer.parseInt(txtp.getText())+"','"+
                        Integer.parseInt(txtm.getText())+"','"+
                        Integer.parseInt(txtg.getText())+"','"+
                        Integer.parseInt(txtgg.getText())+"','"+
                        Integer.parseInt(txteg.getText())+"','"+
                        Integer.parseInt(txtexg.getText())+"')";
                
                try {
                    
                    con_saldo.statement.executeUpdate(sqlinsertsaldo);
                    con_saldo.executeSQL("select * from produtoacabado");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                txtp1.setText("0");
                txtm1.setText("0");
                txtg1.setText("0");
                txtgg1.setText("0");
                txteg1.setText("0");
                txtexg1.setText("0");
            }
        }
    }//GEN-LAST:event_cmbcorFocusLost
    
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
    
    private void txtexgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtexgKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtexgKeyTyped
    
    private void txtexgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtexgFocusGained
// TODO add your handling code here:
        txtexg.selectAll();
    }//GEN-LAST:event_txtexgFocusGained
    
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
    
    private void txtegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtegKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtegKeyTyped
    
    private void txtegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtegFocusGained
// TODO add your handling code here:
        txteg.selectAll();
    }//GEN-LAST:event_txtegFocusGained
    
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
    
    private void txtggKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtggKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtggKeyTyped
    
    private void txtggFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtggFocusGained
// TODO add your handling code here:
        txtgg.selectAll();
    }//GEN-LAST:event_txtggFocusGained
    
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
    
    private void txtgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtgKeyTyped
    
    private void txtgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtgFocusGained
// TODO add your handling code here:
        txtg.selectAll();
    }//GEN-LAST:event_txtgFocusGained
    
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
    
    private void txtmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmKeyTyped
// TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtmKeyTyped
    
    private void txtmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmFocusGained
// TODO add your handling code here:
        txtm.selectAll();
    }//GEN-LAST:event_txtmFocusGained
    
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
    
    private void txtpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpKeyTyped
// TODO add your handling code here:
        // Só aceita numero
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtpKeyTyped
    
    private void txtpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpFocusGained
// TODO add your handling code here:
        txtp.selectAll();
    }//GEN-LAST:event_txtpFocusGained
    
    private void ftxtemprestimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtemprestimoFocusLost
// TODO add your handling code here:
    }//GEN-LAST:event_ftxtemprestimoFocusLost
    
    private void ftxtemprestimoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtemprestimoFocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_ftxtemprestimoFocusGained
    
    private void ftxtedevolucaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtedevolucaoFocusLost
// TODO add your handling code here:
    }//GEN-LAST:event_ftxtedevolucaoFocusLost
    
    private void ftxtedevolucaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtedevolucaoFocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_ftxtedevolucaoFocusGained
    
    private void ftxtdevolucaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdevolucaoFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtdevolucao.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
        // ftxtdata1.setText("");
    }//GEN-LAST:event_ftxtdevolucaoFocusLost
    
    private void ftxtdevolucaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdevolucaoFocusGained
// TODO add your handling code here:
        ftxtenvio.selectAll();
    }//GEN-LAST:event_ftxtdevolucaoFocusGained
    
    private void ftxtenvioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtenvioFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtenvio.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
        // ftxtdata1.setText("");
    }//GEN-LAST:event_ftxtenvioFocusLost
    
    private void ftxtenvioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtenvioFocusGained
// TODO add your handling code here:
        ftxtenvio.selectAll();
    }//GEN-LAST:event_ftxtenvioFocusGained
    
    private void cmbnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnomeActionPerformed
        try {
            
            con_cliente.resultset.first();
            String igual = "n";
            while(igual == "n"){
                if(con_cliente.resultset.getString("nm_contato").equals(cmbnome.getSelectedItem())){
                    igual = "s";
                } else
                    con_cliente.resultset.next();
            }
            
            
            txtcodigo1.setText(con_cliente.resultset.getString(String.valueOf("cd_terceirizada")));
            con_cliente.conecta();
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_cmbnomeActionPerformed
    
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
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
       cmbnome.setSelectedIndex(0);
        cmbcor.setSelectedIndex(0);
        cmbcor.setVisible(true);
        cmbproduto.setSelectedIndex(0);
        cmbproduto.setVisible(true);
        txtobs.setText("");
        txtvalor.setText("0.00");
        lblsaldo.setText("0.00");
        lblsaldopeca.setText("0");
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
        
        
        
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btnsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalvarActionPerformed
// TODO add your handling code here:
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        Date data1 = null;
        Date data2 = null;
        Date data3 = null;
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
            
            data2 = (Date) fmt.parse(ftxtemprestimo.getText());
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        try {
            
            data3 = (Date) fmt.parse(ftxtedevolucao.getText());
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        numeroRegistros = Integer.parseInt(txtlote.getText());
        
        //-----------------------------------------------------
        try {
            
            if (cmbnome.getSelectedItem().equals("-")){
                JOptionPane.showMessageDialog(null,"Selecione o Terceirizada!");
            }
            
            else{
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    numeroRegistros = Integer.parseInt(txtlote.getText());
                    String sqlinserttecido ="INSERT INTO fechprod (cd_numlote1, cd_terceirizada, cd_produto, cd_cor, qt_p, qt_m, qt_g, qt_gg, qt_eg, qt_exg, dt_envlote, dt_vevollote, nm_eqpto, dt_envequpto, dt_devequpto, nm_observacao, vl_unitario, vl_total, qt_peca) VALUES('"+Integer.parseInt(txtlote.getText())+"','"+
                            Integer.parseInt(txtcodigo1.getText())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 7).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 8).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            Integer.parseInt(jTable1.getValueAt(i, 10).toString())+"','"+
                            data+"','"+
                            data1+"','"+
                            txtequipamento.getText().toUpperCase()+"','"+
                            data2+"','"+
                            data3+"','"+
                            txtobs.getText().toUpperCase()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(lblsaldo.getText())+ "','"+
                            Integer.parseInt(lblsaldopeca.getText())+ "')";
                    
                    con_cliente.statement.executeUpdate(sqlinserttecido);
                    
                    
                }
            }
            JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
            String nome = "Deseja imprimir o lote: "+Integer.parseInt(txtlote.getText())+"?";
            
            int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
            if(opcao_escolha==JOptionPane.YES_OPTION){
                
                new Rel_Envio_Fech_Prod();
                
                con_cliente.conecta();
               con_cliente.executeSQL("Select c.cd_terceirizada, c.cd_cpf_terceirizada, c.nm_contato, t.*, en.*, e.nm_email1 FROM Terceirizada c Left Join Telefone t ON (c.cd_terceirizada=t.cd_terceirizada) left join email e on (c.cd_terceirizada=e.cd_terceirizada) left join endereco en on (c.cd_terceirizada=en.cd_terceirizada) order by nm_contato");
//                con_fechamento.conecta();
//                con_fechamento.executeSQL("Select  f.*, t.nm_contato, c.nm_cor,p.nm_produto FROM fechprod as f natural join Terceirizada as t natural join Cor  as c natural join Produto as p order by cd_numlote");
                 
                ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                jTable1.updateUI();
               cmbnome.setSelectedIndex(0);
               lblsaldo.setText("0.00");
               lblsaldopeca.setText("0");
                txtobs.setText("");
                txtequipamento.setText("");
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
                txtequipamento.setEnabled(true);
                numeroRegistros++;
                txtlote.setText(String.valueOf(numeroRegistros));
               
            } else{
                con_cliente.conecta();
                con_cliente.executeSQL("Select c.cd_terceirizada, c.cd_cpf_terceirizada, c.nm_contato, t.*, en.*, e.nm_email1 FROM Terceirizada c Left Join Telefone t ON (c.cd_terceirizada=t.cd_terceirizada) left join email e on (c.cd_terceirizada=e.cd_terceirizada) left join endereco en on (c.cd_terceirizada=en.cd_terceirizada) order by nm_contato");
                con_fechamento.conecta();
                con_fechamento.executeSQL("Select  f.*, t.nm_contato, c.nm_cor,p.nm_produto FROM fechprod as f natural join Terceirizada as t natural join Cor  as c natural join Produto as p order by cd_numlote");
                //cmbcor.setSelectedIndex(0);
                cmbnome.setSelectedIndex(0);
                //cmbproduto.setSelectedIndex(0);
                ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
               lblsaldo.setText("0.00");
               lblsaldopeca.setText("0");
                jTable1.updateUI();
                txtobs.setText("");
                txtequipamento.setText("");
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
                txtequipamento.setEnabled(true);
                numeroRegistros++;
                txtlote.setText(String.valueOf(numeroRegistros));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
        con_fechamento.conecta();
        con_fechamento.executeSQL("Select  f.*, t.nm_contato, c.nm_cor,p.nm_produto FROM fechprod as f natural join Terceirizada as t natural join Cor  as c natural join Produto as p order by cd_numlote");
        con_cliente.conecta();
        con_cliente.executeSQL("Select c.cd_terceirizada, c.cd_cpf_terceirizada, c.nm_contato, t.*, en.*, e.nm_email1 FROM Terceirizada c Left Join Telefone t ON (c.cd_terceirizada=t.cd_terceirizada) left join email e on (c.cd_terceirizada=e.cd_terceirizada) left join endereco en on (c.cd_terceirizada=en.cd_terceirizada) order by nm_contato");
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
            
            double total1 = 0.0;
            modelo.addRow(new Object []{numeroRegistros++, lblcodproduto.getText(),cmbproduto.getSelectedItem(),lblcodcor.getText(),cmbcor.getSelectedItem(),txtp.getText(),txtm.getText(),txtg.getText(),txtgg.getText(),txteg.getText(),txtexg.getText(),txtvalor.getText()});
            
            
            double total = 0.0;
            int total2 = 0;
            //somar as txt
            
            
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                total += Double.parseDouble(jTable1.getValueAt(i, 11)
//                .toString());
            
//            }
            
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                total += ((Double.parseDouble(jTable1.getValueAt(i, 5)
                .toString()))+(Double.parseDouble(jTable1.getValueAt(i, 6)
                .toString()))+(Double.parseDouble(jTable1.getValueAt(i, 7)
                .toString()))+(Double.parseDouble(jTable1.getValueAt(i, 8)
                .toString()))+(Double.parseDouble(jTable1.getValueAt(i, 9)
                .toString()))+(Double.parseDouble(jTable1.getValueAt(i, 10)
                .toString())))*(Double.parseDouble(jTable1.getValueAt(i, 11).toString())) ;

                 total2 += ((Integer.parseInt(jTable1.getValueAt(i, 5)
                .toString()))+(Integer.parseInt(jTable1.getValueAt(i, 6)
                .toString()))+(Integer.parseInt(jTable1.getValueAt(i, 7)
                .toString()))+(Integer.parseInt(jTable1.getValueAt(i, 8)
                .toString()))+(Integer.parseInt(jTable1.getValueAt(i, 9)
                .toString()))+(Integer.parseInt(jTable1.getValueAt(i, 10)
                .toString()))) ;
            }
            lblsaldo.setText(String.valueOf(total));
            lblsaldopeca.setText(String.valueOf(total2));
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CadastroMostruario_1().setVisible(true);
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
    private javax.swing.JComboBox cmbnome;
    private javax.swing.JComboBox cmbproduto;
    private javax.swing.JFormattedTextField ftxtdevolucao;
    private javax.swing.JFormattedTextField ftxtedevolucao;
    private javax.swing.JFormattedTextField ftxtemprestimo;
    private javax.swing.JFormattedTextField ftxtenvio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
    private javax.swing.JLabel lblsaldo;
    private javax.swing.JLabel lblsaldopeca;
    private javax.swing.JTextField txtcodigo1;
    private javax.swing.JTextField txteg;
    private javax.swing.JTextField txteg1;
    private javax.swing.JTextField txtequipamento;
    private javax.swing.JTextField txtexg;
    private javax.swing.JTextField txtexg1;
    private javax.swing.JTextField txtg;
    private javax.swing.JTextField txtg1;
    private javax.swing.JTextField txtgg;
    private javax.swing.JTextField txtgg1;
    public static javax.swing.JTextField txtlote;
    private javax.swing.JTextField txtm;
    private javax.swing.JTextField txtm1;
    private javax.swing.JTextArea txtobs;
    private javax.swing.JTextField txtp;
    private javax.swing.JTextField txtp1;
    private javax.swing.JTextField txtvalor;
    private javax.swing.JTextField txtvalor1;
    // End of variables declaration//GEN-END:variables
    
}
