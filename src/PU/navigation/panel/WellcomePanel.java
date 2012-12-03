/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PU.navigation.panel;

import PU.navigation.NavigationController;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author davidsantiagobarrera
 */
public class WellcomePanel extends JPanel {

    private final JButton start;
    private final JButton story;
    private final ImageIcon fondo;
    private final JButton credits;
    private final JButton control;

    public WellcomePanel(ImageIcon fondo, NavigationController nController) {
        this.fondo = fondo;
        setLayout(null);
        start = new JButton("Game mode");
        start.setBounds(250, 375, 100, 25);
        start.addActionListener(nController);
        
        story = new JButton("Story");
        story.setBounds(250, 400, 100, 25);
        story.addActionListener(nController);
        
        control = new JButton("Controls");
        control.setBounds(250, 425, 100, 25);
        control.addActionListener(nController);

        credits = new JButton("Credits");
        credits.setBounds(250, 450, 100, 25);
        credits.addActionListener(nController);
        
        add(start);
        add(story);
        add(control);
        add(credits);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, null);
    }
}
