/*
 * ColorRenderer.java
 *
 * Created on 2 de Fevereiro de 2012, 11:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Utilitarios;

import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 *
 * @author Familia-Silva
 */
public class ColorRenderer extends DefaultTableCellRenderer {
    
    /** Creates a new instance of ColorRenderer */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
       Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
      
       if (row % 2 == 0) {  
           comp.setBackground(Color.LIGHT_GRAY);
           //comp.setForeground(Color.BLUE);
       } else {  
           comp.setBackground(Color.WHITE);  
       }   //comp.setForeground(Color.GREEN);
         
       return comp;  
    }
    
}
