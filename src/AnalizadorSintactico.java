/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JoseSantiago
 */
public class AnalizadorSintactico {
    String err;
    String[] tipoDato= {"natural","integer","real","text","bit"};//tipos de datos
    Grafo g;
    
    public AnalizadorSintactico(){
        err = "";
    }
    
    public String compilar(String codigo){
       g = new Grafo();
       String lc[] = codigo.split("\n");
       
       for(int i = 0; i < lc.length; i++){
           //javax.swing.JOptionPane.showMessageDialog(null, lc[i]);
           aSintactico(lc[i].split(","), i);
       }
       return err;
    }
    
    public void aSintactico(String []l, int nl){
        for(int i = 0; i < tipoDato.length; i++){
            if(l[0].equals(tipoDato[i])){
            
            }
        }
        switch(l[0]){
            case "start":{
               break;
            }
            case "expression":{
               break;
            }
            case "from":{
               break;
            }
            case "convertion":{
               break;
            }
            case "in":{
               break;
            }
            case "out":{
               break;
            }
            case "graphic":{
               break;
            }
            case "call":{
               break;
            }
            case "during":{
               break;
            }
            case "Dx":{
               break;
            }
            default:{
            
            }
        }    
    }
    
    public void  aSstart(String []val, int nl){
        
    }
}
