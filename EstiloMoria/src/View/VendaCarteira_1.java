/*
 * VendaBalca.java
 *
 * Created on 12 de Setembro de 2011, 10:29
 */

package View;
import Relatorio.Rel_Venda_Carteira;
import Relatorio.Rel_Venda_Carteira_1;
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
public class VendaCarteira_1 extends javax.swing.JFrame {
    private MaskFormatter FormatoData, FormatoCpf, FormatoCnpj, FormatoCep, FormatoTel1, FormatoTel2, FormatoTel3, FormatoTel4;
    private conexao con_cliente, con_contapagar1, con_produto,con_produto1,con_saldo, con_saldo1,con_cor, con_email,con_vendedor, con_data, con_orcamento,con_produtoacbado;
    final DefaultTableModel model = new DefaultTableModel();
    private Object contador;
    private int iniciacombo=0;
    private int numeroRegistros=1;
    private int qtdestoquetinhap=0;
    private int qtdestoquetinham=0;
    private int qtdestoquetinhag=0;
    private int qtdestoquetinhagg=0;
    private int qtdestoquetinhaeg=0;
    private int qtdestoquetinhaexg=0;
    data mostra_data;
    /** Creates new form VendaBalca */
    public VendaCarteira_1() {
        initComponents();
        setResizable(false);
        txtnome.setVisible(false);
        con_produtoacbado = new conexao();
        con_produtoacbado.conecta();
        con_saldo = new conexao();
        con_saldo.conecta();
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        ftxtdataemissao.setText(formatador.format(data));
        con_cor=new conexao();
        con_cor.conecta();
        con_orcamento=new conexao();
        con_orcamento.conecta();
        con_produto=new conexao();
        con_produto.conecta();
        con_produto1=new conexao();
        con_produto1.conecta();
        con_cliente=new conexao();
        con_cliente.conecta();
        con_contapagar1=new conexao();
        con_contapagar1.conecta();
        con_contapagar1.executeSQL("select * from contareceber1");
        con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
        con_cor.executeSQL("select * from cor order by nm_cor");
        con_produto.executeSQL("select * from produto order by nm_produto");
        con_produto1.executeSQL("select * from produto order by nm_produto");
        con_orcamento.executeSQL("select * from vendacarteira");
        try {
            while(con_cliente.resultset.next())
                cmbnome.addItem(con_cliente.resultset.getString("nm_cliente"));
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbnome = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
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
        txtnome = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cmbproduto = new javax.swing.JComboBox();
        cmbcor = new javax.swing.JComboBox();
        lblcodcor = new javax.swing.JLabel();
        lblcodproduto8 = new javax.swing.JLabel();
        lblcodproduto9 = new javax.swing.JLabel();
        txtcodproduto = new javax.swing.JTextField();
        cmbtamanho = new javax.swing.JComboBox();
        lblcodproduto10 = new javax.swing.JLabel();
        txtqtd = new javax.swing.JTextField();
        lblcodproduto11 = new javax.swing.JLabel();
        txtunitario = new javax.swing.JTextField();
        lblcodproduto12 = new javax.swing.JLabel();
        txtdesc = new javax.swing.JTextField();
        lblcodproduto13 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        lblcodproduto14 = new javax.swing.JLabel();
        lblcodproduto16 = new javax.swing.JLabel();
        lblqtd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btninserir = new java.awt.Button();
        btncadastrar = new java.awt.Button();
        btnlimpar = new java.awt.Button();
        btnsair = new java.awt.Button();
        jLabel3 = new javax.swing.JLabel();
        lblsaldo = new javax.swing.JLabel();
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
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txtnota6 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        txtnota7 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        txtnota8 = new javax.swing.JTextField();
        cmbparcela = new javax.swing.JComboBox();
        lblteste = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda Carteira");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        txtcodigo.setEnabled(false);
        jPanel1.add(txtcodigo);
        txtcodigo.setBounds(10, 40, 50, 20);

        jLabel1.setText("C\u00f3digo");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 50, 14);

        cmbnome.setMaximumRowCount(5);
        cmbnome.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbnomeItemStateChanged(evt);
            }
        });
        cmbnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnomeActionPerformed(evt);
            }
        });
        cmbnome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbnomeFocusLost(evt);
            }
        });

        jPanel1.add(cmbnome);
        cmbnome.setBounds(70, 40, 490, 20);

        jLabel2.setText("Nome");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 20, 50, 14);

        ftxtdataemissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusLost(evt);
            }
        });

        jPanel1.add(ftxtdataemissao);
        ftxtdataemissao.setBounds(570, 40, 80, 20);

        jLabel10.setText("Data");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(570, 20, 60, 14);

        txtnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomeActionPerformed(evt);
            }
        });

        jPanel1.add(txtnome);
        txtnome.setBounds(200, 40, 150, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 670, 70);

        jPanel4.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        cmbproduto.setMaximumRowCount(5);
        cmbproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbprodutoActionPerformed(evt);
            }
        });
        cmbproduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbprodutoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbprodutoFocusLost(evt);
            }
        });

        jPanel4.add(cmbproduto);
        cmbproduto.setBounds(70, 40, 220, 20);

        cmbcor.setMaximumRowCount(5);
        cmbcor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcorActionPerformed(evt);
            }
        });
        cmbcor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbcorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbcorFocusLost(evt);
            }
        });

        jPanel4.add(cmbcor);
        cmbcor.setBounds(300, 40, 280, 20);

        lblcodcor.setText("0");
        jPanel4.add(lblcodcor);
        lblcodcor.setBounds(340, 40, 6, 14);

        lblcodproduto8.setText("Produto");
        jPanel4.add(lblcodproduto8);
        lblcodproduto8.setBounds(70, 20, 60, 14);

        lblcodproduto9.setText("Cor");
        jPanel4.add(lblcodproduto9);
        lblcodproduto9.setBounds(300, 20, 60, 14);

        txtcodproduto.setEditable(false);
        jPanel4.add(txtcodproduto);
        txtcodproduto.setBounds(10, 40, 50, 20);

        cmbtamanho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P", "M", "G", "GG", "EG", "EXG" }));
        cmbtamanho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtamanhoActionPerformed(evt);
            }
        });

        jPanel4.add(cmbtamanho);
        cmbtamanho.setBounds(10, 90, 60, 20);

        lblcodproduto10.setText("Tamanho");
        jPanel4.add(lblcodproduto10);
        lblcodproduto10.setBounds(10, 70, 60, 14);

        txtqtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtqtdKeyTyped(evt);
            }
        });

        jPanel4.add(txtqtd);
        txtqtd.setBounds(80, 90, 40, 20);

        lblcodproduto11.setText("Qtd.");
        jPanel4.add(lblcodproduto11);
        lblcodproduto11.setBounds(80, 70, 40, 14);

        txtunitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtunitarioFocusLost(evt);
            }
        });
        txtunitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtunitarioKeyReleased(evt);
            }
        });

        jPanel4.add(txtunitario);
        txtunitario.setBounds(130, 90, 50, 20);

        lblcodproduto12.setText("V. Unit.");
        jPanel4.add(lblcodproduto12);
        lblcodproduto12.setBounds(130, 70, 40, 14);

        txtdesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdescKeyReleased(evt);
            }
        });

        jPanel4.add(txtdesc);
        txtdesc.setBounds(190, 90, 50, 20);

        lblcodproduto13.setText("Desc. (R$)");
        jPanel4.add(lblcodproduto13);
        lblcodproduto13.setBounds(190, 70, 60, 14);

        txttotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txttotalFocusGained(evt);
            }
        });

        jPanel4.add(txttotal);
        txttotal.setBounds(250, 90, 50, 20);

        lblcodproduto14.setText("Total");
        jPanel4.add(lblcodproduto14);
        lblcodproduto14.setBounds(252, 70, 40, 14);

        lblcodproduto16.setText("Dispon\u00edvel");
        jPanel4.add(lblcodproduto16);
        lblcodproduto16.setBounds(590, 20, 70, 14);

        lblqtd.setFont(new java.awt.Font("Tahoma", 3, 14));
        lblqtd.setText("0");
        jPanel4.add(lblqtd);
        lblqtd.setBounds(610, 40, 40, 14);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(10, 80, 670, 120);

        jScrollPane1.setName("Venda Balc\u00e3o");
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Cod. P", "Produto", "Cod C.", "Cor", "Tam.", "Qtd.", "P. Unit.", "P. Total"
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
        jScrollPane1.setBounds(10, 210, 670, 180);

        btninserir.setFont(new java.awt.Font("Dialog", 1, 12));
        btninserir.setLabel("Inserir");
        btninserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninserirActionPerformed(evt);
            }
        });

        getContentPane().add(btninserir);
        btninserir.setBounds(10, 540, 80, 30);

        btncadastrar.setFont(new java.awt.Font("Dialog", 1, 12));
        btncadastrar.setLabel("Cadastrar");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });

        getContentPane().add(btncadastrar);
        btncadastrar.setBounds(100, 540, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(190, 540, 80, 30);

        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(280, 540, 80, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel3.setText("Total (R$)");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(380, 530, 80, 17);

        lblsaldo.setFont(new java.awt.Font("Tahoma", 3, 14));
        lblsaldo.setText("0.00");
        getContentPane().add(lblsaldo);
        lblsaldo.setBounds(390, 550, 80, 17);

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
        jLabel12.setBounds(20, 30, 20, 14);

        jLabel14.setText("2\u00ba");
        jPanel17.add(jLabel14);
        jLabel14.setBounds(120, 30, 20, 14);

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
        ftxtvencimento2.setBounds(150, 30, 70, 20);

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
        ftxtvencimento3.setBounds(280, 30, 70, 20);

        jLabel15.setText("3\u00ba");
        jPanel17.add(jLabel15);
        jLabel15.setBounds(250, 30, 20, 14);

        jLabel16.setText("4\u00ba");
        jPanel17.add(jLabel16);
        jLabel16.setBounds(380, 30, 20, 14);

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
        ftxtvencimento4.setBounds(410, 30, 70, 20);

        jLabel17.setText("5\u00ba");
        jPanel17.add(jLabel17);
        jLabel17.setBounds(500, 30, 20, 14);

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
        ftxtvencimento5.setBounds(530, 30, 70, 20);

        getContentPane().add(jPanel17);
        jPanel17.setBounds(70, 400, 610, 60);

        jPanel18.setLayout(null);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor Parcelas"));
        jLabel13.setText("1\u00ba");
        jPanel18.add(jLabel13);
        jLabel13.setBounds(80, 30, 20, 14);

        jLabel18.setText("2\u00ba");
        jPanel18.add(jLabel18);
        jLabel18.setBounds(180, 30, 40, 14);

        jLabel19.setText("3\u00ba");
        jPanel18.add(jLabel19);
        jLabel19.setBounds(310, 30, 40, 14);

        jLabel20.setText("4\u00ba");
        jPanel18.add(jLabel20);
        jLabel20.setBounds(440, 30, 40, 14);

        jLabel21.setText("5\u00ba");
        jPanel18.add(jLabel21);
        jLabel21.setBounds(560, 30, 40, 14);

        txtparcela1.setText("0.00");
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
        txtparcela1.setBounds(100, 30, 70, 20);

        txtparcela2.setText("0.00");
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
        txtparcela2.setBounds(210, 30, 70, 20);

        txtparcela3.setText("0.00");
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
        txtparcela3.setBounds(340, 30, 70, 20);

        txtparcela4.setText("0.00");
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
        txtparcela4.setBounds(470, 30, 70, 20);

        txtparcela5.setText("0.00");
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
        txtparcela5.setBounds(590, 30, 70, 20);

        getContentPane().add(jPanel18);
        jPanel18.setBounds(10, 460, 670, 60);

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

        cmbparcela.setMaximumRowCount(4);
        cmbparcela.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5" }));
        cmbparcela.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbparcelaItemStateChanged(evt);
            }
        });

        jPanel13.add(cmbparcela);
        cmbparcela.setBounds(10, 30, 40, 20);

        lblteste.setText("teste");
        jPanel13.add(lblteste);
        lblteste.setBounds(10, 30, 25, 14);

        getContentPane().add(jPanel13);
        jPanel13.setBounds(10, 400, 60, 60);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-697)/2, (screenSize.height-610)/2, 697, 610);
    }// </editor-fold>//GEN-END:initComponents
    public static long carregarMemoria(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<1000000; i++){
		list.add(i);	
		}
		return Runtime.getRuntime().freeMemory();
	}    
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
        if (cmbparcela.getSelectedItem().equals("-")) {
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
            //ftxtvencimento1.requestFocus();
        }
    }//GEN-LAST:event_cmbparcelaItemStateChanged
    
    private void txtnota8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota8FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota8FocusGained
    
    private void txtnota7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota7FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota7FocusGained
    
    private void txtnota6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnota6FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_txtnota6FocusGained
    
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
    
    private void txtparcela5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela5FocusGained
