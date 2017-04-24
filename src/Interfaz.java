
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showInputDialog;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abv17
 */
public class Interfaz extends javax.swing.JFrame {
    Hashtable<String, Simbolo> tablaSimbolos;
    String[] palRes={"start","end","natural","integer","real","function","table","text","bit","infinity","main",
                     "pi","euler","if","else","during","from","to","do","terminal","expression","thread","convertion"};
    int fila;
    int col;
    
    private JFileChooser fc;
    private ManejadorArchivos ma;
    
    public Interfaz() {
        initComponents();
        fc = new JFileChooser(new File(".").getAbsolutePath());
        fc.setFileFilter(new FileNameExtensionFilter("*.DFN","dfn"));
        
        ma = new ManejadorArchivos();
        
        abrir.setToolTipText("Abrir");
        guardar.setToolTipText("Guardar");
        compilar.setToolTipText("Compilar");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        areaCodigo.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height/2));
        areaCodigo.addCaretListener(new CaretListener(){
            @Override
            public void caretUpdate(CaretEvent ce) {
                JTextArea codigo = (JTextArea)ce.getSource();
                try{
                    int pos =codigo.getCaretPosition();
                    fila=codigo.getLineOfOffset(pos);
                    col = pos-codigo.getLineStartOffset(fila)+1;
                    fila++;
                }catch(Exception e){}
                update(fila, col);
            }
        });
        panelPrincipal.setBackground(Color.LIGHT_GRAY);
        areaCodigo.requestFocus();
    }
    private void update(int fil, int co){
        numFilas.setText(fil+"");
        numCol.setText(co+"");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        panelPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaCodigo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaComponentesL = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        numFilas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        numCol = new javax.swing.JLabel();
        abrir = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        compilar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miBuscar = new javax.swing.JMenuItem();
        miBR = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        areaCodigo.setColumns(20);
        areaCodigo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        areaCodigo.setRows(5);
        areaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                areaCodigoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(areaCodigo);

        AreaComponentesL.setEditable(false);
        AreaComponentesL.setColumns(20);
        AreaComponentesL.setRows(5);
        jScrollPane2.setViewportView(AreaComponentesL);

        jLabel2.setText("Fila:");

        jLabel1.setText("Col:");

        abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/folderopened-amarillo-icono-6397-32.png"))); // NOI18N
        abrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirMouseClicked(evt);
            }
        });

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/disquetes-excepto-icono-3849-32.png"))); // NOI18N
        guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarMouseClicked(evt);
            }
        });

        compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clicknrun-icono-4776-32.png"))); // NOI18N
        compilar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compilarMouseClicked(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/font-icono-8596-32.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrincipalLayout.createSequentialGroup()
                        .addGap(112, 447, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(numCol, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addComponent(jScrollPane2)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPrincipalLayout.createSequentialGroup()
                                .addComponent(abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compilar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(abrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(compilar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(numCol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Nuevo");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Abrir");
        jMenu1.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Guardar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem5.setText("Guardar como...");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        miBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        miBuscar.setText("Buscar...");
        miBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBuscarActionPerformed(evt);
            }
        });
        jMenu2.add(miBuscar);

        miBR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        miBR.setText("Buscar/Remplazar");
        miBR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miBRActionPerformed(evt);
            }
        });
        jMenu2.add(miBR);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    String patron = ("(start\\b|end\\b|natural\\b|integer\\b|real\\b|function\\b|table\\b|text\\b|bit\\b|infinity\\b|pi\\b|euler\\b|if\\b|else\\b|during\\b|from\\b|to\\b|do\\b|terminal\\b|expression\\b|thread\\b|main\\b|convertion\\b)|"
            + "([:][:]|<=|>=|<|>|[=][?])|"
            + "([-][=]|[+][=]|[/][=]|[*][=]|[=])|"
            + "([a-zA-Z]+[a-zA-Z_0-9]*|[\\x22][[\\s]*[\\w]+[\\s]*]+[\\x22])|"
            + "([#]|[\\x22]|[*][*]|[{]|[}])|"
            + "(AND|OR)|"
            + "(\\x28|\\x29|\\x5B|\\x5D)|"
            +"([\\x2B|\\x2D]{0,1}[\\d]+[.][\\d]+|\\d+|[\\x2B|\\x2D]{0,1}[\\x2E]{0,1}[\\d]+)|"
            + "([-|/|^|+|*])|"
            + "([.|,|;])");
    String[] tipoDato= {"natural","integer","real","text","bit"};
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        Dimension dim = this.getContentPane().getSize();
        panelPrincipal.setPreferredSize(dim);
    }//GEN-LAST:event_formComponentResized

    private void areaCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaCodigoKeyTyped
        // TODO add your handling code here:
