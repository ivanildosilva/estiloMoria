/*
 * CadastroPedido.java
 *
 * Created on 8 de Setembro de 2011, 17:59
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
public class CadastroPedido extends javax.swing.JFrame {
    private MaskFormatter FormatoData, FormatoCpf, FormatoCnpj, FormatoCep, FormatoTel1, FormatoTel2, FormatoTel3, FormatoTel4;
    private conexao con_cliente, con_produto, con_cor, con_email,con_vendedor, con_data, con_orcamento;
    final DefaultTableModel model = new DefaultTableModel();
    private int iniciacombo=0;
    private int numeroRegistros=1;
    data mostra_data;
    /** Creates new form CadastroPedido */
    public CadastroPedido() {
        initComponents();
        enter();
        preenchejTabla1();
        con_cliente=new conexao();
        con_cliente.conecta();
         setResizable(false);
        con_produto=new conexao();
        con_produto.conecta();
        
        con_cor=new conexao();
        con_cor.conecta();
        
        con_vendedor=new conexao();
        con_vendedor.conecta();
        
        con_data = new conexao();
        con_data.conecta();
        
        mostra_data = new data();
        
        mostra_data.le_hora();
        
        
        
        
        txtcodigo1.setVisible(false);
        txtcodigo.setVisible(false);
        
        
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        ftxtdataemissao.setText(formatador.format(data));
        ftxtdataentrada.setText(ftxtdataemissao.getText());
        
        //this.setResizable(false);
        
        mostra_data.le_data();
        
        // this.setTitle("Cadastro Orçamento/ Pedido                    "+ mostra_data.dia_semana+ ", "+mostra_data.dia + " de " + mostra_data.mes + " de "+ mostra_data.ano +"                     "+ mostra_data.hora );
        timer1.start();
        //lbldata.setText(mostra_data.dia_semana+ ", "+mostra_data.dia + " de " + mostra_data.mes + " de "+ mostra_data.ano);
        con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
        con_cor.executeSQL("Select * from cor order by nm_cor");
        con_vendedor.executeSQL("Select * from vendedor order by cd_vendedor");
        con_produto.executeSQL("Select * from produto order by nm_produto");
        
        
        try {
            while(con_cliente.resultset.next())
                cmbnome.addItem(con_cliente.resultset.getString("nm_cliente"));
            while(con_vendedor.resultset.next())
                cmbnome1.addItem(con_vendedor.resultset.getString("nm_vendedor"));
            while(con_produto.resultset.next())
                cmbproduto.addItem(con_produto.resultset.getString("nm_produto"));
            
            while(con_cor.resultset.next())
                cmbcor.addItem(con_cor.resultset.getString("nm_cor"));
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"Não localizou dados "+erro);
        }
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        timer1 = new org.netbeans.examples.lib.timerbean.Timer();
        jPanel1 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        cmbnome = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        try
        {
            FormatoTel1 = new MaskFormatter("(##)####-####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxttel1 = new JFormattedTextField (FormatoTel1);
        lblnome8 = new javax.swing.JLabel();
        try
        {
            FormatoTel1 = new MaskFormatter("(##)####-####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar máscara, erro = " +erro);
        }

        ftxttel2 = new JFormattedTextField (FormatoTel1);
        lblnome9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rdborcamento = new javax.swing.JRadioButton();
        rdbpedido = new javax.swing.JRadioButton();
        txtcodigo1 = new javax.swing.JTextField();
        cmbnome1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
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
        txtpedido = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cmbproduto = new javax.swing.JComboBox();
        cmbcor = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmbtamanho = new javax.swing.JComboBox();
        lblcodproduto = new javax.swing.JLabel();
        lblcodcor = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtqtd = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtpreco = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        lbltotal = new javax.swing.JLabel();
        btninserir = new java.awt.Button();
        btncadastrar = new java.awt.Button();
        btnlimpar = new java.awt.Button();
        btnsair = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Or\u00e7amento/ Pedido");
        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        txtcodigo.setEnabled(false);
        jPanel1.add(txtcodigo);
        txtcodigo.setBounds(270, 40, 50, 20);

        cmbnome.setMaximumRowCount(5);
        cmbnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnomeActionPerformed(evt);
            }
        });

        jPanel1.add(cmbnome);
        cmbnome.setBounds(10, 40, 500, 20);

        jLabel2.setText("Nome");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 20, 50, 14);

        jPanel1.add(ftxttel1);
        ftxttel1.setBounds(10, 90, 100, 20);

        lblnome8.setText("Tel.1");
        jPanel1.add(lblnome8);
        lblnome8.setBounds(10, 70, 80, 14);

        jPanel1.add(ftxttel2);
        ftxttel2.setBounds(120, 90, 100, 20);

        lblnome9.setText("Tel.2");
        jPanel1.add(lblnome9);
        lblnome9.setBounds(120, 70, 80, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 520, 120);

        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Or\u00e7amento/ Pedido"));
        buttonGroup1.add(rdborcamento);
        rdborcamento.setText("Or\u00e7amento");
        rdborcamento.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdborcamento.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdborcamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdborcamentoItemStateChanged(evt);
            }
        });

        jPanel2.add(rdborcamento);
        rdborcamento.setBounds(20, 30, 90, 15);

        buttonGroup1.add(rdbpedido);
        rdbpedido.setText("Pedido");
        rdbpedido.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbpedido.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rdbpedido.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbpedidoItemStateChanged(evt);
            }
        });

        jPanel2.add(rdbpedido);
        rdbpedido.setBounds(110, 30, 60, 15);

        txtcodigo1.setEnabled(false);
        jPanel2.add(txtcodigo1);
        txtcodigo1.setBounds(10, 130, 50, 20);

        cmbnome1.setMaximumRowCount(4);
        cmbnome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnome1ActionPerformed(evt);
            }
        });

        jPanel2.add(cmbnome1);
        cmbnome1.setBounds(10, 80, 230, 20);

        jLabel6.setText("Vendedor");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 60, 60, 14);

        ftxtdataemissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataemissaoFocusLost(evt);
            }
        });

        jPanel2.add(ftxtdataemissao);
        ftxtdataemissao.setBounds(70, 130, 80, 20);

        jLabel10.setText("Pedido");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(70, 110, 60, 14);

        ftxtdataentrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ftxtdataentradaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtdataentradaFocusLost(evt);
            }
        });

        jPanel2.add(ftxtdataentrada);
        ftxtdataentrada.setBounds(160, 130, 80, 20);

        jLabel11.setText("Entrada");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(160, 110, 60, 14);

        txtpedido.setEditable(false);
        jPanel2.add(txtpedido);
        txtpedido.setBounds(10, 130, 50, 20);

        jLabel12.setText("N\u00ba Pedido");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(10, 110, 60, 14);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(540, 10, 250, 160);

        jPanel3.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        cmbproduto.setMaximumRowCount(5);
        cmbproduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbprodutoActionPerformed(evt);
            }
        });

        jPanel3.add(cmbproduto);
        cmbproduto.setBounds(10, 40, 190, 20);

        cmbcor.setMaximumRowCount(5);
        cmbcor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcorActionPerformed(evt);
            }
        });

        jPanel3.add(cmbcor);
        cmbcor.setBounds(210, 40, 300, 20);

        jLabel8.setText("Produto");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 20, 60, 14);

        jLabel9.setText("Cor");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(230, 20, 60, 14);

        jPanel4.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tamanho"));
        jPanel4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel4ComponentHidden(evt);
            }
        });

        cmbtamanho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P", "M", "G", "GG", "EG", "EXG" }));
        jPanel4.add(cmbtamanho);
        cmbtamanho.setBounds(10, 30, 60, 20);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(10, 70, 80, 60);

        lblcodproduto.setText("Produto");
        jPanel3.add(lblcodproduto);
        lblcodproduto.setBounds(110, 40, 60, 14);

        lblcodcor.setText("Cor");
        jPanel3.add(lblcodcor);
        lblcodcor.setBounds(280, 40, 60, 14);

        jPanel5.setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Quantidade"));
        jPanel5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel5ComponentHidden(evt);
            }
        });

        txtqtd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtqtdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtqtdKeyTyped(evt);
            }
        });

        jPanel5.add(txtqtd);
        txtqtd.setBounds(10, 30, 60, 20);

        jPanel3.add(jPanel5);
        jPanel5.setBounds(90, 70, 80, 60);

        jPanel6.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pre\u00e7o Unit."));
        jPanel6.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel6ComponentHidden(evt);
            }
        });

        txtpreco.setText("0.00");
        txtpreco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtprecoFocusGained(evt);
            }
        });
        txtpreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecoKeyReleased(evt);
            }
        });

        jPanel6.add(txtpreco);
        txtpreco.setBounds(10, 30, 60, 20);

        jPanel3.add(jPanel6);
        jPanel6.setBounds(170, 70, 80, 60);

        jPanel7.setLayout(null);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Pre\u00e7o Total"));
        jPanel7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel7ComponentHidden(evt);
            }
        });

        lbltotal.setFont(new java.awt.Font("Tahoma", 1, 16));
        lbltotal.setText("0.00");
        jPanel7.add(lbltotal);
        lbltotal.setBounds(10, 30, 70, 20);

        jPanel3.add(jPanel7);
        jPanel7.setBounds(250, 70, 90, 60);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 140, 520, 140);

        btninserir.setFont(new java.awt.Font("Dialog", 1, 12));
        btninserir.setLabel("Inserir");
        btninserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninserirActionPerformed(evt);
            }
        });

        getContentPane().add(btninserir);
        btninserir.setBounds(540, 180, 80, 30);

        btncadastrar.setEnabled(false);
        btncadastrar.setFont(new java.awt.Font("Dialog", 1, 12));
        btncadastrar.setLabel("Cadastrar");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });

        getContentPane().add(btncadastrar);
        btncadastrar.setBounds(640, 180, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(540, 220, 80, 30);

        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(640, 220, 80, 30);

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
        jScrollPane1.setBounds(10, 290, 790, 130);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-811)/2, (screenSize.height-469)/2, 811, 469);
    }// </editor-fold>//GEN-END:initComponents
    public void enter(){
// Enter simula tecla Tab
        HashSet conj = new HashSet(getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    }
    private void txtprecoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecoKeyReleased
// TODO add your handling code here:
        txtpreco.setText(txtpreco.getText().replace(",", ".").replace("q", "").replace("w", "")
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
    }//GEN-LAST:event_txtprecoKeyReleased
    
    private void txtqtdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdKeyTyped
// TODO add your handling code here:
        // Só aceita numero
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtqtdKeyTyped
    
    private void txtqtdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdKeyReleased
// TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtqtdKeyReleased
    
    private void txtprecoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecoFocusGained
// TODO add your handling code here:
        txtpreco.selectAll();
    }//GEN-LAST:event_txtprecoFocusGained
    
    private void rdbpedidoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbpedidoItemStateChanged
// TODO add your handling code here:
        con_orcamento =new conexao();
        con_orcamento.conecta();
        try {
            if(rdbpedido.isSelected()){
                con_orcamento.executeSQL("select MAX (cd_pedido1+1) AS Codigo from pedido");
                con_orcamento.resultset.next();
                txtpedido.setText(con_orcamento.resultset.getString("Codigo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_rdbpedidoItemStateChanged
    
    private void rdborcamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdborcamentoItemStateChanged
// TODO add your handling code here:
        
        con_orcamento =new conexao();
        con_orcamento.conecta();
        try {
            if(rdborcamento.isSelected()){
                con_orcamento.executeSQL("select MAX (cd_orcamento1+1) AS Codigo from orcamento1");
                con_orcamento.resultset.next();
                txtpedido.setText(con_orcamento.resultset.getString("Codigo"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_rdborcamentoItemStateChanged
    
    private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime
// TODO add your handling code here:
        mostra_data.le_hora();
        //  lblhora.setText(mostra_data.hora);
        // this.setTitle("Cadastro Orçamento/ Pedido                    "+ mostra_data.dia_semana+ ", "+mostra_data.dia + " de " + mostra_data.mes + " de "+ mostra_data.ano +"                     "+ mostra_data.hora );
        
    }//GEN-LAST:event_timer1OnTime
    
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
    
    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusGained
        
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
btncadastrar.setEnabled(false);
        cmbnome.requestFocus();
        cmbcor.setSelectedIndex(0);
        cmbnome.setSelectedIndex(0);
        cmbtamanho.setSelectedIndex(0);
        cmbproduto.setSelectedIndex(0);
        cmbtamanho.setSelectedIndex(0);
        cmbnome1.setSelectedIndex(0);
        txtqtd.setText("");
        txtpreco.setText("");
        buttonGroup1.clearSelection();
        lbltotal.setText("0.00");
        lblcodcor.setText(null);
        numeroRegistros=1;
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        txtpedido.setText("");
        
    }//GEN-LAST:event_btnlimparActionPerformed
    public static String converterDoubleString(double precoDouble) {  
   /*Transformando um double em 2 casas decimais*/  
   DecimalFormat fmt = new DecimalFormat("0.00");    //limita o número de casas decimais     
   String string = fmt.format(precoDouble);  
   String[] part = string.split("[,]");  
   String preco = part[0]+"."+part[1];  
   return preco;  
}  
    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
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
            
            if (rdborcamento.isSelected()){
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria ="INSERT INTO orcamento1 (cd_cliente, cd_vendedor, cd_produto, cd_cor, qt_quantidade, vl_unitario, vl_total, vl_ttgeral, dt_pedido, dt_entrega, nm_tamanho, cd_orcamento1 ) VALUES('"+
                            /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                            /* codigo Vendedor */   Integer.parseInt(txtcodigo1.getText())+"','"+
                            /* cod. produto */         Integer.parseInt(jTable1.getValueAt(i, 2).toString().toUpperCase())+"','"+
                            /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*V. unit */            converterDoubleString(Double.parseDouble(jTable1.getValueAt(i, 8).toString()))+"','"+
                            /*V. total */             converterDoubleString(Double.parseDouble(jTable1.getValueAt(i, 9).toString()))+"','"+
                            /*V. geral */             Double.parseDouble(lbltotal.getText())+"','"+
                            /*data pedido */       data+"','"+
                            /*data entrega */      data1+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 4).toString().toUpperCase()+"','"+
                            /* codigo Cliente */   Integer.parseInt(txtpedido.getText())+"')";
                    con_orcamento.statement.executeUpdate(sqlinsertmateria);
                      
                    
                }
                JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!"); 
                String nome = "Deseja imprimir o orçamento: "+Integer.parseInt(txtpedido.getText())+"?";
                    int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                    if(opcao_escolha==JOptionPane.YES_OPTION){
                        new Rel_Orcamento_1();
                        cmbnome.requestFocus();
                        cmbcor.setSelectedIndex(0);
                        cmbnome.setSelectedIndex(0);
                        cmbtamanho.setSelectedIndex(0);
                        cmbproduto.setSelectedIndex(0);
                        cmbtamanho.setSelectedIndex(0);
                        cmbnome1.setSelectedIndex(0);
                        txtqtd.setText("");
                        txtpreco.setText("");
                        buttonGroup1.clearSelection();
                        lbltotal.setText("0.00");
                        numeroRegistros=1;
                        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                        jTable1.updateUI();
                        txtpedido.setText("");
                        
                        
                        
                    }else
                        
                        cmbnome.requestFocus();
                    cmbcor.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    cmbtamanho.setSelectedIndex(0);
                    cmbproduto.setSelectedIndex(0);
                    cmbtamanho.setSelectedIndex(0);
                    cmbnome1.setSelectedIndex(0);
                    txtqtd.setText("");
                    txtpreco.setText("");
                    buttonGroup1.clearSelection();
                    lbltotal.setText("0.00");
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    txtpedido.setText("");
            } 
            if (rdbpedido.isSelected()){
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                    //  INSERT INTO MateriaPrima
                    
                    String sqlinsertmateria1 ="INSERT INTO Pedido (cd_cliente, cd_vendedor, cd_produto, cd_cor, qt_quantidade, vl_unitario, vl_total, vl_ttgeral, dt_pedido, dt_entrega, nm_tamanho, cd_pedido1 ) VALUES('"+
                            /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                            /* codigo Vendedor */   Integer.parseInt(txtcodigo1.getText())+"','"+
                            /* cod. produto */         Integer.parseInt(jTable1.getValueAt(i, 2).toString().toUpperCase())+"','"+
                            /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 5).toString())+"','"+
                            /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 7).toString())+"','"+
                            /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                            /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 9).toString())+"','"+
                            /*V. geral */             Double.parseDouble(lbltotal.getText())+"','"+
                            /*data pedido */       data+"','"+
                            /*data entrega */      data1+"','"+
                            /*Tamanho */         jTable1.getValueAt(i, 4).toString().toUpperCase()+"','"+
                            /* codigo Cliente */   Integer.parseInt(txtpedido.getText())+"')";
                    con_orcamento.statement.executeUpdate(sqlinsertmateria1);
                                  
                }
            }
             JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");  
            String nome = "Deseja imprimir o pedido: "+Integer.parseInt(txtpedido.getText())+"?";
                    int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
                    if(opcao_escolha==JOptionPane.YES_OPTION){
                        new Rel_Pedido_Cadastro();
                        cmbnome.requestFocus();
                        cmbcor.setSelectedIndex(0);
                        cmbnome.setSelectedIndex(0);
                        cmbtamanho.setSelectedIndex(0);
                        cmbproduto.setSelectedIndex(0);
                        cmbtamanho.setSelectedIndex(0);
                        cmbnome1.setSelectedIndex(0);
                        txtqtd.setText("");
                        txtpreco.setText("");
                        buttonGroup1.clearSelection();
                        lbltotal.setText("0.00");
                        numeroRegistros=1;
                        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                        jTable1.updateUI();
                        txtpedido.setText("");
                        
                        
                        
                    }else
                        
                        cmbnome.requestFocus();
                    cmbcor.setSelectedIndex(0);
                    cmbnome.setSelectedIndex(0);
                    cmbtamanho.setSelectedIndex(0);
                    cmbproduto.setSelectedIndex(0);
                    cmbtamanho.setSelectedIndex(0);
                    cmbnome1.setSelectedIndex(0);
                    txtqtd.setText("");
                    txtpreco.setText("");
                    buttonGroup1.clearSelection();
                    lbltotal.setText("0.00");
                    numeroRegistros=1;
                    ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
                    jTable1.updateUI();
                    txtpedido.setText("");
            
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
    }//GEN-LAST:event_btncadastrarActionPerformed
    
    private void btninserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninserirActionPerformed
// TODO add your handling code here:
        if(cmbnome.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o cliente!");
            cmbnome.requestFocus();
        } else if(cmbnome1.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o vendedor!");
            cmbnome1.requestFocus();
        } else if (txtpedido.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Selecione opção pedido ou orçcamento!");
            txtpedido.requestFocus();
        } else if (cmbproduto.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o produto!");
            cmbproduto.requestFocus();
        } else if(cmbcor.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione a cor!");
            cmbcor.requestFocus();
        }
        
        else if (cmbtamanho.getSelectedItem().equals("-")){
            JOptionPane.showMessageDialog(null,"Selecione o tamanho!");
            cmbtamanho.requestFocus();
        } else if (txtqtd.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Digite a quantidade!");
            txtqtd.requestFocus();
        } else if (txtpreco.getText().equals("0.00")){
            JOptionPane.showMessageDialog(null,"Digite preço unitário!");
            txtpreco.requestFocus();
        } else{
            final DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            btncadastrar.setEnabled(true);
            lbltotal.setText((new String().valueOf(Integer
                    .parseInt(txtqtd.getText())
                    * Double.parseDouble(txtpreco.getText()))));
            
            
            
            modelo.addRow(new Object []{numeroRegistros++,txtpedido.getText(),lblcodproduto.getText(),cmbproduto.getSelectedItem(),cmbtamanho.getSelectedItem(),lblcodcor.getText(),cmbcor.getSelectedItem(),txtqtd.getText(),txtpreco.getText(), lbltotal.getText()});
            cmbcor.setSelectedIndex(0);
            cmbproduto.setSelectedIndex(0);
            cmbtamanho.setSelectedIndex(0);
            txtqtd.setText("");
            txtpreco.setText("0.00");
            
            
            double total = 0.0;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                total += Double.parseDouble(jTable1.getValueAt(i, 9)
                .toString());
                
            }
            lbltotal.setText(String.valueOf(total));
        }
    }//GEN-LAST:event_btninserirActionPerformed
    
    private void jPanel7ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel7ComponentHidden
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel7ComponentHidden
    
    private void jPanel6ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel6ComponentHidden
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel6ComponentHidden
    
    private void jPanel5ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel5ComponentHidden
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel5ComponentHidden
    
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
    
    private void jPanel4ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel4ComponentHidden
