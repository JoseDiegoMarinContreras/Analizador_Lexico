
import java.util.ArrayList;
import java.util.Hashtable;




public class Errores {
    private Hashtable msjerr;
    private ArrayList<String> ec;
    
    public Errores(){
        msjerr = new Hashtable();
        msjerr.put("T", "Error en la linea #%s. ");
        msjerr.put("ELCI","Caracter invalido '%s'.");
        msjerr.put("ELIMH","Identificador mal hecho %s.");
        
        ec = new ArrayList<String>();
    }
    
    public void errorL(String index, String nl, String p){
        String e = ((String)msjerr.get(index)).replaceAll("%s", p);
        String el = ((String)msjerr.get("T")).replaceAll("%s", nl);
        ec.add(el+e);
    }
    
    public String[] obtenerErrores(){
        return (String[])ec.toArray();
    }
}
