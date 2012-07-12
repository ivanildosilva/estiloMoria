/*
 * Rel_Venda_Data.java
 *
 * Created on 18 de Setembro de 2011, 17:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Relatorio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import javax.swing.JOptionPane;
import Controle.conexao;
import View.*;
import org.dom4j.rule.NullAction;
/**
 *
 * @author Familia-Silva
 */
public class Rel_Venda_Nome_Carteira {
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Venda_Data */
    public Rel_Venda_Nome_Carteira() {
    try {
            con_rel.conecta();
            con_rel.executeSQL("SELECT cl.nm_cliente, re.dt_venda, re.vl_valor, re.dt_vencimento1, re.vl_parcela1, re.dt_vencimento2, re.vl_parcela2, re.dt_vencimento3, re.vl_parcela3, re.dt_vencimento4, re.vl_parcela4, re.dt_vencimento5, re.vl_parcela5 FROM contareceber1 as re  Natural JOIN  cliente as cl where nm_cliente LIKE  '"
				+ ContaReceberCarteira_1_1.txtfornecedor.getText().toUpperCase() + "%' order by dt_venda");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_venda_nome_Carteira.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro " + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Venda_Data();
  }  
}