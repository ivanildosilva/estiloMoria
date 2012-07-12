/*
 * CadastroMateriaPrima.java
 *
 * Created on 2 de Setembro de 2011, 09:04
 */

package View;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
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
public class CadastroMateriaPrima extends javax.swing.JFrame {
    
    private Object contador;
    final DefaultTableModel model = new DefaultTableModel();
    private conexao con_contapagar,con_contapagar1,con_contapagar2,con_contapagar3,con_contapagar4,con_contapagar5, con_saldo, con_cor, con_fornecedor ,con_tecido, con_aviamento,con_materia ;
    private int iniciacombo = 0;
    
    MaskFormatter FormatoData, FormatoCpf, FormatoCnpj, FormatoCep, FormatoTel1, FormatoTel2, FormatoTel3, FormatoTel4;
    /** Creates new form CadastroMateriaPrima */
    public CadastroMateriaPrima() {
        initComponents();
        con_fornecedor = new conexao();
        con_fornecedor.conecta();
        setResizable(false);
        con_tecido=new conexao();
        con_tecido.conecta();
        
        con_aviamento=new conexao();
        con_aviamento.conecta();
        
        con_cor=new conexao();
        con_cor.conecta();
        
        con_materia=new conexao();
        con_materia.conecta();
        
        con_saldo=new conexao();
        con_saldo.conecta();
        
        con_contapagar=new conexao();
        con_contapagar.conecta();
        
        con_contapagar1=new conexao();
        con_contapagar1.conecta();
        
        con_contapagar2=new conexao();
        con_contapagar2.conecta();
        
        con_contapagar3=new conexao();
        con_contapagar3.conecta();
        
        con_contapagar4=new conexao();
        con_contapagar4.conecta();
        
        con_contapagar5=new conexao();
        con_contapagar5.conecta();
        
        
        
        con_fornecedor.executeSQL("select * from fornecedor order by nm_razaosocial_fornecedor");
        con_tecido.executeSQL("select * from tecido order by nm_tecido");
        con_aviamento.executeSQL("select * from aviamento order by nm_aviamento");
        con_cor.executeSQL("select * from cor order by nm_cor");
        con_materia.executeSQL("select * from materiaprima order by cd_materiaprima");
        con_saldo.executeSQL("select * from saldomateriaprima order by cd_materiaprima");
        con_contapagar.executeSQL("select * from contapagar order by cd_contapagar");
        con_contapagar1.executeSQL("select * from contapagar order by cd_contapagar");
        con_contapagar2.executeSQL("select * from contapagar order by cd_contapagar");
        con_contapagar3.executeSQL("select * from contapagar order by cd_contapagar");
        con_contapagar4.executeSQL("select * from contapagar order by cd_contapagar");
        con_contapagar5.executeSQL("select * from contapagar order by cd_contapagar");
        
        try {
            while(con_fornecedor.resultset.next())
                cmbrazao.addItem(con_fornecedor.resultset.getString("nm_razaosocial_fornecedor"));
            while(con_tecido.resultset.next()) {
                //JOptionPane.showMessageDialog(null,con_cliente.resultset.getString("nome"));
                cmbproduto.addItem(con_tecido.resultset.getString("nm_tecido"));
            }
            while(con_aviamento.resultset.next())
                cmbaviamento.addItem(con_aviamento.resultset.getString("nm_aviamento"));
            while(con_cor.resultset.next())
                cmbcor.addItem(con_cor.resultset.getString("nm_cor"));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        cmbaviamento.setVisible(false);
        cmbproduto.setVisible(false);
        
        
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
    public void preenchejTabla1(){
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(35);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(9).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(10).setPreferredWidth(70);
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
        GrupoMedida = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        cmptipo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbproduto = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbcor = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbtamanho = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        lbltipoa = new javax.swing.JLabel();
        lblcodproduto = new javax.swing.JLabel();
        cmbaviamento = new javax.swing.JComboBox();
        cmbnula = new javax.swing.JComboBox();
        lbltipot = new javax.swing.JLabel();
        lblcodaviamento = new javax.swing.JLabel();
        lblcodcor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rdbmetro = new javax.swing.JRadioButton();
        rdbkilo = new javax.swing.JRadioButton();
        rdbpeca = new javax.swing.JRadioButton();
        txtmetro = new javax.swing.JTextField();
        txtkilo = new javax.swing.JTextField();
        txtpeca = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rdbmetro1 = new javax.swing.JRadioButton();
        rdbkilo1 = new javax.swing.JRadioButton();
        rdbpeca1 = new javax.swing.JRadioButton();
        txtmetro1 = new javax.swing.JTextField();
        txtkilo1 = new javax.swing.JTextField();
        txtpeca1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cmbrazao = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        try
        {
            FormatoCnpj = new MaskFormatter("##.###.###/####-##");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtcnpj = new JFormattedTextField (FormatoCnpj);
        try
        {
            FormatoCnpj = new MaskFormatter("##.###.###/####-##");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtcnpj1 = new JFormattedTextField (FormatoCnpj);
        try
        {
            FormatoCnpj = new MaskFormatter("##.###.###/####-##");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtcnpj2 = new JFormattedTextField (FormatoCnpj);
        lblcodfornecedor = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtdoc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtserie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdataemissao = new JFormattedTextField (FormatoData);
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtdataentrada = new JFormattedTextField (FormatoData);
        jPanel6 = new javax.swing.JPanel();
        txtvalor = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txtnota = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txtnota1 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        txtnota2 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        txtnota4 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        txtnota3 = new javax.swing.JTextField();
        rdbvista = new javax.swing.JRadioButton();
        rdbfaturado = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtnota6 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        txtnota7 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        txtnota8 = new javax.swing.JTextField();
        cmbparcela = new javax.swing.JComboBox();
        lblteste = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtvencimento1 = new JFormattedTextField (FormatoData);
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtvencimento2 = new JFormattedTextField (FormatoData);
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtvencimento3 = new JFormattedTextField (FormatoData);
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtvencimento4 = new JFormattedTextField (FormatoData);
        jLabel17 = new javax.swing.JLabel();
        try
        {
            FormatoData = new MaskFormatter("##/##/####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxtvencimento5 = new JFormattedTextField (FormatoData);
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtparcela1 = new javax.swing.JTextField();
        txtparcela2 = new javax.swing.JTextField();
        txtparcela3 = new javax.swing.JTextField();
        txtparcela4 = new javax.swing.JTextField();
        txtparcela5 = new javax.swing.JTextField();
        btncadastrar = new java.awt.Button();
        btninserir = new java.awt.Button();
        btnlimpar = new java.awt.Button();
        btnsair = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrada Nota Fiscal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        cmptipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aviamento", "Tecido" }));
        cmptipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmptipoItemStateChanged(evt);
            }
        });
        cmptipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmptipoActionPerformed(evt);
            }
        });

        jPanel1.add(cmptipo);
        cmptipo.setBounds(10, 40, 90, 20);

        jLabel1.setText("Tipo");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 50, 14);

        cmbproduto.setMaximumRowCount(5);
        cmbproduto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbprodutoItemStateChanged(evt);
            }
        });
        cmbproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbprodutoActionPerformed(evt);
            }
        });

        jPanel1.add(cmbproduto);
        cmbproduto.setBounds(110, 40, 250, 20);

        jLabel2.setText("Produto");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(110, 20, 50, 14);

        cmbcor.setMaximumRowCount(5);
        cmbcor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcorActionPerformed(evt);
            }
        });

        jPanel1.add(cmbcor);
        cmbcor.setBounds(370, 40, 120, 20);

        jLabel3.setText("Cor");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(370, 20, 50, 14);

        cmbtamanho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pequeno", "M\u00e9dio", "Grande" }));
        cmbtamanho.setEnabled(false);
        jPanel1.add(cmbtamanho);
        cmbtamanho.setBounds(500, 40, 120, 20);

        jLabel4.setText("Tamanho");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(500, 20, 70, 14);

        lbltipoa.setText("tipoa");
        jPanel1.add(lbltipoa);
        lbltipoa.setBounds(40, 40, 40, 14);

        lblcodproduto.setText("produto");
        jPanel1.add(lblcodproduto);
        lblcodproduto.setBounds(390, 40, 40, 14);

        cmbaviamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbaviamentoItemStateChanged(evt);
            }
        });
        cmbaviamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbaviamentoActionPerformed(evt);
            }
        });

        jPanel1.add(cmbaviamento);
        cmbaviamento.setBounds(110, 40, 250, 20);

        cmbnula.setMaximumRowCount(5);
        cmbnula.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbnula.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbnulaItemStateChanged(evt);
            }
        });
        cmbnula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnulaActionPerformed(evt);
            }
        });

        jPanel1.add(cmbnula);
        cmbnula.setBounds(110, 40, 250, 20);

        lbltipot.setText("tipot");
        jPanel1.add(lbltipot);
        lbltipot.setBounds(50, 40, 40, 14);

        lblcodaviamento.setText("Aviamento");
        jPanel1.add(lblcodaviamento);
        lblcodaviamento.setBounds(390, 40, 60, 14);

        lblcodcor.setText("Cor");
        jPanel1.add(lblcodcor);
        lblcodcor.setBounds(420, 40, 50, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 630, 80);

        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Unid. Medida"));
        GrupoMedida.add(rdbmetro);
        rdbmetro.setText("Metro");
        rdbmetro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbmetro.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbmetro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbmetroItemStateChanged(evt);
            }
        });

        jPanel2.add(rdbmetro);
        rdbmetro.setBounds(10, 30, 60, 15);

        GrupoMedida.add(rdbkilo);
        rdbkilo.setText("Kilo");
        rdbkilo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbkilo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbkilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbkiloActionPerformed(evt);
            }
        });

        jPanel2.add(rdbkilo);
        rdbkilo.setBounds(70, 30, 50, 15);

        GrupoMedida.add(rdbpeca);
        rdbpeca.setText("Pe\u00e7a");
        rdbpeca.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbpeca.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbpeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbpecaActionPerformed(evt);
            }
        });

        jPanel2.add(rdbpeca);
        rdbpeca.setBounds(130, 30, 50, 15);

        txtmetro.setText("0.00");
        txtmetro.setEnabled(false);
        txtmetro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmetroFocusGained(evt);
            }
        });
        txtmetro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmetroKeyReleased(evt);
            }
        });

        jPanel2.add(txtmetro);
        txtmetro.setBounds(10, 50, 40, 20);

        txtkilo.setText("0.00");
        txtkilo.setEnabled(false);
        txtkilo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtkiloFocusGained(evt);
            }
        });
        txtkilo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtkiloKeyReleased(evt);
            }
        });

        jPanel2.add(txtkilo);
        txtkilo.setBounds(70, 50, 40, 20);

        txtpeca.setText("0");
        txtpeca.setEnabled(false);
        txtpeca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpecaFocusGained(evt);
            }
        });
        txtpeca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpecaKeyReleased(evt);
            }
        });

        jPanel2.add(txtpeca);
        txtpeca.setBounds(130, 50, 40, 20);

        jPanel3.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Unid. Medida"));
        GrupoMedida.add(rdbmetro1);
        rdbmetro1.setText("Metro");
        rdbmetro1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbmetro1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbmetro1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbmetro1ItemStateChanged(evt);
            }
        });

        jPanel3.add(rdbmetro1);
        rdbmetro1.setBounds(10, 30, 60, 15);

        GrupoMedida.add(rdbkilo1);
        rdbkilo1.setText("Kilo");
        rdbkilo1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbkilo1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbkilo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbkilo1ActionPerformed(evt);
            }
        });

        jPanel3.add(rdbkilo1);
        rdbkilo1.setBounds(70, 30, 50, 15);

        GrupoMedida.add(rdbpeca1);
        rdbpeca1.setText("Pe\u00e7a");
        rdbpeca1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbpeca1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbpeca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbpeca1ActionPerformed(evt);
            }
        });

        jPanel3.add(rdbpeca1);
        rdbpeca1.setBounds(130, 30, 50, 15);

        txtmetro1.setText("0.00");
        txtmetro1.setEnabled(false);
        txtmetro1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmetro1FocusGained(evt);
            }
        });
        txtmetro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmetro1KeyReleased(evt);
            }
        });

        jPanel3.add(txtmetro1);
        txtmetro1.setBounds(10, 50, 40, 20);

        txtkilo1.setText("0.00");
        txtkilo1.setEnabled(false);
        txtkilo1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtkilo1FocusGained(evt);
            }
        });
        txtkilo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtkilo1KeyReleased(evt);
            }
        });

        jPanel3.add(txtkilo1);
        txtkilo1.setBounds(70, 50, 40, 20);

        txtpeca1.setText("0");
        txtpeca1.setEnabled(false);
        txtpeca1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpeca1FocusGained(evt);
            }
        });
        txtpeca1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpeca1KeyReleased(evt);
            }
        });

        jPanel3.add(txtpeca1);
        txtpeca1.setBounds(130, 50, 40, 20);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(10, 100, 190, 80);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 100, 190, 80);

        jPanel4.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Fornecedor"));
        cmbrazao.setMaximumRowCount(5);
        cmbrazao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbrazaoActionPerformed(evt);
            }
        });

        jPanel4.add(cmbrazao);
        cmbrazao.setBounds(10, 50, 280, 20);

        jLabel5.setText("Raz\u00e3o Social");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(10, 30, 90, 14);

        ftxtcnpj.setEnabled(false);
        jPanel4.add(ftxtcnpj);
        ftxtcnpj.setBounds(320, 140, 120, 20);

        ftxtcnpj1.setEnabled(false);
        jPanel4.add(ftxtcnpj1);
        ftxtcnpj1.setBounds(320, 140, 120, 20);

        ftxtcnpj2.setEnabled(false);
        jPanel4.add(ftxtcnpj2);
        ftxtcnpj2.setBounds(300, 50, 120, 20);

        lblcodfornecedor.setText("Raz\u00e3o Social");
        jPanel4.add(lblcodfornecedor);
        lblcodfornecedor.setBounds(180, 50, 90, 14);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(210, 100, 430, 80);

        jPanel5.setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Nota Fiscal"));
        jPanel5.add(txtdoc);
        txtdoc.setBounds(10, 50, 50, 20);

        jLabel6.setText("N\u00b0/ Doc.");
        jPanel5.add(jLabel6);
        jLabel6.setBounds(10, 30, 60, 14);

        jPanel5.add(txtserie);
        txtserie.setBounds(70, 50, 40, 20);

        jLabel7.setText("S\u00e9rie");
        jPanel5.add(jLabel7);
        jLabel7.setBounds(70, 30, 60, 14);

        ftxtdataemissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusLost(evt);
            }
        });

        jPanel5.add(ftxtdataemissao);
        ftxtdataemissao.setBounds(120, 50, 80, 20);

        jLabel8.setText("Emiss\u00e3o");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(120, 30, 60, 14);

        jLabel9.setText("Entrada");
        jPanel5.add(jLabel9);
        jLabel9.setBounds(210, 30, 60, 14);

        ftxtdataentrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataentradaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataentradaFocusLost(evt);
            }
        });

        jPanel5.add(ftxtdataentrada);
        ftxtdataentrada.setBounds(210, 50, 80, 20);

        getContentPane().add(jPanel5);
        jPanel5.setBounds(10, 190, 300, 80);

        jPanel6.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Unit."));
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

        jPanel6.add(txtvalor);
        txtvalor.setBounds(10, 50, 50, 20);

        getContentPane().add(jPanel6);
        jPanel6.setBounds(320, 190, 70, 80);

        jPanel7.setLayout(null);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnotaFocusGained(evt);
            }
        });
        txtnota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnotaKeyReleased(evt);
            }
        });

        jPanel7.add(txtnota);
        txtnota.setBounds(10, 50, 50, 20);

        jPanel8.setLayout(null);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota1.setText("0.00");
        txtnota1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota1FocusGained(evt);
            }
        });

        jPanel8.add(txtnota1);
        txtnota1.setBounds(10, 50, 60, 20);

        jPanel7.add(jPanel8);
        jPanel8.setBounds(420, 190, 80, 80);

        jPanel11.setLayout(null);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota2.setText("0.00");
        txtnota2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota2FocusGained(evt);
            }
        });

        jPanel11.add(txtnota2);
        txtnota2.setBounds(10, 50, 50, 20);

        jPanel12.setLayout(null);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota4.setText("0.00");
        txtnota4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota4FocusGained(evt);
            }
        });

        jPanel12.add(txtnota4);
        txtnota4.setBounds(10, 50, 60, 20);

        jPanel11.add(jPanel12);
        jPanel12.setBounds(420, 190, 80, 80);

        jPanel7.add(jPanel11);
        jPanel11.setBounds(410, 190, 70, 80);

        getContentPane().add(jPanel7);
        jPanel7.setBounds(400, 190, 70, 80);

        jPanel9.setLayout(null);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagamento"));
        jPanel10.setLayout(null);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota3.setText("0.00");
        txtnota3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota3FocusGained(evt);
            }
        });

        jPanel10.add(txtnota3);
        txtnota3.setBounds(10, 50, 60, 20);

        jPanel9.add(jPanel10);
        jPanel10.setBounds(420, 190, 80, 80);

        buttonGroup1.add(rdbvista);
        rdbvista.setText("\u00c0 Vista");
        rdbvista.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbvista.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbvista.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbvistaItemStateChanged(evt);
            }
        });

        jPanel9.add(rdbvista);
        rdbvista.setBounds(10, 30, 70, 15);

        buttonGroup1.add(rdbfaturado);
        rdbfaturado.setText("Faturado");
        rdbfaturado.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbfaturado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbfaturado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbfaturadoItemStateChanged(evt);
            }
        });

        jPanel9.add(rdbfaturado);
        rdbfaturado.setBounds(10, 50, 70, 15);

        getContentPane().add(jPanel9);
        jPanel9.setBounds(480, 190, 90, 80);

        jPanel13.setLayout(null);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Parc."));
        jPanel14.setLayout(null);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota6.setText("0.00");
        txtnota6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota6FocusGained(evt);
            }
        });

        jPanel14.add(txtnota6);
        txtnota6.setBounds(10, 50, 60, 20);

        jPanel13.add(jPanel14);
        jPanel14.setBounds(420, 190, 80, 80);

        jPanel15.setLayout(null);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota7.setText("0.00");
        txtnota7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota7FocusGained(evt);
            }
        });

        jPanel15.add(txtnota7);
        txtnota7.setBounds(10, 50, 50, 20);

        jPanel16.setLayout(null);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Nota"));
        txtnota8.setText("0.00");
        txtnota8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnota8FocusGained(evt);
            }
        });

        jPanel16.add(txtnota8);
        txtnota8.setBounds(10, 50, 60, 20);

        jPanel15.add(jPanel16);
        jPanel16.setBounds(420, 190, 80, 80);

        jPanel13.add(jPanel15);
        jPanel15.setBounds(410, 190, 70, 80);

        cmbparcela.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        cmbparcela.setEnabled(false);
        cmbparcela.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbparcelaItemStateChanged(evt);
            }
        });

        jPanel13.add(cmbparcela);
        cmbparcela.setBounds(10, 40, 40, 20);

        lblteste.setText("teste");
        jPanel13.add(lblteste);
        lblteste.setBounds(10, 40, 25, 14);

        getContentPane().add(jPanel13);
        jPanel13.setBounds(580, 190, 60, 80);

        jPanel17.setLayout(null);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Vencimento"));
        ftxtvencimento1.setEnabled(false);
        ftxtvencimento1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtvencimento1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtvencimento1FocusLost(evt);
            }
        });

        jPanel17.add(ftxtvencimento1);
        ftxtvencimento1.setBounds(40, 30, 70, 20);

        jLabel12.setText("1\u00ba");
        jPanel17.add(jLabel12);
        jLabel12.setBounds(10, 30, 40, 14);

        jLabel14.setText("2\u00ba");
        jPanel17.add(jLabel14);
        jLabel14.setBounds(140, 30, 40, 14);

        ftxtvencimento2.setEnabled(false);
        ftxtvencimento2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtvencimento2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtvencimento2FocusLost(evt);
            }
        });

        jPanel17.add(ftxtvencimento2);
        ftxtvencimento2.setBounds(170, 30, 70, 20);

        ftxtvencimento3.setEnabled(false);
        ftxtvencimento3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtvencimento3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtvencimento3FocusLost(evt);
            }
        });

        jPanel17.add(ftxtvencimento3);
        ftxtvencimento3.setBounds(300, 30, 70, 20);

        jLabel15.setText("3\u00ba");
        jPanel17.add(jLabel15);
        jLabel15.setBounds(270, 30, 40, 14);

        jLabel16.setText("4\u00ba");
        jPanel17.add(jLabel16);
        jLabel16.setBounds(400, 30, 40, 14);

        ftxtvencimento4.setEnabled(false);
        ftxtvencimento4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtvencimento4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtvencimento4FocusLost(evt);
            }
        });

        jPanel17.add(ftxtvencimento4);
        ftxtvencimento4.setBounds(430, 30, 70, 20);

        jLabel17.setText("5\u00ba");
        jPanel17.add(jLabel17);
        jLabel17.setBounds(520, 30, 40, 14);

        ftxtvencimento5.setEnabled(false);
        ftxtvencimento5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtvencimento5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtvencimento5FocusLost(evt);
            }
        });

        jPanel17.add(ftxtvencimento5);
        ftxtvencimento5.setBounds(550, 30, 70, 20);

        getContentPane().add(jPanel17);
        jPanel17.setBounds(10, 280, 630, 60);

        jPanel18.setLayout(null);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Parcelas"));
        jLabel13.setText("1\u00ba");
        jPanel18.add(jLabel13);
        jLabel13.setBounds(10, 30, 40, 14);

        jLabel18.setText("2\u00ba");
        jPanel18.add(jLabel18);
        jLabel18.setBounds(140, 30, 40, 14);

        jLabel19.setText("3\u00ba");
        jPanel18.add(jLabel19);
        jLabel19.setBounds(270, 30, 40, 14);

        jLabel20.setText("4\u00ba");
        jPanel18.add(jLabel20);
        jLabel20.setBounds(400, 30, 40, 14);

        jLabel21.setText("5\u00ba");
        jPanel18.add(jLabel21);
        jLabel21.setBounds(520, 30, 40, 14);

        txtparcela1.setEnabled(false);
        txtparcela1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtparcela1FocusGained(evt);
            }
        });
        txtparcela1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtparcela1KeyReleased(evt);
            }
        });

        jPanel18.add(txtparcela1);
        txtparcela1.setBounds(40, 30, 70, 20);

        txtparcela2.setEnabled(false);
        txtparcela2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtparcela2FocusGained(evt);
            }
        });
        txtparcela2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtparcela2KeyReleased(evt);
            }
        });

        jPanel18.add(txtparcela2);
        txtparcela2.setBounds(170, 30, 70, 20);

        txtparcela3.setEnabled(false);
        txtparcela3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtparcela3FocusGained(evt);
            }
        });
        txtparcela3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtparcela3KeyReleased(evt);
            }
        });

        jPanel18.add(txtparcela3);
        txtparcela3.setBounds(300, 30, 70, 20);

        txtparcela4.setEnabled(false);
        txtparcela4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtparcela4FocusGained(evt);
            }
        });
        txtparcela4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtparcela4KeyReleased(evt);
            }
        });

        jPanel18.add(txtparcela4);
        txtparcela4.setBounds(430, 30, 70, 20);

        txtparcela5.setEnabled(false);
        txtparcela5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtparcela5FocusGained(evt);
            }
        });
        txtparcela5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtparcela5KeyReleased(evt);
            }
        });

        jPanel18.add(txtparcela5);
        txtparcela5.setBounds(550, 30, 70, 20);

        getContentPane().add(jPanel18);
        jPanel18.setBounds(10, 350, 630, 60);

        btncadastrar.setFont(new java.awt.Font("Dialog", 1, 12));
        btncadastrar.setLabel("Cadastrar");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });

        getContentPane().add(btncadastrar);
        btncadastrar.setBounds(100, 550, 80, 30);

        btninserir.setFont(new java.awt.Font("Dialog", 1, 12));
        btninserir.setLabel("Inserir");
        btninserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninserirActionPerformed(evt);
            }
        });

        getContentPane().add(btninserir);
        btninserir.setBounds(10, 550, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(190, 550, 80, 30);

        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(280, 550, 80, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Cod1", "Produto (A)", "Cod2", "Produto (T)", "Cod3", "Cor", "Metro", "Kilo", "Peça", "Tamanho", "V. Unit."
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
        jScrollPane1.setBounds(10, 420, 630, 120);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-665)/2, (screenSize.height-630)/2, 665, 630);
    }// </editor-fold>//GEN-END:initComponents
    public void enter(){
// Enter simula tecla Tab
        HashSet conj = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }
    private void txtparcela5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela5FocusGained
