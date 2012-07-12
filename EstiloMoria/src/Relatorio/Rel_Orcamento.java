/*
 * Rel_Pedido.java
 *
 * Created on 16 de Setembro de 2011, 16:25
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
public class Rel_Orcamento {
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Pedido */
    public Rel_Orcamento() {
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select  c.*, v.cd_vendedor,v.nm_vendedor, p.cd_produto,p.nm_produto, cl.cd_cliente,cl.nm_cliente, co.* FROM Orcamento1 as c natural join vendedor as v natural join produto  as p natural join cliente as cl natural join cor  as co where cd_orcamento1='"
                    + ConsultaOrcamento.txtorcamento.getText()+ "'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Orcamento3.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro " + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Orcamento();
  }  
}
    
    

