/*
 * Rel_Saldo_Tecido.java
 *
 * Created on 27 de Setembro de 2011, 13:54
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
public class Rel_Saldo_Tecido {
    
    /**
     * Creates a new instance of Rel_Saldo_Tecido
     */
     conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Pedido */
    public Rel_Saldo_Tecido() {
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select  a.nm_tecido, cc.nm_cor, m.qt_metro, m.qt_kilo FROM saldomateriaprima as m natural join cor  as cc natural join tecido as a where cd_tecido <> '"
				+0+ "' order by nm_tecido");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_Saldo_Materia_Geral.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false); 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            
            
        }
    }
    
}
