
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IVAN
 */
public class AnalizadorLexico {
    Hashtable<String, Simbolo> tablaSimbolos;
    String Alfabeto=("[\\w]*[\\x09]*[\\x20]+[\\x3C]*[\\x3E]*[\\x3D]*[\\x2B]*[\\x2D]*[\\x2F]*[\\x2A]*[\\x3A]*[\\x2C]*[\\x28]*[\\x29]*[\\x5B]*[\\x5D]*[\\x7B]*[\\x7D]*[ñ]*[Ñ]*[á]*[Á]*[é]*[É]*[í]*[Í]*[ó]*[Ó]*[ú]*[Ú]*[\\x7C]*[\\x26]*[\\x25]*[\\x5F]*[\\x5E]*[\\x23]*[\\x3F]*[\\x23]*[\\x22]*[\\x2E]*[\\x20]+");
    String Alfabeto2=("[\\w]*[\\x09]*[\\x20]*[\\x3C]*[\\x3E]*[\\x3D]*[\\x2B]*[\\x2D]*[\\x2F]*[\\x2A]*[\\x3A]*[\\x2C]*[\\x28]*[\\x29]*[\\x5B]*[\\x5D]*[\\x7B]*[\\x7D]*[ñ]*[Ñ]*[á]*[Á]*[é]*[É]*[í]*[Í]*[ó]*[Ó]*[ú]*[Ú]*[\\x7C]*[\\x26]*[\\x25]*[\\x5F]*[\\x5E]*[\\x3F]*[\\xF9]*[\\x2E]*");
    String patron = ("(start\\b|end\\b|natural\\b|integer\\b|real\\b|function\\b|table\\b|text\\b|bit\\b|infinity\\b|pi\\b|euler\\b|if\\b|else\\b|during\\b|from\\b|to\\b|do\\b|terminal\\b|expression\\b|thread\\b|main\\b|convertion\\b|call\\b|in\\b|out\\b|graphic\\b)|"
            + "([:][:]|<=|>=|<|>|[=][?])|" //operador relacional
            + "([-][=]|[+][=]|[/][=]|[*][=]|[=])|" //operador de asignacion
            + "([a-zA-Z]+[a-zA-Z_0-9]*)|" //identificador
            + "([\\x22]["+Alfabeto2+"]*[\\x22][#]["+Alfabeto2+"]+[#][\\x22]["+Alfabeto2+"]+[\\x22]|[\\x22]["+Alfabeto2+"]*[\\x22][#]["+Alfabeto2+"]+)|" //Concatenacion
            + "([\\x22][\\x22])|" //Cadena Vacia
            + "([*][*]["+Alfabeto+"]+)|" //Comentario Simple
            + "([{][["+Alfabeto+"]+[\\x0A]*]+[}])|" //Comentario Largo
            + "([\\x22]["+Alfabeto2+"]+[\\x22])|" //Cadenas
            + "([#]|[\\x22]|[*][*]|[{]|[}])|" //signos especiales
            + "(AND|OR)|" //operador logico
            + "(\\x28|\\x29|\\x5B|\\x5D)|"//signos de agrupacion
            +"([\\x2B|\\x2D]{0,1}[\\d]+[.][\\d]+|\\d+|[\\x2B|\\x2D]{0,1}[\\x2E]{0,1}[\\d]+)|"//numero
            + "([-|/|^|+|*])|"//operador aritmetico
            + "([.|,|;])");//signos de puntuacion
    String[] tipoDato= {"natural","integer","real","text","bit"};//tipos de datos
    
    String[] tokens ={"<Palabra Reservada, %s>\n", "<Operador Relacional, %s>\n", "<Operador de Asignación, %s>\n",
                     "<Identificador, %s>\n", "<Concatenación, %s>\n", "<Cadena Vacia, %s>\n", "<Comentario Simple, %s>\n",
                     "<Comentario Largo, %s>\n", "<Cadena de Texto, %s>\n", "<Función Especial, %s>\n", "<Operador Lógico, %s>\n",
                     "<Signo de Agrupación, %s>\n", "<Número, %s>\n", "<Operador Aritmetico, %s>\n","<Signo de Puntuación, %s>\n"};
        
    private String [] re;
    
