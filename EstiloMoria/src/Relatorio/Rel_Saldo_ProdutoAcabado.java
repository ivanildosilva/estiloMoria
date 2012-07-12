/*
 * Rel_Saldo_ProdutoAcabado.java
 *
 * Created on 27 de Setembro de 2011, 14:38
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
public class Rel_Saldo_ProdutoAcabado {
    conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Saldo_ProdutoAcabado */
    public Rel_Saldo_ProdutoAcabado() {
     try {
            con_rel.conecta();
            con_rel.executeSQL("Select  a.nm_produto, cc.nm_cor, m.qt_p, m.qt_m, m.qt_g, m.qt_gg, m.qt_eg, m.qt_exg, m.vl_unitario FROM produtoacabado as m natural join cor  as cc natural join produto as a where cd_produto <> '"
                    +0+ "' order by nm_produto");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            //JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Saldo_produto_acabado.jasper", new HashMap(), jrRS);
           JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/classic_landscape.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
    
}