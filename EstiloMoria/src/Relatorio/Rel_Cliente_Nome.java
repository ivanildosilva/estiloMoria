/*
 * Rel_Cliente_Nome.java
 *
 * Created on 16 de Setembro de 2011, 14:48
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
public class Rel_Cliente_Nome {
    conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Cliente_Nome */
    public Rel_Cliente_Nome() {
        
         try {
            con_rel.conecta();
            con_rel.executeSQL("Select c.cd_cliente, c.nm_cliente, t.cd_tel1, t.cd_tel2, en.nm_logradouro, en.cd_numero, en.cd_cep,en.nm_bairro,en.nm_cidade,  e.nm_email1 FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) where nm_cliente LIKE  '"
				+ ConsultaGeral.txtnome.getText().toUpperCase() + "%' order by nm_cliente");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Cliente_Nome8.jasper", new HashMap(), jrRS);
           JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Cliente_Nome();
  }  
}

    
    