// TODO add your handling code here:
        txtparcela5.selectAll();
    }//GEN-LAST:event_txtparcela5FocusGained
    
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
    
    private void txtparcela4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela4FocusGained
// TODO add your handling code here:
        txtparcela4.selectAll();
    }//GEN-LAST:event_txtparcela4FocusGained
    
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
    
    private void txtparcela3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela3FocusGained
// TODO add your handling code here:
        txtparcela3.selectAll();
    }//GEN-LAST:event_txtparcela3FocusGained
    
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
    
    private void txtparcela2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela2FocusGained
// TODO add your handling code here:
        txtparcela2.selectAll();
    }//GEN-LAST:event_txtparcela2FocusGained
    
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
    
    private void txtparcela1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtparcela1FocusGained
// TODO add your handling code here:
        txtparcela1.selectAll();
    }//GEN-LAST:event_txtparcela1FocusGained
    
    private void ftxtvencimento5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento5FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento5.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento5FocusLost
    
    private void ftxtvencimento5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento5FocusGained
// TODO add your handling code here:
        ftxtvencimento5.selectAll();
    }//GEN-LAST:event_ftxtvencimento5FocusGained
    
    private void ftxtvencimento4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento4FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento4.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento4FocusLost
    
    private void ftxtvencimento4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento4FocusGained