// TODO add your handling code here:
        txtparcela5.selectAll();
    }//GEN-LAST:event_txtparcela5FocusGained
    
    private void txtparcela4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela4FocusGained
// TODO add your handling code here:
        txtparcela4.selectAll();
    }//GEN-LAST:event_txtparcela4FocusGained
    
    private void txtparcela3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela3FocusGained
// TODO add your handling code here:
        txtparcela3.selectAll();
    }//GEN-LAST:event_txtparcela3FocusGained
    
    private void txtparcela2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela2FocusGained
// TODO add your handling code here:
        txtparcela2.selectAll();
    }//GEN-LAST:event_txtparcela2FocusGained
    
    private void txtparcela5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtparcela5KeyReleased
// TODO add your handling code here:
        txtparcela5.setText(txtparcela5.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtparcela5KeyReleased
    
    private void txtparcela4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtparcela4KeyReleased
// TODO add your handling code here:
        txtparcela4.setText(txtparcela4.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtparcela4KeyReleased
    
    private void txtparcela3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtparcela3KeyReleased
// TODO add your handling code here:
        txtparcela3.setText(txtparcela3.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtparcela3KeyReleased
    
    private void txtparcela2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtparcela2KeyReleased
// TODO add your handling code here:
        txtparcela2.setText(txtparcela2.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtparcela2KeyReleased
    
    private void txtparcela1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtparcela1KeyReleased
// TODO add your handling code here:
        
        txtparcela1.setText(txtparcela1.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtparcela1KeyReleased
    
    private void txtnotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnotaKeyReleased
// TODO add your handling code here:
        
        txtnota.setText(txtnota.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtnotaKeyReleased
    
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
    
    private void ftxtvencimento5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento5FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento5.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento5FocusLost
    
    private void ftxtvencimento4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento4FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento4.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento4FocusLost
    
    private void ftxtvencimento3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento3FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento3.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento3FocusLost
    
    private void ftxtvencimento2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento2FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento2.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento2FocusLost
    
    private void ftxtvencimento1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento1FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento1.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento1FocusLost
    
    private void ftxtdataentradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataentradaFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtdataentrada.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
        // ftxtdata1.setText("");
    }//GEN-LAST:event_ftxtdataentradaFocusLost
    
    private void ftxtdataemissaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataemissaoFocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtdataemissao.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
        // ftxtdata1.setText("");
        
    }//GEN-LAST:event_ftxtdataemissaoFocusLost
    
    private void cmbrazaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbrazaoActionPerformed
// TODO add your handling code here:
        
        if(iniciacombo == 1){
            try {
                
                con_fornecedor.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_fornecedor.resultset.getString("nm_razaosocial_fornecedor").equals(cmbrazao.getSelectedItem())){
                        igual = "s";
                    } else
                        con_fornecedor.resultset.next();
                }
                lblcodfornecedor.setText(con_fornecedor.resultset.getString(String.valueOf("cd_fornecedor")));
                ftxtcnpj2.setText(con_fornecedor.resultset.getString(String.valueOf("cd_cnpj_fornecedor")));
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        iniciacombo=1;
        
    }//GEN-LAST:event_cmbrazaoActionPerformed
    
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
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
// TODO add your handling code here:
        
        
        
        
    }//GEN-LAST:event_formWindowOpened
    
    private void cmbnulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnulaActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_cmbnulaActionPerformed
    
    private void cmbnulaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbnulaItemStateChanged
// TODO add your handling code here:
    }//GEN-LAST:event_cmbnulaItemStateChanged
    
    private void cmbaviamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbaviamentoActionPerformed
// TODO add your handling code here:
        if(iniciacombo == 1){
            try {
                
                con_aviamento.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_aviamento.resultset.getString("nm_aviamento").equals(cmbaviamento.getSelectedItem())){
                        igual = "s";
                    } else
                        con_aviamento.resultset.next();
                }
                lblcodaviamento.setText(con_aviamento.resultset.getString(String.valueOf("cd_aviamento")));
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        iniciacombo=1;
        
        
    }//GEN-LAST:event_cmbaviamentoActionPerformed
    
    private void cmbaviamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbaviamentoItemStateChanged
// TODO add your handling code here:
        
    }//GEN-LAST:event_cmbaviamentoItemStateChanged
    
    private void cmptipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmptipoActionPerformed
// TODO add your handling code here:
        
        
    }//GEN-LAST:event_cmptipoActionPerformed
    
    private void cmbprodutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbprodutoActionPerformed
// TODO add your handling code here:
        if(iniciacombo == 1){
            try {
                
                con_tecido.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_tecido.resultset.getString("nm_tecido").equals(cmbproduto.getSelectedItem())){
                        igual = "s";
                    } else
                        con_tecido.resultset.next();
                }
                lblcodproduto.setText(con_tecido.resultset.getString(String.valueOf("cd_tecido")));
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        iniciacombo=1;
    }//GEN-LAST:event_cmbprodutoActionPerformed
    
    private void cmbprodutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbprodutoItemStateChanged
        
    }//GEN-LAST:event_cmbprodutoItemStateChanged
    
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained
    
    
    
    
    private void cmptipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmptipoItemStateChanged
// TODO add your handling code here:
        if(cmptipo.getSelectedItem().equals("Aviamento")){
            lbltipoa.setText("1");
            lbltipot.setText("0");
            cmbproduto.setVisible(false);
            cmbaviamento.setVisible(true);
            cmbnula.setVisible(false);
            cmbproduto.setSelectedIndex(0);
            cmbtamanho.setEnabled(true);
            
        }
        if(cmptipo.getSelectedItem().equals("Tecido")){
            lbltipoa.setText("0");
            lbltipot.setText("1");
            cmbproduto.setVisible(true);
            cmbaviamento.setVisible(false);
            cmbaviamento.setSelectedIndex(0);
            cmbnula.setVisible(false);
            cmbtamanho.setEnabled(false);
        }
        if(cmptipo.getSelectedItem().equals("-")){
            lbltipot.setText("-");
            lbltipoa.setText("-");
            cmbproduto.setVisible(false);
            cmbaviamento.setVisible(false);
            cmbnula.setVisible(true);
            cmbproduto.setSelectedIndex(0);
            cmbaviamento.setSelectedIndex(0);
            
        }
    }//GEN-LAST:event_cmptipoItemStateChanged
    
    private void cmbparcelaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbparcelaItemStateChanged
// TODO add your handling code here:
        // isto serve para ativar o contador a partir da combo parcelas
        contador = cmbparcela.getSelectedItem();
        lblteste.setText((String) contador);
        if (cmbparcela.getSelectedItem().equals("5")) {
            
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento2.setEnabled(true);
            ftxtvencimento3.setEnabled(true);
            ftxtvencimento4.setEnabled(true);
            ftxtvencimento5.setEnabled(true);
            txtparcela1.setEnabled(true);
            txtparcela2.setEnabled(true);
            txtparcela3.setEnabled(true);
            txtparcela4.setEnabled(true);
            txtparcela5.setEnabled(true);
            //ftxtvencimento1.requestFocus();
        }
        if (cmbparcela.getSelectedItem().equals("4")) {
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento2.setEnabled(true);
            ftxtvencimento3.setEnabled(true);
            ftxtvencimento4.setEnabled(true);
            ftxtvencimento5.setEnabled(false);
            txtparcela1.setEnabled(true);
            txtparcela2.setEnabled(true);
            txtparcela3.setEnabled(true);
            txtparcela4.setEnabled(true);
            txtparcela5.setEnabled(false);
            //ftxtvencimento1.requestFocus();
        }
        if (cmbparcela.getSelectedItem().equals("3")) {
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento2.setEnabled(true);
            ftxtvencimento3.setEnabled(true);
            ftxtvencimento4.setEnabled(false);
            ftxtvencimento5.setEnabled(false);
            txtparcela1.setEnabled(true);
            txtparcela2.setEnabled(true);
            txtparcela3.setEnabled(true);
            txtparcela4.setEnabled(false);
            txtparcela5.setEnabled(false);
            ftxtvencimento1.requestFocus();
        }
        if (cmbparcela.getSelectedItem().equals("2")) {
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento2.setEnabled(true);
            ftxtvencimento3.setEnabled(false);
            ftxtvencimento4.setEnabled(false);
            ftxtvencimento5.setEnabled(false);
            txtparcela1.setEnabled(true);
            txtparcela2.setEnabled(true);
            txtparcela3.setEnabled(false);
            txtparcela4.setEnabled(false);
            txtparcela5.setEnabled(false);
            //ftxtvencimento1.requestFocus();
        }
        if (cmbparcela.getSelectedItem().equals("1")) {
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento2.setEnabled(false);
            ftxtvencimento3.setEnabled(false);
            ftxtvencimento4.setEnabled(false);
            ftxtvencimento5.setEnabled(false);
            txtparcela1.setEnabled(true);
            txtparcela2.setEnabled(false);
            txtparcela3.setEnabled(false);
            txtparcela4.setEnabled(false);
            txtparcela5.setEnabled(false);
            //ftxtvencimento1.requestFocus();
            
            
            
        }
    }//GEN-LAST:event_cmbparcelaItemStateChanged
    
    private void txtparcela1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela1FocusGained
