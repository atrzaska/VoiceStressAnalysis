package org.vsa;

import org.vsa.gui.MainWindow;

/**
 * Main
 */
public class Main {

    /**
     * main
     * 
     * @param args 
     */
    public static void main(String args[]) {
        System.out.println("start");
        System.out.println("----------------------------------------------------------------------------------");

        // create gui
        System.out.println("tworze gui");
        System.out.println("----------------------------------------------------------------------------------");

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
* For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
*/

        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
// javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());

// for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
// if ("Nimbus".equals(info.getName())) {
// javax.swing.UIManager.setLookAndFeel(info.getClassName());
// break;
// }
// }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
// java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

}