// TODO add your handling code here:
        ftxtvencimento4.selectAll();
    }//GEN-LAST:event_ftxtvencimento4FocusGained
    
    private void ftxtvencimento3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento3FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento3.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento3FocusLost
    
    private void ftxtvencimento3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento3FocusGained
// TODO add your handling code here:
        ftxtvencimento3.selectAll();
    }//GEN-LAST:event_ftxtvencimento3FocusGained
    
    private void ftxtvencimento2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento2FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento2.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento2FocusLost
    
    private void ftxtvencimento2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento2FocusGained
// TODO add your handling code here:
        ftxtvencimento2.selectAll();
    }//GEN-LAST:event_ftxtvencimento2FocusGained
    
    private void ftxtvencimento1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento1FocusLost
// TODO add your handling code here:
        Pattern p = Pattern
                .compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(ftxtvencimento1.getText());
        if (!m.find())
            
            JOptionPane.showMessageDialog(null, "Data inválida.");
    }//GEN-LAST:event_ftxtvencimento1FocusLost
    
    private void ftxtvencimento1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtvencimento1FocusGained
// TODO add your handling code here:
        ftxtvencimento1.selectAll();
        ftxtvencimento1.setText(ftxtdataemissao.getText());
        ftxtvencimento2.setText(ftxtdataemissao.getText());
        ftxtvencimento3.setText(ftxtdataemissao.getText());
        ftxtvencimento4.setText(ftxtdataemissao.getText());
        ftxtvencimento5.setText(ftxtdataemissao.getText());
    }//GEN-LAST:event_ftxtvencimento1FocusGained
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
// TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened
    
    private void cmbnomeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbnomeItemStateChanged
// TODO add your handling code here:
        txtnome.setText(cmbnome.getSelectedItem().toString());
    }//GEN-LAST:event_cmbnomeItemStateChanged
    
    private void txtnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomeActionPerformed
// TODO add your handling code here:
        
    }//GEN-LAST:event_txtnomeActionPerformed
    
    private void cmbnomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbnomeFocusLost
// TODO add your handling code here:
   //     JOptionPane.showMessageDialog(null,"Selecione um produto!");
        
    }//GEN-LAST:event_cmbnomeFocusLost
    
    private void cmbcorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbcorFocusGained
// TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_cmbcorFocusGained
    
    private void cmbprodutoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbprodutoFocusGained
        
        
    }//GEN-LAST:event_cmbprodutoFocusGained
    
    private void cmbcorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbcorFocusLost
// TODO add your handling code here:
        try {
            con_saldo= new conexao();
            con_saldo.conecta();
            con_saldo.executeSQL("SELECT qt_p, qt_m,qt_g,qt_gg,qt_eg,qt_exg FROM produtoacabado where cd_produto='"
                    + Integer.parseInt(txtcodproduto.getText())
                    + "' and cd_cor = '"
                    + Integer.parseInt(lblcodcor.getText())
                    + "'");
            
            con_saldo.resultset.first();
            
            
            
            qtdestoquetinhap = (con_saldo.resultset.getInt("qt_p")) ;
            qtdestoquetinham = (con_saldo.resultset.getInt("qt_m"));
            qtdestoquetinhag = (con_saldo.resultset.getInt("qt_g"));
            qtdestoquetinhagg = (con_saldo.resultset.getInt("qt_gg"));
            qtdestoquetinhaeg = (con_saldo.resultset.getInt("qt_eg"));
            qtdestoquetinhaexg = (con_saldo.resultset.getInt("qt_exg"));
            
            
        } catch(SQLException erro) {
            
        }
        
        
        
    }//GEN-LAST:event_cmbcorFocusLost
    
    private void cmbprodutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbprodutoFocusLost
// TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Selecione uma cor!");
        
    }//GEN-LAST:event_cmbprodutoFocusLost
    
    private void txttotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txttotalFocusGained
// TODO add your handling code here:
        txttotal.setText((new String().valueOf(Double
                .parseDouble(txtunitario.getText())
                * Integer.parseInt(txtqtd.getText())
                - Double.parseDouble(txtdesc.getText()))));
    }//GEN-LAST:event_txttotalFocusGained
    
    private void txtdescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescKeyReleased
// TODO add your handling code here:
        txtdesc.setText(txtdesc.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtdescKeyReleased
    
    private void txtunitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtunitarioFocusLost
// TODO add your handling code here:
        txtdesc.setText("0.00");
        txtdesc.selectAll();
    }//GEN-LAST:event_txtunitarioFocusLost
    
    private void txtunitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtunitarioKeyReleased
// TODO add your handling code here:
        txtunitario.setText(txtunitario.getText().replace(",", ".")
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
    }//GEN-LAST:event_txtunitarioKeyReleased
    
    private void txtqtdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdKeyTyped
// TODO add your handling code here:
        // Só aceita numero
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtqtdKeyTyped
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
        cmbparcela.setSelectedIndex(0);
        cmbcor.setSelectedIndex(0);
        cmbproduto.setSelectedIndex(0);
        cmbtamanho.setSelectedIndex(0);
        txtqtd.setText("");
        txtunitario.setText("");
        txttotal.setText("");
        txtdesc.setText("");
        cmbnome.setSelectedIndex(0);
        numeroRegistros=1;
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
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        lblsaldo.setText("0.00");
        lblqtd.setText("0");
        
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
// TODO add your handling code here:
        if(cmbparcela.getSelectedItem().equals("-")){
            
            JOptionPane.showMessageDialog(null,"Selecione uma parcela");
        } else{

            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Date data = null;
            Date data1 = null;
            Date data2 = null;
            Date data3 = null;
            Date data4 = null;
            Date data5 = null;
//            try {
//                data = (Date) fmt.parse(ftxtdataemissao.getText());
//                data1 = (Date) fmt.parse(ftxtvencimento1.getText());
//                data2 = (Date) fmt.parse("11/11/1111");
//                data3 = (Date) fmt.parse("11/11/1111");
//                data4 = (Date) fmt.parse("11/11/1111");
//                data5 = (Date) fmt.parse("11/11/1111");
//                
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
            
            if (cmbparcela.getSelectedItem().equals("1")) {
                 try {
                data = (Date) fmt.parse(ftxtdataemissao.getText());
                data1 = (Date) fmt.parse(ftxtvencimento1.getText());
                data2 = (Date) fmt.parse("11/11/1111");
                data3 = (Date) fmt.parse("11/11/1111");
                data4 = (Date) fmt.parse("11/11/1111");
                data5 = (Date) fmt.parse("11/11/1111");
                
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
                try {
                    
                    
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        
                       try {
            con_saldo= new conexao();
            con_saldo.conecta();
            con_saldo.executeSQL("SELECT qt_p, qt_m,qt_g,qt_gg,qt_eg,qt_exg FROM produtoacabado where cd_produto='"
                    + Integer.parseInt(jTable1.getValueAt(i, 1).toString())
                    + "' and cd_cor = '"
                    + Integer.parseInt(jTable1.getValueAt(i, 3).toString())
                    + "'");
            
            con_saldo.resultset.first();
            
            qtdestoquetinhap = (con_saldo.resultset.getInt("qt_p")) ;
            qtdestoquetinham = (con_saldo.resultset.getInt("qt_m"));
            qtdestoquetinhag = (con_saldo.resultset.getInt("qt_g"));
            qtdestoquetinhagg = (con_saldo.resultset.getInt("qt_gg"));
            qtdestoquetinhaeg = (con_saldo.resultset.getInt("qt_eg"));
            qtdestoquetinhaexg = (con_saldo.resultset.getInt("qt_exg"));
            
            
        } catch(SQLException erro) {
            
        }
                        
                        if (jTable1.getValueAt(i, 5).equals("P")){
                            
                            int estoqueatualp = (qtdestoquetinhap) -  (Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                            
                            String sqlinsertprodutoacabadovp ="Update produtoacabado set qt_p= '"+estoqueatualp+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovp);
                            
                        }
                        
                        //continuar daqui!!!
                        if (jTable1.getValueAt(i, 5).equals("M")){
                            int estoqueatualm = qtdestoquetinham -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovm ="Update produtoacabado set qt_m= '"+estoqueatualm+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovm);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("G")){
                            int estoqueatualg = qtdestoquetinhag -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovg ="Update produtoacabado set qt_g= '"+estoqueatualg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("GG")){
                            int estoqueatualgg = qtdestoquetinhagg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovgg ="Update produtoacabado set qt_gg= '"+estoqueatualgg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovgg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EG")){
                            int estoqueatualeg = qtdestoquetinhaeg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadoveg ="Update produtoacabado set qt_eg= '"+estoqueatualeg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadoveg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EXG")){
                            int estoqueatualexg = qtdestoquetinhaexg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovexg ="Update produtoacabado set qt_exg= '"+estoqueatualexg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovexg);
                            
                        }
                        
                      String sqlinsertvendadireta ="INSERT INTO Vendacarteira (cd_cliente, cd_produto, nm_tamanho, cd_cor, qt_quantidade, vl_unitario, vl_sbtl, vl_ttlgeral, dt_venda) VALUES('"+
                                /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                                /* codigo produto */   Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                                /* cod. tamanho */     jTable1.getValueAt(i, 5).toString().toUpperCase()+"','"+
                                /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                                /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                                /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                                /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                                /*V. geral */             Double.parseDouble(lblsaldo.getText())+"','"+
                                /*data venda */       data+"')";
                        con_produto.statement.executeUpdate(sqlinsertvendadireta);  
                    }
                    
                    
                    String sqlinsertcontap = "INSERT INTO ContaReceber1 (cd_cliente, vl_valor, dt_venda, dt_vencimento1, vl_parcela1,dt_vencimento2, vl_parcela2,dt_vencimento3, vl_parcela3,dt_vencimento4, vl_parcela4,dt_vencimento5, vl_parcela5, ds_status1, ds_status2, ds_status3, ds_status4, ds_status5) VALUES( '"+
                            Integer.parseInt(txtcodigo.getText())+"','"+
                            Double.parseDouble(lblsaldo.getText())+"','"+
                            data+"','"+
                            data1+"','"+
                            Double.parseDouble(txtparcela1.getText())+"','"+
                            data2+"','"+
                            Double.parseDouble(txtparcela2.getText())+"','"+
                            data3+"','"+
                            Double.parseDouble(txtparcela3.getText())+"','"+
                            data4+"','"+
                            Double.parseDouble(txtparcela4.getText())+"','"+
                            data5+"','"+
                            Double.parseDouble(txtparcela5.getText())+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"')";
                    
//                    String sqlinsertcontap = "INSERT INTO ContaReceber1 (cd_cliente, vl_valor, dt_venda, dt_vencimento1, vl_parcela1) VALUES( '"+
//                            Integer.parseInt(txtcodigo.getText())+"','"+
//                            Double.parseDouble(lblsaldo.getText())+"','"+
//                            data+"','"+
//                            data1+"','"+
//                            Double.parseDouble(txtparcela1.getText())+"')";
                    
                    con_contapagar1.statement.executeUpdate(sqlinsertcontap);
                    con_produto = new conexao();
                    con_produto.conecta();
                    con_produto.executeSQL("select * from produto order by nm_produto");
                    con_produto.executeSQL("select * from vendacarteira order by dt_venda");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
                
                    
//                String nome1 = "Deseja imprimir Comprovante de Faturamento do Sr(a): "+txtnome.getText()+"?";
//                int opcao_escolha1 = JOptionPane.showConfirmDialog(null,nome1,"Imprimir", JOptionPane.YES_NO_OPTION);
//                if(opcao_escolha1==JOptionPane.YES_OPTION){
//                    new Rel_Venda_Carteira();
                  //  new Rel_Venda_Carteira_1();
//                }
                
                String nome = "Deseja imprimir a Venda do Sr(a): "+txtnome.getText()+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    //new Rel_Venda_Carteira();
                    new Rel_Venda_Carteira_1();
                
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                    txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                    cmbparcela.setSelectedIndex(0);
                } else{
                    
                    
                   cmbparcela.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                   txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                    
                    
                }
             
            }
            

            if (cmbparcela.getSelectedItem().equals("2")) {
                 try {
                data = (Date) fmt.parse(ftxtdataemissao.getText());
                data1 = (Date) fmt.parse(ftxtvencimento1.getText());
                data2 = (Date) fmt.parse(ftxtvencimento2.getText());
                data3 = (Date) fmt.parse("11/11/1111");
                data4 = (Date) fmt.parse("11/11/1111");
                data5 = (Date) fmt.parse("11/11/1111");
                
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
                try {
                    
                    
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        
                        String sqlinsertvendadireta ="INSERT INTO Vendacarteira (cd_cliente, cd_produto, nm_tamanho, cd_cor, qt_quantidade, vl_unitario, vl_sbtl, vl_ttlgeral, dt_venda) VALUES('"+
                                /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                                /* codigo produto */   Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                                /* cod. tamanho */     jTable1.getValueAt(i, 5).toString().toUpperCase()+"','"+
                                /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                                /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                                /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                                /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                                /*V. geral */             Double.parseDouble(lblsaldo.getText())+"','"+
                                /*data venda */       data+"')";
                        con_produto.statement.executeUpdate(sqlinsertvendadireta);
                        
                        if (jTable1.getValueAt(i, 5).equals("P")){
                            
                            int estoqueatualp = (qtdestoquetinhap) -  (Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                            
                            String sqlinsertprodutoacabadovp ="Update produtoacabado set qt_p= '"+estoqueatualp+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovp);
                            
                        }
                        
                        //continuar daqui!!!
                        if (jTable1.getValueAt(i, 5).equals("M")){
                            int estoqueatualm = qtdestoquetinham -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovm ="Update produtoacabado set qt_m= '"+estoqueatualm+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovm);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("G")){
                            int estoqueatualg = qtdestoquetinhag -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovg ="Update produtoacabado set qt_g= '"+estoqueatualg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("GG")){
                            int estoqueatualgg = qtdestoquetinhagg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovgg ="Update produtoacabado set qt_gg= '"+estoqueatualgg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovgg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EG")){
                            int estoqueatualeg = qtdestoquetinhaeg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadoveg ="Update produtoacabado set qt_eg= '"+estoqueatualeg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadoveg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EXG")){
                            int estoqueatualexg = qtdestoquetinhaexg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovexg ="Update produtoacabado set qt_exg= '"+estoqueatualexg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovexg);
                            
                        }
                    }

                     String sqlinsertconta2 = "INSERT INTO ContaReceber1 (cd_cliente, vl_valor, dt_venda, dt_vencimento1, vl_parcela1,dt_vencimento2, vl_parcela2,dt_vencimento3, vl_parcela3,dt_vencimento4, vl_parcela4,dt_vencimento5, vl_parcela5, ds_status1, ds_status2, ds_status3, ds_status4, ds_status5) VALUES( '"+
                            Integer.parseInt(txtcodigo.getText())+"','"+
                            Double.parseDouble(lblsaldo.getText())+"','"+
                            data+"','"+
                            data1+"','"+
                            Double.parseDouble(txtparcela1.getText())+"','"+
                            data2+"','"+
                            Double.parseDouble(txtparcela2.getText())+"','"+
                            data3+"','"+
                            Double.parseDouble(txtparcela3.getText())+"','"+
                            data4+"','"+
                            Double.parseDouble(txtparcela4.getText())+"','"+
                            data5+"','"+
                           Double.parseDouble(txtparcela5.getText())+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"')";
                    
                    
                    con_contapagar1.statement.executeUpdate(sqlinsertconta2);
                    con_produto = new conexao();
                    con_produto.conecta();
                    con_produto.executeSQL("select * from produto order by nm_produto");
                    con_produto.executeSQL("select * from vendacarteira order by dt_venda");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
               
