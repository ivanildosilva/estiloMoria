package Relatorio;
import Controle.conexao;
import View.ConsultaAniversario;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Familia-Silva
 */
public class Rel_Aniversariante {
    conexao con_rel = new conexao();
    /** Creates a new instance of Rel_Cliente */
    public Rel_Aniversariante() {
        
        
        try {
            con_rel.conecta();
            con_rel.executeSQL("Select c.nm_cliente, dt_dia_nascimento,dt_mes_nascimento,t.cd_tel1, t.cd_tel2, e.nm_email1, en.nm_logradouro, en.cd_numero, en.cd_cep,en.nm_cidade  FROM Cliente c Left Join Telefone t ON (c.cd_cliente=t.cd_cliente) left join email e on (c.cd_cliente=e.cd_cliente) left join endereco en on (c.cd_cliente=en.cd_cliente) where dt_mes_nascimento = '" + ConsultaAniversario.cmbmes.getSelectedItem() + "'order by dt_dia_nascimento");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con_rel.resultset);
            JasperPrint jasperPrint  = JasperFillManager.fillReport("C:/Projeto Netbeans/EstiloMoria/relatorios/Rel_aniversario.jasper", new HashMap(), jrRS);
            JasperViewer.viewReport(jasperPrint, false);
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"deu erro" + erro);
            System.out.println("deu erro " + erro);
            
            
        }
    }
  public static void main(String arg[]){
      new Rel_Aniversariante();
  }  
}
