/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.profcristianoaf81.appswingjpa.applicationMain;

import javax.swing.SwingUtilities;
import br.com.profcristianoaf81.appswingjpa.views.CarWindowClass;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author cristianoaf81
 */
@SuppressWarnings("Convert2Lambda")
public class MainClassApp {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                CarWindowClass Window = new CarWindowClass();
                Window.show();
            } catch (ParseException ex) {
                Logger.getLogger(
                        MainClassApp.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        });
    
    }
}
