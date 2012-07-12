/*
 * Rel_Envio_Fech_Prod.java
 *
 * Created on 18 de Setembro de 2011, 14:35
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
public class Rel_Envio_Fech_Prod_1 {
    conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Envio_Fech_Prod */
    public Rel_Envio_Fech_Prod_1() {
     try {
            con_rel.conecta();
            con_rel.executeSQL("Select  f.*, t.nm_contato, c.nm_cor,p.nm_produto FROM fechprod as f natural join Terceirizada as t natural join Cor  as c natural join Produto as p where cd_numlote1='"
                    + CadastroMostruario_1_1.txtlote.getText()+ "'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Envio_Terceirizada.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro " + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Envio_Fech_Prod();
  }  
}