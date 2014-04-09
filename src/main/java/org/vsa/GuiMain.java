package org.vsa;

import org.vsa.gui.MainWindow;

/**
 * GuiMain
 */
public class GuiMain {
    
    /**
     * main
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        // create gui
        System.out.println("tworze gui");
        System.out.println("----------------------------------------------------------------------------------");

        // set look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
//            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }

        // display form
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
        //</editor-fold>
    }
}
