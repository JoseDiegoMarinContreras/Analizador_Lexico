
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class ManejadorArchivos {
    
    public void guardar(){
    
    }
    
    public String abrir(String path) throws IOException{
        String temp = "";
        FileReader f = new FileReader(path);
        BufferedReader bf = new BufferedReader(f);
        String cad = "";
        while((cad=bf.readLine()) != null){
            temp += cad+"\n";
        }
        bf.close();
        return temp;
    }    
    
    public void guardar(String path, String contenido) throws IOException{
        String ar[] = contenido.split("\n");
        File mf = new File(path);
        mf.createNewFile();
        FileWriter f = new FileWriter(mf);
        BufferedWriter bf = new BufferedWriter(f);
        for(int i = 0; i < ar.length; i++){
            bf.write(ar[i]);
            bf.newLine();
        }
        bf.flush();
        bf.close();
        f.close();
    }
}
