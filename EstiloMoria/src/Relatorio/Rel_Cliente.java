/*
 * Rel_Cliente.java
 *
 * Created on 16 de Setembro de 2011, 11:59
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
import org.dom4j.rule.NullAction;
/**
 *
 * @author Familia-Silva
 */
public class Rel_Cliente {
    conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Cliente */
    public Rel_Cliente() {
        
        
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select c.cd_cliente, c.cd_cpf_cliente, c.cd_cnpj_cliente, c.nm_cliente, c.nm_fantasia_cliente, c.cd_rg_cliente, c.ic_sexo_cliente, t.*, en.*, e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) where nm_cliente <> '-' order by nm_cliente");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Cliente.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Cliente();
  }  
}