//                String nome1 = "Deseja imprimir Comprovante de Faturamento do Sr(a): "+txtnome.getText()+"?";
//                int opcao_escolha1 = JOptionPane.showConfirmDialog(null,nome1,"Imprimir", JOptionPane.YES_NO_OPTION);
//                if(opcao_escolha1==JOptionPane.YES_OPTION){
//                    new Rel_Venda_Carteira();
//                  //  new Rel_Venda_Carteira_1();
//                }
                
                String nome = "Deseja imprimir a Venda do Sr(a): "+txtnome.getText()+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    new Rel_Venda_Carteira_1();
                    
                    cmbparcela.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                    txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                } else{
                    
                    cmbparcela.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                    txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                }
            }

            
            if (cmbparcela.getSelectedItem().equals("3")) {
              
                try {
                data = (Date) fmt.parse(ftxtdataemissao.getText());
                data1 = (Date) fmt.parse(ftxtvencimento1.getText());
                data2 = (Date) fmt.parse(ftxtvencimento2.getText());
                data3 = (Date) fmt.parse(ftxtvencimento3.getText());
                data4 = (Date) fmt.parse("11/11/1111");
                data5 = (Date) fmt.parse("11/11/1111");
                
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
                
                try {
                    
                    
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        
                        String sqlinsertvendadireta ="INSERT INTO Vendacarteira (cd_cliente, cd_produto, nm_tamanho, cd_cor, qt_quantidade, vl_unitario, vl_sbtl, vl_ttlgeral, dt_venda) VALUES('"+
                                /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                                /* codigo produto */   Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                                /* cod. tamanho */     jTable1.getValueAt(i, 5).toString().toUpperCase()+"','"+
                                /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                                /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                                /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                                /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                                /*V. geral */             Double.parseDouble(lblsaldo.getText())+"','"+
                                /*data venda */       data+"')";
                        con_produto.statement.executeUpdate(sqlinsertvendadireta);
                        
                        if (jTable1.getValueAt(i, 5).equals("P")){
                            
                            int estoqueatualp = (qtdestoquetinhap) -  (Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                            
                            String sqlinsertprodutoacabadovp ="Update produtoacabado set qt_p= '"+estoqueatualp+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovp);
                            
                        }
                        
                        //continuar daqui!!!
                        if (jTable1.getValueAt(i, 5).equals("M")){
                            int estoqueatualm = qtdestoquetinham -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovm ="Update produtoacabado set qt_m= '"+estoqueatualm+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovm);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("G")){
                            int estoqueatualg = qtdestoquetinhag -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovg ="Update produtoacabado set qt_g= '"+estoqueatualg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("GG")){
                            int estoqueatualgg = qtdestoquetinhagg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovgg ="Update produtoacabado set qt_gg= '"+estoqueatualgg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovgg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EG")){
                            int estoqueatualeg = qtdestoquetinhaeg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadoveg ="Update produtoacabado set qt_eg= '"+estoqueatualeg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadoveg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EXG")){
                            int estoqueatualexg = qtdestoquetinhaexg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovexg ="Update produtoacabado set qt_exg= '"+estoqueatualexg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovexg);
                            
                        }
                    }
                    
                   String sqlinsertconta3 = "INSERT INTO ContaReceber1 (cd_cliente, vl_valor, dt_venda, dt_vencimento1, vl_parcela1,dt_vencimento2, vl_parcela2,dt_vencimento3, vl_parcela3,dt_vencimento4, vl_parcela4,dt_vencimento5, vl_parcela5, ds_status1, ds_status2, ds_status3, ds_status4, ds_status5) VALUES( '"+
                            Integer.parseInt(txtcodigo.getText())+"','"+
                            Double.parseDouble(lblsaldo.getText())+"','"+
                            data+"','"+
                            data1+"','"+
                            Double.parseDouble(txtparcela1.getText())+"','"+
                            data2+"','"+
                            Double.parseDouble(txtparcela2.getText())+"','"+
                            data3+"','"+
                            Double.parseDouble(txtparcela3.getText())+"','"+
                            data4+"','"+
                            Double.parseDouble(txtparcela4.getText())+"','"+
                            data5+"','"+
                            Double.parseDouble(txtparcela5.getText())+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"')";
                                   
                    con_contapagar1.statement.executeUpdate(sqlinsertconta3);
                    
                    con_produto = new conexao();
                    con_produto.conecta();
                    con_produto.executeSQL("select * from produto order by nm_produto");
                    con_produto.executeSQL("select * from vendacarteira order by dt_venda");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
               
