
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    String[] palRes={"start","end","natural","integer","real","function","table","text","bit","infinity","main",
                     "pi","euler","if","else","during","from","to","do","terminal","expression","thread","convertion"};
    int fila;
    int col;
    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaComponentesL = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        numFilas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        numCol = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        areaCodigo.setColumns(20);
        areaCodigo.setRows(5);
        areaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                areaCodigoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(areaCodigo);

        jButton1.setText("Analizar Componentes Léxicos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        AreaComponentesL.setEditable(false);
        AreaComponentesL.setColumns(20);
        AreaComponentesL.setRows(5);
        jScrollPane2.setViewportView(AreaComponentesL);

        jLabel2.setText("Fila:");

        jLabel1.setText("Col:");

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(numCol, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(numCol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
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
    String patron = ("(start|end|natural|integer|real|function|table|text|bit|infinity|pi|euler|if|else|during|from|to|do|terminal|expression|thread|main|convertion)|"
            + "([-|/|^|+|*])|"
            + "(=)|"
            + "([<|>|::|<=|>=]+)|"
            + "(AND|OR)|"
            + "([(|)])|"
            + "([.|,|;])|"
            + "([#])|"
            +"([0-9]+)|"
            + "([a-zA-Z_0-9]+)");
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        Dimension dim = this.getContentPane().getSize();
        panelPrincipal.setPreferredSize(dim);
    }//GEN-LAST:event_formComponentResized

    private void areaCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaCodigoKeyTyped
        // TODO add your handling code here:
        cad+=evt.getKeyChar();
        for (int i = 0; i < palRes.length; i++) {
            if (cad.equals(palRes[i]+" ")) {
                areaCodigo.setForeground(Color.red);
                System.out.println(cad);
                cad="";
            }
        }
    }//GEN-LAST:event_areaCodigoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String texto = areaCodigo.getText();

        Pattern p = Pattern.compile(patron);
        Matcher matcher = p.matcher(texto);

        String cad = "";

        while (matcher.find()){
            String tokenTipo1 = matcher.group(1);
            if(tokenTipo1 != null){
                cad+="Palabra Reservada: "+tokenTipo1+"\n";
            }

            String tokenTipo2 = matcher.group(2);
            if(tokenTipo2 != null){
                cad+="Operador Aritmetico: "+tokenTipo2+"\n";
            }

            String tokenTipo3 = matcher.group(3);
            if(tokenTipo3 != null){
                cad+="Operador de Asignación: "+tokenTipo3+"\n";
            }

            String tokenTipo4 = matcher.group(4);
            if(tokenTipo4 != null){
                cad+="Operador Relacional: "+tokenTipo4+"\n";
            }

            String tokenTipo5 = matcher.group(5);
            if(tokenTipo5 != null){
                cad+="Operador Lógico: "+tokenTipo5+"\n";
            }

            String tokenTipo6 = matcher.group(6);
            if(tokenTipo6 != null){
                cad+="Signo de Agrupación: "+tokenTipo6+"\n";
            }

            String tokenTipo7 = matcher.group(7);
            if(tokenTipo7 != null){
                cad+="Signo de Puntuación: "+tokenTipo7+"\n";
            }

            String tokenTipo8 = matcher.group(8);
            if(tokenTipo8 != null){
                cad+="Función Especial: "+tokenTipo8+"\n";
            }

            String tokenTipo9 = matcher.group(9);
            if(tokenTipo9 != null){
                cad+="Número: "+tokenTipo9+"\n";
            }

            String tokenTipo10 = matcher.group(10);
            if(tokenTipo10 != null){
                cad+="Identificador: "+tokenTipo10+"\n";
            }
        }
        AreaComponentesL.setText(cad);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JTextArea areaCodigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel numCol;
    private javax.swing.JLabel numFilas;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
