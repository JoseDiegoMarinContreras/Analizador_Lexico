



public class Automata {
   public String estadosFinales [];
   public String estadoInicial;
   public String estados[];
   public String alfabeto[];
   public String transiciones[][];
   
   public Automata(String finales[],String ini,String estados[],String alf[]){
       estadosFinales=finales;
       estadoInicial=ini;
       this.estados=estados;
       alfabeto=alf;
   }
   public boolean ini(String estado,String ini){
       boolean valor = true;
        if(!estado.equals(ini))valor = false;
       return valor;
   }
   
   public boolean fin(String estado,String finales[]){
       boolean valor = false;
       for(int i = 0;i<=finales.length-1;i++){
           if(estado.equals(finales[i]))valor=true;
       }
       return valor;
   }
}   
