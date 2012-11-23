package PU;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import PU.navigation.NavigationController;
import javax.swing.JApplet;

/**
 *
 * @author David
 */
@SuppressWarnings("serial")
public class Principal extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */

    @Override
    public void init() {
        this.setSize(600, 500);
        setFocusable(true);
        NavigationController nController =  new NavigationController(this);
    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void start() {
        System.out.println("Ejecutando start()");
    }

    @Override
    public void stop() {
        System.out.println("Ejecutando stop()");
    }

    @Override
    public void destroy() {
        System.out.println("Ejecutando init()");
    }
}

