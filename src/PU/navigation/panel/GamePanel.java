/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation.panel;

import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author davidsantiagobarrera
 */
public class GamePanel extends JPanel{

    public GamePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public GamePanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }
}
