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
public class Rel_Venda_Nome {
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Venda_Data */
    public Rel_Venda_Nome() {
    try {
            con_rel.conecta();
            con_rel.executeSQL("SELECT m.dt_venda, cl.nm_cliente, p.nm_produto, c.nm_cor, m.nm_tamanho, m.qt_quantidade, m.vl_unitario, m.vl_sbtl, m.nm_formapagamento FROM vendadireta as m  NATURAL JOIN cliente as cl NATURAL JOIN cor as c NATURAL JOIN produto as p where nm_cliente LIKE  '"
				+ ContaReceber.txtfornecedor.getText().toUpperCase() + "%' order by dt_venda");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_venda_nome.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro " + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Venda_Data();
  }  
}