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
public class StoryPanel extends JPanel {

    private final ImageIcon fondo;
    private final JButton back;
    private final JTextPane tp;

    public StoryPanel(ImageIcon fondo, NavigationController nController) {
        this.fondo = fondo;
        setLayout(null);
        back = new JButton("Back");
        back.setBounds(250, 450, 100, 25);
        back.addActionListener(nController);

        tp = new JTextPane();
        /*
         tp.setText("Once upon a time in a forbidden\n"
         + "galaxy, two lords of void, one evil,\n"
         + "one psyco, created an overwhelming\n"
         + "battle to death just to amuse\n"
         + "theirseft. I was call PONGUnlimited.");*/
        tp.setText("Once upon a time in a forbidden galaxy, two lords of void, "
                + "one evil, one psyco, created an overwhelming battle to death "
                + "just to amuse themseft. I was called PONGUnlimited.");
        tp.setForeground(Color.white);
        tp.setBounds(175, 350, 250, 100);
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
