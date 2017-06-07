public class AnalizadorSintactico {
    String err;
    String[] tipoDato= {"natural","integer","real","text","bit","function"};//tipos de datos
    String[] opRel={"::","<=",">=","=?","<",">"};
    String[] opLog={"AND","OR"};
    String[] opArit={"+","-","/","*"};    
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
                err += "Error sintáctico en la línea "+p[i]+". START sin END.\n";
           }
       }
       return err;
    }
    
    public void aSintactico(String []l, int nl){
        String t = "";
        condicion(l, nl, 0);
        switch(l[0]){
            case "start":{
               startp = nl+","+startp;
               return;
            }
            case "end":{
               if(startp.equals("")){
                   err += "Error sintáctico en la línea "+nl+". END sin START.\n";
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
            case "ID":{
              t = "asignacion de variable";
              aSid();
               break;
            }
            case "":{
                return;
            }
            default:{
                err += "Error sintáctico en la línea "+nl+". Sentencia mal hecha.\n";
                return;
            }
        }
        g.t = g.ini;
        for(int i = 0; i < l.length; i++){
            if(!g.mover(l[i])){
                err += "Error sintáctico en la línea "+nl+". "+l[i]+" mal puesto.\n";
                return;
            }
            if(g.t.arista != null){
                String c="";
                if(g.t.arista.val.equals("cond")){
                    Vertice v = g.t.arista.punteroaVertice;
                    for(int a = i+1; a < l.length; a++){
                        if(v.arista.val.equals(l[a])){
                            i = a-1;
                            g.mover("cond");
                            break;
                        }
                        c += l[a];
                    }
                   /*if(!metodoAgustin(c)){
                    err += "Error sintactico en la linea "+nl+". Condicion mal hecha.\n";    
                    }*/
                }else if(g.t.arista.val.equals("expr")){
                    Vertice v = g.t.arista.sig.punteroaVertice;
                    for(int a = i+1; a < l.length; a++){
                        if(v.arista.val.equals(l[a])){
                            i = a-1;
                            g.mover("expr");
                            break;
                        }
                        c += l[a];
                    }
                   /*if(!metodoAgustin(c)){
                    err += "Error sintactico en la linea "+nl+". Expresion aritmetica mal hecha.\n";    
                    }*/
                }
                if(!(i < l.length)){
                    return;
                }
            }
        }
        if(!g.t.efin){
            err += "Error sintáctico en la línea "+nl+". Sentencia incompleta para "+t+".\n";
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
        g.crearArista("q2", "q4", "if");
        g.crearArista("q4", "q5", "cond");
        g.crearArista("q5", "q6", ";");
    }
    
    public void aSid(){
        g = new Grafo();
        g.insertar("q1", true, false);
        g.insertar("q2", false, false);
        g.insertar("q3", false, true);
        g.insertar("q4", false, false);
        g.insertar("q5", false, true);
        
        g.crearArista("q1", "q2", "ID");
        g.crearArista("q2", "q3", "=");
        g.crearArista("q3", "q4", "NUMERO");
        g.crearArista("q3", "q4", "ID");
        g.crearArista("q3", "q4", "CADENA");
        g.crearArista("q4", "q5", ";");
        g.crearArista("q2", "q5", ";");
    }   
    private void condicion(String []l, int nl, int num){
        boolean ban=false;
        for (int i = num; i < l.length; i++) {            
            if (l[i].equals("else")) {//condición que pone la bandera en true para poderse aplicar a un else if
                i++;
            }
            if (l[i].equals("if") || l[i].equals("during")||(l[i].equals("if")&&ban)) {// condición que nos permite evaluar las condiciones
                if (ban) {// verificar si la bandera esta activada para las condiciones else if
                    ban=false;// apagar la bandera
                }
                for (int j = i+1; j < l.length; j++) {// for para recorrer el resto de la condición
                    if (l[j].equals(";")) {//condición para verificar si se ha llegado al fin de linea
                        System.out.println("90 Fin de línea");
                        return;
                    }
                    //javax.swing.JOptionPane.showMessageDialog(null, "93 "+l[j]+" linea "+(nl));
                    if (l[j].equals("(")) {
                        j++;
                        //javax.swing.JOptionPane.showMessageDialog(null, "96 "+l[j]+" linea "+(nl));
                        if (l[j].equals("(")) {
                            int r= opAritmetica(l, nl, j);
                            if (r==0) {
                                System.out.println("r = "+r);
                                return;
                            }
                            j = r;
                            int or=operacionR(l, nl, j);
                            if (or==0) {
                                System.out.println("or = "+or);
                                return;
                            }
                            j= or;
                        }else
                        if (l[j].equals("ID")||l[j].equals("NUMERO")) { 
                            int opr = operacionR(l,nl,j);
                            if (opr==0) {
                                System.out.println("opr = "+opr);
                                return;
                            }
                            j= opr;
                        }else{
                            err+="Error sintáctico en la línea "+(nl)+", condición mal formada en "+l[j]+"\n";
                            return;
                        }                        
                    }else{
                        err+="Error sintáctico en la línea "+(nl)+", condición mal formada en "+l[j]+"\n";
                        return;
                    }
                }
            }else{
                return;
            }
        }
    }
    private int opAritmetica(String[] l, int nl, int j){
        //j++;
        //javax.swing.JOptionPane.showMessageDialog(null, "134 "+l[j]+" linea "+(nl));
        int re=expArit(l, nl, j);
        if (re==0) {
            System.out.println("re = "+re);
            return 0;
        }
        j = re;
        for (String opAri:opArit) {
            if (l[j].equals(opAri)) {
                j++;
                //javax.swing.JOptionPane.showMessageDialog(null, "141 "+l[j]+" linea "+(nl));
                if(l[j].equals("(")){
                    System.out.println("Recursiva");
                    int v=opAritmetica(l,nl, j+1);
                    if (v==0){
                        System.out.println("v = "+v);
                        return 0;
                    }
                    j=v;
                //javax.swing.JOptionPane.showMessageDialog(null, "144 "+l[j]+" linea "+(nl));
                }else if (l[j].equals(")")) {
                        return j;
                }else{
                    err+="Error sintáctico en la línea "+(nl)+", operación arimética mal creada en "+l[j]+"\n";
                }
            }
        }
        if (l[j].equals(")")) {
            return j;
        }else{
            err+="Error sintáctico en la línea "+(nl)+", operación arimética mal creada en "+l[j]+"\n";
            return 0;
        }
    }
    
    private int expArit(String[] l, int nl, int j){
        j++;
        //javax.swing.JOptionPane.showMessageDialog(null, "164 "+l[j]+" linea "+(nl));
        if (l[j].equals("NUMERO")||l[j].equals("ID")) {
            j++;
            //javax.swing.JOptionPane.showMessageDialog(null, "173 "+l[j]+" linea "+(nl));
        }
        for(String operador:opArit){
            if (l[j].equals(operador)) {
                int o=expArit(l, nl, j);
                if(o==0){
                    System.out.println("o= "+o);
                    return 0;
                }
                j = o;
            }
        }
        if (l[j].equals("(")) {
            j++;
            //javax.swing.JOptionPane.showMessageDialog(null, "570 "+l[j]+" linea "+(nl));
            int n = expArit(l, nl, j);
            if (n==0) {
                System.out.println("n = "+n);
                return 0;
            }
            j=n;
        }
        if (l[j].equals(";")||l[j].equals(")")) {
            System.out.println("fin  de condición");
            return j;
        }else{
            return 0;
        }
    }
    
    private int operacionR(String[] l, int nl, int j){
        j++;
        //javax.swing.JOptionPane.showMessageDialog(null, "162 "+l[j]+" linea "+(nl));
        for (String op:opRel) {                            
            if (l[j].equals(op)) {
                j++;
                //javax.swing.JOptionPane.showMessageDialog(null, "166 "+l[j]+" linea "+(nl));
                if (l[j].equals("ID")||l[j].equals("NUMERO")) {
                    j++;
                    //javax.swing.JOptionPane.showMessageDialog(null, "169 "+l[j]+" linea "+(nl));
                    if (l[j].equals(")")) {
                        System.out.println("Condición bien formada en la linea "+(nl));
                        return j;
                    }else{
                        err+="Error sintáctico en la línea "+(nl)+", condición mal formada en "+l[j]+"\n";
                        return 0;
                    }
                }else if(l[j].equals("(")){
                    int nu=opAritmetica(l, nl, j);
                    if (nu==0) {
                        System.out.println("nu = "+nu);
                        return 0;
                    }
                    j=nu+1;
                    //javax.swing.JOptionPane.showMessageDialog(null, "615 "+l[j]+" linea "+(nl));
                }else{
                    err+="Error sintáctico en la linea "+(nl)+", condición mal formada en "+l[j]+"\n";
                    return 0;
                }
            }
        }
        return 0;
    }
}