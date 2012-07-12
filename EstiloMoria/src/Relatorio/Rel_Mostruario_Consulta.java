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
public class Rel_Mostruario_Consulta {
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Pedido */
    public Rel_Mostruario_Consulta() {
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select t.nm_vendedor, p.nm_produto,c.nm_cor, f.qt_p,f.qt_m,f.qt_g,f.qt_gg,f.qt_eg,f.qt_exg,f.vl_unitario,f.ds_observacao,f.dt_envio_mostruario,f.dt_devolucao_mostruario FROM mostruario1 f Left Join Vendedor t ON (f.cd_vendedor=t.cd_vendedor) left join produto p on (f.cd_produtoacabado=p.cd_produto) left join cor c on (f.cd_cor=c.cd_cor) where cd_mostruario='"
                    +ConsultaMostruario.txtmostruario.getText()+ "'");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Mostruario2.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Mostruario();
  }  
}
    
    

