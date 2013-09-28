/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devfortress.model.facade;

import devfortress.view.MainView;
import javax.swing.JFrame;

/**
 *
 * @author Mr Dat
 */
public class DevFortressMain {

    static public DevFortressEngine model;
    static public MainView mainView;

    public static void main(String[] args) {
        model = new DevFortressEngine();
        mainView = new MainView(); 
        
        model.addObserver(mainView);
    }
}
