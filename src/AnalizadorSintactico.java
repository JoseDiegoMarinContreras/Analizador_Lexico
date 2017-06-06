public class AnalizadorSintactico {
    String err;
    Grafo g;
    String startp, ifp;
    
    public AnalizadorSintactico(){
        err = startp = ifp = "";
    }
    
    public String compilar(String codigo){
       String lc[] = codigo.split("\n");
       
       for(int i = 0; i < lc.length; i++){
           if(!lc[i].equals("")){
                lc[i] = lc[i].substring(0, lc[i].length()-1);
                lc[i] = lc[i].replaceFirst("@-@=@", "ASIGNACION@");
                lc[i] = lc[i].replaceFirst("@+@=@", "@ASIGNACION@");
                lc[i] = lc[i].replaceFirst("@/@=@", "@ASIGNACION@");
                //lc[i] = lc[i].replaceFirst("@*@=@", "@ASIGNACION@");
                lc[i] = lc[i].replaceFirst("@-@-@", "@--@");
                lc[i] = lc[i].replaceFirst("@+@+@", "@++@");
                aSintactico(lc[i].split("@"), i+1);
           }
       }
       String []p = startp.split(",");
       if(p.length > 2){
           for(int i = 0; i < p.length-1; i++){
                err += "Error sintactico en la linea "+p[i]+". START sin END.\n";
           }
       }
       return err;
    }
    
    public void aSintactico(String []l, int nl){
        String t = "";
        switch(l[0]){
            case "start":{
               startp = nl+","+startp;
               return;
            }
            case "end":{
               if(startp.equals("")){
                   err += "Error sintactico en la linea "+nl+". END sin START.\n";
               }
               startp = startp.replaceFirst(startp.substring(0, startp.indexOf(",")+1),"");
               return;
            }
            case "expression":{
                t = "declarar metodo";
                aSexpression();
               break;
            }
            case "from":{
               t = "from_to_do";
               aSftd();
               break;
            }
            case "convertion":{
               t = "convertion";
               aSconv();
               break;
            }
            case "in":{
               t = "in";
                aSin();
               break;
            }
            case "out":{
               t = "out";
                aSout();
               break;
            }
            case "graphic":{
               t = "graphic";
               aSgr();
               break;
            }
            case "call":{
               t = "call";
               aScall();
               break;
            }
            case "during":{
               t = "during";
               aSduring();
               break;
            }
            case "Dx":{
               t = "derivada (Dx)";
              aSDx();
               break;
            }
            
            case "terminal":{
              t = "declarar variables";
              aSDV();
               break;
            }
            case "TIPO_DATO":{
              t = "declarar variables";
              aSDV();
               break;
            }
            case "table":{
              t = "declarar variables";
              aSDV();
               break;
            }
            
            case "if":{
              t = "if";
              aSif();
               break;
            }
            case "else":{
              t = "else";
              aSelse();
               break;
            }
            case "":{
                return;
            }
            default:{
                err += "Error sintactico en la linea "+nl+". Sentencia mal hecha.\n";
                return;
            }
        }
        g.t = g.ini;
        for(int i = 0; i < l.length; i++){
            if(!g.mover(l[i])){
                err += "Error sintactico en la linea "+nl+". "+l[i]+" mal puesto.\n";
                return;
            }
            if(g.t.arista != null){
                String c="";
                if(g.t.arista.val.equals("cond")){
                    Vertice v = g.t.arista.punteroaVertice;
                    for(int a = i+1; a < l.length; a++){
                        if(v.arista.val.equals(l[a])){
                            break;
                        }
                        c += l[a];
                    }
                   /*if(!metodoAgustin(c)){
                    err += "Error sintactico en la linea "+nl+". Condicion mal hecha.\n";    
                    }*/
                }
                if(g.t.arista.val.equals("expr")){
                    Vertice v = g.t.arista.sig.punteroaVertice;
                    for(int a = i+1; a < l.length; a++){
                        if(v.arista.val.equals(l[a])){
                            break;
                        }
                        c += l[a];
                    }
                   /*if(!metodoAgustin(c)){
                    err += "Error sintactico en la linea "+nl+". Expresion aritmetica mal hecha.\n";    
                    }*/
                }
            }
        }
        if(!g.t.efin){
            err += "Error sintactico en la linea "+nl+". Sentencia incompleta para "+t+".\n";
        }
    }
    
    public void aSexpression(){
        g = new Grafo();
        g.insertar("q0", true, false);
        g.insertar("q1", false, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, false);
        g.insertar("q7", false, false);
        g.insertar("q8", false, false);
        g.insertar("q9", false, true);
        g.insertar("q10", false, true);
        g.insertar("q11", false, false);
        g.insertar("q12", false, false);
        
        g.crearArista("q0", "q1", "expression");
        g.crearArista("q1", "q2", "TIPO_DATO");
        g.crearArista("q1", "q2", "");
        g.crearArista("q2", "q3", "ID");
        g.crearArista("q3", "q4", "(");
        g.crearArista("q4", "q9", ")");
        g.crearArista("q4", "q5", "TIPO_DATO");
        g.crearArista("q5", "q6", "ID");
        g.crearArista("q6", "q12", ",");
        g.crearArista("q12", "q7", "TIPO_DATO");
        g.crearArista("q7", "q8", "ID");
        g.crearArista("q8", "q6", "");
        g.crearArista("q6", "q11", ")");
        g.crearArista("q11", "q10", ";");
    }   
    
    public void aSftd(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, false);
        g.insertar("q7", false, false);
        g.insertar("q8", false, false);
        g.insertar("q9", false, true);
        g.insertar("q10", false, true);
        g.insertar("q11", false, false);
        g.insertar("q12", false, false);
        g.insertar("q13", false, false);
        g.insertar("q14", false, false);
        g.insertar("q15", false, true);
        
        g.crearArista("q1", "q2", "from");
        g.crearArista("q2", "q3", "TIPO_DATO");
        g.crearArista("q2", "q3", "");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q4", "q5", "=");
        g.crearArista("q5", "q6", "NUMERO");
        g.crearArista("q5", "q6", "ID");
        g.crearArista("q6", "q7", "to");
        g.crearArista("q2", "q7", "to");
        g.crearArista("q8", "q9", "do");
        g.crearArista("q9", "q10", "(");
        g.crearArista("q10", "q11", "ID");
        g.crearArista("q11", "q12", "ASIGNACION");
        g.crearArista("q12", "q13","ID");
        g.crearArista("q12", "q13","NUMERO");
        g.crearArista("q11", "q13","--");
        g.crearArista("q11", "q13","++");
        g.crearArista("q13", "q14",")");
        g.crearArista("q14", "q15",";");
    }   
    
    public void aSconv(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, false);
        g.insertar("q7", false, false);
        g.insertar("q8", false, true);
        
        g.crearArista("q1", "q2", "convertion");
        g.crearArista("q2", "q3", "(");
        g.crearArista("q3", "q4", "CADENA");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q3", "q4", "NUMERO");
        g.crearArista("q4", "q5", ",");
        g.crearArista("q5", "q6", "TIPO_DATO");
        g.crearArista("q6", "q7", ")");
        g.crearArista("q7", "q8", ";");
    }
    
    public void aSin(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, true);
        
        g.crearArista("q1", "q2", "in");
        g.crearArista("q2", "q3", "(");
        g.crearArista("q3", "q4", "CADENA");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q4", "q5", ")");
        g.crearArista("q5", "q6", ";");
    }
    
    public void aSout(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, true);
        
        g.crearArista("q1", "q2", "out");
        g.crearArista("q2", "q3", "(");
        g.crearArista("q3", "q4", "CADENA");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q4", "q5", ")");
        g.crearArista("q5", "q6", ";");
    }
    
    public void aSgr(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, false);
        g.insertar("q7", false, false);
        g.insertar("q8", false, false);
        g.insertar("q9", false, false);
        g.insertar("q10", false, true);
        g.insertar("q11", false, false);
        g.insertar("q12", false, true);
        
        g.crearArista("q1", "q2", "graphic");
        g.crearArista("q2", "q3", "(");
        g.crearArista("q3", "q4", "CADENA");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q4", "q11", ")");
        g.crearArista("q11", "q12", ";");
        g.crearArista("q4", "q5", "ID");
        g.crearArista("q4", "q5", "NUMERO");
        g.crearArista("q6", "q7", ",");
        g.crearArista("q7", "q8", "ID");
        g.crearArista("q7", "q8", "NUMERO");
        g.crearArista("q8", "q9", ")");
        g.crearArista("q9", "q10", ";");
    }
    
    public void aScall(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, true);
        g.insertar("q7", false, false);
        g.insertar("q8", false, false);
        g.insertar("q9", false, true);
        g.insertar("q10", false, false);
        g.insertar("q11", false, true);
        
        g.crearArista("q1", "q2", "call");
        g.crearArista("q2", "q3", "ID");
        g.crearArista("q3", "q4", "(");
        g.crearArista("q4", "q5", ")");
        g.crearArista("q5", "q6", ";");
        g.crearArista("q4", "q7", "ID");
        g.crearArista("q4", "q7", "NUMERO");
        g.crearArista("q4", "q7", "CADENA");
        g.crearArista("q7", "q8", ")");
        g.crearArista("q8", "q9", ";");
        g.crearArista("q7", "q10", ",");
        g.crearArista("q10", "q11", "ID");
        g.crearArista("q10", "q11", "ID");
        g.crearArista("q10", "q11", "NUMERO");
        g.crearArista("q10", "q11", "CADENA");
        g.crearArista("q11", "q7", "");
    }
    
    public void aSduring(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, true);
        
        g.crearArista("q1", "q2", "during");
        g.crearArista("q2", "q3", "(");
        g.crearArista("q3", "q4", "cond");
        g.crearArista("q4", "q5", ")");
        g.crearArista("q5", "q6", ";");
    }
    
    public void aSDx(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, true);
        
        g.crearArista("q1", "q2", "Dx");
        g.crearArista("q2", "q3", "[");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q4", "q5", "]");
        g.crearArista("q5", "q6", ";");
    }
    
    public void aSDV(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);
        g.insertar("q6", false, false);
        g.insertar("q7", false, false);
        g.insertar("q8", false, true);
        g.insertar("q9", false, false);
        g.insertar("q10", false, false);
        g.insertar("q11", false, true);
        g.insertar("q12", false, false);
        g.insertar("q13", false, false);
        g.insertar("q14", false, false);
        g.insertar("q15", false, false);
        g.insertar("q16", false, false);
        g.insertar("q17", false, false);
        g.insertar("q18", false, false);
        g.insertar("q19", false, true);
        g.insertar("q20", false, false);
        
        g.crearArista("q1", "q2", "terminal");
        g.crearArista("q1", "q2", "");
        g.crearArista("q2", "q3", "TIPO_DATO");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q4", "q5", ";");
        g.crearArista("q4", "q6", "=");
        g.crearArista("q6", "q7", "CADENA");
        g.crearArista("q6", "q7", "ID");
        g.crearArista("q6", "q7", "NUMERO");
        g.crearArista("q7", "q8", ";");
        g.crearArista("q2", "q9", "table");
        g.crearArista("q9", "q10", "ID");
        g.crearArista("q10", "q11", ";");
        g.crearArista("q10", "q20", "=");
        g.crearArista("q20", "q12", "[");
        g.crearArista("q12", "q13", "ID");
        g.crearArista("q12", "q13", "CADENA");
        g.crearArista("q13", "q14", ",");
        g.crearArista("q14", "q15", "ID");
        g.crearArista("q14", "q15", "CADENA");
        g.crearArista("q15", "q16", ",");
        g.crearArista("q16", "q17", "ID");
        g.crearArista("q16", "q17", "NUMERO");
        g.crearArista("q17", "q18", "]");
        g.crearArista("q18", "q19", ";");
    }
    
    public void aSif(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, false);
        g.insertar("q4", false, true);
        
        g.crearArista("q1", "q2", "if");
        g.crearArista("q2", "q3", "cond");
        g.crearArista("q3", "q4", ";");
    }
    
    public void aSelse(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, true);
        g.insertar("q4", false, false);
        g.insertar("q5", false, false);
        g.insertar("q6", false, true);
        
        g.crearArista("q1", "q2", "else");
        g.crearArista("q2", "q3", ";");
        g.crearArista("q2", "q4", "else");
        g.crearArista("q4", "q5", "cond");
        g.crearArista("q5", "q6", ";");
    }   
}