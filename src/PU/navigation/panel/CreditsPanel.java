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
 * @author AitorC
 */
public class CreditsPanel extends JPanel{
    
    private final ImageIcon fondo;
    private final JButton back;
    private final JTextPane tp;

    public CreditsPanel(ImageIcon fondo, NavigationController nController) {
        this.fondo = fondo;
        setLayout(null);
        back = new JButton("Back");
        back.setBounds(250, 450, 100, 25);
        back.addActionListener(nController);

        tp = new JTextPane();
        
         tp.setText("Grupo Santiago Cardona (SC)\n"
         + "Asignatura:\n"
         + "Aquitectura del software 2013\n"
         + "Profesor :\n"
         + "Javier Sanchéz Peréz\n"
         + "Desarrolladores:\n"
         + "David Santiago Barrera\n"
         + "Aitor Cardona López\n");

        tp.setForeground(Color.white);
        tp.setBounds(220, 310, 250, 130);
        tp.setBackground(Color.black);
        tp.setEnabled(false);

        add(tp);
        add(back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, null);
    }
}