// TODO add your handling code here:
        txtparcela1.selectAll();
    }//GEN-LAST:event_txtparcela1FocusGained
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
        
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        txtmetro.setText("0.00");
        txtkilo.setText("0.00");
        txtpeca.setText("0");
        ftxtcnpj.setText("");
        cmbrazao.setSelectedItem("");
        ftxtdataemissao.setText("");
        ftxtdataentrada.setText("");
        txtdoc.setText("");
        txtvalor.setText("");
        GrupoMedida.clearSelection();
        buttonGroup1.clearSelection();
        ftxtvencimento1.setEnabled(false);
        ftxtvencimento2.setEnabled(false);
        ftxtvencimento3.setEnabled(false);
        ftxtvencimento4.setEnabled(false);
        ftxtvencimento5.setEnabled(false);
        txtparcela1.setEnabled(false);
        txtparcela2.setEnabled(false);
        txtparcela3.setEnabled(false);
        txtparcela4.setEnabled(false);
        txtparcela5.setEnabled(false);
        ftxtvencimento1.setText("");
        ftxtvencimento2.setText("");
        ftxtvencimento3.setText("");
        ftxtvencimento4.setText("");
        ftxtvencimento5.setText("");
        txtparcela1.setText("");
        txtparcela2.setText("");
        txtparcela3.setText("");
        txtparcela4.setText("");
        txtparcela5.setText("");
        rdbmetro.setSelected(false);
        rdbkilo.setSelected(false);
        rdbpeca.setSelected(false);
        rdbvista.setSelected(false);
        rdbfaturado.setSelected(false);
        txtkilo.setEnabled(false);
        txtpeca.setEnabled(false);
        txtmetro.setEnabled(false);
        txtkilo.setEnabled(false);
        txtpeca.setEnabled(false);
        txtvalor.setText("");
        txtnota.setText("");
        txtserie.setText("");
        cmbparcela.setSelectedIndex(0);
        cmbparcela.setEnabled(false);
        cmbrazao.setSelectedIndex(0);
        cmptipo.setSelectedIndex(0);
        cmbtamanho.setSelectedIndex(0);
        cmbcor.setSelectedIndex(0);
        cmptipo.requestFocus();
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btninserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninserirActionPerformed
// TODO add your handling code here:
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        if(cmptipo.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null, "Selecione um tipo de produto!");
            cmptipo.requestFocus();
        }
        if(cmptipo.getSelectedItem().equals("Aviamento")){
            modelo.addRow(new Object []{cmptipo.getSelectedItem(),lblcodaviamento.getText(),cmbaviamento.getSelectedItem(),0,"-",lblcodcor.getText(),cmbcor.getSelectedItem(),txtmetro.getText(),txtkilo.getText(),txtpeca.getText(),cmbtamanho.getSelectedItem(),txtvalor.getText()});
            
            cmptipo.setSelectedIndex(0);
            cmbproduto.setSelectedIndex(0);
            cmbcor.setSelectedIndex(0);
            cmbtamanho.setSelectedIndex(0);
            cmbtamanho.setEnabled(false);
            GrupoMedida.clearSelection();
            txtpeca.setEnabled(false);
            txtmetro.setEnabled(false);
            txtkilo.setEnabled(false);
            txtpeca.setText("");
            txtkilo.setText("");
            txtmetro.setText("");
        }
        if(cmptipo.getSelectedItem().equals("Tecido")){
            modelo.addRow(new Object []{cmptipo.getSelectedItem(),0,"-",lblcodproduto.getText(),cmbproduto.getSelectedItem(),lblcodcor.getText(),cmbcor.getSelectedItem(),txtmetro.getText(),txtkilo.getText(),txtpeca.getText(),cmbtamanho.getSelectedItem(),txtvalor.getText()});
            cmptipo.setSelectedIndex(0);
            cmbproduto.setSelectedIndex(0);
            cmbcor.setSelectedIndex(0);
            cmbtamanho.setSelectedIndex(0);
            cmbtamanho.setEnabled(false);
            GrupoMedida.clearSelection();
            txtpeca.setEnabled(false);
            txtmetro.setEnabled(false);
            txtkilo.setEnabled(false);
            txtpeca.setText("");
            txtkilo.setText("");
            txtmetro.setText("");
        }
        
    }//GEN-LAST:event_btninserirActionPerformed
    
    
    
    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
