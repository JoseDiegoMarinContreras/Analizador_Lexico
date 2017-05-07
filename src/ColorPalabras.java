
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IVAN
 */
public class ColorPalabras {
    
    StyledDocument doc;
    Style styleA, styleN;
    int p, i, j, f;
    String keyWords[]= {"start", "end", "natural", "integer", "real", "function", "table", "text", "bit", "infinity",
                        "pi", "euler", "if", "else", "during", "from", "to", "do", "terminal", "expression", "thread",
                        "main", "convertion"};
    String cad;
    JTextPane jTextPane1;
    
    public ColorPalabras(JTextPane jTextPane){        
        jTextPane1= jTextPane;
        doc= jTextPane1.getStyledDocument();        
        styleA = doc.addStyle("StyleA", null);
        styleN= doc.addStyle("StyleN", null);
        cad= "";
        i= 1;
        j= f= 0;
    }        
    
    public void pressed(){
        p= jTextPane1.getCaretPosition();
             i= 1;
             j= 0;
             cad= "";
             try {                 
                while(!doc.getText(p-i, 1).equals(" ")){                    
                    cad= doc.getText(p-i, 1)+cad;
                    i++;
                    if((p-i)==-1){
                        break;
                    }                    
                }                 
                 if(p!=doc.getLength()){
                    while(!doc.getText(p+j, 1).equals(" ")){                        
                        cad+= doc.getText(p+j, 1);
                        j++;
                        if(p+j==doc.getLength()){
                            break;
                        }                        
                    }
                 }                 
                 for (int k=0;k<keyWords.length;k++) {
                     if(keyWords[k].equals(cad)){
                         StyleConstants.setForeground(styleA, Color.blue);
                         //JOptionPane.showMessageDialog(null, ((p-i)+1)+"  |  "+(p+j)+"  |  "+doc.getText(((p+j)-1), 1));
                         doc.setCharacterAttributes((p-i)+1, (p+j), styleA, true);                         
                     }
                 }
             } catch (BadLocationException ex) {
                 //Logger.getLogger(VentanaColores.class.getName()).log(Level.SEVERE, null, ex);
             }
    }        
        
    public void released(){
        if(jTextPane1.getCaretPosition()==0){                
                return;
        }
        p= jTextPane1.getCaretPosition();            
        i= 1;
        j= 0;
        cad= "";
        try {
            while(!doc.getText(p-i, 1).equals(" ")){
                cad= doc.getText(p-i, 1)+cad;
                i++;
                if((p-i)==-1){
                    break;
                }
            }
             if(p!=doc.getLength()){
                while(!doc.getText(p+j, 1).equals(" ")){
                    cad+= doc.getText(p+j, 1);
                    j++;
                    if(p+j==doc.getLength()){
                        break;
                    }
                }
            }
            //JOptionPane.showMessageDialog(null, cad);
            for(int k=0;k<keyWords.length;k++) {                
                if(keyWords[k].equals(cad)){
                    JOptionPane.showMessageDialog(null, cad+"  |  "+keyWords[k]);  
                    StyleConstants.setForeground(styleN, Color.black);
                    doc.setCharacterAttributes((p-i)+1, p+j, styleN, true);
                    f= 1;
                }
            }
            if(f==0){
                p= jTextPane1.getCaretPosition();
                i= 2;
                j= 0;
                cad= "";
                while(!doc.getText(p-i, 1).equals(" ")){
                    cad= doc.getText(p-i, 1)+cad;
                    i++;
                    if((p-i)==-1){
                        break;
                    }
                }
                if(p!=doc.getLength()){
                   while(!doc.getText(p+j, 1).equals(" ")){
                       cad+= doc.getText(p+j, 1);
                        j++;
                        if(p+j==doc.getLength()){
                            break;
                        }
                    }
                }
                //JOptionPane.showMessageDialog(null, cad+"  |");
                for(int k=0;k<keyWords.length;k++) {
                    if(keyWords[k].equals(cad)){
                       StyleConstants.setForeground(styleN, Color.black);
                       doc.setCharacterAttributes((p+j)-1, 1, styleN, true);
                       //JOptionPane.showMessageDialog(null, doc.getText((p+j)-1, 1));
                    }
                }
            }
        } catch (BadLocationException ex) {
             //Logger.getLogger(VentanaColores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
