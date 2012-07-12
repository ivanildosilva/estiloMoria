/*
 * VendaBalca.java
 *
 * Created on 12 de Setembro de 2011, 10:29
 */

package View;
import Relatorio.Rel_Venda_Balcao;
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
public class VendaBalca extends javax.swing.JFrame {
    private MaskFormatter FormatoData, FormatoCpf, FormatoCnpj, FormatoCep, FormatoTel1, FormatoTel2, FormatoTel3, FormatoTel4;
    private conexao con_cliente, con_produto,con_produto1,con_saldo, con_saldo1,con_cor, con_email,con_vendedor, con_data, con_orcamento,con_produtoacbado;
    final DefaultTableModel model = new DefaultTableModel();
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
    public VendaBalca() {
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
        con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
        con_cor.executeSQL("select * from cor order by nm_cor");
        con_produto.executeSQL("select * from produto order by nm_produto");
        con_produto1.executeSQL("select * from produto order by nm_produto");
        con_orcamento.executeSQL("select * from vendadireta");
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
        cmbpagamento = new javax.swing.JComboBox();
        lblcodproduto15 = new javax.swing.JLabel();
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

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda Balc\u00e3o");
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
        txtnome.setBounds(190, 40, 150, 20);

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

        cmbpagamento.setMaximumRowCount(5);
        cmbpagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Cart\u00e3o Visa - Cr\u00e9dito", "Cart\u00e3o Visa - D\u00e9bito", "Cart\u00e3o Master - Cr\u00e9dito", "Cart\u00e3o Master - D\u00e9bito", "Cart\u00e3o Diners", "Cart\u00e3o Amerian", "Cart\u00e3o Accredito", "Vale Compras", "Cheque", "Dinheiro" }));
        cmbpagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbpagamentoActionPerformed(evt);
            }
        });

        jPanel4.add(cmbpagamento);
        cmbpagamento.setBounds(310, 90, 270, 20);

        lblcodproduto15.setText("Forma Pagamento");
        jPanel4.add(lblcodproduto15);
        lblcodproduto15.setBounds(310, 70, 110, 14);

        lblcodproduto16.setText("Dispon\u00edvel");
        jPanel4.add(lblcodproduto16);
        lblcodproduto16.setBounds(590, 20, 70, 14);

        lblqtd.setFont(new java.awt.Font("Tahoma", 3, 14));
        lblqtd.setText("0");
        jPanel4.add(lblqtd);
        lblqtd.setBounds(610, 40, 40, 14);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(10, 90, 670, 140);

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
        jScrollPane1.setBounds(10, 240, 670, 160);

        btninserir.setFont(new java.awt.Font("Dialog", 1, 12));
        btninserir.setLabel("Inserir");
        btninserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninserirActionPerformed(evt);
            }
        });

        getContentPane().add(btninserir);
        btninserir.setBounds(10, 420, 80, 30);

        btncadastrar.setFont(new java.awt.Font("Dialog", 1, 12));
        btncadastrar.setLabel("Cadastrar");
        btncadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadastrarActionPerformed(evt);
            }
        });

        getContentPane().add(btncadastrar);
        btncadastrar.setBounds(100, 420, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(190, 420, 80, 30);

        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(280, 420, 80, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel3.setText("Total (R$)");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(380, 410, 80, 17);

        lblsaldo.setFont(new java.awt.Font("Tahoma", 3, 14));
        lblsaldo.setText("0.00");
        getContentPane().add(lblsaldo);
        lblsaldo.setBounds(390, 432, 80, 17);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-697)/2, (screenSize.height-500)/2, 697, 500);
    }// </editor-fold>//GEN-END:initComponents
    
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
    public static long carregarMemoria(){
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i<1000000; i++){
            list.add(i);
        }
        return Runtime.getRuntime().freeMemory();
    }
    private void cmbnomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbnomeFocusLost
// TODO add your handling code here:
        //JOptionPane.showMessageDialog(null,"Selecione um produto!");
        
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
        cmbcor.setSelectedIndex(0);
        cmbpagamento.setSelectedIndex(0);
        cmbproduto.setSelectedIndex(0);
        cmbtamanho.setSelectedIndex(0);
        txtqtd.setText("");
        txtunitario.setText("");
        txttotal.setText("");
        txtdesc.setText("");
        cmbnome.setSelectedIndex(0);
        numeroRegistros=1;
        ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
        jTable1.updateUI();
        lblsaldo.setText("0.00");
        lblqtd.setText("0");
        
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btncadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadastrarActionPerformed
// TODO add your handling code here:
        
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = (Date) fmt.parse(ftxtdataemissao.getText());
            
            
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
                
                
                if (jTable1.getValueAt(i, 5).equals("P") ){
                    
                    int estoqueatualp = (qtdestoquetinhap) -  (Integer.parseInt(jTable1.getValueAt(i, 6).toString()));
                    //JOptionPane.showMessageDialog(null,qtdestoquetinhap +   " tinha p ");
                    //JOptionPane.showMessageDialog(null,estoqueatualp +  " Atual p ");
                    String sqlinsertprodutoacabadovp ="Update produtoacabado set qt_p= '"+estoqueatualp+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                            "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                    //JOptionPane.showMessageDialog(null,sqlinsertprodutoacabadovp);
                    con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovp);
                    con_produto.conecta();
                    con_produto.executeSQL("select * from produtoacabado");
                    
                }
                
                //continuar daqui!!!
                if (jTable1.getValueAt(i, 5).equals("M")){
                    int estoqueatualm = qtdestoquetinham -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                    //JOptionPane.showMessageDialog(null,qtdestoquetinham +   " tinha m ");
                    //JOptionPane.showMessageDialog(null,estoqueatualm +  " Atual m ");
                    String sqlinsertprodutoacabadovm ="Update produtoacabado set qt_m= '"+estoqueatualm+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                            "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                    //JOptionPane.showMessageDialog(null,sqlinsertprodutoacabadovm);
                    con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovm);
                     con_produto.conecta();
                    con_produto.executeSQL("select * from produtoacabado");
                }
                if (jTable1.getValueAt(i, 5).equals("G")){
                    int estoqueatualg = qtdestoquetinhag -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                    //JOptionPane.showMessageDialog(null,qtdestoquetinhag +   " tinha g ");
                    //JOptionPane.showMessageDialog(null,estoqueatualg+  " Atual g ");
                    String sqlinsertprodutoacabadovg ="Update produtoacabado set qt_g= '"+estoqueatualg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                            "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                    //JOptionPane.showMessageDialog(null,sqlinsertprodutoacabadovg);
                    con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovg);
                     con_produto.conecta();
                    con_produto.executeSQL("select * from produtoacabado");
                    
                }
                if (jTable1.getValueAt(i, 5).equals("GG")){
                    int estoqueatualgg = qtdestoquetinhagg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                    //JOptionPane.showMessageDialog(null,qtdestoquetinhagg +   " tinha gg ");
                    //JOptionPane.showMessageDialog(null,estoqueatualgg +  " Atual gg ");
                    String sqlinsertprodutoacabadovgg ="Update produtoacabado set qt_gg= '"+estoqueatualgg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                            "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                    //JOptionPane.showMessageDialog(null,sqlinsertprodutoacabadovgg);
                    con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovgg);
                     con_produto.conecta();
                    con_produto.executeSQL("select * from produtoacabado");
                    
                }
                if (jTable1.getValueAt(i, 5).equals("EG")){
                    int estoqueatualeg = qtdestoquetinhaeg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                    //JOptionPane.showMessageDialog(null,qtdestoquetinhaeg+   " tinha eg ");
                    //JOptionPane.showMessageDialog(null,estoqueatualeg+  " Atual eg ");
                    String sqlinsertprodutoacabadoveg ="Update produtoacabado set qt_eg= '"+estoqueatualeg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                            "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                    //JOptionPane.showMessageDialog(null,sqlinsertprodutoacabadoveg);
                    con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadoveg);
                     con_produto.conecta();
                    con_produto.executeSQL("select * from produtoacabado");
                    
                }
                if (jTable1.getValueAt(i, 5).equals("EXG")){
                    int estoqueatualexg = qtdestoquetinhaexg -  Integer.parseInt(jTable1.getValueAt(i, 6).toString());
                    //JOptionPane.showMessageDialog(null,qtdestoquetinhaexg+   " tinha exg ");
                    //JOptionPane.showMessageDialog(null,estoqueatualexg+  " Atual exg ");
                    String sqlinsertprodutoacabadovexg ="Update produtoacabado set qt_exg= '"+estoqueatualexg+"' where cd_produto ='"+Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"' " +
                            "and cd_cor = '"+Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"' ";
                    //JOptionPane.showMessageDialog(null,sqlinsertprodutoacabadovexg);
                    con_orcamento.statement.executeUpdate(sqlinsertprodutoacabadovexg);
                     con_produto.conecta();
                    con_produto.executeSQL("select * from produtoacabado");
                    
                }
                
                String sqlinsertvendadireta ="INSERT INTO Vendadireta (cd_cliente, cd_produto, nm_tamanho, cd_cor, qt_quantidade, vl_unitario, vl_sbtl, vl_ttlgeral, dt_venda, nm_formapagamento) VALUES('"+
                        /* codigo Cliente */   Integer.parseInt(txtcodigo.getText())+"','"+
                        /* codigo produto */   Integer.parseInt(jTable1.getValueAt(i, 1).toString())+"','"+
                        /* cod. tamanho */     jTable1.getValueAt(i, 5).toString().toUpperCase()+"','"+
                        /* cod. cor */ Integer.parseInt(jTable1.getValueAt(i, 3).toString())+"','"+
                        /*quntidade */Integer.parseInt(jTable1.getValueAt(i, 6).toString())+"','"+
                        /*V. unit */            Double.parseDouble(jTable1.getValueAt(i, 7).toString())+"','"+
                        /*V. total */             Double.parseDouble(jTable1.getValueAt(i, 8).toString())+"','"+
                        /*V. geral */             Double.parseDouble(lblsaldo.getText())+"','"+
                        /*data venda */       data+"','"+
                        /*F. Pagamsnto */         cmbpagamento.getSelectedItem().toString().toUpperCase()+"')";
                con_produto.statement.executeUpdate(sqlinsertvendadireta);
                
                
            }
            
            con_produto = new conexao();
            con_produto.conecta();
            con_produto.executeSQL("select * from produtoacabado");
            con_produto.executeSQL("select * from produto order by nm_produto");
            con_produto.executeSQL("select * from vendadireta order by dt_venda");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        JOptionPane.showMessageDialog(null,"Gravação realizado com sucesso!");
        String nome = "Deseja imprimir a Venda do Sr(a): "+txtnome.getText()+"?";
        int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Imprimir", JOptionPane.YES_NO_OPTION);
        if(opcao_escolha==JOptionPane.YES_OPTION){
            new Rel_Venda_Balcao();
            
            cmbpagamento.setSelectedIndex(0);
            cmbnome.setSelectedIndex(0);
            numeroRegistros=1;
            ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
            jTable1.updateUI();
            lblsaldo.setText("0.00");
            lblqtd.setText("0");
            txtcodproduto.setText("0");
        } else{
            
            cmbpagamento.setSelectedIndex(0);
            cmbnome.setSelectedIndex(0);
            numeroRegistros=1;
            ((DefaultTableModel) jTable1.getModel()).setNumRows(0);
            jTable1.updateUI();
            lblsaldo.setText("0.00");
            lblqtd.setText("0");
            txtcodproduto.setText("0");
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
    
    private void cmbpagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbpagamentoActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_cmbpagamentoActionPerformed
    
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
                new VendaBalca().setVisible(true);
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
    private javax.swing.JComboBox cmbpagamento;
    private javax.swing.JComboBox cmbproduto;
    private javax.swing.JComboBox cmbtamanho;
    public static javax.swing.JFormattedTextField ftxtdataemissao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblcodcor;
    private javax.swing.JLabel lblcodproduto10;
    private javax.swing.JLabel lblcodproduto11;
    private javax.swing.JLabel lblcodproduto12;
    private javax.swing.JLabel lblcodproduto13;
    private javax.swing.JLabel lblcodproduto14;
    private javax.swing.JLabel lblcodproduto15;
    private javax.swing.JLabel lblcodproduto16;
    private javax.swing.JLabel lblcodproduto8;
    private javax.swing.JLabel lblcodproduto9;
    private javax.swing.JLabel lblqtd;
    private javax.swing.JLabel lblsaldo;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcodproduto;
    private javax.swing.JTextField txtdesc;
    public static javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtqtd;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtunitario;
    // End of variables declaration//GEN-END:variables
    
}