// TODO add your handling code here:
        
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        Date data1 = null;
        Date data2 = null;
        Date data3 = null;
        Date data4 = null;
        Date data5 = null;
        
        try {
            data = (Date) fmt.parse(ftxtdataentrada.getText());
            
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        try {
            
            if ((cmbparcela.getSelectedItem().equals("0")) && (rdbvista.isSelected()== true)){
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(txtdoc.getText())+"','"+
                            txtserie.getText()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(txtnota.getText())+"','" +
                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
                            data+"')";
                    con_materia.statement.executeUpdate(sqlinsertmateria);
                    
                }
            }
//            if (cmptipo.getSelectedItem().equals("Tecido")){
//                
//                for (int i = 0; i < jTable1.getRowCount(); i++) {
//                    
//                    //  INSERT INTO MateriaPrima
//                    
//                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
//                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
//                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
//                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
//                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
//                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
//                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
//                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
//                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString()) +"','"+
//                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
//                            Integer.parseInt(txtdoc.getText())+"','"+
//                            txtserie.getText()+"','"+
//                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
//                            Double.parseDouble(txtnota.getText())+"','" +
//                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
//                            data+"')";
//                    con_materia.statement.executeUpdate(sqlinsertmateria);
//                    
//                }
//            }
            try {
                
                data1 = (Date) fmt.parse(ftxtvencimento1.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            
            
            
            if (cmbparcela.getSelectedItem().equals("1")) {
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(txtdoc.getText())+"','"+
                            txtserie.getText()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(txtnota.getText())+"','" +
                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
                            data+"')";
                    con_materia.statement.executeUpdate(sqlinsertmateria);
                    
                }
                
                String sqlinsertcontap = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data1+"','"+
                        Double.parseDouble(txtparcela1.getText())+"')";
                con_contapagar1.statement.executeUpdate(sqlinsertcontap);
                
            }
            try {
                
                
                data2 = (Date) fmt.parse(ftxtvencimento2.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            
            if (cmbparcela.getSelectedItem().equals("2")) {
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(txtdoc.getText())+"','"+
                            txtserie.getText()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(txtnota.getText())+"','" +
                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
                            data+"')";
                    con_materia.statement.executeUpdate(sqlinsertmateria);
                    
                }
                
                String sqlinsertcontap = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data1+"','"+
                        Double.parseDouble(txtparcela1.getText())+"')";
                con_contapagar2.statement.executeUpdate(sqlinsertcontap);
                
                String sqlinsertcontap2 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data2+"','"+
                        Double.parseDouble(txtparcela2.getText())+"')";
                con_contapagar2.statement.executeUpdate(sqlinsertcontap2);
                
                
            }
            try {
                
                data3 = (Date) fmt.parse(ftxtvencimento3.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            
            if (cmbparcela.getSelectedItem().equals("3")) {
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(txtdoc.getText())+"','"+
                            txtserie.getText()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(txtnota.getText())+"','" +
                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
                            data+"')";
                    con_materia.statement.executeUpdate(sqlinsertmateria);
                    
                }
                
                String sqlinsertcontap = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data1+"','"+
                        Double.parseDouble(txtparcela1.getText())+"')";
                con_contapagar1.statement.executeUpdate(sqlinsertcontap);
                
                String sqlinsertcontap2 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data2+"','"+
                        Double.parseDouble(txtparcela2.getText())+"')";
                con_contapagar2.statement.executeUpdate(sqlinsertcontap2);
                
                String sqlinsertcontap3 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data3+"','"+
                        Double.parseDouble(txtparcela3.getText())+"')";
                con_contapagar3.statement.executeUpdate(sqlinsertcontap3);
                
            }
            try {
                
                data4 = (Date) fmt.parse(ftxtvencimento4.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            
            if (cmbparcela.getSelectedItem().equals("4")) {
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(txtdoc.getText())+"','"+
                            txtserie.getText()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(txtnota.getText())+"','" +
                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
                            data+"')";
                    con_materia.statement.executeUpdate(sqlinsertmateria);
                    
                }
                
                String sqlinsertcontap = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data1+"','"+
                        Double.parseDouble(txtparcela1.getText())+"')";
                con_contapagar1.statement.executeUpdate(sqlinsertcontap);
                
                String sqlinsertcontap2 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data2+"','"+
                        Double.parseDouble(txtparcela2.getText())+"')";
                con_contapagar2.statement.executeUpdate(sqlinsertcontap2);
                
                String sqlinsertcontap3 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data3+"','"+
                        Double.parseDouble(txtparcela3.getText())+"')";
                con_contapagar3.statement.executeUpdate(sqlinsertcontap3);
                
                String sqlinsertcontap4 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data4+"','"+
                        Double.parseDouble(txtparcela4.getText())+"')";
                con_contapagar4.statement.executeUpdate(sqlinsertcontap4);
                
            }
            try {
                
                
                data5 = (Date) fmt.parse(ftxtvencimento5.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            if (cmbparcela.getSelectedItem().equals("5")) {
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO MateriaPrima (cd_fornecedor, nm_tipo, cd_tecido, cd_aviamento, nm_tamanho, qt_metro, qt_kilo, qt_peca, cd_cor, cd_notafiscal, nm_serie, vl_valor, vl_total, qt_parcela, dt_entrada) values ('"+
                            /* codigo Fornecedor */   Integer.parseInt(lblcodfornecedor.getText())+"','"+
                            /* nome tipo */          jTable1.getValueAt(i, 0).toString().toUpperCase()+"','"+
                            /* codigo tecido */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                            /*codigo aviamento */Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 10).toString().toUpperCase()+"','"+
                            /*Metro */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*Kilo */             Double.parseDouble(jTable1.getValueAt(i, 8).toString()) +"','"+
                            /*Peça */             Integer.parseInt(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*Codigo cor */       Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            Integer.parseInt(txtdoc.getText())+"','"+
                            txtserie.getText()+"','"+
                            Double.parseDouble(jTable1.getValueAt(i, 11).toString())+"','"+
                            Double.parseDouble(txtnota.getText())+"','" +
                            cmbparcela.getSelectedItem().toString().toUpperCase()+"','"+
                            data+"')";
                    con_materia.statement.executeUpdate(sqlinsertmateria);
                    
                }
                
                String sqlinsertcontap = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data1+"','"+
                        Double.parseDouble(txtparcela1.getText())+"')";
                con_contapagar.statement.executeUpdate(sqlinsertcontap);
                
                String sqlinsertcontap2 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data2+"','"+
                        Double.parseDouble(txtparcela2.getText())+"')";
                con_contapagar.statement.executeUpdate(sqlinsertcontap2);
                
                String sqlinsertcontap3 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data3+"','"+
                        Double.parseDouble(txtparcela3.getText())+"')";
                con_contapagar.statement.executeUpdate(sqlinsertcontap3);
                
                String sqlinsertcontap4 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data4+"','"+
                        Double.parseDouble(txtparcela4.getText())+"')";
                con_contapagar.statement.executeUpdate(sqlinsertcontap4);
                
                String sqlinsertcontap5 = "INSERT INTO ContaPagar (cd_notafiscal, cd_fornecedor, dt_dtcontapagar, vl_contapagar) VALUES( '"+
                        Integer.parseInt(txtdoc.getText())+"','" +
                        Integer.parseInt(lblcodfornecedor.getText())+"','"+
                        data5+"','"+
                        Double.parseDouble(txtparcela5.getText())+"')";
                con_contapagar.statement.executeUpdate(sqlinsertcontap5);
                
            }
            ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
            jTable1.updateUI();
            txtmetro.setText("0.00");
            txtkilo.setText("0.00");
            txtpeca.setText("0");
            ftxtcnpj.setText("");
            cmbrazao.setSelectedItem("");
            ftxtdataemissao.setText("");
            ftxtdataentrada.setText("");
            txtdoc.setText("");
            txtvalor.setText("0.00");
            ftxtvencimento1.setText("");
            ftxtvencimento2.setText("");
            ftxtvencimento3.setText("");
            ftxtvencimento4.setText("");
            ftxtvencimento5.setText("");
            ftxtvencimento1.setEnabled(false);
            ftxtvencimento2.setEnabled(false);
            ftxtvencimento3.setEnabled(false);
            ftxtvencimento4.setEnabled(false);
            ftxtvencimento5.setEnabled(false);
            txtparcela1.setText("");
            txtparcela2.setText("");
            txtparcela3.setText("");
            txtparcela4.setText("");
            txtparcela5.setText("");
            txtparcela1.setEnabled(false);
            txtparcela2.setEnabled(false);
            txtparcela3.setEnabled(false);
            txtparcela4.setEnabled(false);
            txtparcela5.setEnabled(false);
            rdbmetro.setSelected(false);
            rdbkilo.setSelected(false);
            rdbpeca.setSelected(false);
            rdbvista.setSelected(false);
            rdbfaturado.setSelected(false);
            txtkilo.setEnabled(false);
            txtpeca.setEnabled(false);
            txtmetro.setEnabled(false);
            txtvalor.setText("");
            txtnota.setText("");
            txtserie.setText("");
            cmbparcela.setSelectedIndex(0);
            cmbparcela.setEnabled(false);
            cmbrazao.setSelectedIndex(0);
            cmptipo.setSelectedIndex(0);
            cmbtamanho.setSelectedIndex(0);
            cmbcor.setSelectedIndex(0);
            cmptipo.requestFocus();
            
            JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
    }//GEN-LAST:event_btncadastrarActionPerformed
    
    private void ftxtvencimento5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento5FocusGained
// TODO add your handling code here:
        ftxtvencimento5.selectAll();
        
    }//GEN-LAST:event_ftxtvencimento5FocusGained
    
    private void ftxtvencimento4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento4FocusGained
// TODO add your handling code here:
        ftxtvencimento4.selectAll();
        
    }//GEN-LAST:event_ftxtvencimento4FocusGained
    
    private void ftxtvencimento3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento3FocusGained
// TODO add your handling code here:
        ftxtvencimento3.selectAll();
        
    }//GEN-LAST:event_ftxtvencimento3FocusGained
    
    private void ftxtvencimento2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento2FocusGained
// TODO add your handling code here:
        ftxtvencimento2.selectAll();
        
    }//GEN-LAST:event_ftxtvencimento2FocusGained
    
    private void ftxtvencimento1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento1FocusGained
// TODO add your handling code here:
        ftxtvencimento1.selectAll();
        ftxtvencimento1.setText(ftxtdataentrada.getText());
        ftxtvencimento2.setText(ftxtdataentrada.getText());
        ftxtvencimento3.setText(ftxtdataentrada.getText());
        ftxtvencimento4.setText(ftxtdataentrada.getText());
        ftxtvencimento5.setText(ftxtdataentrada.getText());
    }//GEN-LAST:event_ftxtvencimento1FocusGained
    
    private void rdbfaturadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbfaturadoItemStateChanged
// TODO add your handling code here:
        if(rdbfaturado.isSelected() && txtnota.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o valor da nota!");
            txtnota.requestFocus();
            cmbparcela.setEnabled(true);
            cmbparcela.setSelectedIndex(1);
            buttonGroup1.clearSelection();
        }
        if(rdbfaturado.isSelected()&& txtvalor.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o valor unitário!");
            txtnota.requestFocus();
            cmbparcela.setEnabled(true);
            cmbparcela.setSelectedIndex(1);
            buttonGroup1.clearSelection();
            
            
            
        } else{
            cmbparcela.setEnabled(true);
            cmbparcela.setSelectedIndex(1);
            txtparcela1.setEnabled(true);
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento1.setText(ftxtdataentrada.getText());
            ftxtvencimento2.setText(ftxtdataentrada.getText());
            ftxtvencimento3.setText(ftxtdataentrada.getText());
            ftxtvencimento4.setText(ftxtdataentrada.getText());
            ftxtvencimento5.setText(ftxtdataentrada.getText());
            
        }
    }//GEN-LAST:event_rdbfaturadoItemStateChanged
    
    private void rdbvistaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbvistaItemStateChanged
// TODO add your handling code here:
        if(rdbvista.isSelected()&& txtnota.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o valor da nota!");
            txtnota.requestFocus();
            cmbparcela.setEnabled(false);
            cmbparcela.setSelectedIndex(0);
            buttonGroup1.clearSelection();
        }
        if(rdbvista.isSelected()&& txtvalor.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite o valor unitário!");
            txtnota.requestFocus();
            cmbparcela.setEnabled(false);
            cmbparcela.setSelectedIndex(0);
            buttonGroup1.clearSelection();
        }
        
        else{
            cmbparcela.setEnabled(false);
            ftxtvencimento1.setText(ftxtdataemissao.getText());
            ftxtvencimento2.setText(ftxtdataentrada.getText());
            ftxtvencimento3.setText(ftxtdataentrada.getText());
            ftxtvencimento4.setText(ftxtdataentrada.getText());
            ftxtvencimento5.setText(ftxtdataentrada.getText());
            cmbparcela.setSelectedIndex(0);
            ftxtvencimento1.setEnabled(true);
            ftxtvencimento2.setEnabled(false);
            ftxtvencimento3.setEnabled(false);
            ftxtvencimento4.setEnabled(false);
            ftxtvencimento5.setEnabled(false);
            txtparcela1.setEnabled(true);
            txtparcela2.setEnabled(false);
            txtparcela3.setEnabled(false);
            txtparcela4.setEnabled(false);
            txtparcela5.setEnabled(false);
            //ftxtvencimento1.requestFocus();
            txtparcela1.setEnabled(true);
            ftxtvencimento1.setEnabled(true);
            // isto serve para ativar o contador a partir da combo parcelas
            contador = (String) cmbparcela.getSelectedItem();
            lblteste.setText((String) contador);
            
        }
    }//GEN-LAST:event_rdbvistaItemStateChanged
    
    private void txtnota8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota8FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota8FocusGained
    
    private void txtnota7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota7FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota7FocusGained
    
    private void txtnota6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota6FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota6FocusGained
    
    private void txtnota4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota4FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota4FocusGained
    
    private void txtnota2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota2FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota2FocusGained
    
    private void txtnota3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota3FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota3FocusGained
    
    private void txtnota1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota1FocusGained
    
    private void txtnotaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnotaFocusGained
// TODO add your handling code here:
        txtnota.selectAll();
    }//GEN-LAST:event_txtnotaFocusGained
    
    private void ftxtdataentradaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataentradaFocusGained
// TODO add your handling code here:
        ftxtdataentrada.selectAll();
    }//GEN-LAST:event_ftxtdataentradaFocusGained
    
    private void ftxtdataemissaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtdataemissaoFocusGained
