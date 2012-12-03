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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author AitorC
 */
public class LoginPanel extends JPanel {

    private final JTextField userT;
    private final JTextField passT;
    private final JLabel user;
    private final JLabel pass;
    private final JButton login;
    private final JButton register;
    private final ImageIcon fondo;

    public LoginPanel(ImageIcon fondo, NavigationController nController) {
        this.fondo = fondo;
        setLayout(null);
        userT = new JTextField(10);
        userT.setText("UserPrueba");
        userT.setBounds(300, 400, 150, 22);
        
        passT = new JTextField(10);
        passT.setBounds(300, 425, 150, 22);
        
        user = new JLabel("User:");
        user.setForeground(Color.white);
        user.setBounds(240, 400, 100, 25);
        
        pass = new JLabel("Password:");
        pass.setForeground(Color.white);
        pass.setBounds(240, 425, 100, 25);
        
        
        login = new JButton("Login");
        login.addActionListener(nController);
        login.setBounds(450, 400, 100, 25);
        
        register = new JButton("Register");
        register.addActionListener(nController);
        register.setBounds(450, 425, 100, 25);
        
        add(user);
        add(userT);
        add(pass);
        add(passT);
        add(login);
        add(register);
    }

    public JTextField getUser() {
        return userT;
    }

    public JTextField getPass() {
        return passT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo.getImage(), 0, 0, null);
    }
}
