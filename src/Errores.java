
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
    
    public void error(String index){
        ec.add((String)msjerr.get("T")+(String)msjerr.get(index));
    }
    
    public String[] obtenerErrores(){
        return (String[])ec.toArray();
    }
}