// TODO add your handling code here:
        ftxtdataemissao.selectAll();
    }//GEN-LAST:event_ftxtdataemissaoFocusGained
    
    private void txtvalorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtvalorFocusGained
// TODO add your handling code here:
        txtvalor.selectAll();
        
    }//GEN-LAST:event_txtvalorFocusGained
    
    private void txtpeca1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpeca1KeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_txtpeca1KeyReleased
    
    private void txtpeca1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpeca1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtpeca1FocusGained
    
    private void txtkilo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkilo1KeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_txtkilo1KeyReleased
    
    private void txtkilo1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtkilo1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtkilo1FocusGained
    
    private void txtmetro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmetro1KeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_txtmetro1KeyReleased
    
    private void txtmetro1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmetro1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtmetro1FocusGained
    
    private void rdbpeca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbpeca1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_rdbpeca1ActionPerformed
    
    private void rdbkilo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbkilo1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_rdbkilo1ActionPerformed
    
    private void rdbmetro1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbmetro1ItemStateChanged
// TODO add your handling code here:
    }//GEN-LAST:event_rdbmetro1ItemStateChanged
    
    private void txtpecaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpecaFocusGained
// TODO add your handling code here:
        txtpeca.selectAll();
    }//GEN-LAST:event_txtpecaFocusGained
    
    private void txtkiloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtkiloFocusGained
// TODO add your handling code here:
        txtkilo.selectAll();
    }//GEN-LAST:event_txtkiloFocusGained
    
    private void txtmetroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmetroFocusGained
// TODO add your handling code here:
        txtmetro.selectAll();
    }//GEN-LAST:event_txtmetroFocusGained
    
    private void txtpecaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpecaKeyReleased
// TODO add your handling code here:
        txtpeca.setText(txtpeca.getText().replace(",", "")
        .replace(".", "").replace("q", "").replace("w", "")
        .replace("e", "").replace("r", "").replace("t", "")
        .replace("y", "").replace("u", "").replace("i", "")
        .replace("o", "").replace("p", "").replace("´", "")
        .replace("[", "").replace("a", "").replace("s", "")
        .replace("d", "").replace("f", "").replace("g", "")
        .replace("h", "").replace("j", "").replace("k", "")
        .replace("l", "").replace("ç", "").replace("~", "")
        .replace("]", "").replace("|", "").replace("z", "")
        .replace("x", "").replace("c", "").replace("v", "")
        .replace("b", "").replace("n", "").replace("m", "")
        .replace(";", "").replace("<", "").replace(">", "")
        .replace("/", "").replace("^", "").replace("}", "")
        .replace("º", "").replace("{", "").replace("ª", "")
        .replace("'", "").replace("!", "").replace("@", "")
        .replace("$", "").replace("%", "").replace("¨", "")
        .replace("&", "").replace("*", "").replace("(", "")
        .replace(")", "").replace("+", "").replace("-", "")
        .replace("_", "").replace(")", "").replace("#", "")
        .replace("?", "").replace("°", ""));
    }//GEN-LAST:event_txtpecaKeyReleased
    
    private void txtkiloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkiloKeyReleased
// TODO add your handling code here:
        txtkilo.setText(txtkilo.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtkiloKeyReleased
    
    private void txtmetroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmetroKeyReleased
// TODO add your handling code here:
        
        txtmetro.setText(txtmetro.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtmetroKeyReleased
    
    private void rdbpecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbpecaActionPerformed
// TODO add your handling code here:
        if(rdbpeca.isSelected()){
            
            
            
            txtpeca.requestFocus();
            txtkilo.setEnabled(false);
            txtmetro.setText("0.00");
            txtkilo.setText("0.00");
            txtmetro.setEnabled(false);
            txtpeca.setEnabled(true);
        }
    }//GEN-LAST:event_rdbpecaActionPerformed
    
    private void rdbkiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbkiloActionPerformed
// TODO add your handling code here:
        if(rdbkilo.isSelected()){
            
            
            txtkilo.requestFocus();
            
            txtmetro.setText("0.00");
            txtpeca.setText("0");
            txtkilo.setEnabled(true);
            txtmetro.setEnabled(false);
            txtpeca.setEnabled(false);
        }
        
    }//GEN-LAST:event_rdbkiloActionPerformed
    
    private void rdbmetroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbmetroItemStateChanged
// TODO add your handling code here:
        
        if(rdbmetro.isSelected()){
            
            
            txtkilo.setText("0.00");
            txtpeca.setText("0");
            txtmetro.requestFocus();
            txtmetro.setEnabled(true);
            txtkilo.setEnabled(false);
            txtpeca.setEnabled(false);
        }
        
    }//GEN-LAST:event_rdbmetroItemStateChanged
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroMateriaPrima().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoMedida;
    private java.awt.Button btncadastrar;
    private java.awt.Button btninserir;
    private java.awt.Button btnlimpar;
    private java.awt.Button btnsair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbaviamento;
    private javax.swing.JComboBox cmbcor;
    private javax.swing.JComboBox cmbnula;
    private javax.swing.JComboBox cmbparcela;
    private javax.swing.JComboBox cmbproduto;
    private javax.swing.JComboBox cmbrazao;
    private javax.swing.JComboBox cmbtamanho;
    private javax.swing.JComboBox cmptipo;
    private javax.swing.JFormattedTextField ftxtcnpj;
    private javax.swing.JFormattedTextField ftxtcnpj1;
    private javax.swing.JFormattedTextField ftxtcnpj2;
    private javax.swing.JFormattedTextField ftxtdataemissao;
    private javax.swing.JFormattedTextField ftxtdataentrada;
    private javax.swing.JFormattedTextField ftxtvencimento1;
    private javax.swing.JFormattedTextField ftxtvencimento2;
    private javax.swing.JFormattedTextField ftxtvencimento3;
    private javax.swing.JFormattedTextField ftxtvencimento4;
    private javax.swing.JFormattedTextField ftxtvencimento5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblcodaviamento;
    private javax.swing.JLabel lblcodcor;
    private javax.swing.JLabel lblcodfornecedor;
    private javax.swing.JLabel lblcodproduto;
    private javax.swing.JLabel lblteste;
    private javax.swing.JLabel lbltipoa;
    private javax.swing.JLabel lbltipot;
    private javax.swing.JRadioButton rdbfaturado;
    private javax.swing.JRadioButton rdbkilo;
    private javax.swing.JRadioButton rdbkilo1;
    private javax.swing.JRadioButton rdbmetro;
    private javax.swing.JRadioButton rdbmetro1;
    private javax.swing.JRadioButton rdbpeca;
    private javax.swing.JRadioButton rdbpeca1;
    private javax.swing.JRadioButton rdbvista;
    private javax.swing.JTextField txtdoc;
    private javax.swing.JTextField txtkilo;
    private javax.swing.JTextField txtkilo1;
    private javax.swing.JTextField txtmetro;
    private javax.swing.JTextField txtmetro1;
    private javax.swing.JTextField txtnota;
    private javax.swing.JTextField txtnota1;
    private javax.swing.JTextField txtnota2;
    private javax.swing.JTextField txtnota3;
    private javax.swing.JTextField txtnota4;
    private javax.swing.JTextField txtnota6;
    private javax.swing.JTextField txtnota7;
    private javax.swing.JTextField txtnota8;
    private javax.swing.JTextField txtparcela1;
    private javax.swing.JTextField txtparcela2;
    private javax.swing.JTextField txtparcela3;
    private javax.swing.JTextField txtparcela4;
    private javax.swing.JTextField txtparcela5;
    private javax.swing.JTextField txtpeca;
    private javax.swing.JTextField txtpeca1;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
    
}
