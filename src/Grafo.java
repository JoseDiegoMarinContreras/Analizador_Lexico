


public class Grafo {
    public Vertice ini,fin;
    public Vertice t;
    
    public Grafo(){
        ini = fin = null;
    }
    
    public boolean insertar(String valor, boolean fst, boolean end){
        Vertice temp = new Vertice(valor, fst, end);
        if(temp == null){return false;}
        if(ini == null && fin == null){
            ini = fin = temp;
            return true;
        }
        fin.sig = temp;
        temp.ant = fin;
        fin = temp;
        return true;
    }
    
    public Vertice buscarVertice(String valor){
        if(ini == null && fin == null){return null;}
        for(Vertice temp = ini; temp != null; temp = temp.sig){
            if(temp.valor.equals(valor)){
                return temp;
            }
        }
        return null;
    }

    
    public boolean crearArista(String origen, String destino, String val){
        if(ini == null && fin == null){return false;}
        Vertice NodoOrigen = buscarVertice(origen);
        Vertice NodoDestino = buscarVertice(destino);
        if(NodoOrigen == null || NodoDestino == null){return false;}
        return NodoOrigen.enlazar(NodoDestino, val);
    }
        
    
    public boolean validarElem(String elemento){
        return recorrer(elemento,0, ini);        
    }
    
    public boolean mover(String elemento){
        Arista ar;
        
        if(t.arista == null){
            return false;
        }
        
        for(ar = t.arista;ar != null;ar = ar.sig){
            if(ar.val.equals("")){
                if(mover(elemento, ar.punteroaVertice)){
                    return true;
                }
            }else if(ar.val.equals(elemento)){
                if(recorrer(elemento, i+1, ar.punteroaVertice)){
                    return true;
                }
            }
        }
        return false;      
    }
    
    public boolean mover(String elemento, Vertice temp){
        Arista ar;
        
        if(temp.arista == null){
            return false;
        }
        
        for(ar = temp.arista;ar != null;ar = ar.sig){
            if(ar.val.equals("")){
                if(mover(elemento, ar.punteroaVertice)){
                    return true;
                }
            }else if(ar.val.equals(elemento)){
                temp = ar.punteroaVertice;
                return true;
            }
        }
        return false;        
    }
    
    private boolean recorrer(String elemento, int i, Vertice temp){
        Arista ar;
        
        if(i == elemento.length()){
            for(ar = temp.arista;ar != null;ar = ar.sig){
                if(ar.val.equals("")){
                    if(recorrer(elemento,i,ar.punteroaVertice)){
                        return true;
                    }
                }
            }
            return temp.efin;
        }
        
        for(ar = temp.arista;ar != null;ar = ar.sig){
            if(ar.val.equals("")){
                if(recorrer(elemento,i,ar.punteroaVertice)){
                    return true;
                }
            }else if(ar.val.equals(elemento.substring(i,i+1))){
                if(recorrer(elemento, i+1, ar.punteroaVertice)){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
 
}
