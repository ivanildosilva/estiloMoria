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
public class Rel_Pedido_Cadastro {
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Pedido */
    public Rel_Pedido_Cadastro() {
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select  cl.nm_cliente, p.nm_produto, co.nm_cor, c.cd_pedido1,c.nm_tamanho, c.qt_quantidade,c.vl_unitario,c.vl_total,c.vl_ttgeral,c.dt_pedido,c.dt_entrega  FROM Pedido as c natural join produto  as p natural join cliente as cl natural join cor  as co where cd_pedido1='"
				+CadastroPedido.txtpedido.getText()+ "'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Cadastro_Pedido.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Pedido_Cadastro();
  }  
}
    
    