//        cad+=evt.getKeyChar();
//        for (int i = 0; i < palRes.length; i++) {
//            if (cad.equals(palRes[i]+" ")) {
//                areaCodigo.setForeground(Color.red);
//                //System.out.println(cad);
//                cad="";
//            }
//        }
    }//GEN-LAST:event_areaCodigoKeyTyped

    private void abrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrirMouseClicked
        int r = fc.showOpenDialog(this);
        if(r == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getPath(); 
            setTitle("Archivo: "+path);
            try{
            areaCodigo.setText(ma.abrir(path));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_abrirMouseClicked

    private void guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseClicked
        guardar();
    }//GEN-LAST:event_guardarMouseClicked

    private void compilarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compilarMouseClicked
        compilar();
    }//GEN-LAST:event_compilarMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        guardar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        guardar();
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void miBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBuscarActionPerformed
        VentanaBuscar vb = new VentanaBuscar(this, true,areaCodigo);
        vb.setVisible(true);
    }//GEN-LAST:event_miBuscarActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        /*JColorChooser c = new JColorChooser();
        c.setVisible(true);*/
    }//GEN-LAST:event_jButton1MouseClicked

    private void miBRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miBRActionPerformed
        String cb = showInputDialog(this,"Introduzca la palabra a buscar.");
        if(cb == null){return;}
        String nc = showInputDialog(this,"Introduzca la palabra nueva.");
        if(nc == null){return;}
        areaCodigo.setText(areaCodigo.getText().replaceAll(cb,nc));
    }//GEN-LAST:event_miBRActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public void compilar(){
        tablaSimbolos = new Hashtable<>();
        String texto = areaCodigo.getText();
        Simbolo simbolo = new Simbolo();
        String nombre = "";
        int linea=0;
        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(texto);
        boolean bandera= false;
        String cad = "";

        while (matcher.find()){
            String tokenTipo1 = matcher.group(1);
            if(tokenTipo1 != null){
                cad+="Palabra Reservada: "+tokenTipo1+"\n";
                for(String tipo:tipoDato){
                    if (tokenTipo1.equals(tipo)) {
                        simbolo.tipo=tokenTipo1;
                    }
                }
            }

            String tokenTipo2 = matcher.group(2);
            if(tokenTipo2 != null){
                cad+="Operador Relacional: "+tokenTipo2+"\n";
            }

           String tokenTipo3 = matcher.group(3);
            if(tokenTipo3 != null){
                cad+="Operador de Asignación: "+tokenTipo3+"\n";
                if (!bandera) {
                    bandera=true;
                }
            }
            
            String tokenTipo4 = matcher.group(4);
            if(tokenTipo4 != null){
                cad+="Identificador: "+tokenTipo4+"\n";
                if (!bandera) {
                    nombre= tokenTipo4;
                    linea= fila;
                }else{
                    simbolo.valor= tokenTipo4;
                    bandera=false;
                }
            }
            
            String tokenTipo5 = matcher.group(5);
            if(tokenTipo5 != null){
                cad+="Función Especial: "+tokenTipo5+"\n";
            }
            
            String tokenTipo6 = matcher.group(6);
            if(tokenTipo6 != null){
                cad+="Operador Lógico: "+tokenTipo6+"\n";
            }

            String tokenTipo7 = matcher.group(7);
            if(tokenTipo7 != null){
                cad+="Signo de Agrupación: "+tokenTipo7+"\n";
            }
            
            String tokenTipo8 = matcher.group(8);
            if(tokenTipo8 != null){
                cad+="Número: "+tokenTipo8+"\n";
                if (bandera) {
                    simbolo.valor=tokenTipo8;
                    bandera=false;
                }
            }
            
            String tokenTipo9 = matcher.group(9);
            if(tokenTipo9 != null){
                cad+="Operador Aritmetico: "+tokenTipo9+"\n";
            }
            
            String tokenTipo10 = matcher.group(10);
            if(tokenTipo10 != null){
                cad+="Signo de Puntuación: "+tokenTipo10+"\n";
                if (tokenTipo10.equals(";")) {
                    if (!tablaSimbolos.containsKey(nombre)) {
                        tablaSimbolos.put(nombre, new Simbolo(simbolo.tipo, simbolo.valor, simbolo.fila));            
                        System.out.println("Nombre: "+nombre
                                   +"\nTipo: "+simbolo.tipo
                                   +"\nValor: "+simbolo.valor
                                   +"\nFila: "+linea);
                    }else{
                        javax.swing.JOptionPane.showMessageDialog(this,"El id "+nombre+" ya existe");
                    }
                }
            }
            


        }
        AreaComponentesL.setText(cad);
    }
    
    
    public void guardar(){
        int r = fc.showOpenDialog(this);
        if(r == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getPath()+".dfn"; 
            setTitle("Archivo: "+path);
            try{
                ma.guardar(path, areaCodigo.getText());
            }catch(IOException e){
                e.printStackTrace();
            }
        }  
    }
    
    String cad = "";
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaComponentesL;
    private javax.swing.JButton abrir;
    private javax.swing.JTextArea areaCodigo;
    private javax.swing.JButton compilar;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem miBR;
    private javax.swing.JMenuItem miBuscar;
    private javax.swing.JLabel numCol;
    private javax.swing.JLabel numFilas;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

}
