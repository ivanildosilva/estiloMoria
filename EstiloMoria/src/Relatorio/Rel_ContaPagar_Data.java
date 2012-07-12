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
public class Rel_ContaPagar_Data {
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Venda_Data */
    public Rel_ContaPagar_Data() {
    try {
            con_rel.conecta();
            con_rel.executeSQL("SELECT m.dt_dtcontapagar, n.*, m.cd_notafiscal, m.vl_contapagar  FROM Contapagar as m  NATURAL JOIN fornecedor as n where dt_dtcontapagar = '"
                    + ContaPagar.ftxtinicio.getText() + "'order by nm_razaosocial_fornecedor");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_contapagar_data.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro " + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_ContaPagar_Data();
  }  
}