    public String [] compilar(String codigo){
        tablaSimbolos = new Hashtable<>();
        re = new String[2];
        re[0] = re[1] = "";
        String []texto = codigo.split("\n");
        String w;
        String stop = "\":=()[],+-*/<>^; ";
        
        for(int y = 0;y < texto.length; y++){
            w = "";
            for(int x = 0;x < texto[y].length(); x++){
                w += texto[y].substring(x,x+1);
                if(x < texto[y].length()-1){
                    if(stop.substring(8,14).contains(w) || stop.substring(1,3).contains(w)){
                        if(x < texto[y].length()-1){
                            if(texto[y].substring(x+1,x+2).equals("=")){// si es +=,-=,/=,*=,>=,<=
                                w += texto[y].substring(x+1,x+2);
                                x++;         
                                compLex(w, y+1);
                                w="";                       
                            }else if(w.equals(":") && texto[y].substring(x+1,x+2).equals(":")){//si es ::
                                w += texto[y].substring(x+1,x+2);
                                x++;         
                                compLex(w, y+1);
                                w="";                                
                            }else if(w.equals("=") && texto[y].substring(x+1,x+2).equals("?")){// si es =?
                                w += texto[y].substring(x+1,x+2);
                                x++;         
                                compLex(w, y+1);
                                w="";                                
                            }
                        }
                    }else if(stop.contains(texto[y].substring(x+1,x+2))){//separador
                        compLex(w, y+1);
                        w="";
                    }
                }
                if(stop.contains(w) && !w.equals("")){//si es cadena de texto o cadena vacia
                    if(w.equals("\"")){
                        for(x++;x<= texto[y].length();x++){
                            if(x == texto[y].length()){
                                re[1]+="Error en la linea #"+(y+1)+". "+"\" perdido.\n";
                                break;
                            }
                            w += texto[y].substring(x,x+1);
                            if(texto[y].substring(x,x+1).equals("\"")){
                                break;
                            }
                        }
                    }
                    compLex(w, y+1);
                    w="";
                }
            }
            compLex(w, y+1);
            w="";
        }
        return re;
    }
    
    Simbolo simbolo = new Simbolo();
    String nombre = "";
    boolean bandera = false; 
    
    private void compLex(String temp, int l){
        if (temp.isEmpty() || temp.equals(" ")) {
            return;
        }
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(temp);
        matcher.find();
        String r = matcher.group(0);
        int c=1;
        for (int i =1; i <= tokens.length;i++) {
            if(matcher.group(i) != null){
                if(r.length() < matcher.group(i).length()){
                    r = matcher.group(i);
                    c = i;
                }else if(temp.length() == r.length()){
                    re[0] += tokens[i-1];
                    re[0] = re[0].replace("%s", temp);
                    tabla(i, temp, l);
                    Enumeration elem = tablaSimbolos.elements();
                    Simbolo sim;
                    while (elem.hasMoreElements()){
                        sim = (Simbolo)elem.nextElement();
                        System.out.println("Nombre: "+tablaSimbolos.keySet()
                                +"\nTipo: "+sim.tipo
                                +"\nValor: "+sim.valor
                                +"\nFila: "+sim.fila);
                    }
                    return;
                }
            }
        }
        re[1] = "Error lexico en la linea "+l+". "+tokens[c-1].substring(1,tokens[c-1].indexOf(","))+" en "+temp+" "
        +temp.charAt(r.length())+" encontrado.\n";        
    }
       
    private void tabla(int i, String temp, int l){
        if (i == 4) {
            nombre = temp;
            simbolo.fila = l;
        }
        if (i == 1) {
            for (String palRes : tipoDato) {
                if (temp == palRes) {
                    simbolo.tipo= temp;
                }
            }
        }
        if (i == 3) {
            bandera = true;
        }
        if ((i == 6 || i == 9) && !bandera) {
            simbolo.valor = temp;
            bandera = false;
        }
        if (i == 13 && !bandera) {
            simbolo.valor = temp;
            bandera = false;
        }
        if (i == 15) {
            if (temp.equals(";")) {
                if (!tablaSimbolos.containsKey(nombre) && simbolo.tipo!=null) {                                
                    tablaSimbolos.put(nombre, new Simbolo(simbolo.tipo, simbolo.valor, simbolo.fila));
                    System.out.println();
                    simbolo = new Simbolo();

                }
            }else{
                if (simbolo.tipo==null) {
                    tablaSimbolos.get(nombre).valor= simbolo.valor;
                    simbolo =new Simbolo();
                }else{
                    //Error por identificador duplicado
                    //AreaErrores.setText("Error, en la linea "+simbolo.fila+" Identificador duplicado");
                }
            }
        }
    }
}
