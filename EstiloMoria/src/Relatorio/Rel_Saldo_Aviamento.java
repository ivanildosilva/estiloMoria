/*
 * Rel_Saldo_Aviamento.java
 *
 * Created on 27 de Setembro de 2011, 14:14
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
public class Rel_Saldo_Aviamento {
    conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Saldo_Aviamento */
    public Rel_Saldo_Aviamento() {
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select  a.nm_aviamento, cc.nm_cor, m.qt_metro, m.qt_peca, m.nm_tamanho FROM saldomateriaprima as m natural join cor  as cc natural join aviamento as a where cd_aviamento <> '"
                    +0+ "' order by nm_aviamento");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Saldo_Aviamento_Geral.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
    
}