// TODO add your handling code here:
    }//GEN-LAST:event_jPanel4ComponentHidden
    
    private void cmbnome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnome1ActionPerformed
// TODO add your handling code here:
        
        try {
            
            con_vendedor.resultset.first();
            
            String igual = "n";
            while(igual == "n"){
                if(con_vendedor.resultset.getString("nm_vendedor").equals(cmbnome1.getSelectedItem())){
                    igual = "s";
                } else
                    con_vendedor.resultset.next();
            }
            txtcodigo1.setText(con_vendedor.resultset.getString(String.valueOf("cd_vendedor")));
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }//GEN-LAST:event_cmbnome1ActionPerformed
    
    private void cmbnomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbnomeActionPerformed
// TODO add your handling code here:
        
        
        try {
            
            con_cliente.resultset.first();
            String igual = "n";
            while(igual == "n"){
                if(con_cliente.resultset.getString("nm_cliente").equals(cmbnome.getSelectedItem())){
                    igual = "s";
                } else
                    con_cliente.resultset.next();
            }
            
            ftxttel1.setText(con_cliente.resultset.getString(String.valueOf("cd_tel1")));
            ftxttel2.setText(con_cliente.resultset.getString(String.valueOf("cd_tel2")));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        
        
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
                new CadastroPedido().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btncadastrar;
    private java.awt.Button btninserir;
    private java.awt.Button btnlimpar;
    private java.awt.Button btnsair;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbcor;
    private javax.swing.JComboBox cmbnome;
    private javax.swing.JComboBox cmbnome1;
    private javax.swing.JComboBox cmbproduto;
    private javax.swing.JComboBox cmbtamanho;
    private javax.swing.JFormattedTextField ftxtdataemissao;
    private javax.swing.JFormattedTextField ftxtdataentrada;
    private javax.swing.JFormattedTextField ftxttel1;
    private javax.swing.JFormattedTextField ftxttel2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblcodcor;
    private javax.swing.JLabel lblcodproduto;
    private javax.swing.JLabel lblnome8;
    private javax.swing.JLabel lblnome9;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JRadioButton rdborcamento;
    private javax.swing.JRadioButton rdbpedido;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcodigo1;
    public static javax.swing.JTextField txtpedido;
    private javax.swing.JTextField txtpreco;
    private javax.swing.JTextField txtqtd;
    // End of variables declaration//GEN-END:variables
    
}
