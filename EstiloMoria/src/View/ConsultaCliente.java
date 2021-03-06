/*
 * ConsultaCliente.java
 *
 * Created on 5 de Setembro de 2011, 20:03
 */

package View;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import java.sql.SQLException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.MaskFormatter;
import Controle.*;
import Modelo.*;
import org.lavieri.modelutil.cep.WebServiceCep;

/**
 *
 * @author  Familia-Silva
 */
public class ConsultaCliente extends javax.swing.JFrame {
   private MaskFormatter FormatoData, FormatoCpf, FormatoCnpj, FormatoCep, FormatoTel1, FormatoTel2, FormatoTel3, FormatoTel4;
    private conexao con_cliente, con_endereco, con_telefone, con_email;
    
    
    
    public ConsultaCliente() {
        initComponents();
        enter();
        con_cliente=new conexao();
        con_cliente.conecta();
         setResizable(false);
        con_endereco=new conexao();
        con_endereco.conecta();
        
        con_telefone=new conexao();
        con_telefone.conecta();
        
        con_email=new conexao();
        con_email.conecta();
        
        con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
        con_telefone.executeSQL("Select * from telefone order by cd_telefone");
        con_email.executeSQL("Select * from email order by cd_email");
        con_endereco.executeSQL("Select * from endereco order by cd_endereco");
        
        try {
            while(con_cliente.resultset.next())
                cmbnome.addItem(con_cliente.resultset.getString("nm_cliente"));
            
            
        } catch(SQLException erro) {
            JOptionPane.showMessageDialog(null,"N�o localizou dados "+erro);
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
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbnome = new javax.swing.JComboBox();
        try
        {
            FormatoCpf = new MaskFormatter("###.###.###-##");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxtcpf = new JFormattedTextField (FormatoCpf);
        try
        {
            FormatoCnpj = new MaskFormatter("##.###.###/####-##");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxtcnpj = new JFormattedTextField (FormatoCnpj);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtfantasia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ftxtdia = new javax.swing.JFormattedTextField();
        txtmes = new javax.swing.JFormattedTextField();
        txtano = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsexo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtrg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        try
        {
            FormatoTel1 = new MaskFormatter("(##)####-####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxttel1 = new JFormattedTextField (FormatoTel1);
        lblnome8 = new javax.swing.JLabel();
        try
        {
            FormatoTel1 = new MaskFormatter("(##)####-####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxttel2 = new JFormattedTextField (FormatoTel1);
        lblnome9 = new javax.swing.JLabel();
        try
        {
            FormatoTel1 = new MaskFormatter("(##)####-####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxttel3 = new JFormattedTextField (FormatoTel1);
        lblnome10 = new javax.swing.JLabel();
        try
        {
            FormatoTel1 = new MaskFormatter("(##)####-####");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxttel4 = new JFormattedTextField (FormatoTel1);
        lblnome11 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtobs = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        try
        {
            FormatoCep = new MaskFormatter("##.###-###");
        }
        catch (Exception erro)
        {
            JOptionPane.showMessageDialog(null, "N�o foi poss�vel gerar m�scara, erro = " +erro);
        }

        ftxtcep = new JFormattedTextField (FormatoCep);
        lblnome14 = new javax.swing.JLabel();
        lblnome15 = new javax.swing.JLabel();
        txtlogradouro = new javax.swing.JTextField();
        txtnumero = new javax.swing.JTextField();
        lblnome17 = new javax.swing.JLabel();
        txtcomplemento = new javax.swing.JTextField();
        lblnome18 = new javax.swing.JLabel();
        txtbairro = new javax.swing.JTextField();
        lblnome19 = new javax.swing.JLabel();
        lblnome20 = new javax.swing.JLabel();
        txtcidade = new javax.swing.JTextField();
        txtuf = new javax.swing.JTextField();
        lblnome21 = new javax.swing.JLabel();
        btnalterar = new java.awt.Button();
        btndeleta = new java.awt.Button();
        btnlimpar = new java.awt.Button();
        btnsair = new java.awt.Button();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Cliente");
        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));
        txtcodigo.setEnabled(false);
        jPanel1.add(txtcodigo);
        txtcodigo.setBounds(10, 40, 50, 20);

        jLabel1.setText("C\u00f3digo");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 50, 14);

        cmbnome.setMaximumRowCount(5);
        cmbnome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbnomeActionPerformed(evt);
            }
        });

        jPanel1.add(cmbnome);
        cmbnome.setBounds(70, 40, 260, 20);

        ftxtcpf.setEnabled(false);
        jPanel1.add(ftxtcpf);
        ftxtcpf.setBounds(340, 40, 100, 20);

        ftxtcnpj.setEnabled(false);
        jPanel1.add(ftxtcnpj);
        ftxtcnpj.setBounds(450, 40, 120, 20);

        jLabel2.setText("Nome");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 20, 50, 14);

        jLabel3.setText("Cpf");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(340, 20, 50, 14);

        jLabel4.setText("Cnpj");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(450, 20, 50, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 580, 70);

        jPanel2.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        jPanel2.add(txtfantasia);
        txtfantasia.setBounds(10, 40, 250, 20);

        jLabel5.setText("Fantasia");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 20, 60, 14);

        jPanel2.add(ftxtdia);
        ftxtdia.setBounds(270, 40, 20, 20);

        jPanel2.add(txtmes);
        txtmes.setBounds(300, 40, 20, 20);

        jPanel2.add(txtano);
        txtano.setBounds(330, 40, 40, 20);

        jLabel6.setText("Nascimento");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(270, 20, 80, 14);

        jPanel2.add(txtsexo);
        txtsexo.setBounds(380, 40, 90, 20);

        jLabel7.setText("Sexo");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(380, 20, 60, 14);

        jPanel2.add(txtrg);
        txtrg.setBounds(480, 40, 90, 20);

        jLabel8.setText("Rg");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(480, 20, 60, 14);

        jPanel2.add(ftxttel1);
        ftxttel1.setBounds(10, 90, 100, 20);

        lblnome8.setText("Tel.1");
        jPanel2.add(lblnome8);
        lblnome8.setBounds(10, 70, 80, 14);

        jPanel2.add(ftxttel2);
        ftxttel2.setBounds(120, 90, 100, 20);

        lblnome9.setText("Tel.2");
        jPanel2.add(lblnome9);
        lblnome9.setBounds(120, 70, 80, 14);

        jPanel2.add(ftxttel3);
        ftxttel3.setBounds(230, 90, 100, 20);

        lblnome10.setText("Tel.3");
        jPanel2.add(lblnome10);
        lblnome10.setBounds(230, 70, 80, 14);

        jPanel2.add(ftxttel4);
        ftxttel4.setBounds(340, 90, 100, 20);

        lblnome11.setText("Tel.4");
        jPanel2.add(lblnome11);
        lblnome11.setBounds(340, 70, 80, 14);

        jPanel2.add(txtemail);
        txtemail.setBounds(10, 140, 250, 20);

        jLabel9.setText("Email");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(10, 120, 60, 14);

        jPanel2.add(txtobs);
        txtobs.setBounds(270, 140, 300, 20);

        jLabel10.setText("Observa\u00e7\u00e3o");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(270, 120, 100, 14);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 80, 580, 170);

        jPanel3.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Endere\u00e7o"));
        ftxtcep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ftxtcepFocusLost(evt);
            }
        });

        jPanel3.add(ftxtcep);
        ftxtcep.setBounds(10, 50, 80, 20);

        lblnome14.setText("CEP");
        jPanel3.add(lblnome14);
        lblnome14.setBounds(10, 30, 80, 14);

        lblnome15.setText("Logradouro");
        jPanel3.add(lblnome15);
        lblnome15.setBounds(100, 30, 110, 14);

        jPanel3.add(txtlogradouro);
        txtlogradouro.setBounds(100, 50, 400, 20);

        txtnumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumeroKeyTyped(evt);
            }
        });

        jPanel3.add(txtnumero);
        txtnumero.setBounds(510, 50, 60, 20);

        lblnome17.setText("N\u00famero");
        jPanel3.add(lblnome17);
        lblnome17.setBounds(510, 30, 60, 14);

        jPanel3.add(txtcomplemento);
        txtcomplemento.setBounds(10, 100, 100, 20);

        lblnome18.setText("Complemento");
        jPanel3.add(lblnome18);
        lblnome18.setBounds(10, 80, 100, 14);

        jPanel3.add(txtbairro);
        txtbairro.setBounds(120, 100, 200, 20);

        lblnome19.setText("Bairro");
        jPanel3.add(lblnome19);
        lblnome19.setBounds(120, 80, 100, 14);

        lblnome20.setText("Cidade");
        jPanel3.add(lblnome20);
        lblnome20.setBounds(330, 80, 100, 14);

        jPanel3.add(txtcidade);
        txtcidade.setBounds(330, 100, 200, 20);

        txtuf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtufKeyTyped(evt);
            }
        });

        jPanel3.add(txtuf);
        txtuf.setBounds(540, 100, 30, 20);

        lblnome21.setText("UF");
        jPanel3.add(lblnome21);
        lblnome21.setBounds(540, 80, 30, 14);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 250, 580, 130);

        btnalterar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnalterar.setLabel("Alterar");
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        getContentPane().add(btnalterar);
        btnalterar.setBounds(100, 390, 80, 30);

        btndeleta.setFont(new java.awt.Font("Dialog", 1, 12));
        btndeleta.setLabel("Deletar");
        btndeleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletaActionPerformed(evt);
            }
        });

        getContentPane().add(btndeleta);
        btndeleta.setBounds(10, 390, 80, 30);

        btnlimpar.setFont(new java.awt.Font("Dialog", 1, 12));
        btnlimpar.setLabel("Limpar");
        btnlimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimparActionPerformed(evt);
            }
        });

        getContentPane().add(btnlimpar);
        btnlimpar.setBounds(190, 390, 80, 30);

        btnsair.setFont(new java.awt.Font("Dialog", 1, 12));
        btnsair.setLabel("Sair");
        btnsair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsairActionPerformed(evt);
            }
        });

        getContentPane().add(btnsair);
        btnsair.setBounds(280, 390, 80, 30);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-615)/2, (screenSize.height-469)/2, 615, 469);
    }// </editor-fold>//GEN-END:initComponents
 public static long carregarMemoria(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i<1000000; i++){
		list.add(i);	
		}
		return Runtime.getRuntime().freeMemory();
	}       
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
            
            
            txtemail.setText(con_cliente.resultset.getString(String.valueOf("nm_email1")));
            
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
            
            ftxttel1.setText(con_cliente.resultset.getString(String.valueOf("cd_tel1")));
            ftxttel2.setText(con_cliente.resultset.getString(String.valueOf("cd_tel2")));
            ftxttel3.setText(con_cliente.resultset.getString(String.valueOf("cd_tel3")));
            ftxttel4.setText(con_cliente.resultset.getString(String.valueOf("cd_tel4")));
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
            
            ftxtcep.setText(con_cliente.resultset.getString(String.valueOf("cd_cep")));
            txtlogradouro.setText(con_cliente.resultset.getString(String.valueOf("nm_logradouro")));
            txtnumero.setText(con_cliente.resultset.getString(String.valueOf("cd_numero")));
            txtcomplemento.setText(con_cliente.resultset.getString(String.valueOf("nm_complemento")));
            txtbairro.setText(con_cliente.resultset.getString(String.valueOf("nm_bairro")));
            txtcidade.setText(con_cliente.resultset.getString(String.valueOf("nm_cidade")));
            txtuf.setText(con_cliente.resultset.getString(String.valueOf("sg_ufederativa")));
            txtobs.setText(con_cliente.resultset.getString(String.valueOf("nm_observacao")));
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
            ftxtcnpj.setText(con_cliente.resultset.getString(String.valueOf("cd_cnpj_cliente")));
            ftxtcpf.setText(con_cliente.resultset.getString(String.valueOf("cd_cpf_cliente")));
            txtfantasia.setText(con_cliente.resultset.getString(String.valueOf("nm_fantasia_cliente")));
            ftxtdia.setText(con_cliente.resultset.getString(String.valueOf("dt_dia_nascimento")));
            txtmes.setText(con_cliente.resultset.getString(String.valueOf("dt_mes_nascimento")));
            txtano.setText(con_cliente.resultset.getString(String.valueOf("dt_ano_nascimento")));
            txtsexo.setText(con_cliente.resultset.getString(String.valueOf("ic_sexo_cliente")));
            txtrg.setText(con_cliente.resultset.getString(String.valueOf("cd_rg_cliente")));
            
            mostraDados();
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        
        
    }//GEN-LAST:event_cmbnomeActionPerformed
    
    public void mostraDados(){
        
        
    }
    
    private void btnsairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsairActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsairActionPerformed
    
    private void btnlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimparActionPerformed
// TODO add your handling code here:
        cmbnome.setSelectedIndex(0);
    }//GEN-LAST:event_btnlimparActionPerformed
    
    private void btndeletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletaActionPerformed
// TODO add your handling code here:
        
        
        try {
            String nome = "Deletar: "+cmbnome.getSelectedItem()+"?";
            int opcao_escolha = JOptionPane.showConfirmDialog(null,nome,"Exlus�o", JOptionPane.YES_NO_OPTION);
            if(opcao_escolha==JOptionPane.YES_OPTION){
                String sql = "DELETE from cliente where cd_cliente="+Integer.parseInt(txtcodigo.getText());
                int conseguiu_excluir = con_cliente.statement.executeUpdate(sql);
                
                
                if(conseguiu_excluir==1){
                    JOptionPane.showMessageDialog(null, "Exclus�o realizada com sucesso!!!");
                    
                    con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
                    con_cliente.resultset.first();
                    con_endereco.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
                    con_endereco.resultset.first();
                    con_email.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
                    con_email.resultset.first();
                    con_telefone.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
                    con_telefone.resultset.first();
                    
                    cmbnome.setSelectedIndex(0);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
            
        }
        
    }//GEN-LAST:event_btndeletaActionPerformed
    
    private void btnalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalterarActionPerformed
        
        
        try {
            String sql = "UPDATE Cliente Set nm_fantasia_cliente='"+txtfantasia.getText().toUpperCase()+"',"+
                    "dt_dia_nascimento='"+ftxtdia.getText()+"',"+
                    "dt_mes_nascimento='"+txtmes.getText()+"',"+
                    "dt_ano_nascimento='"+txtano.getText()+"',"+
                    "cd_rg_cliente='"+txtrg.getText()+"' where cd_cliente ='"+txtcodigo.getText()+"'";
            con_cliente.statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Altera��o realizada com sucesso!!!");
            
            //isso serve para atualizar o resultset
            con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
            con_cliente.resultset.next();
            
            
            String sql1 = "UPDATE Email Set nm_email1='"+txtemail.getText().toLowerCase()+"' where cd_cliente ='"+txtcodigo.getText()+"'";
            con_email.statement.executeUpdate(sql1);
            con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
            con_cliente.resultset.next();
            
            String sql2 = "UPDATE Telefone Set cd_tel1='"+ftxttel1.getText().replace("(","").replace(")","").replace("-","")+"',"+
                    "cd_tel2='"+ftxttel2.getText().replace("(","").replace(")","").replace("-","")+"',"+
                    "cd_tel3='"+ftxttel3.getText().replace("(","").replace(")","").replace("-","")+"',"+
                    "cd_tel4='"+ftxttel4.getText().replace("(","").replace(")","").replace("-","")+"' where cd_cliente ='"+txtcodigo.getText()+"'";
            con_telefone.statement.executeUpdate(sql2);
            con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
            con_cliente.resultset.next();
            
            String sql4 = "UPDATE Endereco Set cd_cep='"+ftxtcep.getText().replace(".","").replace("-","")+"',"+
                    "nm_logradouro='"+txtlogradouro.getText().toUpperCase()+"',"+
                    "nm_observacao='"+txtobs.getText().toUpperCase()+"',"+
                    "sg_ufederativa='"+txtuf.getText().toUpperCase()+"',"+
                    "nm_cidade='"+txtcidade.getText().toUpperCase()+"',"+
                    "nm_bairro='"+txtbairro.getText().toUpperCase()+"',"+
                    "nm_complemento='"+txtcomplemento.getText().toUpperCase()+"',"+
                    "cd_numero='"+txtnumero.getText()+"' where cd_cliente ='"+txtcodigo.getText()+"'";
            con_endereco.statement.executeUpdate(sql4);
            con_cliente.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.dt_dia_nascimento, c.dt_mes_nascimento,c.dt_ano_nascimento,c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) order by nm_cliente");
            con_cliente.resultset.next();
            
            cmbnome.setSelectedIndex(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_btnalterarActionPerformed
    
    private void txtufKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtufKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
        String conteudo = txtuf.getText();
        if (conteudo.length() >= 2) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtufKeyTyped
    
    private void txtnumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumeroKeyTyped
// S� aceita numero
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtnumeroKeyTyped
    
    private void ftxtcepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ftxtcepFocusLost
        WebServiceCep cep = WebServiceCep.searchCep(ftxtcep.getText());
        
        if (cep.wasSuccessful()) {
            
            ftxtcep.setText(cep.getCep().toString());
            txtlogradouro.setText(cep.getLogradouroFull().toString());
            txtbairro.setText(cep.getBairro().toString());
            txtcidade.setText(cep.getCidade().toString());
            txtuf.setText(cep.getUf().toString());
        } else {
            
            JOptionPane.showMessageDialog(null, "Erro n�mero  " + cep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descri��o do erro " + cep.getResultText());
            
        }
    }//GEN-LAST:event_ftxtcepFocusLost
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaCliente().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnalterar;
    private java.awt.Button btndeleta;
    private java.awt.Button btnlimpar;
    private java.awt.Button btnsair;
    private javax.swing.JComboBox cmbnome;
    private javax.swing.JFormattedTextField ftxtcep;
    private javax.swing.JFormattedTextField ftxtcnpj;
    private javax.swing.JFormattedTextField ftxtcpf;
    private javax.swing.JFormattedTextField ftxtdia;
    private javax.swing.JFormattedTextField ftxttel1;
    private javax.swing.JFormattedTextField ftxttel2;
    private javax.swing.JFormattedTextField ftxttel3;
    private javax.swing.JFormattedTextField ftxttel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblnome10;
    private javax.swing.JLabel lblnome11;
    private javax.swing.JLabel lblnome14;
    private javax.swing.JLabel lblnome15;
    private javax.swing.JLabel lblnome17;
    private javax.swing.JLabel lblnome18;
    private javax.swing.JLabel lblnome19;
    private javax.swing.JLabel lblnome20;
    private javax.swing.JLabel lblnome21;
    private javax.swing.JLabel lblnome8;
    private javax.swing.JLabel lblnome9;
    private javax.swing.JFormattedTextField txtano;
    private javax.swing.JTextField txtbairro;
    private javax.swing.JTextField txtcidade;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcomplemento;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfantasia;
    private javax.swing.JTextField txtlogradouro;
    private javax.swing.JFormattedTextField txtmes;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtobs;
    private javax.swing.JTextField txtrg;
    private javax.swing.JTextField txtsexo;
    private javax.swing.JTextField txtuf;
    // End of variables declaration//GEN-END:variables
    
}