//                String nome1 = "Deseja imprimir Comprovante de Faturamento do Sr(a): "+txtnome.getText()+"?";
//                int opcao_escolha1 = JOptionPane.showConfirmDialog(null,nome1,"Imprimir", JOptionPane.YES_NO_OPTION);
//                if(opcao_escolha1==JOptionPane.YES_OPTION){
//                    new Rel_Venda_Carteira();
//                  //  new Rel_Venda_Carteira_1();
//                }
                
                String nome = "Deseja imprimir a Venda do Sr(a): "+txtnome.getText()+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    new Rel_Venda_Carteira_1();
                    
                    cmbparcela.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                    txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                } else{
                    
                    cmbparcela.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                   txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                }
            }
            if (cmbparcela.getSelectedItem().equals("4")) {
                
                try {
                    
                    data = (Date) fmt.parse(ftxtdataemissao.getText());
                    data1 = (Date) fmt.parse(ftxtvencimento1.getText());
                    data3 = (Date) fmt.parse(ftxtvencimento3.getText());
                    data2 = (Date) fmt.parse(ftxtvencimento2.getText());
                    data5 = (Date) fmt.parse("11/11/1111");
                    data4 = (Date) fmt.parse(ftxtvencimento4.getText());
                    
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                try {
                    
                    
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        
                        String sqlinsertvendadireta ="INSERT INTO Vendacarteira (cd_cliente, cd_produto, nm_tamanho, cd_cor, qt_quantidade, vl_unitario, vl_sbtl, vl_ttlgeral, dt_venda) VALUES('"+
                                /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                                /* codigo produto */   Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                                /* cod. tamanho */     jTable1.getValueAt(i, 5).toString().toUpperCase()+"','"+
                                /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                                /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                                /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                                /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                                /*V. geral */             Double.parseDouble(lblsaldo.getText())+"','"+
                                /*data venda */       data+"')";
                        con_produto.statement.executeUpdate(sqlinsertvendadireta);
                        
                        if (jTable1.getValueAt(i, 5).equals("P")){
                            
                            int estoqueatualp = (qtdestoquetinhap) -  (Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                            
                            String sqlinsertprodutoacabadovp ="Update produtoacabado set qt_p= '"+estoqueatualp+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovp);
                            
                        }
                        
                        //continuar daqui!!!
                        if (jTable1.getValueAt(i, 5).equals("M")){
                            int estoqueatualm = qtdestoquetinham -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovm ="Update produtoacabado set qt_m= '"+estoqueatualm+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovm);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("G")){
                            int estoqueatualg = qtdestoquetinhag -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovg ="Update produtoacabado set qt_g= '"+estoqueatualg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("GG")){
                            int estoqueatualgg = qtdestoquetinhagg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovgg ="Update produtoacabado set qt_gg= '"+estoqueatualgg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovgg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EG")){
                            int estoqueatualeg = qtdestoquetinhaeg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadoveg ="Update produtoacabado set qt_eg= '"+estoqueatualeg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadoveg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EXG")){
                            int estoqueatualexg = qtdestoquetinhaexg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovexg ="Update produtoacabado set qt_exg= '"+estoqueatualexg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovexg);
                            
                        }
                    }
                    
                   
                    String sqlinsertconta4 = "INSERT INTO ContaReceber1 (cd_cliente, vl_valor, dt_venda, dt_vencimento1, vl_parcela1,dt_vencimento2, vl_parcela2,dt_vencimento3, vl_parcela3,dt_vencimento4, vl_parcela4,dt_vencimento5, vl_parcela5, ds_status1, ds_status2, ds_status3, ds_status4, ds_status5) VALUES( '"+
                            Integer.parseInt(txtcodigo.getText())+"','"+
                            Double.parseDouble(lblsaldo.getText())+"','"+
                            data+"','"+
                            data1+"','"+
                            Double.parseDouble(txtparcela1.getText())+"','"+
                            data2+"','"+
                            Double.parseDouble(txtparcela2.getText())+"','"+
                            data3+"','"+
                            Double.parseDouble(txtparcela3.getText())+"','"+
                            data4+"','"+
                            Double.parseDouble(txtparcela4.getText())+"','"+
                            data5+"','"+
                            Double.parseDouble(txtparcela5.getText())+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"')";
                    
                    con_contapagar1.statement.executeUpdate(sqlinsertconta4);

                    
                    con_produto = new conexao();
                    con_produto.conecta();
                    con_produto.executeSQL("select * from produto order by nm_produto");
                    con_produto.executeSQL("select * from vendacarteira order by dt_venda");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
                
