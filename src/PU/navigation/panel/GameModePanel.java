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

/**
 *
 * @author davidsantiagobarrera
 */
public class GameModePanel extends JPanel {
    private final ImageIcon fondo;
    private final JButton pve;
    private final JButton pvp;
    private final JButton online;
    private final JButton back;

    public GameModePanel(ImageIcon fondo, NavigationController nController) {
        this.fondo = fondo;
        setLayout(null);
        
        pve = new JButton("Survival");
        pve.setBounds(250, 375, 100, 25);
        pve.addActionListener(nController);
        
        pvp = new JButton("Duel");
        pvp.setBounds(250, 400, 100, 25);
        pvp.addActionListener(nController);
        
        online = new JButton("Online");
        online.setBounds(250, 425, 100, 25);
        online.addActionListener(nController);
        
        back = new JButton("Back");
        back.setBounds(250, 450, 100, 25);
        back.addActionListener(nController);
        
        add(pve);
        add(pvp);
        add(online);
        add(back);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, null);
    }
}
