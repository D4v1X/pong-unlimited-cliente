/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation.panel;

import PU.navigation.NavigationController;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author davidsantiagobarrera
 */
public class ControlsPanel extends JPanel {

    private final ImageIcon fondo;
    private final JButton back;
    private final JTextPane tp;

    public ControlsPanel(ImageIcon fondo, NavigationController nController) {
        this.fondo = fondo;
        setLayout(null);
        back = new JButton("Back");
        back.setBounds(250, 450, 100, 25);
        back.addActionListener(nController);

        tp = new JTextPane();
        tp.setText("Player 1:\n"
                + "     UP   = Flecha Arriba\n"
                + "     Down = Flecha Abajo\n"
                + "Player 2:\n"
                + "     UP   = Tecla W\n"
                + "     Down = Tecla S\n"
                + "Pause = Esc\n"
                + "Retry = Teclas R\n");
        tp.setForeground(Color.white);
        tp.setBounds(220, 305, 160, 141);
        tp.setBackground(Color.black);
        add(tp);
        add(back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, null);
    }
}