//                String nome1 = "Deseja imprimir Comprovante de Faturamento do Sr(a): "+txtnome.getText()+"?";
//                int opcao_escolha1 = JOptionPane.showConfirmDialog(null,nome1,"Imprimir", JOptionPane.YES_NO_OPTION);
//                if(opcao_escolha1==JOptionPane.YES_OPTION){
//                    new Rel_Venda_Carteira();
//                  //  new Rel_Venda_Carteira_1();
//                }
                
                String nome = "Deseja imprimir a Venda do Sr(a): "+txtnome.getText()+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    new Rel_Venda_Carteira_1();
                    
                    
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                    txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                } else{
                    
                    
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                    txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                }
            }
            

            if (cmbparcela.getSelectedItem().equals("5")) {
               try {
                data = (Date) fmt.parse(ftxtdataemissao.getText());
                data1 = (Date) fmt.parse(ftxtvencimento1.getText());
                data3 = (Date) fmt.parse(ftxtvencimento3.getText());
                data2 = (Date) fmt.parse(ftxtvencimento2.getText());
                data4 = (Date) fmt.parse(ftxtvencimento4.getText());
                data5 = (Date) fmt.parse(ftxtvencimento5.getText());
                
            } catch (ParseException ex) {
                ex.printStackTrace();
            }  
                try {
                    
                    
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        
                        String sqlinsertvendadireta ="INSERT INTO Vendacarteira (cd_cliente, cd_produto, nm_tamanho, cd_cor, qt_quantidade, vl_unitario, vl_sbtl, vl_ttlgeral, dt_venda) VALUES('"+
                                /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                                /* codigo produto */   Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                                /* cod. tamanho */     jTable1.getValueAt(i, 5).toString().toUpperCase()+"','"+
                                /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                                /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                                /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                                /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                                /*V. geral */             Double.parseDouble(lblsaldo.getText())+"','"+
                                /*data venda */       data+"')";
                        con_produto.statement.executeUpdate(sqlinsertvendadireta);
                        
                        if (jTable1.getValueAt(i, 5).equals("P")){
                            
                            int estoqueatualp = (qtdestoquetinhap) -  (Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                            
                            String sqlinsertprodutoacabadovp ="Update produtoacabado set qt_p= '"+estoqueatualp+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovp);
                            
                        }
                        
                        //continuar daqui!!!
                        if (jTable1.getValueAt(i, 5).equals("M")){
                            int estoqueatualm = qtdestoquetinham -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovm ="Update produtoacabado set qt_m= '"+estoqueatualm+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovm);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("G")){
                            int estoqueatualg = qtdestoquetinhag -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovg ="Update produtoacabado set qt_g= '"+estoqueatualg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("GG")){
                            int estoqueatualgg = qtdestoquetinhagg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovgg ="Update produtoacabado set qt_gg= '"+estoqueatualgg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovgg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EG")){
                            int estoqueatualeg = qtdestoquetinhaeg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadoveg ="Update produtoacabado set qt_eg= '"+estoqueatualeg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadoveg);
                            
                        }
                        if (jTable1.getValueAt(i, 5).equals("EXG")){
                            int estoqueatualexg = qtdestoquetinhaexg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                            String sqlinsertprodutoacabadovexg ="Update produtoacabado set qt_exg= '"+estoqueatualexg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                                    "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                            con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovexg);
                            
                        }
                    }
                    String sqlinsertcontap = "INSERT INTO ContaReceber1 (cd_cliente, vl_valor, dt_venda, dt_vencimento1, vl_parcela1,dt_vencimento2, vl_parcela2,dt_vencimento3, vl_parcela3,dt_vencimento4, vl_parcela4,dt_vencimento5, vl_parcela5, ds_status1, ds_status2, ds_status3, ds_status4, ds_status5) VALUES( '"+
                            Integer.parseInt(txtcodigo.getText())+"','"+
                            Double.parseDouble(lblsaldo.getText())+"','"+
                            data+"','"+
                            data1+"','"+
                            Double.parseDouble(txtparcela1.getText())+"','"+
                            data2+"','"+
                            Double.parseDouble(txtparcela2.getText())+"','"+
                            data3+"','"+
                            Double.parseDouble(txtparcela3.getText())+"','"+
                            data4+"','"+
                            Double.parseDouble(txtparcela4.getText())+"','"+
                            data5+"','"+
                            Double.parseDouble(txtparcela5.getText())+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"','"+
                            "PENDENTE"+"')";
                    
                    con_contapagar1.statement.executeUpdate(sqlinsertcontap);

                    
                    con_produto = new conexao();
                    con_produto.conecta();
                    con_produto.executeSQL("select * from produto order by nm_produto");
                    con_produto.executeSQL("select * from vendacarteira order by dt_venda");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
                
//                String nome1 = "Deseja imprimir Comprovante de Faturamento do Sr(a): "+txtnome.getText()+"?";
//                int opcao_escolha1 = JOptionPane.showConfirmDialog(null,nome1,"Imprimir", JOptionPane.YES_NO_OPTION);
//                if(opcao_escolha1==JOptionPane.YES_OPTION){
//                    new Rel_Venda_Carteira();
//                  //  new Rel_Venda_Carteira_1();
//                }
                
                String nome = "Deseja imprimir a Venda do Sr(a): "+txtnome.getText()+"?";
                int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                if(opcao_escolha==JOptionPane.YES_OPTION){
                    new Rel_Venda_Carteira_1();
                    
                    
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                   txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                } else{
                    
                    
                    cmbnome.setSelectedIndex(0);
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    lblsaldo.setText("0.00");
                    lblqtd.setText("0");
                    txtcodproduto.setText("0");
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
                   txtparcela1.setText("0.00");
                    txtparcela2.setText("0.00");
                    txtparcela3.setText("0.00");
                    txtparcela4.setText("0.00");
                    txtparcela5.setText("0.00");
                    txtparcela1.setEnabled(false);
                    txtparcela2.setEnabled(false);
                    txtparcela3.setEnabled(false);
                    txtparcela4.setEnabled(false);
                    txtparcela5.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_btncadastrarActionPerformed
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
        if(cmbnome.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o cliente!");
            cmbnome.requestFocus();
        } else if(cmbproduto.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o produto!");
            cmbproduto.requestFocus();
        } else if(cmbcor.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione a cor!");
            cmbcor.requestFocus();
        } else if (cmbtamanho.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o tamanho!");
            cmbproduto.requestFocus();
        } else if (txtqtd.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Digite a quantidade!");
            txtqtd.requestFocus();
        } else if (txtunitario.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Digite o valor unitário!");
            txtunitario.requestFocus();
        }
        
        else if (cmbtamanho.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o tamanho!");
            cmbtamanho.requestFocus();
        } else if (txtqtd.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Digite a quantidade!");
            txtqtd.requestFocus();
            
        }
        else if (lblqtd.getText().equals("0")){
            JOptionPane.showMessageDialog(null,"Produto zerado no estoque!");
            cmbtamanho.requestFocus();

        }else{
            final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            
            modelo.addRow(new Object []{numeroRegistros++,txtcodproduto.getText(),cmbproduto.getSelectedItem(),lblcodcor.getText(),cmbcor.getSelectedItem(),cmbtamanho.getSelectedItem(),txtqtd.getText(),txtunitario.getText(), txttotal.getText()});
            
            
            cmbcor.setSelectedIndex(0);
            cmbproduto.setSelectedIndex(0);
            cmbtamanho.setSelectedIndex(0);
            txtqtd.setText("");
            txtunitario.setText("");
            txttotal.setText("");
            txtdesc.setText("");
            lblqtd.setText("0");
            double total = 0.0;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                total += Double.parseDouble(jTable1.getValueAt(i, 8)
                .toString());
            }
            lblsaldo.setText(String.valueOf(converterDoubleString(total)));
        }
    }//GEN-LAST:event_btninserirActionPerformed
    public void preenchejTabla1(){
        
        final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(155);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(45);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(204);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(36);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(32);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(60);
        
        
        modelo.setNumRows(0);
        
    }
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained
    
    private void cmbtamanhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtamanhoActionPerformed
// TODO add your handling code here:
        
        try {
            if(cmbtamanho.getSelectedItem().equals("P")) {
                con_saldo= new conexao();
                con_saldo.conecta();
                
                con_saldo.executeSQL("SELECT qt_p FROM produtoacabado where cd_produto='"
                        + Integer.parseInt(txtcodproduto.getText())
                        + "' and cd_cor = '"
                        + Integer.parseInt(lblcodcor.getText())
                        + "'");
                
                con_saldo.resultset.first();
                
                lblqtd.setText(con_saldo.resultset.getString("qt_p"));
                
            }
            if(cmbtamanho.getSelectedItem().equals("M")) {
                con_saldo= new conexao();
                con_saldo.conecta();
                con_saldo.executeSQL("SELECT qt_m FROM produtoacabado where cd_produto='"
                        + Integer.parseInt(txtcodproduto.getText())
                        + "' and cd_cor = '"
                        + Integer.parseInt(lblcodcor.getText())
                        + "'");
                
                con_saldo.resultset.first();
                
                lblqtd.setText(con_saldo.resultset.getString("qt_m"));
                
            }
            if(cmbtamanho.getSelectedItem().equals("G")) {
                con_saldo= new conexao();
                con_saldo.conecta();
                con_saldo.executeSQL("SELECT qt_g FROM produtoacabado where cd_produto='"
                        + Integer.parseInt(txtcodproduto.getText())
                        + "' and cd_cor = '"
                        + Integer.parseInt(lblcodcor.getText())
                        + "'");
                
                con_saldo.resultset.first();
                
                lblqtd.setText(con_saldo.resultset.getString("qt_g"));
                
            }
            if(cmbtamanho.getSelectedItem().equals("GG")) {
                con_saldo= new conexao();
                con_saldo.conecta();
                con_saldo.executeSQL("SELECT qt_gg FROM produtoacabado where cd_produto='"
                        + Integer.parseInt(txtcodproduto.getText())
                        + "' and cd_cor = '"
                        + Integer.parseInt(lblcodcor.getText())
                        + "'");
                
                con_saldo.resultset.first();
                
                lblqtd.setText(con_saldo.resultset.getString("qt_gg"));
                
            }
            if(cmbtamanho.getSelectedItem().equals("EG")) {
                con_saldo= new conexao();
                con_saldo.conecta();
                con_saldo.executeSQL("SELECT qt_eg FROM produtoacabado where cd_produto='"
                        + Integer.parseInt(txtcodproduto.getText())
                        + "' and cd_cor = '"
                        + Integer.parseInt(lblcodcor.getText())
                        + "'");
                
                con_saldo.resultset.first();
                
                lblqtd.setText(con_saldo.resultset.getString("qt_eg"));
                
            }
            if(cmbtamanho.getSelectedItem().equals("EXG")) {
                con_saldo= new conexao();
                con_saldo.conecta();
                con_saldo.executeSQL("SELECT qt_exg FROM produtoacabado where cd_produto='"
                        + Integer.parseInt(txtcodproduto.getText())
                        + "' and cd_cor = '"
                        + Integer.parseInt(lblcodcor.getText())
                        + "'");
                
                con_saldo.resultset.first();
                
                lblqtd.setText(con_saldo.resultset.getString("qt_exg"));
                
            }
            
        } catch(SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Combinação não localizada");
            cmbproduto.setSelectedIndex(0);
            cmbcor.setSelectedIndex(0);
            cmbtamanho.setSelectedIndex(0);
            cmbproduto.requestFocus();
        }
        
    }//GEN-LAST:event_cmbtamanhoActionPerformed
    
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
        
        if(iniciacombo == 1){
            try {
                con_produto1.conecta();
                con_produto1.resultset.first();
                String igual = "n";
                while(igual == "n"){
                    if(con_produto1.resultset.getString("nm_produto").equals(cmbproduto.getSelectedItem())){
                        igual = "s";
                    } else
                        con_produto1.resultset.next();
                }
                txtcodproduto.setText(con_produto1.resultset.getString(String.valueOf("cd_produto")));
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                
            }
        }
        try {
            con_saldo1= new conexao();
            con_saldo1.conecta();
            
            con_saldo.executeSQL("SELECT vl_unitario FROM produtoacabado where cd_produto='"
                    + Integer.parseInt(txtcodproduto.getText())
                    + "'");
            
            con_saldo.resultset.first();
            
            // txtunitario.setText(con_saldo.resultset.getString("vl_preco"));
            txtunitario.setText(converterDoubleString(con_saldo.resultset.getDouble("vl_unitario")));
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
        iniciacombo=1;
    }//GEN-LAST:event_cmbprodutoActionPerformed
    
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
    
    private void cmbnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnomeActionPerformed
// TODO add your handling code h
        
        try {
            
            con_cliente.resultset.first();
            
            String igual = "n";
            while(igual == "n"){
                if(con_cliente.resultset.getString("nm_cliente").equals(cmbnome.getSelectedItem())){
                    igual = "s";
                } else
                    con_cliente.resultset.next();
            }
            txtcodigo.setText(con_cliente.resultset.getString(String.valueOf("cd_cliente")));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_cmbnomeActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendaCarteira_1().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btncadastrar;
    private java.awt.Button btninserir;
    private java.awt.Button btnlimpar;
    private java.awt.Button btnsair;
    private javax.swing.JComboBox cmbcor;
    public static javax.swing.JComboBox cmbnome;
    private javax.swing.JComboBox cmbparcela;
    private javax.swing.JComboBox cmbproduto;
    private javax.swing.JComboBox cmbtamanho;
    public static javax.swing.JFormattedTextField ftxtdataemissao;
    private javax.swing.JFormattedTextField ftxtvencimento1;
    private javax.swing.JFormattedTextField ftxtvencimento2;
    private javax.swing.JFormattedTextField ftxtvencimento3;
    private javax.swing.JFormattedTextField ftxtvencimento4;
    private javax.swing.JFormattedTextField ftxtvencimento5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblcodcor;
    private javax.swing.JLabel lblcodproduto10;
    private javax.swing.JLabel lblcodproduto11;
    private javax.swing.JLabel lblcodproduto12;
    private javax.swing.JLabel lblcodproduto13;
    private javax.swing.JLabel lblcodproduto14;
    private javax.swing.JLabel lblcodproduto16;
    private javax.swing.JLabel lblcodproduto8;
    private javax.swing.JLabel lblcodproduto9;
    private javax.swing.JLabel lblqtd;
    private javax.swing.JLabel lblsaldo;
    private javax.swing.JLabel lblteste;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcodproduto;
    private javax.swing.JTextField txtdesc;
    public static javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtnota6;
    private javax.swing.JTextField txtnota7;
    private javax.swing.JTextField txtnota8;
    private javax.swing.JTextField txtparcela1;
    private javax.swing.JTextField txtparcela2;
    private javax.swing.JTextField txtparcela3;
    private javax.swing.JTextField txtparcela4;
    private javax.swing.JTextField txtparcela5;
    private javax.swing.JTextField txtqtd;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtunitario;
    // End of variables declaration//GEN-END:variables
    
}
